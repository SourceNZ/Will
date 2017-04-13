package will;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class PatientDisplay extends JPanel 
{
  static JFrame frame = new JFrame("Patient Information");
  public static JTable table;
  static File DataFile = new File("patients.csv");
  public static MyModel NewModel;
  ListSelectionModel listSelectionModel;
  private ArrayList<Patient> PatientList;
  private ArrayList<Ambulance> AmbulanceList;
  
  public PatientDisplay(ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceList)
  {
    super(new BorderLayout(3, 3));
    this.PatientList = PatientList;
    this.AmbulanceList = AmbulanceList;
    this.table = new JTable(new MyModel());
    this.table.setPreferredScrollableViewportSize(new Dimension(700, 300));
    this.table.setFillsViewportHeight(true);
    JPanel ButtonOpen = new JPanel(new FlowLayout(FlowLayout.CENTER));
    add(ButtonOpen, BorderLayout.NORTH);
    JScrollPane scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);
    setBorder(new EmptyBorder(5, 5, 5, 5));
    NewModel = new MyModel();
    table.setModel(NewModel);
    AmbulanceTrackerApp.CSVFile R = new AmbulanceTrackerApp.CSVFile();
    ArrayList<String[]> Rs4 = R.ReadCSVfile(DataFile);
    NewModel.AddCSVData(Rs4);
    NewModel.fireTableDataChanged();    
    listSelectionModel = table.getSelectionModel();
    listSelectionModel.addListSelectionListener(new PatientTableListener( PatientList, AmbulanceList));


    JButton BackButton = new JButton("Back");
    BackButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent ex)
      {
        AmbulanceTrackerApp.main(null);
        frame.setVisible(false);
      }
    });
    JButton NewButton = new JButton("Add New");
    NewButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        AddEditPatient.main(PatientList,AmbulanceList);
        frame.setVisible(false);
//      AmbulanceTrackerApp.CSVFile R1 = new AmbulanceTrackerApp.CSVFile();
//		File DataFile1 = new File("patients.csv");
//		ArrayList<String[]> Rs3 = R1.ReadCSVfile(DataFile1);
//		NewModel.AddCSVData(Rs3);
		NewModel.fireTableDataChanged();

      }
    });
    JPanel tests = new JPanel(new BorderLayout(3,3));
    tests.add(NewButton, BorderLayout.EAST);
    tests.add(BackButton, BorderLayout.WEST);
    add(tests, BorderLayout.SOUTH);
    //add(NewButton, BorderLayout.EAST);
    //add(BackButton, BorderLayout.SOUTH);

  }

  

  public static void loadPatientDisplay(ArrayList<Patient> PatientList,ArrayList<Ambulance> AmbulanceList)
  {
	
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    PatientDisplay newContentPane = new PatientDisplay(PatientList, AmbulanceList);
    frame.setContentPane(newContentPane);
    
   // AmbulanceTrackerApp.CSVFile R = new AmbulanceTrackerApp.CSVFile();
  //  ArrayList<String[]> Rs4 = R.ReadCSVfile(DataFile);

    frame.pack();
    frame.setVisible(true);
  }

  
  public class MyModel  extends AbstractTableModel  { // implements TableModelListener
	    public final String[] columnNames = { "id","Location", "status", "Ambulance" };
	    public ArrayList<String[]> Data = new ArrayList<String[]>();
	    public void AddCSVData(ArrayList<String[]> DataIn)
	    {
	      this.Data = DataIn;
	      this.fireTableDataChanged();
	    }

	    public String[] getRowAt(int row)
	    {
	      return Data.get(row);
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


  public static void main(ArrayList<Patient> PatientList,ArrayList<Ambulance> AmbulanceList)
  {
	  
    javax.swing.SwingUtilities.invokeLater(new Runnable()
    {
      @Override
      public void run()
      {
        loadPatientDisplay(PatientList, AmbulanceList);
      }
    });
  }


}
