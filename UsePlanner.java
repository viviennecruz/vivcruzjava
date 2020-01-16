// The "UsePlanner" class.
import java.awt.*;
import hsa.Console;
import java.io.*;
import java.awt.image.*;

/*This is the main class where we have methods for displaying different screens and to ask user questions */
/*This program is directed to highschool students to help plan their post-secondary education.
Depending on their grade, marks and interests, this program will lay out a couple of post-secondary program options to the students.
The students will then select which post-secondary program interests them the most.
The program will then provide the student with a list of the top three schools (based on reputation).
Moreover, if the student knows what program they want to pursue in post-secondary, then this program will suggest the top three schools based on which program they input. */


public class UsePlanner
{
    static Console c;           // The output console
    public Image Pic;
    public static void main (String[] args) throws IOException
    {
	c = new Console ();
	Image Pic = Picture2.Pic;
	new Picture2 ("University.jpg", c, Pic, 30, 170);
	IntroScreen ();
	BeginningScreen ();
	char userGrade = question1 ();
	BeginningScreen ();
	Planner p = new Planner ();
	SeniorPlanner sp = new SeniorPlanner ();

	p.setUserGrade (userGrade);
	if (userGrade == '1')                                         //the program runs differently depending on what grade the user is in
	{ //GRADE 9/10
	    char userAns = question2 ();
	    p.setUserAns (userAns);
	    if (userAns == '1')                                         //grade 9/10 knows what they want to go into
	    {
		String programChosen = listPrograms ();                     //user picks from the list of programs
		int location = p.searchSchool (programChosen);
		int newLocation = location;
		p.searchDisplaySchool (location, newLocation);                  //show the user top 3 schools and the averages
		c.clear ();
		background ();
		display (p, location, newLocation, sp);

	    }
	    else                                                           //grade 9/10 does not know what they want to go into
	    {
		int sum = InterestQ (p);
		String programChosen = results (sum);                           //take the result and suggest options
		int location = p.searchSchool (programChosen);
		int newLocation = location;
		p.searchDisplaySchool (location, newLocation);
		c.clear ();
		background ();
		display (p, location, newLocation, sp);

	    }
	}
	else                                                                             //GRADE 11/12
	{
	    if (question2 () == '1')                                         //grade 11/12 knows what they want to go into
	    {
		char userAns = question2 ();
		p.setUserAns (userAns);
		int[] courses = askForCourses (sp);                             //checking what courses they have
		int avg = askForMarks (sp);                                     //determining their average with their top 6 courses
		sp.setAvg (avg);
		String programChosen = listPrograms ();
		p.setProgram (programChosen);
		int location = p.searchSchool (programChosen);                  //searching for the top 3 schools and the required average
		int newLocation = location;
		p.searchDisplaySchool (location, newLocation);
		c.clear ();
		background ();
		display (p, location, newLocation, sp);
		displayAvg (sp, p);
	    }
	    else                                                              //grade 11/12 does not know what they want to go into
	    {
		int[] courses = askForCourses (sp);
		int avg = askForMarks (sp);
		sp.setAvg (avg);
		int sum = InterestQ (p);
		String programChosen = results (sum);                       //result from the interest questions help determine what the user is possibly interested in
		p.setProgram (programChosen);
		int location = p.searchSchool (programChosen);
		int newLocation = location;
		p.searchDisplaySchool (location, newLocation);          //searching the top 3 schools for program chosen
		c.clear ();
		background ();
		display (p, location, newLocation, sp);
		displayAvg (sp, p);                                     //checking if they meet the cut off average

	    }
	}


	// Place your program here.  'c' is the output console
    } // main method


    public static void background ()
    {

	c.setColor (Color.black);
	c.fillRect (0, 0, 650, 500);
	c.setColor (Color.white);
	c.drawRect (10, 10, 620, 480);
	c.drawRect (20, 20, 600, 460);
    }


    public static void IntroScreen ()
    {
	background ();

	Font CalifornianFB = new Font ("Californian FB", Font.BOLD, 28);
	c.setFont (CalifornianFB);
	c.setColor (Color.white);
	c.drawString ("Post- secondary Planner", 150, 120);
	c.drawString ("Press enter to continue...", 150, 430);          //CLEARING
	c.getChar ();                                                    //THE INTRO
	c.clear ();
    }


