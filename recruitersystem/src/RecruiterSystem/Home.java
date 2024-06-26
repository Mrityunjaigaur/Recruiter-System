package RecruiterSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    JButton view, add, update, remove;
    ImageIcon backgroundImageIcon;
    JLabel background;

    Home() {
        setTitle("Recruiter System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true); // Allow resizing
        setSize(800, 500);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        backgroundImageIcon = new ImageIcon(getClass().getResource("/icons/home.jpg"));
        background = new JLabel(backgroundImageIcon);
        mainPanel.add(background, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for better alignment
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing between buttons

        addButtons(buttonPanel, gbc);
        mainPanel.add(buttonPanel, BorderLayout.WEST);

        add(mainPanel);
        setVisible(true);
    }

    private void addButtons(Container container, GridBagConstraints gbc) {
        add = new JButton("Add Candidate");
        add.addActionListener(this);
        customizeButton(add);
        container.add(add, gbc);

        gbc.gridy++;
        view = new JButton("View Candidates");
        view.addActionListener(this);
        customizeButton(view);
        container.add(view, gbc);

        gbc.gridy++;
        update = new JButton("Update Candidate");
        update.addActionListener(this);
        customizeButton(update);
        container.add(update, gbc);

        gbc.gridy++;
        remove = new JButton("Remove Candidate");
        remove.addActionListener(this);
        customizeButton(remove);
        container.add(remove, gbc);
    }

    private void customizeButton(JButton button) {
        button.setBackground(new Color(59, 89, 182)); // Blue button color
        button.setForeground(Color.WHITE); // White text color
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(200, 40)); // Set preferred size
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            setVisible(false);
            new AddEmployee();
        } else if (ae.getSource() == view) {
            setVisible(false);
            new ViewEmployee();
        } else if (ae.getSource() == update) {
            setVisible(false);
            new ViewEmployee();
        } else {
            setVisible(false);
            new RemoveEmployee();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Home());
    }
}

