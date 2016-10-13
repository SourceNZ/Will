using System.Data.Entity;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System;

namespace WindowsFormsApplication1
{
    public class AMBULANCES_STAFF_CONTEXT : DbContext
    {
        public AMBULANCES_STAFF_CONTEXT() : base("MySqlConnection") { }
        public  DbSet<AMBULANCE_STAFF> AMBULANCE_STAFFS { get; set; }
        //public string Surname { get; internal set; }
    }
    [Table("AMBULANCE_STAFF")]
    public class AMBULANCE_STAFF
    {
        
        public string Surname { get; set; }
        public string FirstName { get; set; }
        [DatabaseGenerated(DatabaseGeneratedOption.None)]
        [Key]
        public int OfficerID { get; set; }
        public string SkillLevel { get; set; }
        public string Ambulance { get; set; }

    }
}
