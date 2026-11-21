
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/UserDB",
                    "root",
                    "PIYUSH"
            );

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                System.out.println("All fields are required.");
                return;
            }

            String check = "SELECT * FROM users WHERE email = ?";

            PreparedStatement ps = con.prepareStatement(check);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Email already registered.");
            } else {

                String insert = "INSERT INTO users(name, email, password) VALUES(?, ?, ?)";

                PreparedStatement ps2 = con.prepareStatement(insert);

                ps2.setString(1, name);
                ps2.setString(2, email);
                ps2.setString(3, password);

                int result = ps2.executeUpdate();

                if (result > 0) {
                    System.out.println("Registration Successful!");
                }

                ps2.close();
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Email already exists!");

        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());

        } finally {
            sc.close();
        }
    }
}
