import javax.swing.*;

import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Admission extends JFrame{
	private JTextField tfID;
	public Admission(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblGetID = new JLabel("Enter Student ID :");
		lblGetID.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblGetID.setBounds(88, 73, 140, 14);
		panel.add(lblGetID);
		
		tfID = new JTextField();
		tfID.setBounds(215, 71, 86, 20);
		panel.add(tfID);
		JLabel lblCollegeAdmissionSystem = new JLabel("<HTML><U>College Admission System<U><HTML>");
		lblCollegeAdmissionSystem.setFont(new Font("Verdana", Font.BOLD, 20));
		lblCollegeAdmissionSystem.setBounds(64, 11, 312, 34);
		panel.add(lblCollegeAdmissionSystem);
		
		JButton btnOK = new JButton("OK");
		btnOK.setFont(new Font("SansSerif", Font.PLAIN, 13));
		btnOK.setBounds(155, 112, 89, 23);
		panel.add(btnOK);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfID.getText().isEmpty())
					JOptionPane.showMessageDialog(rootPane, "Above field is empty!");
				else
				{
					String cbranch = null;
					int cid=Integer.parseInt(tfID.getText());
					int flag=0;
					Connection c = null;
					Statement stmt = null;
					try {
						Class.forName("org.sqlite.JDBC");
						c = DriverManager.getConnection("jdbc:sqlite:student.db");
						c.setAutoCommit(false);
						System.out.println("Opened database successfully");
						stmt = c.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT ID,BRANCH,STATUS FROM STUDENT;");
						while(rs.next()){
							if(cid==rs.getInt(1)&&rs.getString(3).equals("PENDING"))
							{
								cbranch=rs.getString(2);
								flag=1;
								break;
							}
						}
						rs.close();
						stmt.close();
						c.close();
					} catch (Exception E) {
						System.err.println(E.getClass().getName() + ": " + E.getMessage());
						System.exit(0);
					}
					if(flag==1)
					{
					dispose();
					new VerifyDoc(cid,cbranch);
				   }
					else
						JOptionPane.showMessageDialog(rootPane, "Invalid ID or admission already done!");
				}
			}
		});
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(440,250);
	}
}