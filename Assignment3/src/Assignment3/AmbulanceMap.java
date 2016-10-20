package Assignment3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

class AmbulanceMap extends JFrame{
	 private ArrayList<Patient> PatientList;
	  private ArrayList<Ambulance> AmbulanceList;
	  public AmbulanceMap(ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceList){
		  JFrame frame = new JFrame(){
		          @Override public void paint(Graphics g) {
		        	g.setColor(Color.white);
		            g.drawRect(0, 0, 500, 500);
		            g.fillRect(0, 0, 500, 500);
		            g.setColor(Color.red);
		            g.drawRect(90*5-10, 20*5-10, 20, 20);
		            g.fillRect(90*5-10, 20*5-10, 20, 20);
		            g.drawRect(80*5-10, 30*5-10, 20, 20);
		            g.fillRect(80*5-10, 30*5-10, 20, 20);
		            g.drawRect(10*5-10, 0, 20, 20);
		            g.fillRect(10*5-10, 0, 20, 20);
		            g.drawRect(50*5-10, 50*5-10, 20, 20);
		            g.fillRect(50*5-10, 50*5-10, 20, 20);
		            for(Patient p : PatientList){
		            	g.setColor(Color.green);
		            	if(!p.getX_location().equals("50") && !p.getY_location().equals("50") && !p.getStatus().equals("Pending")){
		            		g.drawRect(Integer.parseInt(p.getX_location())*5,Integer.parseInt(p.getY_location())*5, 5, 5);
			            	g.fillRect(Integer.parseInt(p.getX_location())*5,Integer.parseInt(p.getY_location())*5, 5, 5);
		            	}
		            	
		            	 
		            }
		  		  	for(Ambulance a: AmbulanceList){
		  		  	g.setColor(Color.blue);
		  		  		g.drawRect(Integer.parseInt(a.getX_location())*5,Integer.parseInt(a.getY_location())*5, 5, 5);
		  		  		g.fillRect(Integer.parseInt(a.getX_location())*5,Integer.parseInt(a.getY_location())*5, 5, 5);
		  		  	}
		  		  	
		          }
		        };
		  
		  frame.setVisible(true);
		  frame.setSize(new Dimension(500, 500));
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  frame.getContentPane().setLayout(new BorderLayout());
		  
		 // JPanel panel = new JPanel
		 // Rectangle grid = new Rectangle(0,0,500,500);
		  //frame.getContentPane().add(grid);s
		 
		  
		  //frame.setVisible(true);
	  }
	  
	  public static void main(ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceList)
	  {
	    javax.swing.SwingUtilities.invokeLater(new Runnable()
	    {
	      @Override
	      public void run()
	      {
	    	  AmbulanceMap tt = new AmbulanceMap(PatientList, AmbulanceList);
	    	  tt.repaint();
	      }
	    });
	  }


	
}