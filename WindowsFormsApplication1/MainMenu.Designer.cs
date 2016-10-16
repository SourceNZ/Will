namespace WindowsFormsApplication1
{
    partial class MainMenu
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
        private void InitializeComponent()
        {
            this.label1 = new System.Windows.Forms.Label();
            this.officersButton = new System.Windows.Forms.Button();
            this.AmbulancesButton = new System.Windows.Forms.Button();
            this.RosterButton = new System.Windows.Forms.Button();
            this.exitButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(45, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(376, 25);
            this.label1.TabIndex = 0;
            this.label1.Text = "Ambulance Staff Rostering System";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // officersButton
            // 
            this.officersButton.Location = new System.Drawing.Point(97, 59);
            this.officersButton.Name = "officersButton";
            this.officersButton.Size = new System.Drawing.Size(248, 65);
            this.officersButton.TabIndex = 1;
            this.officersButton.Text = "Officers";
            this.officersButton.UseVisualStyleBackColor = true;
            this.officersButton.Click += new System.EventHandler(this.button1_Click);
            
            // 
            // AmbulancesButton
            // 
            this.AmbulancesButton.Location = new System.Drawing.Point(97, 148);
            this.AmbulancesButton.Name = "AmbulancesButton";
            this.AmbulancesButton.Size = new System.Drawing.Size(248, 69);
            this.AmbulancesButton.TabIndex = 2;
            this.AmbulancesButton.Text = "Ambulances";
            this.AmbulancesButton.UseVisualStyleBackColor = true;
            this.AmbulancesButton.Click += new System.EventHandler(this.button2_Click);
            // 
            // RosterButton
            // 
            this.RosterButton.Location = new System.Drawing.Point(97, 232);
            this.RosterButton.Name = "RosterButton";
            this.RosterButton.Size = new System.Drawing.Size(248, 73);
            this.RosterButton.TabIndex = 3;
            this.RosterButton.Text = "Check Rosters";
            this.RosterButton.UseVisualStyleBackColor = true;
            this.RosterButton.Click += new System.EventHandler(this.RosterButton_Click);
            // 
            // exitButton
            // 
            this.exitButton.Location = new System.Drawing.Point(97, 323);
            this.exitButton.Name = "exitButton";
            this.exitButton.Size = new System.Drawing.Size(248, 71);
            this.exitButton.TabIndex = 4;
            this.exitButton.Text = "Exit";
            this.exitButton.UseVisualStyleBackColor = true;
            this.exitButton.Click += new System.EventHandler(this.button4_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(460, 438);
            this.Controls.Add(this.exitButton);
            this.Controls.Add(this.RosterButton);
            this.Controls.Add(this.AmbulancesButton);
            this.Controls.Add(this.officersButton);
            this.Controls.Add(this.label1);
            this.BackColor = System.Drawing.Color.LightSkyBlue;
            this.Name = "MainMenu";
            this.Text = "MainMenu";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button officersButton;
        private System.Windows.Forms.Button AmbulancesButton;
        private System.Windows.Forms.Button RosterButton;
        private System.Windows.Forms.Button exitButton;
    }
}

