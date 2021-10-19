/*
 * Book Club Group Organizer 
 * End User: English Teachers 
 * Description: 
 *  This program was developed to help create book club groups for English teachers
 * Authors: Erin Zhou & Sana Ahmed 
 * Date: 18 January 2021
 */ 

// ------------------------------------------------------------------------------------------------------------------//

// Importing toolkits & methods 
import javax.swing.*; 
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*; 
import java.io.*;
import java.util.Scanner; 

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////// PROGRAM ////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class BookClubGroupOrganizer extends JFrame implements ActionListener{ 
  // Extends JFrame to set up GUI
  // Implements ActionListener to know when buttons are clicked 
  
  /////////////////////////////////////////////////// GUI VARIABLES ///////////////////////////////////////////////////
  
  // - GENERAL GUI VARIABLES - //
  JPanel panel = new JPanel(); // (Goes onto the JFrame) JPanel container to store GUI components 
  
  // Layouts // 
  FlowLayout layout1 = new FlowLayout(); 
  BoxLayout layout2 = new BoxLayout(panel, BoxLayout.Y_AXIS); // Box Layout for complete vertical layout
  
  // Buttons //
  JButton okButton = new JButton("OK"); // Ok button for any confirmations 
  
  JButton nextButton = new JButton("Next"); // Next button for going to the next page/screen 
  
  // Errors //
  JLabel typeOptionError = new JLabel("Please type something."); // When nothing is entered in JTextFields
  
  JLabel repeatedError = new JLabel("Option aready entered!"); // When book/student/etc. has already been entered 
  
  // Arrays //
  // Details: Multidimensional (2D) arrays are like tables for elements. They are arrays within an array. 
  //          Ex. String[][] array = {{"A","B","C"}, {"1", "2, "3"}};
  //              "B" has the index of array[0][1]
  //              "3" has the index of array[1][2]
  static String finalizedGroups[][]; // This array stores the finalized book club groups 
  static String studentPicks[][]; // This array stores the student names and their top 3 choices 
  
  // --------------------------------------------------------------------------------------------------------------- //
  
  // - PAGE-SPECIFIC VARIABLES - //
  
  // - Title Page - //
  // JLabels // 
  JLabel programTitle = new JLabel("Book Club Group Organizer"); // Title
  JLabel description = new JLabel("This program will help organize students into book club groups."); // Description of this page
  
  JLabel numStudentBook = new JLabel("List the number of books and students."); // Asking for data 
  JLabel numBook = new JLabel("Books:"); // Label for JTextField book
  JLabel numStudent = new JLabel("Students:"); // Label for JTextField student 
  JLabel maxStudentGroup = new JLabel("Max Number of Students in Each Group:"); // Label for JTextField maxGroupNum
  
  // JTextFields // 
  JTextField student = new JTextField(); // To get # of students 
  JTextField book = new JTextField(); // To get # of books 
  JTextField maxGroupNum = new JTextField(); // To get the max # of students there can be in a group 
  
  // Error Messages // 
  JLabel errorMessage = new JLabel("Please type numbers in the boxes."); // Error message to tell user to enter only numbers 
  
  JLabel inaccurateNumbersError = new JLabel("Enter a greater number of students or a higher max number!"); // The inputted numbers aren't reasonable (won't make proper groups) 
  
  // Other Variables // 
  
  // Strings to store the data entered into the JTextFields
  String bookStrInput = ""; 
  String studentStrInput = ""; 
  String maxGroupStrInput = ""; 
  
  int bookNumInput, studentNumInput, maxGroupNumInput; // Integers to store the data entered into the JTextFields which are converted from Strings 
  
  // --------------------------------------------------------
  
  // - File I/O or GUI Input Option Page - //
  // Details: Asking user to enter book names and asking the user if they will enter the data through a text file or the GUI 
  
  // Temporary Variables to Hold Data // 
  int bookCounter = 1; // To count the # of books entered/used 
  int studentCounter = 0; // To count the # of students entered/used 
  String tempBookNames = ""; // A temporary string to hold a list of book names 
  
  // JLabels // 
  JLabel inputChoiceTitle = new JLabel("Data Input Choice"); // Title 
  JLabel descriptionInputChoice = new JLabel("Would you like to input book and student information through a text file or by using this interface?"); // Description 
  
  JLabel bookNamesLabel = new JLabel("Please input the names of all " + "books:"); // Label to enter book 
  JLabel bookNumberCount = new JLabel("Book " + bookCounter + ":"); // The book # they are entering 
  
  // JButtons // 
  JButton fileIOChoice = new JButton("Text File"); // Button to pick text file input methods 
  JButton guiChoice = new JButton("Interface"); // Button to pick GUI input methods 
  
  // JTextFields // 
  JTextField bookNamesInput = new JTextField(10); // To get the book names 
  
  JLabel bookNamesList = new JLabel(""); // To show which books were entered 
  
  // --------------------------------------------------------
  
  // - General Option Variables  - // 
  // Details: Will be used in both GUI and File Input 
  // JButtons // 
  JButton optionAssign = new JButton("Assign"); // Option to directly assign students to groups 
  JButton optionTop3 = new JButton("Top 3"); // Option to get the top 3 choices from students 
  
  JLabel optionInputDescription = new JLabel("Would you like to assign students to ready-made groups or make groups according to the students' top 3 picks?"); // Description for the buttons 
  
  // --------------------------------------------------------
  
  // - GUI Input Option Page - //
  // Details: Will ask if user wants 'Assign' or 'Top 3' 
  JLabel guiChoiceTitle = new JLabel("GUI Input Choice"); // Title 
  
  // --------------------------------------------------------
  
  // - General GUI Variables - // 
  
  JLabel guiStudentList = new JLabel(""); // Shows list of students later 
  int groupCounter = 1; // To count the # of groups entered/used 
  String tempStudentNames = ""; // To hold list of student names
  
  // --------------------------------------------------------
  
  // - GUI Assign Input Page - // 
  
  JLabel guiAssignTitle = new JLabel("Assigning Through Interface"); // Title 
  JLabel guiAssignDescription = new JLabel("Assign students to each group."); // Description 
  
  JLabel guiAssignBookName = new JLabel(""); // To show book name later 
  
  JTextField studentAInput = new JTextField("Enter 0 to go to the next book"); // To get student name 
  
  // --------------------------------------------------------
  
  // - GUI Top 3 - // 
  
  // JLabels // 
  JLabel guiTop3Title = new JLabel("Top 3 Through Interface"); // Title 
  JLabel guiTop3Description = new JLabel("Enter student name and their 3 book choices:"); // Description 
  
  // To Get Data // 
  JLabel guiTop3SLabel = new JLabel("Enter student name:"); // Label for student name 
  JTextField guiTop3Student = new JTextField(); // To get data 
  
  JLabel guiTop3B1Label = new JLabel("Enter 1st choice:"); // Label for the 1st book choice 
  JTextField guiTop3B1 = new JTextField(); // To get data 
  
  JLabel guiTop3B2Label = new JLabel("Enter 2nd choice:"); // Label for the 2nd book choice 
  JTextField guiTop3B2 = new JTextField(); // To get data 
  
  JLabel guiTop3B3Label = new JLabel("Enter 3rd choice:"); // Label for the 3rd book choice 
  JTextField guiTop3B3 = new JTextField(); // To get data 
  
