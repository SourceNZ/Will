package will;

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
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class AmbulanceDisplay extends JPanel implements TableModelListener
{
 private ArrayList<Patient> PatientList;
  private ArrayList<Ambulance> AmbulanceList;
  static JFrame frame = new JFrame("Ambulance Information");
  public static JTable table;
  static MyModel NewModel;
  static AmbulanceTrackerApp.CSVFile R1 = new AmbulanceTrackerApp.CSVFile();
  ListSelectionModel listSelectionModel;
  static File DataFile1 = new File("ambulances.csv");


  public AmbulanceDisplay(ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceList)
  {
    super(new BorderLayout(3, 3));
    this.PatientList = PatientList;
    this.AmbulanceList = AmbulanceList;
    this.table = new JTable(new MyModel());
    this.table.setPreferredScrollableViewportSize(new Dimension(700, 300));
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
    listSelectionModel = table.getSelectionModel();
    listSelectionModel.addListSelectionListener(new AmbulanceTableListener(PatientList, AmbulanceList));// ZAC NEED TO SOMEHOW PASS PATIENT LIST TO THIS
    table.setSelectionModel(listSelectionModel);

    
    JTextField newValue = new JTextField(" "); // Text field for users to enter data
    newValue.setToolTipText("Enter Here:");
    JLabel Value = new JLabel("Enter Here:", JLabel.CENTER);
    JButton BackButton = new JButton("Back");
    BackButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent ex)
      {
        AmbulanceTrackerApp.main(null);
        AmbulancePanel.setVisible(false);
        frame.setVisible(false);
        // AddEditPatient.main(null);
      }
    });
    JButton NewButton = new JButton("Add New");
    NewButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
    	  //ZAC
        AddEditAmbulance.main(PatientList,  AmbulanceList);
        frame.setVisible(false);
        // takes it to the add new screen in AddEditPatient.java
      }
    });
    JPanel tests = new JPanel(new BorderLayout(3,3));
    tests.add(NewButton, BorderLayout.EAST);
    tests.add(BackButton, BorderLayout.WEST);
    add(tests, BorderLayout.SOUTH);



  }

  // Method for reading CSV file
  

  public static void loadAmbulanceDisplay(ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceList)
  {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    AmbulanceDisplay newContentPane = new AmbulanceDisplay(PatientList, AmbulanceList);
    newContentPane.setVisible(true);
    frame.setContentPane(newContentPane);


    frame.pack();
    frame.setVisible(true);
  }
  private void addTableListener() {
      NewModel.addTableModelListener(new TableModelListener() {

          @Override
          public void tableChanged(TableModelEvent tme) {
              if (tme.getType() == TableModelEvent.UPDATE) {
                  NewModel.fireTableDataChanged();
              }
          }
      });
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

  @Override
  public void tableChanged(TableModelEvent e)
  {
    // TODO Auto-generated method stub

  }
}
