package Assignment3;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmbulanceThread implements Runnable {
	Ambulance a = new Ambulance(); 
	List<Point> stations = new ArrayList<Point>();
	ArrayList<Ambulance> AmbulanceList = new ArrayList<Ambulance>();
	ArrayList<Patient> PatientList = new ArrayList<Patient>();
	List<Station> stationList = new ArrayList<Station>();
	  private ArrayList<Patient> tt = new ArrayList<Patient>();
	  static Tester1.CSVFile R1 = new Tester1.CSVFile(); 
	  private File DataFile1 = new File("ambulances-2.csv");
		   public AmbulanceThread(Ambulance a, List<Point> stations, ArrayList<Ambulance> AmbulanceList, ArrayList<Patient> PatientList, List<Station> stationList, ArrayList<Patient> tt ) {
			   this.a = a; 
			   this.stations = stations;
			   this.AmbulanceList = AmbulanceList;
			   this.PatientList =  PatientList;
			   this.stationList =  stationList;
			   this.tt = tt;
		   }	
		
		 
		   public synchronized void  run() {
				if(a.getStatus().equals("At Station")&& !tt.isEmpty()){ // sets status to responding
					synchronized(this){
					System.out.println(a.getID() + " IS SEARCHING FOR PATIENTS");
					Patient P = findPatient(a, PatientList, tt);
					if(P.getID() != ""){
						getPatient(a, P);
						tt.remove(P);
					}
					}
					 System.out.println("HERE");
					 for(Patient pe : tt){
						 System.out.println(pe);
					 }
					 System.out.println("THERE");
				}
				if(a.getStatus().equals("Responding")){ // sets status to at scene
					for(Station st: stationList){
						if(st.getambulances().contains(a)){
							synchronized(this){
							st.removeambulance(a);
							}
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
			    	    synchronized(this){
			    	    a.setStatus("Transporting");
			    		p.setStatus("Transporting");
			    	    }
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
			    	    synchronized(this){
			    	    a.setStatus( "Returning");
			    	    a.setpatient(""); //clear patient from ambulance
			    	    p.setambulance ( ""); //clear ambulance from patient
			    	    }
			    	    System.out.println(a.id + " with status (" + a.status  + ") is returning from hospital ..." );
			    	    
			    	    	ArrayList<String[]> strings = new ArrayList<>(AmbulanceList.size());
			    	    	for (Ambulance str : AmbulanceList) {
							    strings.add(new String[]{str.getID(), str.getX_location()+"," +str.getY_location(), str.getStatus(), str.getpatient()});
							}
			    			
			    	    	synchronized(this){
			    			AmbulanceDisplay.NewModel.AddCSVData(strings);
			    	    	}
			    			AmbulanceDisplay.NewModel.fireTableDataChanged();
			    	    
//			    	    try {
//							writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
//						} catch (Exception e) {
//							e.printStackTrace();
//						} 
//					 	ArrayList<String[]> Rs5 = R1.ReadCSVfile(DataFile1);
//					    AmbulanceDisplay.NewModel.AddCSVData(Rs5);
//					    AmbulanceDisplay.NewModel.fireTableDataChanged();
			    	   // }
			    	} catch(InterruptedException ex) {
			    	    Thread.currentThread().interrupt();
			    	}
				
				}

				if(a.getStatus().equals("Returning")){
				    Returning(a, stations, AmbulanceList, PatientList, this.stationList);
				}
				  synchronized(this){
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
			private synchronized void getPatient(Ambulance a, Patient p) {
				 synchronized(this){
					// if(a.getpatient() != "" )
			p.setStatus("Assigned");
			a.setStatus("Responding");
			a.setpatient(p.getID());
			p.setambulance( a.getID());
				  }
			System.out.println(a.getID() + " with status (" + a.getStatus()  + ") is picking up Patient " + p.getID() + " with status ("+ p.getStatus() +") ..." + a.getLocat() + p.getLocat());
			  
//			try {
//			writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
//			} catch (ClassNotFoundException e) {
//
//			e.printStackTrace();
//			} catch (IOException e) {
//
//			e.printStackTrace();
//			}
			ArrayList<String[]> strings = new ArrayList<>(AmbulanceList.size());
			for (Ambulance str : AmbulanceList) {
			    strings.add(new String[]{str.getID(), str.getX_location()+"," +str.getY_location(), str.getStatus(), str.getpatient()});
			}
			
		//	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
			synchronized(this){
			AmbulanceDisplay.NewModel.AddCSVData(strings);
			}
			AmbulanceDisplay.NewModel.fireTableDataChanged();
			}
			
			public synchronized Patient findPatient(Ambulance a1, ArrayList<Patient> PatientList, ArrayList<Patient> tt){
			Patient pe = new Patient();
			for(Patient p : PatientList){
				if(p.getStatus().equals("Pending")){
					synchronized(this){
					Point amb = new Point(Integer.parseInt(a1.getX_location()),Integer.parseInt(a1.getY_location()));
					Patient closest1 = FindNearestPatient.main(amb, PatientList, tt);
					if(closest1.getID().equals(p.getID())){
						pe = p;
					}
					}
				}	
			}

			return pe;

			}



			void prepareGUI(ArrayList<Patient> PatientList,ArrayList<Ambulance> AmbulanceList){
			AmbulanceDisplay.main( PatientList, AmbulanceList);
			}

			private synchronized void Responding(Ambulance a, List<Point> stations, ArrayList<Ambulance> AmbulanceList, ArrayList<Patient> PatientList ) {
			this.AmbulanceList = AmbulanceList;
			this.PatientList = PatientList;
			Patient p = new Patient();
			for(Patient pp : PatientList){
			  if(pp.getID().equals(a.getpatient())){
				  p = pp;
			  }
				  
			}

			if(p.getStatus() != null){
			  

			Point2D p1 = new Point2D.Double(Integer.parseInt(a.getX_location()),Integer.parseInt(a.getY_location()));
			Point2D p2 = new Point2D.Double(Integer.parseInt(p.getX_location()),Integer.parseInt(p.getY_location()));

			double deltaX = p2.getX() - p1.getX();
			double deltaY = p2.getY() - p1.getY();
			double coeff = 0.01; 
			//double coeff = 0.25;
			//Responding: 4 moves/second
			//Transporting: 3 moves/second
			// Returning: 3 moves/second
			while(!p1.equals(p2)){
			try {
				  synchronized(this){
				p1.setLocation(p1.getX() + coeff*deltaX, p1.getY() + coeff*deltaY);
				System.out.println(a.id + " with status (" + a.status  + ") Transporting Patient " +  p1 + " ..." );
				Double test = p1.getX();
				Double test1 = p1.getY();
				a.setX_location(Integer.toString(test.intValue()));
				a.setY_location(Integer.toString(test1.intValue()));
				ArrayList<String[]> strings = new ArrayList<>(AmbulanceList.size());
				for (Ambulance str : AmbulanceList) {
				    strings.add(new String[]{str.getID(), str.getX_location()+"," +str.getY_location(), str.getStatus(), str.getpatient()});
				}
				
			//	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
				synchronized(this){
				AmbulanceDisplay.NewModel.AddCSVData(strings);
				}
				AmbulanceDisplay.NewModel.fireTableDataChanged();
				}
//				 try {
//				writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
//				 } catch (Exception e) {
//					 e.printStackTrace();
//				 } 
//				 ArrayList<String[]> Rs6 = R1.ReadCSVfile(DataFile1);
//				 AmbulanceDisplay.NewModel.AddCSVData(Rs6);
//				 AmbulanceDisplay.NewModel.fireTableDataChanged();
//				 }
				 
			    Thread.sleep(250);    
				} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			}
			if(p1.equals(p2)){
				  
				Double test = p2.getX();
				Double test1 = p2.getY();
				synchronized(this){
				a.setStatus("At Scene");
				a.setX_location(Integer.toString(test.intValue()));
				a.setY_location(Integer.toString(test1.intValue()));
				System.out.println(a.id + " with status (" + a.status  + ") is at scene with Patient " + p.id + " with status ("+ p.status+") ..." );
				ArrayList<String[]> strings = new ArrayList<>(AmbulanceList.size());
				for (Ambulance str : AmbulanceList) {
				    strings.add(new String[]{str.getID(), str.getX_location()+"," +str.getY_location(), str.getStatus(), str.getpatient()});
				}
			
				synchronized(this){
				AmbulanceDisplay.NewModel.AddCSVData(strings);
				}
				AmbulanceDisplay.NewModel.fireTableDataChanged();
				}
				
				//				try {
//					writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			 	
//			 	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
//			    AmbulanceDisplay.NewModel.AddCSVData(Rs2);
//			    AmbulanceDisplay.NewModel.fireTableDataChanged();
				  }
			}
			}
		
			private synchronized void Transporting(Ambulance a, List<Point> stations, ArrayList<Ambulance> AmbulanceList, ArrayList<Patient> PatientList ) {
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
			//double coeff1 = 0.33333; 
		    double coeff1 = 0.03; 
		    //Transporting: 3 moves/second
			// Returning: 3 moves/second
			Rectangle rect = new Rectangle(45,45,10,10);
			while(!rect.contains(p3)){
				try {
					  
					p3.setLocation(p3.getX() + coeff1*deltaX1, p3.getY() + coeff1*deltaY1);
					System.out.println(a.id + " with status (" + a.status  + ") Transporting Patient " +  p3 + " ..." );
					Double test = p3.getX();
					Double test1 = p3.getY();
					synchronized(this){
					a.setX_location(Integer.toString(test.intValue()));
					a.setY_location(Integer.toString(test1.intValue()));
					
					}
					ArrayList<String[]> strings = new ArrayList<>(AmbulanceList.size());
					for (Ambulance str : AmbulanceList) {
					    strings.add(new String[]{str.getID(), str.getX_location()+"," +str.getY_location(), str.getStatus(), str.getpatient()});
					}
					
				//	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
					synchronized(this){
					AmbulanceDisplay.NewModel.AddCSVData(strings);
					}
					AmbulanceDisplay.NewModel.fireTableDataChanged();
					
//					try {
//						writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
//					} catch (Exception e) {
//						e.printStackTrace();
//					} 
//				 	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
//				    AmbulanceDisplay.NewModel.AddCSVData(Rs2);
//				    AmbulanceDisplay.NewModel.fireTableDataChanged();
					  
					Thread.sleep(300);
			   	} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			if (rect.contains(p3))
			 {
				  synchronized(this){
					  
				a.setX_location("50");
				a.setY_location("50");
				p.setX_location("50");
				p.setY_location("50");
				a.setStatus("At Destination");
				p.setStatus("Completed");
				ArrayList<String[]> strings = new ArrayList<>(AmbulanceList.size());
				for (Ambulance str : AmbulanceList) {
				    strings.add(new String[]{str.getID(), str.getX_location()+"," +str.getY_location(), str.getStatus(), str.getpatient()});
				}
				
			//	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
				AmbulanceDisplay.NewModel.AddCSVData(strings);
				AmbulanceDisplay.NewModel.fireTableDataChanged();
				}
//				try {
//					writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
//				} catch (Exception e) {
//					e.printStackTrace();
//				} 
//			 	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
//			    AmbulanceDisplay.NewModel.AddCSVData(Rs2);
//			    AmbulanceDisplay.NewModel.fireTableDataChanged();
//				  }
				System.out.println(a.id + " with status (" + a.status  + ") is At hospital with Patient " + p.id + " with status ("+ p.status+") ..." );
			 
			}
			}
			}
				
			private synchronized void Returning(Ambulance a, List<Point> stations, ArrayList<Ambulance> AmbulanceList, ArrayList<Patient> PatientList, List<Station> stationList ) {

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
					//double coeff2 = 0.33333; 
					double coeff2 = 0.03; 
					Double test4 = stat.getX();
					Double test5 = stat.getY();
					Rectangle rect1 = new Rectangle(test4.intValue()-5,test5.intValue()-5,10,10);
					while(!rect1.contains(p4)){
						try {
							  
							p4.setLocation(p4.getX() + coeff2*deltaX2, p4.getY() + coeff2*deltaY2);
							System.out.println(a.id + " with status (" + a.getStatus()  + ") Going to " +  stat + "AND IS CURRENTLY AT" + p4 +" ..." );
							Double test = p4.getX();
							Double test1 = p4.getY();
							synchronized(this){
							a.setX_location(Integer.toString(test.intValue()));
							a.setY_location(Integer.toString(test1.intValue()));
							a.setLocation(("(" + a.getX_location() + ", " + a.getY_location() + ")"));
							}
							ArrayList<String[]> strings = new ArrayList<>(AmbulanceList.size());
							for (Ambulance str : AmbulanceList) {
							    strings.add(new String[]{str.getID(), str.getX_location()+"," +str.getY_location(), str.getStatus(), str.getpatient()});
							}
						//	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
							synchronized(this){
							AmbulanceDisplay.NewModel.AddCSVData(strings);
							}
							AmbulanceDisplay.NewModel.fireTableDataChanged();
							
//						    writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
//							ArrayList<String[]> Rs21 = R1.ReadCSVfile(DataFile1);
//							AmbulanceDisplay.NewModel.AddCSVData(Rs21);
//							AmbulanceDisplay.NewModel.fireTableDataChanged();
							// }
							Thread.sleep(300);
				       	} catch(Exception ex) {
				    	    Thread.currentThread().interrupt();
				    	}
				}
				if(rect1.contains(p4)){
					  synchronized(this){
					a.setStatus("At Station");
					a.setX_location(Integer.toString(closest.getX_location()));
					a.setY_location(Integer.toString(closest.getY_location()));
			    	a.setLocation(("(" + closest.getX_location() + ", " + closest.getY_location() + ")"));
			    	for(Station stat1: stationList){
			    		if(stat1.equals(closest)){
			    			stat1.addambulance(a);
			    		}
			    	}
					  }
			    	this.stationList = stationList;
			    	//closest.addambulance(a);
					//closest.setCapacity(closest.getCapacity());
					System.out.println("ADDED AMBULANCE TO STATION" + closest + a);
					System.out.println(a.getID() + " with status (" + a.getStatus()  + ") IS AT STATION " +  stat + " ..." + a.getLocat());
					ArrayList<String[]> strings = new ArrayList<>(AmbulanceList.size());
					for (Ambulance str : AmbulanceList) {
					    strings.add(new String[]{str.getID(), str.getX_location()+"," +str.getY_location(), str.getStatus(), str.getpatient()});
					}
				
				//	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
					synchronized(this){
					AmbulanceDisplay.NewModel.AddCSVData(strings);
					}
					AmbulanceDisplay.NewModel.fireTableDataChanged();
					
//					try {
//						writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
//					} catch (Exception e) {
//						
//						e.printStackTrace();
//					} 
//				 	ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
//				    AmbulanceDisplay.NewModel.AddCSVData(Rs2);
//				    AmbulanceDisplay.NewModel.fireTableDataChanged();
					//  }
				}
				 System.out.println();
				
			 }


		   }


			   
		   
		


