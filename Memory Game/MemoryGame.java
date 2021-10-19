/* 
 * Memory Game 2D Array Game 
 * Description: Memory game built with 2D arrays with PvP and PvC
 * Author: Erin Zhou
 * Teacher: Mr. Ho 
 * Date: 5/10/21 
 */

 // Importing packages 
import java.util.Scanner; // To get user input 
import java.util.Random; // To create random numbers 

class MemoryGame {

  // - METHODS USING JAVA.UTIL.RANDOM TO CREATE RANDOMIZED VALUES IN THE GAME - // 
  
  /**
   * Generates a random number 0-5 (used by the computer to randomly choose a card (row, column) on the board)
   * @return a randomly generated number 0-5
   */
  public static int randomNumber(){
    Random rand = new Random(); // Declaring Random object 
    int num = rand.nextInt(6); // generating random object
    return num; 
  }


  /**
   * Generates a random number 0-100 and checks if it is even or odd (For the PvC gamemode)
   * @return true (if even, and the player starts first)
   * @return false (if odd, and the computer starts first) 
   */
  public static boolean whoPlaysFirst() {
    // Declaring/initializing variables + generating random number 
    Random rand = new Random();
    int num = rand.nextInt(101);
    // Checks if it is even (returns true) or odd (returns false)
    if (num % 2 == 0) {
      System.out.println("Player starts first.");
      return true;
    }
    System.out.println("The computer starts first.");
    return false;
  }



  // - METHODS TO CREATE AND PRINT THE BOARD - // 

  /**
   * Fills the board with cards (capital letters)
   * @param board is a 2D char array that stores all of the 'cards'
   */
  public static void populateBoard(char[][] board) {
    // Declaring and initializing variable 
    int counter = 0;
    // Nested for loops that loop through the index of the 2D char array 
    for (int i = 0; i < board.length; i++) { // iterates through the rows
      for (int j = 0; j < board[i].length; j++) { // iterates through the columns
        if (counter >= 18) { // must only have 18 letters because the cards must have duplicates (total of 36 cards, so half would be 18)
          counter = 0; // Prevents letters from passing 'R' (the 18th letter on the alphabet)
        }
        board[i][j] = (char) ('A' + counter); // All chars (ex. 'A' = 41) are just numbers (unicode), so the counter goes onto the next letter by adding a number)
        counter++; // for each iteration, 1 is added to the counter to move onto the next letter 
      }
    }
  }


  /**
   * Randomly shuffles the 'cards' (elements) on the board (char array) 
   * @param board is a 2D char array that stores all of the 'cards'
   */
  public static void shuffleBoard(char[][] board) {
    // Initializing and declaring variables/objects 
    Random rand = new Random();
    int randomRow, randomColumn;
    char temp;
    // Nested for loop to loop through each element of the array 
    for (int i = 0; i < board.length; i++) { // iterates through the rows
      for (int j = 0; j < board[i].length; j++) { // iterates through the columns
        // Swtiches a random element on the board with the current element
        randomRow = rand.nextInt(board.length);
        randomColumn = rand.nextInt(board[i].length);
        temp = board[i][j]; // stores first/current card on the board 
        board[i][j] = board[randomRow][randomColumn]; // makes current position/card into the new value from randomly picked second card
        board[randomRow][randomColumn] = temp; // makes the randomly picked second card have the same value as the first card 
      }
    }
  }
  

/**
 * Prints a board with all cards facing down so that the player can pick (no cheating)
 * @param board is a 2D char array that stores all of the 'cards'
 */
  public static void printCoveredBoard(char[][] board) {
    System.out.println("_______________________________________\n   1   2   3   4   5   6"); // to divide whatever was before printing this board 
    // nested for loop to loop through the array and prints each card as a [/] and removed cards as an empty space
    for (int i = 0; i < board.length; i++) { // iterates through the rows
      System.out.print(i + 1); // printing row numbers 
      for (int j = 0; j < board[i].length; j++) { // iterates through the columns and prints them on one line
        if (board[i][j] != '0') {
          System.out.print(" [/]");
        } else {
          System.out.print("    ");
        }
      }
      System.out.println(); // whenever it reaches the next row, it starts on another line 
    }
  }


