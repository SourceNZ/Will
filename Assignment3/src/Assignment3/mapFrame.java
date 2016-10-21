package Assignment3;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class mapFrame extends JFrame {
	private JTable table;
	private JTextField textField;
	public mapFrame() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 464, 315);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		textField = new JTextField();
		textField.setBounds(190, 337, 240, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblDurationseconds = new JLabel("Duration(Seconds)");
		lblDurationseconds.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDurationseconds.setBounds(37, 337, 138, 14);
		panel.add(lblDurationseconds);
		
		JButton btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnStart.setBounds(37, 378, 107, 44);
		panel.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnStop.setBounds(332, 378, 98, 44);
		panel.add(btnStop);
	}
}
