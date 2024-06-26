package RecruiterSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;




public class Splash extends JFrame {

    Splash() {
        getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background
        setLayout(null);

        JLabel heading = new JLabel("RECRUITER SYSTEM");
        heading.setBounds(150, 50, 900, 60); // Adjusted heading position
        heading.setFont(new Font("Arial", Font.BOLD, 40)); // Modern font
        heading.setForeground(new Color(70, 130, 180)); // Navy blue color
        heading.setHorizontalAlignment(SwingConstants.CENTER); // Center align
        add(heading);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/view.jpg"));
        Image image = imageIcon.getImage().getScaledInstance(900, 500, Image.SCALE_SMOOTH); // Adjusted image size
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(135, 120, 900, 500); // Adjusted image position
        add(imageLabel);

        JButton Button = new JButton("Click Here");
        Button.setBounds(510, 650, 150, 40); // Adjusted button position
        Button.setFont(new Font("Arial", Font.BOLD, 18)); // Button font
        Button.setBackground(new Color(70, 130, 180)); // Navy blue background color
        Button.setForeground(Color.WHITE); // White text color
        Button.setFocusPainted(false); // Remove focus border
        Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login();
            }
        });
        add(Button);

        setSize(1170, 800); // Adjusted frame size
        setLocationRelativeTo(null);
        setUndecorated(true); // Remove window decorations for a cleaner look
        setVisible(true);
    }

    public static void main(String args[]) {
        new Splash();
    }
}
