package Assignment3;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class FindNearestPatient {
	//List<Patient> PatientList
  public static Patient main(Point pat, List<Patient> PatientList, ArrayList<Patient> tt) {

	  List<Patient> patients  = tt;

    Point Initial = pat;
    Point p1 = new Point(Integer.parseInt(patients.get(0).getX_location()),Integer.parseInt(patients.get(0).getY_location()));
    
    double shortestDistance = distance(Initial, p1); 
    Patient am = new Patient(patients.get(0).getID(),patients.get(0).getX_location(), patients.get(0).getY_location(), patients.get(0).getStatus(), patients.get(0).getambulance() );
    for (int i = 0; i < patients.size(); i++) {
        double distance = distance(Initial,  new Point(Integer.parseInt(patients.get(i).getX_location()),Integer.parseInt(patients.get(i).getY_location()))); 
        if (shortestDistance > distance) {
          am = patients.get(i); 
          shortestDistance = distance; 
        }
      }
    
    System.out.println("The closest point to " + Initial + " is " +  am );
	return am;

}
  private static double distance(Point x, Point y) {
      return Math.sqrt((y.getX() - x.getX()) * (y.getX() - x.getX()) + (y.getY() - x.getY()) * (y.getY() - x.getY()));
    }

}