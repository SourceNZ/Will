package Assignment3;

import java.awt.Point;
import java.util.List;


public class FindNearestPoints {
	
  public static Point main(Point pat,  List<Point> ambulances1, List<Ambulance> AmbulanceList) {
	//find an ambulance with those co-ordinates then return it
	List<Point> ambulances = ambulances1;
    Point Initial = pat;
    Point p1 = ambulances1.get(0); ;
    double shortestDistance = distance(Initial, ambulances1.get(0)); 

    for (int i = 0; i < ambulances1.size(); i++) {
        double distance = distance(Initial, ambulances1.get(i)); 
        if (shortestDistance > distance) {
          p1 = ambulances1.get(i); 
          shortestDistance = distance; 
        }
      }
    
    System.out.println("The closest point to " + Initial + " is " +  p1 );
   
	return p1;

}
  private static double distance(Point x, Point y) {
      return Math.sqrt((y.getX() - x.getX()) * (y.getX() - x.getX()) + (y.getY() - x.getY()) * (y.getY() - x.getY()));
    }

}