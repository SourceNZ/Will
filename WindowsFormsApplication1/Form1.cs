using System;
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
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            Form3 frm = new Form3();
            frm.Show();
            this.Hide();

            //Ambulances button
        }


        private void button1_Click(object sender, EventArgs e)
        {
            Form2 frm = new Form2();
            frm.Show();
            this.Hide();
            //officers button
        }

        private void button4_Click(object sender, EventArgs e)
        {
            this.Close();
            if(System.Windows.Forms.Application.MessageLoop)
             {
                // Use this since we are a WinForms app
                System.Windows.Forms.Application.Exit();
             }
            else
             {
                // Use this since we are a console app
                System.Environment.Exit(1);
             }
        }

        private void RosterButton_Click(object sender, EventArgs e)
        {
            //Ambulances button
        }

       
    }
}
