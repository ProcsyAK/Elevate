
import java.sql.*;
import java.util.Scanner;

public class EmployeeApp {

    private static void addEmployee(Connection conn, String name, String dept, double salary) throws Exception {
        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, dept);
        ps.setDouble(3, salary);
        ps.executeUpdate();
        System.out.println("Employee added successfully!");
    }

    private static void viewEmployees(Connection conn) throws Exception {
        String sql = "SELECT * FROM employees";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("ID | Name | Department | Salary");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getString("department") + " | " +
                    rs.getDouble("salary"));
        }
    }

    private static void updateEmployee(Connection conn, int id, double salary) throws Exception {
        String sql = "UPDATE employees SET salary=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, salary);
        ps.setInt(2, id);
        int rows = ps.executeUpdate();
        if (rows > 0) System.out.println("Employee updated!");
        else System.out.println("Employee not found.");
    }

    private static void deleteEmployee(Connection conn, int id) throws Exception {
        String sql = "DELETE FROM employees WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        int rows = ps.executeUpdate();
        if (rows > 0) System.out.println("Employee deleted!");
        else System.out.println("Employee not found.");
    }

    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n--- Employee Database Menu ---");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee Salary");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter department: ");
                        String dept = sc.nextLine();
                        System.out.print("Enter salary: ");
                        double salary = sc.nextDouble();
                        addEmployee(conn, name, dept, salary);
                        break;
                    case 2:
                        viewEmployees(conn);
                        break;
                    case 3:
                        System.out.print("Enter employee ID: ");
                        int id = sc.nextInt();
                        System.out.print("Enter new salary: ");
                        double newSalary = sc.nextDouble();
                        updateEmployee(conn, id, newSalary);
                        break;
                    case 4:
                        System.out.print("Enter employee ID to delete: ");
                        int delId = sc.nextInt();
                        deleteEmployee(conn, delId);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";
    private static final String PASSWORD = "0000";

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}