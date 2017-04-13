package Assignment3;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



/*   problems
 *   
 *   	
	

	 


	 patients that are right next to hospital, e.g patient at 49,50
	 
	 problems with patients near to stations and hospitals?
	 
	make it draw the map when the user pushes stop.
	
	get distance movements to be correct
	
 */


public class Tester1 {
  public JFrame mainFrame;
  private ArrayList<Patient> PatientList;
  private ArrayList<Ambulance> AmbulanceList;
  private List<Point> stations = new ArrayList<Point>();
  private List<Station> stationList = new ArrayList<Station>();
 static Tester1.CSVFile R1 = new Tester1.CSVFile(); 
  private File DataFile1 = new File("ambulances-2.csv");
  static ExecutorService threadPool;
  int endTime = 60;
  static AmbulanceMap map;
 
  
  public Tester1(ArrayList<Patient> PatientList,ArrayList<Ambulance> AmbulanceList, int endTime )
  {	
	  this.endTime = endTime;
	  if(System.currentTimeMillis() <=  this.endTime * 1000) {
			System.out.println("ENDING THREADS");
	  }
	  double stationCapacity;
	  stationCapacity = Math.ceil((double)AmbulanceList.size()/3);
	  int stationCap = (int) Math.round(stationCapacity);
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
	  

	  Tester(this.PatientList, this.AmbulanceList, stations, this.stationList, this.endTime);	  
  }

  public static void main(ArrayList<Patient> PatientList,ArrayList<Ambulance> AmbulanceList)  {
	  int endTime = 60;
	  Tester1 menu = new Tester1(PatientList, AmbulanceList,endTime);

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

	        }
	        brd.close();
	      }
	      catch (Exception e)
	      {
	        String errmsg = e.getMessage();
	        System.out.println("" + errmsg);
	      }
	      return Values;
	    }
	  }

  public void Tester(ArrayList<Patient> patientList2, ArrayList<Ambulance> ambulanceList2, List<Point> stations, List<Station> stationList, int endTime) {

	
		ArrayList<Patient> tt = new ArrayList<Patient>();
		for(Patient pe : PatientList){
			if(pe.getStatus().equals("Pending")){
				tt.add(pe);
			}
		}
	 map = new AmbulanceMap(PatientList, AmbulanceList);

	 this.AmbulanceList = ambulanceList2;
	 this.PatientList = patientList2;
	 this.stations = stations;
	 threadPool = Executors.newFixedThreadPool(AmbulanceList.size());


	long start = System.currentTimeMillis();
	long end = start + this.endTime*1000; 
	

	for(Ambulance a : AmbulanceList){
		try{
			Thread.sleep(100);
			Thread t = new Thread(new MyRunnableTask(a, stations, AmbulanceList, PatientList, this.stationList, tt)) ;
			threadPool.submit(t);
		}catch(Exception e){
			
		}
		
	}
	
while (System.currentTimeMillis() < end && !threadPool.isShutdown())
	{
		try{
			Thread.sleep(1000);
			ArrayList<String[]> strings = new ArrayList<>(AmbulanceList.size());
			for (Ambulance str : AmbulanceList) {
			    strings.add(new String[]{str.getID(), str.getX_location()+"," +str.getY_location(), str.getStatus(), str.getpatient()});
			}
			AmbulanceSimulation.NewModel.AddCSVData(strings);
			AmbulanceSimulation.NewModel.fireTableDataChanged();
			
       	} catch(Exception ex) {
    	    Thread.currentThread().interrupt();
    	}
	}
	map.setVisible(false);
	//map.dispose();
	
	//threadPool.cancel(true);
	threadPool.shutdownNow();
	  synchronized(this){
	try {
		writeCSVFileAmbulance.writeCSVFile(AmbulanceList);
	} catch (Exception e) {
		e.printStackTrace();
	} 
	JOptionPane.showMessageDialog(mainFrame,endTime +  "Timer is Done or Stop was pressed!");		 
	System.out.println(endTime +  "Timer is Done!");
	System.out.println(endTime +  "Timer is Done!");
	System.out.println(endTime +  "Timer is Done!");
	System.out.println(endTime +  "Timer is Done!");
	System.out.println(endTime +  "Timer is Done!");
	
	  }
	  }
		
	}





