package will;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

public class AddEditPatient extends JPanel{
	private JTextField textField;
	private JTextField textField_2;
	//static 
	private ArrayList<Patient> PatientList;
	private ArrayList<Ambulance> AmbulanceList;
	static File DataFile = new File("patients.csv");
	public static void main(ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceList) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame1 = new JFrame();
					AddEditPatient frame = new AddEditPatient(frame1, PatientList, AmbulanceList);
					frame1.pack();
					frame1.setSize(500,400);
			        frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddEditPatient(JFrame frame, ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceList) {
		this.PatientList = PatientList;
		this.AmbulanceList = AmbulanceList;
		
		//System.out.println(Integer.parseInt(PatientList.get(PatientList.size()-1).getID())+ 1);
		Patient NewPatient = new Patient();
		NewPatient.setID(Integer.toString((Integer.parseInt(PatientList.get(PatientList.size()-1).getID())+ 1)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 361);
		frame.getContentPane().setLayout(null);
		JButton CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				PatientDisplay.main(PatientList, AmbulanceList);
				//PatientDisplay.frame.setVisible(true);
			}
		});
		CancelButton.setBounds(40, 236, 117, 58);
		frame.getContentPane().add(CancelButton);
		
		JButton savebutton = new JButton("Save");
		savebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//PatientDisplay.MyModel.AddRow(NewPatient);
				if (!PatientList.contains(NewPatient)){
					PatientList.add(NewPatient);
				}
				//cycle through this patient and see if any values are null, then set default value
				if(NewPatient.getID() == null){
					NewPatient.setID(Integer.toString((Integer.parseInt(PatientList.get(PatientList.size()-1).getID())+ 1)));
				}
				if(NewPatient.getX_location() == null || NewPatient.getX_location() == ""){
					NewPatient.setX_location("0");
				}
				if(NewPatient.getY_location() == null || NewPatient.getY_location() == ""){
					NewPatient.setY_location("0");
				}
				if(NewPatient.getStatus() == null || NewPatient.getStatus() == ""){
					NewPatient.setStatus("Completed");
				}
				if(NewPatient.getambulance() == null || NewPatient.getambulance() == ""){
					NewPatient.setambulance(" ");
				}
				try {
					writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
			        writeCSVFile.writeCSVFile(PatientList);
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
				AmbulanceTrackerApp.CSVFile R1 = new AmbulanceTrackerApp.CSVFile();
				ArrayList<String[]> Rs3 = R1.ReadCSVfile(DataFile);
				PatientDisplay.NewModel.AddCSVData(Rs3);
				PatientDisplay.NewModel.fireTableDataChanged();
				//PatientDisplay.main(PatientList, AmbulanceList);
				frame.setVisible(false);
				PatientDisplay.frame.setVisible(true);
				//make this fire table data changed and log all the changes
			}
		});
		savebutton.setBounds(315, 236, 117, 58);
		frame.getContentPane().add(savebutton);
		//Mark, i want to make this equal to as many lines as there are currently in the CSV file plus one
		JLabel lblPatients = new JLabel("Patient:" + (Integer.parseInt(PatientList.get(PatientList.size()-1).getID())+ 1));
		lblPatients.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPatients.setBounds(172, 0, 100,100);
		frame.getContentPane().add(lblPatients);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 191, 260, -107);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblId.setBounds(40, 70, 46, 14);
		frame.getContentPane().add(lblId);
		
		JLabel label = new JLabel("Location:");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(40, 95, 76, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Status:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(40, 120, 56, 20);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Ambulance:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(40, 151, 76, 14);
		frame.getContentPane().add(label_2);
		List<String> rsss = new ArrayList<String>();
		rsss.add("Assigned");
		rsss.add("Completed");
		rsss.add("Pending");
		rsss.add("Transporting");
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(rsss.toArray()));
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox.setBounds(162, 121, 182, 19);
		comboBox.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e1) {
	        	 NewPatient.setStatus((String)(comboBox.getSelectedItem()));
	         }
		});
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		AmbulanceTrackerApp.CSVFile R2 = new AmbulanceTrackerApp.CSVFile();
		File DataFile1 = new File("ambulances.csv");
		ArrayList<String[]> Rs5 = R2.ReadCSVfile(DataFile1);
		List<String> rss = new ArrayList<String>();
		rss.add(" ");
		for(Ambulance amb: AmbulanceList){
			//System.out.println("\"" + amb.getpatient() + "\"" + " before");
			if(amb.getpatient() == null || amb.getpatient() == "None" || amb.getpatient() == "null" || amb.getpatient().equals(" ")){
				//System.out.println(amb + " After");
				if(!rss.contains(amb.getID())){
					rss.add(amb.getID());
				}
				
			}
		}
		Collections.sort(rss);
		comboBox_1.setModel(new DefaultComboBoxModel(rss.toArray()));
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		comboBox_1.setBounds(162, 149, 182, 19);
		comboBox_1.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e2) {
	        	 if (((String) (comboBox_1.getSelectedItem())).equals(" ")) {
	    		  for(Ambulance p: AmbulanceList){
	    			  if(p.getID().equals(NewPatient.getambulance())){
	    	     			 p.setpatient(" ");
	    	     		 }
	        	   	}	
	        	 }
	    	  
	    	  NewPatient.setambulance((String) (comboBox_1.getSelectedItem()));
	    	  if (!((String) (comboBox_1.getSelectedItem())).equals(" ")) {
	    	  for(Ambulance amb: AmbulanceList){
	     		 if(amb.getID().equals(NewPatient.getambulance())){
	     			 amb.setpatient(NewPatient.getID());
	     		 }
	     	 	}
	    	  }
	         }
		});
		frame.getContentPane().add(comboBox_1);
		textField = new JTextField(); //y location
		textField.setText(NewPatient.getY_location());
		textField.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e3) {
	        	 try{
	        		 if(Integer.parseInt(textField.getText()) > 100 || Integer.parseInt(textField.getText()) < 0){
	        		 NewPatient.setY_location("1");
	        	 	 }
	        		 else{
		        		 NewPatient.setY_location(textField.getText());
		        	 }
	        	 }
	        	 catch(NumberFormatException ex){
	        		 NewPatient.setY_location("1");
	        	 }

	 
	         }
		});
		
		textField.setBounds(258, 95, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label_3 = new JLabel((Integer.toString((Integer.parseInt(PatientList.get(PatientList.size()-1).getID())+ 1)))); 
		label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		label_3.setBounds(162, 68, 182, 20);
		frame.getContentPane().add(label_3);
		
		
		textField_2 = new JTextField(); // x_location
		textField_2.setText(NewPatient.getX_location());
		textField_2.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 try{
	        		 if(Integer.parseInt(textField_2.getText()) > 100 || Integer.parseInt(textField_2.getText()) < 0){
	        		 NewPatient.setX_location("1");
	        	 	 }
	        		 else{
		        		 NewPatient.setX_location(textField_2.getText());
		        	 }
	        	 }
	        	 catch(NumberFormatException ex){
	        		 NewPatient.setX_location("1");
	        	 }
	        	 
	        	 
	         }
		});

		textField_2.setColumns(10);
		textField_2.setBounds(162, 95, 86, 20);
		frame.getContentPane().add(textField_2);
		frame.setSize(500, 500);

		//System.out.println(NewPatient);
		
	}
}

