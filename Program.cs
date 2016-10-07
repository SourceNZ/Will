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
            string line;
            Console.Write(">:");
            while (!((line = Console.ReadLine().ToLower()) == "exit"))
            {

                using (AMBULANCES_STAFF_CONTEXT db = new AMBULANCES_STAFF_CONTEXT())
                {
                    string[] a = line.ToLower().Split(new String[] {" as "}, StringSplitOptions.None);
                    string[] b = line.ToLower().Split(new String[] { " id " }, StringSplitOptions.None);
                    string[] c = line.Split(' ');
                    if (c[0].Equals("add"))
                    {
                        //c = line.Split(new String[] { "add " }, StringSplitOptions.None);
                        //Console.WriteLine(c + "WILL");
                        if(line.IndexOf(" id ") == -1)
                        {
                            Console.WriteLine("An officer must have a six digit ID number");
                        }
                        if (line.IndexOf(" as ") == -1)
                        {
                            Console.WriteLine("An officer musthave a skill level(basic, intermediate, or advanced)");
                        }

                        string v = line.Replace("add ", "");
                        v = v.Replace(" id ", " ");
                        v = v.Replace(" as ", " ");
                        string[] d = v.Split(' ');
                        Console.WriteLine(d.Length + "dfddsdsd");
                        //if the user enters 4 different words either correctly.
                        if (d.Length == 4)
                        {
                            
                            Console.WriteLine("WILL" + d[0] + d[1] + d[2] + d[3] + "WILL");
                        }
                        
                        
                        // skill level .length should be 2  
                        for (int i = 0; i < a.Length; i++)
                        {
                            Console.WriteLine(a[i] + " a {0}", a.Length);
                        }
                        Console.WriteLine("DONE");
                        //id .length should be 2
                        for (int i = 0; i < b.Length; i++)
                        {
                            //b[1]
                            Console.WriteLine(b[i] + " b  : {0}", b.Length);
                        }
                        Console.WriteLine("DONE ID");

                        //need to reconvert the first letters of names to upper case and also check if user input is correct
                        for (int i = 0; i < c.Length; i++)
                        {
                            Console.WriteLine(c[i]);
                        }
                       // if (west[1])
                       // {
                       //  }
                        //var new_staff = new AMBULANCE_STAFF();
                        //new_staff.FirstName = c[1];
                       // new_staff.Surname =  c[2];
                        //new_staff.OfficerID = Convert.ToInt32(c[4]);
                        //new_staff.SkillLevel = c[6];
                        //new_staff.Ambulance = null;
                        //db.AMBULANCE_STAFFS.Add(new_staff);
                       // db.SaveChanges();
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
