import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

class AdminHome extends JFrame{
	public AdminHome() {
		getContentPane().setLayout(null);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JButton btnGrantAdmission = new JButton("Grant Admission");
		btnGrantAdmission.setBounds(65, 83, 160, 23);
		getContentPane().add(btnGrantAdmission);
		btnGrantAdmission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Admission();
			}
		});
		
		JButton btnAccessMeritList = new JButton("Access Merit List");
		btnAccessMeritList.setBounds(65, 117, 160, 23);
		getContentPane().add(btnAccessMeritList);
		btnAccessMeritList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MList();
			}
		});
		
		JButton btnBack = new JButton("Logout");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Home();
			}
		});
		btnBack.setBounds(100, 151, 89, 23);
		getContentPane().add(btnBack);
		
		JLabel lblWelcomeAdmin = new JLabel("Welcome Admin!");
		lblWelcomeAdmin.setFont(new Font("Poor Richard", Font.BOLD, 16));
		lblWelcomeAdmin.setBounds(81, 30, 129, 23);
		getContentPane().add(lblWelcomeAdmin);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setSize(300,300);
	}
}