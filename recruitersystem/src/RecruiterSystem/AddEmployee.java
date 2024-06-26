package RecruiterSystem;
import java.awt.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.sql.ResultSet;

public class AddEmployee extends JFrame implements ActionListener {

    JTextField tfname, tffname, tfphone, tfemail, tfCandidateID, tf10thPercentage, tf12thPercentage, tfGradPercentage;
    JDateChooser dcdob;
    JComboBox<String> cbeducation, cbGender;
    JButton add, back;

    AddEmployee() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        Font largerFont = new Font("Arial", Font.PLAIN, 20); 
        
        JLabel heading = new JLabel("Add Candidate Detail");
        heading.setBounds(320, 30, 500, 50);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        add(heading);

        JLabel labelCandidateID = new JLabel("Candidate ID");
        labelCandidateID.setBounds(50, 100, 200, 30);
        labelCandidateID.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelCandidateID);

        tfCandidateID = new JTextField();
        tfCandidateID.setBounds(250, 100, 150, 30);
        tfCandidateID.setFont(largerFont);
        add(tfCandidateID);

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50, 150, 150, 30);
        labelname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelname);

        tfname = new JTextField();
        tfname.setBounds(250, 150, 150, 30);
        tfname.setFont(largerFont);
        add(tfname);

        JLabel labelGender = new JLabel("Gender");
        labelGender.setBounds(450, 150, 150, 30);
        labelGender.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelGender);

        String[] genders = {"Male", "Female", "Other"};
        cbGender = new JComboBox<>(genders);
        cbGender.setBackground(Color.WHITE);
        cbGender.setBounds(650, 150, 150, 30);
        cbGender.setFont(largerFont);
        add(cbGender);

        JLabel labelfname = new JLabel("Father Name");
        labelfname.setBounds(50, 200, 150, 30);
        labelfname.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelfname);

        tffname = new JTextField();
        tffname.setBounds(250, 200, 150, 30);
        tffname.setFont(largerFont);
        add(tffname);

        JLabel labeldob = new JLabel("Date of Birth");
        labeldob.setBounds(450, 200, 150, 30);
        labeldob.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeldob);

        dcdob = new JDateChooser();
        dcdob.setBounds(650, 200, 150, 30);
        dcdob.setFont(largerFont);
        add(dcdob);

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(50, 250, 150, 30);
        labelphone.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelphone);

        tfphone = new JTextField();
        tfphone.setBounds(250, 250, 150, 30);
        tfphone.setFont(largerFont);
        add(tfphone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(450, 250, 150, 30);
        labelemail.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelemail);

        tfemail = new JTextField();
        tfemail.setBounds(650, 250, 150, 30);
        tfemail.setFont(largerFont);
        add(tfemail);

        JLabel labeleducation = new JLabel("Highest Education");
        labeleducation.setBounds(50, 300, 150, 30);
        labeleducation.setFont(new Font("serif", Font.PLAIN, 20));
        add(labeleducation);

        String[] courses = {"N/A", "10th", "12th", "BBA", "BCA", "BA", "BSC", "B.COM", "BTech", "MBA", "MCA", "MA", "MTech", "MSC", "PHD"};
        cbeducation = new JComboBox<>(courses);
        cbeducation.setBackground(Color.WHITE);
        cbeducation.setBounds(250, 300, 150, 30);
        cbeducation.setFont(largerFont);
        add(cbeducation);

        JLabel label10thPercentage = new JLabel("10th %");
        label10thPercentage.setBounds(450, 300, 150, 30);
        label10thPercentage.setFont(new Font("serif", Font.PLAIN, 20));
        add(label10thPercentage);

        tf10thPercentage = new JTextField();
        tf10thPercentage.setBounds(650, 300, 150, 30);
        cbeducation.setFont(largerFont);
        add(tf10thPercentage);

        JLabel label12thPercentage = new JLabel("12th %");
        label12thPercentage.setBounds(50, 350, 150, 30);
        label12thPercentage.setFont(new Font("serif", Font.PLAIN, 20));
        add(label12thPercentage);

        tf12thPercentage = new JTextField();
        tf12thPercentage.setBounds(250, 350, 150, 30);
        tf12thPercentage.setFont(largerFont);
        add(tf12thPercentage);

        JLabel labelGradPercentage = new JLabel("Graduation %");
        labelGradPercentage.setBounds(450, 350, 150, 30);
        labelGradPercentage.setFont(new Font("serif", Font.PLAIN, 20));
        add(labelGradPercentage);

        tfGradPercentage = new JTextField();
        tfGradPercentage.setBounds(650, 350, 150, 30);
        tfGradPercentage.setFont(largerFont);
        add(tfGradPercentage);

        add = new JButton("Add Details");
        add.setBounds(250, 450, 150, 40);
        add.addActionListener(this);
        add.setBackground(Color.BLACK);
        add.setForeground(Color.RED);
        add(add);

        back = new JButton("Back");
        back.setBounds(450, 450, 150, 40);
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.RED);
        add(back);

        setSize(900, 550);
        setLocation(300, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String cadId = tfCandidateID.getText();
            String name = tfname.getText();
            String fname = tffname.getText();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String education = (String) cbeducation.getSelectedItem();
            String tenthPercentage = tf10thPercentage.getText();
            String twelfthPercentage = tf12thPercentage.getText();
            String gradPercentage = tfGradPercentage.getText();
            String gender = (String) cbGender.getSelectedItem();
            try {
            Conn conn = new Conn();
            String query = "select * from employee where cadId='" + cadId + "'";
            ResultSet rs = conn.s.executeQuery(query);
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Candidate ID already exists!");
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

            // Validate percentage fields
        if (tenthPercentage.isEmpty() || twelfthPercentage.isEmpty() || gradPercentage.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Percentage fields cannot be empty!");
            return;
        }
            // Parse the percentage values to double
            double tenthPercentageValue = Double.parseDouble(tenthPercentage);
            double twelfthPercentageValue = Double.parseDouble(twelfthPercentage);
            double gradPercentageValue = Double.parseDouble(gradPercentage);

            // Calculate the total score
            double totalScore = (tenthPercentageValue + twelfthPercentageValue + gradPercentageValue) / 3;
            try {
                Conn conn = new Conn();
                String query = "insert into employee values('" + cadId + "', '" + name + "', '" + fname + "', '" + gender + "', '" + dob + "', '" + phone + "', '" + email + "', '" + education + "', '" + tenthPercentage + "', '" + twelfthPercentage + "', '" + gradPercentage + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Details added successfully. Total Score: " + totalScore);
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
        new AddEmployee();
    }
}