    public static void BeginningScreen ()
    {
	background ();

	c.setColor (Color.white);               //drawing boxes
	c.fillRect (135, 250, 100, 50);
	c.fillRect (395, 250, 100, 50);
	c.fillRect (270, 350, 100, 50);

	c.setColor (Color.black);           //outlining boxes
	c.drawRect (135, 250, 100, 50);
	c.drawRect (395, 250, 100, 50);
	c.drawRect (270, 350, 100, 50);
    }


    public static char question1 ()
    {
	char userGrade = '0';
	do
	{
	    Font cambria30b = new Font ("Cambria", Font.BOLD, 30);
	    c.setFont (cambria30b);
	    c.setColor (Color.white);
	    c.drawString ("What grade are you in? Enter 1 or 2.", 80, 150);

	    Font cambria22p = new Font ("Cambria", Font.PLAIN, 22);
	    c.setFont (cambria22p);

	    c.drawString ("1", 175, 245);
	    c.drawString ("2", 435, 245);

	    c.setColor (Color.black);
	    c.drawString ("9 & 10", 155, 280);
	    c.drawString ("11 & 12", 410, 280);

	    c.setColor (Color.black);
	    userGrade = c.getChar ();                                                      //printing users grade to screen
	    c.drawString (userGrade + "", 315, 380);
	    c.getChar ();
	    c.clear ();
	    BeginningScreen ();
	}
	while (userGrade != '1' && userGrade != '2');                                   // do while loop to make sure user inputs only 1 or 2

	return userGrade;
    }


    public static char question2 ()
    {

	char userProgram = '0';
	do
	{
	    Font cambria30b = new Font ("Cambria", Font.BOLD, 30);
	    c.setFont (cambria30b);
	    c.setColor (Color.white);
	    c.drawString ("Do you know which program", 120, 140);
	    c.drawString ("you want to go into?", 165, 180);

	    Font cambria22p = new Font ("Cambria", Font.PLAIN, 22);
	    c.setFont (cambria22p);

	    c.drawString ("1", 175, 245);
	    c.drawString ("2", 435, 245);

	    c.setColor (Color.black);
	    c.drawString ("yes", 170, 280);
	    c.drawString ("no", 430, 280);

	    c.setColor (Color.black);
	    userProgram = c.getChar ();          //printing users grade to screen
	    c.drawString (userProgram + "", 315, 380);
	    c.getChar ();
	    c.clear ();
	    BeginningScreen ();
	}
	while (userProgram != '1' && userProgram != '2');                        //making sure user only inputs 1 or 2 as their answer (error check)
	return userProgram;
    }


    public static String listPrograms ()
    {
	background ();

	Font cambria22p = new Font ("Cambria", Font.PLAIN, 22);
	c.setFont (cambria22p);
	c.setColor (Color.white);
	c.drawString ("1. Medical", 50, 120);                                           //listing different program options
	c.drawString ("2. Computer Science", 50, 150);
	c.drawString ("3. Engineering", 50, 180);
	c.drawString ("4. Business", 50, 210);
	c.drawString ("5. Art", 50, 240);
	c.drawString ("6. English", 50, 270);
	c.drawString ("7. Teacher", 50, 300);
	c.drawString ("8. Lawyer", 50, 330);
	c.drawString ("9. Police", 50, 360);
	c.drawString ("0. Math", 50, 390);

	Font cambria25b = new Font ("Cambria", Font.BOLD, 25);
	c.setFont (cambria25b);
	c.drawString ("Enter the number for your desired program.", 50, 80);

	c.fillRect (350, 250, 150, 100);
	c.setColor (Color.black);
	c.drawRect (350, 250, 150, 100);

	Font cambria30b = new Font ("Cambria", Font.BOLD, 30);
	c.setFont (cambria30b);
	char userAnswer = c.getChar ();
	c.drawString (userAnswer + "", 415, 310);

	String program = "";
	switch (userAnswer)                             //returns a different string depending on what the user inputs
	{
	    case '1':
		{
		    program = "medical";
		    break;
		}
	    case '2':
		{
		    program = "computer science";
		    break;
		}
	    case '3':
		{
		    program = "engineering";
		    break;
		}
	    case '4':
		{
		    program = "business";
		    break;
		}
	    case '5':
		{
		    program = "arts";
		    break;
		}
	    case '6':
		{
		    program = "english";
		    break;
		}
	    case '7':
		{
		    program = "teacher";
		    break;
		}
	    case '8':
		{
		    program = "lawyer";
		    break;
		}
	    case '9':
		{
		    program = "police";
		    break;
		}
	    case '0':
		{
		    program = "math";
		    break;
		}

	}
	return program;
    }


