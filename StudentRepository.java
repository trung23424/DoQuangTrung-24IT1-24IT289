package quanlyvayvoncuasinhvientrongnganhang;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository extends ConnectData {
	public List<Student> getAllStudent() {
		List<Student> students = new ArrayList<>(); // Tạo một danh sách rỗng để chứa các đối tượng Student lấy từ cơ sở dữ liệu.
		try {
			ConnectDatabase(); // Gọi phương thức để kết nối cơ sở dữ liệu
			var stmt = conn.createStatement(); // Tạo một đối tượng Statement (stmt) từ kết nối conn để thực hiện câu lệnh SQL.
			String sql = "SELECT * FROM STUDENT"; // Xây dựng câu lệnh SQL SELECT * FROM STUDENT để lấy tất cả các bản ghi từ bảng STUDENT.
			ResultSet rs = stmt.executeQuery(sql); // Sử dụng stmt.executeQuery(sql) để thực thi câu lệnh SQL và nhận về kết quả dưới dạng ResultSet (rs).
			while (rs.next()) {
				Student st = new Student();
				st.setId(rs.getInt("id"));
				st.setHo_ten(rs.getString("ho_ten"));
				st.setSdt(rs.getString("sdt"));
				st.setGioi_tinh(rs.getString("gioi_tinh"));
				st.setNgay_sinh(rs.getString("ngay_sinh"));
				st.setCccd(rs.getString("cccd"));
				st.setDia_chi(rs.getString("dia_chi"));
				students.add(st); // Thêm đối tượng Student vào danh sách students.
			}

		} catch (Exception e) {
			e.printStackTrace(); // Nếu có lỗi xảy ra trong quá trình kết nối, truy vấn cơ sở dữ liệu hoặc xử lý kết quả, lỗi sẽ được bắt và in ra console.
		} finally {
			DisconnectDatabase(); // Sau khi hoàn thành việc truy vấn, phương thức DisconnectDatabase() được gọi để ngắt kết nối với cơ sở dữ liệu.
		}
		return students;
	}

	
	public boolean InsertStudent(Student st) {
		try {
			ConnectDatabase();
			String query = "INSERT INTO STUDENT VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(query); // PreparedStatement được sử dụng thay cho Statement để bảo vệ khỏi các lỗ hổng SQL Injection và tối ưu việc thực thi câu lệnh.
			pstmt.setInt(1, st.getId());
			pstmt.setString(2, st.getHo_ten());
			pstmt.setString(3, st.getSdt());
			pstmt.setString(4, st.getNgay_sinh());
			pstmt.setString(5, st.getGioi_tinh());
			pstmt.setString(6, st.getCccd());
			pstmt.setString(7, st.getDia_chi());

			// Thực thi câu lệnh và kiểm tra kết quả
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DisconnectDatabase();
		}
		return false;
	}
	public boolean UpdateStudent(Student st) {
	    try {
	        ConnectDatabase();
	        
	        // Use the UPDATE statement to modify an existing student
	        String query = "UPDATE STUDENT SET ho_ten = ?, sdt = ?, ngay_sinh = ?, gioi_tinh = ?, dia_chi = ? WHERE cccd = ?";
	        
	        PreparedStatement pstmt = conn.prepareStatement(query);// PreparedStatement được sử dụng thay cho Statement để bảo vệ khỏi các lỗ hổng SQL Injection và tối ưu việc thực thi câu lệnh.
	       
	        pstmt.setString(1, st.getHo_ten());
	        pstmt.setString(2, st.getSdt());
	        pstmt.setString(3, st.getNgay_sinh());
	        pstmt.setString(4, st.getGioi_tinh());
	        pstmt.setString(5, st.getDia_chi());
	        pstmt.setString(6, st.getCccd());

	        //Thực thi câu lệnh và kiểm tra kết quả
	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;  

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DisconnectDatabase();
	    }
	    return false;  
	}
	public boolean DeleteStudent(String cccd) {
	    try {
	        ConnectDatabase();
	        
	     
	        String query = "DELETE FROM STUDENT WHERE cccd = ?";
	        
	        PreparedStatement pstmt = conn.prepareStatement(query); // PreparedStatement được sử dụng thay cho Statement để bảo vệ khỏi các lỗ hổng SQL Injection và tối ưu việc thực thi câu lệnh.
	        
	       
	        pstmt.setString(1, cccd); // Dùng phương thức setString() để gán giá trị cccd cho placeholder ? trong câu lệnh SQL.

	        // Thực thi câu lệnh và kiểm tra kết quả
	        int rowsAffected = pstmt.executeUpdate(); // hương thức executeUpdate() được gọi để thực thi câu lệnh SQL DELETE.
	        return rowsAffected > 0;  

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DisconnectDatabase();
	    }
	    return false;  
	}
	
	// Truy vấn tất cả dữ liệu từ bảng INFORMATION trong cơ sở dữ liệu và trả về danh sách các đối tượng Information.
	public List<Information> getAllInformation() {
		List<Information> informations = new ArrayList<>(); // Khởi tạo danh sách để lưu trữ thông tin 
		try {
			ConnectDatabase();
			var stmt = conn.createStatement(); // Tạo một đối tượng Statement từ kết nối conn và chuẩn bị câu lệnh SQL SELECT * FROM INFORMATION để truy vấn tất cả các bản ghi từ bảng INFORMATION.
			String sql = "SELECT * FROM INFORMATION";
			ResultSet rs = stmt.executeQuery(sql);
			
			// Duyệt qua các bản ghi trong ResultSet
			while (rs.next()) { // Duyệt qua từng dòng trong ResultSet bằng cách sử dụng phương thức rs.next().
				Information infor = new Information();
				infor.setId(rs.getString ("cccd"));
				infor.setMaVay(rs.getInt("ma_khoan_vay"));
				infor.setSoTien(rs.getDouble("so_tien_vay"));
				infor.setKyHan(rs.getString("ky_han_vay"));
				infor.setLaiXuat(rs.getDouble("lai_suat"));
				infor.setThoiGian(rs.getString("thoi_gian_vay"));
				infor.setNhuCau(rs.getString("nhu_cau"));
				informations.add(infor);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DisconnectDatabase();
		}
		return informations;
	}
	
	public boolean InsertInformation (Information st) {
		try {
			ConnectDatabase();
			String query = "INSERT INTO INFORMATION VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, st.getId());
			pstmt.setInt(2, st.getMaVay());
			pstmt.setDouble(3, st.getSoTien());
			pstmt.setString(4, st.getKyHan());
			pstmt.setDouble(5, st.getLaiSuat());
			pstmt.setString(6, st.getThoiGian());
			pstmt.setString(7, st.getNhuCau());

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DisconnectDatabase();
		}
		return false;
		
	}
	public boolean UpdateInformation(Information st) {
	    try {
	        ConnectDatabase();
	        
	        // Use the UPDATE statement to modify an existing student
	        String query = "UPDATE INFORMATION SET so_tien_vay = ?, ky_han_vay = ?, lai_suat = ?, thoi_gian_vay = ?, nhu_cau = ? WHERE ma_khoan_vay = ?";
	        
	        PreparedStatement pstmt = conn.prepareStatement(query);
	       
			pstmt.setInt(6, st.getMaVay());
			pstmt.setDouble(1, st.getSoTien());
			pstmt.setString(2, st.getKyHan());
			pstmt.setDouble(3, st.getLaiSuat());
			pstmt.setString(4, st.getThoiGian());
			pstmt.setString(5, st.getNhuCau());


	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;  

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DisconnectDatabase();
	    }
	    return false;  
	}
	public boolean DeleteInformation(int maKhoanVay) {
	    try {
	        ConnectDatabase();
	        
	     
	        String query = "DELETE FROM INFORMATION WHERE ma_khoan_vay = ?";
	        
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        
	       
	        pstmt.setInt(1, maKhoanVay);

	       
	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;  

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DisconnectDatabase();
	    }
	    return false;  
	}
	public List<Object[]> searchByCCCD(String cccd) {
	    List<Object[]> results = new ArrayList<>();
	    try {
	        ConnectDatabase();
	        String query = "SELECT s.cccd, s.ho_ten, i.ma_khoan_vay, i.so_tien_vay, s.sdt, s.gioi_tinh, i.thoi_gian_vay " +
	                       "FROM STUDENT s " +
	                       "LEFT JOIN INFORMATION i ON s.cccd = i.cccd " +
	                       "WHERE s.cccd = ?";
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, cccd);
	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            Object[] row = {
	                rs.getString("cccd"),
	                rs.getString("ho_ten"),
	                rs.getInt("ma_khoan_vay"),
	                rs.getDouble("so_tien_vay"),
	                rs.getString("sdt"),
	                rs.getString("gioi_tinh"),
	                rs.getString("thoi_gian_vay")
	            };
	            results.add(row);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DisconnectDatabase();
	    }
	    return results;
	}
	
	//Thêm một bản ghi mới vào bảng ADMIN với thông tin đăng ký gồm username, password, và role.
	 public boolean InsertRegister(Register rg) {
			try {
				ConnectDatabase();
				String query = "INSERT INTO ADMIN VALUES (?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(query); // PreparedStatement được sử dụng để bảo vệ khỏi các lỗ hổng SQL Injection và tối ưu hóa việc thực thi câu lệnh.
				pstmt.setString(1, rg.getUsername());
				pstmt.setString(2, rg.getPassword());
				pstmt.setString(3, rg.getRole());

				int rowsAffected = pstmt.executeUpdate(); // executeUpdate() được gọi để thực thi câu lệnh INSERT. Phương thức này trả về số lượng bản ghi bị ảnh hưởng (inserted rows).
				return rowsAffected > 0;

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DisconnectDatabase();
			}
			return false;
		}
	 
	 public boolean LoginAdmin(Register rg) {
			try {
				ConnectDatabase();
				String query = "INSERT INTO ADMIN VALUES (?, ?, ?)";
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, rg.getUsername());
				pstmt.setString(2, rg.getPassword());
				pstmt.setString(3, rg.getRole());

				int rowsAffected = pstmt.executeUpdate();
				return rowsAffected > 0;

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DisconnectDatabase();
			}
			return false;
		}
	 public boolean checkLogin(String User, String Pass) {
		    try {
		        ConnectDatabase();
		        String query = "SELECT * FROM ADMIN WHERE USERNAME = ? AND PASSWORD = ?";
		        PreparedStatement pstmt = conn.prepareStatement(query);
		        pstmt.setString(1, User);
		        pstmt.setString(2, Pass);

		        ResultSet rs = pstmt.executeQuery();
		        return rs.next(); // Trả về true nếu có kết quả trả về từ cơ sở dữ liệu

		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        DisconnectDatabase();
		    }
		    return false; // Trả về false nếu có lỗi hoặc không tìm thấy kết quả
		}
	 public double getTotalLoanAmount() {
		    double total = 0; // Khởi tạo biến total để lưu tổng số tiền vay, mặc định là 0.
		    try {
		        ConnectDatabase();
		        // Tạo câu truy vấn SQL để tính tổng số tiền vay (sum of loan amount)
		        String sql = "SELECT SUM(so_tien_vay) AS total FROM INFORMATION";
		        var stmt = conn.createStatement(); // Tạo đối tượng Statement để thực thi câu lệnh SQL
		        ResultSet rs = stmt.executeQuery(sql); // // Thực thi câu lệnh SQL và lấy kết quả trả về
		        if (rs.next()) {
		            total = rs.getDouble("total"); // Lấy giá trị tổng số tiền vay từ cột 'total'
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        DisconnectDatabase();
		    }
		    return total;
		}

		public int getTotalLoanCount() {
		    int count = 0; // // Khởi tạo biến count để lưu tổng số lượng khoản vay, mặc định là 0.
		    try {
		        ConnectDatabase();
		        String sql = "SELECT COUNT(*) AS total FROM INFORMATION";
		        var stmt = conn.createStatement();
		        ResultSet rs = stmt.executeQuery(sql);
		        if (rs.next()) {
		            count = rs.getInt("total"); // // Lấy giá trị số lượng các khoản vay từ cột 'total'
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        DisconnectDatabase();
		    }
		    return count;
		}

}
