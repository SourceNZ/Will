using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleApplication1
{
    class Program
    {
        static void Main(string[] args)
        {
            String line;
            Console.Write(">:");
            while (!((line = Console.ReadLine().ToLower()) == "exit"))
            {

                using (AMBULANCES_STAFF_CONTEXT db = new AMBULANCES_STAFF_CONTEXT())
                {
                    //string[] wess = line.Split(new String[] {"as"}, StringSplitOptions.None);
                    string[] west = line.Split(' ');
                    if (west[0].Equals("add"))
                    {

                        //need to reconvert the first letters of names to upper case and also check if user input is correct
                        for (int i = 0; i < west.Length; i++)
                        {
                            Console.WriteLine(west[i]);
                        }
             
                        var new_staff = new AMBULANCE_STAFF();
                        new_staff.FirstName = west[1];
                        new_staff.Surname =  west[2];
                        new_staff.OfficerID = Convert.ToInt32(west[4]);
                        new_staff.SkillLevel = west[6];
                        new_staff.Ambulance = null;
                        db.AMBULANCE_STAFFS.Add(new_staff);
                        db.SaveChanges();
                    }
                    //LIST FUNCTION ALL
                    if (line.Equals("list"))
                    {
                        DateTime dat = DateTime.Now;
                        Console.WriteLine("\nAmbulance Officer List as of {0:d} at {0:T}", dat);

                        Console.WriteLine("Surname  FirstName  OfficerID  SkillLevel  Ambulance");
                        foreach (var x in db.AMBULANCE_STAFFS) {
                            if (x.Ambulance == (null))
                            {
                                x.Ambulance = "None";
                            }
                            Console.WriteLine("{0}, {1}, {2}, {3}, {4}", x.Surname, x.FirstName, x.OfficerID, x.SkillLevel, x.Ambulance);
                        }
                        int k = db.AMBULANCE_STAFFS.Count(); Console.WriteLine(String.Format("Listed {0} Officers. \n", k));
                    }
                    
                    string[] words = line.Split(' ');
                    int l = 0;
                    //LIST FUNCTION SPECIFIC NAME
                    if (words.Length == 2)
                    {
                        Console.WriteLine("Surname  FirstName  OfficerID  SkillLevel  Ambulance");
                        foreach (var x in db.AMBULANCE_STAFFS)
                        {

                            if (x.Ambulance == (null))
                            {
                                x.Ambulance = "None";
                            }
                            words[1]  = words[1].ToLower();
                            if (words[1].Equals(x.Surname.ToLower()))
                            {
                                l++;
                                
                                Console.WriteLine("{0}, {1}, {2}, {3}, {4}", x.Surname, x.FirstName, x.OfficerID, x.SkillLevel, x.Ambulance);
                            }
                        }
                        Console.WriteLine(String.Format("Listed {0} Officers. \n", l));
                    }
                    
 
                }
                using (AMBULANCES_CONTEXT db = new AMBULANCES_CONTEXT())
                {
                    /*
                     *            string line;
                        //Console.WriteLine("Connection string: {0}", db.Database.Connection.ConnectionString); int j = db.AMBULANCES.Count(); Console.WriteLine(String.Format("We have {0} records. \n", j));
                        Console.Write(">:");
                        line = Console.ReadLine();
                        if(line.Equals("list"))
                        {
                            DateTime dat = DateTime.Now;
                            Console.WriteLine("\nAmbulance officer list as of {0:d} at {0:T}", dat);

                            Console.WriteLine("AmbulanceID  Station");
                            foreach (var x in db.AMBULANCES) { Console.WriteLine("AmbulanceID {0}; Station {1}; ", x.AmbulanceID, x.Station); }
                        }
                        //If they specify a last name, filter through the list of ambulances/officers and find those people with that last name
                        foreach (var x in db.AMBULANCES)
                        {
                            if (line.Equals(x.AmbulanceID))
                            {
                                Console.WriteLine("AmbulanceID {0}; Station {1}; ", x.AmbulanceID, x.Station);
                            }
                        }
                        int k = db.AMBULANCES.Count(); Console.WriteLine(String.Format("Listed {0} Ambulances. \n", k));
                        //Console.ReadLine();
                        */
                }
                //  Console.Write(">:");
                //  if (Console.ReadLine() == "exit")
                // {
                //     
                //  }
                // using (AMBULANCES_STAFF_CONTEXT db = new AMBULANCES_STAFF_CONTEXT())
                //{
                //var new_staff = new AMBULANCE_STAFF(db.Surname = "Bob", FirstName = "Jones", OfficerID = "123456", SkillLevel = "basic", AMBULANCE = "A1");
                // db.add(new_staff);
                // db.SaveChanges();
                // }
                //line = Console.ReadLine();
                //Console.Write(">:");
                Console.Write(">:");
            }
            Environment.Exit(0);
        }
    }
}