    public static int InterestQ (Planner p) throws IOException                          //this method displays the interest questions
    {

	BufferedReader frq = new BufferedReader (new FileReader ("questions.txt"));
	String m = "";
	int sum = 0;
	for (int i = 0 ; i < 10 ; i++)
	{
	    background ();

	    Font cambria18p = new Font ("Cambria", Font.PLAIN, 18);
	    c.setFont (cambria18p);
	    c.setColor (Color.white);
	    m = frq.readLine ();
	    c.drawString (m, 20, 100);
	    c.fillRect (200, 300, 100, 50);
	    c.fillRect (125, 200, 100, 50);
	    c.fillRect (275, 200, 100, 50);
	    c.fillRect (425, 200, 100, 50);
	    c.fillRect (350, 300, 100, 50);

	    c.drawString ("1", 170, 185);
	    c.drawString ("2", 320, 185);
	    c.drawString ("3", 470, 185);
	    c.drawString ("4", 245, 285);
	    c.drawString ("5", 395, 285);

	    c.setColor (Color.black);
	    c.drawRect (125, 200, 100, 50);
	    c.drawRect (275, 200, 100, 50);
	    c.drawRect (425, 200, 100, 50);
	    c.drawRect (200, 300, 100, 50);
	    c.drawRect (350, 300, 100, 50);

	    c.setFont (cambria18p);
	    c.drawString ("Strongly", 135, 220);
	    c.drawString ("Disagree", 133, 240);
	    c.drawString ("Disagree", 290, 230);
	    c.drawString ("Neutral", 440, 230);
	    c.drawString ("Agree", 220, 330);
	    c.drawString ("Strongly", 360, 320);
	    c.drawString ("Agree", 370, 340);

	    int ans = c.readInt ();                                             //user will type in the number 1-5 depending if they agree or disagree
	    sum += ans;
	    p.setSum (sum);
	    c.clear ();
	}

	return sum;
    }


    public static String results (int s)                    //this method will list out some programs that the user will be most suited for depending on their interest results
    {
	background ();

	Font cambria18p = new Font ("Cambria", Font.PLAIN, 18);
	c.setFont (cambria18p);
	c.setColor (Color.white);

	c.drawString ("According to your interests, you would be most suited for...", 20, 100);

	if (s >= 10 && s <= 25)                         //the range for these if/else statements were calculated and determined by Julia and Vivienne based on one's interests
	{
	    c.drawString ("1. English", 50, 150);
	    c.drawString ("2. Math", 50, 200);
	}
	else if (s >= 26 && s <= 35)
	{
	    c.drawString ("1. Lawyer", 50, 150);
	    c.drawString ("2. Arts", 50, 200);
	    c.drawString ("3. Police", 50, 250);
	    c.drawString ("4. Computer Science", 50, 300);
	}
	else if (s >= 36 && s <= 50)
	{
	    c.drawString ("1. Business", 50, 150);
	    c.drawString ("2. Engineering", 50, 200);
	    c.drawString ("3. Medical", 50, 250);
	    c.drawString ("4. Teacher", 50, 300);
	}

	c.drawString ("Which career are you most interested in?", 50, 350);
	c.fillRect (400, 325, 100, 50);
	c.setColor (Color.black);
	c.drawRect (400, 325, 100, 50);

	char a = c.getChar ();                                                          //user decides one program from the option available
	Font cambria25b = new Font ("Cambria", Font.BOLD, 25);
	c.setFont (cambria25b);
	c.drawString (a + "", 440, 360);
	//different sets of sum
	if (s >= 10 && s <= 25)                                     //between 10-25 --> English or Math
	{
	    if (a == '1')
		return "english";
	    else if (a == '2')
		return "math";
	}
	else if (s >= 26 && s <= 35)                 //between 26-35 --> Lawyer, Arts, Police, Computer Science
	{
	    if (a == '1')
		return "lawyer";
	    else if (a == '2')
		return "arts";
	    else if (a == '3')
		return "police";
	    else if (a == '4')
		return "computer science";
	}
	else if (s >= 36 && s <= 50)                // //between 36-50 --> Business, Engineering, Medical, Teacher
	{
	    if (a == '1')
		return "business";
	    else if (a == '2')
		return "engineering";
	    else if (a == '3')
		return "medical";
	    else if (a == '4')
		return "teacher";
	}
	return "";

    }


