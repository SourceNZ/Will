package Assignment3;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;



/*   problems
 *   
 *   need to make it remove ambulances from the list if they have a passenger, 
 *   problem is that multiple ambulances can be at one station at a time.
	 
	 need a different way to store available ambulances
	 The number of ambulances a station can accommodate is defined by the total number of ambulances divided by three, rounded up. For example, if there are four ambulances defined, each station can accommodate two ambulances (four divided by three, rounded up).
• A station is available if there are less ambulances at the station than it can accommodate.
	 use ambulancelist as the data storage,
	group by conditions,
	e.g
	if its at station then search for the nearest and assign it.
	if its assigned then go that patients location
	if its at scene then wait 4 seconds 
	if its transporting go to hospital.
	if its at destination then go to the nearest station and reset status
	 
	 
	 station availability.
	 
	 stationlist needs to be shared between threads
	 ambulanceList and PatientList too.
	 the station an ambulance is going to when returning needs to be set at the hospital.
	 
	 
	 
 */


public class Tester1{
  public JFrame mainFrame;
  private ArrayList<Patient> PatientList;
  private ArrayList<Ambulance> AmbulanceList;
  private List<Point> stations = new ArrayList<Point>();
  private List<Station> stationList = new ArrayList<Station>();
 // private List<Point> ambulances = new ArrayList<Point>();
  private List<Point> patients = new ArrayList<Point>();
  static Tester1.CSVFile R1 = new Tester1.CSVFile(); 
  private File DataFile1 = new File("ambulances-2.csv");
  
