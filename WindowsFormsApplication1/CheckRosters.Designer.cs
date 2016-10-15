using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    partial class CheckRosters
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent(int o, List<string> ambulances)
        {

           
            
            this.label1 = new System.Windows.Forms.Label();
            this.panel1 = new System.Windows.Forms.Panel();
            this.panel1.AutoScroll = true;
            this.AmbulancePanelTextBox1 = new System.Windows.Forms.TextBox();
           
            this.AmbulancePanel1 = new System.Windows.Forms.Panel();

            this.BackButton = new System.Windows.Forms.Button();
            this.panel1.SuspendLayout();
            this.AmbulancePanel1.SuspendLayout();

            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(174, 20);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(166, 25);
            this.label1.TabIndex = 0;
            this.label1.Text = "Check Rosters";
            // 
            // panel1
            // 
            this.panel1.BackColor = System.Drawing.SystemColors.Window;
 
            this.panel1.Location = new System.Drawing.Point(46, 109);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(453, 309);
            this.panel1.TabIndex = 2;

            // 
            // BackButton
            // 
            this.BackButton.Location = new System.Drawing.Point(37, 435);
            this.BackButton.Name = "BackButton";
            this.BackButton.Size = new System.Drawing.Size(119, 64);
            this.BackButton.TabIndex = 3;
            this.BackButton.Text = "Back";
            this.BackButton.UseVisualStyleBackColor = true;
            this.BackButton.Click += new System.EventHandler(this.BackButton_Click);
            int i = 0;
             foreach (var x in ambulances)
            {

                this.AmbulancePanel1 = new System.Windows.Forms.Panel();
                this.AmbulancePanelTextBox1 = new System.Windows.Forms.TextBox();
           
                this.AmbulancePanel1.Name = "AmbulancePanel1";
                this.AmbulancePanel1.Size = new System.Drawing.Size(396, 86);
                if (i == 0)
                {
                    this.AmbulancePanel1.Location = new System.Drawing.Point(27, 20);
                }
                else
                {
                    this.AmbulancePanel1.Location = new System.Drawing.Point(27, 20 + i);
                }
                this.AmbulancePanel1.TabIndex = 2;


                this.AmbulancePanelTextBox1.Location = new System.Drawing.Point(3, 16);
                this.AmbulancePanelTextBox1.Name = "AmbulancePanelTextBox1";
                this.AmbulancePanelTextBox1.ReadOnly = true;
                this.AmbulancePanelTextBox1.Multiline = true;
                this.AmbulancePanelTextBox1.AutoSize = false;
                this.AmbulancePanelTextBox1.ScrollBars = ScrollBars.Horizontal;
                this.AmbulancePanelTextBox1.Size = new System.Drawing.Size(380, 50);
                this.AmbulancePanelTextBox1.Invalidate();
                //this.AmbulancePanelTextBox1.Size = new System.Drawing.Size(100, 10);
                this.AmbulancePanelTextBox1.TabIndex = 0;


                var array = x.Split(' ');
                string firstElem = array.First();
                string restOfArray = string.Join(" ", array.Skip(1));
                Debug.WriteLine(firstElem + "REST: " + restOfArray);
                string[] rest = restOfArray.Split(' ');
                int p = rest.Length;
                Debug.WriteLine(p + "length");
                //At least one crew member must have Intermediate or Advanced skill
                if (p < 3)
                {
                    this.AmbulancePanelTextBox1.BackColor = Color.Red;
                }
                else if(p >= 3 && p < 5)
                {
                    // Debug.WriteLine(rest.Contains("Intermediate") + "here");
                    // Debug.WriteLine(rest.Contains("Advanced") + "here1");  rest.Contains("Intermediate") || rest.Contains("Advanced")
                    int w = 0;
                    //seems to accept anything as long as there are 2 people
                    foreach (string y in rest)
                    {
                        if (y.Contains("Advanced") || y.Contains("Intermediate"))
                        {
                           w = 1;
                        }
                       
                    }
                    if (w != 0 )
                    {
                    
                        this.AmbulancePanelTextBox1.BackColor = Color.Green;
                    }
                    else 
                    {
                       
                        this.AmbulancePanelTextBox1.BackColor = Color.Red;
                    }
                    
                }
                else if (restOfArray.Length == 0)
                {
                    this.AmbulancePanelTextBox1.BackColor =  Color.Red;
                }
                else
                {
                    this.AmbulancePanelTextBox1.BackColor = Color.Red;
                }


                this.AmbulancePanelTextBox1.Text = firstElem + "                    " + restOfArray;
                this.AmbulancePanel1.BackColor = System.Drawing.SystemColors.ActiveBorder;
                this.AmbulancePanel1.Controls.Add(this.AmbulancePanelTextBox1);
                this.panel1.Controls.Add(this.AmbulancePanel1);
                i += 86;

            }

            // 
            // CheckRosters
            // 
            
            this.panel1.Controls.Add(this.AmbulancePanel1);
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(549, 511);
            this.Controls.Add(this.BackButton);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.label1);
       
            this.Name = "CheckRosters";
            this.Text = "CheckRosters";
            this.panel1.ResumeLayout(false);
            this.AmbulancePanel1.ResumeLayout(false);
            this.AmbulancePanel1.PerformLayout();
     
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Panel AmbulancePanel1;
        private System.Windows.Forms.TextBox AmbulancePanelTextBox1;
  
        private System.Windows.Forms.Button BackButton;
    }
}
/*

*/
