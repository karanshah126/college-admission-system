import javax.swing.*;

import java.sql.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

class ApplicationForm extends JFrame {

	public ApplicationForm(int currID) {

		final JPanel AFpanel = new JPanel();
		AFpanel.setBounds(0, 0, 482, 640);
		getContentPane().add(AFpanel);

		setTitle("Application Form");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		AFpanel.setLayout(null);

		JLabel CAFlbl = new JLabel("College Admission Form");
		CAFlbl.setBounds(70, 13, 311, 29);
		CAFlbl.setFont(new Font("Verdana", Font.BOLD, 23));
		AFpanel.add(CAFlbl);

		JLabel label = new JLabel("<HTML><U>Application No. :<U><HTML>");
		label.setBounds(115, 55, 105, 16);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		AFpanel.add(label);

		JLabel AppNo = new JLabel("1");
		AppNo.setBounds(246, 55, 7, 16);
		AFpanel.add(AppNo);

		JLabel label_0 = new JLabel("Name:");
		label_0.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_0.setBounds(116, 95, 56, 16);
		AFpanel.add(label_0);

		JLabel label_1 = new JLabel();
		label_1.setBounds(233, 96, 56, 16);
		AFpanel.add(label_1);

		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAddress.setBounds(116, 136, 66, 16);
		AFpanel.add(lblAddress);

		JLabel label_3 = new JLabel();
		label_3.setBounds(233, 124, 157, 41);
		AFpanel.add(label_3);

		JLabel lblContactno = new JLabel("Contact No:");
		lblContactno.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblContactno.setBounds(116, 176, 76, 16);
		AFpanel.add(lblContactno);

		JLabel label_5 = new JLabel();
		label_5.setBounds(233, 176, 113, 16);
		AFpanel.add(label_5);

		JLabel lblEmailid = new JLabel("Email ID:");
		lblEmailid.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEmailid.setBounds(116, 205, 56, 16);
		AFpanel.add(lblEmailid);

		JLabel label_7 = new JLabel();
		label_7.setBounds(233, 205, 126, 16);
		AFpanel.add(label_7);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGender.setBounds(116, 234, 66, 16);
		AFpanel.add(lblGender);

		JLabel label_9 = new JLabel();
		label_9.setBounds(233, 233, 66, 16);
		AFpanel.add(label_9);

		JLabel lblCetMarks = new JLabel("CET Marks:");
		lblCetMarks.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCetMarks.setBounds(116, 265, 76, 16);
		AFpanel.add(lblCetMarks);

		JLabel label_11 = new JLabel();
		label_11.setBounds(233, 266, 56, 16);
		AFpanel.add(label_11);

		JLabel lblHscMarks = new JLabel("HSC Marks:");
		lblHscMarks.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHscMarks.setBounds(118, 300, 76, 16);
		AFpanel.add(lblHscMarks);

		JLabel label_13 = new JLabel();
		label_13.setBounds(234, 301, 56, 16);
		AFpanel.add(label_13);

		JLabel lblBranch = new JLabel("Branch:");
		lblBranch.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblBranch.setBounds(119, 332, 56, 16);
		AFpanel.add(lblBranch);

		JLabel label_15 = new JLabel();
		label_15.setBounds(235, 331, 56, 16);
		AFpanel.add(label_15);

		final JButton btnPrint = new JButton("Print");
		btnPrint.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnPrint.setBounds(156, 373, 89, 23);
		AFpanel.add(btnPrint);
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPrint.setVisible(false);
				PrinterJob pjob = PrinterJob.getPrinterJob();
				PageFormat preformat = pjob.defaultPage();
				preformat.setOrientation(PageFormat.LANDSCAPE);
				PageFormat postformat = pjob.pageDialog(preformat);
				//If user does not hit cancel then print.
				if (preformat != postformat) {
				    //Set print component
				    pjob.setPrintable((Printable) new Printer(AFpanel), postformat);
				    if (pjob.printDialog()) {
				        try {
							pjob.print();
						} catch (PrinterException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						btnPrint.setVisible(true);
				    }
				}
			}
			
		});

		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(450, 500);

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:student.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENT WHERE ID="
					+ currID + ";");
			AppNo.setText(Integer.toString(currID));
			label_1.setText(rs.getString(2));
			label_3.setText(rs.getString(3));
			label_5.setText(rs.getString(4));
			label_7.setText(rs.getString(5));
			label_9.setText(rs.getString(6));
			label_11.setText(Integer.toString(rs.getInt(7)));
			label_13.setText(Float.toString(rs.getFloat(8)));
			label_15.setText(rs.getString(9));

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}
}

class Printer implements Printable {
	final Component comp;

	public Printer(Component comp) {
		this.comp = comp;
	}

	public int print(Graphics g, PageFormat format, int page_index)
			throws PrinterException {
		if (page_index > 0) {
			return Printable.NO_SUCH_PAGE;
		}

		// get the bounds of the component
		Dimension dim = comp.getSize();
		double cHeight = dim.getHeight();
		double cWidth = dim.getWidth();

		// get the bounds of the printable area
		double pHeight = format.getImageableHeight();
		double pWidth = format.getImageableWidth();

		double pXStart = format.getImageableX();
		double pYStart = format.getImageableY();

		double xRatio = pWidth / cWidth;
		double yRatio = pHeight / cHeight;

		Graphics2D g2 = (Graphics2D) g;
		g2.translate(pXStart, pYStart);
		g2.scale(xRatio, yRatio);
		comp.paint(g2);
		return Printable.PAGE_EXISTS;
	}
}
