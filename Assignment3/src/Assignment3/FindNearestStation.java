package Assignment3;

import java.awt.Point;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class FindNearestStation {
	
  public synchronized static Station main(Point pat,  List<Point> ambulances1, List<Ambulance> AmbulanceList, List<Station> stationList) {
	
			  
			Point Initial = pat; // pat is the ambulances location, always 50,50;
		    //Point p1 = ambulances1.get(0); //this is stations list with all the points
		    Point p1 = new Point(stationList.get(0).getX_location(), stationList.get(0).getY_location());
		    Station st = new Station(stationList.get(0).getName(),stationList.get(0).getX_location(),stationList.get(0).getY_location(),stationList.get(0).getCapacity(),stationList.get(0).getambulances());
		    
		    //36.0555 - 30,80
		    //64.0312 - 10,0
		    //50 - 90,20
		    
		    System.out.println(p1 + " THIS " + st);
		    //double shortestDistance = distance(Initial, p1); 

		   Station green = new Station();
		   Station blue = new Station();
		   Station red = new Station();
		   
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
		    
		    System.out.println(green + "< LOOK HERE AND HERE >" + blue + red);
		    if(blue.getambulances().size() < blue.getCapacity()){
				st = blue;
				return st;
			}
			else if(red.getambulances().size() < red.getCapacity()){
				st = red;
				return st;
				
			}
			else if(green.getambulances().size() < green.getCapacity()){
				st = green;
				return st;
			}

		    System.out.println("The closest point to " + Initial + " is " +  st );
//		    if(st.getambulances().size() > st.getCapacity()){
//		    	
//		    }
			return st;
			  
  }
	//The number of ambulances a station can accommodate is defined by the total number of ambulances divided by three, rounded up. 
	//For example, if there are four ambulances defined, each station can accommodate two ambulances (four divided by three, rounded up).
	//• A station is available if there are less ambulances at the station than it can accommodate.
	
	
  private static double distance(Point x, Point y) {
      return Math.sqrt((y.getX() - x.getX()) * (y.getX() - x.getX()) + (y.getY() - x.getY()) * (y.getY() - x.getY()));
    }

}
