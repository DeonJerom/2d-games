// The "ReadLib " class by R.Parteno February 2001
// Updated by T. Campos, Sept. 2013.
// Converted to "InputBox" by T. Campos to support Swing dialog boxes, Sept. 2013.
// Updated by S. Buchanan-Bruce, April 2016.
// Updated by T. Campos March 2018- Support for images for all read methods
// Updated by K. Hashmi October 2020- Added img paths, selection inputs, & yes/no boxes
// Updated by J.Deon October 2022 - Implemented combo box inside JOptionPane

// IO is a library class of read routines all based upon pressing the enter key
// IO provides methods for reading int, long, double, boolean, char and String types

// Methods:
// readInt(String prompt) // uses a Tokenizer and Integer wrapper class
// readInt(String prompt, ImageIcon image) // uses a Tokenizer and Integer wrapper class
// readInt(String prompt, String imagePath) // uses a Tokenizer and Integer wrapper class
// readLong(String prompt) // uses a Tokenizer and Long wrapper class
// readLong(String prompt, ImageIcon image) // uses a Tokenizer and Long wrapper class
// readLong(String prompt, String imagePath) // uses a Tokenizer and Long wrapper class
// readDouble (String prompt) // uses a Tokenizer and Double wrapper class
// readDouble (String prompt, ImageIcon image) // uses a Tokenizer and Double wrapper class
// readDouble (String prompt, String imagePath) // uses a Tokenizer and Double wrapper class
// readBoolean (String prompt) // reads a boolean, looks for true or false
// readBoolean (String prompt, ImageIcon image) // reads a boolean, looks for true or false
// readBoolean (String prompt, String imagePath) // reads a boolean, looks for true or false
// readChar (String prompt) // reads the first character of the stream
// readChar (String prompt, ImageIcon image) // reads the first character of the stream
// readChar (String prompt, String imagePath) // reads the first character of the stream
// readString(String prompt) // simply reads the string
// readString(String prompt, ImageIcon image) // simply reads the string, allows program to specify an icon
// readString(String prompt, String init) // simply reads the string and allows for an initial value
// readString(String prompt, ImageIcon image, String init) // simply reads the string, allows program to specify an icon and an initial value
// readString(String prompt, String imagePath, String init) // simply reads the string, allows program to specify an icon and an initial value
// readSelection(String prompt, String[] options) // reads selection from array of options
// readSelection(String prompt, ImageIcon image, String[] options) // reads selection from array of options
// readSelection(String prompt, String imagePath, String[] options) // reads selection from array of options
//
// display (String prompt)  // displays message
// display (String prompt, ImageIcon image)  // displays message with an icon
// display (String prompt, String title, ImageIcon image)  // displays message with an icon and title
// display (String prompt, String title, String imagePath)  // displays message with an icon and title
// display (String prompt, String title, int msgOption)  // displays message with a title and JOptionPane message option
// displayInt (int prompt) // displays integer
// displayLong (long prompt) // displays long
// displayDouble (double prompt) // displays double
// displayChar (char prompt) // displays char
//
// confirmYesNo (String prompt) // returns true for yes and false for no
// confirmYesNo (String prompt, ImageIcon image) // returns true for yes and false for no
// confirmYesNo (String prompt, String imagePath) // returns true for yes and false for no

/*
   combo (values) // used to add any type of values inside the combo box, Duplicate values will not be added to the combo box
   boxChar (String prompt) // char box selected by the user will be returned as a Char type
   boxChar (String prompt, String icon) // boxChar with Image display
   boxString (String prompt) // String value box selected by the user would be returned
   boxString (String prompt, String icon) // boxString with Image display
   boxInt (String prompt) // Returns int value selected by the user
   boxInt (String prompt, String icon) // boxInt with Image Display
   boxDouble (String prompt) // Returns double value selected by the user

 */
import java.io.*;
import java.util.*;
import javax.swing.*;

public class IO
{
   // the Tokenizer is used to get the first item typed on a line, used with
   //  readInt(), readDouble(), and readBoolean()
   static private StringTokenizer stok;
   static private JComboBox box = new JComboBox();
   // the BufferedReader opens the connection to the keyboard
   static private ButtonGroup group = new ButtonGroup();
   static private  Object[] choice;
   static private JRadioButton[] buttons;
   static private String prompt;
   
