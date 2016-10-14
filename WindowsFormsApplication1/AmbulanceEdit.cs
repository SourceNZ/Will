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
    public partial class AmbulanceEdit : Form
    {
        private AMBULANCES new_amb;
        public AmbulanceEdit(AMBULANCES new_amb)
        {
            this.new_amb = new_amb;
            InitializeComponent(new_amb);

        }
        
        private void AmbulanceIDBox_changed(object sender, System.Windows.Forms.KeyEventArgs e)
        {

            if (e.KeyCode == Keys.Enter)
            {

                string text = AmbulanceIDBox.Text;
                Debug.WriteLine(text + "LOLOL");
                if (String.IsNullOrEmpty(text))
                {
                    MessageBox.Show("Please enter your Surname");

                }

                else if (text != null)
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
            }

        }
        private void StationBox_changed(object sender, System.Windows.Forms.KeyEventArgs e)
        {

            if (e.KeyCode == Keys.Enter)
            {
                string text = StationBox.Text;
                if (String.IsNullOrEmpty(text))
                {
                    MessageBox.Show("Please enter a 6 Digit Number");
                }
                else if (text.Length > 0)
                {
                    new_amb.Station = text; 
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
            save(new_amb);
            Form3 frm = new Form3();
            frm.Show();
            this.Hide();
        }
        private void save(AMBULANCES new_amb)
        {
            using (AMBULANCES_STAFF db = new AMBULANCES_STAFF())
            {
                foreach (var x in db.AMBULANCES)
                {
                    if (x.AmbulanceID == new_amb.AmbulanceID)
                    {
                        x.Station = new_amb.Station;
                    }

                }

                db.SaveChanges();
            }
        }
    }

}
