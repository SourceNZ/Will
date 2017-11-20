
package will;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



public class EditPatient // extends PatientDisplay
{
  private JTextField textField;
  private JTextField textField_2;
  private ArrayList<Patient> PatientList;
  private ArrayList<Ambulance> AmbulanceList;
  //JFrame frame1 = new JFrame();
  //static JFrame frame1 = new JFrame();

  public static void main(int[] args, ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceList){
  int i = args[0];
  
    EventQueue.invokeLater(new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
          JFrame frame1 = new JFrame();
          EditPatient frame = new EditPatient(frame1, i, PatientList,AmbulanceList);
          frame1.pack();
          frame1.setSize(500, 400);
          frame1.setVisible(true);
          
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    });
  }

  public EditPatient(JFrame frame1, int i, ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceList)
  {

	this.PatientList = PatientList;
	this.AmbulanceList= AmbulanceList;
	 //ZAC 
	Patient newPatient = new Patient();
	int j = 0;
	for(Patient p: PatientList){
		if( j == i){
			newPatient = p;
		}
		j++;
	}
	final Patient patientf = newPatient;
	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame1.setBounds(100, 100, 502, 361);
    frame1.getContentPane().setLayout(null);
    JButton CancelButton = new JButton("Cancel");
    CancelButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
    	  frame1.setVisible(false);
          PatientDisplay.main(PatientList, AmbulanceList);
        // AmbulancesButton()
      }
    });
    CancelButton.setBounds(40, 236, 117, 58);
    frame1.getContentPane().add(CancelButton);

    JButton button = new JButton("Save");
    button.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)      {
    	  //checking whether anything has changed
    	PatientList.set(i, patientf);
    	// if(!AmbulanceList.contains(newPatient)){
        //	  AmbulanceList.add(newPatient);
        // }
        try
        {
        	writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
            writeCSVFile.writeCSVFile(PatientList);
        }
        catch (ClassNotFoundException | IOException e1)
        {
          e1.printStackTrace();
        }
        AmbulanceTrackerApp.CSVFile R1 = new AmbulanceTrackerApp.CSVFile();
        File DataFile = new File("patients.csv");
        ArrayList<String[]> Rs3 = R1.ReadCSVfile(DataFile);
        PatientDisplay.NewModel.AddCSVData(Rs3);
        PatientDisplay.NewModel.fireTableDataChanged();
        PatientDisplay.frame.setVisible(true);
        frame1.setVisible(false);

      }
    });
    button.setBounds(315, 236, 117, 58);
    frame1.getContentPane().add(button);

    JLabel lblPatients = new JLabel("Patient: " + newPatient.getID());
    lblPatients.setFont(new Font("Tahoma", Font.BOLD, 12));
    lblPatients.setBounds(170, 0, 120, 120);
    frame1.getContentPane().add(lblPatients);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(84, 191, 260, -107);
    frame1.getContentPane().add(scrollPane);

    JLabel lblId = new JLabel("ID:");
    lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
    lblId.setBounds(50, 50, 80, 50);
    frame1.getContentPane().add(lblId);

    JLabel label = new JLabel("Location:");
    label.setFont(new Font("Tahoma", Font.BOLD, 12));
    label.setBounds(40, 95, 76, 14);
    frame1.getContentPane().add(label);

    JLabel label_1 = new JLabel("Status:");
    label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
    label_1.setBounds(40, 120, 56, 20);
    frame1.getContentPane().add(label_1);

    JLabel label_2 = new JLabel("Ambulances:");
    label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
    label_2.setBounds(35, 145, 80, 20);
    frame1.getContentPane().add(label_2);

    JComboBox comboBox = new JComboBox();
    comboBox.setModel(new DefaultComboBoxModel(
        new String[] { "Assigned", "Completed", "Pending" ,"Transporting"}));
    comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
    comboBox.setBounds(162, 121, 182, 19);
    comboBox.setSelectedItem(newPatient.getStatus());
    comboBox.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e1)
      {
    	  patientf.setStatus((String)(comboBox.getSelectedItem()));
      }
    });
    frame1.getContentPane().add(comboBox);

    JComboBox comboBox_1 = new JComboBox();
    
    AmbulanceTrackerApp.CSVFile R2 = new AmbulanceTrackerApp.CSVFile();
	File DataFile1 = new File("patients.csv");
	ArrayList<String[]> Rs5 = R2.ReadCSVfile(DataFile1);
	List<String> rss = new ArrayList<String>();
	rss.add(" ");
	for(Ambulance amb: AmbulanceList){
		if(amb.getpatient() == null || amb.getpatient() == "None" || amb.getpatient() == "null" || amb.getpatient().equals(" ")){
			if(!rss.contains(amb.getID())){
				rss.add(amb.getID());
			}
		}
	}
	comboBox_1.setModel(new DefaultComboBoxModel(rss.toArray()));
    comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 12));
    comboBox_1.setBounds(162, 149, 182, 19);
    comboBox_1.setSelectedItem(newPatient.getambulance());
    comboBox_1.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e2)
      {

    	  if (((String) (comboBox_1.getSelectedItem())).equals(" ")) {
    		  for(Ambulance p: AmbulanceList){
    			  if(p.getID().equals(patientf.getambulance())){
    	     			 p.setpatient(" ");
    	     		 }
        	   	}	
    	  }
    	  
    	  patientf.setambulance((String) (comboBox_1.getSelectedItem()));
    	  if (!((String) (comboBox_1.getSelectedItem())).equals(" ")) {
    	  for(Ambulance amb: AmbulanceList){
     		 if(amb.getID().equals(patientf.getambulance())){
     			 amb.setpatient(patientf.getID());
     		 }
     	 	}
    	  }
      }
    });
    frame1.getContentPane().add(comboBox_1);
    textField = new JTextField(newPatient.getY_location()); // y location
    textField.setText(newPatient.getY_location());
    textField.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e3)
      {
    	  if(Integer.parseInt(textField.getText()) > 100 || Integer.parseInt(textField.getText()) < 0){
    		  patientf.setY_location("1");
     	 }
     	 else{
     		patientf.setY_location(textField.getText());
     	 }  
        
      }
    });
    textField.setBounds(258, 95, 86, 20);
    frame1.getContentPane().add(textField);
    textField.setColumns(10);
    JLabel textField_id = new JLabel((newPatient.getID()));
    textField_id.setFont(new Font("Tahoma", Font.BOLD, 13));
    textField_id.setBounds(162, 68, 182, 20);
    frame1.getContentPane().add(textField_id);

    

    textField_2 = new JTextField(); // x_location
    textField_2.setText(newPatient.getX_location());
    textField_2.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
    	 if(Integer.parseInt(textField_2.getText()) > 100 || Integer.parseInt(textField_2.getText()) < 0){
    		 patientf.setX_location("1");
     	 }
     	 else{
     		patientf.setX_location(textField_2.getText());
     	 }
        
      }
    });
    textField_2.setColumns(10);
    textField_2.setBounds(162, 95, 86, 20);
    frame1.getContentPane().add(textField_2);
    frame1.setSize(500, 500);
    frame1.setVisible(false);


  }
}
