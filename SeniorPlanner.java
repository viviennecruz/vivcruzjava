/*this class inherits from Planner class. This class includes some get and set methods that are neccessary to have for only senior grades
and it also contains sort method used for when sorting the courses from lowest (0) to greatest (4) and the compare method to check if the user's 
average is above the cut off average */

import java.io.*;
public class SeniorPlanner extends Planner
{
    private int[] marks = new int [6];
    private int[] courses = new int [6];
    private int avg;
    private int lowest;


    public SeniorPlanner (int c[], int m[])                         //constructor#1
    {
	courses = c;
	marks = m;
	avg = 0;
    }


    public SeniorPlanner ()                                         //constructor#2
    {
	avg = 0;

    }


    public int[] getMarks ()                                            //getters and setters
    {
	return marks;
    }


    public void setMarks (int m[])
    {
	marks = m;
    }


    public int[] getCourses ()
    {
	return courses;
    }


    public void setCourses (int c[])
    {
	courses = c;
    }


    public int getLowest ()
    {
	return lowest;
    }


    public void setLowest (int l)
    {
	lowest = l;
    }


    public int calcAvg (int sum)                                                       //to calculate the average of the user's marks
    {
	avg = sum / 6;
	return avg;
    }


    public void bubbleSort (int c[])                                                   //sorting the courses they enter in order to compare
    {
	int Switch = 1;
	int Temp;
	while (Switch != 0)
	{
	    Switch = 0;
	    for (int j = 0 ; j < 5 ; j++)
	    {
		if (c [j] > c [j + 1])
		{
		    Temp = c [j];
		    c [j] = c [j + 1];
		    c [j + 1] = Temp;
		    Switch = 1;
		}
	    }
	}
    }

/*    public boolean compareCourses (int courses[], String p) throws IOException                      //comparing user courses to required courses
    {
	RandomAccessFile raf = new RandomAccessFile ("RequiredCourse.bin", "rw");
	int location;                                                                                   //0 = others
	// 1 = English ; 2 = Advanced Functions ; 3 = Calculus
	// 4 = Science
	location = super.searchForCourses (p);
	raf.seek ((40 * location) + 16);
	boolean j = false;

	for (int i = 0 ; i < 6 ; i++)
	{
	    if (courses [i] == raf.readInt ())
	    {
		j = true;
		continue;
	    }
	    else
	    {
		break;
	    }
	}
	return j;
    }

*/
    public boolean compareAvg (int avg, String p) throws IOException                //comparing the avg to get into the program
    {
	RandomAccessFile raf = new RandomAccessFile ("programs.bin", "rw");
	int location;
	location = super.searchSchool (p);
	int[] avgNeeded = new int [3];
	int v = 50;
	for (int q = 0 ; q < 3 ; q++)
	{
	    raf.seek ((location * 112) + v);
	    avgNeeded [q] = raf.readInt ();
	    v += 25;

	}

	boolean i = false;
	int atleastOne = 0;

	for (int w = 0 ; w < 3 ; w++)
	{
	    if (avg >= avgNeeded [w])
	    {
		atleastOne++;
	    }
	}

	if (atleastOne >= 1)                                    //There are three different averages since there are 3 options for schools
	{                                                          //if the average is above at least one cut off avg, it will return true
	    i = true;
	}
	setLowest (avgNeeded [0]);
	return i;
    }
}
