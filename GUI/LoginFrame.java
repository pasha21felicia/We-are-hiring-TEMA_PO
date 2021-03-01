package grafic;

import temaPOO.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class LoginFrame extends JFrame implements ActionListener  {
    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");


    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        setFontandColor();
    }

    public void setLayoutManager() {
        container.setLayout(null);
        container.setBackground(new Color(248,241,241)); //culoarea de background
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
    }
    public void setFontandColor() {
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        userTextField.setFont(new Font("Calibri Light", Font.PLAIN, 15));
        passwordField.setFont(new Font("Calibri Light", Font.PLAIN, 15));
        loginButton.setFont(new Font("Calibri Light", Font.PLAIN, 15));
        resetButton.setFont(new Font("Calibri Light", Font.PLAIN, 15));
        userLabel.setForeground(new Color(25,69,107)); //culoarea 3
        passwordLabel.setForeground(new Color(25,69,107)); //culoarea 3
        userTextField.setForeground(new Color(25,69,107));
        passwordField.setForeground(new Color(25,69,107));
        loginButton.setBackground(new Color(25,69,107));
        loginButton.setForeground(Color.white);
        resetButton.setBackground(new Color(25,69,107));
        resetButton.setForeground(Color.white);
        showPassword.setForeground(new Color(25,69,107));
    }
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        Application app = Application.getInstance();
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            ArrayList<Company> com = app.getCompanies();

            if (userText.equalsIgnoreCase("admin") && pwdText.equalsIgnoreCase("12345")) {
                JOptionPane.showMessageDialog(this, "Admin Login Successful");
                this.dispose();
                AdminPage window = new AdminPage();
                window.getContentPane();
            }
//            if (!userText.equalsIgnoreCase("manager") || !userText.equalsIgnoreCase("admin")) {
//                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
//            }
            for (int i = 0; i < com.size(); i++) {
                Company company = com.get(i);
                Manager m = company.getManager();
                String username = m.getUsername();
                String password = m.getPassword();
                if (pwdText.equals(password) && username.equalsIgnoreCase("manager")) {
                    JOptionPane.showMessageDialog(this, "Manager Login Successful");
                    this.dispose();
                    ManagerPage window = new ManagerPage(m);
                    window.getContentPane();
                }
            }
            for (int i = 0; i < app.getUsers().size();i++) {
                User user = app.getUsers().get(i);
                String username = user.getUsername();
                String password = user.getPassword();
                if (pwdText.equals(password) && username.contains("user")) {
                    JOptionPane.showMessageDialog(this, "User Login Successful");
                    this.dispose();
                    UserPage window = new UserPage(user.getResume().getInfo().getNume());
                    window.getContentPane();
                }
            }
        }
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
    }
    public static void main(String[] args) throws Exception {
        LoginFrame frame = new LoginFrame();
        Test.main(args);
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setResizable(false);

    }

}