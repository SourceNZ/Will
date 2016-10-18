package Assignment3;
import java.io.*;
import java.util.List;

import javax.swing.JTable;


public class writeCSVFileAmbulance  {
		public static void writeCSVFile(List<Ambulance> AmbulanceList) throws IOException, ClassNotFoundException{
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ambulances-2.csv")));

				StringBuffer bufferHeader = new StringBuffer();
				bufferHeader.append("id,");
				bufferHeader.append("x.Location,");
				bufferHeader.append("y.Location,");
				bufferHeader.append("status,");
				bufferHeader.append("patient");
				writer.write(bufferHeader.toString() + "\n");

				StringBuffer buffer = new StringBuffer();
				for (Ambulance Ambulance: AmbulanceList){
					buffer.append(Ambulance.getID() + ",");
					buffer.append(Ambulance.getX_location() + ",");
					buffer.append(Ambulance.getY_location() + ",");
					buffer.append(Ambulance.getStatus() + ",");
					if(Ambulance.getpatient() == null || Ambulance.getpatient() == "None" || Ambulance.getpatient().equals("")){
						buffer.append(""+ "\n");
					}
					else {
						buffer.append(Ambulance.getpatient() + "\n");
					}
					
				}
				
				
				writer.write(buffer.toString() + "\n");
				
			}finally {
				writer.close();
			}
		}

}