// --------------------------------------------------------
  
  // - File Options - // 
  JLabel fileChoiceTitle = new JLabel("Text File Choice"); // Title for file option (assign/top 3)
  
  // --------------------------------------------------------
  
  // - General File Variables - //
  JLabel fileInputLabel = new JLabel("Enter file name without extension:"); // Label asking for file name 
  
  JTextField fileInputFileName = new JTextField("Ex. BookClubGroups", 10); // To get file name 
  
  static File data = new File("EmptyFileObject"); // Making empty file object for later use 
  
  JLabel fileError = new JLabel("Please enter a proper file!"); // Error for not entering a file that is usable 
  JLabel bookMissingError = new JLabel("The book names are wrong!"); // Error for not formatting the file right
  
  Scanner scanner; // Empty scanner object for later use 
  
  // --------------------------------------------------------
  
  // - File Assign - // 
  
  JLabel fileAssignTitle = new JLabel("Assigning Through Text File"); // Title 
  JLabel fileAssignDescription = new JLabel("<html>The groups in the file should be listed like this:<br/>Book Name<br/>Student 1<br/>Student 2<br/> Student 3<br/> ... </html>");
  // Showing user how to format the text file  
  // <html> (html tag) allows JLabel to be formatted in a certain way 
  // <br/> means that the text following it will be on another line 
  
  // --------------------------------------------------------
  
  // - File Top 3 - // 
  JLabel fileTop3Title = new JLabel("Top 3 Through Text File"); // Title 
  JLabel fileTop3Description = new JLabel("<html>The students in the file should be listed like this:<br/>Student 1<br/>Choice 1<br/>Choice 2<br/> Choice 3<br/> ... </html>");
  // Showing how to format text file 
  
  // --------------------------------------------------------
  
  // - Final - //  
  // Details: Where the finalized data of student groups are located + table of data 
  
  JLabel tableTitle = new JLabel("Book Club Groups"); // Title 
  
  JLabel table = new JLabel(""); // Empty label to hold the table later 
  
  // Replace Books // 
  JLabel replaceBookLabel = new JLabel("Replace book:"); // Replace Books label 
  JLabel withBookLabel = new JLabel("With:"); // Replace books label 
  
  JTextField replaceBook = new JTextField(); // Get which book to replace 
  JTextField withBook = new JTextField(); // Get replacement 
  
  // Replace Students // 
  
  JLabel replaceStudent = new JLabel("Replace __"); // Shows which student that it will replace 
  JLabel withStudentLabel = new JLabel("With:"); // Replace student label 
  JTextField withStudent = new JTextField(); // Get the replacement 
  JButton okStudentButton = new JButton("OK"); // Confirm for student replacement 
  
  // Export // 
  JLabel exportLabel = new JLabel("Export to text file:"); // Label to export 
  JButton export = new JButton("Export"); // Button to export a text file 
  
  
  //////////////////////////////////////////////// CONSTRUCTOR METHOD /////////////////////////////////////////////////
  public BookClubGroupOrganizer() {
    
    // Frame Variables // 
    setTitle("Book Club Group Organizer"); // Set title 
    setSize(580, 500); // Set size 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // How to close the GUI
    
    introScreen(); // Adds intro page to the frame when the GUI first opens 
    
    // JButtons // 
    okButton.addActionListener(this); // Adds action listener to OK button 
    nextButton.addActionListener(this); // Adds action listener to next button 
    
    optionAssign.addActionListener(this); // Adds action listener to option assign button 
    optionTop3.addActionListener(this); // Adds action listener to option top 3 button 
    
    // Panel // 
    add(panel); // Adding panel to the frame so the GUI shows components 
    
    // Other // 
    setVisible(true); // Making the frame visible 
    
  }
  
  /////////////////////////////////////////////////// GUI FRAMES //////////////////////////////////////////////////////
  
  public void introScreen() { // This intro (beginning) page 
    
    panel.setLayout(layout2); // Setting it to Box Layout 
    
    programTitle.setFont(programTitle.getFont().deriveFont(40.0f)); // Setting title page font 
    
    okButton.setActionCommand("Continue"); // Setting the intro screen ok button command 
    
    // Errors // 
    // Details: Setting them as invisible until they occur 
    errorMessage.setVisible(false);
    inaccurateNumbersError.setVisible(false);
    
    // Adding to Panel // (so that the objects wil show up on GUI)
    panel.add(programTitle);
    panel.add(description); 
    panel.add(numStudentBook);
    panel.add(numBook);
    panel.add(book);
    panel.add(numStudent);
    panel.add(student); 
    panel.add(maxStudentGroup);
    panel.add(maxGroupNum);
    panel.add(okButton);
    panel.add(errorMessage);
    panel.add(inaccurateNumbersError);
  }
  
  // --------------------------------------------------------------------------------------------------------------- //
  
  public void dataInputOption() {
    // Asking if user wants to do file IO or GUI input 
    
    panel.setLayout(layout1); // Setting to flow layout 
    
    inputChoiceTitle.setFont(inputChoiceTitle.getFont().deriveFont(32.0f)); // Setting title font size 
    
    // JButtons // 
    fileIOChoice.addActionListener(this); // Adding action listener to this button 
    fileIOChoice.setActionCommand("Text File"); // setting action command for button 
    
    guiChoice.addActionListener(this); // Adding action listener to this button 
    guiChoice.setActionCommand("Interface"); // setting action command for button
    
    okButton.setActionCommand("OKBookNames"); // setting action command for ok button of this page 
    
    // Errors // 
    typeOptionError.setVisible(false);
    repeatedError.setVisible(false);
    
    // Adding to Panel // 
    panel.add(inputChoiceTitle);
    panel.add(bookNamesLabel);
    panel.add(bookNumberCount);
    panel.add(bookNamesInput);
    panel.add(okButton);
    panel.add(bookNamesList);
    panel.add(repeatedError);
    panel.add(typeOptionError);
    
  }
  
  // --------------------------------------------------------------------------------------------------------------- //
  
  public void guiInputOption() { // Assign students to groups or students' top 3 picks 
    panel.setLayout(layout1);  // Setting flow layout 
    
    guiChoiceTitle.setFont(guiChoiceTitle.getFont().deriveFont(32.0f)); // setting title font size 
    
    optionAssign.setActionCommand("GUIAssign"); // setting action command for button
    
    optionTop3.setActionCommand("GUITop3"); // setting action command for button
    
    // Adding to Panel // 
    panel.add(guiChoiceTitle);
    panel.add(optionInputDescription);
    panel.add(optionAssign);
    panel.add(optionTop3);
    
  }
  
  // --------------------------------------------------------------------------------------------------------------- //
  
  public void guiInputAssign() {// get list of books and students 
    panel.setLayout(layout2); // Setting box layout 
    
    guiAssignTitle.setFont(guiAssignTitle.getFont().deriveFont(32.0f)); // setting font size 
    
    okButton.setActionCommand("OKGUIAssign"); // setting action command 
    
    nextButton.setActionCommand("NextGUIAssign"); // setting action command 
    
    guiAssignBookName.setText("Book: " + finalizedGroups[0][0]); // Putting book name to first book in array 
    
    // Error // 
    typeOptionError.setVisible(false);
    
    // Adding to Panel // 
    panel.add(guiAssignTitle);
    panel.add(guiAssignDescription);
    panel.add(guiAssignBookName);
    panel.add(studentAInput);
    panel.add(okButton);
    panel.add(typeOptionError);
    panel.add(guiStudentList);
    panel.add(nextButton);
  }
  
  // --------------------------------------------------------------------------------------------------------------- //
  
  public void guiInputTop3() {
    panel.setLayout(layout2); // set box layout 
    
    guiTop3Title.setFont(guiTop3Title.getFont().deriveFont(32.0f)); // set title font size 
    
    // Errors // 
    typeOptionError.setVisible(false);
    bookMissingError.setVisible(false);
    repeatedError.setVisible(false);
    
    // Button // 
    okButton.setActionCommand("okGUITop3"); // setting action command for ok button 
    
    // adding to Panel // 
    panel.add(guiTop3Title);
    panel.add(bookNamesList);
    panel.add(guiTop3Description);
    panel.add(guiTop3SLabel);
    panel.add(guiTop3Student);
    panel.add(guiTop3B1Label); 
    panel.add(guiTop3B1);
    panel.add(guiTop3B2Label);
    panel.add(guiTop3B2);
    panel.add(guiTop3B3Label);
    panel.add(guiTop3B3);
    panel.add(okButton);
    panel.add(guiStudentList);
    panel.add(typeOptionError);
    panel.add(repeatedError);
    panel.add(bookMissingError);
    
  }
  
  // --------------------------------------------------------------------------------------------------------------- //
  
  public void fileOptions() { // assign students to groups or students' top 3 picks 
    panel.setLayout(layout1); // setting flow layout 
    
    fileChoiceTitle.setFont(fileChoiceTitle.getFont().deriveFont(32.0f)); // font size 
    
    optionAssign.setActionCommand("FileAssign"); // button set action command 
    optionTop3.setActionCommand("FileTop3"); // button set action command 
    
    // adding // 
    panel.add(fileChoiceTitle);
    panel.add(optionInputDescription);
    panel.add(optionAssign);
    panel.add(optionTop3);
  }
  
  // --------------------------------------------------------------------------------------------------------------- //
  
  public void fileInputAssign() { // making groups through text file 
    panel.setLayout(layout2); // box layout 
    
    fileAssignTitle.setFont(fileAssignTitle.getFont().deriveFont(32.0f)); // font size 
    
    okButton.setActionCommand("okFileAssign"); // set specific action command 
    
    // Errors // 
    fileError.setVisible(false); 
    bookMissingError.setVisible(false);
    typeOptionError.setVisible(false);
    
    // Adding // 
    panel.add(fileAssignTitle);
    panel.add(fileAssignDescription);
    panel.add(bookNamesList);
    panel.add(fileInputLabel);
    panel.add(fileInputFileName);
    panel.add(okButton);
    panel.add(fileError);
    panel.add(typeOptionError);
    panel.add(bookMissingError);
  }
  
  // --------------------------------------------------------------------------------------------------------------- //
  
  public void fileInputTop3() { // Top 3 book choices through text file 
    panel.setLayout(layout2); // box layout 
    
    fileTop3Title.setFont(fileTop3Title.getFont().deriveFont(32.0f)); // font size 
    
    // Errors // 
    typeOptionError.setVisible(false);
    fileError.setVisible(false);
    bookMissingError.setVisible(false);
    repeatedError.setVisible(false);
    
    okButton.setActionCommand("okFileTop3"); // set specific action command 
    
    // adding // 
    panel.add(fileTop3Title);
    panel.add(fileTop3Description);
    panel.add(fileInputFileName);
    panel.add(okButton);
    panel.add(typeOptionError);
    panel.add(fileError);
    panel.add(bookMissingError);
    panel.add(repeatedError);
    
  }
  
  // --------------------------------------------------------------------------------------------------------------- //
  
  public void finalPage() { // the final page where the book club groups are displayed 
    panel.setLayout(layout2); // box layout 
    
    tableTitle.setFont(tableTitle.getFont().deriveFont(40.0f)); // font size 
    
    // JButtons // 
    okButton.setActionCommand("okFinal"); // set specific action command 
    nextButton.setActionCommand("nextFinal"); // set specific action command 
    
    okStudentButton.addActionListener(this); // adding action listener to this new button 
    okStudentButton.setActionCommand("okStudentButton"); // set specific action command 
    
    export.addActionListener(this); // adding action listener to this new button 
    export.setActionCommand("Export"); // set specific action command 
    
    // Error // 
    typeOptionError.setVisible(false);
    
    // Table // 
    String tempString = makeTable(finalizedGroups); // Using tempString to contain table 
    table.setText(tempString); // puttint it in JLabel to show on GUI
    
    // adding // 
    panel.add(tableTitle);
    panel.add(table);
    panel.add(replaceBookLabel);
    panel.add(replaceBook);
    panel.add(withBookLabel);
    panel.add(withBook);
    panel.add(okButton);
    panel.add(typeOptionError);
    panel.add(replaceStudent);
    panel.add(nextButton);
    panel.add(withStudentLabel);
    panel.add(withStudent);
    panel.add(okStudentButton);
    panel.add(exportLabel);
    panel.add(export);
    
  }
  ////////////////////////////////////////////////// OTHER METHODS ///////////////////////////////////////////////////
  // They complete some work 
  
  ////////// - Shorter Methods - //////////
  public static boolean isNumeric(String str) { // Checks if a string is numeric 
    double num;
    try { // trying to see if it can be converted to a double 
      num = Double.parseDouble(str); 
      return true; // if it can, it 'true' will be returned 
    }
    catch (NumberFormatException e){ // if it can't, an error will show up 
      return false; // Will return 'false' 
    }
  }
  
  // --------------------------------------------------------
  
  public void removePanelElements() { // removing all elements from a panel 
    panel.removeAll(); // removes all elements from the container 
    refreshPanel();
  }
  
  // --------------------------------------------------------
  
  public void refreshPanel() { // refreshing the panel 
    panel.repaint(); // this goes through the panel and makes changes if variables were made 
    panel.revalidate(); // this goes through the panel to make sure everything is in the correct order and allows the panel to be changed again
  }
  
  // --------------------------------------------------------
  
  public boolean fileContains(Scanner scanner, File data) throws Exception { // checks if file contains anything 
    if (scanner.hasNext()) { // if it does, true is returned 
      return true;
    }
    else { // if not, false is returned 
      return false;
    }
  }
  
  // --------------------------------------------------------
  
  public boolean alreadyExists(String name, int max, String[][] array){ // checks if the variable already exists in the array 
    for (int i = 0; i < max; i++){ // goes through the array 
      if (name.equals(array[i][0])) { // checks if it matches with any elements of the array 
        return true; // if it does, returns true 
      }
    }
    return false; // else, returns false 
  }
  
  // --------------------------------------------------------
  
  public int searchPosition1(String tempString, int max, String[][] array){ // searches through the row index to find something
    int found = 0;
    for (int j = 0; j < max; j++) { // looping through 
      if (array[j][0].equals(tempString)){ // if element matches variable 
        found = j; // found recieves the row index 
        break;
      }
    }
    return found; // returns found 
  }
  
  // --------------------------------------------------------
  
  public void removeArrayElements(String[][] array, int pos1Max, int pos2Max, int startingNum) { // removes elements from element 
    for (int i = 0; i < pos1Max; i++) { // max of row index 
      for (int j = startingNum; j < pos2Max; j++){ // max of column index 
        array[i][j] = null;
      }
    }
    
  }
  
  // --------------------------------------------------------
  
  
  public int searchPosition2(String tempString, int pos1, int max, String[][] array){ // searching through column index 
    int found = 0; 
    for (int i = 0; i < max; i++){ // looping through array 
      if (tempString != null) { // if it is not null, found gets that position that matches with the string 
        if (array[pos1][i].equals(tempString)){
          found = i;
          break;
        }
      }
      else {
        if (array[pos1][i] == null) { // checks for null positions 
          found = i;
          break;
        }
      }
    }
    return found;
  }
  // --------------------------------------------------------
  
  public boolean addToArray(String tempString, int found, int index, String empty, String[][] array, String[][] list, int i, boolean nextBook) { // adds an element to an array 
    if (array[found][maxGroupNumInput] == null) { // makes sure this row has room 
      index = searchPosition2(empty, found, (maxGroupNumInput + 1), array); // searches for null position 
      array[found][index] = list[i][0]; // adds element to array 
      nextBook = false; // no need to search through the next book (row) 
    }
    else { // if none are null 
      nextBook = true; // need to search through next row to add the element 
    }
    return nextBook;
  } 
  
  // --------------------------------------------------------
  
  public void exportFile(String[][] array) throws Exception{ // making a table in text file 
    String string = ""; // empty string 
    PrintWriter output = new PrintWriter("BookClubGroupTable.txt"); // making file writer 
    for (int i = 0; i < bookNumInput; i++) { // goes through finalized group array 
      string += array[i][0] + " || "; // for every book name, it divides it from student names 
      for (int j = 1; j <= maxGroupNumInput; j++){ // looping through the elements in each row 
        if (array[i][j] != null) {
          if (j == 1) { // if it is the first name of the row, it doesn't have a comma in front 
            string += array[i][j]; 
          }
          else { // if it isn't the first, it will have a comma in front 
            string += ", " + array[i][j];
          }
        }
      }
      string += "\n------------------------------------------------------------\n"; // divides the groups 
    }
    output.println(string); // printing the string 
    output.close(); // closing the print writer 
  }
  
  // --------------------------------------------------------
  
  public void changingFileDataToArray(Scanner scanner, String[][] array) { // changes file assign data to final array 
    
    // Variables // 
    String tempString = "";  // empty string to hold scanned data 
    int found = 0; // to find row index 
    int bookCounter = 0; // to keep track of the # of books  
    int studentCounter = 0; // to keep track of the # of students 
    int totalStudent = 0; // to keep track of the total # of students 
    boolean bookName = false; // to find out if the current string is a book name or a student name 
    
    scanner.reset(); // making sure that the scanner is at the top of the file 
    while (scanner.hasNext()) { // as long as the file has more text 
      tempString = scanner.nextLine(); // getting text 
      bookName = false; // making book name false for every iteration  
      
      if (!tempString.isEmpty()){ // making sure string isn't empty 
        for (int i = 0; i < bookNumInput; i++) { // looping for final array to find book name 
          if (array[i][0].equals(tempString)){ // this tells if the string is a book name or not 
            bookName = true;
            found = i; // row index is gotten 
            bookCounter++; // counter goes up 
            studentCounter = 0; // every book, the student counter must be 0 
            break;
          }
        }
        if (bookName == false) { // if it is a student name 
          if (studentCounter >= maxGroupNumInput){ // making sure the student counter doesn't go over max group num 
            studentCounter = 0;
          }
          studentCounter++; // adding another student 
          totalStudent++; // adding student to total 
          array[found][studentCounter] = tempString; // assigning tempString to certain position in array 
          
        }
      }
    }
    
    if (bookCounter < bookNumInput || totalStudent > studentNumInput || totalStudent < studentNumInput) {
      // if the # of books is too low or the # of students is too much or little 
      bookMissingError.setVisible(true); // making error labels visible 
      fileError.setVisible(true);
      
      removeArrayElements(finalizedGroups, bookNumInput, (maxGroupNumInput + 1), 1); // removing array elements that contain student names 
      refreshPanel();
    }
    else { // if everything is perfect 
      removePanelElements();
      finalPage(); // displaying the groups in final page 
      refreshPanel();
    }
  }
  
  // --------------------------------------------------------
  
  public void fileSDataToArray(Scanner scanner, String[][] array, String[][] list) { // this gets the data from top 3 student picks and turns it into an array 
    
    String tempString = ""; // temp string to store scanned data 
    int totalBooks = 0; // keeping track of total # of books 
    int studentCounter = 0; // keep track of student #
    int bookCounter = 1; // keep track of book #
    boolean bookName = false; // if the data being scanned is a book name or a student name 
    
    while (scanner.hasNext()) { // loops while data can still be scanned 
      tempString = scanner.nextLine(); // gets scanned data 
      bookName = false; // makes it false for every iteration 
      
      typeOptionError.setVisible(false); // making error message invisible 
      
      for (int i = 0; i < bookNumInput; i++) { // looping through book names array 
        if (array[i][0].equals(tempString)){ // if it is a book name 
          
          bookMissingError.setVisible(false); // making errors message invisible 
          
          list[studentCounter][bookCounter] = tempString; // adding string to array 

          bookCounter++; // increasing # of books (of total of 3 per student)
          totalBooks++; // increasing total # of books 
          
          bookName = true; // this is a book name 
          break;
        }
      }
      
      if (bookCounter > 3) { // making sure that the book counter doesn't exceed 3 (max amount per student) 
        studentCounter++; // once the three choices have been input, it goes to another student (add 1 more to student counter) 
        bookCounter = 1; // book counter needs to be reset for each student 
      }
      
      if (bookName == false) { // if it is a student name 
        if (!alreadyExists(tempString, studentNumInput, studentPicks)) { // making sure student doesn't already exist 
          
          // Making error messages invisible // 
          bookMissingError.setVisible(false);
          typeOptionError.setVisible(false);
          repeatedError.setVisible(false);
          
          list[studentCounter][0] = tempString; // adding student name to array 

          if (studentCounter >= studentNumInput) { // if all students have been added 
            break;
          }
        }
        else { // if it does already exist, error message pops up 
          repeatedError.setVisible(true);
          removeArrayElements(studentPicks, studentNumInput, 4, 0); // resetting array 
          break;
        }
      }
    }
    
    if (totalBooks < (studentNumInput*3) || totalBooks > (studentNumInput*3)) { // making sure the amount of books input isn't lower/higher than student # x 3 
      bookMissingError.setVisible(true); // if it is, error message is visible 
      removeArrayElements(studentPicks, studentNumInput, 4, 0); // resets array 
      refreshPanel();
    }
    
  }
  
  // --------------------------------------------------------
  
  public void listToArray(String list[][], String array[][]) { // turning top 3 student array to the finalized array 
    
    // Variables //  
    String tempString = ""; // temp string to hold array element 
    int found = 0; // geting row index 
    int index = 0; // getting column index 
    String empty = null; // making an empty string 
    boolean nextBook = false; // if need to go to the next book group 
    
    for (int i = 0; i <studentNumInput; i++) { // loops through student names 
      // Choice 1 // 
      tempString = list[i][1]; // gets the first book choice 
      found = searchPosition1(tempString, bookNumInput, finalizedGroups); // finding the index of the book name 
      nextBook = addToArray(tempString, found, index, empty, array, list, i, nextBook); // adding element to array
      // also checking to see if the element should be assigned to the next group instead (due to the group being full)
      
      // Choice 2 // 
      if (nextBook) { // if next book was true from the previous 
        tempString = list[i][2]; // get choice 2 
        found = searchPosition1(tempString, bookNumInput, finalizedGroups); // find position of choice 2 book 
        nextBook = addToArray(tempString, found, index, empty, array, list, i, nextBook); // adding element and checking next book
        
        // Choice 3 // 
        if (nextBook) { // if choice 2 was full 
          tempString = list[i][3]; // get choice 3 
          found = searchPosition1(tempString, bookNumInput, finalizedGroups); // find position of choice 3 book 
          nextBook = addToArray(tempString, found, index, empty, array, list, i, nextBook); // adding element and checking next book

          // Random Assign //
          if (nextBook) { // if all choice book groups were full 
            for (int j = 0; j < bookNumInput; j++) { // loop through array 
              if (array[j][maxGroupNumInput] == null) { // find an empty position 
                found = j; // get the index of empty position 
                nextBook = addToArray(tempString, found, index, empty, array, list, i, nextBook); // add element 
                break;
              }
            }
          }
        }
      }
    }
  }
  
  // --------------------------------------------------------
  public String makeTable(String[][] array) { // making the final table 
    String string = "<html>"; // adding html tag to make sure table is formatted right 
    for (int i = 0; i < bookNumInput; i++) { // looping through book names 
      string += array[i][0] + " || "; // adding book name to string 
      for (int j = 1; j <= maxGroupNumInput; j++){ // looping through student names in each group 
        if (array[i][j] != null) { // if the element is not null - if it is null, it will not be displayed 
          if (j == 1) { // if it is the first name, it will have no comma 
            string += array[i][j];
          }
          else { // if not first, it will have a comma 
            string += ", " + array[i][j];
          }
        }
      }
      string += "<br/>------------------------------------------------------------<br/>"; // dividing each group 
    }
    string += "</html>"; // to end the string 
    return string; 
  }
  
  // --------------------------------------------------------------------------------------------------------------- //
  
  /////////////////////////////////////////// - ACTION LISTENER - /////////////////////////////////////////////////////
  
  public void actionPerformed(ActionEvent event) { // this is the action listener method that catches all actions performed 
    
    // - GENERAL ACTION LISTENER VARIABLES - // 
    String command = event.getActionCommand(); // getting the action performed 
    
    String tempString = ""; // a temporary string that is used a lot 
    String tempString2 = ""; // other temporary strings 
    String tempString3 = ""; // other temporary strings 
    
    int tempInt; // a temporary integer that will be used 
    
    
    // --------------------------------------------------------
    
    // - Title page - //
    
    // booleans to check if strings are numeric // 
    boolean bookNumeric = false;
    boolean studentNumeric = false; 
    boolean maxGroupNumeric = false; 
    
    // doubles for calculations // 
    double tempDouble;
    double tempDouble1;
    double tempDouble2;
    
    if (command.equals("Continue")) { // if ok button was pressed  
      // Strings get data // 
      bookStrInput = book.getText(); 
      studentStrInput = student.getText(); 
      maxGroupStrInput = maxGroupNum.getText();
      
      // Checking if they are numeric // 
      bookNumeric = isNumeric(bookStrInput);
      studentNumeric = isNumeric(studentStrInput);
      maxGroupNumeric = isNumeric(maxGroupStrInput);
      
      try { // trying to parse each string into an integer 
        bookNumInput = Integer.parseInt(bookStrInput);
        studentNumInput = Integer.parseInt(studentStrInput);
        maxGroupNumInput = Integer.parseInt(maxGroupStrInput);
        
      }
      catch (NumberFormatException e) { // if it doesn't work, an error message will appear 
        errorMessage.setVisible(true);
        refreshPanel();
      }
      // Calculations with temp doubles // 
      tempDouble1 = studentNumInput;
      tempDouble2 = bookNumInput;
      tempDouble = Math.round(tempDouble1/tempDouble2);

      if (studentNumInput < bookNumInput || maxGroupNumInput < tempDouble) { // if there are too many/less students 
        inaccurateNumbersError.setVisible(true); // error message shows up 
        refreshPanel();
      }
      
      else if (!bookStrInput.isEmpty() && !studentStrInput.isEmpty() && !maxGroupStrInput.isEmpty() && bookNumeric && studentNumeric && maxGroupNumeric){
        // if the numbers are correct:
        // Array Creation // 
        finalizedGroups = new String[bookNumInput][maxGroupNumInput + 1]; // creating final group array 
        studentPicks = new String[studentNumInput][4]; // creating student's top 3 picks array 
        removePanelElements();
        dataInputOption(); // going to next page 
      }
    }

    // --------------------------------------------------------
    
    // - File IO or GUI Choice - //
    
    if (bookCounter < (bookNumInput + 1) && command.equals("OKBookNames")){ // if ok button was pressed and bookCounter is not complete 
      tempString = bookNamesInput.getText(); // get name 

      if (tempString.isEmpty()) { // if it is empty, error message 
        typeOptionError.setVisible(true);
        refreshPanel();
      }
      else { // if it not empty 
        typeOptionError.setVisible(false); // error message is invisible 
        
        if (!alreadyExists(tempString, bookNumInput, finalizedGroups)){ // if it was not repeated (name already exists) 
          repeatedError.setVisible(false); // no error message 
          if (bookCounter == 1) { // if book counter is 1, no comma to temp list of books 
            tempBookNames += tempString;
          }
          else { // if not 1, there is a comma 
            tempBookNames += ", " + tempString;
          }
          
          bookNamesList.setText("<html>Books:<br/>" + tempBookNames + "</html>"); // setting label to list of books so user can see what they have input 
          
          bookCounter++; // increasing book counter for book entered 
          finalizedGroups[bookCounter - 2][0] = tempString; // adding book name to final array 
          
          if (bookCounter < bookNumInput + 1){ // as long as the book counter is below max book num, 
            bookNumberCount.setText("Book " + bookCounter); // display which book # is being added 
          }
          refreshPanel();
        }
        else { // if it was repeated, error shows up 
          repeatedError.setVisible(true);
        }
      }
      if (finalizedGroups[bookNumInput - 1][0] != null){ // if all book names were filled in
        // Displaying GUI or File Input Choices // 
        panel.add(descriptionInputChoice); 
        panel.add(fileIOChoice);
        panel.add(guiChoice);
        refreshPanel();
      }
    }
    if (command.equals("Text File")) { // if they choose File I/O  button
      removePanelElements();
      fileOptions(); // show file options page 
      refreshPanel();
    }
    
    else if (command.equals("Interface")) { // if they choose GUI button
      removePanelElements();
      guiInputOption(); // show gui options page 
      refreshPanel();
    }
    
    // --------------------------------------------------------
    
    //- GUI Option - // 
    
    if (command.equals("GUIAssign")) { // if they choose assign button 
      bookCounter = 0; // reset book counter 
      removePanelElements();
      guiInputAssign(); // show gui assign page 
      refreshPanel();
    }
    else if (command.equals("GUITop3")) { // if they choose top 3 button 
      removePanelElements();
      guiInputTop3(); // show gui top 3 page 
      refreshPanel();
    }
    
    // --------------------------------------------------------
    
    // - GUI Assign - //
    
    if (command.equals("OKGUIAssign")) { // if gui assign ok button was pressed 
      tempString = studentAInput.getText(); // get student data 
      if (bookCounter == 0 && groupCounter == 1) { // if first book
        tempStudentNames += finalizedGroups[0][0] + ": "; // add book name to list 
      }
      if (tempString.isEmpty()) { // if string is empty, error message shows 
        typeOptionError.setVisible(true); 
        refreshPanel();
      }
      else if (bookCounter == (bookNumInput - 1) && groupCounter > maxGroupNumInput) { // if all students are entered 
        removePanelElements();
        finalPage(); // go to final page to display table 
        refreshPanel();
      }
      else if (tempString.equals("0") || groupCounter > maxGroupNumInput) { // if the user enters 0 to go to next list or group counter (# of students) reaches max 
        typeOptionError.setVisible(false);
        bookCounter++; // new book group 
        groupCounter = 1; // reset group counter (# of student) 
        tempStudentNames += "<br/>" + finalizedGroups[bookCounter][0] + ": "; // show book name 
        guiAssignBookName.setText("Book: " + finalizedGroups[bookCounter][0]); // Show which book the students are being assigned to 
        refreshPanel(); 
      }
      else { // adding student names 
        typeOptionError.setVisible(false);
        
        if (groupCounter == 1) { // if 1, no comma 
          tempStudentNames += tempString;
        }
        else { // if not 1, yes comma 
          tempStudentNames += ", " + tempString;
        }
        guiStudentList.setText("<html>Students:<br/>" + tempStudentNames + "</html>"); // display list of input groups to user 
        finalizedGroups[bookCounter][groupCounter] = tempString; // add name to array 
        groupCounter++; // increasing group counter (# of students) 
        refreshPanel();
      }
    }
    
    // GUI Assign Next // if user presses 'next' button before all array spaces are filled up 
    if (command.equals("NextGUIAssign")) {
      removePanelElements();
      finalPage(); // go to final page and display table 
      refreshPanel();
    }
    
    
    // --------------------------------------------------------
    // - GUI TOP 3 - // 
    if (command.equals("okGUITop3")) { // if ok button pressed 
      
      tempString = guiTop3Student.getText(); // get data 
      
      if (!tempString.isEmpty()){ // if string is not empty 
        if (studentCounter >= studentNumInput) { // if all students were entered 
          removePanelElements();
          listToArray(studentPicks, finalizedGroups); // turn top 3 array to finalized array 
          finalPage(); // go to final page to display table 
          refreshPanel();
        }
        else if (!alreadyExists(tempString, studentNumInput, studentPicks)){ // if string was not a repeat 
          tempStudentNames += tempString + ": "; // list of students' choices + student name 
          
          studentPicks[studentCounter][0] = tempString; // adding student name to array 
          
          // Getting Book Choice Data //  
          tempString = guiTop3B1.getText();
          tempString2 = guiTop3B2.getText();
          tempString3 = guiTop3B3.getText();
          
          if (!tempString.isEmpty() && !tempString2.isEmpty() && !tempString3.isEmpty()){ // if no string is empty 
            if (tempString.equals(tempString2) || tempString.equals(tempString3) || tempString3.equals(tempString2)){ // if choices were repeated, error message shows 
              repeatedError.setVisible(true);
            }
            else if (alreadyExists(tempString, bookNumInput, finalizedGroups) && alreadyExists(tempString2, bookNumInput, finalizedGroups) && alreadyExists(tempString3, bookNumInput, finalizedGroups)){
              // Making sure book names are listed already in final array
              // everything is correct 
              
              // Error Message // they are invisible
              repeatedError.setVisible(false);
              bookMissingError.setVisible(false);
              typeOptionError.setVisible(false);
              
              // Add Choice 1 // 
              tempStudentNames += tempString; // adding to temporary list 
              studentPicks[studentCounter][1] = tempString; // adding to array 
              
              // Add Choice 2 // 
              tempStudentNames += ", " + tempString2;
              studentPicks[studentCounter][2] = tempString2;
              
              // Add Choice 3 // 
              tempStudentNames += ", " + tempString3 + " || ";
              studentPicks[studentCounter][3] = tempString3;
              
              guiStudentList.setText(tempStudentNames); // displaying temporary list of choices 
              refreshPanel();
              
              studentCounter++; // student counter increases 
            }
            else { // if they don't exist already, error message shows 
              bookMissingError.setVisible(true);
              refreshPanel();
            }
          }
          else { // if empty, show error message 
            typeOptionError.setVisible(true);
            refreshPanel();
          }
        }
        else { // if string was repeated, error message shows 
          repeatedError.setVisible(true);
          refreshPanel();
        }
      }
      else { // if string is empty, error message shows 
        typeOptionError.setVisible(true);
        refreshPanel();
      }
    }
    
    // --------------------------------------------------------
    
    // - File I/O Option - // 

    if (command.equals("FileAssign")) { // if file assign button was chosen 
      removePanelElements();
      fileInputAssign(); // going to file assign page 
      refreshPanel();
    }
    else if (command.equals("FileTop3")) { // if file top 3 button was chosen 
      removePanelElements();
      fileInputTop3(); // going to file top 3 page 
      refreshPanel();
    }
    
    // --------------------------------------------------------
    
    // - File Assign - //  
    
    if (command.equals("okFileAssign")) { // if ok button was pressed 
      tempString = fileInputFileName.getText(); // get file name 
      data = new File(tempString + ".txt"); // make file object 
      if (!tempString.isEmpty()) { // making sure temp string was not empty 
        try { // this is in replacement of "throws Exception" because ActionListener method cannot throw Exception
          scanner = new Scanner(data); // making new scanner 
          if (data.exists() && !data.isDirectory() && fileContains(scanner, data)){ // making sure file exists 
            // Errors // making them invisible if file is correct 
            fileError.setVisible(false);
            bookMissingError.setVisible(false);
            typeOptionError.setVisible(false);
            
            changingFileDataToArray(scanner, finalizedGroups); // getting file data and putting it into an array 
          }
        }
        catch (Exception e) { // if file doesn't exist or any other errors show up 
          fileError.setVisible(true);
          refreshPanel();
        }
      }
      else { // if it was empty, error message shows up
        typeOptionError.setVisible(true);
        refreshPanel();
      }
    }
    
    // --------------------------------------------------------
    
    // - File Top 3 - // 
    
    if (command.equals("okFileTop3")) { // ok button was pressed 
      tempString = fileInputFileName.getText(); // get file name 
      data = new File(tempString + ".txt"); // make file object 
      try { // replacement for throws Exception  
        scanner = new Scanner(data); // make scanner 
        if (data.exists() && !data.isDirectory() && fileContains(scanner, data)){ // if file exists 
          fileError.setVisible(false); // error message is invisible 
          fileSDataToArray(scanner, finalizedGroups, studentPicks); // getting file data and turning it into student's top 3 picks array 
        }
      }
      catch (Exception e) { // if any part of the file is wrong, error message shows up 
        fileError.setVisible(true);
        removeArrayElements(studentPicks, studentNumInput, 4, 0); // resets array so program can redo it 
        refreshPanel();
      }
      if (studentPicks[studentNumInput - 1][3] != null) { // if all spaces in array were filled 
        removePanelElements();
        listToArray(studentPicks, finalizedGroups); // change students' picks array to final group array 
        finalPage(); // go to final page and display table 
        refreshPanel();
      }
    }
    
    // --------------------------------------------------------
    
    // - Final Page - // 
    
    if (command.equals("okFinal")) { // if ok button was pressed 
      tempString = replaceBook.getText(); // get book replacement data 
      tempString2 = withBook.getText(); // get book replacement data 
      if (tempString.isEmpty() || tempString2.isEmpty()) { // if it is empty, error shows up 
        typeOptionError.setVisible(true);
        refreshPanel();
      }
      else { // if it is not empty: 
        typeOptionError.setVisible(false); // errors are invisible 
        
        tempInt = searchPosition1(tempString, bookNumInput, finalizedGroups); // search for position of book being replaced 
        finalizedGroups[tempInt][0] = tempString2; // replace book 
        finalPage(); // restart final page (remake table) 
        refreshPanel();
      }
    }
    
    if (command.equals("nextFinal")) { // if student next button is pressed 
      studentCounter++; // student number increases 
      
      if (studentCounter > maxGroupNumInput){ // if student number reaches max 
        studentCounter = 1; // reset counter 
        bookCounter++; // increase book num 
      }
      if (bookCounter >= bookNumInput) { // if book num reaches max 
        bookCounter = 0; // reset counter 
      }
      replaceStudent.setText("Replace " + finalizedGroups[bookCounter][0] + " [" + finalizedGroups[bookCounter][studentCounter] + "]" + ":"); // display what student is being replaced 
      refreshPanel();
    }
    if (command.equals("okStudentButton")){ // if student ok button was pressed 
      tempString = withStudent.getText(); // get replacement name 
      if (tempString.isEmpty()) { // if the string is empty 
        finalizedGroups[bookCounter][studentCounter] = null; // get rid of the element (nullify) 
        finalPage(); // refresh table 
        refreshPanel();
      }
      else { // if string is not empty 
        finalizedGroups[bookCounter][studentCounter] = tempString; // replace student with string replacement 
        finalPage(); // refresh table 
        refreshPanel();
      }
    }
    
    if (command.equals("Export")){ // if export button was pressed 
      try { // replacement of throws Exception 
        exportFile(finalizedGroups); // export table into text file 
      }
      catch (Exception e) { // if exception is somehow triggered, print to console 
        System.out.println("Exception triggered."); 
      }
    }
  }
  
  //////////////////////////////////////////////////// MAIN METHOD ////////////////////////////////////////////////////
  public static void main(String[] args) throws Exception {
    
    BookClubGroupOrganizer frame = new BookClubGroupOrganizer(); // starting GUI 
  }
}