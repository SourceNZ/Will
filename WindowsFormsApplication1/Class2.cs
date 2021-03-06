﻿using System.Data.Entity;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System;

namespace WindowsFormsApplication1
{
    public class AMBULANCES_STAFF : DbContext
    {
        public AMBULANCES_STAFF() : base("MySqlConnection") { }
        public DbSet<AMBULANCES> AMBULANCES { get; set; }
    }
    [Table("AMBULANCES")]
    public class AMBULANCES
    {
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        [Key]
        public string AmbulanceID { get; set; }
        public string Station { get; set; }

    }
}