    public static void display (Planner p, int location, int newLocation, SeniorPlanner sp) throws IOException
    {
	c.setColor (Color.white);
	Font cambria22p = new Font ("Cambria", Font.PLAIN, 22);
	c.setFont (cambria22p);

	String school = p.getSchool ();
	int avg = p.getAvg ();

	c.drawString ("Recommended Schools and Cutoff Averages:", 50, 50);

	c.drawString (school, 100, 100);
	c.drawString ("" + avg, 100, 150);

	newLocation += 29;
	p.searchDisplaySchool (location, newLocation);

	school = p.getSchool ();
	avg = p.getAvg ();

	c.drawString (school, 100, 200);                            //Displaying top 3 schools and the cutoff average
	c.drawString ("" + avg, 100, 250);

	newLocation += 29;
	p.searchDisplaySchool (location, newLocation);

	school = p.getSchool ();
	avg = p.getAvg ();

	c.drawString (school, 100, 300);
	c.drawString ("" + avg, 100, 350);
	char answer = '0';

	do
	{
	    if (p.getUserGrade () == '1' && p.getUserAns () == '1')
	    {
		c.drawString ("Press 'c' key to go back", 80, 400);
		answer = c.getChar ();
		if (answer == 'c')                                            //go back to options that they are given
		{
		    String programChosen = listPrograms ();
		    location = p.searchSchool (programChosen);
		    newLocation = location;
		    p.searchDisplaySchool (location, newLocation);
		    background ();
		    display (p, location, newLocation, sp);
		}
	    }

	    else if (p.getUserGrade () == '1' && p.getUserAns () == '2')
	    {
		c.drawString ("Press 'b' key to go back", 80, 400);
		answer = c.getChar ();
		if (answer == 'b')                                            //go back to options
		{
		    String programChosen = results (p.getSum ());
		    location = p.searchSchool (programChosen);
		    newLocation = location;
		    p.searchDisplaySchool (location, newLocation);

		    background ();
		    display (p, location, newLocation, sp);
		}
	    }
	    else if (p.getUserGrade () == '2' && p.getUserAns () == '1')
	    {
		c.drawString ("Press 'c' key to go back", 80, 400);
		c.drawString ("Press 'm' key to continue", 80, 430);

		answer = c.getChar ();
		if (answer == 'c')                                            //go back to options
		{
		    String programChosen = listPrograms ();
		    location = p.searchSchool (programChosen);
		    newLocation = location;
		    p.searchDisplaySchool (location, newLocation);
		    background ();
		    display (p, location, newLocation, sp);
		}
		else if (answer == 'm')                                     //moving on to the next page
		{
		    displayAvg (sp, p);
		}
	    }

	    else if (p.getUserGrade () == '2' && p.getUserAns () == '2')
	    {
		c.drawString ("Press 'b' key to go back", 80, 400);
		c.drawString ("Press 'm' key to continue", 80, 430);
		answer = c.getChar ();

		if (answer == 'b')                                            //go back to options
		{
		    String programChosen = results (p.getSum ());
		    location = p.searchSchool (programChosen);
		    newLocation = location;
		    p.searchDisplaySchool (location, newLocation);

		    background ();
		    display (p, location, newLocation, sp);
		}
		else if (answer == 'm')
		{
		    displayAvg (sp, p);
		}
	    }
	}
	while (answer == 'c' || answer == 'b');

    }


