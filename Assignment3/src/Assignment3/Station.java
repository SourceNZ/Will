package Assignment3;

import java.util.List;

public class Station {
	protected  String Name;
	protected  int x_location;
	protected  int y_location;
	protected  int capacity;
	protected  List<Ambulance> ambulances;
	protected  String location = ("(" + x_location + ", " + y_location + ")");
	//int i; //selected patient, if not an already existing patient then it is set to the next unused row.
	public Station(){
		this.Name = "";
		this.x_location =  0;
		this.y_location = 0;
		this.capacity =  0;
		this.ambulances =  null;
		this.location = ("(" + x_location + ", " + y_location + ")");

	}
	public Station(String id_,int x_location_, int y_location_, int status_,List<Ambulance> ambulance_){
		this.Name = id_;
		this.x_location = x_location_;
		this.y_location = y_location_;
		this.capacity = status_;
		this.ambulances = ambulance_;
		this.location = ("(" + x_location_ + ", " + y_location_ + ")");
	}
	public String getLocat(){
		return this.location;
	}
	public void setLocation(String loc){
		 this.location= loc;
	}
	public String getName(){
		return this.Name;
	}
	public void setName(String newid){
		this.Name = newid;
	}
	public int getX_location(){
		return x_location;
	}
	public void setX_location(int newx){
		x_location = newx;
	}
	public int getY_location(){
		return y_location;
	}
	public  void setY_location(int newy){
		y_location = newy;
	}
	public int getCapacity(){
		return this.capacity;
	}
	public void setCapacity(int newStatus){
		this.capacity = newStatus;
	}
	public List<Ambulance> getambulances(){
		return ambulances;
	}
	public void removeambulance(Ambulance newamb){
		ambulances.remove(newamb);
	}
	public void addambulance(Ambulance newamb){
		ambulances.add(newamb);
	}
	public String toString(){
		return ("" + this.Name + "," + x_location + "," + y_location +  "," + capacity + ", " + ambulances);
		
	}
}