package quanlyvayvoncuasinhvientrongnganhang;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectData {
	String url = "jdbc:sqlserver://localhost:1433;databaseName=StudentLoanManagement;encrypt=true;trustServerCertificate=true;";
	String username = "sa";
	String password = "123456789";
	
	Connection conn; // Đối tượng Connection được sử dụng để quản lý kết nối với cơ sở dữ liệu.
	
	
	public void ConnectDatabase() {
		try {
			if (conn == null || conn.isClosed())
			//Kiểm tra nếu đối tượng conn là null hoặc đã đóng (conn.isClosed()), nếu đúng thì tạo kết nối mới bằng DriverManager.getConnection(url, username, password).
			{
				conn = DriverManager.getConnection(url, username, password);
			}
			System.out.println("Kết nối thành công!");
		} catch (Exception e) { // Nếu gặp lỗi trong quá trình kết nối, ngoại lệ sẽ được bắt và in ra thông báo lỗi ("Kết nối thất bại!").
			e.printStackTrace();
			System.out.println("Kết nối thất bại!");
		} 
	}
	
	
	public void DisconnectDatabase() {
	    try {
	        if (conn != null && !conn.isClosed()) {
	            conn.close();
	            System.out.println("Ngắt kết nối thành công!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Nếu có lỗi trong quá trình ngắt kết nối, ngoại lệ sẽ được bắt và in ra thông báo lỗi ("Ngắt kết nối thất bại!").
	        System.out.println("Ngắt kết nối thất bại!");
	    }
	}
	
	public static void main(String[] args) {
		ConnectData con = new ConnectData();
	}

}
