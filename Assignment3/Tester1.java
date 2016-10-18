package Assignment3;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
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
		
		List<Point> ambulances = new ArrayList<Point>();

		for(Ambulance a : ambulanceList2){
			if(a.status.equals("At Station")){
			 ambulances.add(new Point(Integer.parseInt(a.x_location),Integer.parseInt(a.y_location)));
			}
		}
		for(Ambulance a : ambulanceList2){
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
						System.out.println(a.id + " with status (" + a.status  + ") is picking up Patient " + p.id + " with status ("+ p.status+") ..." );
						
						Responding(p, a);
						break;
					}
				}
			}
		}
		System.out.println("All Ambulances Assigned!");
	


	
}
  private static void Responding(Patient p, Ambulance a) {
		//‘Responding’: move the ambulance towards the assigned patient by four moves. 
		//If the ambulance reaches the patient, change the status to ‘At scene’.
		Point2D.Double p1 = new Point2D.Double(Integer.parseInt(a.x_location),Integer.parseInt(a.y_location));
		Point2D.Double p2 = new Point2D.Double(Integer.parseInt(p.x_location),Integer.parseInt(p.y_location));
		//p1 and p2 inits

		// you don't use abs value and use the still point as the first one of the subtraction
		double deltaX = p2.getX() - p1.getX();
		double deltaY = p2.getY() - p1.getY();

		// now you know how much far they are
		double coeff = 0.25; //this coefficient can be tweaked to decide how much near the two points will be after the update.. 0.5 = 50% of the previous distance
		
		while(!p1.equals(p2)){
		try {
			p1.setLocation(p1.getX() + coeff*deltaX, p1.getY() + coeff*deltaY);
			System.out.println(a.id + " with status (" + a.status  + ") Transporting Patient " +  p1 + " ..." );
    	    Thread.sleep(1000);    
       	} catch(InterruptedException ex) {
    	    Thread.currentThread().interrupt();
    	}
		}
		if(p1.equals(p2)){
			
			a.status = "At Scene";
			//System.out.println(p1 + "At Scene");
			System.out.println(a.id + " with status (" + a.status  + ") is at scene with Patient " + p.id + " with status ("+ p.status+") ..." );
		}
		//make it wait 4 seconds to do next step
		//‘At scene’: if the ambulance has been at the scene for four seconds, change the status to ‘Transporting’. Otherwise, do nothing.
		a.status = "Transporting";
		p.status = "Transporting";
		//‘Transporting’: move the ambulance towards the hospital by three moves. If the ambulance reaches the hospital, change the status to ‘At destination’.
		System.out.println(a.id + " with status (" + a.status  + ") is going to hospital with Patient " + p.id + " with status ("+ p.status+") ..." );
		Point2D.Double p3 = new Point2D.Double(Integer.parseInt(a.x_location),Integer.parseInt(a.y_location));
		Point2D.Double hospital = new Point2D.Double(50,50);
		
		double deltaX1 = hospital.getX() - p3.getX();
		double deltaY1 = hospital.getY() - p3.getY();

		// now you know how much far they are
		double coeff1 = 0.33333; //this coefficient can be tweaked to decide how much near the two points will be after the update.. 0.5 = 50% of the previous distance
		p3.setLocation(p3.getX() + coeff1*deltaX1, p3.getY() + coeff1*deltaY1);
		p3.setLocation(p3.getX() + coeff1*deltaX1, p3.getY() + coeff1*deltaY1);
		p3.setLocation(p3.getX() + coeff1*deltaX1, p3.getY() + coeff1*deltaY1);
		Rectangle rect = new Rectangle(45,45,10,10);//use your points co-ordinates 
	    if (rect.contains(p3))
	     {
	    	a.status = "At destination";
	    	p.status = "Completed";
	    	System.out.println(a.id + " with status (" + a.status  + ") is At hospital with Patient " + p.id + " with status ("+ p.status+") ..." );
	    	//if 2 seconds pass
	    	try {
	    	    Thread.sleep(2000);    
	    	    a.status = "Returning";//1000 milliseconds is one second.
	    	} catch(InterruptedException ex) {
	    	    Thread.currentThread().interrupt();
	    	}
	    	
	    	
	    	
	     }
	    System.out.println();
	    //‘At destination’: if the ambulance has been at the hospital for two seconds, change the status to ‘Returning’. Otherwise, do nothing.
	
		
	
		
	}
}