  /**
   * Prints the board with the two chosen cards flipped so that you can see what letter the cards have 
   * @param board is a 2D char array that stores all of the 'cards'
   * @param rowCard1 is the index of the row of the first card 
   * @param columnCard1 is the index of the column of the first card 
   * @param rowCard2 is the index of the row of the second card 
   * @param columnCard2 is the index of the column of the second card
   */
  public static void printsFlippedBoard(char[][] board, int rowCard1, int columnCard1, int rowCard2, int columnCard2){
    System.out.println("   1   2   3   4   5   6"); // printing column numbers 
    // nested for loop to loop through array and print a board 
    for (int i = 0; i < board.length; i++) { // iterates through the rows
      System.out.print(i + 1); // prints row numbers 
      for (int j = 0; j < board[i].length; j++) { // iterates through the columns
        if ((i == rowCard1 && j == columnCard1) || (i == rowCard2 && j == columnCard2)){ // if it is a properly chosen card, reveal it 
          System.out.print(" [" + board[i][j] + "]");
        }
        else if (board[i][j] != '0') { // if it is not a chosen card, keep it hidden 
          System.out.print(" [/]");
        } else { // if it is not an available card, use empty spaces 
          System.out.print("    ");
        }
      }
      System.out.println(); // whenever it reaches the next row, it starts on another line 
    }
  }


/**
 * Method used for DEBUGGING or running TEST CASES - Reveals all cards on the board 
 * @param board char 2D array that contains all the elements/'cards' of the game 
 */
  /*
  public static void printRevealedBoard(char[][] board) {
    System.out.println("   1   2   3   4   5   6");
    for (int i = 0; i < board.length; i++) { // iterates through the rows
      System.out.print(i + 1);
      for (int j = 0; j < board[i].length; j++) { // iterates through the columns
        System.out.print(" [" + board[i][j] + "]");
      }
      System.out.println();
    }
  }
  */



  // - METHODS THAT RUN THE GAME - // 

  /**
   * method for user(s) to choose what gamemode they want to play (PvP or PvC)
   * @return a value (0 - PvP or 1 - PvC) depending on what gamemode they want to play 
   */
  public static int gamemode() {
    // Initializing and declaring variables/objects 
    Scanner scan = new Scanner(System.in);
    String gamemodeChoice;
    // Instructions 
    System.out.println("Please choose a gamemode - PvP/PvC:");

    // Do-while loop that makes sure that user typed PvP or PvC, if not, then it continues until they do 
    do {
      // User input
      gamemodeChoice = scan.nextLine();
      // Checking what they typed 
      if (gamemodeChoice.equalsIgnoreCase("pvp")) {
        return 0;
      } 
      else if (gamemodeChoice.equalsIgnoreCase("pvc")) {
        return 1;
      } else {
        System.out.println("Please choose a proper gamemode:");
      }
    } while (true);
  }


  // - Player vs. Player gamdemode methods + User-input related methods - // 


