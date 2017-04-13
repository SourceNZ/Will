package Assignment3;

import java.awt.Point;
import java.util.List;


public class FindNearestStation {
	
  public static Station main(Point pat,  List<Point> ambulances1, List<Ambulance> AmbulanceList, List<Station> stationList) {
	//The number of ambulances a station can accommodate is defined by the total number of ambulances divided by three, rounded up. 
	//For example, if there are four ambulances defined, each station can accommodate two ambulances (four divided by three, rounded up).
	//• A station is available if there are less ambulances at the station than it can accommodate.
	
	
	Point Initial = pat; // pat is the ambulances location, always 50,50;
    //Point p1 = ambulances1.get(0); //this is stations list with all the points
    Point p1 = new Point(stationList.get(0).getX_location(), stationList.get(0).getY_location());
    Station st = new Station(stationList.get(0).getName(),stationList.get(0).getX_location(),stationList.get(0).getY_location(),stationList.get(0).getCapacity(),stationList.get(0).getambulances());
    
    //36.0555 - 30,80
    //64.0312 - 10,0
    //50 - 90,20
    
    System.out.println(p1 + " THIS " + st);
    double shortestDistance = distance(Initial, p1); 
//    for (int i = 0; i < stationList.size(); i++) {
//    	System.out.println("stationList.get(i).getambulances().size() " +  stationList.get(i).getambulances().size() );
//    	if(stationList.get(i).getambulances().size() < stationList.get(i).getCapacity()){
//    		double distance = distance(Initial, new Point(stationList.get(i).getX_location(), stationList.get(i).getY_location())); 
//    		if (shortestDistance > distance) {
//    			//p1 = new Point(stationList.get(i).getX_location(), stationList.get(i).getY_location()); 
//    			st = stationList.get(i);
//    			shortestDistance = distance; 
//    			}
//    		}
//    	else if(stationList.get(i++).getambulances().size() < stationList.get(i++).getCapacity()){
//    		st = stationList.get(i);
//    		return st;
//			
//    	}
//    	else if(stationList.get(stationList.size()-1).getambulances().size() < stationList.get(stationList.size()-1).getCapacity()){
//    		st = stationList.get(i);
//    		return st;
//			
//    	}
//    	    System.out.println(st + "THIS STATION IS FULL");
//      }   
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
//    
//   for(Station stat: stationList){
//	   if(stat.getambulances().size() > stat.getCapacity()){
//		   
//	   }
//   }
//    if(st.getambulances().size() > st.getCapacity()){
//        Station st1 = st;
//    	if((st.getName().equals("Bluelane")) && (st.getambulances().size() < st.getCapacity())){
//    			st1 = st;
//    			
//    	}
//    	else if((st.getName().equals("Greenfields")) && (st.getambulances().size() < st.getCapacity())){
//    		
//    			st1 = st;
//    	}
//    	else if((st.getName().equals("Redvill")) && (st.getambulances().size() < st.getCapacity())){
//    			return st1 = st;
//    	}
//    }
    System.out.println("The closest point to " + Initial + " is " +  st );
//    if(st.getambulances().size() > st.getCapacity()){
//    	
//    }
	return st;

}
  private static double distance(Point x, Point y) {
      return Math.sqrt((y.getX() - x.getX()) * (y.getX() - x.getX()) + (y.getY() - x.getY()) * (y.getY() - x.getY()));
    }

}
