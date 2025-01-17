package quanlyvayvoncuasinhvientrongnganhang;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.*;
import java.util.List;

public class StudentLoanManagementJava extends JFrame {

	private CardLayout cardLayout;
	private JPanel mainPanel;
	private final JComboBox comboBox_1 = new JComboBox();
	private final JButton btnNewButton = new JButton("Thông tin cá nhân");
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private DefaultTableModel tableModel;
	private DefaultTableModel tableModel2;
	private static JFrame frame;
	JPanel personalInfoPanel;
	JPanel loanPanel;
	JPanel reportPanel;
	JPanel searchPanel;
	StudentRepository stRepo = new StudentRepository();
	JComboBox<String> hoTen;
	DefaultTableModel tableModel4;
	
    JLabel totalLoanAmountLabel = new JLabel("Tổng số tiền cho vay:");
    JLabel totalAmountValueLabel = new JLabel("0 VNĐ");
    JLabel totalNumberOfLoansLabel = new JLabel("Tổng số khoản vay:");
    JLabel totalLoansValueLabel = new JLabel("0");
    JLabel averageLoanAmountLabel = new JLabel("Trung bình mỗi khoản vay:");
    JLabel averageAmountValueLabel = new JLabel("0 VNĐ");
	
	public StudentLoanManagementJava() {
		setTitle("Student Loan Management System");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		// Tiêu đề giao diện 
		JLabel titleLabel = new JLabel("Quản lý vay vốn của sinh viên", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(titleLabel, BorderLayout.NORTH);
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
        // Tạo bảng điều hướng bên trái
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));//Sắp xếp các thành phần theo chiều dọc (trục Y).
		leftPanel.setPreferredSize(new Dimension(150, getHeight())); //Đặt chiều rộng cố định là 150px, chiều cao tự động.
		leftPanel.setBackground(Color.LIGHT_GRAY);
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		//Tạo các nút điều hướng
		JButton personalInfoButton = new JButton("Thông tin cá nhân");
		JButton loanButton = new JButton("Khoản vay");
		JButton reportButton = new JButton("Báo cáo");
		JButton searchButton = new JButton("Tìm kiếm");
		JButton logoutButton = new JButton("Đăng xuất");

		// Đặt căn chỉnh nút
		personalInfoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		loanButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		reportButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Đặt nền và màu chữ
		personalInfoButton.setBackground(new Color(100, 149, 237)); // Màu xanh dương nhạt
        personalInfoButton.setForeground(Color.WHITE); // Màu chữ trắng
        loanButton.setBackground(new Color(100, 149, 237)); 
        loanButton.setForeground(Color.WHITE);
        reportButton.setBackground(new Color(100, 149, 237));
        reportButton.setForeground(Color.WHITE);
        searchButton.setBackground(new Color(60, 179, 113));
        searchButton.setForeground(Color.WHITE);
        logoutButton.setBackground(new Color(255, 69, 0));
        logoutButton.setForeground(Color.WHITE);
        
        // Thêm hiệu ứng di chuột vào các nút
        personalInfoButton.setFocusPainted(false);
        loanButton.setFocusPainted(false);
        reportButton.setFocusPainted(false);
        searchButton.setFocusPainted(false);
        logoutButton.setFocusPainted(false);

		// Thêm các nút vào leftPanel
		leftPanel.add(Box.createVerticalGlue()); // Thêm một khoảng trống co giãn dọc vào leftPanel
		leftPanel.add(personalInfoButton); // Thêm nút personalInforButton vào leftPanel
		leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Thêm một khoảng trống cố định vào panel (chiều rộng 0 chiều cao là 20 pixel)
		leftPanel.add(loanButton);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		leftPanel.add(reportButton);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		leftPanel.add(searchButton);
		leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		leftPanel.add(logoutButton);
		leftPanel.add(Box.createVerticalGlue());

		// Main Panel with CardLayout
		//Tạo và quản lý các giao diện chính 
		mainPanel = new JPanel();
		cardLayout = new CardLayout();
		mainPanel.setLayout(cardLayout);