  /**
   * If PvP was chosen, this method runs the code for this gamemode 
   * @param board is a 2D char array that stores all of the 'cards'
   * @param computerMemory is a 2D char array that stores all revealed 'cards' for the computer to use (
   *    - this is only necessary because the method 'playOneTurn' requires this parameter)
   */
  public static void gamemodePvP(char[][] board, char[][] computerMemory){
    // Initializing/declaring variables
    int player1Score = 0;
    int player2Score = 0;

    // Instructions 
    System.out.println("Player 1 vs Player 2:\nPlayer 1 starts first.");

    // Do-while loop so that each player plays until all the cards on the board are gone/all pairs have been found
    do {
      // Player 1's turn: 
      printCoveredBoard(board); // prints board of available cards 
      System.out.println("Player 1's Turn:");
      player1Score += playOneTurn(board, computerMemory); //calls another method so that the player can make a move 
      if (isEmptyBoard(board)){ // checks if the board is empty after this player plays 
        break; // if it is empty, then the game ends 
      }
      // Player 2's turn 
      printCoveredBoard(board); // prints board of available cards
      System.out.println("Player 2's Turn:");
      player2Score += playOneTurn(board, computerMemory); //calls another method so that the player can make a move
      // Printing the scoreboard 
      System.out.println("The current score is: Player 1 - " + player1Score + " | Player 2 - " + player2Score);
    } while (!isEmptyBoard(board)); // keeps playing until the board is empty 

    // Figuring out who won based on their scores 
    if (player1Score > player2Score){ // player 1 wins 
      System.out.println("Congratulations to player 1 for winning!");
    }
    else if (player1Score < player2Score){ // player 2 wins 
      System.out.println("Congratulations to player 2 for winning!");
    }
    else { // they tie 
      System.out.println("Congratulations to the both of you! You got a tie!");
    }
  }


  /**
   * Loops through the 2D char aray 'board' to check if all pairs have been found 
   * @param board is a 2D char array that stores all of the 'cards'
   * @return true or false if the board is found to be empty 
   */
  public static boolean isEmptyBoard(char[][] board){
    // nested for loop to loop through all elements of the array 
    for (int i = 0; i < board.length; i++) { // iterates through the rows
      for (int j = 0; j < board[i].length; j++) { // iterates through the columns
        if (board[i][j] != '0'){ // if an available card is found (meaning, it does not have the value of 0), this method will return a false 
          return false;
        } 
      }
    }
    return true; // if all elements were found to be equal to '0' (all pairs found), it returns true 
  }


  /**
   * Used it is a player's turn to choose cards (tells user if they got matching cards or not) - This method is used in both the PvP and PvC gamemode 
   * @param board is a 2D char array that stores all of the 'cards'
   * @param computerMemory is a 2D char array that stores all revealed 'cards' for the computer to use
   * @return a number (0 or 1) that tells the game to give the certain player another point if the chosen cards match 
   */
  public static int playOneTurn (char[][] board, char[][] computerMemory){
    // Initialize/declar variables 
    int addPoint = 0; 
    // if block that checks if the chosen cards are matching 
    if (chooseCards(board, computerMemory)){ // calls another method so that the user can choose cards and checks if they match 
      System.out.println("Wow! You got a pair!");
      addPoint = 1;
    }
    else { // if they don't match, no points will be added (score + 0)
      System.out.println("Nice try!");
    }
    return addPoint; 
  }


  /**
   * This allows a user to choose the cards and checks if they are matching - also lets computer know which cards player picked to store in its memory
   * @param board is a 2D char array that stores all of the 'cards'
   * @param computerMemory is a 2D char array that stores all revealed 'cards' for the computer to use
   * @return true or false if a pair is chosen or not 
   */
  public static boolean chooseCards(char[][] board, char[][] computerMemory){
    // Initialize/declar variables that corespond to the index of certain 'cards'/elements on the board (char array)
    int rowCard1 = 0;
    int rowCard2 = 0;
    int columnCard1 = 0;
    int columnCard2 = 0;

    // do-while loop that asks player for two cards 
    // makes sure chosen cards are different cards and that they are available cards (meaning that they exist on the board)
    do {
      // Choosing second card 
      System.out.println("Remember to choose proper cards!\nPlease choose your first card.\nRow:");
      rowCard1 = isProperBoardInput(); // isProperBoardInput() checks if the player entered a proper number (1-6)
      System.out.println("Column:");
      columnCard1 = isProperBoardInput();

      // Choosing second card
      System.out.println("Please choose your second card.\nRow:");
      rowCard2 = isProperBoardInput(); 
      System.out.println("Column:");
      columnCard2 = isProperBoardInput(); 
      // while conditions check to make sure that player chose proper cards 
    } while ((rowCard1 == rowCard2 && columnCard1 == columnCard2) || !isAvailableCard(board, rowCard1, columnCard1) || !isAvailableCard(board, rowCard2, columnCard2));

    // Tells the computer which cards the player revealed and to remember those positions 
    computerMemory[rowCard1 - 1][columnCard1 - 1] = board[rowCard1 - 1][columnCard1 - 1]; // remember first card 
    computerMemory[rowCard2 - 1][columnCard2 - 1] = board[rowCard2 - 1][columnCard2 - 1]; // remember second card 

    // returns true or false by calling another boolean method to check if the chosen cards are a pair 
    return isMatching(board, rowCard1 - 1, columnCard1 - 1, rowCard2 - 1, columnCard2 - 1); 
  }