   public IO (String prompt)
   {
      //constructor
      this.prompt = prompt;
   }
   //
   public static int readInt (String prompt)   // uses a Tokenizer and Integer wrapper class
   { //  to get a true int value
      int i;
      try
      {
         String str = JOptionPane.showInputDialog (prompt);
         StringTokenizer stok = new StringTokenizer (str);
         i = new Integer (stok.nextToken ()).intValue ();
      }
      catch (NumberFormatException nfe)  // invalid keyboard entry
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         i = 0;
      }
      catch (NoSuchElementException nfe)
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         i = 0;
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         i = 0;
      }
      
      return i;
   }
   
   public static int readInt (String prompt, ImageIcon image)   // uses a Tokenizer and Integer wrapper class
   { //  to get a true int value
      int i;
      try
      {
         String str = (String)JOptionPane.showInputDialog (null,
                                                           prompt,
                                                           null,
                                                           JOptionPane.QUESTION_MESSAGE,
                                                           image,
                                                           null,
                                                           null);
         
         StringTokenizer stok = new StringTokenizer (str);
         i = new Integer (stok.nextToken ()).intValue ();
      }
      catch (NumberFormatException nfe)  // invalid keyboard entry
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         i = 0;
      }
      catch (NoSuchElementException nfe)
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         i = 0;
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         i = 0;
      }
      
      return i;
   }
   
   public static int readInt (String prompt, String imagePath)   // uses a Tokenizer and Integer wrapper class
   { 
	  
	  // load image from path
	  ImageIcon image = new ImageIcon(imagePath);
	  
	  //  to get a true int value
      int i;
      try
      {
         String str = (String)JOptionPane.showInputDialog (null,
                                                           prompt,
                                                           null,
                                                           JOptionPane.QUESTION_MESSAGE,
                                                           image,
                                                           null,
                                                           null);
         
         StringTokenizer stok = new StringTokenizer (str);
         i = new Integer (stok.nextToken ()).intValue ();
      }
      catch (NumberFormatException nfe)  // invalid keyboard entry
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         i = 0;
      }
      catch (NoSuchElementException nfe)
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         i = 0;
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         i = 0;
      }
      
      return i;
   }
   
   
   public static long readLong (String prompt)   // uses a Tokenizer and Long wrapper class
   { //  to get a true long value
      long l;
      try
      {
         String str = JOptionPane.showInputDialog (prompt);
         StringTokenizer stok = new StringTokenizer (str);
         l = new Long (stok.nextToken ()).longValue ();
      }
      catch (NumberFormatException nfe)  // invalid keyboard entry
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         l = 0;
      }
      catch (NoSuchElementException nfe)
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         l = 0;
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         l = 0;
      }
      
      return l;
   }
   
   public static long readLong (String prompt, ImageIcon image)   // uses a Tokenizer and Long wrapper class
   { //  to get a true long value
      long l;
      try
      {
         String str = (String)JOptionPane.showInputDialog (null,
                                                           prompt,
                                                           null,
                                                           JOptionPane.QUESTION_MESSAGE,
                                                           image,
                                                           null,
                                                           null);
         
         StringTokenizer stok = new StringTokenizer (str);
         l = new Long (stok.nextToken ()).longValue ();
      }
      catch (NumberFormatException nfe)  // invalid keyboard entry
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         l = 0;
      }
      catch (NoSuchElementException nfe)
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         l = 0;
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         l = 0;
      }
      
      return l;
   }
   
   public static long readLong (String prompt, String imagePath)   // uses a Tokenizer and Long wrapper class
   { 
	   
	   // load image from path
	   ImageIcon image = new ImageIcon(imagePath);
	   
	   //  to get a true long value
      long l;
      try
      {
         String str = (String)JOptionPane.showInputDialog (null,
                                                           prompt,
                                                           null,
                                                           JOptionPane.QUESTION_MESSAGE,
                                                           image,
                                                           null,
                                                           null);
         
         StringTokenizer stok = new StringTokenizer (str);
         l = new Long (stok.nextToken ()).longValue ();
      }
      catch (NumberFormatException nfe)  // invalid keyboard entry
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         l = 0;
      }
      catch (NoSuchElementException nfe)
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         l = 0;
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         l = 0;
      }
      
      return l;
   }
   
   public static double readDouble (String prompt)  // uses a Tokenizer and Double wrapper class
   { //  to get a true double value
      double d = 0;
      try
      {
         String str = JOptionPane.showInputDialog (prompt);
         stok = new StringTokenizer (str);
         d = new Double (stok.nextToken ()).doubleValue ();
      }
      catch (NumberFormatException nfe)
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         d = 0;
      }
      catch (NoSuchElementException nfe)
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         d = 0;
      }
      
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         d = 0;
      }
      return d;
   }
   
   public static double readDouble (String prompt, ImageIcon image)  // uses a Tokenizer and Double wrapper class
   { //  to get a true double value
      double d = 0;
      try
      {
         String str = (String)JOptionPane.showInputDialog (null,
                                                           prompt,
                                                           null,
                                                           JOptionPane.QUESTION_MESSAGE,
                                                           image,
                                                           null,
                                                           null);
         stok = new StringTokenizer (str);
         d = new Double (stok.nextToken ()).doubleValue ();
      }
      catch (NumberFormatException nfe)
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         d = 0;
      }
      catch (NoSuchElementException nfe)
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         d = 0;
      }
      
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         d = 0;
      }
      return d;
   }
   
   public static double readDouble (String prompt, String imagePath)  // uses a Tokenizer and Double wrapper class
   { 
	   
	   // load image from path
	   ImageIcon image = new ImageIcon(imagePath);
	   
	   //  to get a true double value
      double d = 0;
      try
      {
         String str = (String)JOptionPane.showInputDialog (null,
                                                           prompt,
                                                           null,
                                                           JOptionPane.QUESTION_MESSAGE,
                                                           image,
                                                           null,
                                                           null);
         stok = new StringTokenizer (str);
         d = new Double (stok.nextToken ()).doubleValue ();
      }
      catch (NumberFormatException nfe)
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         d = 0;
      }
      catch (NoSuchElementException nfe)
      {
         JOptionPane.showMessageDialog (null, "Your variable has been given value of 0.",
                                        "Warning: A non-numeric value was entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
         d = 0;
      }
      
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         d = 0;
      }
      return d;
   }
   
   public static boolean readBoolean (String prompt)   // reads a boolean, looks for true or false
   { // assumes false otherwise
      boolean result = false;
      try
      {
         String str = JOptionPane.showInputDialog (prompt);
         stok = new StringTokenizer (str);
         String answer = stok.nextToken ();
         if (answer.equals ("true"))   // valid true entry
         {
            result = true;
         }
         else
         {
            if (!answer.equals ("false"))  // invalid entry
               JOptionPane.showMessageDialog (null, "False is assumed.",
                                              "Warning: An invalid value was entered.",
                                              JOptionPane.INFORMATION_MESSAGE);
            result = false;
         }
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         result = false;
      }
      catch (Exception e){
         JOptionPane.showMessageDialog (null, "No input. Assuming false.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         result = false;
      }
      return result;
   }
   
   public static boolean readBoolean (String prompt, ImageIcon image)   // reads a boolean, looks for true or false
   { // assumes false otherwise
      boolean result = false;
      try
      {
         String str = (String)JOptionPane.showInputDialog (null,
                                                           prompt,
                                                           null,
                                                           JOptionPane.QUESTION_MESSAGE,
                                                           image,
                                                           null,
                                                           null);
         stok = new StringTokenizer (str);
         String answer = stok.nextToken ();
         if (answer.equals ("true"))   // valid true entry
         {
            result = true;
         }
         else
         {
            if (!answer.equals ("false"))  // invalid entry
               JOptionPane.showMessageDialog (null, "False is assumed.",
                                              "Warning: An invalid value was entered.",
                                              JOptionPane.INFORMATION_MESSAGE);
            result = false;
         }
         
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         result = false;
      }
      catch (Exception e){
         JOptionPane.showMessageDialog (null, "No input. Assuming false.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         result = false;
      }
      return result;
   }
   
   public static boolean readBoolean (String prompt, String imagePath)   // reads a boolean, looks for true or false
   { 
	   
	   // load image from path
	   ImageIcon image = new ImageIcon(imagePath);
	   
	   // assumes false otherwise
      boolean result = false;
      try
      {
         String str = (String)JOptionPane.showInputDialog (null,
                                                           prompt,
                                                           null,
                                                           JOptionPane.QUESTION_MESSAGE,
                                                           image,
                                                           null,
                                                           null);
         stok = new StringTokenizer (str);
         String answer = stok.nextToken ();
         if (answer.equals ("true"))   // valid true entry
         {
            result = true;
         }
         else
         {
            if (!answer.equals ("false"))  // invalid entry
               JOptionPane.showMessageDialog (null, "False is assumed.",
                                              "Warning: An invalid value was entered.",
                                              JOptionPane.INFORMATION_MESSAGE);
            result = false;
         }
         
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         result = false;
      }
      catch (Exception e){
         JOptionPane.showMessageDialog (null, "No input. Assuming false.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         result = false;
      }
      return result;
   }
   
   public static char readChar (String prompt)
   {
      char oneChar = ' ';
      
      try
      {
         
         String str = JOptionPane.showInputDialog (prompt);
         oneChar = str.charAt (0);   // oneChar is first character of keyboard entry
      }
      
      catch (StringIndexOutOfBoundsException se)  // enter key only, no character
      {
         JOptionPane.showMessageDialog (null, "Blank assumed.",
                                        "Warning: There was no character entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
      }
      
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         oneChar = '0';
      }
      return oneChar;
   }
   
   public static char readChar (String prompt, ImageIcon image)
   {
      char oneChar = ' ';
      
      try
      {
         
         String str = (String)JOptionPane.showInputDialog (null,
                                                           prompt,
                                                           null,
                                                           JOptionPane.QUESTION_MESSAGE,
                                                           image,
                                                           null,
                                                           null);
         oneChar = str.charAt (0);   // oneChar is first character of keyboard entry
      }
      
      catch (StringIndexOutOfBoundsException se)  // enter key only, no character
      {
         JOptionPane.showMessageDialog (null, "Blank assumed.",
                                        "Warning: There was no character entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
      }
      
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         oneChar = '0';
      }
      return oneChar;
   }
   
   public static char readChar (String prompt, String imagePath)
   {
	   
	   // load image from path
	   ImageIcon image = new ImageIcon(imagePath);
	   
	   
      char oneChar = ' ';
      
      try
      {
         
         String str = (String)JOptionPane.showInputDialog (null,
                                                           prompt,
                                                           null,
                                                           JOptionPane.QUESTION_MESSAGE,
                                                           image,
                                                           null,
                                                           null);
         oneChar = str.charAt (0);   // oneChar is first character of keyboard entry
      }
      
      catch (StringIndexOutOfBoundsException se)  // enter key only, no character
      {
         JOptionPane.showMessageDialog (null, "Blank assumed.",
                                        "Warning: There was no character entered.",
                                        JOptionPane.INFORMATION_MESSAGE);
      }
      
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
         oneChar = '0';
      }
      return oneChar;
   }
   
   public static String readString (String prompt)
   {
      String str = "";
      try
      {
         str = JOptionPane.showInputDialog (prompt);   // returns null if no characters typed
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
      }
      return str;
   }
   
     //
   
   public static String readString (String prompt, String init)  // overloaded readString method allowing an initial value
   {
      String str = "";
      try
      { // returns null if no characters typed
         str = (String) JOptionPane.showInputDialog (null,
                                                     prompt,
                                                     null,
                                                     JOptionPane.QUESTION_MESSAGE,
                                                     null,
                                                     null,
                                                     init);
         
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null,
                                        "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
      }
      return str;
   }
   public static String readString (String prompt, ImageIcon image)  // overloaded readString method allowing an icon
   {
      String str = "";
      try
      { // returns null if no characters typed
         str = (String) JOptionPane.showInputDialog (null,
                                                     prompt,
                                                     null,
                                                     JOptionPane.QUESTION_MESSAGE,
                                                     image,
                                                     null,
                                                     null);
         
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null,
                                        "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
      }
      return str;
   }
   
   
   //
   
   public static String readString (String prompt, ImageIcon image, String init)  // overloaded readString method allowing an icon and initial value
   {
      String str = "";
      try
      { // returns null if no characters typed
         str = (String) JOptionPane.showInputDialog (null,
                                                     prompt,
                                                     null,
                                                     JOptionPane.QUESTION_MESSAGE,
                                                     image,
                                                     null,
                                                     init);
         
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null,
                                        "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
      }
      return str;
   }
   
   public static String readString (String prompt, String imagePath, String init)  // overloaded readString method allowing an icon and initial value
   {
	   
	   // load image from path
	   ImageIcon image = new ImageIcon(imagePath);
	   
      String str = "";
      try
      { // returns null if no characters typed
         str = (String) JOptionPane.showInputDialog (null,
                                                     prompt,
                                                     null,
                                                     JOptionPane.QUESTION_MESSAGE,
                                                     image,
                                                     null,
                                                     init);
         
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null,
                                        "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
      }
      return str;
   }
   
   public static String readSelection (String prompt, String[] options) // reads selection from array
   {
      String str = "";
      try
      {
    	  // Display option panel with options
    	  Object selected = JOptionPane.showInputDialog(null, prompt, null, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    	  
    	  str = selected.toString(); // converts selection to string
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
      }
      return str;
   }
   
   public static String readSelection (String prompt, ImageIcon image, String[] options) // reads selection from array with icon
   {
      String str = "";
      try
      {
    	  // Display option panel with options
    	  Object selected = JOptionPane.showInputDialog(null,
    			  										prompt,
    			  										null,
    			  										JOptionPane.QUESTION_MESSAGE,
    			  										image,
    			  										options,
    			  										options[0]);
    	  
    	  str = selected.toString(); // converts selection to string
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
      }
      return str;
   }
   
   public static String readSelection (String prompt, String imagePath, String[] options) // reads selection from array with icon
   {
	   
	   // load image from path
	   ImageIcon image = new ImageIcon(imagePath);
	   
      String str = "";
      try
      {
    	  // Display option panel with options
    	  Object selected = JOptionPane.showInputDialog(null,
    			  										prompt,
    			  										null,
    			  										JOptionPane.QUESTION_MESSAGE,
    			  										image,
    			  										options,
    			  										options[0]);
    	  
    	  str = selected.toString(); // converts selection to string
      }
      catch (NullPointerException npe)
      {
         JOptionPane.showMessageDialog (null, "You have pressed CANCEL. Aborting operation.",
                                        "ABORT",
                                        JOptionPane.INFORMATION_MESSAGE);
      }
      return str;
   }
   
   
   public static void display (String prompt)  // displays String
   {
      JOptionPane.showMessageDialog (null,
                                     prompt);
   }
   
   
   public static void display (String prompt, ImageIcon image)  // displays String with icon
   {
      JOptionPane.showMessageDialog (null,
                                     prompt,
                                     null,
                                     JOptionPane.PLAIN_MESSAGE,
                                     image);
   }
   
   public static void display (String prompt, String title, ImageIcon image)  // displays String with title and icon
   {
      JOptionPane.showMessageDialog (null,
                                     prompt,
                                     title,
                                     JOptionPane.PLAIN_MESSAGE,
                                     image);
   }
   
   public static void display (String prompt, String title, String imagePath)  // displays String with title and icon
   {
	   
	   // load image from path
	   ImageIcon image = new ImageIcon(imagePath);
	   
      JOptionPane.showMessageDialog (null,
                                     prompt,
                                     title,
                                     JOptionPane.PLAIN_MESSAGE,
                                     image);
   }
   
   public static void display (String prompt, String title, int msgOption)  // displays String with JOptionPane message option
   {
      JOptionPane.showMessageDialog (null,
                                     prompt,
                                     title,                       
                                     msgOption);
   }
   
   
   public static void displayInt (int prompt)  // displays int
   {
      JOptionPane.showMessageDialog (null,
                                     String.valueOf (prompt));
   }
   
   
   public static void displayLong (long prompt)  // displays long
   {
      JOptionPane.showMessageDialog (null,
                                     String.valueOf (prompt));
   }
   
   
   public static void displayDouble (double prompt)  // displays double
   {
      JOptionPane.showMessageDialog (null,
                                     String.valueOf (prompt));
   }
   
   
   public static void displayChar (char prompt)  // displays char
   {
      JOptionPane.showMessageDialog (null,
                                     String.valueOf (prompt));
   }
   
   public static boolean confirmYesNo (String prompt)  // asks yes no confirmation
   {
	   int option = 0;
	   
	   option = JOptionPane.showConfirmDialog(null,
			   								prompt, null, JOptionPane.YES_NO_OPTION);

	   if(option == JOptionPane.YES_OPTION) return true;
	   return false;

   }
   
   public static boolean confirmYesNo (String prompt, ImageIcon image)  // asks yes no confirmation with icon
   {
	   int option = 0;
	   
	   option = JOptionPane.showConfirmDialog(null,
			   								prompt,
			   								null,
			   								JOptionPane.YES_NO_OPTION,
			   								JOptionPane.QUESTION_MESSAGE,
			   								image);

	   if(option == JOptionPane.YES_OPTION) return true;
	   return false;

   }
   
   public static boolean confirmYesNo (String prompt, String imagePath)  // asks yes no confirmation with icon
   {
	   
	   // load image from path
	   ImageIcon image = new ImageIcon(imagePath);
	   
	   int option = 0;
	   
	   option = JOptionPane.showConfirmDialog(null,
			   								prompt,
			   								null,
			   								JOptionPane.YES_NO_OPTION,
			   								JOptionPane.QUESTION_MESSAGE,
			   								image);

	   if(option == JOptionPane.YES_OPTION) return true;
	   return false;

   }
   public static void combo(String values)
   {
      String[] adding = values.split(",");
      //values entered by the user which are between commas and separated into different index inside an Array


      for(int i=0;i<adding.length;i++){
         box.addItem(adding[i]);
      }

//        for(int i=0;i< validate.size();i++)
//        {
//            if(validate.get(i).equals(" "))
//            {
//                validate.remove(validate.get(i));
//            }else
//            {
//                box.addItem(validate.get(i).replaceAll(" ",""));
//            }
//        }
   }
   public static char boxChar(String prompt) // method overloaded use for adding images
   {
      char c = '0'; // initial value for the return char is initialized
      int index = JOptionPane.showConfirmDialog(null,box,prompt,
              JOptionPane.YES_NO_OPTION,
              JOptionPane.INFORMATION_MESSAGE,null);
      //user is prompted to select a value from the given inside the combo box

      //Program checks which button user pressed using switch statements
      //case 0 is assigned to yes which returns the selected value
      //anything other than yes would result in program displaying an error message and assigns a defautl value
      switch(index)
      {
         case 0: c = box.getSelectedItem().toString().charAt(0);
            break;
         default: JOptionPane.showMessageDialog(null,
                 "You wished to Abort, Value 0 is given to the variable",
                 "Exit",0,null);
      }
      return c;
   }
   public static char boxChar(String prompt, String icon) // method overloaded use for adding images
   {
      //Same functionality as boxChar but this also displays a user preferred image
      ImageIcon image = new ImageIcon(icon);

      char c = '0'; // initial value for the return char is initialized

      int index = JOptionPane.showConfirmDialog(null,box,prompt,
              JOptionPane.YES_NO_OPTION,
              JOptionPane.INFORMATION_MESSAGE,image);

      switch(index)
      {
         case 0: c = box.getSelectedItem().toString().charAt(0);
            break;

         default: JOptionPane.showMessageDialog(null,
                 "You wished to Abort, Value 0 is given to the variable",
                 "Exit",0,null);
      }
      return c;
   }
   public static String boxString(String prompt)
   {
      JTextField text = new JTextField();
      text.setSize(100,30);
      Object[] hello = {"Name: ",text,"Speed of the rocket: ",box};

      //The return value is initially declared to 0
      String varStr = "0";
      while(varStr.equals("0")){
         int index = JOptionPane.showConfirmDialog(null,hello,prompt,
                 JOptionPane.YES_NO_OPTION,
                 JOptionPane.INFORMATION_MESSAGE,null);
         //user is prompted to select a String value from the given inside the combo box

         //Program checks which button user pressed using switch statements
         //case 0 is assigned to yes which returns the selected value
         //anything other than yes would result in program displaying an error message and assigns a default value
         if(index==0){
            if(!text.getText().replaceAll(" ","").equals("")){
               varStr = String.valueOf(box.getSelectedItem());
            }else if(text.getText().length() >6){
               JOptionPane.showMessageDialog(null,"1 - 4 chars only for name !!",null,0);
            }
            else{
               JOptionPane.showMessageDialog(null,"Please enter a name !!!",null,0);
            }
         }else{
            JOptionPane.showMessageDialog(null,
                    "You wished to Abort, Value 0 is given to the variable",
                    "Exit",0,null);
            break;
         }
      }
      box.removeAllItems();
      return text.getText()+","+varStr;
   }
   public static String getChoice(String prompt)
   {
      //The return value is initially declared to 0
      String varStr = "0";
      while(varStr.equals("0")){
         int index = JOptionPane.showConfirmDialog(null,box,prompt,
                 JOptionPane.YES_NO_OPTION,
                 JOptionPane.INFORMATION_MESSAGE,null);
         //user is prompted to select a String value from the given inside the combo box

         //Program checks which button user pressed using switch statements
         //case 0 is assigned to yes which returns the selected value
         //anything other than yes would result in program displaying an error message and assigns a default value
         if(index==0){
            varStr = String.valueOf(box.getSelectedItem());
         }else{
            varStr = String.valueOf(box.getItemAt(1));
            JOptionPane.showMessageDialog(null, "Rocket Speed Automatically set to: "+varStr);
         }
      }
      box.removeAllItems();
      return varStr;
   }
   public static String boxString(String prompt, String icon) // method overloaded use for adding images
   {
      //Same functionality as boxChar but this also displays a user preferred image
      //The return value is initially declared to 0
      String varStr = "0";

      // load image from path
      ImageIcon image = new ImageIcon(icon);

      int index = JOptionPane.showConfirmDialog(null,box,prompt,
              JOptionPane.YES_NO_OPTION,
              JOptionPane.INFORMATION_MESSAGE,image);
      switch(index)
      {
         case 0: varStr = String.valueOf(box.getSelectedItem());
            break;

         default: JOptionPane.showMessageDialog(null,
                 "You wished to Abort, Value 0 is given to the variable",
                 "Exit",0,null);
      }
      return varStr;
   }
   public static int boxInt(String prompt)
   {   //returns a Wrapper Classes Integer value
      //The return value is initially declared to 0
      int varInt = 0;

      int index = JOptionPane.showConfirmDialog(null,box,prompt,
              JOptionPane.YES_NO_OPTION,
              JOptionPane.INFORMATION_MESSAGE,null);
      //user is prompted to select a String value from the given inside the combo box

      //Program checks which button user pressed using switch statements
      //case 0 is assigned to yes which returns the selected value
      //anything other than yes would result in program displaying an error message and assigns a default value
      switch(index) {
         case 0:
            try{
               varInt = Integer.valueOf((String) box.getSelectedItem());
            }catch(NumberFormatException e){
               JOptionPane.showMessageDialog(null,
                       "Display Aborted, Type declared mismatch",
                       "Declaration Error",0,null);
            }

         default:
            JOptionPane.showMessageDialog(null,
                    "You wished to Abort, Value 0 is given to the variable",
                    "Exit", 0, null);
      }
      return varInt;
   }
   public static int boxInt(String prompt, String icon)
   // method overloaded use for adding images
   {   //returns a Wrapper Classes Integer value
      // load image from path
      ImageIcon image = new ImageIcon(icon);

      //The return value is initially declared to 0
      int varInt = 0;

      int index = JOptionPane.showConfirmDialog(null,box,prompt,
              JOptionPane.YES_NO_OPTION,
              JOptionPane.INFORMATION_MESSAGE,image);
      //user is prompted to select a String value from the given inside the combo box

      //Program checks which button user pressed using switch statements
      //case 0 is assigned to yes which returns the selected value
      //anything other than yes would result in program displaying an error message and assigns a default value
      if (index == 0) {
         try {
            varInt = Integer.valueOf((String) box.getSelectedItem());
         } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Display Aborted, Type declared mismatch",
                    "Declaration Error", 0, image);
         }
      } else {
         JOptionPane.showMessageDialog(null,
                 "You wished to Abort, Value 0 is given to the variable",
                 "Exit", 0, null);
      }
      return varInt;
   }
   public static double boxDouble(String prompt)
   {   //returns a Wrapper Classes Integer value
      //The return value is intially declared to 0
      double varDouble = 0;

      int index = JOptionPane.showConfirmDialog(null,box,prompt,
              JOptionPane.YES_NO_OPTION,
              JOptionPane.INFORMATION_MESSAGE,null);
      //user is prompted to select a String value from the given inside the combo box

      //Program checks which button user pressed using switch statements
      //case 0 is assigned to yes which returns the selected value
      //anything other than yes would result in program displaying an error message and assigns a default value
      switch(index) {
         case 0:
            varDouble = Double.valueOf((String) box.getSelectedItem());
            break;
         default:
            JOptionPane.showMessageDialog(null,
                    "You wished to Abort, Value 0 is given to the variable",
                    "Exit", 0, null);
      }
      return varDouble;
   }
   

} // IO class

