package will;

import java.util.ArrayList;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AmbulanceTableListener implements ListSelectionListener {
	private ArrayList<Patient> PatientList;
	private ArrayList<Ambulance> AmbulanceList;
	
		public AmbulanceTableListener(ArrayList<Patient> PatientList, ArrayList<Ambulance> AmbulanceList) {
			this.PatientList = PatientList;
			this.AmbulanceList = AmbulanceList;
		}
	
        public void valueChanged(ListSelectionEvent e) { 
        	if(!e.getValueIsAdjusting() && !((ListSelectionModel)e.getSource()).isSelectionEmpty()){
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            int[] row = new int[1];
            int firstIndex = e.getFirstIndex();
            int lastIndex = e.getLastIndex();
            row[0] = firstIndex;
            boolean isAdjusting = e.getValueIsAdjusting(); 
            //AddEditPatient.main(null);
            //ZAC not sure how to pass the lists here
            EditAmbulance.main(row, PatientList, AmbulanceList);
            AmbulanceDisplay.frame.setVisible(false);
           
 
            if (lsm.isSelectionEmpty()) {
                System.out.println(" <none>");
            } else {
            	 System.out.println("\n");
                
            }
            System.out.println("\n");
        	}
        }
    }