  /**
   * Asks player to enter a number between 1-6 and checks to make sure the player entered a proper number 
   * @return the number that the player entered after it has been checked
   */
  public static int isProperBoardInput() {
    // initialize/declare variables/objects 
    Scanner scan = new Scanner(System.in);
    int num;

    // do-while that asks player to repeatedly enter a number until it is within 1-6
    do {
      // Instructions 
      System.out.println("Please enter a number between 1-6:");
      // This while loop makes sure the next player input is indeed a number, if it is not, then it asks again 
      while (!scan.hasNextInt()){ // hasNextInt() checks if the next scan input is a number \
        // if it is not a number: 
        System.out.println("That's not a number.");
        scan.next(); // makes sure scanner doesn't skip a line because of the nextInt() bug 
      }
      num = scan.nextInt(); // asks player to input a number 
    } while (num < 1 || num > 6); // checks to make sure that the number is within 1-6
    return num; // returns the number player entered  
  }


  /**
   * checks if the card chosen by the player is an available card (meaning that its pair still needs to be found)
   * @param board is a 2D char array that stores all of the 'cards'
   * @param row is the index of the row of the card/element
   * @param column is the index of the column of the card/element
   * @return true or false if the card is or is not available 
   */
  public static boolean isAvailableCard (char[][] board, int row, int column){
    // checks if the chosen card exists 
    if (board[row - 1][column - 1] != '0') { // if the element on the array == '0', that means it doesn't exist/isn't available 
      // if the card is available, the player can choose this card 
      System.out.println("Chosen card: (" + row + ", " + column + ").");
      return true;
    }
    // if it doesn't exist, return false 
    return false;
  }


  /**
   * Checks if the chosen cards are matching 
   * @param board is a 2D char array that stores all of the 'cards'
   * @param rowCard1 is the index of the row of the first card/element
   * @param columnCard1 is the index of the column of the first card/element
   * @param rowCard2 is the index of the row of the second card/element
   * @param columnCard2 is the index of the column of the second card/element
   * @return true or false if the cards are matching or not 
   */
  public static boolean isMatching(char[][] board, int rowCard1, int columnCard1, int rowCard2, int columnCard2){
    // printing chosen cards on the board 
    printsFlippedBoard(board, rowCard1, columnCard1, rowCard2, columnCard2);

    // checks if  matching
    if (board[rowCard1][columnCard1] == board[rowCard2][columnCard2]){ // if matching: 
      // tells board to make the cards no longer available if they are indeed pairs 
      board[rowCard1][columnCard1] = '0';
      board[rowCard2][columnCard2] = '0'; 
      return true; 
    }
    // if not matching: 
    return false; 
  }


  
  // - Player vs. Computer gamdemode methods + Computer decision-making related methods - // 

