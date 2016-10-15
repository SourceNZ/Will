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
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
            frmDGV_Load();
        }


        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            int j = e.ColumnIndex;
            int i = e.RowIndex;
            Debug.WriteLine(i + "HERERERERER" + j);
            using (AMBULANCES_STAFF_CONTEXT db = new AMBULANCES_STAFF_CONTEXT())
            {
                int l = 0;
                var new_staff = new AMBULANCE_STAFF();
                //dummy data
                List<AMBULANCE_STAFF> lstStaff = new List<AMBULANCE_STAFF>();
                foreach (var x in db.AMBULANCE_STAFFS)
                {
                    if(l == i)
                    {
                        var new_staff1 = new AMBULANCE_STAFF();
                        new_staff1.OfficerID = x.OfficerID;
                        new_staff1.FirstName = x.FirstName;
                        new_staff1.Surname = x.Surname;
                        new_staff1.SkillLevel = x.SkillLevel;
                        new_staff1.Ambulance = x.Ambulance;
                        Form4 for4 = new Form4(new_staff1);
                        for4.Show();
                        this.Hide();
                       
                    }
                    this.dataGridView1.Rows.Add(x.OfficerID, x.FirstName + " " + x.Surname, x.SkillLevel, x.Ambulance);
                    lstStaff.Add(x);
                    l++;
                }
                foreach (var x in lstStaff)
                {
                    Debug.WriteLine("ID" + x.OfficerID + x.FirstName + " " + x.Surname + x.SkillLevel + x.Ambulance);

                }

        
            }

        }

        private void button1_Click(object sender, EventArgs e)
        {
            Form1 newform = new Form1();
            newform.Show();
            this.Hide();
            
        }
        private void button2_Click(object sender, EventArgs e)
        {
            AddNewOfficerForm newform = new AddNewOfficerForm();
            newform.Show();
            this.Hide();

        }
        
    }
    
}
