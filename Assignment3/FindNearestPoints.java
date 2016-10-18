package Assignment3;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class FindNearestPoints {
  public static Point main(Point pat, ArrayList<Ambulance> ambulanceList2) {
	  List<Point> ambulances = new ArrayList<Point>();
		for(Ambulance a : ambulanceList2){
			if(a.status.equals("At Station")){
			 ambulances.add(new Point(Integer.parseInt(a.x_location),Integer.parseInt(a.y_location)));
			}
		}
    Point Initial = pat;
    Point p1 = ambulances.get(0); ;
    double shortestDistance = distance(Initial, ambulances.get(0)); 

    for (int i = 0; i < ambulances.size(); i++) {
        double distance = distance(Initial, ambulances.get(i)); 
        if (shortestDistance > distance) {
          p1 = ambulances.get(i); 
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