  /**
   * This method is what runs the PvC gamemode. It lets the player or computer play first and then they have alternating turns 
   * @param board is a 2D char array that stores all of the 'cards'
   * @param computerMemory is a 2D char array that stores all revealed 'cards' for the computer to use
   */
  public static void gamemodePvC(char[][] board, char[][] computerMemory){
    // initializing/declaring variables 
    int playerScore = 0;
    int computerScore = 0; 

    // Checks for who plays first. If the condition is true, then the player will play first. If false, this block is skipped and the computer plays first
    if (whoPlaysFirst()){ // calls method to determine who plays first 
      printCoveredBoard(board);
      System.out.println("Player's turn."); 
      playerScore += playOneTurn(board, computerMemory); // calls method to let player play. if chosen cards match, a point is added 
    }
    // do-while loop that allows the alternating moves and stops when the board is empty 
    do {
        // Computer's turn 
        printCoveredBoard(board); // prints board 
        System.out.println("Computer's turn.");
        computerScore += computerPlays (board, computerMemory); // calls method to allow computer to choose cards and adds a point if cards are matching 
        System.out.println("The score is: Player - " + playerScore + " | Computer - " + computerScore); // prints scoreboard in case player had first play 
        if (isEmptyBoard(board)){ // checks to make sure that the board is not empty. If true, then the game will end. If false, the player can choose cards next
          break;
        }
        
        // Player's turn 
        printCoveredBoard(board); // prints board 
        System.out.println("Player's turn.");
        playerScore += playOneTurn(board, computerMemory); // calls method to let player play. if chosen cards match, a point is added
        System.out.println("The score is: Player - " + playerScore + " | Computer - " + computerScore); // prints scoreboard 
    } while (!isEmptyBoard(board)); // while there are still available cards to choose 
    
    // Checks to see who won and prints appropriate response 
    if (playerScore > computerScore){ // if player won (nearly impossible): 
      System.out.println("HOW DID YOU BEAT A COMPUTER IN A MEMORY GAME????"); 
    }
    else if (playerScore < computerScore){ // if computer won (99% chance): 
      System.out.println("Good try, player! It's hard to beat a computer in memory games.");
    }
    else { // if they tie: (nearly impossible):
      System.out.println("You tied with the computer! Nice try!"); 
    }
  }


  /**
   * The computer checks if it knows any pairs in its memory. If not, it randomly picks two cards and memorizes them. 
   * @param board is a 2D char array that stores all of the 'cards'
   * @param computerMemory is a 2D char array that stores all revealed 'cards' for the computer to use
   * @return 0 or 1 depending if chosen cards are matching 
   */
  public static int computerPlays (char[][] board, char[][] computerMemory){
    // Initializing variables 
    int rowCard1, rowCard2, columnCard1, columnCard2; 

    // do while loop that pulls random numbers (0-5) that act as the cards the computer chooses
    // checks to make sure the chosen cards are not the same card and they are available 
    do {
      rowCard1 = randomNumber(); // calls method for random number 
      columnCard1 = randomNumber(); 
      rowCard2 = randomNumber();
      columnCard2 = randomNumber(); 
    } while (board[rowCard1][columnCard1] == '0' || board[rowCard2][columnCard2] == '0' || (rowCard1 == rowCard2 && columnCard1 == columnCard2)); 

    // checks if the computer knows of any two cards that are matching 
    if (computerDuplicates(board, computerMemory)){ // if there is a duplicate in its memory, a point goes to the computer (return 1)
      System.out.println("The computer got a pair!");
      return 1; 
    }
    // if not, then it randomly chooses two cards 
    else { 
      // tells player what the computer chose 
      System.out.println("The computer chooses: (" + (rowCard1 + 1) + ", " + (columnCard1 + 1) + ") and (" + (rowCard2 + 1) + ", " + (columnCard2 + 1) + ").");
      // prints board
      printsFlippedBoard(board, rowCard1, columnCard1, rowCard2, columnCard2);
      // gets the computer to remember the value of the chosen cards 
      computerMemory[rowCard1][columnCard1] = board[rowCard1][columnCard1]; 
      computerMemory[rowCard2][columnCard2] = board[rowCard2][columnCard2]; 
      // in the rare chance that the randomly chosen cards are pairs, this checks that and gives the computer a point (return 1)
      if (board[rowCard1][columnCard1] == board[rowCard2][columnCard2]){
        board[rowCard1][columnCard1] = '0'; 
        board[rowCard2][columnCard2] = '0'; 
        System.out.println("It's a pair!"); 
        return 1; 
      }
      // if they are not pairs, then no points for the computer (return 0)
      else{
        System.out.println("It's not a pair!");
      }
      return 0;
    }
  }


