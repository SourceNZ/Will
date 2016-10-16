using System;
using System.Diagnostics;
using System.Linq;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    partial class AmbulanceEdit
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
        private void InitializeComponent(AMBULANCES new_amb)
        {

            this.AmbulanceOfficerLabel = new System.Windows.Forms.Label();
            this.OfficerNameLAbel = new System.Windows.Forms.Label();
            this.FirstnamesLabel = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.CrewLabel = new System.Windows.Forms.Label();
            this.AmbulanceIDBox = new System.Windows.Forms.TextBox();
            this.StationBox = new System.Windows.Forms.TextBox();
            this.CrewBox = new System.Windows.Forms.TextBox();
            


            this.Back = new System.Windows.Forms.Button();
            this.AddNew = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // AmbulanceOfficerLabel
            // 
            this.AmbulanceOfficerLabel.AutoSize = true;
            this.AmbulanceOfficerLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.AmbulanceOfficerLabel.Location = new System.Drawing.Point(62, 9);
            this.AmbulanceOfficerLabel.Name = "AmbulanceOfficerLabel";
            this.AmbulanceOfficerLabel.Size = new System.Drawing.Size(212, 25);
            this.AmbulanceOfficerLabel.TabIndex = 0;
            this.AmbulanceOfficerLabel.Text = "Ambulance:";
            // 
            // OfficerNameLAbel
            // 
            this.OfficerNameLAbel.AutoSize = true;
            this.OfficerNameLAbel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.OfficerNameLAbel.Location = new System.Drawing.Point(280, 16);
            this.OfficerNameLAbel.Name = "OfficerNameLAbel";
            this.OfficerNameLAbel.Size = new System.Drawing.Size(180, 16);
            this.OfficerNameLAbel.TabIndex = 1;
            this.OfficerNameLAbel.Text = new_amb.AmbulanceID;
            // 
            // FirstnamesLabel
            // 
            this.FirstnamesLabel.AutoSize = true;
            this.FirstnamesLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.FirstnamesLabel.Location = new System.Drawing.Point(35, 64);
            this.FirstnamesLabel.Name = "FirstnamesLabel";
            this.FirstnamesLabel.Size = new System.Drawing.Size(99, 16);
            this.FirstnamesLabel.TabIndex = 2;
            this.FirstnamesLabel.Text = "AmbulanceID:";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(36, 105);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(75, 16);
            this.label1.TabIndex = 3;
            this.label1.Text = "Station:";
            // 
            // crew label
            // 
            this.CrewLabel.AutoSize = true;
            this.CrewLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.CrewLabel.Location = new System.Drawing.Point(36, 190);
            this.CrewLabel.Name = "CrewLabel";
            this.CrewLabel.Size = new System.Drawing.Size(75, 16);
            this.CrewLabel.TabIndex = 3;
            this.CrewLabel.Text = "Crew:";




            // 
            // AmbulanceIDBox
            // 
            this.AmbulanceIDBox.Location = new System.Drawing.Point(167, 64);
            this.AmbulanceIDBox.Name = "FirstnamesBox";
            this.AmbulanceIDBox.Size = new System.Drawing.Size(220, 20);
            this.AmbulanceIDBox.TabIndex = 7;
            this.AmbulanceIDBox.Text = new_amb.AmbulanceID;
            this.AmbulanceIDBox.KeyDown += new KeyEventHandler(AmbulanceIDBox_changed);

            // 
            // SurnameBox
            // 
            this.StationBox.Location = new System.Drawing.Point(167, 105);
            this.StationBox.Name = "SurnameBox";
            this.StationBox.Size = new System.Drawing.Size(220, 20);
            this.StationBox.TabIndex = 8;
            this.StationBox.Text = new_amb.Station;
            
            this.StationBox.KeyDown += new KeyEventHandler(StationBox_changed);

            // 
            // CrewBox
            // 
            using (AMBULANCES_STAFF_CONTEXT db1 = new AMBULANCES_STAFF_CONTEXT())
            {
                string crew = "";
                foreach (var x in db1.AMBULANCE_STAFFS)
                {
                    if (x.Ambulance == new_amb.AmbulanceID)
                    {
                        crew += x.FirstName  +" " + x.Surname + " ";
                    }

                }
                this.CrewBox.Text = crew;
            }
            this.CrewBox.Location = new System.Drawing.Point(167, 190);
            this.CrewBox.Name = "SurnameBox";
            this.CrewBox.Size = new System.Drawing.Size(220, 50);
            this.CrewBox.TabIndex = 8;
        
            this.CrewBox.ReadOnly = true;

           
         
            // 
            // Back
            // 
            this.Back.Location = new System.Drawing.Point(31, 327);
            this.Back.Name = "Back";
            this.Back.Size = new System.Drawing.Size(133, 72);
            this.Back.TabIndex = 2;
            this.Back.Text = "Back";
            this.Back.UseVisualStyleBackColor = true;
            this.Back.Click += new System.EventHandler(this.button1_click);
            // 
            // AddNew
            // 
            this.AddNew.Location = new System.Drawing.Point(355, 327);
            this.AddNew.Name = "AddNew";
            this.AddNew.Size = new System.Drawing.Size(129, 72);
            this.AddNew.TabIndex = 3;
            this.AddNew.Text = "Save";
            this.AddNew.UseVisualStyleBackColor = true;
            this.AddNew.Click += new System.EventHandler(this.button2_click);


            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(485, 413);
  
     
            this.Controls.Add(this.label1);
            this.Controls.Add(this.FirstnamesLabel);
            this.Controls.Add(this.OfficerNameLAbel);
            this.Controls.Add(this.AmbulanceOfficerLabel);
            this.Controls.Add(this.AddNew);
            this.Controls.Add(this.Back);
            this.Controls.Add(this.StationBox);
            this.Controls.Add(this.CrewBox);
            this.Controls.Add(this.AmbulanceIDBox);
            this.Controls.Add(this.CrewLabel);
            
            this.Name = "AmbulanceEdit";
            this.Text = "AmbulanceEdit";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label AmbulanceOfficerLabel;
        private System.Windows.Forms.Label OfficerNameLAbel;
        private System.Windows.Forms.Label FirstnamesLabel;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label CrewLabel;

        private System.Windows.Forms.Button Back;
        private System.Windows.Forms.Button AddNew;
        private System.Windows.Forms.TextBox StationBox;
        private System.Windows.Forms.TextBox CrewBox;
        private System.Windows.Forms.TextBox AmbulanceIDBox;
        
            
       

    }
}