  public Tester1(ArrayList<Patient> PatientList,ArrayList<Ambulance> AmbulanceList)
  {

	  double stationCapacity;
	  stationCapacity = Math.ceil((double)AmbulanceList.size()/3);
	  int stationCap = (int) Math.round(stationCapacity);
	  System.out.println(stationCap + "UP HERE");
	  //List<Ambulance> amblist = new ArrayList<Ambulance>();
	  Station GreenFields = new Station("Greenfields",10, 0, stationCap, new ArrayList<Ambulance>());
	  Station BlueLane = new Station("Bluelane",30, 80, stationCap, new ArrayList<Ambulance>());
	  Station RedVill = new Station("Redvill",90, 20, stationCap, new ArrayList<Ambulance>());
	 
	  this.stationList.add(BlueLane);
	  this.stationList.add(GreenFields);
	  this.stationList.add(RedVill);
	  for(Station st: stationList){
		  System.out.println(st + "THIS IS THE ORDER");
		  for(Ambulance amb: AmbulanceList){
			  if(amb.getLocat().equals(st.getLocat())){
				  st.addambulance(amb);
			  }
		  }
	  }

	  this.PatientList = PatientList;
	  this.AmbulanceList = AmbulanceList;
	  prepareGUI(PatientList, AmbulanceList);
	  try {
		Thread.sleep(500);
	  } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	  
	  Tester(this.PatientList, this.AmbulanceList, stations, stationList);

	  
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
	      
	    }
	  }

  public void Tester(ArrayList<Patient> patientList2, ArrayList<Ambulance> ambulanceList2, List<Point> stations, List<Station> stationList) {

//		At station: check if there is a new patient to pick up, if so,
//		assign the closest unassigned patient to the ambulance and change the status to ‘Responding’. Otherwise, do nothing.		

		System.out.println("BACK AT THE START");
		ArrayList<Patient> tt = new ArrayList<Patient>();
		for(Patient pe : PatientList){
			if(pe.getStatus().equals("Pending")){
				tt.add(pe);
			}
		
		}
		//while there aren't any patients pending 
	 this.AmbulanceList = ambulanceList2;
	 this.PatientList = patientList2;
	 this.stations = stations;
	
while(!tt.isEmpty()){
	for(Ambulance a : AmbulanceList){
		//"A7",17,25,"Transporting",3
			if(a.getStatus().equals("At Station")&& !tt.isEmpty()){ // sets status to responding
				System.out.println(a.getID() + " IS SEARCHING FOR PATIENTS");
				Patient P = findPatient(a, PatientList, tt);
				getPatient(a, P);
				tt.remove(P);
				 System.out.println("HERE");
				 for(Patient pe : tt){
					 System.out.println(pe);
				 }
				 System.out.println("THERE");
			}
			if(a.getStatus().equals("Responding")){ // sets status to at scene
				for(Station st: stationList){
					if(st.getambulances().contains(a)){
						st.removeambulance(a);
						System.out.println(a + "REMOVED AMBULANCE FROM STATION");
					}
				}
				Responding(a, stations, AmbulanceList, PatientList);
			}
			if(a.getStatus().equals("At Scene")){// sets status to Transporting
				Patient p = new Patient();
				for(Patient pp : PatientList){
					  if(pp.getID().equals(a.getpatient())){
						  p = pp;
					  } 
				  }
			  	try {
					System.out.println("Waiting 4 seconds....");
		    	    Thread.sleep(4000);    
		    	    a.setStatus("Transporting");
		    		p.setStatus("Transporting");
		       	} catch(InterruptedException ex) {
		    	    Thread.currentThread().interrupt();
		    	}
			}
			if(a.getStatus().equals("Transporting")){
				Transporting(a, stations, AmbulanceList, PatientList);
				
			}
			if(a.getStatus().equals("At Destination")){// sets status to returning
				Patient p = new Patient();
				  for(Patient pp : PatientList){
					  if(pp.getID().equals(a.getpatient())){
						  p = pp;
					  } 
				  }
				try {
		    	    Thread.sleep(2000);    
		    	    a.setStatus( "Returning");
		    	    a.setpatient(""); //clear patient from ambulance
		    	    p.setambulance ( ""); //clear ambulance from patient
		    	    System.out.println(a.id + " with status (" + a.status  + ") is returning from hospital ..." );
		    	    try {
						writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
					} catch (Exception e) {
						e.printStackTrace();
					} 
				 	ArrayList<String[]> Rs5 = R1.ReadCSVfile(DataFile1);
				    AmbulanceDisplay.NewModel.AddCSVData(Rs5);
				    AmbulanceDisplay.NewModel.fireTableDataChanged();
		    	} catch(InterruptedException ex) {
		    	    Thread.currentThread().interrupt();
		    	}
			
			}

			if(a.getStatus().equals("Returning")){
	    	    Returning(a, stations, AmbulanceList, PatientList, this.stationList);
			}
			try {
				writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
		    AmbulanceDisplay.NewModel.AddCSVData(Rs2);
		    AmbulanceDisplay.NewModel.fireTableDataChanged();
			
		}
		
	}
	
  }
private void getPatient(Ambulance a, Patient p) {
	p.setStatus( "Assigned");
	a.setStatus ("Responding");
	a.setpatient(p.getID());
	p.setambulance( a.getID());
	System.out.println(a.getID() + " with status (" + a.getStatus()  + ") is picking up Patient " + p.getID() + " with status ("+ p.getStatus() +") ..." + a.getLocat() + p.getLocat());
	try {
		writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
    AmbulanceDisplay.NewModel.AddCSVData(Rs2);
    AmbulanceDisplay.NewModel.fireTableDataChanged();
}

public Patient findPatient(Ambulance a1, ArrayList<Patient> PatientList, ArrayList<Patient> tt){
	Patient pe = new Patient();
	  for(Patient p : PatientList){
			if(p.getStatus().equals("Pending")){
				Point amb = new Point(Integer.parseInt(a1.getX_location()),Integer.parseInt(a1.getY_location()));
				Patient closest1 = FindNearestPatient.main(amb, PatientList, tt);
				System.out.println(closest1 + "OIOI");
				if(closest1.getID().equals(p.getID())){
					pe = p;
				}
			}	
	}
	  System.out.println("ALSO WATCH HERE" + pe);
	  return pe;
	
}

	
  
  void prepareGUI(ArrayList<Patient> PatientList,ArrayList<Ambulance> AmbulanceList){
	  AmbulanceDisplay.main( PatientList, AmbulanceList);
  }

  private void Responding(Ambulance a, List<Point> stations, ArrayList<Ambulance> AmbulanceList, ArrayList<Patient> PatientList ) {
	  this.AmbulanceList = AmbulanceList;
	  this.PatientList = PatientList;
	  Patient p = new Patient();
	  for(Patient pp : PatientList){
		  if(pp.getID().equals(a.getpatient())){
			  p = pp;
		  }
			  
	  }
	  System.out.println("WATCH HERE " + p);
	  if(p.getStatus() != null){
		  
	 
	  Point2D p1 = new Point2D.Double(Integer.parseInt(a.getX_location()),Integer.parseInt(a.getY_location()));
	  Point2D p2 = new Point2D.Double(Integer.parseInt(p.getX_location()),Integer.parseInt(p.getY_location()));
		
		double deltaX = p2.getX() - p1.getX();
		double deltaY = p2.getY() - p1.getY();
		//double coeff = 0.01; 
		double coeff = 0.25;
		while(!p1.equals(p2)){
		try {
			p1.setLocation(p1.getX() + coeff*deltaX, p1.getY() + coeff*deltaY);
			System.out.println(a.id + " with status (" + a.status  + ") Transporting Patient " +  p1 + " ..." );
			Double test = p1.getX();
			Double test1 = p1.getY();
			a.setX_location(Integer.toString(test.intValue()));
			a.setY_location(Integer.toString(test1.intValue()));
			
			 try {
			writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
			 } catch (Exception e) {
				 e.printStackTrace();
			 } 
			 ArrayList<String[]> Rs6 = R1.ReadCSVfile(DataFile1);
			 AmbulanceDisplay.NewModel.AddCSVData(Rs6);
			 AmbulanceDisplay.NewModel.fireTableDataChanged();
			 
    	    Thread.sleep(1000);    
       	} catch(InterruptedException ex) {
    	    Thread.currentThread().interrupt();
    	}
		}
		if(p1.equals(p2)){
			Double test = p2.getX();
			Double test1 = p2.getY();
			a.setStatus("At Scene");
			a.setX_location(Integer.toString(test.intValue()));
			a.setY_location(Integer.toString(test1.intValue()));
			System.out.println(a.id + " with status (" + a.status  + ") is at scene with Patient " + p.id + " with status ("+ p.status+") ..." );
			try {
				writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	
		 	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
		    AmbulanceDisplay.NewModel.AddCSVData(Rs2);
		    AmbulanceDisplay.NewModel.fireTableDataChanged();
		}
  }
  }	
private void Transporting(Ambulance a, List<Point> stations, ArrayList<Ambulance> AmbulanceList, ArrayList<Patient> PatientList ) {
	//‘Transporting’: move the ambulance towards the hospital by three moves. 
	//If the ambulance reaches the hospital, change the status to ‘At destination’.
	Patient p = new Patient();
	  for(Patient pp : PatientList){
		  if(pp.getID().equals(a.getpatient())){
			  p = pp;
		  }
			  
	  }	
	
		System.out.println(a.id + " with status (" + a.status  + ") is going to hospital with Patient " + p.id + " with status ("+ p.status+") ..." );
		Point2D.Double p3 = new Point2D.Double(Integer.parseInt(a.getX_location()),Integer.parseInt(a.getY_location()));
		Point2D.Double hospital = new Point2D.Double(50,50);
		double deltaX1 = hospital.getX() - p3.getX();
		double deltaY1 = hospital.getY() - p3.getY();
		double coeff1 = 0.33333; 
		//double coeff1 = 0.03; 
		Rectangle rect = new Rectangle(45,45,10,10);
		while(!rect.contains(p3)){
			try {
				p3.setLocation(p3.getX() + coeff1*deltaX1, p3.getY() + coeff1*deltaY1);
				System.out.println(a.id + " with status (" + a.status  + ") Transporting Patient " +  p3 + " ..." );
				Double test = p3.getX();
				Double test1 = p3.getY();
				
				a.setX_location(Integer.toString(test.intValue()));
				a.setY_location(Integer.toString(test1.intValue()));
				try {
					writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			 	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
			    AmbulanceDisplay.NewModel.AddCSVData(Rs2);
			    AmbulanceDisplay.NewModel.fireTableDataChanged();
				Thread.sleep(1000);
	       	} catch(InterruptedException ex) {
	    	    Thread.currentThread().interrupt();
	    	}

	    if (rect.contains(p3))
	     {
	    	a.setX_location("50");
	    	a.setY_location("50");
	    	p.setX_location("50");
	    	p.setY_location("50");
	    	a.setStatus("At Destination");
	    	p.setStatus("Completed");
	    	try {
				writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
		 	
		 	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
		    AmbulanceDisplay.NewModel.AddCSVData(Rs2);
		    AmbulanceDisplay.NewModel.fireTableDataChanged();
	    	System.out.println(a.id + " with status (" + a.status  + ") is At hospital with Patient " + p.id + " with status ("+ p.status+") ..." );
	     
		}
		}
}
	    	
private void Returning(Ambulance a, List<Point> stations, ArrayList<Ambulance> AmbulanceList, ArrayList<Patient> PatientList, List<Station> stationList ) {
	
	this.stationList = stationList;
	this.AmbulanceList = AmbulanceList;
	this.PatientList = PatientList;
	Patient p = new Patient();
	  for(Patient pp : PatientList){
		  if(pp.getID().equals(a.getpatient())){
			  p = pp;
		  }
			  
	  }

	    
	    		Point pat = new Point(Integer.parseInt(a.getX_location()),Integer.parseInt(a.getY_location()));
	    		System.out.println(a + "THIS AMBULANCE IS HERE: " + pat);
	    		Station closest = FindNearestStation.main(pat, stations, AmbulanceList, stationList);
	    		System.out.println("THIS STATION HAS CAPACITY: " + closest.getCapacity());
	    		System.out.println(a.getID() + " with status (" + a.getStatus()  + ") is going to station " + closest + " ..." );
	    		Point2D.Double p4 = new Point2D.Double(Integer.parseInt(a.getX_location()),Integer.parseInt(a.getY_location()));
	    		Point2D.Double stat = new Point2D.Double(closest.getX_location(), closest.getY_location());
	    		double deltaX2 = stat.getX() - p4.getX();
	    		double deltaY2 = stat.getY() - p4.getY();
	    		double coeff2 = 0.33333; 
	    		//double coeff2 = 0.03; 
	    		Double test4 = stat.getX();
				Double test5 = stat.getY();
	    		Rectangle rect1 = new Rectangle(test4.intValue()-5,test5.intValue()-5,10,10);
	    		while(!rect1.contains(p4)){
	    			try {
	    				p4.setLocation(p4.getX() + coeff2*deltaX2, p4.getY() + coeff2*deltaY2);
	    				System.out.println(a.id + " with status (" + a.getStatus()  + ") Going to " +  stat + "AND IS CURRENTLY AT" + p4 +" ..." );
	    				Double test = p4.getX();
	    				Double test1 = p4.getY();
	    				a.setX_location(Integer.toString(test.intValue()));
	    				a.setY_location(Integer.toString(test1.intValue()));
	    				a.setLocation(("(" + a.getX_location() + ", " + a.getY_location() + ")"));
	    				
	    			    writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
	    				ArrayList<String[]> Rs21 = R1.ReadCSVfile(DataFile1);
	    				AmbulanceDisplay.NewModel.AddCSVData(Rs21);
	    				AmbulanceDisplay.NewModel.fireTableDataChanged();
	    				Thread.sleep(1000);
	    	       	} catch(InterruptedException | ClassNotFoundException | IOException ex) {
	    	    	    Thread.currentThread().interrupt();
	    	    	}
	    	}
	    	if(rect1.contains(p4)){
	    		a.setStatus("At Station");
	    		a.setX_location(Integer.toString(closest.getX_location()));
				a.setY_location(Integer.toString(closest.getY_location()));
		    	a.setLocation(("(" + closest.getX_location() + ", " + closest.getY_location() + ")"));
		    	for(Station stat1: stationList){
		    		if(stat1.equals(closest)){
		    			stat1.addambulance(a);
		    		}
		    	}
		    	this.stationList = stationList;
		    	//closest.addambulance(a);
	    		//closest.setCapacity(closest.getCapacity());
	    		System.out.println("ADDED AMBULANCE TO STATION" + closest + a);
	    		System.out.println(a.getID() + " with status (" + a.getStatus()  + ") IS AT STATION " +  stat + " ..." + a.getLocat());
	    		try {
					writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
				} catch (Exception e) {
					
					e.printStackTrace();
				} 
			 	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
			    AmbulanceDisplay.NewModel.AddCSVData(Rs2);
			    AmbulanceDisplay.NewModel.fireTableDataChanged();
	    	}
	    	 System.out.println();
	    		
	     }
	    	
	
}
	
	



