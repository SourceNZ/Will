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
		  this.PatientList = PatientList;
		  this.AmbulanceList = AmbulanceList;
		  JFrame frame = new JFrame(){
		          @Override public void paint(Graphics g) {
		        	 g.drawString("Stations/Hospital:", 530, 100);
		        	 g.setColor(Color.red);
		        	 g.drawRect(630, 90, 15, 15);
			         g.fillRect(630, 90, 15, 15);
			         g.setColor(Color.black);
			         g.drawString("Ambulances:", 530, 200);
		        	 g.setColor(Color.blue);
		        	 g.drawRect(630, 190, 15, 15);
			         g.fillRect(630, 190, 15, 15);
			         g.setColor(Color.black);
			         g.drawString("Patients:", 530, 300);
		        	 g.setColor(Color.green);
		        	 g.drawRect(600, 290, 15, 15);
			         g.fillRect(600, 290, 15, 15);

		        	g.setColor(Color.white);
		            g.drawRect(0, 0, 500, 500);
		            g.fillRect(0, 0, 500, 500);
		            g.setColor(Color.red);
		            g.drawRect(90*5-10, 20*5, 15, 15);
		            g.fillRect(90*5-10, 20*5, 15, 15);
		            g.drawRect(30*5,80*5, 15, 15);
		            g.fillRect(30*5, 80*5, 15, 15);
		            g.drawRect(50, 30, 15, 15);
		            g.fillRect(50, 30, 15, 15);
		            g.drawRect(50*5-10, 50*5-10, 20, 20);
		            g.fillRect(50*5-10, 50*5-10, 20, 20);
		            for(Patient p : PatientList){
		            		g.setColor(Color.GREEN);
		            		g.drawRect(Integer.parseInt(p.getX_location())*500/100,Integer.parseInt(p.getY_location())*500/100, 7, 7);
			            	g.fillRect(Integer.parseInt(p.getX_location())*500/100,Integer.parseInt(p.getY_location())*500/100, 7, 7);
		            }
		  		  	for(Ambulance a: AmbulanceList){
		  		  		g.setColor(Color.blue);
		  		  		if((a.getY_location().equals("0"))){
		  		  			g.drawRect(Integer.parseInt(a.getX_location())*500/100, 32, 7, 7);
		  		  			g.fillRect(Integer.parseInt(a.getX_location())*500/100, 32, 7, 7);
		  		  		}
		  		  		else{
		  		  		g.drawRect(Integer.parseInt(a.getX_location())*500/100,Integer.parseInt(a.getY_location())*500/100, 7, 7);
		  		  		g.fillRect(Integer.parseInt(a.getX_location())*500/100,Integer.parseInt(a.getY_location())*500/100, 7, 7);
		  		  		}
		  		  	}
		  		  	g.setColor(Color.white);
		          }
		        };
		  
		  frame.setVisible(true);
		  frame.setSize(new Dimension(700, 500));
		  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  frame.getContentPane().setLayout(new BorderLayout());
		 
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