  /**
   * Scans through the computer's memory (char array) to see if it knows any matching cards 
   * @param board is a 2D char array that stores all of the 'cards'
   * @param computerMemory is a 2D char array that stores all revealed 'cards' for the computer to use
   * @return true or false depending if there are matching cards
   */
  public static boolean computerDuplicates(char[][] board, char[][] computerMemory){
    // first two for loops loop through the char array 
    for (int i = 0; i < computerMemory.length; i++) { // iterates through the rows
      for (int j = 0; j < computerMemory[i].length; j++) { // iterates through the columns 
        // the second two for loops loops through the char array to compare each element to the element with the index of [i][j] 
        for (int k = 0; k < computerMemory.length; k++){
          for (int l = 0; l < computerMemory[i].length; l++){
            // if the cards are available cards and are not the same card being compared with itself and if they are matching 
            if (board[i][j] != '0' && board[k][l] != '0' && computerMemory[i][j] == computerMemory[k][l] && (i != k && j != l) && (computerMemory[i][j] != '\0' || computerMemory[k][l] != '\0')){ 
              System.out.println("The computer chooses: (" + (i + 1) + ", " + (j + 1) + ") and (" + (k + 1) + ", " + (l + 1) + ")."); // tells player what the chosen cards are
              printsFlippedBoard(board, i, j, k, l); // prints revealed cards 
              // tels the board to remove those cards 
              board[i][j] = '0'; 
              board[k][l] = '0'; 
              return true;
            }
          }
        }
      }
    }
    // if there weren't any duplicates in the computer's memories 
    return false; 
  }



  // - MAIN METHOD - //

  public static void main(String[] args) {
    // Initialize/declare variables 
    boolean play = true; 

    // Welcome to the game 
    System.out.println("This is a non-standard memory card game.\nYou must choose two cards before flipping!\nHave fun! ^v^");

    // Main do-while loop that the whole game runs on 
    do {

      // initialize/declare Scanner object 
      Scanner scan = new Scanner(System.in);

      // Initialize/declare 2D char arrays 
      char[][] computerMemory = new char[6][6]; 
      char[][] board = new char[6][6];

      // Generate the board 
      populateBoard(board);
      shuffleBoard(board);

      // ask if they want to do PvP or PvC
      if (gamemode() == 0) { // They want to play PvP
        System.out.println("You chose PvP.");
        gamemodePvP(board, computerMemory); // calls method for this gamemode to be played 
      } 
      else { // They want to play PvC
        System.out.println("You chose PvC.");
        gamemodePvC(board, computerMemory); // called method for this gamemode to be played 
      }

      // End of game: ask if they want to play again 
      System.out.println("Do you want to continue playing? If yes, type a yes-related word. If no, just type anything else.");
      String continuePlay = scan.nextLine(); // getting player input 
      // Analyzing what they input 
      if (continuePlay.equalsIgnoreCase("yes") || continuePlay.equalsIgnoreCase("ye") || continuePlay.equalsIgnoreCase("ya") || continuePlay.equalsIgnoreCase("sure")) { // if they want to continue
          play = true; // loop continues and the whole game 'resets'
      } 
      else { // they didn't want to play again 
        play = false; // the loop ends and the whole program stops 
      }
    } while (play); // while the player(s) still wants to play
  }
}