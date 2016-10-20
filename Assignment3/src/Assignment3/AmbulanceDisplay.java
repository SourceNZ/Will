package Assignment3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class AmbulanceDisplay extends JPanel 
{
 private ArrayList<Patient> PatientList;
  private ArrayList<Ambulance> AmbulanceList;
  static JFrame frame = new JFrame("Ambulance Information");
  public static JTable table;
  static MyModel NewModel;
  static Tester1.CSVFile R1 = new Tester1.CSVFile(); 
  ListSelectionModel listSelectionModel;
  static File DataFile1 = new File("ambulances.csv");
  int endTime = 0;


  public AmbulanceDisplay(ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceList)
  {
    super(new BorderLayout(3, 3));
    this.PatientList = PatientList;
    this.AmbulanceList = AmbulanceList;
    this.table = new JTable(new MyModel());
    this.table.setPreferredScrollableViewportSize(new Dimension(500, 300));
    this.table.setFillsViewportHeight(true);
    JPanel AmbulancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    add(AmbulancePanel, BorderLayout.SOUTH);
    JScrollPane scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);
    setBorder(new EmptyBorder(5, 5, 5, 5));
    NewModel = new MyModel();
    table.setModel(NewModel);
    ArrayList<String[]> Rs2 = R1.ReadCSVfile(DataFile1);
    NewModel.AddCSVData(Rs2);
    NewModel.fireTableDataChanged();
    JTextField texting = new JTextField();
    texting.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent ex)
      {
    	  //Tester1.main(new String[]{texting.getText()});
        //this should be the duration of the animation
    	
      }
    });
   //AmbulanceMap.main(PatientList, AmbulanceList);
    JButton startButton = new JButton("Start");
    startButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent ex)
      {    	 
    	  //this needs to start all threads/open gui
      }
    });
  
    JButton stopButton = new JButton("Stop");
    stopButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
    	 //this needs to stop all threads
    	  // Tester1.threadPool.shutdownNow();
    	 
      }
    });
    JPanel tests = new JPanel(new BorderLayout(3,3));
    tests.add(stopButton, BorderLayout.EAST);
    tests.add(startButton, BorderLayout.WEST);
    add(tests, BorderLayout.SOUTH);
    add(texting, BorderLayout.NORTH);
  }

  

  public static void loadAmbulanceDisplay(ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceList)
  {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    AmbulanceDisplay newContentPane = new AmbulanceDisplay(PatientList, AmbulanceList);
    newContentPane.setVisible(true);
    frame.setContentPane(newContentPane);
    frame.pack();
    frame.setVisible(true);
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

  public static void main(ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceList)
  {
    javax.swing.SwingUtilities.invokeLater(new Runnable()
    {
      @Override
      public void run()
      {
        loadAmbulanceDisplay(PatientList, AmbulanceList);
      }
    });
  }

 
}
