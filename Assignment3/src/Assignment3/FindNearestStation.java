package Assignment3;

import java.awt.Point;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class FindNearestStation {
	
  public synchronized static Station main(Point pat,  List<Ambulance> AmbulanceList, List<Station> stationList) {
	  		
			Point Initial = pat; 
		    Point p1 = new Point(stationList.get(0).getX_location(), stationList.get(0).getY_location());
		    Station st = new Station(stationList.get(0).getName(),stationList.get(0).getX_location(),stationList.get(0).getY_location(),stationList.get(0).getCapacity(),stationList.get(0).getambulances());
		    //Station st = new Station();
			 Station green = new Station(stationList.get(0).getName(),stationList.get(0).getX_location(),stationList.get(0).getY_location(),stationList.get(0).getCapacity(),stationList.get(0).getambulances());
			 Station blue = new Station(stationList.get(1).getName(),stationList.get(1).getX_location(),stationList.get(1).getY_location(),stationList.get(1).getCapacity(),stationList.get(1).getambulances());
			 Station red = new Station(stationList.get(2).getName(),stationList.get(2).getX_location(),stationList.get(2).getY_location(),stationList.get(2).getCapacity(),stationList.get(2).getambulances());
			   //problem could be that its creating a new station every time
			 
		    System.out.println(p1 + " THIS " + st);
		    for(Station stt: stationList){
		    	if(stt.getName().equals("Greenfields")){
		    		System.out.println(stt + "THIS IS Greenfields");
		    		green = stt;
		    	}
		    	if(stt.getName().equals("Bluelane")){
		    		System.out.println(stt + "THIS IS Bluelane");
		    		blue = stt;
		    	}
		    	if(stt.getName().equals("Redvill")){
		    		System.out.println(stt + "THIS IS Redvill");
		    		red = stt;
		    	}
		    }
		    for(Station stat1: stationList){
	    		if(stat1.getName().equals(st.getName())){
	    			st = stat1;
	    		}
	    	}
		    System.out.println(green + "< LOOK HERE AND HERE >" + blue + red);
		  //blue.getambulances().size() ambulances is null
		    if(blue.getambulances().size() < blue.getCapacity()){
				st = blue;
				System.out.println("THIS IS WHAT IS BEING RETURNED " +st + "THIS IS BLUE AND ");
				return st;
			}
			else if(red.getambulances().size() < red.getCapacity()){
				st = red;
				System.out.println("THIS IS WHAT IS BEING RETURNED " +st + "THIS IS REDVILL AND ");
				
				return st;
			}
			 if(green.getambulances().size() < green.getCapacity()){
				st = green;
				System.out.println("THIS IS WHAT IS BEING RETURNED " +st + "THIS IS GREEN AND ");
				return st;
			}

		    System.out.println("The closest point to " + Initial + " is " +  st );
		    
		    return st;
		 
			
			  
  }
}
