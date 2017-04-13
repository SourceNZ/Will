package Assignment3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class AmbulanceSimulation extends JPanel 
{
 private ArrayList<Patient> PatientList;
  private ArrayList<Ambulance> AmbulanceList;
  static JFrame frame = new JFrame("Ambulance Information");
  //public static JTable table;
  static MyModel NewModel;
  static Tester1.CSVFile R1 = new Tester1.CSVFile(); 
  ListSelectionModel listSelectionModel;
  static File DataFile1 = new File("ambulances.csv");
  int endTime = 60;
  private Thread thread = new Thread();
 // private JTable table;
	//private JTextField textField;

  public AmbulanceSimulation(ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceList)
  {
	  		JFrame frame = new JFrame("Ambulance Information");	
	  		JPanel panel = new JPanel();
			frame.getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(500,500);
			frame.setPreferredSize(new Dimension(500, 500));
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 11, 464, 315);
			JTable table = new JTable();
			
			table = new JTable(new MyModel());
		    table.setPreferredScrollableViewportSize(new Dimension(500, 300));
		    table.setFillsViewportHeight(true);
		    scrollPane.setViewportView(table);
			panel.add(scrollPane);
			JTextField textField = new JTextField();
			textField.setBounds(190, 337, 240, 20);
			textField.addActionListener(new ActionListener()
		    {
		      @Override
		      public void actionPerformed(ActionEvent ex)
		      {
		    	 try{
		    		 
		    		 if(Integer.parseInt(textField.getText()) > 0){
		    			 endTime = Integer.parseInt(textField.getText());
		    		 }
		    	 }
		    	 catch(Exception e){
		    		 JOptionPane.showMessageDialog(frame,  "Duration Must be a number greater than 0.");		    
		    		 }
		      }
		    });
			
			panel.add(textField);
			textField.setColumns(10);
			setBorder(new EmptyBorder(5, 5, 5, 5));
			NewModel = new MyModel();
			table.setModel(NewModel);
			ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
		    NewModel.AddCSVData(Rs2);
			NewModel.fireTableDataChanged();
			JLabel lblDurationseconds = new JLabel("Duration(Seconds)");
			lblDurationseconds.setFont(new Font("Tahoma", Font.BOLD, 12));
			lblDurationseconds.setBounds(37, 337, 138, 14);
			panel.add(lblDurationseconds);
			JButton btnStart = new JButton("Start");
			btnStart.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnStart.setBounds(37, 378, 107, 44);
			btnStart.addActionListener(new ActionListener()
				    {
				      @Override
				      public void actionPerformed(ActionEvent ex)
				      {
				    	  try{
				    	  if (Integer.parseInt(textField.getText()) > 0) {
							    Thread thread = new Thread() {
							        public void run() {
							        	Tester1 menu = new Tester1(PatientList, AmbulanceList, endTime);
							        }
							    };
							    thread.start();
							}
				    	  }catch(Exception e){
				    		  JOptionPane.showMessageDialog(frame,  "Enter a Duration!");		
				    	  }
		

				      }
				    });
			panel.add(btnStart);
			JButton btnStop = new JButton("Stop");
			btnStop.setFont(new Font("Tahoma", Font.BOLD, 12));
			btnStop.setBounds(332, 378, 98, 44);
			btnStop.addActionListener(new ActionListener()
		    {
		      @Override
		      public void actionPerformed(ActionEvent ex)
		      {
		    	  try{
		    		  Tester1.threadPool.shutdownNow();
		    		  //Tester1.map.repaint();
		    		  AmbulanceMap.main(PatientList, AmbulanceList);
		    	  }catch(Exception e){
		    		  JOptionPane.showMessageDialog(frame,  "There is nothing to stop.");		    
		    	  }
		    	
		      }
		    });
			
			panel.add(btnStop);
			frame.add(panel);
			frame.pack();
		    frame.setVisible(true);

    this.PatientList = PatientList;
    this.AmbulanceList = AmbulanceList;

  }
  public static class CSVFile  {
	    public ArrayList<String[]> ReadCSVfile(File DataFile)    {
	      ArrayList<String[]> Values = new ArrayList<>();// contains raw data
	      try
	      {
	        BufferedReader brd = new BufferedReader(new FileReader(DataFile));
	        brd.readLine();

	        String st = null;
	        while ((st = brd.readLine()) != null)
	        {
	            String[] destStrings = new String[6];
	            String[] srcStrings = st.split(",");
	            String[] srcCopy = new String[6];
	            srcCopy[0] = srcStrings[0]; 
	            srcCopy[1] = ("(" + srcStrings[1] + ", " + srcStrings[2] + ")");  
	            srcCopy[2] = srcStrings[3]; 
	            if(srcStrings.length == 4){ 
	          	  srcCopy[3] = " ";
	            }
	            else if(srcStrings.length == 5){
	          	  srcCopy[3] = srcStrings[4];
	            }
	            srcCopy[4] = srcStrings[1];
	            srcCopy[5] = srcStrings[2];
	            System.arraycopy(srcCopy, 0, destStrings, 0, 6);
	            destStrings[0] = destStrings[0].replaceAll("\"", "");
	            destStrings[1] = destStrings[1].replaceAll("\"", "");
	            destStrings[2] = destStrings[2].replaceAll("\"", "");
	            destStrings[3] = destStrings[3].replaceAll("\"", "");
	            Values.add(destStrings);

	        }
	        brd.close();
	      }
	      catch (Exception e)
	      {
	        String errmsg = e.getMessage();
	        System.out.println("" + errmsg);
	      }
	      return Values;
	    }
	  }

  public static void loadAmbulanceDisplay()
  {
	int endTime = 60;
	ArrayList<Patient> PatientList = new ArrayList<Patient>();
	ArrayList<Ambulance> AmbulanceList = new ArrayList<Ambulance>();
	Tester1.CSVFile s = new Tester1.CSVFile();
	ArrayList<String[]> e = s.ReadCSVfile(new File("ambulances.csv"));
	new Tester1.CSVFile();
	ArrayList<String[]> v = s.ReadCSVfile(new File("patients.csv"));	
	 for (String[] strings : v)
	    {
	      Patient test1 = new Patient(strings[0], strings[4], strings[5], strings[2], strings[3]);
	      if(!PatientList.contains(test1)){
	    	  PatientList.add(test1);
	      }
	    }
	 for (String[] strings : e)
	    {
	      Ambulance test1 = new Ambulance(strings[0], strings[4], strings[5], strings[2], strings[3]);
	      if(!AmbulanceList.contains(test1)){
	    	  AmbulanceList.add(test1);
	      }
	    }
	 //AmbulanceDisplay test = new AmbulanceDisplay(PatientList, AmbulanceList);
	 AmbulanceSimulation newContentPane = new AmbulanceSimulation(PatientList, AmbulanceList);
	 newContentPane.setVisible(true);
	 //Tester1 menu = new Tester1(PatientList, AmbulanceList, endTime);
    

  }
  public class MyModel  extends AbstractTableModel  { // implements TableModelListener
    public final String[] columnNames = { "id", "Location", "status", "Patient" };
    public ArrayList<String[]> Data = new ArrayList<String[]>();
    

    public void AddCSVData(ArrayList<String[]> DataIn)
    {
      this.Data = DataIn;
      this.fireTableDataChanged();
    }

    public String[] getRowAt(int row)
    {
      return (String[]) Data.get(row);
    }

    @Override
    public int getColumnCount()
    {
      return this.columnNames.length;// length;
    }

    @Override
    public int getRowCount()
    {
      return this.Data.size();
    }

    @Override
    public String getColumnName(int col)
    {
      return this.columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col)
    {
      try
      {
        return getRowAt(row)[col];
      }
      catch (Exception ex)
      {
        return null;
      }

    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
      return false;

    }

    public void setValueAt(String value, int row, int column)
    {
      getRowAt(row)[column] = value;
      this.fireTableDataChanged();
    }

  }

  public static void main(String[] args)
  {
    javax.swing.SwingUtilities.invokeLater(new Runnable()
    {
      @Override
      public void run()
      {
        loadAmbulanceDisplay();
      }
    });
  }

 
}
