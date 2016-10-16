using System;
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
    public partial class AmbulanceAdd : Form
    {
        
        public AmbulanceAdd()
        {
            
            InitializeComponent();

        }

        private void AmbulanceIDBox_changed(object sender, System.Windows.Forms.KeyEventArgs e)
        {

            if (e.KeyCode == Keys.Enter)
            {

                string text2 = AmbulanceIDBox.Text;
                Debug.WriteLine(text2 + "LOLOL");
                if (String.IsNullOrEmpty(text2))
                {
                    MessageBox.Show("Please enter an AmbulanceID beginning with 'A'");

                }

                else if (text2 != null && text2[0] == 'A' && text2.Length >= 2)
                {
                    Debug.WriteLine(AmbulanceIDBox.Text + "LOLOL2" + new_amb.AmbulanceID);
                    try
                    {
                        new_amb.AmbulanceID = AmbulanceIDBox.Text;
                    }
                    catch (Exception)
                    {
                        MessageBox.Show("NULL");
                    }

                }
                else
                {
                    MessageBox.Show("Please enter an AmbulanceID beginning with 'A' and at least one digit");
                }
            }

        }
        private void StationBox_changed(object sender, System.Windows.Forms.KeyEventArgs e)
        {

            if (e.KeyCode == Keys.Enter)
            {
               // string text1 = StationBox.Text;
                if (String.IsNullOrEmpty(StationBox.Text))
                {
                    MessageBox.Show("Please enter a Station ");
                }
                else if (StationBox.Text.Length > 0)
                {
                    new_amb.Station = StationBox.Text;
                }
            }

        }



        private void button1_click(object sender, EventArgs e)
        {
            Form3 frm = new Form3();
            frm.Show();
            this.Hide();
        }

        private void button2_click(object sender, EventArgs e)
        {
            int p = Validator();
            if (p == 1)
            {
                save(new_amb);
                Form3 frm = new Form3();
                frm.Show();
                this.Hide();
            }
            else
            {
                InitializeComponent();
            }

          
        }
        private int Validator()
        {
            int i = 0;
            string text = AmbulanceIDBox.Text;
            if (String.IsNullOrEmpty(text) && text.Length > 0)
            {
                MessageBox.Show("Please enter your First Name");
            }
            else if (new_amb.AmbulanceID == null)
            {
                MessageBox.Show("Please enter an Ambulance ID");
            }
            else if (text[0] != 'A')
            {
                
                MessageBox.Show("Please enter an Ambulance ID Beginning with A     " + text[0]);
            }
            else if (text.Length < 2)
            {
                MessageBox.Show("Please enter an Ambulance ID Beginning with A with more 1 Digit");
            }
            else if (text.Length > 4)
            {
                MessageBox.Show("Too many digits in AmbulanceID, Only three are allowed");
            }
            else if (new_amb.Station == null && StationBox.Text != null)
            {
                MessageBox.Show("Please specify a Station");
            }
            else
            {
                i = 1;
            }
            return i;
        }
        private void save(AMBULANCES new_amb)
        {

            using (AMBULANCES_STAFF db = new AMBULANCES_STAFF())
            {
                int i = 0;
                foreach (var p in db.AMBULANCES)
                {
                    if (p.AmbulanceID == new_amb.AmbulanceID)
                    {
                        MessageBox.Show("Ambulance Already Exists. Please Specify a different Ambulance ID");
                        i = 1;
                        Validator();
                    }
                    
                }
                if(i == 0)
                {
                    db.AMBULANCES.Add(new_amb);
                    db.SaveChanges();
                }
                else
                {
                    InitializeComponent();
                }
                
            }
        }
    }
}


