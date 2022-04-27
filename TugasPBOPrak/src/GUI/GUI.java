package GUI;

import javax.swing.*;

//import com.mysql.cj.xdevapi.Statement;

//import com.mysql.cj.PreparedQuery;

import Connection.Connector;

import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GUI extends JFrame {

    private static JPanel panel;
    private static JFrame frame;
    

    private static JLabel userLabelLogin;
    private static JTextField userTextLogin;
    private static JLabel passwordLabelLogin;
    private static JPasswordField passwordTextLogin;
    private static JButton loginButton;


    private static JLabel userLabelRegister;
    private static JTextField userTextRegister;
    private static JLabel passwordLabelRegister;
    private static JPasswordField passwordTextRegister;
    private static JButton registerButton;

    final private static Font mainFont = new Font("Roboto", Font.BOLD, 13);

    public void initialize() {
        Connector connector = new Connector();

        panel = new JPanel();
        frame = new JFrame();
        
        // Set Size
        frame.setSize(600, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setTitle("M Fawwaz Firjatullah 123200148");

        // Set Color
        panel.setLayout(null);
        panel.setBackground(Color.white);

        // LOGIN
        userLabelLogin = new JLabel("Username");
        userLabelLogin.setBounds(10,20,80,25);
        panel.add(userLabelLogin);
        userLabelLogin.setFont(mainFont);
        userLabelLogin.setForeground(Color.black);

        userTextLogin = new JTextField(20);
        userTextLogin.setBounds(100,20,165,25);
        panel.add(userTextLogin);

        passwordLabelLogin = new JLabel("Password");
        passwordLabelLogin.setBounds(10,60,80,25);
        panel.add(passwordLabelLogin);
        passwordLabelLogin.setFont(mainFont);
        passwordLabelLogin.setForeground(Color.black);

        passwordTextLogin = new JPasswordField();
        passwordTextLogin.setBounds(100,60,165,25);
        panel.add(passwordTextLogin);

        loginButton = new JButton("Login");
        loginButton.setBounds(140,105,80,25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int jumlah = 0;
                String username = userTextLogin.getText();
                String password = new String(passwordTextLogin.getPassword());
                Boolean berhasilLogin = false;
                try { 
                    String data[][] = new String[100][3];
                    String query = "select * from users";
                    PreparedStatement pstmt = connector.koneksi.prepareStatement(query);
                    connector.statement = connector.koneksi.createStatement();
                    ResultSet resultSet = pstmt.executeQuery(query);
                    while (resultSet.next()) {
                        data[jumlah][0] = String.valueOf(resultSet.getInt("id"));
                        data[jumlah][1] = resultSet.getString("username");
                        data[jumlah][2] = resultSet.getString("password");
                        if((data[jumlah][1].equals(username)) && (data[jumlah][2].equals(password))) {
                            berhasilLogin = true;
                            break;
                        }
                        jumlah++;
                    }
                    if (berhasilLogin == true) {
                        JOptionPane.showMessageDialog(frame, "Login Berhasil!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Login Gagal!");
                        JOptionPane.showMessageDialog(frame, "Invalid Username/Password!");
                    }
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                    JOptionPane.showMessageDialog(frame, "Gagal Login!");
                }
            }
        });
        panel.add(loginButton);
        loginButton.setForeground(Color.black);

        // REGISTER
        userLabelRegister = new JLabel("Username");
        userLabelRegister.setBounds(310,20,80,25);
        panel.add(userLabelRegister);
        userLabelRegister.setFont(mainFont);
        userLabelRegister.setForeground(Color.black);

        userTextRegister = new JTextField(20);
        userTextRegister.setBounds(400,20,165,25);
        panel.add(userTextRegister);

        passwordLabelRegister = new JLabel("Password");
        passwordLabelRegister.setBounds(310,60,80,25);
        panel.add(passwordLabelRegister);
        passwordLabelRegister.setFont(mainFont);
        passwordLabelRegister.setForeground(Color.black);

        passwordTextRegister = new JPasswordField();
        passwordTextRegister.setBounds(400,60,165,25);
        panel.add(passwordTextRegister);

        registerButton = new JButton("Register");
        registerButton.setBounds(440,105,100,25);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String query = "insert into users(username, password) values (?,?)";
                    PreparedStatement pstmt = connector.koneksi.prepareStatement(query);
                    pstmt.setString(1, getUsernameRegister());
                    pstmt.setString(2, getPasswordRegister());
                    connector.statement = connector.koneksi.createStatement();
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "Registrasi Berhasil!");
                } catch(Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        });
        panel.add(registerButton);
        registerButton.setForeground(Color.black);

        frame.setVisible(true);
    }

    public String getUsernameRegister() {
        return userTextRegister.getText();
    }

    public String getPasswordRegister() {
        return new String(passwordTextRegister.getPassword());
    }
}
