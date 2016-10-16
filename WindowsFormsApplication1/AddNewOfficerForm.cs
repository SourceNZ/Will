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
    public partial class AddNewOfficerForm : Form
    {

        public AddNewOfficerForm()
        {
            
            InitializeComponent();

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
                else if (text.Length == 6)
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
                else
                {
                    MessageBox.Show("Please enter a 6 Digit Number");
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
            int p = Validator();
            if(p == 1)
            {
                save(new_staff1);
                Form2 frm = new Form2();
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
            string text = FirstnamesBox.Text;
            if (String.IsNullOrEmpty(text) && text.Length > 0)
            {
                MessageBox.Show("Please enter your First Name");
            }
            else if (new_staff1.Surname == null)
            {
                MessageBox.Show("Please enter your Surname");
            }
            else if (new_staff1.OfficerID == Convert.ToInt32("0") && (new_staff1.OfficerID.ToString()).Length == 6)
            {
                MessageBox.Show("Please enter a 6 Digit Number");
            }
            else if (new_staff1.Ambulance == null)
            {
                MessageBox.Show("Please pick none or any ambulance");
            }
            else if (new_staff1.SkillLevel == null)
            {
                MessageBox.Show("Please pick a skill level");
            }
            else
            {
                i = 1;
            }
            return i;
        }
        private void save(AMBULANCE_STAFF new_staff1)
        {
            using (AMBULANCES_STAFF_CONTEXT db = new AMBULANCES_STAFF_CONTEXT())
            {
                int i = 0;
                foreach (var p in db.AMBULANCE_STAFFS)
                { 
                    if (p.OfficerID == new_staff1.OfficerID)
                    {
                        MessageBox.Show("Officer Already Exists. Please Specify a different Officer ID");
                        i = 1;
                        Validator();
                    }

                }
                if (i == 0)
                {
                    db.AMBULANCE_STAFFS.Add(new_staff1);
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
