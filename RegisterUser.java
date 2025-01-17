package quanlyvayvoncuasinhvientrongnganhang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class RegisterUser extends JFrame {
    // Các thành phần giao diện
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JTextField employeeNameField;
    private JButton registerButton;
    private JButton cancelButton;
    private JButton exitButton;
    StudentRepository stRepo = new StudentRepository();

    public RegisterUser() {
    	setTitle("Đăng ký User");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Căn giữa cửa sổ
        setResizable(false);
        setLayout(null); // Sử dụng null layout

        // Tiêu đề
        JLabel titleLabel = new JLabel("Đăng ký", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setBounds(100, 10, 200, 30); // Căn giữa theo chiều ngang
        add(titleLabel);

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 60, 100, 30);
        add(usernameLabel);

        usernameField = new JTextField(15);
        usernameField.setBounds(160, 60, 180, 30);
        add(usernameField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        add(passwordLabel);

        passwordField = new JPasswordField(15);
        passwordField.setBounds(160, 100, 180, 30);
        add(passwordField);

        // Role
        JLabel roleLabel = new JLabel("Quyền:");
        roleLabel.setBounds(50, 140, 100, 30);
        add(roleLabel);

        String[] roles = {"Admin", "User", "Manager"};
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(160, 140, 180, 30);
        add(roleComboBox);

        // Employee Name
        JLabel employeeNameLabel = new JLabel("Tên Nhân Viên:");
        employeeNameLabel.setBounds(50, 180, 100, 30);
        add(employeeNameLabel);

        employeeNameField = new JTextField(15);
        employeeNameField.setBounds(160, 180, 180, 30);
        add(employeeNameField);

        // Buttons
        registerButton = new JButton("Đăng ký");
        registerButton.setBounds(50, 230, 100, 30);
        registerButton.setBackground(Color.GREEN);
        add(registerButton);

        cancelButton = new JButton("Hủy bỏ");
        cancelButton.setBounds(160, 230, 100, 30);
        cancelButton.setBackground(Color.ORANGE);
        add(cancelButton);

        exitButton = new JButton("Thoát");
        exitButton.setBounds(270, 230, 100, 30);
        exitButton.setBackground(Color.RED);
        add(exitButton);


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword()); // Lấy giá trị từ trường mật khẩu passwordField (có thể là một JPasswordField) và chuyển đổi sang kiểu String. Đây là cách để lấy mật khẩu người dùng đã nhập.
                String role = (String) roleComboBox.getSelectedItem();
                String employeeName = employeeNameField.getText(); // Lấy giá trị từ trường tên nhân viên employeeNameField.
//                dispose();
//                LoginFrame ad = new LoginFrame();
//                ad.main(null);
                
                if (username.isEmpty() || password.isEmpty() || employeeName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Đăng ký thành công!\nUsername: " + username + "\nQuyền: " + role, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                if (stRepo.InsertRegister(new Register(username, password, role))) { // Kiểm tra xem các trường bắt buộc (username, password, employeeName) có bị bỏ trống không.
    				JOptionPane.showMessageDialog(null, "Added successfully!");
    				LoginFrame lg = new LoginFrame(); 
    	            lg.setVisible(true); 
    	            dispose();
    			} else {
    				JOptionPane.showMessageDialog(null, "Retry.");
    			}
            }
            
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
                employeeNameField.setText("");
                roleComboBox.setSelectedIndex(0);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegisterUser().setVisible(true);
            }
        });
    }
    
   
}

