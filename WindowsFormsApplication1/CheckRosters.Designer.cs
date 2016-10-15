using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;

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
            this.AmbulancePanelTextBox1 = new System.Windows.Forms.MaskedTextBox();
            this.AmbulancePanelTextBox2 = new System.Windows.Forms.MaskedTextBox();
            this.AmbulancePanel1 = new System.Windows.Forms.Panel();
            this.AmbulancePanel2TextBox1 = new System.Windows.Forms.MaskedTextBox();
            this.AmbulancePanel2TextBox2 = new System.Windows.Forms.MaskedTextBox();
            this.AmbulancePanel2 = new System.Windows.Forms.Panel();
            this.AmbulancePane3lTextBox1 = new System.Windows.Forms.MaskedTextBox();
            this.AmbulancePanel3TextBox2 = new System.Windows.Forms.MaskedTextBox();
            this.AmbulancePanel3 = new System.Windows.Forms.Panel();
            this.BackButton = new System.Windows.Forms.Button();
            this.panel1.SuspendLayout();
            this.AmbulancePanel1.SuspendLayout();
            this.AmbulancePanel2.SuspendLayout();
            this.AmbulancePanel3.SuspendLayout();
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
            this.panel1.Controls.Add(this.AmbulancePanel3);
            this.panel1.Controls.Add(this.AmbulancePanel2);
            this.panel1.Controls.Add(this.AmbulancePanel1);
            this.panel1.Location = new System.Drawing.Point(46, 109);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(453, 309);
            this.panel1.TabIndex = 2;



            this.AmbulancePanelTextBox1.Location = new System.Drawing.Point(3, 16);
            this.AmbulancePanelTextBox1.Name = "AmbulancePanelTextBox1";
            this.AmbulancePanelTextBox1.ReadOnly = true;
            this.AmbulancePanelTextBox1.Size = new System.Drawing.Size(100, 20);
            this.AmbulancePanelTextBox1.TabIndex = 0;

            this.AmbulancePanelTextBox2.Location = new System.Drawing.Point(137, 16);
            this.AmbulancePanelTextBox2.Name = "AmbulancePanelTextBox2";
            this.AmbulancePanelTextBox2.ReadOnly = true;
            this.AmbulancePanelTextBox2.Size = new System.Drawing.Size(234, 20);
            this.AmbulancePanelTextBox2.TabIndex = 1;

            this.AmbulancePanel1.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.AmbulancePanel1.Controls.Add(this.AmbulancePanelTextBox1);
            this.AmbulancePanel1.Controls.Add(this.AmbulancePanelTextBox2);
            this.AmbulancePanelTextBox1 = new System.Windows.Forms.MaskedTextBox();
            this.AmbulancePanelTextBox2 = new System.Windows.Forms.MaskedTextBox();
            this.AmbulancePanel1.Name = "AmbulancePanel1";
            this.AmbulancePanel1.Size = new System.Drawing.Size(396, 86);
            this.AmbulancePanel1.Location = new System.Drawing.Point(27,20);
            this.AmbulancePanel1.TabIndex = 2;


            this.AmbulancePanel2.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.AmbulancePanel2.Controls.Add(this.AmbulancePanel2TextBox1);
            this.AmbulancePanel2.Controls.Add(this.AmbulancePanel2TextBox2);
            this.AmbulancePanel2.Location = new System.Drawing.Point(27, 112);
            this.AmbulancePanel2.Name = "AmbulancePanel2";
            this.AmbulancePanel2.Size = new System.Drawing.Size(396, 86);
            this.AmbulancePanel2.TabIndex = 3;

      
            // 
            // AmbulancePanel2TextBox1
            // 
            this.AmbulancePanel2TextBox1.Location = new System.Drawing.Point(3, 16);
            this.AmbulancePanel2TextBox1.Name = "AmbulancePanel2TextBox1";
            this.AmbulancePanel2TextBox1.ReadOnly = true;
            this.AmbulancePanel2TextBox1.Size = new System.Drawing.Size(100, 20);
            this.AmbulancePanel2TextBox1.TabIndex = 0;
            // 
            // AmbulancePanel2TextBox2
            // 
            this.AmbulancePanel2TextBox2.Location = new System.Drawing.Point(137, 16);
            this.AmbulancePanel2TextBox2.Name = "AmbulancePanel2TextBox2";
            this.AmbulancePanel2TextBox2.ReadOnly = true;
            this.AmbulancePanel2TextBox2.Size = new System.Drawing.Size(234, 20);
            this.AmbulancePanel2TextBox2.TabIndex = 1;
            // 
            // AmbulancePanel2
            // 
            this.AmbulancePanel2.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.AmbulancePanel2.Controls.Add(this.AmbulancePanel2TextBox1);
            this.AmbulancePanel2.Controls.Add(this.AmbulancePanel2TextBox2);
            this.AmbulancePanel2.Location = new System.Drawing.Point(27, 112);
            this.AmbulancePanel2.Name = "AmbulancePanel2";
            this.AmbulancePanel2.Size = new System.Drawing.Size(396, 86);
            this.AmbulancePanel2.TabIndex = 3;
            // 
            // AmbulancePane3lTextBox1
            // 
            this.AmbulancePane3lTextBox1.Location = new System.Drawing.Point(3, 16);
            this.AmbulancePane3lTextBox1.Name = "AmbulancePane3lTextBox1";
            this.AmbulancePane3lTextBox1.ReadOnly = true;
            this.AmbulancePane3lTextBox1.Size = new System.Drawing.Size(100, 20);
            this.AmbulancePane3lTextBox1.TabIndex = 0;
            // 
            // AmbulancePanel3TextBox2
            // 
            this.AmbulancePanel3TextBox2.Location = new System.Drawing.Point(137, 16);
            this.AmbulancePanel3TextBox2.Name = "AmbulancePanel3TextBox2";
            this.AmbulancePanel3TextBox2.ReadOnly = true;
            this.AmbulancePanel3TextBox2.Size = new System.Drawing.Size(234, 20);
            this.AmbulancePanel3TextBox2.TabIndex = 1;
            // 
            // AmbulancePanel3
            // 
            this.AmbulancePanel3.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.AmbulancePanel3.Controls.Add(this.AmbulancePane3lTextBox1);
            this.AmbulancePanel3.Controls.Add(this.AmbulancePanel3TextBox2);
            this.AmbulancePanel3.Location = new System.Drawing.Point(27, 204);
            this.AmbulancePanel3.Name = "AmbulancePanel3";
            this.AmbulancePanel3.Size = new System.Drawing.Size(396, 86);
            this.AmbulancePanel3.TabIndex = 3;
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
            
           
            // 
            // CheckRosters
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(549, 511);
            this.Controls.Add(this.BackButton);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.label1);
           // this.Controls.Add(this.AmbulancePanel1);
            this.Name = "CheckRosters";
            this.Text = "CheckRosters";
            this.panel1.ResumeLayout(false);
            this.AmbulancePanel1.ResumeLayout(false);
            this.AmbulancePanel1.PerformLayout();
            this.AmbulancePanel2.ResumeLayout(false);
            this.AmbulancePanel2.PerformLayout();
            this.AmbulancePanel3.ResumeLayout(false);
            this.AmbulancePanel3.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Panel AmbulancePanel3;
        private System.Windows.Forms.MaskedTextBox AmbulancePane3lTextBox1;
        private System.Windows.Forms.MaskedTextBox AmbulancePanel3TextBox2;
        private System.Windows.Forms.Panel AmbulancePanel2;
        private System.Windows.Forms.MaskedTextBox AmbulancePanel2TextBox1;
        private System.Windows.Forms.MaskedTextBox AmbulancePanel2TextBox2;
        private System.Windows.Forms.Panel AmbulancePanel1;
        private System.Windows.Forms.MaskedTextBox AmbulancePanelTextBox1;
        private System.Windows.Forms.MaskedTextBox AmbulancePanelTextBox2;
        private System.Windows.Forms.Button BackButton;
    }
}
/*
 * foreach (var x in ambulances)
            {

                this.AmbulancePanel1 = new System.Windows.Forms.Panel();
                this.AmbulancePanelTextBox1 = new System.Windows.Forms.MaskedTextBox();
                this.AmbulancePanelTextBox2 = new System.Windows.Forms.MaskedTextBox();
                this.AmbulancePanel1.Name = "AmbulancePanel1";
                this.AmbulancePanel1.Size = new System.Drawing.Size(396, 86);
                this.AmbulancePanel1.Location = new System.Drawing.Point(100, 100);
                this.AmbulancePanel1.TabIndex = 2;


                this.AmbulancePanelTextBox1.Location = new System.Drawing.Point(3, 16);
                this.AmbulancePanelTextBox1.Name = "AmbulancePanelTextBox1";
                this.AmbulancePanelTextBox1.ReadOnly = true;
                this.AmbulancePanelTextBox1.Size = new System.Drawing.Size(100, 20);
                this.AmbulancePanelTextBox1.TabIndex = 0;
                this.AmbulancePanelTextBox2.Location = new System.Drawing.Point(137, 16);
                this.AmbulancePanelTextBox2.Name = "AmbulancePanelTextBox2";
                this.AmbulancePanelTextBox2.ReadOnly = true;
                this.AmbulancePanelTextBox2.Size = new System.Drawing.Size(234, 20);
                this.AmbulancePanelTextBox2.TabIndex = 1;

                var array = x.Split(' ');
                string firstElem = array.First();
                string restOfArray = string.Join(" ", array.Skip(1));
                Debug.WriteLine(firstElem + "REST: " + restOfArray);
                this.AmbulancePanelTextBox1.Text = firstElem;
                this.AmbulancePanelTextBox1.Text = restOfArray;

                this.AmbulancePanel1.BackColor = System.Drawing.SystemColors.ActiveBorder;
                this.AmbulancePanel1.Controls.Add(this.AmbulancePanelTextBox1);
                this.AmbulancePanel1.Controls.Add(this.AmbulancePanelTextBox2);
                this.panel1.Controls.Add(this.AmbulancePanel1);
                this.AmbulancePanel1.BringToFront();

            }
*/