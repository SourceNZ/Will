package Assignment3;

public class Ambulance
{
  protected String id;
  protected String x_location;
  protected String y_location;
  protected String status;
 // protected String location;
  protected String patient;
  protected  String location = ("(" + x_location + ", " + y_location + ")");

  // int i; //selected patient, if not an already existing patient then it is set to the next unused row.
  public Ambulance()
  {
    this.id = "";
    this.x_location = "0";// value set in the jtextbox;
    this.y_location = "0";// value set in the jtextbox;
    this.status = null;// value set in the jtextbox;
    this.patient = null;// value set in the jtextbox;
    this.location = ("(" + x_location + ", " + y_location + ")");

  }

  public Ambulance(String id_, String x_location_, String y_location_, String status_, String patient)
  {
    this.id = id_;
    this.x_location = x_location_;
    this.y_location = y_location_;
    this.status = status_;
    this.patient = patient;
    this.location = ("(" + x_location_ + ", " + y_location_ + ")");
  }
  public String getLocat(){
	return this.location;
  }
  public void setLocation(String loc){
	 this.location= loc;
  }
  public String getID()
  {
    return id;
  }

  public void setID(String newid)
  {
    id = newid;
  }

  public String getX_location()
  {
    return x_location;
  }

  public void setX_location(String newx)
  {
    x_location = newx;
  }

  public String getY_location()
  {
    return y_location;
  }

  public void setY_location(String newy)
  {
    y_location = newy;
  }

  public String getStatus()
  {
    return status;
  }

  public void setStatus(String newStatus)
  {
    status = newStatus;
  }

  public String getpatient()
  {
    return patient;
  }

  public void setpatient(String newamb)
  {
    patient = newamb;
  }

  @Override
  public String toString()
  {
    return ("" + this.id + "," + x_location + "," + y_location + "," + status + ", " + patient);

  }
}
