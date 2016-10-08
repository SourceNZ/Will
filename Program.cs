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
                throw new ArgumentException("FirstCharToUpper String.IsNullOrEmpty..... Good One....!");
            }
            return input.First().ToString().ToUpper() + input.Substring(1);
        }
        public static string[] assign(string inputt)
        {
            string[] newid = new string[2] {"1", "1"};
            string tester = inputt.ToUpper();
            using (AMBULANCES_CONTEXT db1 = new AMBULANCES_CONTEXT())
            {
                foreach (var y in db1.AMBULANCES)
                {
                    //Console.WriteLine(y.AmbulanceID + "newambIDS");
                    if (tester.Equals(y.AmbulanceID))
                    {
                        newid[0] = y.AmbulanceID;
                        newid[1] = y.Station;
                    }
                    
                }
                if(newid == null)
                {
                    newid[0] = "1";
                }
            }
            return newid;
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

                                    new_staff.FirstName = FirstCharToUpper(testarray[0]);
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
                                    //FirstCharToUpper(testarray[1]);
                                    new_staff.Surname = FirstCharToUpper(testarray[1]);
                                }



                            }
                            //if there are more than 2 names
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
                                        newname = newname + " " + FirstCharToUpper(testarray[i]);
                                    }
                                }
                                new_staff.FirstName = FirstCharToUpper(newname);
                                int num2;
                                bool isNum2 = Int32.TryParse(testarray[testarray.Length - 1], out num2);
                                if (isNum2)
                                {
                                    Console.WriteLine("An officer must have a surname and at least one given name");
                                    goto NotFound;
                                }
                                else
                                {
                                    new_staff.Surname = FirstCharToUpper(testarray[testarray.Length - 1]);
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

                                    //Console.WriteLine(num.ToString().Length );
                                    if (isNum)
                                    {

                                        if (test.Length == 6)
                                        {
                                            new_staff.OfficerID = num;
                                        }
                                        else
                                        {
                                            Console.WriteLine("The officer ID must be a six digit number");
                                            goto NotFound;
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

                        //if the user enters 4 different words either correctly.
                        string caseSwitch = d[d.Length - 1];
                        switch (caseSwitch)
                        {
                            case "basic":
                                new_staff.SkillLevel = "Basic";

                                break;
                            case "intermediate":
                                new_staff.SkillLevel = "Intermediate";

                                break;
                            case "advanced":
                                new_staff.SkillLevel = "Advanced";

                                break;
                            default:
                                Console.WriteLine("The skill level for the officer must be one of basic, intermediate, or advanced");
                                goto NotFound;

                        }



                        new_staff.Ambulance = null;
                        db.AMBULANCE_STAFFS.Add(new_staff);
                        db.SaveChanges();
                        Console.WriteLine("The ambulance officer has been added");

                    }

                    else if (c[0].Equals("remove"))
                    {
                        string teste = line.Replace("remove ", "");
                        string[] tester = teste.Split(' ');
                        int numba = 0;
                        Console.WriteLine("pay here " + tester.Length);
                        Console.WriteLine("pay heresds " + tester[0]);
                        tester[0] = tester[0].Replace(" ", "");
                        bool isNum = Int32.TryParse(tester[0], out numba);

                        if (isNum)
                        {

                            if (tester[0].Length == 6)
                            {
                                foreach (var x in db.AMBULANCE_STAFFS)
                                {
                           
                                    if (x.OfficerID.Equals(numba))
                                    {

                                        db.AMBULANCE_STAFFS.Remove(x);
                                        Console.WriteLine("Officer {0} {1} ({2}) has been removed ", x.Surname, x.FirstName, x.SkillLevel);
                                    }
                                }
                            }
                            else
                            {
                                Console.WriteLine("Officer ID not found");
                                goto NotFound;
                            }
                        }
                        else
                        {
                            Console.WriteLine("Please enter a 6 Digit Officer ID");
                            goto NotFound;
                        }
                        db.SaveChanges();

                    }

                    //LIST FUNCTION ALL
                    else if (c[0].Equals("list"))
                    {
                        if (c.Length == 1)
                        {


                            DateTime dat = DateTime.Now;
                            Console.WriteLine("\nAmbulance Officer List as of {0:d} at {0:T}", dat);

                            Console.WriteLine("Surname  FirstName  OfficerID  SkillLevel  Ambulance");
                            foreach (var x in db.AMBULANCE_STAFFS)
                            {
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
                            int o = 0;
                            foreach (var x in db.AMBULANCE_STAFFS)
                            {
                                string wordsss = words[words.Length - 1].ToLower();
                                try
                                {
                                    if (wordsss.Equals(x.Surname.ToLower()))
                                    {
                                        o = 1;
                                    }
                                }catch(Exception k)
                                {
                                    
                                }
                            }
                            if(o != 0)
                            {
                                Console.WriteLine("Surname  FirstName  OfficerID  SkillLevel  Ambulance");
                            }
                                //should find a way to make sure this doesnt print 
                                
                            foreach (var x in db.AMBULANCE_STAFFS)
                            {

                                if (x.Ambulance == (null))
                                {
                                    x.Ambulance = "None";
                                }
                                
                                string wordss = words[words.Length - 1].ToLower();
                                try
                                {
                                    if (wordss.Equals(x.Surname.ToLower()))
                                    {
                                        l++;
                                        Console.WriteLine("{0}, {1}, {2}, {3}, {4}", x.Surname, x.FirstName, x.OfficerID, x.SkillLevel, x.Ambulance);
                                    }
                                }
                                catch (Exception n)
                                {
                                    
                                }
                                

                            }
                            if (o == 0)
                            {
                                Console.WriteLine("Please enter a Officer surname");
                            }
                            
                            Console.WriteLine(String.Format("Listed {0} Officers. \n", l));
                        }
                        db.SaveChanges();
                    }
                    
                    // c[0] = assign, c[1] = 123456, c[2] = to, c[3] = a7
                    else if (c[0].Equals("assign"))
                    {

                        string[] assignarray = line.Split(' ');
                        // assignarray[0] = assign, assignarray[1] = 123456, assignarray[2] = to, assignarray[3] = a7     length  = 4
                        if ((line.IndexOf(" to ")) != -1)
                        {
                            //if there is a to : 
                            if (assignarray.Length == 4)
                            {
                                //string newid = null;
                                string offid = assignarray[1];
                                string newamb = null;
                                if (assignarray.Length > 0)
                                {
                                    newamb = assignarray[assignarray.Length - 1];

                                }
                                //Console.WriteLine(newamb + "< newamb");
                                int num = 0;
                                bool isNum = Int32.TryParse(offid, out num);
                                //Console.WriteLine(num + "< NUMM");
                                //if the user entered a number correctly it will pass this test:
                                
                                if (isNum)
                                {
                                    //if the number is 6 digit long
                                    if (offid.Length == 6)
                                    {
                                        //find the staff member with that id
                                        //then change that staff members.ambulance to be set to ambulance specified if they specify one.
                                        string[] newid = assign(newamb);
                                        if (newid.Length > 0)
                                        {
                                         
                                            //unhandled null exception - handled now
                                            if (!(newid[0].Equals("1")))
                                            {
                                                foreach (var x in db.AMBULANCE_STAFFS)
                                                {
                                                    if (x.OfficerID.Equals(num) && newid[0] != "1")
                                                    {
                                                        x.Ambulance = newid[0];
                                                        Console.WriteLine("The ambulance officer has been assigned to {0} at {1}.", newid[0], newid[1]);

                                                    }
                                                }
                                            }
                                            else
                                            {
                                                //Console.WriteLine(newid + "< newid after assignment");
                                            }
                                        }
                                    }
                                    else
                                    {
                                        Console.WriteLine("The officer ID must be a six digit number");
                                        goto NotFound;
                                    }

                                }
                                else
                                {
                                    Console.WriteLine("The officer ID must be a six digit number");
                                    goto NotFound;
                                }
                           
                            }
                            //if there is no to: print error message
                            else
                            {
                                Console.WriteLine("Ambulance ID is missing or not found");

                            }

                        }
                        else
                        {
                            Console.WriteLine("Ambulance ID is missing or not found");

                        }
                        db.SaveChanges();

                    }
                    //unassign - syntax = unassign 134321 
                    else if (c[0].Equals("unassign"))
                    {
                        string[] assignarray = line.Split(' ');
                        // assignarray[0] = unassign, assignarray[1] = 123456
                        if (assignarray.Length == 2)
                        {
                            //string newid = null;
                            string offid = assignarray[1];
                            string newamb = null;
                            Console.WriteLine(newamb + "< newamb");
                            int num = 0;
                            bool isNum = Int32.TryParse(offid, out num);
                            Console.WriteLine(num + "< NUMM");
                            int l = 0;
                            //if the user entered a number correctly it will pass this test:
                            //Console.WriteLine(num.ToString().Length );
                            if (isNum)
                            {
                                //if the number is 6 digit long
                                if (offid.Length == 6)
                                {

                                    //find if the officer id exits and it has an ambulance, if it does then find out the details of that ambulance, if it doesnt then say it does
                                    //returns ambulance details: gets passed ambulance
                                    foreach (var x in db.AMBULANCE_STAFFS)
                                    {
                                    
                                        if (x.OfficerID.Equals(num))
                                        {
                                            l = 1;
                                            
                                            if(x.Ambulance != null)
                                            {

                                              if (!(x.Ambulance.Equals("None")))
                                              {
                                                string[] newid = new string[2] {"1", "1"};
                                                newamb = x.Ambulance;
                                                if(!x.Ambulance.Equals("None"))
                                                {
                                                    newid = assign(newamb);
                                                }
                                                //Console.WriteLine(newamb + "this is the ambulance that the persons aassigned to");    //None
                                               // Console.WriteLine(newid[0] + "this should be the same as above");                //1
                                                if (!(newid[0].Equals("1")))
                                                {
                                                      x.Ambulance = null;
                                                      Console.WriteLine(x.Ambulance + "this should be the null");
                                                      Console.WriteLine("The ambulance officer has been removed from {0} at {1}.", newid[0], newid[1]);
                                                }
                                                
                                            }
                                            
                                            else
                                            {
                                                Console.WriteLine("Officer is not assigned to an ambulance");
                                                goto NotFound;
                                            }
                                            }


                                        }

                                    }
                                    if (l == 0)
                                    {
                                        Console.WriteLine("Officer ID not found");
                                    }
                                    
                                }
                                else
                                {
                                    Console.WriteLine("The officer ID must be a six digit number");
                                    goto NotFound;
                                }

                            }

                            else
                            {
                                Console.WriteLine("The officer ID must be a six digit number");
                                goto NotFound;
                            }

                        }
                        else
                        {
                            Console.WriteLine("Officer not found");
                            goto NotFound;
                        }
                    }
                    else
                    { 
                         Console.WriteLine("Invalid command: the valid commands are ADD, REMOVE, LIST, ASSIGN, UNASSIGN and EXIT");
                    }
                    db.SaveChanges();
                }
                Console.Write(">:");
            }
            Environment.Exit(0);
        }
    }
}

