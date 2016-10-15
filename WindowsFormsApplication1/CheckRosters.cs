using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    public partial class CheckRosters : Form
    {
        public CheckRosters()
        {
            test();
            
        }

      

        private void BackButton_Click(object sender, EventArgs e)
        {
            test();
            Form1 newform = new Form1();
            newform.Show();
            this.Hide();


        }
        private void test()
        {
           List<string> ambulances = new List<string>();
            
            using(AMBULANCES_STAFF_CONTEXT db = new AMBULANCES_STAFF_CONTEXT())
            {
                foreach (var x in db.AMBULANCE_STAFFS)
                {
                    if(x.Ambulance != null && !(x.Ambulance.Equals("None")))
                    {
                        var matchh = ambulances.FirstOrDefault(stringToCheck => stringToCheck.Contains(x.Ambulance));
                        //  if the ambulance isnt already in the list, add it
                        if (matchh == null)
                        {
                           
                            ambulances.Add(x.Ambulance + " " + x.OfficerID + " ");
                           // int i = ambulances.IndexOf(x.Ambulance);
                           // Debug.WriteLine("TEAPARTY" + i);
                        }
                       // if it is in the list then find where it is and add the persons name to it
                      //     else
                       //   {
                       //     Debug.WriteLine("TEAPARTY");
                       //     int i = ambulances.IndexOf(x.Ambulance);
                      //      ambulances.Insert(ambulances[i].Length, "" + x.OfficerID);

                      //  }
                        var match = ambulances.FirstOrDefault(stringToCheck => stringToCheck.Contains(x.Ambulance));
                        int l = ambulances.IndexOf(match);
                        if (!match.Contains(x.OfficerID.ToString()))
                        {
                            ambulances[l] += x.OfficerID + " ";
                        }
                    }
                }
                
                int o = 0;
                foreach(var x in ambulances)
                {
                    //create a new panel for each ambulance, seperate the two parts, ambulance and crew then add them to their respective boxes
                    Debug.WriteLine(x + "THISSSSSSS " + o);
                    o++;
                }
                InitializeComponent(o, ambulances);
                


            }
        }
    }
}
