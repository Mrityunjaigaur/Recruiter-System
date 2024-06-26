package RecruiterSystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateEmployee extends JFrame implements ActionListener{
    
    JTextField tfname, tffname, tfphone, tfemail, tf10thPercentage, tf12thPercentage, tfgraduation;
    Choice genderChoice;
    JButton add, back;
    String cadId;
    
    
    
    UpdateEmployee(String cadId) {
    this.cadId = cadId;
    getContentPane().setBackground(Color.WHITE);
    setLayout(null);
    Font largerFont = new Font("Arial", Font.PLAIN, 20);
    
    JLabel heading = new JLabel("Update Candidate Detail");
    heading.setBounds(320, 30, 500, 50);
    heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
    add(heading);
    
    JLabel labelCandidateID = new JLabel("Candidate ID");
    labelCandidateID.setBounds(50, 100, 150, 30);
    labelCandidateID.setFont(new Font("serif", Font.PLAIN, 20));
    add(labelCandidateID);
    
    JLabel lblCandidateID = new JLabel();
    lblCandidateID.setText(cadId);
    lblCandidateID.setBounds(200, 100, 150, 30);
    lblCandidateID.setFont(largerFont);
    add(lblCandidateID);
    
        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 150, 150, 30);
        tfname.setFont(largerFont);
        add(tfname);
        
        JLabel labelGender = new JLabel("Gender");
        labelGender.setBounds(400, 150, 150, 30);
        labelGender.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelGender);
        
        // Use Choice for gender selection
        genderChoice = new Choice();
        genderChoice.add("Male");
        genderChoice.add("Female");
        genderChoice.add("Other");
        genderChoice.setBounds(600, 150, 150, 30);
        genderChoice.setFont(largerFont);
        add(genderChoice);
        
        JLabel labelfname = new JLabel("Father's Name");
        labelfname.setBounds(50, 200, 150, 30);
        labelfname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelfname);
        
        tffname = new JTextField();
        tffname.setBounds(200, 200, 150, 30);
        tffname.setFont(largerFont);
        add(tffname);
        
        JLabel labeldob = new JLabel("Date of Birth");
        labeldob.setBounds(400, 200, 150, 30);
        labeldob.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldob);
        
        JLabel lbldob = new JLabel();
        lbldob.setBounds(600, 200, 150, 30);
        lbldob.setFont(largerFont);
        add(lbldob);
        
        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(50, 250, 150, 30);
        labelphone.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(200, 250, 150, 30);
        tfphone.setFont(largerFont);
        add(tfphone);
        
        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(400, 250, 150, 30);
        labelemail.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(600, 250, 150, 30);
        tfemail.setFont(largerFont);
        add(tfemail);
        
        JLabel labelgraduation = new JLabel("Gradution%");
        labelgraduation.setBounds(50, 300, 150, 30);
        labelgraduation.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelgraduation);
        
        tfgraduation = new JTextField();
        tfgraduation.setBounds(200, 300, 150, 30);
        tfgraduation.setFont(largerFont);
        add(tfgraduation);
        
        JLabel label10thPercentage = new JLabel("10th %");
        label10thPercentage.setBounds(400, 300, 150, 30);
        label10thPercentage.setFont(new Font("serif", Font.PLAIN, 20));
        add(label10thPercentage);
        
        tf10thPercentage = new JTextField();
        tf10thPercentage.setBounds(600, 300, 150, 30);
        tf10thPercentage.setFont(largerFont);
        add(tf10thPercentage);
        
        JLabel label12thPercentage = new JLabel("12th %");
        label12thPercentage.setBounds(50, 350, 150, 30);
        label12thPercentage.setFont(new Font("serif", Font.PLAIN, 20));
        add(label12thPercentage);
        
        tf12thPercentage = new JTextField();
        tf12thPercentage.setBounds(200, 350, 150, 30);
        tf12thPercentage.setFont(largerFont);
        add(tf12thPercentage);
               
        try {
            Conn c = new Conn();
            String query = "select * from employee where cadId = '"+cadId+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                tfname.setText(rs.getString("name"));
                tffname.setText(rs.getString("fname"));
                lbldob.setText(rs.getString("dob"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                tfgraduation.setText(rs.getString("Grad_percentage"));
                tf10thPercentage.setText(rs.getString("tenthPercentage"));
                tf12thPercentage.setText(rs.getString("twelfth_percentage"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        add = new JButton("Update Details");
        add.setBounds(250, 550, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add(add);
        
        back = new JButton("Back");
        back.setBounds(450, 550, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        add(back);
        
        setSize(900, 700);
        setLocation(300, 50);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == add) {
        String name = tfname.getText(); // Updated line to retrieve the name
        String fname = tffname.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();
        String Grad_percentage = tfgraduation.getText();
        String tenthPercentage = tf10thPercentage.getText();
        String twelfth_percentage = tf12thPercentage.getText();
        String gender = genderChoice.getSelectedItem();
        
        try {
            Conn conn = new Conn();
            // Updated query to include name field
            String query = "update employee set name = '"+name+"', fname = '"+fname+"', phone = '"+phone+"', email = '"+email+"', Grad_percentage = '"+Grad_percentage+"', tenthPercentage =  '"+tenthPercentage+"', twelfth_percentage = '"+twelfth_percentage+"', gender = '"+gender+"' where cadId = '"+cadId+"'";
            conn.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Details updated successfully");
            setVisible(false);
            new Home();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new UpdateEmployee("");
    }
}
