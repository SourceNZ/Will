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
    public partial class Form3 : Form
    {
        List<AMBULANCES> lstStaff = new List<AMBULANCES>();
        public Form3()
        {
            InitializeComponent();
            frmDGV_Load();
        }

        private void BackButton_Click(object sender, EventArgs e)
        {
            Form1 newform = new Form1();
            newform.Show();
            this.Hide();
        }
        //add add new button that essentially duplicates ambulance edit
        private void SaveButton_Click(object sender, EventArgs e)
        {
            AmbulanceAdd newform = new AmbulanceAdd();
            newform.Show();
            this.Hide();
        }
        
    

        private void dataGridView2_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0 && e.RowIndex < lstStaff.Count)
            {
                int j = e.ColumnIndex;
                int i = e.RowIndex;

                using (AMBULANCES_STAFF db = new AMBULANCES_STAFF())
                {
                    int l = 0;

                    //dummy data
                    List<AMBULANCES> lstStaff = new List<AMBULANCES>();
                    foreach (var x in db.AMBULANCES)
                    {
                        if (l == i)
                        {
                            var new_amb = new AMBULANCES();
                            new_amb.AmbulanceID = x.AmbulanceID;
                            new_amb.Station = x.Station;
                            AmbulanceEdit newfrm = new AmbulanceEdit(new_amb);
                            newfrm.Show();
                            this.Hide();

                        }
                        this.dataGridView2.Rows.Add(x.AmbulanceID + " " + x.Station);
                        lstStaff.Add(x);
                        l++;
                    }


                    //use binding source to hold dummy data
                    // BindingSource binding = new BindingSource();
                    // binding.DataSource = new_staff;

                    //bind datagridview to binding source
                    //dataGridView1.DataSource = binding;
                }
            }

        }
    }
}
