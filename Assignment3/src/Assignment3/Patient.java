package Assignment3;

import java.awt.Point;

public class Patient {
	protected  String id;
	protected  String location;
	protected  String x_location;
	protected  String y_location;
	protected  String status;
	protected  String ambulance;
	//int i; //selected patient, if not an already existing patient then it is set to the next unused row.
	public Patient(){
		this.id = "";
		this.x_location =  "";//value set in the jtextbox;
		this.y_location =  "";//value set in the jtextbox;
		this.status =  null;//value set in the jtextbox;
		this.ambulance =  null;//value set in the jtextbox;
		this.location = ("(" + x_location + ", " + y_location + ")");

	}
	public Patient(String id_,String x_location_, String y_location_, String status_,String ambulance_){
		this.id = id_;
		this.x_location = x_location_;
		this.y_location = y_location_;
		this.status = status_;
		this.ambulance = ambulance_;
		this.location = ("(" + x_location_ + ", " + y_location_ + ")");
	}
	public String getLocat(){
		return this.location;
	}
	public void setLocation(String loc){
		 this.location= loc;
	}
	public String getID(){
		return id;
	}
	public void setID(String newid){
		id = newid;
	}
	public String getX_location(){
		return x_location;
	}
	public void setX_location(String newx){
		x_location = newx;
	}
	public String getY_location(){
		return y_location;
	}
	public  void setY_location(String newy){
		y_location = newy;
	}
	public String getStatus(){
		return status;
	}
	public void setStatus(String newStatus){
		status = newStatus;
	}
	public String getambulance(){
		return ambulance;
	}
	public void setambulance(String newamb){
		ambulance = newamb;
	}
	public String toString(){
		return ("" + this.id + "," + x_location + "," + y_location +  "," + status + ", " + ambulance);
		
	}
}