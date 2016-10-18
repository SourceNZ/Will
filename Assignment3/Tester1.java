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


public class Tester1{
  public JFrame mainFrame;
  private ArrayList<Patient> PatientList;
  private ArrayList<Ambulance> AmbulanceList;
  private List<Point> stations = new ArrayList<Point>();
  private List<Point> ambulances = new ArrayList<Point>();
  private List<Point> patients = new ArrayList<Point>();
  
  public Tester1(ArrayList<Patient> PatientList,ArrayList<Ambulance> AmbulanceList)
  {
		Point hospital = new Point(50, 50);
		Point Greenfields = new Point(10, 0);
		Point Bluelane = new Point(30, 80);
		Point Redvill = new Point(90, 20);
		this.stations.add(Greenfields);
		this.stations.add(Bluelane);
		this.stations.add(Redvill);

	
	  this.PatientList = PatientList;
	  this.AmbulanceList = AmbulanceList;
	  
	Tester(PatientList, AmbulanceList,ambulances, stations);

	  
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
  public void Tester(ArrayList<Patient> patientList2, ArrayList<Ambulance> ambulanceList2, List<Point> ambulances, List<Point> stations) {

//		At station: check if there is a new patient to pick up, if so,
//		assign the closest unassigned patient to the ambulance and change the status to ‘Responding’. Otherwise, do nothing.		
	  for(Patient pp : PatientList){
			if(pp.status.equals("Pending")){
				this.patients.add(new Point(Integer.parseInt(pp.x_location),Integer.parseInt(pp.y_location)));
				}
			else{
				this.patients.remove(new Point(Integer.parseInt(pp.x_location),Integer.parseInt(pp.y_location)));
			}
		}
	 //do{
	  //	 while(!patients.isEmpty());
		System.out.println("BACK AT THE START");
		 
	
	 this.ambulances = ambulances;
	 this.AmbulanceList = ambulanceList2;
	 this.PatientList = patientList2;
	 this.stations = stations;
//	 for(Ambulance a : AmbulanceList){
//			if(a.status.equals("At Station")){
//			 this.ambulances.add(new Point(Integer.parseInt(a.x_location),Integer.parseInt(a.y_location)));
//			}
//			else{
//				this.ambulances.remove(a);
//			}
//		}
//
//	  for(Patient pp : PatientList){
//		if(pp.status.equals("Pending")){
//			this.patients.add(new Point(Integer.parseInt(pp.x_location),Integer.parseInt(pp.y_location)));
//			}
//		
//	  else{this.patients.remove(pp);
//		  
//	  }}
//	  for(Ambulance ab : AmbulanceList){
//		  if(ab.status.equals("At Station")){
//				 System.out.println(ab);;
//				
//			}
//	  }
//		
	 
		for(Ambulance a : AmbulanceList){
			if(a.status.equals("At Station")){
			 this.ambulances.add(new Point(Integer.parseInt(a.x_location),Integer.parseInt(a.y_location)));
			}
			else{
				this.ambulances.remove(new Point(Integer.parseInt(a.x_location),Integer.parseInt(a.y_location)));
			}
		}
		for(Patient pp : PatientList){
			if(pp.status.equals("Pending")){
				this.patients.add(new Point(Integer.parseInt(pp.x_location),Integer.parseInt(pp.y_location)));
				}
			else{
				this.patients.remove(new Point(Integer.parseInt(pp.x_location),Integer.parseInt(pp.y_location)));
			}
		}
		  
	 
	for(Ambulance a : AmbulanceList){
		Point amb = new Point(Integer.parseInt(a.x_location),Integer.parseInt(a.y_location));
		if(a.status.equals("At Station")){
			this.ambulances.add(new Point(Integer.parseInt(a.x_location),Integer.parseInt(a.y_location)));
			
			for(Patient p : PatientList){
				if(p.status.equals("Pending")){
					
					Point pat = new Point(Integer.parseInt(p.x_location),Integer.parseInt(p.y_location));
					Point closest1 = FindNearestPoints.main(pat, this.ambulances);
					
					if(closest1.equals(amb)){
						p.status = "Assigned";
						a.status = "Responding";
						a.patient = p.id;
						p.ambulance = a.id;
						System.out.println(a.id + " with status (" + a.status  + ") is picking up Patient " + p.id + " with status ("+ p.status+") ..." + a.location + p.location);
						Responding(p, a, stations);
						this.ambulances = ambulances;
						System.out.println("BREAK!!!");
//						break;	
						
					}
					
				}
			}
		}
	}
	 for(Ambulance ab : AmbulanceList){
		  if(ab.status.equals("At Station")){
				 System.out.println(ab);;
				
			}
	  }

	  for(Patient pp : PatientList){
		if(pp.status.equals("Pending")){
			 System.out.println(pp);
		}
	  }
	  
	System.out.println("All Ambulances Assigned!");
	 


  
}
//	switch (a.location) {
//    case "(50, 50)":  System.out.print ("HOSPITAL - ");
//             break;
//    case "(10, 0)":  System.out.print ("Greenfields - ");
//             break;
//    case "(30, 80)":  System.out.print("Bluelane - ");
//    		break;
//    case "(90, 20)" : System.out.print ("Redvill - ");
//    		break;
//    default : System.out.println ("NO STATION - " + a.location);
//    		break;
//}	
  private void Responding(Patient p, Ambulance a, List<Point> stations ) {
		//‘Responding’: move the ambulance towards the assigned patient by four moves. 
		//If the ambulance reaches the patient, change the status to ‘At scene’.
	  	
		Point2D p1 = new Point2D.Double(Integer.parseInt(a.x_location),Integer.parseInt(a.y_location));
		Point2D p2 = new Point2D.Double(Integer.parseInt(p.x_location),Integer.parseInt(p.y_location));
	
		double deltaX = p2.getX() - p1.getX();
		double deltaY = p2.getY() - p1.getY();
		double coeff = 0.25; 
		
		while(!p1.equals(p2)){
		try {
			p1.setLocation(p1.getX() + coeff*deltaX, p1.getY() + coeff*deltaY);
			System.out.println(a.id + " with status (" + a.status  + ") Transporting Patient " +  p1 + " ..." );
			Double test = p1.getX();
			Double test1 = p1.getY();
			p.setX_location(Integer.toString(test.intValue()));
			p.setY_location(Integer.toString(test1.intValue()));
			a.setX_location(Integer.toString(test.intValue()));
			a.setY_location(Integer.toString(test1.intValue()));
    	    Thread.sleep(1000);    
       	} catch(InterruptedException ex) {
    	    Thread.currentThread().interrupt();
    	}
		}
		if(p1.equals(p2)){
			Double test = p2.getX();
			Double test1 = p2.getY();

			a.status = "At Scene";
			p.setX_location(Integer.toString(test.intValue()));
			p.setY_location(Integer.toString(test1.intValue()));
			a.setX_location(Integer.toString(test.intValue()));
			a.setY_location(Integer.toString(test1.intValue()));
			System.out.println(a.id + " with status (" + a.status  + ") is at scene with Patient " + p.id + " with status ("+ p.status+") ..." );
		}
		//make it wait 4 seconds to do next step
		//‘At scene’: if the ambulance has been at the scene for four seconds, change the status to ‘Transporting’. Otherwise, do nothing.
		
		try {
		
    	    Thread.sleep(4000);    
    	    a.status = "Transporting";
    		p.status = "Transporting";
       	} catch(InterruptedException ex) {
    	    Thread.currentThread().interrupt();
    	}
		
		//‘Transporting’: move the ambulance towards the hospital by three moves. 
		//If the ambulance reaches the hospital, change the status to ‘At destination’.
		
		System.out.println(a.id + " with status (" + a.status  + ") is going to hospital with Patient " + p.id + " with status ("+ p.status+") ..." );
		Point2D.Double p3 = new Point2D.Double(Integer.parseInt(a.x_location),Integer.parseInt(a.y_location));
		Point2D.Double hospital = new Point2D.Double(50,50);
		
		double deltaX1 = hospital.getX() - p3.getX();
		double deltaY1 = hospital.getY() - p3.getY();

		
		double coeff1 = 0.33333; 
		Rectangle rect = new Rectangle(45,45,10,10);
		while(!rect.contains(p3)){
			try {
				p3.setLocation(p3.getX() + coeff1*deltaX1, p3.getY() + coeff1*deltaY1);
				System.out.println(a.id + " with status (" + a.status  + ") Transporting Patient " +  p3 + " ..." );
				Double test = p3.getX();
				Double test1 = p3.getY();
				p.setX_location(Integer.toString(test.intValue()));
				p.setY_location(Integer.toString(test1.intValue()));
				a.setX_location(Integer.toString(test.intValue()));
				a.setY_location(Integer.toString(test1.intValue()));
				
				Thread.sleep(1000);
	       	} catch(InterruptedException ex) {
	    	    Thread.currentThread().interrupt();
	    	}

	    if (rect.contains(p3))
	     {
	    	a.setX_location("50");
	    	a.setY_location("50");
	    	a.status = "At destination";
	    	p.status = "Completed";
	    	System.out.println(a.id + " with status (" + a.status  + ") is At hospital with Patient " + p.id + " with status ("+ p.status+") ..." );
	    	//if 2 seconds pass
	    	try {
	    	    Thread.sleep(2000);    
	    	    a.status = "Returning";
	    	    a.patient = ""; //clear patient from ambulance
	    	    p.ambulance = ""; //clear ambulance from patient
	    	    System.out.println(a.id + " with status (" + a.status  + ") is returning from hospital ..." );
	    	} catch(InterruptedException ex) {
	    	    Thread.currentThread().interrupt();
	    	}
	    	//Returning’: move the ambulance towards the nearest available station by three moves.
	    	//If the ambulance reaches the station, change the status to ‘At station’.
	    	if(a.status == "Returning"){
	    		Point pat = new Point(Integer.parseInt(a.x_location),Integer.parseInt(a.y_location));
	    		
	    		Point closest = FindNearestPoints.main(pat, stations);
	    		System.out.println(a.id + " with status (" + a.status  + ") is going to station " + closest+ " ..." );
	    		Point2D.Double p4 = new Point2D.Double(Integer.parseInt(a.x_location),Integer.parseInt(a.y_location));
	    		Point2D.Double stat = new Point2D.Double(closest.getX(), closest.getY());
	    		double deltaX2 = stat.getX() - p4.getX();
	    		double deltaY2 = stat.getY() - p4.getY();

	    		double coeff2 = 0.33333; 
	    		Double test4 = p4.getX();
				Double test5 = p4.getY();
	    		Rectangle rect1 = new Rectangle(test4.intValue(),test5.intValue(),10,10);
	    		while(!rect1.contains(p4)){
	    			try {
	    				p4.setLocation(p4.getX() + coeff2*deltaX2, p4.getY() + coeff2*deltaY2);
	    				System.out.println(a.id + " with status (" + a.status  + ") Going to " +  stat + " ..." );
	    				Double test = p4.getX();
	    				Double test1 = p4.getY();
	    				a.setX_location(Integer.toString(test.intValue()));
	    				a.setY_location(Integer.toString(test1.intValue()));
	    				Thread.sleep(1000);
	    	       	} catch(InterruptedException ex) {
	    	    	    Thread.currentThread().interrupt();
	    	    	}
	    	}
	    		if(rect1.contains(p4)){
	    			
	    			System.out.println(a.id + " with status (" + a.status  + ") IS AT STATION " +  stat + " ..." );
	    			a.status = "At Station";
		    		//ambulances.add(new Point(test4.intValue(),test5.intValue()));
		    		this.ambulances = ambulances;
		    		
	    		}
	    		
	     }
	    	
	    System.out.println();
	
	     }
		
	}
}
}
