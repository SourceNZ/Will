using System.Diagnostics;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    partial class Form4
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
        private void InitializeComponent(AMBULANCE_STAFF new_staff1)
        {

            this.AmbulanceOfficerLabel = new System.Windows.Forms.Label();
            this.OfficerNameLAbel = new System.Windows.Forms.Label();
            this.FirstnamesLabel = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.FirstnamesBox = new System.Windows.Forms.TextBox();
            this.SurnameBox = new System.Windows.Forms.TextBox();
            this.OfficerIDBox = new System.Windows.Forms.TextBox();
            this.SkillLevelBox = new System.Windows.Forms.ComboBox();
            this.AmbulanceBox = new System.Windows.Forms.ComboBox();
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
            this.AmbulanceOfficerLabel.Text = "Ambulance Officer:";
            // 
            // OfficerNameLAbel
            // 
            this.OfficerNameLAbel.AutoSize = true;
            this.OfficerNameLAbel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.OfficerNameLAbel.Location = new System.Drawing.Point(280, 16);
            this.OfficerNameLAbel.Name = "OfficerNameLAbel";
            this.OfficerNameLAbel.Size = new System.Drawing.Size(180, 16);
            this.OfficerNameLAbel.TabIndex = 1;
            this.OfficerNameLAbel.Text = new_staff1.FirstName + " " + new_staff1.Surname;
            // 
            // FirstnamesLabel
            // 
            this.FirstnamesLabel.AutoSize = true;
            this.FirstnamesLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.FirstnamesLabel.Location = new System.Drawing.Point(35, 64);
            this.FirstnamesLabel.Name = "FirstnamesLabel";
            this.FirstnamesLabel.Size = new System.Drawing.Size(99, 16);
            this.FirstnamesLabel.TabIndex = 2;
            this.FirstnamesLabel.Text = "First Names:";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(36, 105);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(75, 16);
            this.label1.TabIndex = 3;
            this.label1.Text = "Surname:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(35, 156);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(76, 16);
            this.label2.TabIndex = 4;
            this.label2.Text = "OfficerID:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(35, 204);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(88, 16);
            this.label3.TabIndex = 5;
            this.label3.Text = "Skill Level:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(35, 249);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(86, 16);
            this.label4.TabIndex = 6;
            this.label4.Text = "Ambulance";
            // 
            // FirstnamesBox
            // 
            this.FirstnamesBox.Location = new System.Drawing.Point(167, 64);
            this.FirstnamesBox.Name = "FirstnamesBox";
            this.FirstnamesBox.Size = new System.Drawing.Size(220, 20);
            this.FirstnamesBox.TabIndex = 7;
            this.FirstnamesBox.Text = new_staff1.FirstName;
            // 
            // SurnameBox
            // 
            this.SurnameBox.Location = new System.Drawing.Point(167, 105);
            this.SurnameBox.Name = "SurnameBox";
            this.SurnameBox.Size = new System.Drawing.Size(220, 20);
            this.SurnameBox.TabIndex = 8;
            this.SurnameBox.Text = new_staff1.Surname;
            // 
            // OfficerIDBox
            // 
            this.OfficerIDBox.Location = new System.Drawing.Point(167, 152);
            this.OfficerIDBox.Name = "OfficerIDBox";
            this.OfficerIDBox.Size = new System.Drawing.Size(220, 20);
            this.OfficerIDBox.TabIndex = 9;
            this.OfficerIDBox.Text = new_staff1.OfficerID.ToString();

            // 
            // SkillLevelBox
            // 
            this.SkillLevelBox.Items.Add("Basic");
            this.SkillLevelBox.Items.Add("Intermediate");
            this.SkillLevelBox.Items.Add("Advanced");
            this.SkillLevelBox.SelectedIndex = SkillLevelBox.FindString(new_staff1.SkillLevel.ToString());
            Debug.WriteLine(new_staff1.OfficerID.ToString() + "HEY HO");

            this.SkillLevelBox.FormattingEnabled = true;
            this.SkillLevelBox.Location = new System.Drawing.Point(167, 204);
            this.SkillLevelBox.Name = "SkillLevelBox";
            this.SkillLevelBox.Size = new System.Drawing.Size(220, 21);
            this.SkillLevelBox.TabIndex = 10;

            // 
            // AmbulanceBox
            // 
            this.AmbulanceBox.Items.Add("None");
            using (AMBULANCES_CONTEXT db1 = new AMBULANCES_CONTEXT())
            {
                foreach (var y in db1.AMBULANCES)
                {
                    this.AmbulanceBox.Items.Add(y.AmbulanceID);
                }
            }
            if (new_staff1.Ambulance != null)
            {


                if (!new_staff1.Ambulance.Equals("None") )
                {
                    this.AmbulanceBox.SelectedIndex = AmbulanceBox.FindString(new_staff1.Ambulance);
                }
                else
                {
                    this.AmbulanceBox.SelectedIndex = AmbulanceBox.FindString("None");
                }
            }

            this.AmbulanceBox.FormattingEnabled = true;
            this.AmbulanceBox.Location = new System.Drawing.Point(167, 244);
            this.AmbulanceBox.Name = "AmbulanceBox";
            this.AmbulanceBox.Size = new System.Drawing.Size(220, 21);
            this.AmbulanceBox.TabIndex = 11;

            //COMPLETE THIS
            // 
            // Form4
            // 
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
            this.AddNew.Text = "Add New";
            this.AddNew.UseVisualStyleBackColor = true;

            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(485, 413);
            this.Controls.Add(this.AmbulanceBox);
            this.Controls.Add(this.SkillLevelBox);
            this.Controls.Add(this.OfficerIDBox);
            this.Controls.Add(this.SurnameBox);
            this.Controls.Add(this.FirstnamesBox);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.FirstnamesLabel);
            this.Controls.Add(this.OfficerNameLAbel);
            this.Controls.Add(this.AmbulanceOfficerLabel);
            this.Controls.Add(this.AddNew);
            this.Controls.Add(this.Back);
            this.Name = "Form4";
            this.Text = "Form4";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label AmbulanceOfficerLabel;
        private System.Windows.Forms.Label OfficerNameLAbel;
        private System.Windows.Forms.Label FirstnamesLabel;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox FirstnamesBox;
        private System.Windows.Forms.TextBox SurnameBox;
        private System.Windows.Forms.TextBox OfficerIDBox;
        private System.Windows.Forms.ComboBox SkillLevelBox;
        private System.Windows.Forms.ComboBox AmbulanceBox;
        private System.Windows.Forms.Button Back;
        private System.Windows.Forms.Button AddNew;
    
}
}