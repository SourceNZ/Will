package Asssignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JFrame;

public class AmbulanceTrackerApp{
  public JFrame mainFrame;
  private ArrayList<Patient> PatientList;
  private ArrayList<Ambulance> AmbulanceList;
  
  public AmbulanceTrackerApp(ArrayList<Patient> PatientList,ArrayList<Ambulance> AmbulanceList)
  {
	  this.PatientList = PatientList;
	  this.AmbulanceList = AmbulanceList;
	 
  }

  public static void main(String[] args)  {
	
	ArrayList<Patient> PatientList = new ArrayList<Patient>();
	ArrayList<Ambulance> AmbulanceList = new ArrayList<Ambulance>();
	AmbulanceTrackerApp.CSVFile s = new AmbulanceTrackerApp.CSVFile();
	ArrayList<String[]> e = s.ReadCSVfile(new File("ambulances.csv"));
	new AmbulanceTrackerApp.CSVFile();
	ArrayList<String[]> v = s.ReadCSVfile(new File("patients.csv"));	
	 for (String[] strings : v)
	    {
		
	      Patient test1 = new Patient(strings[0], strings[4], strings[5], strings[2], strings[3]);
	      
	      if(!PatientList.contains(test1)){
	    	  PatientList.add(test1);
	      }
	    }
	 for (String[] strings : e)
	    {
		
	      Ambulance test1 = new Ambulance(strings[0], strings[4], strings[5], strings[2], strings[3]);
	      if(!AmbulanceList.contains(test1)){
	    	  AmbulanceList.add(test1);
	      }
	      
	    }
	 
    AmbulanceTrackerApp menu = new AmbulanceTrackerApp(PatientList, AmbulanceList);
    menu.prepareGUI();
  }
  



  public static class CSVFile  {
	    public ArrayList<String[]> ReadCSVfile(File DataFile)    {
	      ArrayList<String[]> Values = new ArrayList<>();// contains raw data
	      try
	      {
	        BufferedReader brd = new BufferedReader(new FileReader(DataFile));
	        brd.readLine();

	        String st = null;
	        while ((st = brd.readLine()) != null)
	        {
	            String[] destStrings = new String[6];
	            String[] srcStrings = st.split(",");
	            String[] srcCopy = new String[6];
	            srcCopy[0] = srcStrings[0];
	            srcCopy[1] = ("(" + srcStrings[1] + ", " + srcStrings[2] + ")");
	            srcCopy[2] = srcStrings[3];
	            if(srcStrings.length == 4){
	          	  srcCopy[3] = null;
	            }
	            else if(srcStrings.length == 5){
	          	  srcCopy[3] = srcStrings[4];
	            }
	            srcCopy[4] = srcStrings[1];
	            srcCopy[5] = srcStrings[2];
	         
	            System.arraycopy(srcCopy, 0, destStrings, 0, 6);
	            destStrings[0] = destStrings[0].replaceAll("\"", "");
	            destStrings[1] = destStrings[1].replaceAll("\"", "");
	            destStrings[2] = destStrings[2].replaceAll("\"", "");
	            destStrings[3] = destStrings[3].replaceAll("\"", "");
	            
	            Values.add(destStrings);

	        } // end of while
	        brd.close();
	      } // end of try
	      catch (Exception e)
	      {
	        String errmsg = e.getMessage();
	        System.out.println("" + errmsg);
	      } // end of Catch
	      return Values;
	      
	    }// end of ReadFile method
	  }// end of CSVFile class
  private void prepareGUI()
  {
//    mainFrame = new JFrame("Ambulance Tracking System");
//    mainFrame.setSize(500, 500);
//    mainFrame.setLayout(new GridLayout(3, 1));
//    mainFrame.addWindowListener(new WindowAdapter()
//    {
//      @Override
//      public void windowClosing(WindowEvent windowEvent)
//      {
//        System.exit(0);
//      }
//    });
//    headerLabel = new JLabel("", JLabel.CENTER);
//    controlPanel = new JPanel();
//    controlPanel.setLayout(new GridLayout(3, 1));
//    controlPanel.setVisible(true);
//    mainFrame.add(headerLabel);
//    mainFrame.add(controlPanel);
//
//    headerLabel.setText("Ambulance Tracking System");
//    Font a = new Font("Dialog", Font.BOLD, 30);
//    headerLabel.setFont(a);
////    JButton PatientButton = new JButton("Patients");
////    PatientButton.setFont(a);
//    JButton AmbulancesButton = new JButton("Ambulances");
//    AmbulancesButton.setFont(a);
//    JButton ExitButton = new JButton("Exit");
//    ExitButton.setFont(a);
//    ExitButton.setHorizontalTextPosition(SwingConstants.LEFT);
//
////    PatientButton.addActionListener(new ActionListener()
////    {
////      @Override
////      public void actionPerformed(ActionEvent e)
////      {
////        mainFrame.setVisible(false);
////        PatientDisplay.main(PatientList, AmbulanceList);
////      }
////    });
//
//    AmbulancesButton.addActionListener(new ActionListener()
//    {
//     @Override
//      public void actionPerformed(ActionEvent ex)
//      {
//        
//        mainFrame.setVisible(false);
//      }
//    });
//
//    ExitButton.addActionListener(new ActionListener()
//    {
//      @Override
//      public void actionPerformed(ActionEvent e)
//      {
//        System.exit(0);
//      }
//    });
//
////    controlPanel.add(PatientButton);
//    controlPanel.add(AmbulancesButton);
//    controlPanel.add(ExitButton);
	  
	  Testing.main(PatientList, AmbulanceList);
	//AmbulanceDisplay.main( PatientList,  AmbulanceList);
//    mainFrame.setVisible(true);

  }
}
