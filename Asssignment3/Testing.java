package Asssignment3;

import java.awt.Point;
import java.util.ArrayList;

public class Testing {
	 	private ArrayList<Patient> PatientList;
	 	private ArrayList<Ambulance> AmbulanceList;

	public static void main(ArrayList<Patient> patientList2, ArrayList<Ambulance> ambulanceList2){
		Testing t = new Testing(patientList2, ambulanceList2);
		
		//Tester(patientList2, ambulanceList2);
		
		
	}
	public Testing(ArrayList<Patient> patientList2, ArrayList<Ambulance> ambulanceList2){
		this.PatientList = patientList2;
		this.AmbulanceList = ambulanceList2;
		Tester(PatientList, AmbulanceList);
	}
	public static void Tester(ArrayList<Patient> patientList2, ArrayList<Ambulance> ambulanceList2) {
		Point hospital = new Point(50, 50);
		Point Greenfields = new Point(10, 0);
		Point Bluelane = new Point(30, 80);
		Point Redvill = new Point(90, 20);
//		At station: check if there is a new patient to pick up, if so,
//		assign the closest unassigned patient to the ambulance and change the status to ‘Responding’. Otherwise, do nothing.		
		for(Patient p : patientList2){
			System.out.println(p.id + " " + p.status + " " +p.location+ "Initial Locaiton");
		}
		System.out.println("Patients Done!");
	
		Point Amb;
		Point Pat;
		//notFound:
		
		for(Ambulance a : ambulanceList2){
			//System.out.println(a.id + " " +a.status + " " + a.location + "Initial Locaiton ");
//			switch (a.location) {
//            case "(50, 50)":  System.out.println ("HOSPITAL");    
//            	break;
//            case "(10, 0)":  System.out.println ("Greenfields");	
//            	break;
//            case "(30, 80)":  System.out.println("Bluelane");
//            	break ;
//            case "(90, 20)" : System.out.println ("Redvill");	
//            	break ;
//            //default : System.out.println ("NO STATION " + a.location);
//            		
	//		}
			if(a.status.equals("At Station")){
				System.out.println(a.id + " Location" + a.location);
				
			
				for(Patient p : patientList2){
					if(p.status.equals("Pending")){
						System.out.println(a.id + " with status (" + a.status  + ") and Location" + a.location + " is picking up Patient " + p.id + " with status ("+ p.status+")" );
						p.status = "Assigned";
						a.status = "Transporting";
						System.out.println(a.id + " with status (" + a.status  + ") is picking up Patient " + p.id + " with status ("+ p.status+") ----" );
						break;
						
					}

				}
				
			}
		}
	
		for(Ambulance a : ambulanceList2){
			System.out.println(a);
			
		}
		
	
	
	
		System.out.println("Ambulances Done!");
		
		
		
	}
	
	

}
