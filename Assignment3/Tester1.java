package Assignment3;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFrame;

import main.AmbulanceTableModel;
import main.DataModel;
import main.PatientTableModel;

public class Tester1{
  public JFrame mainFrame;
  private ArrayList<Patient> PatientList;
  private ArrayList<Ambulance> AmbulanceList;
  
  public Tester1(ArrayList<Patient> PatientList,ArrayList<Ambulance> AmbulanceList)
  {
	  this.PatientList = PatientList;
	  this.AmbulanceList = AmbulanceList;
	 
  }

  public static void main(String[] args)  {
	
	ArrayList<Patient> PatientList = new ArrayList<Patient>();
	ArrayList<Ambulance> AmbulanceList = new ArrayList<Ambulance>();
	Tester1.CSVFile s = new Tester1.CSVFile();
	ArrayList<String[]> e = s.ReadCSVfile(new File("ambulances.csv"));
	new Tester1.CSVFile();
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
	 
	 Tester1 menu = new Tester1(PatientList, AmbulanceList);
    menu.prepareGUI();
    Tester(PatientList, AmbulanceList);
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
	            srcCopy[0] = srcStrings[0]; //id
	            srcCopy[1] = ("(" + srcStrings[1] + ", " + srcStrings[2] + ")");  //x and y locaiton
	            srcCopy[2] = srcStrings[3]; //status
	            if(srcStrings.length == 4){ //if theres no ambulance, set it to null
	          	  srcCopy[3] = " ";
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


  }
  public static void Tester(ArrayList<Patient> patientList2, ArrayList<Ambulance> ambulanceList2) {
		Point hospital = new Point(50, 50);
		Point Greenfields = new Point(10, 0);
		Point Bluelane = new Point(30, 80);
		Point Redvill = new Point(90, 20);
		
//		At station: check if there is a new patient to pick up, if so,
//		assign the closest unassigned patient to the ambulance and change the status to ‘Responding’. Otherwise, do nothing.		
		
		//for(Patient p : patientList2){
		//	System.out.println(p.id + " " + p.status + " " +p.location+ "Initial Locaiton");
		
		//}
		//System.out.println("Patients Done!");
		Patient pe = null;
		Ambulance be = null;
		List<Point> ambulances = new ArrayList<Point>();

		for(Ambulance a : ambulanceList2){
			if(a.status.equals("At Station")){
			 ambulances.add(new Point(Integer.parseInt(a.x_location),Integer.parseInt(a.y_location)));
			 System.out.println(a.location + "Ambulances at stations");
			}
		}
	
		for(Ambulance a : ambulanceList2){
			//System.out.println(a.id + " " +a.status + " " + a.location + "Initial Locaiton ");
			if(a.status.equals("At Station")){
				switch (a.location) {
	            case "(50, 50)":  System.out.print ("HOSPITAL - ");
	                     break;
	            case "(10, 0)":  System.out.print ("Greenfields - ");
	                     break;
	            case "(30, 80)":  System.out.print("Bluelane - ");
	            		break;
	            case "(90, 20)" : System.out.print ("Redvill - ");
	            		break;
	            default : System.out.println ("NO STATION - " + a.location);
	            		break;
	     
			}		
				for(Patient p : patientList2){
				Point pat = new Point(Integer.parseInt(p.x_location),Integer.parseInt(p.y_location));
					if(p.status.equals("Pending")){
						Point closest = FindNearestPoints.main(pat, ambulanceList2);
						if(pat.equals(closest)){
							System.out.println("OOMG THIS WORKED WTF");
						}
						//find the closest patient.
						p.status = "Assigned";
						a.status = "Responding";
						a.patient = p.id;
						p.ambulance = a.id;
						System.out.println(a.id + " with status (" + a.status  + ") is picking up Patient " + p.id + " with status ("+ p.status+") ----" );
						System.out.println();
						break;
						
					}

				}
				
			}
			
		}
		System.out.println("All Ambulances Assigned!");
		for(Ambulance a : ambulanceList2){
			System.out.println(a);
		}
		System.out.println("Ambulances Done!");
		
		
		
	}
}
