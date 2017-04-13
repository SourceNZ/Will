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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class AddEditAmbulance extends JPanel
{
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_2;
  //static JFrame frame1 = new JFrame();
  static File DataFile = new File("patients.csv");
  private ArrayList<Patient> PatientList;
  private ArrayList<Ambulance> AmbulanceList;


  public static void main(ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceList)
  {
    EventQueue.invokeLater(new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
        	JFrame frame1 = new JFrame();
          AddEditAmbulance frame = new AddEditAmbulance(frame1, PatientList,  AmbulanceList);
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

  public AddEditAmbulance(JFrame frame, ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceListt)
  {
	this.AmbulanceList = AmbulanceListt;
	this.PatientList = PatientList;
	
    Ambulance NewAmbulance = new Ambulance();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 502, 361);
    frame.getContentPane().setLayout(null);
    JButton CancelButton = new JButton("Cancel");
    CancelButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        frame.setVisible(false);
        AmbulanceDisplay.main(PatientList, AmbulanceList);
        // AmbulancesButton()
      }
    });
    CancelButton.setBounds(40, 236, 117, 58);
    frame.getContentPane().add(CancelButton);

    JButton button = new JButton("Save");
    button.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
    	if(!AmbulanceList.contains(NewAmbulance)){
    		 AmbulanceList.add(NewAmbulance);
        }   
    	if(NewAmbulance.getID() == null || NewAmbulance.getID() == "A" || NewAmbulance.getID() == ""){
    		NewAmbulance.setID("A" + Integer.toString((AmbulanceList.size()-1)+ 1));
		}
		if(NewAmbulance.getX_location() == null || NewAmbulance.getX_location() == ""){
			NewAmbulance.setX_location("1");
		}
		if(NewAmbulance.getY_location() == null || NewAmbulance.getY_location() == ""){
			NewAmbulance.setY_location("1");
		}
		if(NewAmbulance.getStatus() == null || NewAmbulance.getStatus() == ""){
			NewAmbulance.setStatus("At Station");
		}
		if(NewAmbulance.getpatient() == null || NewAmbulance.getpatient() == ""){
			NewAmbulance.setpatient(" ");
		}
       for(Patient p: PatientList){
      	  if(p.getID().equals(NewAmbulance.getpatient())){
      		  p.setambulance(NewAmbulance.getID());
      		}	
       }	
      	  
        
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
        File DataFile = new File("ambulances.csv");
        ArrayList<String[]> Rs3 = R1.ReadCSVfile(DataFile);
        AmbulanceDisplay.NewModel.AddCSVData(Rs3);
        AmbulanceDisplay.NewModel.fireTableDataChanged();
        frame.setVisible(false);
        AmbulanceDisplay.frame.setVisible(true);

      }
    });
    button.setBounds(315, 236, 117, 58);
    frame.getContentPane().add(button);

    JLabel lblPatients = new JLabel("New Ambulance");
    lblPatients.setFont(new Font("Tahoma", Font.BOLD, 12));
    lblPatients.setBounds(172, 0, 100, 100);
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

    JLabel label_2 = new JLabel("Patient:");
    label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
    label_2.setBounds(40, 151, 76, 14);
    frame.getContentPane().add(label_2);

    JComboBox comboBox = new JComboBox();
    comboBox.setModel(new DefaultComboBoxModel(
        new String[] { "At Station", "Responding", "At Scene", "Transporting", "At Destination", "Returning" }));
    comboBox.setFont(new Font("Tahoma", Font.BOLD, 13));
    comboBox.setBounds(162, 121, 182, 19);
    comboBox.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e1)
      {
        NewAmbulance.setStatus((String) (comboBox.getSelectedItem()));
      }
    });
    frame.getContentPane().add(comboBox);

    JComboBox comboBox_1 = new JComboBox();
	List<String> rss = new ArrayList<String>();
	rss.add(" ");
	for(Patient p: PatientList){
		if(p.getambulance() == null || p.getambulance() == "None" || p.getambulance() == "null" || p.getambulance().equals(" ")){
			if(!rss.contains(p.getID())){
				rss.add(p.getID());
			}
		}
	}
	comboBox_1.setModel(new DefaultComboBoxModel(rss.toArray()));

    comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 13));
    comboBox_1.setBounds(162, 149, 182, 19);
    comboBox_1.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e2)
      {
    	  if (((String) (comboBox_1.getSelectedItem())).equals(" ")) {
    		  for(Patient p: PatientList){
        	   		 if(p.getID().equals(NewAmbulance.getpatient())){
        	   			 p.setambulance(" ");
        	   			 
        	   		 }
        	   	}	
    	  }
    	  
    	  NewAmbulance.setpatient((String) (comboBox_1.getSelectedItem()));
    	  
    	  if (!((String) (comboBox_1.getSelectedItem())).equals(" ")) {
    		  for(Patient p: PatientList){
    			  if(p.getID().equals(NewAmbulance.getpatient())){
    				  p.setambulance(NewAmbulance.getID());
    			  }	
    		  }	
    	  }
      }
    });
    frame.getContentPane().add(comboBox_1);
    textField = new JTextField(); // y location
    textField.setText(NewAmbulance.getY_location());
    textField.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e3)
      {	
    	  try{
     		 if(Integer.parseInt(textField.getText()) > 100 || Integer.parseInt(textField.getText()) < 0){
     			NewAmbulance.setY_location("1");
     	 	 }
     		 else{
     			NewAmbulance.setY_location(textField.getText());
	        	 }
     	 	}
     	 	catch(NumberFormatException ex){
     	 		NewAmbulance.setY_location("1");
     	 	}

      }
    });

    textField.setBounds(258, 95, 86, 20);
    frame.getContentPane().add(textField);
    textField.setColumns(10);

    JTextField textField_id = new JTextField(("A"));
    textField_id.setFont(new Font("Tahoma", Font.BOLD, 13));
    textField_id.setBounds(162, 68, 182, 20);
    frame.getContentPane().add(textField_id);
    textField_id.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e)
      {
    	  try{
    		 
       		 if(Integer.parseInt(textField_id.getText().split("A")[1]) < 100 && Integer.parseInt(textField_id.getText().split("A")[1]) >= 0){
       			NewAmbulance.setID(textField_id.getText());
       	 	 }
       	 	}
       	 	catch(NumberFormatException ex){
       	 		NewAmbulance.setID("A" + AmbulanceList.size());
       	 	}
    	  //NewAmbulance.setID((textField_id.getText()));
      }
    });
    

    textField_2 = new JTextField(); // x_location
    textField_2.setText(NewAmbulance.getX_location());
    textField_2.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
    	  try{
      		 if(Integer.parseInt(textField_2.getText()) > 100 || Integer.parseInt(textField_2.getText()) < 0){
      			NewAmbulance.setX_location("1");
      	 	 }
      		 else{
      			NewAmbulance.setX_location(textField_2.getText());
 	        	 }
      	 	}
      	 	catch(NumberFormatException ex){
      	 		NewAmbulance.setX_location("1");
      	 	}
        
      }
    });

    textField_2.setColumns(10);
    textField_2.setBounds(162, 95, 86, 20);
    frame.getContentPane().add(textField_2);
    frame.setSize(500, 500);


  }
}
