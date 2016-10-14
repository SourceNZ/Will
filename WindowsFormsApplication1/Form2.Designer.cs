using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    partial class Form2
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
        private void frmDGV_Load()
        {
            using (AMBULANCES_STAFF_CONTEXT db = new AMBULANCES_STAFF_CONTEXT())
            {

                var new_staff = new AMBULANCE_STAFF();
                //dummy data
                List<AMBULANCE_STAFF> lstStaff = new List<AMBULANCE_STAFF>();
                foreach (var x in db.AMBULANCE_STAFFS)
                {
                    this.dataGridView1.Rows.Add(x.OfficerID, x.FirstName + " " + x.Surname, x.SkillLevel, x.Ambulance);
                    lstStaff.Add(x);

                }
                foreach (var x in lstStaff)
                {
                    Debug.WriteLine("ID" + x.OfficerID + x.FirstName + " " + x.Surname + x.SkillLevel + x.Ambulance);

                }
        
                //use binding source to hold dummy data
               // BindingSource binding = new BindingSource();
               // binding.DataSource = new_staff;

                //bind datagridview to binding source
                //dataGridView1.DataSource = binding;
            }
        }
        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.ID = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Names = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Skill = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Ambulance = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.Back = new System.Windows.Forms.Button();
            this.AddNew = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(125, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(217, 25);
            this.label1.TabIndex = 0;
            this.label1.Text = "Ambulance Officers";
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.ID,
            this.Names,
            this.Skill,
            this.Ambulance});
            this.dataGridView1.Location = new System.Drawing.Point(31, 60);
            this.dataGridView1.MaximumSize = new System.Drawing.Size(800, 400);
            this.dataGridView1.MinimumSize = new System.Drawing.Size(443, 244);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.Size = new System.Drawing.Size(453, 261);
            this.dataGridView1.TabIndex = 1;
            this.dataGridView1.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView1_CellContentClick);
            // 
            // ID
            // 
            this.ID.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.Fill;
            this.ID.HeaderText = "ID";
            this.ID.Name = "ID";
            // 
            // Name
            // 
            this.Names.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.Fill;
            this.Names.HeaderText = "Name";
            this.Names.Name = "Name";
            // 
            // Skill
            // 
            this.Skill.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.Fill;
            this.Skill.HeaderText = "Skill";
            this.Skill.Name = "Skill";
            // 
            // Ambulance
            // 
            this.Ambulance.AutoSizeMode = System.Windows.Forms.DataGridViewAutoSizeColumnMode.Fill;
            this.Ambulance.HeaderText = "Ambulance";
            this.Ambulance.Name = "Ambulance";
            // 
            // Back
            // 
            this.Back.Location = new System.Drawing.Point(31, 327);
            this.Back.Name = "Back";
            this.Back.Size = new System.Drawing.Size(133, 72);
            this.Back.TabIndex = 2;
            this.Back.Text = "Back";
            this.Back.UseVisualStyleBackColor = true;
            this.Back.Click += new System.EventHandler(this.button1_Click);
            // 
            // AddNew
            // 
            this.AddNew.Location = new System.Drawing.Point(355, 327);
            this.AddNew.Name = "AddNew";
            this.AddNew.Size = new System.Drawing.Size(129, 72);
            this.AddNew.TabIndex = 3;
            this.AddNew.Text = "Add New";
            this.AddNew.UseVisualStyleBackColor = true;
            this.AddNew.Click += new System.EventHandler(this.button2_Click);

            // 
            // Form2
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(506, 424);
            this.Controls.Add(this.AddNew);
            this.Controls.Add(this.Back);
            this.Controls.Add(this.dataGridView1);
            this.Controls.Add(this.label1);
            //this.Name = "Form2";
            this.Text = "Form2";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();
            

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.DataGridViewTextBoxColumn Names;
        private DataGridViewTextBoxColumn ID;
        
        private DataGridViewTextBoxColumn Skill;
        private DataGridViewTextBoxColumn Ambulance;
        private Button Back;
        private Button AddNew;
    }
}