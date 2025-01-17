package quanlyvayvoncuasinhvientrongnganhang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
	
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton exitButton;
    private JButton registerButton;

    public LoginFrame() {
    	 setTitle("Hệ Thống Quản Lý Vay Vốn Của Sinh Viên Trong Ngân Hàng");
         // Thiết lập cơ bản cho JFrame
         setSize(400, 300);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setLocationRelativeTo(null); // Căn giữa màn hình

         // Panel chính
         JPanel mainPanel = new JPanel();
         mainPanel.setLayout(new BorderLayout());
         mainPanel.setBackground(new Color(240, 240, 240)); // Màu nền nhẹ

         // Tiêu đề
         JLabel titleLabel = new JLabel("Student Loan Management", SwingConstants.CENTER);
         titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
         titleLabel.setForeground(new Color(60, 63, 65));
         titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); // Khoảng cách bên trong
         mainPanel.add(titleLabel, BorderLayout.NORTH);

         // Panel chứa ô nhập liệu
         JPanel formPanel = new JPanel();
         formPanel.setLayout(null); // Dùng null layout để tùy chỉnh vị trí
         formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // Padding
         formPanel.setBackground(new Color(240, 240, 240));

         JLabel usernameLabel = new JLabel("Username:");
         usernameLabel.setBounds(50, 20, 100, 30); // Đặt vị trí và kích thước
         formPanel.add(usernameLabel);

         JTextField usernameField = new JTextField();
         usernameField.setBounds(150, 20, 200, 30);
         formPanel.add(usernameField);

         JLabel passwordLabel = new JLabel("Password:");
         passwordLabel.setBounds(50, 70, 100, 30);
         formPanel.add(passwordLabel);

         JPasswordField passwordField = new JPasswordField();
         passwordField.setBounds(150, 70, 200, 30);
         formPanel.add(passwordField);

         mainPanel.add(formPanel, BorderLayout.CENTER);

         // Panel chứa các nút
         JPanel buttonPanel = new JPanel();
         buttonPanel.setLayout(null); // Null layout để căn chỉnh chính xác
         buttonPanel.setBackground(new Color(240, 240, 240));
         buttonPanel.setPreferredSize(new Dimension(400, 80));

         JButton loginButton = new JButton("Đăng nhập");
         loginButton.setBounds(40, 20, 100, 30);
         loginButton.setBackground(new Color(60, 179, 113));
         loginButton.setForeground(Color.WHITE);
         loginButton.setFocusPainted(false);

         JButton exitButton = new JButton("Hủy");
         exitButton.setBounds(160, 20, 90, 30);
         exitButton.setBackground(new Color(255, 69, 0));
         exitButton.setForeground(Color.WHITE);
         exitButton.setFocusPainted(false);

         JButton registerButton = new JButton("Đăng ký");
         registerButton.setBounds(270, 20, 90, 30);
         registerButton.setBackground(new Color(100, 149, 237));
         registerButton.setForeground(Color.WHITE);
         registerButton.setFocusPainted(false);

         buttonPanel.add(loginButton);
         buttonPanel.add(exitButton);
         buttonPanel.add(registerButton);

         mainPanel.add(buttonPanel, BorderLayout.SOUTH);

         // Thêm mainPanel vào JFrame
         add(mainPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	StudentRepository check = new StudentRepository();
                String username = usernameField.getText(); // Lấy tên người dùng từ trường nhập liệu usernameField. Phương thức getText() trả về giá trị chuỗi đã nhập trong trường văn bản (text field).
                String password = new String(passwordField.getPassword()); // Lấy mật khẩu người dùng từ trường mật khẩu passwordField. Lưu ý rằng phương thức getPassword() trả về mảng ký tự (char[]), nên ta cần chuyển đổi nó thành chuỗi bằng new String().
                if (check.checkLogin(username, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Đăng nhập thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    new StudentLoanManagementJava(); // Mở giao diện quản lý sau khi đăng nhập
                    dispose(); // Đóng cửa sổ đăng nhập
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Sai Username hoặc Password", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        registerButton.addActionListener(e -> {
            RegisterUser registerUser = new RegisterUser(); // Khởi tạo giao diện đăng ký
            registerUser.setVisible(true); // Hiển thị giao diện
            dispose(); // Đóng giao diện đăng nhập (nếu cần)
        });

        add(mainPanel);
    }

    
    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
//        });
    }
}

