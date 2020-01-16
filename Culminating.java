/* Vivienne Cruz
   Wheel of Fortune
   ICS 3U
   Culminating

   Date: June 4th 2018

   This program will replicate a version of the Wheel Of Fortune. This game will produce a random word/phrase in which the user will try to guess by guessing
   indiviual letters. Each letter will be worth a random amount of money determined randomly by the computer. The user has to keeping guessing letters until they
   are able to fully guess the word/phrase. The amount of money won will be revealed at the end. This program will include a game board which will reveal the word
   /phrase letter by letter. */

// The "Culminating" class.
import java.awt.*;
import hsa.Console;

public class Culminating
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();

	//CREATING THE INTRO SCREEN
	Color lightRed = new Color (240, 68, 77);
	c.setColor (lightRed);

	Color lightYellow = new Color (255, 255, 187);
	Color aqua = new Color (152, 203, 203);

	c.fillRect (0, 0, 650, 500);            //background of the intro screen

	c.setColor (aqua);

	c.fillRect (10, 10, 620, 480);    //background of the intro screen

	Font harlowFont = new Font ("Harlow Solid Italic", Font.BOLD, 75);           //FONT #1 FOR THE INTO SCREEN
	c.setFont (harlowFont);

	Color navy = new Color (0, 64, 128);
	c.setColor (navy);

	c.drawString ("Wheel of", 170, 130);                      // TITLE
	c.drawString ("Fortune", 120, 170);                       // TEXT

	c.setColor (navy);
	c.fillOval (210, 190, 180, 180);                    //drawing the wheel

	c.setColor (lightYellow);
	drawingStars ();


	c.setColor (lightYellow);                       //drawing the lines on the wheel
	c.fillOval (290, 270, 20, 20);
	c.drawLine (225, 230, 290, 275);
	c.drawLine (280, 190, 300, 270);
	c.drawLine (345, 205, 305, 280);
	c.drawLine (385, 250, 310, 280);
	c.drawLine (378, 320, 300, 280);
	c.drawLine (340, 360, 305, 290);
	c.drawLine (280, 365, 295, 290);
	c.drawLine (220, 320, 295, 280);


	Font erasFont = new Font ("Eras Bold ITC", Font.BOLD, 25);                 //FONT #2 FOR THE INTRO SCREEN
	c.setFont (erasFont);

	c.setColor (navy);
	c.drawString ("Press enter to continue...", 140, 400);                  //CLEARING
	c.getChar ();                                                             //THE INTRO
	c.clear ();                                                               //SCREEN

	//CREATING THE INSTRUCTIONS SCREEN (ADD MORE DETAIL LATER)
	c.setColor (lightRed);

	c.fillRect (0, 0, 650, 500);

	Font harlowFont2 = new Font ("Harlow Solid Italic", Font.BOLD, 50);       //FONT #3 FOR INSTRUCTIONS SCREENS
	c.setFont (harlowFont2);

	c.setColor (navy);
	c.drawString ("How to play...", 150, 60);                                 //ADDING THE TITLE TO THE INSTRUCTIONS SCREEN

	c.setColor (Color.white);
	c.setFont (erasFont);

	c.drawString ("-You will be given a word from the category", 20, 120);    //RULE #1
	c.drawString ("'COMPUTER SCIENCE' to guess.", 20, 150);

	c.drawString ("-You can only guess one letter at a time,", 20, 180);  //RULE #2
	c.drawString ("but you lose if you guess the wrong letter.", 20, 210);

	c.drawString ("-You'll be given 5 free tries to guess letters", 20, 240);  //RULE #3
	c.drawString ("before losing a turn", 20, 270);

	c.drawString ("-To solve the puzzle, enter '$' to see if ", 20, 300); //RULE #4
	c.drawString ("you've won the game!", 20, 330);

	c.drawString ("-You'll spin the wheel and for every correct", 20, 360);   //RULE #5
	c.drawString ("guess, you'll will money!", 20, 390);

	c.setColor (navy);
	c.setFont (harlowFont2);

	c.drawString ("Press enter to play...", 60, 450);                     //CLEARING THE
	c.getChar ();                                                           //SCREEN TO BEGIN
	c.clear ();                                                             //THE GAME

	//CREATING THE BACKGROUND OF THE MAIN SCREEN
	c.setColor (lightRed);


	c.fillRect (0, 0, 650, 500);


	c.setColor (aqua);

	c.fillRect (10, 10, 620, 480);

	//DRAWING THE GAMEBOARD
	c.setColor (navy);
	c.fillRect (40, 30, 570, 230);                               //GAMEBOARD

	c.setColor (lightYellow);
	c.fillRect (50, 40, 550, 210);                                      //GAMEBOARD

	c.setColor (navy);

	c.drawLine (100, 40, 100, 250);
	c.drawLine (150, 40, 150, 250);
	c.drawLine (200, 40, 200, 250);                                   //DRAWING THE
	c.drawLine (250, 40, 250, 250);
	c.drawLine (300, 40, 300, 250);                                   //VERTICAL LINES OF
	c.drawLine (350, 40, 350, 250);
	c.drawLine (400, 40, 400, 250);                                   //THE GAME BOARD
	c.drawLine (450, 40, 450, 250);
	c.drawLine (500, 40, 500, 250);
	c.drawLine (550, 40, 550, 250);

	c.drawLine (50, 90, 600, 90);                                   //DRAWING THE
	c.drawLine (50, 145, 600, 145);                                 //HORIZONTAL LINES OF
	c.drawLine (50, 200, 600, 200);                                 //THE GAME BOARD

	Font forte25 = new Font ("Forte", Font.PLAIN, 25);
	c.setFont (forte25);

	c.drawString ("The category is... words in Computer Science", 80, 285);  //CATEGORY DECLARATION

	c.fillRect (30, 295, 200, 150);                               //LETTER BANK

	c.setColor (lightYellow);

	c.fillRect (40, 305, 180, 130);                              //LETTER BANK

	c.setColor (navy);
	Font forte22 = new Font ("Forte", Font.PLAIN, 22);
	c.setFont (forte22);

	c.drawString ("Letter Bank", 75, 325);

	//DECLARING THE POSSIBLE WORDS FOR THE USER TO GUESS
	String[] words = {"javascript", "engineering", "method", "hardware", "conditional", "software", "technology", "programming", "algorithms", "database"};

	//RANDOMLY CHOOSING WHICH WORD WILL BE USED
	int wordToGuessNum = 0;
	wordToGuessNum = (int) (Math.random () * 6) + 1;
	String wordToGuess = "";

	switch (wordToGuessNum)                                    //SWITCH STATEMENT WILL DECLARE WHICH WORD WILL BE USED FOR THE USER
	{
	    case 1:                                                //TO GUESS BY DECLARING EACH WORD TO A NUMBER
		{
		    wordToGuess = words [0];
		    break;
		}
	    case 2:
		{
		    wordToGuess = words [1];
		    break;
		}
	    case 3:
		{
		    wordToGuess = words [2];
		    break;
		}
	    case 4:
		{
		    wordToGuess = words [3];
		    break;
		}
	    case 5:
		{
		    wordToGuess = words [4];
		    break;
		}
	    case 6:
		{
		    wordToGuess = words [5];
		    break;
		}
	    case 7:
		{
		    wordToGuess = words [6];
		    break;
		}
	    case 8:
		{
		    wordToGuess = words [7];
		    break;
		}
	    case 9:
		{
		    wordToGuess = words [8];
		    break;
		}
	    case 10:
		{
		    wordToGuess = words [9];
		    break;
		}

	}

	//DRAWING THE USER'S TOTAL WINNINGS BOX
	c.setColor (navy);
	c.fillRect (320, 430, 230, 60);

	c.setColor (lightYellow);
	c.fillRect (330, 440, 210, 40);

	c.setColor (navy);
	c.drawString ("Winnings:", 335, 465);

	//ADDING THE USERS TOTAL WINNINGS
	int usersWinnings = 0;

	//ASKING THE USER TO "SPIN THE WHEEL" (ASSIGNING A VALUE TO THE USER'S GUESS
	spinTheWheelText ();                                                             //METHOD ASKS THE USERS TO SPIN THE WHEEL AND DRAWS THE SPIN VALUE BOX

	int[] wheelValues = {100, 200, 300, 400, 500, 600, 700, 800, 900, 2000};        //DECLARING THE MONEY VALUES FOR THE "WHEEL"
	int userGuessValue = spinningTheWheel (wheelValues);                            //METHOD WHICH "SPINS" THE "WHEEL"

	c.drawString ("$" + userGuessValue + "", 430, 355);                             //SHOWING THE USER THEIR SPIN VALUE

	//ASKING THE USER TO GUESS A LETTER
	int x = 50, y = 370, freeTryCount = 5;
	String userGuessOfPuzzle = "", userSolvesPuzzle = "";
	char guess = '1';



	for (int ctr = 1 ; ctr <= 5 ; ctr++)      //FIX TO MAKE IT SO THAT IT CAN ONLY GUESS EACH LETTER OF THE ALPHABETH ONCE
	{

	    for (x = 50 ; x <= 190 ; x += 25)       //ALLOWS ONLY 5 LETTERS TO BE PLACED IN A ROW FOR THE LETTER BANK
	    {
		do                                                                                  //this do while gives the user five free tries
		{
		    c.setColor (aqua);
		    c.fillRect (30, 450, 250, 30);

		    c.setColor (navy);
		    c.drawString ("You have " + freeTryCount + " free tries left", 30, 475);            //counting how many free tries the user has
		    freeTryCount--;                                                                     // until they start losing turns


		    c.drawString ("Guess a letter! Enter $ if you want", 290, 400);
		    c.drawString ("to solve the puzzle completely.", 290, 420);

		    guess = c.getChar ();                                           //RECIEVES THE USER'S GUESS
		    c.drawString (guess + "", x, y);
		    x += 25;

		    if (guess == '$')                                               //entering $ lets the user solves the puzzle
		    {
			break;                                                      //breaks out of this loop to access the userSolvesPuzzle method
		    }
		    if (!(checkGuess (guess, wordToGuess).equals ("")))
		    {
			c.setColor (navy);
			c.fillRect (320, 430, 230, 60);

			c.setColor (lightYellow);
			c.fillRect (330, 440, 210, 40);

			c.setColor (navy);
			c.drawString ("Winnings:", 335, 465);

			c.setColor (navy);

			c.setColor (lightYellow);
			c.fillRect (425, 465, 50, 10);
			c.setColor (navy);
			usersWinnings += userGuessValue;                        //adding together the user's winning values if they guess the correct letter
			c.drawString ("$" + usersWinnings, 445, 465);
			c.setColor (navy);
			spinTheWheelText ();                                //ASKS THE USER TO SPIN THE WHEEL AGAIN

			userGuessValue = spinningTheWheel (wheelValues);
			c.drawString ("$" + userGuessValue + "", 430, 355);     //PRINTS OUT THE NEW SPIN VALUE IN THE VALUE BOX
		    }
		    else
		    {
			c.setColor (navy);
			spinTheWheelText ();                                //ASKS THE USER TO SPIN THE WHEEL AGAIN

			userGuessValue = spinningTheWheel (wheelValues);
			c.drawString ("$" + userGuessValue + "", 430, 355);     //PRINTS OUT THE NEW SPIN VALUE IN THE VALUE BOX
			continue;
		    }
		}
		while (freeTryCount >= 1);

		if (guess == '$')                                                //entering $ lets the user solves the puzzle
		{
		    break;                                                      //breaks out of this loop to access the userSolvesPuzzle method
		}
		if (!(checkGuess (guess, wordToGuess).equals ("")))     //this if statement runs only after the user has used up all of their free tries
		{
		    c.setColor (navy);
		    c.fillRect (320, 430, 230, 60);                     //this if statement checks to see if the user's guess is correct by calling the checkGuess method

		    c.setColor (lightYellow);
		    c.fillRect (330, 440, 210, 40);

		    c.setColor (navy);
		    c.drawString ("Winnings:", 335, 465);

		    c.setColor (navy);

		    c.setColor (lightYellow);
		    c.fillRect (425, 465, 50, 10);
		    c.setColor (navy);
		    usersWinnings += userGuessValue;
		    c.drawString ("$" + usersWinnings, 445, 465);
		}
		else
		    break;                      //breaks out of the nested for loop to end the game

	    }
	    y += 20;     //increases the y coordinate when printing the letters in the letter bank

	    if (guess == '$')                                   //entering $ lets the user solves the puzzle
	    {

		break;                                  //breaks out of this loop to access the userSolvesPuzzle method
	    }

	    if (checkGuess (guess, wordToGuess).equals (""))        //ends the game if the user guessed the wrong letter
	    {
		c.setColor (lightRed);

		c.fillRect (0, 0, 650, 500);

		c.setColor (aqua);

		c.fillRect (10, 10, 620, 480);

		c.setColor (lightYellow);
		drawingStars ();

		c.setColor (navy);
		c.setFont (harlowFont);
		c.drawString ("End of Game!", 100, 140);

		c.setFont (forte25);

		c.drawString ("The word to guess was " + wordToGuess, 100, 200);

		c.drawString ("You ran out of tries!", 100, 250);
		c.drawString ("You won: $" + (100 + usersWinnings), 100, 290);

		break;
	    }

	}

	if (guess == '$')
	{
	    userGuessOfPuzzle = userSolvesPuzzle (wordToGuess);                 //this outro screen will print if the user guessed the word before
	    c.setColor (lightRed);                                              // running out of tries

	    c.fillRect (0, 0, 650, 500);

	    c.setColor (aqua);

	    c.fillRect (10, 10, 620, 480);

	    c.setColor (lightYellow);
	    drawingStars ();

	    c.setColor (navy);
	    c.setFont (harlowFont);
	    c.drawString ("End of Game!", 100, 140);
	    c.setFont (forte25);

	    c.drawString ("The word to guess was " + wordToGuess, 100, 200);
	    if (userGuessOfPuzzle.equals (wordToGuess))
	    {
		c.drawString ("You guessed the word correctly!", 100, 250);                                //this will print if they guessed correctly
		c.drawString ("You won the jackpot: $" + (10000 + usersWinnings), 100, 290);

	    }
	    else
	    {
		c.drawString ("You guessed the word incorrectly!", 100, 250);                           //this will print if they guessed incorrectly
		c.drawString ("You won: $" + usersWinnings, 100, 290);

	    }
	}

    }


    // main method


    //Method for spinTheWheelText
    public static void spinTheWheelText ()
    {
	Color lightYellow = new Color (255, 255, 187);

	Color navy = new Color (0, 64, 128);

	Color aqua = new Color (152, 203, 203);

	c.drawString ("Press enter to spin the wheel!", 290, 310);
	c.fillRect (320, 320, 210, 60);
	c.setColor (lightYellow);                                                        //SPIN VALUE BOX
	c.fillRect (330, 330, 190, 40);
	c.setColor (navy);                                                              //SPIN VALUE BOX
	c.drawString ("Value:", 345, 355);
	c.getChar ();

	c.setColor (lightYellow);                                                   //DRAWS A BOX OVER THE SPIN VALUE BOX TO "CLEAR" IT BEFORE EACH SPIN
	c.fillRect (430, 635, 75, 50);

	c.setColor (navy);
    }


    //Method for spinningTheWheel
    public static int spinningTheWheel (int[] x)
    {
	int cell = (int) (Math.random () * 11);                                 //RANDOMIZES WHICH VALUE IS CHOSEN

	int spin = 0;

	switch (cell)                                                          //SWITCH CASE ALLOWS DECLARES WHICH VALUE IS RETURNED
	{
	    case 0:
		{
		    spin = x [cell];
		    break;
		}
	    case 1:
		{
		    spin = x [cell];
		    break;
		}
	    case 2:
		{
		    spin = x [cell];
		    break;
		}
	    case 3:
		{
		    spin = x [cell];
		    break;
		}
	    case 4:
		{
		    spin = x [cell];
		    break;
		}
	    case 5:
		{
		    spin = x [cell];
		    break;
		}
	    case 6:
		{
		    spin = x [cell];
		    break;
		}
	    case 7:
		{
		    spin = x [cell];
		    break;
		}
	    case 8:
		{
		    spin = x [cell];
		    break;
		}
	    case 9:
		{
		    spin = x [cell];
		    break;
		}
	    default:
		{
		    spin = 1000;
		    break;
		}
	}
	return spin;
    }


    //Method for checkGuess
    public static String checkGuess (char x, String y)               //x IS THE LETTER GUESSED, y IS THE WORD TO GUESS
    {
	Font forte50 = new Font ("Forte", Font.PLAIN, 50);          //MAKING FONTS AND COLOURS LOCAL
	Font forte22 = new Font ("Forte", Font.PLAIN, 22);         // AND COLOURS
	Color navy = new Color (0, 64, 128);                        //LOCAL
	Color aqua = new Color (152, 203, 203);                    //VARIABLES
	Color lightYellow = new Color (255, 255, 187);

	String guessedWord = "";
	int xCord = 10, count = 1;

	for (int ctr = 0 ; ctr <= y.length () - 1 ; ctr++)      //CHECKS TO SEE IF THE LETTER GUESSED MATCHES ANY OF THE
	{
	    xCord += 50;                                        //LETTERS IN THE ONE WORD
	    if (x == (y.charAt (ctr)))
	    {
		c.setFont (forte50);                            //IF CORRECT, IT PRINTS THE LETTER ON THE GAMEBOARD
		c.drawString (x + "", xCord, 130);
		c.setFont (forte22);

		guessedWord += x;

		c.setColor (navy);
	    }
	    else if (x != (y.charAt (ctr)))
	    {
		c.setFont (forte22);
		continue;
	    }
	}
	return guessedWord;
    }


    //Method for userSolvesPuzzle
    public static String userSolvesPuzzle (String y)
    {
	Font forte50 = new Font ("Forte", Font.PLAIN, 50);          //MAKING FONTS AND COLOURS LOCAL
	Font forte22 = new Font ("Forte", Font.PLAIN, 22);         // AND COLOURS
	Color navy = new Color (0, 64, 128);                        //LOCAL
	Color aqua = new Color (152, 203, 203);                    //VARIABLES
	Color lightRed = new Color (240, 68, 77);

	c.setColor (aqua);
	c.fillRect (270, 290, 350, 140);

	c.setColor (navy);
	c.drawString ("Type in the entire word to", 290, 320);
	c.drawString ("solve the puzzle!", 290, 340);
	c.drawString ("Press the enter key when you are", 290, 360);
	c.drawString ("done entering your guess.", 290, 380);

	c.setColor (lightRed);

	char enter;
	String guessedWord = "";

	int xCord = 10;
	for (int counter = 1 ; counter <= y.length () ; counter++)     //only lets user enter the same amount of letters as there are in the word to guess
	{
	    xCord += 50;
	    enter = c.getChar ();
	    guessedWord += enter;                           //creates a new string with the user's guess
	    c.setFont (forte50);                            //IF CORRECT, IT PRINTS THE LETTER ON THE GAMEBOARD
	    c.drawString (enter + "", xCord, 130);
	}
	c.setFont (forte22);
	c.setColor (aqua);

	return guessedWord;                             //returns the user's guess
    }


    //Mehtod for drawingStars
    public static void drawingStars ()
    {
	//drawing the stars at the top of the  screen
	c.fillStar (20, 13, 50, 50);
	c.fillStar (80, 13, 50, 50);
	c.fillStar (140, 13, 50, 50);
	c.fillStar (200, 13, 50, 50);
	c.fillStar (260, 13, 50, 50);
	c.fillStar (320, 13, 50, 50);
	c.fillStar (380, 13, 50, 50);
	c.fillStar (440, 13, 50, 50);
	c.fillStar (500, 13, 50, 50);
	c.fillStar (560, 13, 50, 50);

	//drawing the stars at the bottom of the  screen
	c.fillStar (20, 435, 50, 50);
	c.fillStar (80, 435, 50, 50);
	c.fillStar (140, 435, 50, 50);
	c.fillStar (200, 435, 50, 50);
	c.fillStar (260, 435, 50, 50);
	c.fillStar (320, 435, 50, 50);
	c.fillStar (380, 435, 50, 50);
	c.fillStar (440, 435, 50, 50);
	c.fillStar (500, 435, 50, 50);
	c.fillStar (560, 435, 50, 50);
    }
}


// Culminating class