		// Tạo bảng điều khiển riêng cho từng phần
		personalInfoPanel = createPersonalInfoPanel();
		loanPanel = createLoanPanel();
		reportPanel = createReportPanel();
		searchPanel = createSearchPanel();

		// Thêm các panel con vào mainPanel với một từ khóa duy nhất 
		mainPanel.add(personalInfoPanel, "PersonalInfo");
		mainPanel.add(loanPanel, "Loan");
		mainPanel.add(searchPanel, "Search");
		mainPanel.add(reportPanel, "Report");

		// Gán sự kiện cho các nút điều hướng 
		personalInfoButton.addActionListener(e -> cardLayout.show(mainPanel, "PersonalInfo")); // được gọi để hiển thị panel tương ứng
		loanButton.addActionListener(e -> cardLayout.show(mainPanel, "Loan"));
		searchButton.addActionListener(e -> cardLayout.show(mainPanel, "Search"));
		// khi bấm nút giao diện chuyển sang thẻ report và thực hiện một số tác vụ bổ sung
		reportButton.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(mainPanel, "Report"); // Hiển thị thẻ report trong mainPanel
				updateReportSummary(totalAmountValueLabel, totalLoansValueLabel, averageAmountValueLabel);
//				updateReportSummary(...): Gọi một phương thức cập nhật các thông tin tóm tắt báo cáo
				loadDataToTable(tableModel4); // Nạp dữ liệu vào bảng JTable
			}
		});
		logoutButton.addActionListener(e -> System.exit(0));

		// Thêm vào cửa sổ và hiển thị
		add(leftPanel, BorderLayout.WEST);
		add(mainPanel, BorderLayout.CENTER);

		setVisible(true);
		refreshTableModel();
		refreshTableModel2();
	}
	private JPanel createSearchPanel () {
		//Khởi tạo trang tìm kiếm 
		  JPanel searchPanel = new JPanel();
		    searchPanel.setLayout(new BorderLayout()); 
		    searchPanel.setBackground(Color.WHITE);
		    searchPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		    // Panel trên cùng với ô nhập liệu và nút tìm kiếm
		    JPanel topPanel = new JPanel();
		    topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		    JLabel searchLabel = new JLabel("Nhập cccd: ");
		    JTextField searchField = new JTextField(20); 
		    JButton searchBtn = new JButton("Tìm kiếm");
		    searchBtn.setBackground(new Color(60, 179, 113));
		    searchBtn.setForeground(Color.WHITE);
		    
		    // Thêm các thành phần vào topPanel
		    topPanel.add(searchLabel);
		    topPanel.add(searchField);
		    topPanel.add(searchBtn);

		    // Bảng hiển thị kết quả tìm kiếm
		    String[] columnNames = {"cccd", "Tên", "Khoản vay", "Số tiền vay","Số điện thoại", "Giới tính","Thời gian vay"};
		    JTable searchTable = new JTable(new DefaultTableModel(columnNames, 0)); // JTable searchTable: Tạo bảng sử dụng DefaultTableModel với tiêu đề cột là columnNames và không có dữ liệu ban đầu.
		    JScrollPane scrollPane = new JScrollPane(searchTable); //Thêm bảng vào khung cuộn để hỗ trợ xem dữ liệu dài.

		    searchPanel.add(topPanel, BorderLayout.NORTH);
		    searchPanel.add(scrollPane, BorderLayout.CENTER);
		    
		    searchBtn.addActionListener(e -> { 
		        String cccd = searchField.getText().trim(); // Lấy dữ liệu nhập từ trường tìm kiếm
		        if (!cccd.isEmpty()) { //  Kiểm tra nếu người dùng đã nhập số CCCD.
		            List<Object[]> searchResults = stRepo.searchByCCCD(cccd); // Gọi phương thức searchByCCCD từ stRepo (lớp StudentRepository) để tìm dữ liệu tương ứng với số CCCD.
		            DefaultTableModel model = (DefaultTableModel) searchTable.getModel(); //  Lấy mô hình dữ liệu của bảng searchTable.
		            model.setRowCount(0); // Xóa các hàng cũ
		            for (Object[] row : searchResults) { // Thêm từng hàng trong danh sách kết quả (searchResults) vào bảng.
		                model.addRow(row); // Thêm dữ liệu mới
		            }
		            if (searchResults.isEmpty()) { // Kiểm tra nếu không tìm thấy kết quả nào.
		                JOptionPane.showMessageDialog(frame, "Không tồn tại dữ liệu" );
		            }
		        } else {
		            JOptionPane.showMessageDialog(frame, "Vui lòng nhập CCCD để tìm kiếm!");
		        }
		    });


		    return searchPanel;
		}
	
		

	private JPanel createPersonalInfoPanel() {
		
		// khởi tạo panel
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(240, 240, 240)); // Đặt màu nền xám nhạt
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JTextField textField = new JTextField();
        textField.setBounds(150, 50, 250, 30);
        textField.setBackground(new Color(255, 255, 255));
        textField.setForeground(Color.DARK_GRAY);
        panel.add(textField);
        
        //Họ và tên 
		JLabel lblNewLabel = new JLabel("Họ và tên");
		lblNewLabel.setBounds(50, 50, 100, 30);
		panel.add(lblNewLabel);

		// Số điện thoại 
		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setBounds(50, 90, 100, 30);
		panel.add(lblSinThoi);

		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(150, 90, 250, 30);
		panel.add(textField_1);

		// Giới tính
		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setBounds(50, 130, 100, 30);
		panel.add(lblGiiTnh);

		JRadioButton rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setBounds(150, 130, 70, 30);
		panel.add(rdbtnNam);

		JRadioButton rdbtnNu = new JRadioButton("Nữ");
		rdbtnNu.setBounds(230, 130, 70, 30);
		panel.add(rdbtnNu);

		ButtonGroup genderGroup = new ButtonGroup(); // Nhóm các nút radio để đảm bảo chỉ một tùy chọn được chọn
		genderGroup.add(rdbtnNam);
		genderGroup.add(rdbtnNu);

		// day of birth
		JLabel lblDob = new JLabel("DOB");
		lblDob.setBounds(50, 170, 100, 30);
		panel.add(lblDob);

		String[] days = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		JComboBox<String> dayBox = new JComboBox<>(days);
		dayBox.setBounds(150,170,50,25);
		panel.add(dayBox);
		
		String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

		JComboBox<String> monthBox = new JComboBox<>(months);
		monthBox.setBounds(210,170,70,25);
		panel.add(monthBox);
		
		String[] years = {"1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025"};
		JComboBox<String> yearBox = new JComboBox<>(years);
		yearBox.setBounds(290,170,80,25);
		panel.add(yearBox);

		// Căn cước công dân
		JLabel lblCccd = new JLabel("CCCD");
		lblCccd.setBounds(50, 210, 100, 30);
		panel.add(lblCccd);

		JTextField textFieldCccd = new JTextField();
		textFieldCccd.setColumns(10);
		textFieldCccd.setBounds(150, 210, 250, 30);
		panel.add(textFieldCccd);

		// Địa chỉ 
		JLabel lblaCh = new JLabel("Địa chỉ");
		lblaCh.setBounds(50, 250, 100, 30);
		panel.add(lblaCh);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(150, 250, 250, 60);
		panel.add(textArea);

		// Bảng hiển thị
		String[] columnNames = { "CCCD", "Họ và tên", "Số điện thoại", "Giới tính", "DOB", "Địa chỉ" };
		tableModel = new DefaultTableModel(columnNames, 0); // Dữ liệu mô hình bảng
		JTable table = new JTable(tableModel); // Hiển thị thông tin sinh viên 
		JScrollPane scrollPane = new JScrollPane(table); // Cho phép cuộn bảng khi dữ liệu vượt quá kích thước
		scrollPane.setBounds(50, 330, 550, 200);
		panel.add(scrollPane);

		// Nút thêm
		JButton addStudent = new JButton("Add");
		addStudent.setBounds(450, 100, 150, 30);
		panel.add(addStudent);

		// Nút Sửa
		JButton updateRecord = new JButton("Update");
		updateRecord.setBounds(450, 150, 150, 30);
		panel.add(updateRecord);

		// Nút Xóa
		JButton deleteRecord = new JButton("Delete");
		deleteRecord.setBounds(450, 200, 150, 30);
		panel.add(deleteRecord);

		// Sự kiện cho nút "Add"
		addStudent.addActionListener(e -> {
			String gender = rdbtnNam.isSelected() ? "Nam" : rdbtnNu.isSelected() ? "Nữ" : ""; // Xác định giới tính của sinh viên dựa trên trạng thái của hai nút radio (rdbtnNam và rdbtnNu).
			String Dob = dayBox.getSelectedItem() + "/" + monthBox.getSelectedItem() + "/" + yearBox.getSelectedItem(); //Lấy ngày sinh (Dob) từ các combobox dayBox, monthBox, và yearBox.
			Student st = new Student(0, textField.getText(), textField_1.getText(), gender, Dob,
					textFieldCccd.getText(), textArea.getText()); //Tạo một đối tượng Student mới với các thông tin từ giao diện.
			if (stRepo.InsertStudent(st)) { // Kiểm tra xem việc thêm sinh viên vào cơ sở dữ liệu có thành công hay không.
				JOptionPane.showMessageDialog(frame, "Added successfully!");
				refreshTableModel(); // Gọi refreshTableModel() để cập nhật bảng hiển thị danh sách sinh viên.
			} else {
				JOptionPane.showMessageDialog(frame, "Retry.");
			}
			getStudentNameToJComboBox();// Tải lại danh sách tên sinh viên vào combobox sau khi thêm sinh viên mới.

		});
		updateRecord.addActionListener(e -> {
			int selectedRow = table.getSelectedRow(); //Lấy chỉ số của hàng được chọn trong bảng table.

			if (selectedRow != -1) { //Kiểm tra xem người dùng có chọn một hàng trong bảng hay không.

				String value = (String) table.getValueAt(selectedRow, 0); //Lấy giá trị của cột đầu tiên trong hàng được chọn.
				String Dob = dayBox.getSelectedItem() + "/" + monthBox.getSelectedItem() + "/" + yearBox.getSelectedItem();

				System.out.println("Value in column 0: " + value);
				String gender = rdbtnNam.isSelected() ? "Nam" : rdbtnNu.isSelected() ? "Nữ" : "";
				Student st = new Student(0, textField.getText(), textField_1.getText(), gender, Dob,
						textFieldCccd.getText(), textArea.getText()); // Tạo một đối tượng Student mới với các thông tin được nhập từ giao diện.
				if (stRepo.UpdateStudent(st)) { // Kiểm tra việc cập nhật bản ghi trong cơ sở dữ liệu.
					JOptionPane.showMessageDialog(frame, "Updated successfully!");
					refreshTableModel();
				} else {
					JOptionPane.showMessageDialog(frame, "Retry.");
				}

				JOptionPane.showMessageDialog(frame, "Updated successfully!");
			} else {
				JOptionPane.showMessageDialog(frame, "Please select a row to update.");
			}
		});

		// Sự kiện cho nút "Delete"
		deleteRecord.addActionListener(e -> {
			int selectedRow = table.getSelectedRow(); //Lấy chỉ số của hàng được chọn trong bảng table.
			if (selectedRow != -1) {
				
				String value = (String) table.getValueAt(selectedRow, 0); //Lấy giá trị của cột đầu tiên trong hàng được chọn.
				
				if (stRepo.DeleteStudent(value)) { // Kiểm tra xem việc xóa bản ghi trong cơ sở dữ liệu có thành công hay không.
					JOptionPane.showMessageDialog(frame, "Delete successfully!");
					refreshTableModel();
				} else {
					JOptionPane.showMessageDialog(frame, "Retry.");
				}

			} else {
				JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
			}
		});

		// Chọn dòng trên bảng
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = table.getSelectedRow(); // Lấy hàng đã chọn
				if (selectedRow != -1) { // Kiểm tra xem hàng đã được chọn hay chưa
					// Lấy giá trị từ cột 0 (e.g., ID)
					String cccd = (String) table.getValueAt(selectedRow, 0);
					String hoten = (String) table.getValueAt(selectedRow, 1);
					String sdt = (String) table.getValueAt(selectedRow, 2);
					String gioi_tinh = (String) table.getValueAt(selectedRow, 3);
					String dia_chi = (String) table.getValueAt(selectedRow, 5);
					String DOB = (String) table.getValueAt(selectedRow, 4);
					
					String[] date = DOB.split("/");
					
					// Cập nhật combobox ngày sinh
					dayBox.setSelectedItem(date[0]); // Ngày (DD)
					monthBox.setSelectedItem( date[1]); // Tháng (MM)
					yearBox.setSelectedItem(date[2]); // Năm (YYYY)
					textFieldCccd.setText(cccd);
					textField.setText(hoten);
					textField_1.setText(sdt);
					textArea.setText(dia_chi);

				}
			}
		});
		return panel;

	}

	private JPanel createLoanPanel() {
		
		// Khởi tạo panel
		JPanel panel = new JPanel();
		panel.setLayout(null); // Set layout to null for absolute positioning
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JTextField textField = new JTextField();
		textField.setBounds(150, 50, 250, 30); 
		panel.add(textField);
		textField.setColumns(10);

		// Mã khoản vay
		JLabel lblNewLabel = new JLabel("Mã khoản vay");
		lblNewLabel.setBounds(50, 50, 100, 30);
		panel.add(lblNewLabel);

		// Số tiền vay
		JLabel lblSinThoi = new JLabel("Số tiền vay VNĐ");
		lblSinThoi.setBounds(50, 90, 100, 30); 
		panel.add(lblSinThoi);

		JTextField textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(150, 90, 250, 30); 
		panel.add(textField_1);

		// Kỳ hạn vay
		JLabel lblSinThoi_1 = new JLabel("Kỳ hạn vay");
		lblSinThoi_1.setBounds(50, 130, 100, 30); 
		panel.add(lblSinThoi_1);

		JTextField textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(150, 130, 250, 30); 
		panel.add(textField_2);

		// Lãi suất vay
		JLabel lblGiiTnh_1 = new JLabel("Lãi suất vay (%)");
		lblGiiTnh_1.setBounds(50, 170, 100, 30); // y = 170
		panel.add(lblGiiTnh_1);

		JTextField textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(150, 170, 250, 30); // y = 170
		panel.add(textField_3);

		// Thời gian vay
		JLabel lblDob = new JLabel("Thời gian vay");
		lblDob.setBounds(50, 210, 100, 30); // y = 210
		panel.add(lblDob);

		String[] days = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		JComboBox<String> dayBox = new JComboBox<>(days);
		dayBox.setBounds(150,210,60,30);
		panel.add(dayBox);
		String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

		JComboBox<String> monthBox = new JComboBox<>(months);
		monthBox.setBounds(220,210,60,30);
		panel.add(monthBox);
		
		String[] years = {"1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024","2025"};
		JComboBox<String> yearBox = new JComboBox<>(years);
		yearBox.setBounds(290,210,80,30);
		panel.add(yearBox);

		// Nhu cầu vay
		JLabel lblaCh = new JLabel("Nhu cầu vay");
		lblaCh.setBounds(50, 250, 100, 30); // y = 250
		panel.add(lblaCh);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(150, 250, 250, 60); // Tọa độ x giống các textField, chiều cao tăng lên
		panel.add(textArea);
		
		String[] columnNames = {"Mã khoản vay", "Số tiền vay VNĐ", "Kỳ hạn", "Lãi suất (%)", "Thời gian", "Nhu cầu" };
		tableModel2 = new DefaultTableModel(columnNames, 0);
		JTable table2 = new JTable(tableModel2);
		JScrollPane scrollPane = new JScrollPane(table2);
		scrollPane.setBounds(50, 330, 550, 200);
		panel.add(scrollPane);

		JButton addInfor = new JButton("Add");
		addInfor.setBounds(450, 50, 150, 30);
		panel.add(addInfor);
		
		// Khai báo để tìm kiếm cccd
		hoTen = new JComboBox<>();
		hoTen.setBounds(450,200,150,30);
		panel.add(hoTen);
		getStudentNameToJComboBox();
		
		addInfor.addActionListener(e -> {
		    String thoiGian = dayBox.getSelectedItem() + "/" + monthBox.getSelectedItem() + "/" + yearBox.getSelectedItem();
            Information infor = new Information(hoTen.getSelectedItem().toString(), Integer.parseInt(textField.getText()), Double.parseDouble(textField_1.getText()),
					textField_2.getText(),Double.parseDouble(textField_3.getText()),thoiGian, textArea.getText()); // Tạo đối tượng Information với các thông tin được lấy từ giao diện.
				if (stRepo.InsertInformation(infor)) {
					JOptionPane.showMessageDialog(frame, "Added successfully!");
					refreshTableModel2(); //  Làm mới dữ liệu hiển thị trong bảng liên quan đến thông tin (Information).
				} else {
					JOptionPane.showMessageDialog(frame, "Retry.");
				}
		});
		

		// Nút Sửa
		JButton updateRecord = new JButton("Update");
		updateRecord.setBounds(450, 100, 150, 30);
		panel.add(updateRecord);
		

		updateRecord.addActionListener(e -> {
			int selectedRow = table2.getSelectedRow(); // Lấy chỉ số của hàng được chọn trong bảng table2.

			if (selectedRow != -1) {

				String value = (String) table2.getValueAt(selectedRow, 0); // Lấy giá trị trong cột đầu tiên (có thể là mã ID hoặc một giá trị quan trọng khác) của hàng đã chọn trong bảng table2.
				String thoiGian = dayBox.getSelectedItem() + "/" + monthBox.getSelectedItem() + "/" + yearBox.getSelectedItem();
				System.out.println("Value in column 0: " + value);			
				Information infor = new Information( hoTen.getSelectedItem().toString(), Integer.parseInt(textField.getText()), Double.parseDouble(textField_1.getText()),
						textField_2.getText(),Double.parseDouble(textField_3.getText()),thoiGian, textArea.getText()); // Tạo một đối tượng Information với các giá trị lấy từ các trường nhập liệu của người dùng.
				if (stRepo.UpdateInformation(infor)) { // Kiểm tra xem việc cập nhật thông tin (Information) có thành công hay không.
					JOptionPane.showMessageDialog(frame, "Updated successfully!");
					refreshTableModel();
				} else {
					JOptionPane.showMessageDialog(frame, "Retry.");
				}

				JOptionPane.showMessageDialog(frame, "Updated successfully!");
			} else {
				JOptionPane.showMessageDialog(frame, "Please select a row to update.");
			}
		});

		// Nút Xóa
		JButton deleteRecord = new JButton("Delete");
		deleteRecord.setBounds(450, 150, 150, 30);
		panel.add(deleteRecord);
		
		deleteRecord.addActionListener(e -> {
			int selectedRow = table2.getSelectedRow();
			if (selectedRow != -1) {
				
				int value = (int) table2.getValueAt(selectedRow, 0); // Lấy giá trị trong cột đầu tiên (có thể là ID của bản ghi) của hàng đã chọn trong bảng table2.
				
				if (stRepo.DeleteInformation(value)) {
					JOptionPane.showMessageDialog(frame, "Delete successfully!");
					refreshTableModel2();
				} else {
					JOptionPane.showMessageDialog(frame, "Retry.");
				}

			} else {
				JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
			}
		});
		
		table2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = table2.getSelectedRow(); // Get the selected row
				if (selectedRow != -1) { // Check if a row is selected
					// Get value from column 0 (e.g., ID)
					
					textField.setText(Integer.toString((int) table2.getValueAt(selectedRow,0)));
					textField_1.setText(Double.toString((double) table2.getValueAt(selectedRow,1)));
					textField_2.setText((String)table2.getValueAt(selectedRow,2));
					textField_3.setText(Double.toString((double)table2.getValueAt(selectedRow,3)));
					textArea.setText((String)table2.getValueAt(selectedRow,5));
					
					String DATE = (String) table2.getValueAt(selectedRow, 4);
					
					String[] date = DATE.split("/");
					
					dayBox.setSelectedItem(date[0]);
					monthBox.setSelectedItem( date[1]);
					yearBox.setSelectedItem(date[2]);
				}
			}
		});
		

		return panel;

	}
	
	private void getStudentNameToJComboBox() {
		List<Student> students = stRepo.getAllStudent(); // Lấy danh sách tất cả các sinh viên từ kho dữ liệu thông qua phương thức getAllStudent() của đối tượng stRepo.
		for (Student item : students) {
		    hoTen.addItem(item.getCccd()); // Thêm giá trị của cccd của từng sinh viên vào JComboBox có tên hoTen
		}
	}

	private JPanel createReportPanel() {
		// Tạo panel chính
		JPanel panel = new JPanel(new BorderLayout());
	    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Giữ nguyên padding
	    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

	    // Bảng báo cáo 
	    tableModel4 = new DefaultTableModel(new String[]{"Mã khoản vay", "Số tiền vay"}, 0);
	    JTable reportTable = new JTable(tableModel4);
	    JScrollPane scrollPane = new JScrollPane(reportTable);
	    panel.add(scrollPane, BorderLayout.CENTER);

	    // 3. Panel thống kê (Thông tin tổng quan) 
	    JPanel summaryPanel = new JPanel(new GridBagLayout());
	    summaryPanel.setBorder(BorderFactory.createTitledBorder("Thống kê")); // Thêm một tiêu đề "Thống kê" cho panel.
	    GridBagConstraints gbc = new GridBagConstraints(); // Khởi tạo các thuộc tính cho các thành phần con trong layout:
	    gbc.insets = new Insets(5, 5, 5, 5);
	    gbc.anchor = GridBagConstraints.WEST;

	    // Thêm các thành phần vào summaryPanel
	    gbc.gridx = 0; gbc.gridy = 0; summaryPanel.add(totalLoanAmountLabel, gbc); // totalLoanAmountLabel: Tên thống kê cho tổng số tiền vay.
	    gbc.gridx = 1; gbc.gridy = 0; summaryPanel.add(totalAmountValueLabel, gbc);// totalAmountValueLabel: Giá trị tương ứng cho tổng số tiền vay.
	    gbc.gridx = 0; gbc.gridy = 1; summaryPanel.add(totalNumberOfLoansLabel, gbc);// Tên thống kê cho tổng số khoản vay.
	    gbc.gridx = 1; gbc.gridy = 1; summaryPanel.add(totalLoansValueLabel, gbc); // totalLoansValueLabel: Giá trị tương ứng cho tổng số khoản vay.
	    gbc.gridx = 0; gbc.gridy = 2; summaryPanel.add(averageLoanAmountLabel, gbc);// averageLoanAmountLabel: Tên thống kê cho số tiền vay trung bình.
	    gbc.gridx = 1; gbc.gridy = 2; summaryPanel.add(averageAmountValueLabel, gbc);// averageAmountValueLabel: Giá trị tương ứng cho số tiền vay trung bình.

	    panel.add(summaryPanel, BorderLayout.SOUTH);

	    // 4. Cập nhật dữ liệu báo cáo sau khi được panel tạo
	    updateReportSummary(totalAmountValueLabel, totalLoansValueLabel, averageAmountValueLabel); // Cập nhật các bảng thống kê bằng các giá trị tính toán từ dữ liệu
	    loadDataToTable(tableModel4); // Tải dữ liệu vào bảng báo cáo tableModel4.
	    return panel;
	}
	private void loadDataToTable(DefaultTableModel tableModel) {
	    try {
	        // Xóa dữ liệu cũ trong bảng
	        tableModel.setRowCount(0);

	        // Lấy dữ liệu từ cơ sở dữ liệu
	        stRepo.ConnectDatabase(); // Kết nối đến cơ sở dữ liệu thông qua một phương thức ConnectDatabase() trong đối tượng stRepo.
	        String sql = "SELECT ma_khoan_vay, so_tien_vay FROM INFORMATION"; // Tạo một câu lệnh SQL để truy vấn dữ liệu từ bảng INFORMATION, với hai cột: ma_khoan_vay (Mã khoản vay) và so_tien_vay (Số tiền vay).
	        ResultSet rs = stRepo.conn.createStatement().executeQuery(sql); // Thực thi câu lệnh SQL và trả về một ResultSet chứa các bản ghi từ cơ sở dữ liệu.

	        // Thêm từng bản ghi vào bảng
	        while (rs.next()) { // Duyệt qua các bản ghi trong ResultSet và thêm dữ liệu vào tableModel để hiển thị trên bảng.
	            Object[] rowData = {
	                rs.getString("ma_khoan_vay"),
	                rs.getDouble("so_tien_vay")
	            };
	            tableModel.addRow(rowData);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Bắt các lỗi có thể xảy ra trong quá trình kết nối cơ sở dữ liệu và truy vấn dữ liệu. Nếu có lỗi, sẽ in ra thông tin chi tiết của lỗi thông qua e.printStackTrace().
	    } finally {
	        stRepo.DisconnectDatabase(); // Đảm bảo ngắt kết nối với cơ sở dữ liệu sau khi hoàn tất việc truy xuất và cập nhật dữ liệu vào bảng.
	    }
	}

	private void updateReportSummary(JLabel totalAmountValueLabel, JLabel totalLoansValueLabel, JLabel averageAmountValueLabel) {
	    double totalLoanAmount = stRepo.getTotalLoanAmount();
	    int totalLoanCount = stRepo.getTotalLoanCount();
	    double averageLoanAmount = totalLoanCount > 0 ? totalLoanAmount / totalLoanCount : 0;

	    totalAmountValueLabel.setText(String.format("%.2f VNĐ", totalLoanAmount)); // Cập nhật totalAmountValueLabel với giá trị tổng số tiền vay, được định dạng với 2 chữ số thập phân và thêm đơn vị "VNĐ".
	    totalLoansValueLabel.setText(String.valueOf(totalLoanCount)); // Cập nhật totalLoansValueLabel với tổng số khoản vay, chuyển đổi giá trị totalLoanCount thành chuỗi.
	    averageAmountValueLabel.setText(String.format("%.2f VNĐ", averageLoanAmount)); // Cập nhật averageAmountValueLabel với số tiền vay trung bình, cũng được định dạng với 2 chữ số thập phân và đơn vị "VNĐ".
	}

	public void refreshTableModel() {
		List<Student> students = stRepo.getAllStudent(); // Gọi phương thức getAllStudent() từ đối tượng stRepo để lấy tất cả các sinh viên từ cơ sở dữ liệu. Phương thức này trả về một danh sách các đối tượng Student.
		tableModel.setRowCount(0); // Xóa các hàng cũ

		// Thêm dữ liệu mới vào tableModel
		for (Student student : students) {
			Object[] rowData = { student.getCccd(), student.getHo_ten(), student.getSdt(), student.getGioi_tinh(),
					student.getNgay_sinh(), student.getDia_chi() }; // Mỗi sinh viên sẽ được biểu diễn dưới dạng một mảng đối tượng (Object[])
			tableModel.addRow(rowData); // Thêm một hàng mới vào tableModel
		}
	}
	
	public void refreshTableModel2() {
		List<Information> informations = stRepo.getAllInformation();
		tableModel2.setRowCount(0); // Xóa các hàng cũ

		// Thêm dữ liệu mới vào tableModel
		for (Information information : informations) {
			Object[] rowData = { information.getMaVay(), information.getSoTien(), information.getKyHan(), information.getLaiSuat(),
					information.getThoiGian(), information.getNhuCau() };
			tableModel2.addRow(rowData); // Thêm một hàng mới vào tableModel
		}
	}

	public static void main(String[] args) {
		new StudentLoanManagementJava();
	}
}
