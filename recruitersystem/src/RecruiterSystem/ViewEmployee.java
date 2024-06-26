package RecruiterSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import javax.swing.table.JTableHeader;

public class ViewEmployee extends JFrame implements ActionListener {

    JTable table;
    Choice cemployeeId, filterChoice;
    JButton search, print, update, back, highScoreButton;
    JScrollPane jsp;
    JTextField searchField; // Added JTextField for manual search

    ViewEmployee() {
        initializeUI();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        Font font = new Font("Arial", Font.PLAIN, 16); // You can adjust the font family and size as needed

        table.setFont(font);

        // Additionally, if you want to increase the header font size:
        JTableHeader header = table.getTableHeader();
        header.setFont(font);
    }

    private void initializeUI() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JLabel searchlbl = new JLabel("Search by Candidate Id");
        topPanel.add(searchlbl);
        
        cemployeeId = new Choice();
        topPanel.add(cemployeeId);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT cadId FROM employee ORDER BY cadId ASC"); // Sort by cadId in ascending order
            while (rs.next()) {
                cemployeeId.add(rs.getString("cadId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchField = new JTextField(10); // Added JTextField for manual search
        topPanel.add(searchField);

        search = new JButton("Search");
        search.addActionListener(this);
        topPanel.add(search);

        print = new JButton("Print");
        print.addActionListener(this);
        topPanel.add(print);

        update = new JButton("Update");
        update.addActionListener(this);
        topPanel.add(update);

        back = new JButton("Back");
        back.addActionListener(this);
        topPanel.add(back);

        highScoreButton = new JButton("Apply Filter");
        highScoreButton.addActionListener(this);
        topPanel.add(highScoreButton);

        filterChoice = new Choice();
        filterChoice.add("High");
        filterChoice.add("Low");
        filterChoice.add("Top 5");
        filterChoice.add("Top 10");
        filterChoice.add("Reset"); // Added Reset option
        topPanel.add(filterChoice);

        add(topPanel, BorderLayout.NORTH);

        table = new JTable();

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT *, (tenthPercentage + twelfth_percentage + Grad_percentage) / 3 AS totalScore FROM employee ORDER BY CAST(cadId AS UNSIGNED) ASC"); // Sort by cadId numerically
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Modify column names here
        table.getColumnModel().getColumn(0).setHeaderValue("CADID");
        table.getColumnModel().getColumn(1).setHeaderValue("NAME");
        table.getColumnModel().getColumn(2).setHeaderValue("FATHER NAME");
        table.getColumnModel().getColumn(3).setHeaderValue("GENDER");
        table.getColumnModel().getColumn(4).setHeaderValue("DOB");
        table.getColumnModel().getColumn(5).setHeaderValue("PHONE");
        table.getColumnModel().getColumn(6).setHeaderValue("EMAIL");
        table.getColumnModel().getColumn(7).setHeaderValue("EDUCATION");
        table.getColumnModel().getColumn(8).setHeaderValue(" 10 % ");
        table.getColumnModel().getColumn(9).setHeaderValue(" 12 % ");
        table.getColumnModel().getColumn(10).setHeaderValue("GRADUATION %");
        table.getColumnModel().getColumn(11).setHeaderValue("TOTAL SCORE");
        // Add more columns if needed

        jsp = new JScrollPane(table);
        add(jsp, BorderLayout.CENTER);

        setSize(900, 700);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String selectedId = cemployeeId.getSelectedItem();
            String enteredId = searchField.getText();
            String query = "SELECT *, (tenthPercentage + twelfth_percentage + Grad_percentage) / 3 AS totalScore FROM employee";

            if (!enteredId.isEmpty()) {
                query += " WHERE cadId = '" + enteredId + "'";
            } else if (selectedId != null) {
                query += " WHERE cadId = '" + selectedId + "'";
            }

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateEmployee(cemployeeId.getSelectedItem());
        } else if (ae.getSource() == highScoreButton) {
            String selectedFilter = filterChoice.getSelectedItem();
            String query = "";
            if (selectedFilter.equals("High")) {
                query = "SELECT *, (tenthPercentage + twelfth_percentage + Grad_percentage) / 3 AS totalScore FROM employee ORDER BY totalScore DESC";
            } else if (selectedFilter.equals("Low")) {
                query = "SELECT *, (tenthPercentage + twelfth_percentage + Grad_percentage) / 3 AS totalScore FROM employee ORDER BY totalScore ASC";
            } else if (selectedFilter.equals("Top 5")) {
                query = "SELECT *, (tenthPercentage + twelfth_percentage + Grad_percentage) / 3 AS totalScore FROM employee ORDER BY totalScore DESC LIMIT 5";
            } else if (selectedFilter.equals("Top 10")) {
                query = "SELECT *, (tenthPercentage + twelfth_percentage + Grad_percentage) / 3 AS totalScore FROM employee ORDER BY totalScore DESC LIMIT 10";
            } else if (selectedFilter.equals("Reset")) { // Handle reset
                query = "SELECT *, (tenthPercentage + twelfth_percentage + Grad_percentage) / 3 AS totalScore FROM employee ORDER BY CAST(cadId AS UNSIGNED) ASC";
            }
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new ViewEmployee();
    }
}
