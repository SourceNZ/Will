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
 
    
    System.out.println("ambulances1.get(0) TESTING " +  p1 );
    double shortestDistance = distance(Initial, p1); 
    for (int i = 0; i < stationList.size(); i++) {
    	if(stationList.get(i).getCapacity() < stationList.get(i).getambulances().size()){
        double distance = distance(Initial, new Point(stationList.get(i).getX_location(), stationList.get(i).getY_location())); 
        if (shortestDistance > distance) {
          p1 = new Point(stationList.get(i).getX_location(), stationList.get(i).getY_location()); 
          st = stationList.get(i);
          shortestDistance = distance; 
        }
    	}
    	else{
    		System.out.println("THIS STATION IS FULL" + stationList.get(i));
    	}
      }
    
    System.out.println("The closest point to " + Initial + " is " +  p1 );
	return st;

}
  private static double distance(Point x, Point y) {
      return Math.sqrt((y.getX() - x.getX()) * (y.getX() - x.getX()) + (y.getY() - x.getY()) * (y.getY() - x.getY()));
    }

}
