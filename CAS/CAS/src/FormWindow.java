import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class FormWindow extends JFrame {
	JLabel LName, LAddress, LContactNO, LEmail, LBranch, LGender, LCETMarks,
			LAppNoValue, LHSCMarks;
	JTextField TBName, TBContactNO, TBEmail, TBCETMarks, TBHSCMarks;
	JTextArea TAAddress;
	JRadioButton MaleButton, FemaleButton;
	JComboBox CBBranch;
	JRadioButton RBGender;
	JButton BSubmit, BReset;
	int AppNo;
	String text[] = new String[8];
	private JLabel lblCollegeAdmission;

	public static void main(String[] args) {
		FormWindow FWobj = new FormWindow();
	}

	public FormWindow() {

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createTable();

		JPanel FWPanel = new JPanel();
		FWPanel.setBounds(0, 0, 494, 479);

		this.setSize(500, 500);

		this.setTitle("FORM");

		getContentPane().setLayout(null);

		getContentPane().add(FWPanel);

		LName = new JLabel("Name:");
		LName.setBounds(99, 93, 38, 16);
		LAddress = new JLabel("Address:");
		LAddress.setBounds(85, 128, 51, 16);
		LContactNO = new JLabel("Contact No.:");
		LContactNO.setBounds(67, 225, 71, 16);
		LEmail = new JLabel("Email ID:");
		LEmail.setBounds(85, 257, 52, 16);
		LBranch = new JLabel("Branch:");
		LBranch.setBounds(86, 373, 51, 16);
		LGender = new JLabel("Gender:");
		LGender.setBounds(86, 286, 51, 16);
		LCETMarks = new JLabel("CET Marks:");
		LCETMarks.setBounds(71, 315, 66, 16);
		LHSCMarks = new JLabel("HSC Marks:");
		LHSCMarks.setBounds(70, 344, 67, 16);

		AppNo = NoGen() + 1;

		TBName = new JTextField(20);
		TBName.setBounds(164, 90, 206, 22);
		TBContactNO = new JTextField(10);
		TBContactNO.setBounds(165, 222, 186, 22);
		TBEmail = new JTextField();
		TBEmail.setBounds(164, 254, 187, 22);
		TBCETMarks = new JTextField(3);
		TBCETMarks.setBounds(164, 312, 66, 22);
		TBHSCMarks = new JTextField();
		TBHSCMarks.setBounds(164, 341, 66, 22);

		TAAddress = new JTextArea();
		TAAddress.setBounds(164, 125, 206, 87);

		String[] BranchArray = { "I.T.", "EXTC", "Electronics", "Mechanical",
				"Computer Science" };

		MaleButton = new JRadioButton("Male");
		MaleButton.setBounds(164, 282, 55, 25);
		FemaleButton = new JRadioButton("Female");
		FemaleButton.setBounds(230, 282, 71, 25);
		ButtonGroup Gender = new ButtonGroup();
		Gender.add(MaleButton);
		Gender.add(FemaleButton);

		BSubmit = new JButton("SUBMIT");
		BSubmit.setBounds(139, 427, 80, 25);

		BSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				makeArray();
			}
		});

		BReset = new JButton("RESET");
		BReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}

		});

		BReset.setBounds(270, 427, 71, 25);
		FWPanel.setLayout(null);

		FWPanel.add(LName);
		FWPanel.add(LAddress);
		FWPanel.add(LContactNO);
		FWPanel.add(LEmail);
		FWPanel.add(LBranch);
		FWPanel.add(LGender);
		FWPanel.add(LCETMarks);
		FWPanel.add(LHSCMarks);

		FWPanel.add(TBName);
		FWPanel.add(TBContactNO);
		FWPanel.add(TBEmail);

		FWPanel.add(TBCETMarks);
		FWPanel.add(TBHSCMarks);

		FWPanel.add(TAAddress);

		FWPanel.add(MaleButton);
		CBBranch = new JComboBox(BranchArray);
		CBBranch.setBounds(164, 370, 131, 22);

		FWPanel.add(CBBranch);
		FWPanel.add(FemaleButton);
		FWPanel.add(BSubmit);

		FWPanel.add(BReset);

		lblCollegeAdmission = new JLabel("College Admission Form");
		lblCollegeAdmission.setFont(new Font("Verdana", Font.BOLD, 23));
		lblCollegeAdmission.setBounds(86, 11, 357, 34);
		FWPanel.add(lblCollegeAdmission);

		JLabel LAppNo = new JLabel("<HTML><U>Application No. :<U><HTML>");
		LAppNo.setFont(new Font("Tahoma", Font.BOLD, 13));
		LAppNo.setBounds(165, 50, 112, 16);
		FWPanel.add(LAppNo);

		LAppNoValue = new JLabel(Integer.toString(AppNo));
		LAppNoValue.setBounds(274, 51, 56, 16);
		FWPanel.add(LAppNoValue);

		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	private void reset() {
		TBName.setText("");
		TBContactNO.setText("");
		TBEmail.setText("");
		TBCETMarks.setText("");
		TBHSCMarks.setText("");
		TAAddress.setText("");
		CBBranch.setSelectedIndex(0);
		LAppNoValue.setText(Integer.toString(NoGen() + 1));
	}

	public void makeArray() {

		int j = 0;
		text[0] = TBName.getText();
		text[1] = TAAddress.getText();
		text[2] = TBContactNO.getText();
		text[3] = TBEmail.getText();

		if (MaleButton.isSelected()) {
			text[4] = "Male";
		} else if (FemaleButton.isSelected()) {
			text[4] = "Female";
		}

		text[5] = TBCETMarks.getText();
		text[6] = TBHSCMarks.getText();
		text[7] = (String) CBBranch.getSelectedItem();
		
		if((text[2].length()!=10)||!isNumeric(text[2])){
			JOptionPane.showMessageDialog(this, "Invalid Contact No!");
			j++;
		}
		if((!isNumeric(text[5])||Integer.parseInt(text[5])>200)||(Integer.parseInt(text[5])<0)){
			JOptionPane.showMessageDialog(this, "Invalid CET Score!");
			j++;
		}
		if((!isNumeric(text[6])||Float.parseFloat(text[6])>100)||(Float.parseFloat(text[6])<0)){
			JOptionPane.showMessageDialog(this, "Invalid HSC Marks!");
			j++;
		}
		for (int i = 0; i < 8; i++) {
			if (text[i] == "" || text[i] == null || text[i].isEmpty()) {
				JOptionPane.showMessageDialog(this, "Some fields are empty!");
				j++;
				break;
			}
		}
		if (j == 0) {
			InputValues();
	}
}

	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	   Float f = Float.parseFloat(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	private void createTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:student.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS STUDENT"
					+ "(ID INT PRIMARY KEY     NOT NULL,"
					+ "NAME           TEXT    NOT NULL,"
					+ "ADDRESS        CHAR(50), "
					+ "CONTACTNO            TEXT     NOT NULL,"
					+ "EMAILID            TEXT     NOT NULL,"
					+ "GENDER            TEXT     NOT NULL,"
					+ "CET_MKS          INT       NOT NULL,"
					+ "HSC_MKS            REAL     NOT NULL,"
					+ "BRANCH            TEXT     NOT NULL,"
					+ "STATUS 		TEXT 		DEFAULT 'PENDING')";

			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	private void InputValues() {

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:student.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String newrec = "";
			for (int i = 0; i < 8; i++) {
				if (i >= 0 && i <= 4) {
					newrec += "'" + text[i] + "',";
				} else if (i == 7) {
					newrec += "'" + text[i] + "'";
				} else {
					newrec += text[i] + ",";
				}

			}

			String sql = "INSERT INTO STUDENT (ID,NAME,ADDRESS,CONTACTNO,EMAILID,GENDER,CET_MKS,HSC_MKS,BRANCH) "
					+ "VALUES (" + AppNo + "," + newrec + ");";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
		dispose();
		JOptionPane.showMessageDialog(this, "Registration successful!");
		new Home();
	}

	public int NoGen() {
		Connection c = null;
		Statement stmt = null;
		int counter = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:student.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENT;");

			while (rs.next()) {
				counter++;
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return counter;
	}
}