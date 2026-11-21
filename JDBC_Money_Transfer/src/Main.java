
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Connection con = null;

        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/BankDB",
                    "root",
                    "PIYUSH"
            );

            con.setAutoCommit(false);

            int fromAccount = 1;
            int toAccount = 2;
            double amount = 10000;

            String check = "SELECT balance FROM accounts WHERE id = ? FOR UPDATE";

            PreparedStatement ps1 = con.prepareStatement(check);
            ps1.setInt(1, fromAccount);

            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Sender account not found");
            }

            double balance = rs.getDouble("balance");

            if (balance < amount) {
                System.out.println("Insufficient balance!");
                con.rollback();
                return;
            }

            String debit = "UPDATE accounts SET balance = balance - ? WHERE id = ? AND balance >= ?";

            PreparedStatement ps2 = con.prepareStatement(debit);

            ps2.setDouble(1, amount);
            ps2.setInt(2, fromAccount);
            ps2.setDouble(3, amount);

            int r1 = ps2.executeUpdate();

            if (r1 != 1) {
                throw new SQLException("Debit failed");
            }

            String credit = "UPDATE accounts SET balance = balance + ? WHERE id = ?";

            PreparedStatement ps3 = con.prepareStatement(credit);

            ps3.setDouble(1, amount);
            ps3.setInt(2, toAccount);

            int r2 = ps3.executeUpdate();

            if (r2 != 1) {
                throw new SQLException("Credit failed");
            }

            String history = "INSERT INTO transaction_history(from_account, to_account, amount) VALUES (?, ?, ?)";

            PreparedStatement ps4 = con.prepareStatement(history);

            ps4.setInt(1, fromAccount);
            ps4.setInt(2, toAccount);
            ps4.setDouble(3, amount);

            int r3 = ps4.executeUpdate();

            if (r3 != 1) {
                throw new SQLException("Transaction history failed");
            }

            con.commit();

            System.out.println("Money transferred successfully!");

            rs.close();
            ps1.close();
            ps2.close();
            ps3.close();
            ps4.close();

        } catch (SQLException e) {

            try {
                if (con != null) {
                    con.rollback();
                    System.out.println("Transaction rolled back!");
                }
            } catch (SQLException ex) {
                System.out.println("Rollback failed: " + ex.getMessage());
            }

            System.out.println("Error: " + e.getMessage());

        } finally {

            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            sc.close();
        }
    }
}
