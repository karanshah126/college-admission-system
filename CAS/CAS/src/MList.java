import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class MList extends JFrame {
	ArrayList<Object[]> data = new ArrayList<Object[]>();
	Object realData[][];
	public static void main(String[] args) {
		MList ml = new MList();
	}

	public MList() {

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:student.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT NAME,CET_MKS,HSC_MKS FROM STUDENT ORDER BY CET_MKS DESC,HSC_MKS DESC;");
			while(rs.next()){
			    Object[] row = new Object[]{rs.getString(1),rs.getInt(2),rs.getFloat(3)};
			    data.add(row);
			}
			realData = data.toArray(new Object[data.size()][]);
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		JPanel MLpanel = new JPanel();
		this.setTitle("Merit List");
		String col[] = { "<HTML><B>Name<B><HTML>",
				"<HTML><B>CET Marks<B><HTML>", "<HTML><B>HSC Marks<B><HTML>" };
		JTable MLtable = new JTable(realData, col);
		JTableHeader MLheader = MLtable.getTableHeader();
		MLheader.setBackground(Color.black);
		MLheader.setForeground(Color.white);
		MLtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane pane = new JScrollPane(MLtable);
		MLpanel.add(pane);
		this.add(MLpanel);
		this.setSize(500,550);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}