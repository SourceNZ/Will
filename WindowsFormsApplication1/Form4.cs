﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    public partial class Form4 : Form
    { 
        public Form4(AMBULANCE_STAFF new_staff1)
        {
            InitializeComponent(new_staff1);

        }

        private void button1_click(object sender, EventArgs e)
        {
            Form2 frm = new Form2();
            frm.Show();
            this.Hide();
        }

        private void button2_click(object sender, EventArgs e)
        {

        }
    }
}
