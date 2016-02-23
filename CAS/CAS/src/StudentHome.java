import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.sql.*;

class StudentHome extends JFrame{
	public StudentHome(String uname,final int cid) {
		setTitle("StudentHome");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnViewApplicationForm = new JButton("View Application Form");
		btnViewApplicationForm.setBounds(60, 83, 160, 23);
		getContentPane().add(btnViewApplicationForm);
		btnViewApplicationForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ApplicationForm(cid);
			}
		});
		
		JButton btnMeritList = new JButton("Merit List");
		btnMeritList.setBounds(96, 117, 89, 23);
		getContentPane().add(btnMeritList);
		btnMeritList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MList();
			}
		});
		
		
		JButton btnCheckStatus = new JButton("Check Status");
		btnCheckStatus.setBounds(83, 148, 114, 23);
		getContentPane().add(btnCheckStatus);
		btnCheckStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection c = null;
				Statement stmt = null;
				try {
					Class.forName("org.sqlite.JDBC");
					c = DriverManager.getConnection("jdbc:sqlite:student.db");
					c.setAutoCommit(false);
					System.out.println("Opened database successfully");
					stmt = c.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT STATUS FROM STUDENT WHERE ID="+cid+";");
					JOptionPane.showMessageDialog(rootPane, "Admission Status : "+rs.getString(1)+"!");
					rs.close();
					stmt.close();
					c.close();
				} catch (Exception E) {
					System.err.println(E.getClass().getName() + ": " + E.getMessage());
					System.exit(0);
				}
			}
		});
		
		JButton btnBack = new JButton("Logout");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Home();
			}
		});
		btnBack.setBounds(96, 182, 89, 23);
		getContentPane().add(btnBack);
		
		JLabel lblWelcomeStudent = new JLabel("Welcome "+uname+"!");
		lblWelcomeStudent.setFont(new Font("Poor Richard", Font.BOLD, 16));
		lblWelcomeStudent.setBounds(71, 29, 150, 23);
		getContentPane().add(lblWelcomeStudent);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(300,300);
	}
	
}