package will;
import java.io.*;
import java.util.List;

import javax.swing.JTable;


public class writeCSVFile   {
		public static void writeCSVFile( List<Patient> PatientList) throws IOException, ClassNotFoundException{
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("patients.csv")));

				StringBuffer bufferHeader = new StringBuffer();
				
				bufferHeader.append("id,");
				bufferHeader.append("x.Location,");
				bufferHeader.append("y.Location,");
				bufferHeader.append("status,");
				bufferHeader.append("ambulance");
				writer.write(bufferHeader.toString() + "\n");
				
				StringBuffer buffer = new StringBuffer();
				for (Patient patient: PatientList){
					buffer.append(patient.getID() + ",");
					buffer.append(patient.getX_location() + ",");
					buffer.append(patient.getY_location() + ",");

					buffer.append("\"" + patient.getStatus() + "\""+ ",");
					if(patient.getambulance() == null || patient.getambulance() == "None" || patient.getambulance().equals(" ") ){
						buffer.append(" "+ "\n");
					}
					else {
						buffer.append(patient.getambulance() + "\n");
					}
					
				}
				writer.write(buffer.toString() + "\n");
				
			}finally {
				writer.close();
			}
		}

}
