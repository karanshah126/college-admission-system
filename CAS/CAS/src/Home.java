import javax.swing.*;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class Home extends JFrame {
	int flag = 0;
	public Home() {
		setTitle("College Admission System");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Select Your Category :");
		lblNewLabel.setBounds(25, 24, 163, 14);
		getContentPane().add(lblNewLabel);

		final JRadioButton rstudent = new JRadioButton("Student");
		rstudent.setBounds(20, 45, 109, 23);
		getContentPane().add(rstudent);

		final JRadioButton radmin = new JRadioButton("Administrator");
		radmin.setBounds(20, 71, 109, 23);
		getContentPane().add(radmin);

		ButtonGroup acType = new ButtonGroup();
		acType.add(rstudent);
		acType.add(radmin);

		JLabel lblUsername = new JLabel("User ID :");
		lblUsername.setBounds(25, 113, 65, 14);
		getContentPane().add(lblUsername);

		tusername = new JTextField();
		tusername.setBounds(110, 110, 125, 20);
		getContentPane().add(tusername);
		tusername.setColumns(10);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(25, 137, 65, 14);
		getContentPane().add(lblEmail);

		temail = new JTextField();
		temail.setBounds(110, 134, 125, 20);
		getContentPane().add(temail);
		temail.setColumns(10);

		final JButton btnOk = new JButton("Login");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = tusername.getText();
				String email = temail.getText();
				if (rstudent.isSelected()) {
					int userid = Integer.parseInt(username);
					Connection c = null;
					Statement stmt = null;
					try {
						Class.forName("org.sqlite.JDBC");
						c = DriverManager
								.getConnection("jdbc:sqlite:student.db");
						c.setAutoCommit(false);
						System.out.println("Opened database successfully");
						stmt = c.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT ID,EMAILID,NAME FROM STUDENT;");
						while (rs.next()) {
							if ((userid == rs.getInt(1))
									&& email.equals(rs.getString(2))) {
								username = rs.getString(3);
								dispose();
								flag = 1;
								break;
							}
						}
						rs.close();
						stmt.close();
						c.close();
					} catch (Exception e) {
						System.err.println(e.getClass().getName() + ": "
								+ e.getMessage());
						System.exit(0);
					}
					if (flag == 1)
						new StudentHome(username, userid);
					else {
						JOptionPane.showMessageDialog(btnOk,
								"Incorrect username or password!");
						tusername.setText("");
						temail.setText("");
					}
				} else if (radmin.isSelected()) {
					if (username.equals("admin") && email.equals("pass@admin")) {
						dispose();
						new AdminHome();
					} else {
						JOptionPane.showMessageDialog(btnOk,
								"Incorrect username or password!");
						tusername.setText("");
						temail.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(btnOk,
							"Please select category");
					tusername.setText("");
					temail.setText("");
				}
			}
		});
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		btnOk.setBounds(59, 169, 65, 23);
		getContentPane().add(btnOk);
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new FormWindow();
			}
		});
		btnNewButton.setBounds(133, 169, 89, 23);
		getContentPane().add(btnNewButton);
		this.setVisible(true);
	}

	JLabel Option;
	JRadioButton Student, Administrator;
	JButton OK;
	private JTextField tusername;
	private JTextField temail;

	public static void main(String[] args) {

		Home Hobj = new Home();

	}
}
