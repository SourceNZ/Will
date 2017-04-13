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
    public partial class Form4 : Form
    {
        private AMBULANCE_STAFF new_staff1;


        public Form4(AMBULANCE_STAFF new_staff1)
        {
            this.new_staff1 = new_staff1;
            InitializeComponent(new_staff1);

        }
        private void FirstnamesBox_changed(object sender, System.Windows.Forms.KeyEventArgs e)
        {

            if (e.KeyCode == Keys.Enter)
            {
                string text = FirstnamesBox.Text;
                if (String.IsNullOrEmpty(text))
                {
                    MessageBox.Show("Please enter your First Name");

                }
                else if (text != null)
                {
                    Debug.WriteLine(FirstnamesBox.Text + "LOLOL2" + new_staff1.FirstName);
                    try
                    {
                        new_staff1.FirstName = FirstnamesBox.Text;
                    }
                    catch (Exception)
                    {
                        MessageBox.Show("NULL");
                    }

                }
            }

        }
        private void SurnamesBox_changed(object sender, System.Windows.Forms.KeyEventArgs e)
        {

            if (e.KeyCode == Keys.Enter)
            {

                string text = SurnameBox.Text;
                Debug.WriteLine(text + "LOLOL");
                if (String.IsNullOrEmpty(text))
                {
                    MessageBox.Show("Please enter your Surname");

                }


                else if (text != null)
                {
                    Debug.WriteLine(SurnameBox.Text + "LOLOL2" + new_staff1.Surname);
                    try
                    {
                        new_staff1.Surname = SurnameBox.Text;
                    }
                    catch (Exception)
                    {
                        MessageBox.Show("NULL");
                    }
                    
                }
            }

        }
        private void OfficerIDBox_changed(object sender, System.Windows.Forms.KeyEventArgs e)
        {

            if (e.KeyCode == Keys.Enter)
            {
                string text = OfficerIDBox.Text;
                if (String.IsNullOrEmpty(text))
                {
                    MessageBox.Show("Please enter a 6 Digit Number");
                }
                else if(text.Length == 6)
                {
                    int x = 0;
                    try
                    {
                        x = Convert.ToInt32(OfficerIDBox.Text);
                    }
                    catch (Exception)
                    {
                        MessageBox.Show("Please enter a 6 Digit Number");
                    }
                    new_staff1.OfficerID = x;
                }
            }

        }
        private void SkillBox_changed(object sender, EventArgs e)
        {



            if (SkillLevelBox.SelectedIndex > -1)
            {
                new_staff1.SkillLevel = SkillLevelBox.SelectedItem.ToString();
            }

        }
        private void AmbulanceBox_changed(object sender, EventArgs e)
        {

            if (AmbulanceBox.SelectedIndex > -1)
            {
                new_staff1.Ambulance = AmbulanceBox.SelectedItem.ToString();
            }

        }
        



        private void button1_click(object sender, EventArgs e)
        {
            Form2 frm = new Form2();
            frm.Show();
            this.Hide();
        }

        private void button2_click(object sender, EventArgs e)
        {
            save(new_staff1);
            Form2 frm = new Form2();
            frm.Show();
            this.Hide();
        }
        private void save(AMBULANCE_STAFF new_staff1)
        {
            using (AMBULANCES_STAFF_CONTEXT db = new AMBULANCES_STAFF_CONTEXT())
            {

                foreach (var x in db.AMBULANCE_STAFFS)
                {
                    if (x.OfficerID == new_staff1.OfficerID)
                    {
                        x.FirstName = new_staff1.FirstName;
                        x.Surname = new_staff1.Surname;
                        x.Ambulance = new_staff1.Ambulance;
                        x.SkillLevel = new_staff1.SkillLevel;

                    }

                }
                
                db.SaveChanges();
            }
        }
    }
}
