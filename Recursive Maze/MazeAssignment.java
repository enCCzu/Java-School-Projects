/*
 * Description: This program solves any maze made with '#', '.', 'S', 'G' symbols. It outputs the correct path with '+'
 * Author: Erin Zhou
 * Teacher: Mr. Ho
 * Date: 18/10/21
 */ 

// Importing packages 
import java.util.Scanner; 
import java.io.BufferedReader;
import java.io.File; 
import java.io.FileReader;
import java.io.IOException;

class ZhouErinMazeAssignment {

  /**
   * Description: Asks user to repeatedly input a file name until the file is real and is not a directory 
   * @return the file name to be used with Buffered Reader 
   */
  public static String getFile() {

    // Initializing and/or declaring variables to get user input and store user input 
    Scanner scan = new Scanner(System.in); 
    String fileName; 
    File textFile; // creating file object to check if file exists 

    // loop to check if file exists and is not a folder 
    do {
      // Instructions to the user 
      System.out.println("Please enter the name of a real text file without the extension:"); 
      // getting user input 
      fileName = scan.nextLine(); 
      // declaring file object 
      textFile = new File(fileName + ".txt");
      
    } while (!textFile.exists() && !textFile.isDirectory()); // loops until file exists and is not a directory (aka a folder)
    
    // returns the file name 
    return fileName; 
  }


  /**
   * Description: This recursive method solves the maze and marks the correct path.
   * @param maze is the 2D array that stores the maze 
   * @param y is the index of the row. Marks the position of the computer in the maze. Using (x, y) as specified by the assignment document
   * @param x is the index of the column. Marks the position of the computer in the maze 
   * @return true if a solution is found, false if there is no solution 
   */
  public static boolean solveMaze(char[][] maze, int y, int x){
    
    // Checking to see if the computer has found the goal 
    if (maze[y][x] == 'G'){
      // If it has, then return true 
      return true; 
    }

    // if statement: makes sure the computer is making moves within the maze and that it is moving on an available path
    if (y > 0 && y < maze.length - 1 && x > 0 && x < maze[0].length - 1 && maze[y][x] == '.'){ 

      // this makes sure that it isn't going backwards for no reason and making weird moves (keeps the computer going forward down a path)
      if (maze[y][x] == '+'){ 
        return false; 
      }

      // marking the path where the computer has gone
      maze[y][x] = '+';  

      // - Recursively making the computer go north, east, south, or west - 
      // makes the computer attempt to go north (up)
      if (solveMaze(maze,  y - 1, x)) {
        return true; // returns true so that the previous position (previous recursive case) is completed
      }
      // makes the computer attempt to go east (right)
      if (solveMaze(maze,  y, x + 1)) {
        return true;
      }
      // makes the computer attempt to go south (down)
      if (solveMaze(maze,  y + 1, x)) {
        return true; 
      }
      // makes the computer attempt to go west (left)
      if (solveMaze(maze,  y, x - 1)) {
        return true; 
      }
      // BASE CASE to BACKTRACK - will be activated first if all 'if statements' above are false. 
      // If the computer meets a dead end, all previous recursions will 'unravel' and this backtracking will continue until it can go in a different direction)
      // Ex. if it hits a dead end going east, it will backtrack once, check the following if statements: south and west, and if it still cant move, then it will backtrack again, check south and west... etc. etc. 
      maze[y][x] = '.'; // removes current position from the solution path (marked with '+')
      return false; // this ends the current recursive case and returns false to show that the path the computer is taking is wrong so that the computer will not continue going in this direction 
    }
    // BASE CASE - 'Ultimate' base case 
    // This base case is activated if the computer made the wrong move (tries to move up (recursion happens), but there is no 'up' (if statement is false), so that move/recursive case hits this base case and returns false)
    // This is also the final base case activated if there is no solution 
    return false; 
  }
  
  public static void main(String[] args){
    
    // Getting file name from user and checking if the file is real 
    String fileName = getFile();
    
    // BufferedReader variables 
    BufferedReader sizeReader, mazeReader; 
    String currentLine;
    // Variables to declare the size of the maze array below 
    int row = 0; 
    int column = 0; 
    
    // Try and catch any possible IOException 
    try {

      // - Getting size of the maze -
      // Declaring a new buffered reader 
      sizeReader = new BufferedReader(new FileReader(fileName + ".txt"));

      // while loop to read through the whole text file 
      while ((currentLine = sizeReader.readLine()) != null) {
        // Getting how many columns (x value) the maze has 
        column = currentLine.length(); 
        // Counter to count how many rows the maze has (y value)
        row++; 
      }
      
      // Creating 2D array to store the maze
      char[][] maze = new char[row + 2][column + 2]; // (+2) is to prevent index out of bounds exception (there is an extra row and column wrapping around the maze)

      // Initializing and declaring variables to put the maze into the 2D array and find where the S is 
      int row2 = 1;  
      int sPositionY = 0; // y = index of the row 
      int sPositionX = 0; // x = index of the column 
      
      // Print statement to show that the maze will be printed next 
      System.out.println("Maze:");
      
      // Declaring new buffered reader to scan through the text file again 
      mazeReader = new BufferedReader(new FileReader(fileName + ".txt"));

      // While loop to read each line of the text file, put each character as an element in the array, and print the maze
      while ((currentLine = mazeReader.readLine()) != null) {
        // Looping through each column of the 2D array 
        for (int i = 0; i < maze[1].length; i++){
          // if i is smaller than the length of a line in the text file, then add the character to the maze array 
          if (i < currentLine.length()) { // prevents index out of bounds 
            // Adding the character of the String at index 'i' as an element of the array  
            maze[row2][i + 1] = currentLine.charAt(i); 
            // Prints the line 
            System.out.print(maze[row2][i + 1]);

            // Find where the starting position (S) is 
            if (currentLine.charAt(i) == 'S') {
              sPositionY = row2; 
              sPositionX = i + 1; 
            }
          }
        }
        // Once the line of the text file has been read, add 1 to the row2 counter for the next iteration and line in the text file 
        row2++;
        // To make sure the following line starts on the a new line 
        System.out.println();
      }

      // Making 'S' in the maze '.' so that the computer can move (only moves if it is on a '.' character)
      maze[sPositionY][sPositionX] = '.'; 

      // Checks if the maze has been solved  
      if (solveMaze(maze, sPositionY, sPositionX)){
        // If it has: 
        // Print congratulatory message
        System.out.println("Hurray! The computer found the goal.");

        // Make 'S', which was changed to '.' before, back into 'S' so that the maze will be printed properly 
        maze[sPositionY][sPositionX] = 'S'; 

        // Nested for loops to print the maze 
        for (int i = 1; i < maze.length - 1; i++){
          // Print each element of the maze within the indexes that the maze occupies (remember that there are extra rows and columns that surround the maze)
          for (int j = 1; j < maze[i].length - 1; j++){
            // Printing each column of row 'i'
            System.out.print(maze[i][j]);
          }
          // So that the next row starts on a new line 
          System.out.println();
        }
      }
      // If the method returns false: 
      else {
        // That means that there was no solution. Print statement: 
        System.out.println("The maze has no solution.\nHow dare you make the pitiful computer run around for nothing.");
      }
    }
    catch (IOException e){
      e.printStackTrace(); // prints the exception and where the exception occurred 
    }
  }
}
