package org.example.jspassignment.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Định nghĩa các thông số kết nối tới database
    private static final String URL = "jdbc:mysql://localhost:3306/demo-database"; // URL kết nối tới MySQL
    private static final String USER = "root";  // Username mặc định của MySQL
    private static final String PASSWORD = "";  // Mật khẩu của người dùng (trong XAMPP thường là chuỗi rỗng)

    // Phương thức kết nối
    public static Connection getConnection() throws SQLException {
        // Đăng ký Driver MySQL (không cần nếu sử dụng Java 6 trở lên)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // Cái này là để nạp driver MySQL vào JVM
        } catch (ClassNotFoundException e) {
            System.out.println("Driver không tìm thấy: " + e.getMessage());
            e.printStackTrace();
        }

        // Tạo kết nối đến database và trả về Connection object
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Phương thức để kiểm tra kết nối
    public static void testConnection() {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Kết nối thành công!");
            }
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại: " + e.getMessage());
        }
    }

    // Main method để kiểm tra kết nối
    public static void main(String[] args) {
        // Kiểm tra kết nối
        testConnection();
    }
}