    public static int[] askForCourses (SeniorPlanner sp) throws IOException
    {
	background ();
	c.setColor (Color.white);
	Font cambria22p = new Font ("Cambria", Font.PLAIN, 22);
	c.setFont (cambria22p);

	c.drawString ("Enter your top six courses.", 50, 100);                              //displaying options

	c.drawString ("1. English", 100, 150);
	c.drawString ("2. Advanced Functions", 100, 200);
	c.drawString ("3. Calculus", 100, 250);
	c.drawString ("4. Science", 100, 300);
	c.drawString ("0. Other", 100, 350);

	Font cambria18p = new Font ("Cambria", Font.PLAIN, 18);
	c.setFont (cambria18p);
	c.drawString ("(Enter 4 twice if you are taking 2 different sciences.)", 40, 375);

	c.drawString ("Your Courses", 445, 140);

	c.fillRect (400, 150, 200, 150);
	c.setColor (Color.black);
	c.drawRect (400, 150, 200, 150);

	int[] courses = new int [6];
	char ans = '0';
	int x = 425;
	int y = 170;

	int engCount = 0;
	int afCount = 0;
	int calcCount = 0;
	int sciCount = 0;
	do                             //this do while checks that the user doesnt put the same course in twice
	{
	    for (int e = 0 ; e < 6 ; e++)
	    {
		ans = c.getChar ();


		if (ans == '1')
		{

		    courses [e] = 1;
		    engCount++;
		    if (engCount > 1)
		    {
			courses [e] = -1;
			e--;
			continue;
		    }
		    c.drawString ("English", x, y);
		}
		else if (ans == '2')
		{

		    courses [e] = 2;
		    afCount++;
		    if (afCount > 1)
		    {
			courses [e] = -1;
			e--;
			continue;
		    }
		    c.drawString ("Advanced Functions", x, y);
		}
		else if (ans == '3')
		{

		    courses [e] = 3;
		    calcCount++;
		    if (calcCount > 1)
		    {
			courses [e] = -1;
			e--;
			continue;
		    }
		    c.drawString ("Calculus", x, y);
		}
		else if (ans == '4')
		{

		    courses [e] = 4;
		    sciCount++;
		    if (sciCount > 3)
		    {
			courses [e] = -1;
			e--;
			continue;
		    }
		    c.drawString ("Science", x, y);
		}
		else if (ans == '0')
		{
		    c.drawString ("Other", x, y);
		    courses [e] = 0;
		}
		y += 20;

	    }
	}


	while (ans != '1' && ans != '2' && ans != '3' && ans != '4' && ans != '0');
	sp.bubbleSort (courses);

	sp.setCourses (courses);

	return courses;
    }


    public static void displayMarks (SeniorPlanner sp, int[] m)             //boxes to display the marks for the user to see
    {
	int xvalue = 50;

	for (int e = 0 ; e < 6 ; e++)
	{
	    c.setColor (Color.white);
	    c.fillRect (xvalue, 200, 80, 50);
	    c.setColor (Color.black);
	    c.drawRect (xvalue, 200, 80, 50);
	    xvalue += 70;
	}


	xvalue = 78;
	for (int w = 0 ; w < 6 ; w++)
	{
	    c.setColor (Color.black);                   //prints their top 6 marks to the screen
	    c.drawString ("" + m [w], xvalue, 227);
	    xvalue += 70;
	    c.setColor (Color.white);
	}
    }


    public static int askForMarks (SeniorPlanner sp)             //the user inputs top six marks that they have
    {
	background ();
	c.setColor (Color.white);
	Font cambria22p = new Font ("Cambria", Font.PLAIN, 22);
	c.setFont (cambria22p);

	c.drawString ("Enter your top six marks.", 50, 100);

	int marks[] = new int [6];
	int sum = 0;
	displayMarks (sp, marks);
	for (int t = 0 ; t < 6 ; t++)
	{
	    int ans = c.readInt ();
	    marks [t] = ans;
	    sum += marks [t];
	    c.clear ();
	    background ();
	    displayMarks (sp, marks);
	    c.drawString ("Enter your top six marks.", 50, 100);

	}
	int avg = sp.calcAvg (sum);
	return avg;
    }


    public static void displayAvg (SeniorPlanner sp, Planner p) throws IOException              //check if the user made the cut off average
    {
	background ();
	c.setColor (Color.white);
	boolean average = sp.compareAvg (sp.getAvg (), p.getProgram ());
	if (average == true)
	{
	    c.drawString ("You made the cut off for one of the schools for ", 50, 100);
	    c.drawString ("the program you desire!", 60, 130);
	    c.drawString ("Your current average is " + sp.getAvg (), 80, 160);

	}

	else
	{
	    c.drawString ("Unfortunately, your average is lower than the cut off", 50, 100);
	    c.drawString ("Your current average is " + sp.getAvg (), 80, 130);
	    c.drawString ("The cut off is " + sp.getLowest (), 80, 160);
	}
    }
} // UsePlanner class


