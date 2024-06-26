package RecruiterSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JTextField tfUsername, tfAddUsername;
    JPasswordField pfPassword, pfAddPassword;
    JPanel loginPanel, addUserPanel, removeUserPanel;
    CardLayout cardLayout;

    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Create the card layout and main panel
        cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        // Login panel
        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(40, 20, 100, 30);
        loginPanel.add(lblUsername);
        tfUsername = new JTextField();
        tfUsername.setBounds(150, 20, 150, 30);
        loginPanel.add(tfUsername);
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(40, 70, 100, 30);
        loginPanel.add(lblPassword);
        pfPassword = new JPasswordField();
        pfPassword.setBounds(150, 70, 150, 30);
        loginPanel.add(pfPassword);
        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(150, 140, 150, 30);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);
        mainPanel.add(loginPanel, "login");

        // Add user panel
        addUserPanel = new JPanel();
        addUserPanel.setLayout(null);
        JLabel lblAddUsername = new JLabel("Username");
        lblAddUsername.setBounds(40, 20, 100, 30);
        addUserPanel.add(lblAddUsername);
        tfAddUsername = new JTextField();
        tfAddUsername.setBounds(150, 20, 150, 30);
        addUserPanel.add(tfAddUsername);
        JLabel lblAddPassword = new JLabel("Password");
        lblAddPassword.setBounds(40, 70, 100, 30);
        addUserPanel.add(lblAddPassword);
        pfAddPassword = new JPasswordField();
        pfAddPassword.setBounds(150, 70, 150, 30);
        addUserPanel.add(pfAddPassword);
        JButton addUserButton = new JButton("Add User");
        addUserButton.setBounds(150, 140, 150, 30);
        addUserButton.setBackground(Color.BLACK);
        addUserButton.setForeground(Color.WHITE);
        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = tfAddUsername.getText();
                String password = new String(pfAddPassword.getPassword());
                if (!username.isEmpty() && !password.isEmpty()) {
                    addUserToDatabase(username, password);
                    JOptionPane.showMessageDialog(null, "User added successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter username and password");
                }
            }
        });
        addUserPanel.add(addUserButton);
        mainPanel.add(addUserPanel, "addUser");

        // Remove user panel
        removeUserPanel = new JPanel();
        removeUserPanel.setLayout(null);
        JLabel lblRemoveUsername = new JLabel("Username");
        lblRemoveUsername.setBounds(40, 20, 100, 30);
        removeUserPanel.add(lblRemoveUsername);
        JTextField tfRemoveUsername = new JTextField();
        tfRemoveUsername.setBounds(150, 20, 150, 30);
        removeUserPanel.add(tfRemoveUsername);
        JButton removeUserButton = new JButton("Remove User");
        removeUserButton.setBounds(150, 70, 150, 30);
        removeUserButton.setBackground(Color.BLACK);
        removeUserButton.setForeground(Color.WHITE);
        removeUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = tfRemoveUsername.getText();
                if (!username.isEmpty()) {
                    removeUserFromDatabase(username);
                    JOptionPane.showMessageDialog(null, "User removed successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter username");
                }
            }
        });
        removeUserPanel.add(removeUserButton);
        mainPanel.add(removeUserPanel, "removeUser");

        add(mainPanel, BorderLayout.CENTER);

        // Card layout control buttons
        JPanel controlPanel = new JPanel();
        JButton loginControlButton = new JButton("Login");
        JButton addUserControlButton = new JButton("Add User");
        JButton removeUserControlButton = new JButton("Remove User");
        loginControlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "login");
            }
        });
        addUserControlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "addUser");
            }
        });
        removeUserControlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "removeUser");
            }
        });
        controlPanel.add(loginControlButton);
        controlPanel.add(addUserControlButton);
        controlPanel.add(removeUserControlButton);
        add(controlPanel, BorderLayout.SOUTH);

        setTitle("User Management System");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String username = tfUsername.getText();
            String password = new String(pfPassword.getPassword());

            Conn c = new Conn();
            String query = "select * from login where username = '" + username + "' and password = '" + password + "'";

            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                setVisible(false);
                new Home();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
                setVisible(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addUserToDatabase(String username, String password) {
        try {
            Conn c = new Conn();
            String query = "insert into login(username, password) values('" + username + "', '" + password + "')";
            c.s.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void removeUserFromDatabase(String username) {
        try {
            Conn c = new Conn();
            String query = "delete from login where username = '" + username + "'";
            c.s.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
