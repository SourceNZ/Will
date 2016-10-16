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
                    //need to add ambulances with no staff.
                    if(x.Ambulance != null && !(x.Ambulance.Equals("None")))
                    {
                        var matchh = ambulances.FirstOrDefault(stringToCheck => stringToCheck.Contains(x.Ambulance + " "));
                        //  if the ambulance isnt already in the list, add it
                        if (matchh == null)
                        {
                           
                            ambulances.Add(x.Ambulance + " " + x.OfficerID + "(" + x.SkillLevel + ")" + " ");
                      
                        }
              
                        var match = ambulances.FirstOrDefault(stringToCheck => stringToCheck.Contains(x.Ambulance + " "));
                        int l = ambulances.IndexOf(match);
                        if (!match.Contains(x.OfficerID.ToString()))
                        {
                            ambulances[l] +=  x.OfficerID + "(" + x.SkillLevel + ")" + " ";
                        }

                    }
                }
                using (AMBULANCES_STAFF db1 = new AMBULANCES_STAFF())
                {
                    foreach(var y in db1.AMBULANCES)
                    {
                        var match1 = ambulances.FirstOrDefault(stringToCheck => stringToCheck.Contains(y.AmbulanceID));
                        int l = ambulances.IndexOf(match1);
                        if (match1 == null)
                        {
                                ambulances.Add(y.AmbulanceID);
                        }
                        Debug.WriteLine(y.AmbulanceID + "HEYY ");
                    }
                }

                    int o = 0;

                InitializeComponent(o, ambulances);


            }
        }
    }
}
