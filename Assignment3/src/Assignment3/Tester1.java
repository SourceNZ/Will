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



/*   problems
 *   
 *   	
	

	 
	ambulances going to the same patient, not every ambulance is doing something, often stuck at At station.
	gui screen not implemented.
	hospital area too big?
	 
	 
	 test multiple files to make sure it works.
	 make ambulances keep searching for patients.
	 
	 patients that are right next to hospital, e.g patient at 49,50
	 
	 problems with patients near to stations and hospitals?
	 
	 station capacities not working properly.
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
  int endTime = 100;
 
  
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
	  
	  try {
		Thread.sleep(500);
		prepareGUI(PatientList, AmbulanceList);
	  } catch (InterruptedException e) {
		
		e.printStackTrace();
	  }
	  Tester(this.PatientList, this.AmbulanceList, stations, this.stationList, this.endTime);	  
  }
	
		
  public static void main(String[] args)  {
	 
	  int endTime = 100;
	  try{
		  endTime = Integer.parseInt(args[0]);
		  System.out.println(endTime + "THIS IS THE END TIME");
	  }
	  catch(Exception e){
		  endTime = 100;
	  }
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
	 
	 Tester1 menu = new Tester1(PatientList, AmbulanceList,endTime);
	

  }
  
static void prepareGUI(ArrayList<Patient> PatientList,ArrayList<Ambulance> AmbulanceList){
  AmbulanceDisplay.main( PatientList, AmbulanceList);
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

		System.out.println("BACK AT THE START");
		ArrayList<Patient> tt = new ArrayList<Patient>();
		for(Patient pe : PatientList){
			if(pe.getStatus().equals("Pending")){
				tt.add(pe);
			}
		}
	 this.AmbulanceList = ambulanceList2;
	 this.PatientList = patientList2;
	 this.stations = stations;
	 ExecutorService threadPool = Executors.newFixedThreadPool(AmbulanceList.size());
	 System.out.println(AmbulanceList.size() + "LOOK HERE WILL");

	long start = System.currentTimeMillis();
	long end = start + this.endTime*1000; 
	//AmbulanceMap map = new AmbulanceMap(PatientList, AmbulanceList);
	
	for(Ambulance a : AmbulanceList){
		Thread t = new Thread(new MyRunnableTask(a, stations, AmbulanceList, PatientList, this.stationList, tt)) ;
		threadPool.submit(t);
		
		//t.start();
		 //a, stations, AmbulanceList, PatientList, stationList, tt
		//new MyRunnableTask(a, stations, AmbulanceList, PatientList, stationList, tt;
		//Thread t = new Thread(new MyRunnableTask(a, stations, AmbulanceList, PatientList, stationList, tt));
		//AmbulanceThread t = new AmbulanceThread(a, stations, AmbulanceList, PatientList, stationList, tt);
		//threadPool.scheduleAtFixedRate(new MyRunnableTask(a, stations, AmbulanceList, PatientList, stationList, tt), 0, 2, TimeUnit.SECONDS);
		//MyRunnableTask ttt = new MyRunnableTask(a, stations, AmbulanceList, PatientList, stationList, tt);
		//threadPool.execute(new AmbulanceThread(a, stations, AmbulanceList, PatientList, stationList, tt));
		//threadPool.execute(new MyRunnableTask(a, stations, AmbulanceList, PatientList, stationList, tt));
		//threadPool.submit(new AmbulanceThread(a, stations, AmbulanceList, PatientList, stationList, tt));
		//threadPool.submit(new MyRunnableTask(a, stations, AmbulanceList, PatientList, stationList, tt));
		//threadPool.execute(t);
		//t.run();
		
	}
while (System.currentTimeMillis() < end)
	{
		try{
			Thread.sleep(1000);
			ArrayList<String[]> strings = new ArrayList<>(AmbulanceList.size());
			for (Ambulance str : AmbulanceList) {
			    strings.add(new String[]{str.getID(), str.getX_location()+"," +str.getY_location(), str.getStatus(), str.getpatient()});
			}
			AmbulanceDisplay.NewModel.AddCSVData(strings);
			AmbulanceDisplay.NewModel.fireTableDataChanged();
			//AmbulanceMap.main(PatientList, AmbulanceList);
			
       	} catch(Exception ex) {
    	    Thread.currentThread().interrupt();
    	}
		for(Station st: this.stationList){
			System.out.println(st + "HEY");
		}
		//this.stationList
		
		//threadPool.execute(new MyRunnableTask);
	}
	threadPool.shutdownNow();
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

		
	}





