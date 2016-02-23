import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class VerifyDoc extends JFrame {
	int bvac=3;
	VerifyDoc(final int cid, final String cbranch) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(312, 300);

		JPanel VDpanel = new JPanel();
		getContentPane().add(VDpanel, BorderLayout.CENTER);
		VDpanel.setLayout(null);

		final JCheckBox chkbox1 = new JCheckBox("Leaving Cerificate");
		chkbox1.setBounds(90, 55, 133, 23);
		VDpanel.add(chkbox1);

		JLabel lblDocumentVerification = new JLabel(
				"<HTML><U>Document Verification<U><HTML>");
		lblDocumentVerification.setBounds(25, 11, 249, 27);
		lblDocumentVerification.setFont(new Font("Tahoma", Font.BOLD, 22));
		VDpanel.add(lblDocumentVerification);

		final JCheckBox chkbox2 = new JCheckBox("HSC Marksheet");
		chkbox2.setBounds(90, 81, 155, 23);
		VDpanel.add(chkbox2);

		final JCheckBox chkbox3 = new JCheckBox("CET Scorecard");
		chkbox3.setBounds(90, 107, 144, 23);
		VDpanel.add(chkbox3);

		final JCheckBox chkbox4 = new JCheckBox("10th Marksheet");
		chkbox4.setBounds(90, 133, 155, 23);
		VDpanel.add(chkbox4);

		final JCheckBox chkbox5 = new JCheckBox("Nationality Proof");
		chkbox5.setBounds(90, 159, 155, 23);
		VDpanel.add(chkbox5);

		JButton btn = new JButton("Grant");
		btn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn.setBounds(96, 198, 89, 23);
		VDpanel.add(btn);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chkbox1.isSelected() && chkbox2.isSelected()
						&& chkbox3.isSelected() && chkbox4.isSelected()
						&& chkbox5.isSelected()) {
					if (vacancyCheck(cid, cbranch)) {
						Connection c = null;
						Statement stmt = null;
						try {
							Class.forName("org.sqlite.JDBC");
							c = DriverManager
									.getConnection("jdbc:sqlite:student.db");
							c.setAutoCommit(false);
							System.out.println("Opened database successfully");

							stmt = c.createStatement();
							String sql = "UPDATE STUDENT set STATUS = 'DONE' where ID="
									+ cid + ";";
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
								+ " has been admitted to the college!");
					} else {
						dispose();
						int result = JOptionPane
								.showConfirmDialog(
										null,
										"No vacancy in "
												+ cbranch
												+ " branch. Do you want to take admission in another branch?",
										null, JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {
									dispose();
									new AdFull(cid,bvac);
						}
						if (result == JOptionPane.NO_OPTION) {
							Connection c = null;
							Statement stmt = null;
							try {
								Class.forName("org.sqlite.JDBC");
								c = DriverManager
										.getConnection("jdbc:sqlite:student.db");
								c.setAutoCommit(false);
								System.out
										.println("Opened database successfully");
								stmt = c.createStatement();
								String sql = "UPDATE STUDENT set STATUS = 'CANCELLED' where ID="
										+ cid + ";";
								stmt.executeUpdate(sql);
								c.commit();
								stmt.close();
								c.close();
							} catch (Exception e1) {
								System.err.println(e1.getClass().getName()
										+ ": " + e1.getMessage());
								System.exit(0);
							}
							JOptionPane.showMessageDialog(rootPane,
									"Admission of ID No. " + cid
											+ " has been cancelled!");
						}
					}
				} 
				else {
					dispose();
					int res = JOptionPane
							.showConfirmDialog(
									null,
									"Documents not verified. Do you want to cancel admission?",
									null, JOptionPane.YES_NO_OPTION);
					if (res == JOptionPane.YES_OPTION) {
						Connection c = null;
						Statement stmt = null;
						try {
							Class.forName("org.sqlite.JDBC");
							c = DriverManager
									.getConnection("jdbc:sqlite:student.db");
							c.setAutoCommit(false);
							System.out
									.println("Opened database successfully");
							stmt = c.createStatement();
							String sql = "UPDATE STUDENT set STATUS = 'CANCELLED' where ID="
									+ cid + ";";
							stmt.executeUpdate(sql);
							c.commit();
							stmt.close();
							c.close();
						} catch (Exception e1) {
							System.err.println(e1.getClass().getName()
									+ ": " + e1.getMessage());
							System.exit(0);
						}
						JOptionPane.showMessageDialog(rootPane,
								"Admission of ID No. " + cid
										+ " has been cancelled!");
					}
					if (res == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(rootPane, "Admission status for ID No. "+cid+ " is pending.");
				}
			}
			}});
	}

	protected boolean vacancyCheck(int cid, String cbranch) {
		int count = 0;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:student.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT BRANCH,STATUS FROM STUDENT WHERE BRANCH='"
							+ cbranch + "';");
			while (rs.next()) {
				if (rs.getString(2).equals("DONE"))
					count++;
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		if (count == bvac)
			return false;
		else
			return true;

	}
}