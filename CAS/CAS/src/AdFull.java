import javax.swing.*;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AdFull extends JFrame {
	int branchVac[] = new int[5];
	ButtonGroup Branch = new ButtonGroup();
	String tmp;
	int flag=0;
	
	AdFull(final int cid, int bvac) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(300, 300);

		String branch[] = { "I.T.", "EXTC", "Electronics", "Mechanical",
				"Computer Science" };
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblVacantBranches = new JLabel("Vacant Branches:");
		lblVacantBranches.setBounds(89, 5, 118, 25);
		lblVacantBranches.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblVacantBranches);

		final JRadioButton rbB0 = new JRadioButton("I.T.");
		rbB0.setBounds(85, 30, 109, 23);
		panel.add(rbB0);
		rbB0.setVisible(false);

		final JRadioButton rbB1 = new JRadioButton("EXTC");
		rbB1.setBounds(85, 56, 109, 23);
		panel.add(rbB1);
		rbB1.setVisible(false);

		final JRadioButton rbB2 = new JRadioButton("Electronics");
		rbB2.setBounds(85, 82, 109, 23);
		panel.add(rbB2);
		rbB2.setVisible(false);

		final JRadioButton rbB3 = new JRadioButton("Mechanical");
		rbB3.setBounds(85, 108, 109, 23);
		panel.add(rbB3);
		rbB3.setVisible(false);

		final JRadioButton rbB4 = new JRadioButton("Computer Science");
		rbB4.setBounds(85, 134, 135, 23);
		panel.add(rbB4);
		rbB4.setVisible(false);

		Branch.add(rbB0);
		Branch.add(rbB1);
		Branch.add(rbB2);
		Branch.add(rbB3);
		Branch.add(rbB4);

		JButton btn = new JButton("OK");
		btn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn.setBounds(93, 185, 89, 23);
		panel.add(btn);

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:student.db");
			ResultSet rs = null;
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			for (int i = 0; i < 5; i++) {
				rs = stmt
						.executeQuery("SELECT COUNT(*) FROM STUDENT WHERE STATUS='DONE' AND BRANCH='"
								+ branch[i] + "';");
				branchVac[i] = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			c.close();
			if (branchVac[0] < bvac)
				rbB0.setVisible(true);
			if (branchVac[1] < bvac)
				rbB1.setVisible(true);
			if (branchVac[2] < bvac)
				rbB2.setVisible(true);
			if (branchVac[3] < bvac)
				rbB3.setVisible(true);
			if (branchVac[4] < bvac)
				rbB4.setVisible(true);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						if (rbB0.isSelected())
						{
							tmp = "I.T.";
							flag=0;
						}
						else if (rbB1.isSelected())
						{
							tmp = "EXTC";
							flag=0;
						}
						else if (rbB2.isSelected())
						{
							tmp = "Electronics";
							flag=0;
						}
						else if (rbB3.isSelected())
						{
							tmp = "Mechanical";
							flag=0;
						}
						else if (rbB4.isSelected())
						{
							tmp = "Computer Science";
							flag=0;
						}
						else
							flag=1;
						
						if(flag==1)
							JOptionPane.showMessageDialog(rootPane, "Please select an option!");
						else{
							Connection c = null;
							Statement stmt = null;
							try {
								Class.forName("org.sqlite.JDBC");
								c = DriverManager
										.getConnection("jdbc:sqlite:student.db");
								c.setAutoCommit(false);
								System.out.println("Opened database successfully");
								stmt = c.createStatement();
									String sql = "UPDATE STUDENT SET STATUS = 'DONE',BRANCH='"
											+ tmp + "' WHERE ID=" + cid + ";";
									stmt.executeUpdate(sql);
									c.commit();
									stmt.close();
									c.close();
							} catch (Exception e1) {
								System.err.println(e1.getClass().getName() + ": "
										+ e1.getMessage());
								System.exit(0);
							}
							dispose();
							JOptionPane.showMessageDialog(rootPane, "ID no." + cid
									+ " has been admitted in " + tmp + " branch!");
						}
					}
				}

);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
}