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
        public static string FirstCharToUpper(string input)
        {
            if (String.IsNullOrEmpty(input))
            {
                throw new ArgumentException("ARGH!");
            }
            return input.First().ToString().ToUpper() + input.Substring(1);
        }
        static void Main(string[] args)
        {
        NotFound:
            string line;
            Console.Write(">:");
            while (!((line = Console.ReadLine()).ToLower() == "exit"))
            {

                using (AMBULANCES_STAFF_CONTEXT db = new AMBULANCES_STAFF_CONTEXT())
                {
                    string[] a = line.ToLower().Split(new String[] { " as " }, StringSplitOptions.None);
                    string[] b = line.ToLower().Split(new String[] { " id " }, StringSplitOptions.None);
                    string[] c = line.Split(' ');
                    string test;
                    if (c[0].Equals("add"))
                    {
                        var new_staff = new AMBULANCE_STAFF();
                        //if there is no id then print an error message
                        if (line.IndexOf(" id ") == -1)
                        {
                            
                            Console.WriteLine("An officer must have a six digit ID number");
                            goto NotFound;
                        }
                        //if there is no as then print error message
                        if (line.IndexOf(" as ") == -1)
                        {
                            Console.WriteLine("An officer musthave a skill level(basic, intermediate, or advanced)");
                            goto NotFound;
                        }
                        //if there is an id then get the index of that and substring it with just after add and those are all the specified names, if there are less than 2 names print and error
                        // and if there are more than 2 names make all the names but the last one first names then the last one a last name
                        if (!(line.IndexOf(" id ") == -1))
                        {

                            test = line.Substring(4, line.IndexOf(" id ") - 4);

                            string[] testarray = test.Split(' ');
                            for (int i = 0; i < testarray.Length; i++)
                            {
                                Console.WriteLine(testarray[i] + " HERE BOY");

                            }
                            if (testarray.Length < 2)
                            {
                                Console.WriteLine("An officer must have a surname and at least one given name");
                                goto NotFound;
                            }
                            else if (testarray.Length == 2)
                            {
                                //check if the user has entered a number for either the first name or last name
                                int num;
                                bool isNum = Int32.TryParse(testarray[0], out num);
                                if (isNum)
                                {
                                    Console.WriteLine("An officer must have a surname and at least one given name");
                                    goto NotFound;
                                }
                                else
                                {
                                    FirstCharToUpper(testarray[0]);
                                    new_staff.FirstName = testarray[0];
                                }
                                int num1;
                                bool isNum1 = Int32.TryParse(testarray[0], out num1);
                                if (isNum1)
                                {
                                    Console.WriteLine("An officer must have a surname and at least one given name");
                                    goto NotFound;
                                }
                                else
                                {
                                    new_staff.Surname = testarray[1];
                                }



                            }
                            else if (testarray.Length > 2) {
                                string newname = "";
                                for (int i = 0; i < (testarray.Length - 1); i++)
                                {
                                    int num;
                                    bool isNum = Int32.TryParse(testarray[i], out num);
                                    if (isNum)
                                    {
                                        Console.WriteLine("An officer must have a surname and at least one given name");
                                        goto NotFound;
                                    }
                                    else
                                    {
                                        newname = newname + " " + testarray[i];
                                    }
                                }
                                new_staff.FirstName = newname;
                                int num2;
                                bool isNum2 = Int32.TryParse(testarray[testarray.Length - 1], out num2);
                                if (isNum2)
                                {
                                    Console.WriteLine("An officer must have a surname and at least one given name");
                                    goto NotFound;
                                }
                                else
                                {
                                    new_staff.Surname = testarray[testarray.Length - 1];
                                }

                                //Console.WriteLine(newname + " last name: {0}  THIS IS THE NEW NAME FOR LOTS OF NAMES", testarray[testarray.Length - 1]);
                            }
                            //if there is an id and and as, then get the number between those two words and it should be the ID # then verify its a 6 digit number DONE
                            if (!(line.IndexOf(" id ") == -1))
                            {
                                if (!(line.IndexOf(" as ") == -1))
                                {
                                    test = line.Substring(line.IndexOf(" id "), line.Length - (line.IndexOf(" as ") - 1));
                                    test = test.Replace(" id ", "");
                                    test = test.Replace(" ", "");
                                    test = test.Replace("as", "");
                                    test = test.Replace("int", "");
                                    
                                    int num = 0;
                                    bool isNum = Int32.TryParse(test, out num);
                                    
                                    Console.WriteLine(num.ToString().Length );
                                    if (isNum)
                                    {

                                        if (test.Length == 6)
                                        {
                                            new_staff.OfficerID = num;
                                        }

                                    }
                                    else
                                    {
                                        
                                        Console.WriteLine("The officer ID must be a six digit number");
                                        goto NotFound;
                                    }


                                }
                            }



                        }

                        string v = line.Replace("add ", "");
                        v = v.Replace(" id ", " ");
                        v = v.Replace(" as ", " ");
                        string[] d = v.Split(' ');
                        Console.WriteLine(d.Length + "dfddsdsd");
                        //if the user enters 4 different words either correctly.
                        string caseSwitch = d[d.Length - 1];
                        switch (caseSwitch)
                        {
                            case "basic":
                                new_staff.SkillLevel = "Basic";
                                Console.WriteLine("basic BITCH");
                                break;
                            case "intermediate":
                                new_staff.SkillLevel = "Intermediate";
                                Console.WriteLine("intermediate BITCH");
                                break;
                            case "advanced":
                                new_staff.SkillLevel = "Advanced";
                                Console.WriteLine("advanced BITCH");
                                break;
                            default:
                                Console.WriteLine("The skill level for the officer must be one of basic, intermediate, or advanced");
                                goto NotFound;

                        }

                        // int offid = Convert.ToInt32(line[d.Length - 2]);
                        //Console.WriteLine(offid + "TESTS");
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


                        new_staff.Ambulance = null;
                        db.AMBULANCE_STAFFS.Add(new_staff);
                        db.SaveChanges();
                        Console.WriteLine("The ambulance officer has been added");

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
                            words[1] = words[1].ToLower();
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

