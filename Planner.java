/*This is the superclass for our project. The fields found in this class are used for both grade 9/10s and 11/12s. This class is extended to SeniorPlanner,
exhibiting inheritance. The setters and getters for each field are used in the other classes to access the data for each instance of an object. This class
contains three search methods in order to find and compare certain string values (the string value is usually a program name) determined by the user.*/

import java.io.*;
public class Planner
{
    private int avg;
    private String school;
    private int sum;                //fields
    private char userAns;
    private char userGrade;
    private String program;

    public Planner ()       //constructor
    {
	avg = 0;
	school = "";
	sum = 0;
	userAns = '0';
	userGrade = '0';
	program = "";
    }


    public void setAvg (int n)                      //getters and setters
    {
	avg = n;
    }


    public int getAvg ()
    {
	return avg;
    }


    public void setProgram (String p)
    {
	program = p;
    }


    public String getProgram ()
    {
	return program;
    }


    public void setUserAns (char u)
    {
	userAns = u;
    }


    public int getUserAns ()
    {
	return userAns;
    }


    public void setUserGrade (char g)
    {
	userGrade = g;
    }


    public int getUserGrade ()
    {
	return userGrade;
    }


    public void setSum (int s)
    {
	sum = s;
    }


    public int getSum ()
    {
	return sum;
    }


    public void setSchool (String s)
    {
	school = s;
    }


    public String getSchool ()
    {
	return school;
    }


    public int searchForCourses (String p) throws IOException
    {
	BufferedReader frprograms = new BufferedReader (new FileReader ("RequiredCourses.txt"));                //conversion for String
	RandomAccessFile raf = new RandomAccessFile ("RequiredCourse.bin", "rw");

	byte[] proBytes = new byte [16];

	for (int i = 0 ; i < 10 ; i++)
	{
	    raf.seek (i * 40);
	    frprograms.readLine ().getBytes (0, frprograms.readLine ().length (), proBytes, 0);
	    raf.write (proBytes);

	    for (int j = 0 ; j < 6 ; j++)
	    {
		raf.writeInt (Integer.parseInt (frprograms.readLine ()));
	    }

	}
	frprograms.close ();
	raf.close ();

	raf = new RandomAccessFile ("RequiredCourse.bin", "rw");

	int location = -1;
	for (int x = 0 ; x < 10 ; x++)
	{
	    raf.seek (x * 40);                                          //looks for the location of a program in the binary file and then returns the location
	    raf.read (proBytes);                                        //converts from binary back to string for comparisions
	    String programName = new String (proBytes, 0);
	    programName = programName.trim ();

	    if (p.equals (programName))
		location = x;
	}
	return location;
    }


    public int searchSchool (String p) throws IOException
    {
	BufferedReader frschools = new BufferedReader (new FileReader ("programs.txt"));
	RandomAccessFile raf = new RandomAccessFile ("programs.bin", "rw");

	byte[] schBytes = new byte [25];
	byte[] schBytes2 = new byte [25];

	for (int x = 0 ; x < 10 ; x++)
	{
	    String school = frschools.readLine ();
	    schBytes = new byte [25];
	    school.getBytes (0, school.length (), schBytes, 0);
	    raf.seek (x * 112);
	    raf.write (schBytes);
	    //converts programs.txt file into a binary file
	    String school2 = "";
	    int school3 = 0;
	    for (int y = 0 ; y < 3 ; y++)
	    {
		school2 = frschools.readLine ();
		schBytes2 = new byte [25];
		school2.getBytes (0, school2.length (), schBytes2, 0);
		raf.write (schBytes2);

		school3 = Integer.parseInt (frschools.readLine ());
		raf.writeInt (school3);
	    }

	}

	frschools.close ();
	raf.close ();

	raf = new RandomAccessFile ("programs.bin", "rw");
	int location = -1;
	String program = "";
	for (int j = 0 ; j < 10 ; j++)                          //searches for the location of a program in the binary file and then returns its location
	{
	    raf.seek (j * 112);
	    raf.read (schBytes);

	    program = new String (schBytes, 0);
	    program = program.trim ();

	    if (program.compareTo (p) == 0)
	    {
		location = j;
		break;
	    }
	}

	return location;
    }


    public void searchDisplaySchool (int l, int newL) throws IOException
    {
	RandomAccessFile raf = new RandomAccessFile ("programs.bin", "rw");

	if (l == newL)
	{
	    raf.seek ((l * 112) + 25);                      //this method uses raf.seek to access the string (school name)
	} //and the integer (average needed) for a specific program
	else if (newL == l + 29)
	{
	    raf.seek ((l * 112) + 54);
	}
	else if (newL == l + 58)                                //the value of newL changes in (main) UsePlanner, adds 29 each time
	{ //29 = 25 (length of string byte array) + 4 (length of integer byte)
	    raf.seek ((l * 112) + 83);
	}
	byte[] schoolBytes = new byte [25];

	raf.read (schoolBytes);
	String school = new String (schoolBytes, 0);                //converts the binary values into strings and integers respectively
	setSchool (school);                                         //setters and getters are used so that these values are
	//accessible in the main method (UsePlanner)
	int avg = raf.readInt ();
	setAvg (avg);

	raf.close ();

    }
}
