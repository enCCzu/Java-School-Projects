/*
 * Clacker Game Assignemnt 
 * Description: 
 *  - A game of clacker where a player rolls two die.
 *  - When a number is rolled, the number will be 'covered'.
 *  - The player must cover numbers 1-12 in the fewest rolls possible.
 *  - The sums of two rolls that are covered numbers can cover the numbers 7-12.
 *  - Once all the numbers are covered, the player wins. 
 * Name: Erin Zhou
 * Date: October 15th, 2020
 */ 

// Importing java utilities (classes)
import java.util.Scanner; // Scanner class to be able to use user input 
import java.util.Random; // Random class to generate random numbers 
// An arraylist is a tool in Java that can store multiple objects such as strings or integers
// Arraylists can be modified, and they are useful when there are sets of data that need to be recorded and used 
import java.util.ArrayList; // 
// Importing the collections framework allows for objects in collections (such as arrays, lits, arraylists) to be 
// manipulated easily (such as sorting an arraylist).
import java.util.Collections; 

// Setting the class, Clacker 
public class Clacker {
  public static void main(String args[]) { // The main method which is needed for code to run
    
    // - DECLARATION of IMPORTED UTILITIES -
    // Declaring a variable to be the scanner for user input
    Scanner myScanner = new Scanner(System.in);
    // Declaring variables as arraylists to be able to store objects inside 
    ArrayList<Integer> uncovered = new ArrayList<Integer>();
    ArrayList<Integer> covered = new ArrayList<Integer>();
    // Declaring a variable to be the random number generator 
    Random dice = new Random();
    
    // INTRODUCTION
    System.out.println("Welcome to the Clacker game.");
    System.out.println("Your mission is to 'cover' the numbers from 1-12 by rolling them.");
    System.out.println("If both numbers rolled are covered, the sum will be covered.");
    System.out.println("For example, if you have covered 4 and 5 already, 9 will be covered.");
    System.out.println("A zero on the uncovered board means that a number has been covered.");
    System.out.println("Your goal is to win in the fewest # of rolls.");
    System.out.println("Have fun!");
    
    // - VARIABLE DECLARATION -
    boolean play = true; // Declaring the variable 'play' which is for the game to run 
    String name, continuePlay; // Declaring variable for user input on whether they want to continue playing 
    int best = 1000000000; // Declaring best score with a value of 1 billion because no one will ever roll that much
    int requestedRolls, roll1, roll2, sumRolls; // variables that will be assigned values later 
    int attempts = 0; // How many games they've tried 
    int totalRolls = 0; //How many rolls they've tried throughout all games 
    
    // - NAME -
    System.out.println("Enter your name:");
    name = myScanner.nextLine();
    
    // ADDING OBJECTS (NUMBERS) TO ARRAYLIST 
    for (int i = 1; i <= 12; i++){
      uncovered.add(i); } // add() adds an object to the arraylist 
    
    do {
      
      // Counting the amount of times it takes for the user to finish the game (1 attempt = input of attempted rolls)
      attempts += 1;
      
      // HOW MANY ROLLS? Asks how many times the user wants to roll
      System.out.println("How many rolls would you like to attempt?");
      requestedRolls = myScanner.nextInt();
      // Making sure the # of rolls isn't below 1 or above 30
      if (requestedRolls <= 0 || requestedRolls > 30) {
        System.out.println("You must roll a number between 1-30. Enter another number.");
        requestedRolls = myScanner.nextInt();}
      
      // MAIN GAME. This for loop is how the game works 
      for (int i = 1; i <= requestedRolls; i++){
        // Rolling 2 random numbers and printing them
        System.out.println("Currently on roll: " + i + " of " + requestedRolls);
        roll1 = dice.nextInt(6) + 1;
        roll2 = dice.nextInt(6) + 1;
        System.out.println("Dice 1: " + roll1);
        System.out.println("Dice 2: " + roll2);
        
        // - COVERING NUMBERS - 
        // Condition statements that checks if the arraylist of covered numbers contain the numbers rolled 
        if (!covered.contains(new Integer (roll1)) || !covered.contains(new Integer (roll2))) {
          // contains() checks if an onject is in the arraylist 
          if (!covered.contains(new Integer (roll1))){ // If roll 1 hasn't been covered...
            covered.add(roll1); // Adds the 1st number rolled to the 'covered' arraylist 
            uncovered.set((roll1 - 1), 0);} // Replaces the 1st number in the 'uncovered' arraylist with 0 
          
          if (!covered.contains(new Integer (roll2))){ // If roll 2 hasn't been covered...
            covered.add(roll2); // Adds the 2nd number rolled to the 'covered' arraylist 
            uncovered.set((roll2 - 1), 0);} } // Replaces the 2nd number in the 'uncovered' arraylist with 0 
        
        
        // - SUM OF COVERED NUMBERS ROLLED -
        // Checks if both numbers rolled are covered 
        else if (covered.contains(new Integer (roll1)) && covered.contains(new Integer (roll2))){
          sumRolls = roll1 + roll2; // Assigning the sum of the rolled numbers to a variable 
          
          // Checks if the sum of the rolled numbers is already covered. If it is not... 
          if (!covered.contains(new Integer(sumRolls))){ 
            covered.add(sumRolls); // Adds the sum to the 'covered' arraylist
            uncovered.set((sumRolls - 1), 0);} } // Replaces the sum in the 'uncovered' arraylist with 0
        
        // - BOARD OF UNCOVERED NUMBERS - 
        // Prints a board of uncovered numbers
        System.out.println("Here is the board. A zero is a covered number:"); 
        System.out.println(uncovered.get(0) + "  " + uncovered.get(1) + "  " + uncovered.get(2) + "  " + uncovered.get(3));
        System.out.println(uncovered.get(4) + "  " + uncovered.get(5) + "  " + uncovered.get(6) + "  " + uncovered.get(7));
        System.out.println(uncovered.get(8) + " " + uncovered.get(9) + " " + uncovered.get(10) + " " + uncovered.get(11));
        
        // - COVERED -
        Collections.sort(covered); // Sorts the objects in the 'covered' arraylist 
        System.out.println("You have covered:" + covered); // Prints the arraylist to show covered numbers 
        
        // Counts the amount of times that the dice has been rolled 
        totalRolls++;
        
        // Stops the for loop if all numbers are covered 
        if (covered.size() == 12){ // covered.size() tells how many objects are in the arraylist 
          break;}
      }
      
      // - LOST - 
      if (!(covered.size() == 12)) { // If the numbers weren't all covered...
        // Prints the losing statement and the score 
        System.out.println("Aww you didn't cover all of the numbers in the amount of rolls you requested.");
        System.out.println("Here is your current score:");
        
        System.out.println("Player: " + name);
        System.out.println("Total amount of rolls: " + totalRolls);
        System.out.println("Attempts at the game: " + attempts);
        // Prints the best score 
        if (attempts == 1){
          System.out.println("Best: You do not have a best score yet.");}
        else {
          System.out.println("Best: " + best);}
        
        // Asks if the player wants to continue playing 
        System.out.println("Would you like to continue playing? (yes/no)");
        myScanner.nextLine();
        continuePlay = myScanner.nextLine();
        if (continuePlay.equals("No") || continuePlay.equals("n") || continuePlay.equals("N") || continuePlay.equals("no")){
          play = false;}}
      
      // - WIN - 
      else {
        // Prints winning statement and score 
        System.out.println("Congrats! You have covered all of the numbers!");
        System.out.println("Here is your score:");
        
        System.out.println("Player: " + name);
        System.out.println("Total amount of rolls: " + totalRolls);
        System.out.println("Attempts at the game: " + attempts);
        // Prints best score and updates it if a new best score was achieved 
        if (best >= totalRolls){
          best = totalRolls;
          System.out.println("Best: " + best);}
        else {
          System.out.println("Best: " + best);}
      
        // Asks player if they want to play the game again 
        System.out.println("Would you like to play again?");
        myScanner.nextLine();
        continuePlay = myScanner.nextLine();
        // If not, then the progran ends 
        if (continuePlay.equals("No") || continuePlay.equals("n") || continuePlay.equals("N") || continuePlay.equals("no")){
          play = false;}
        // If yes, then the game is reset, but the best score is still visible 
        else{
          // Resetting variables 
          totalRolls = 0;
          attempts = 0;
          // Resetting arraylists by removing all objects in them 
          uncovered.clear();
          covered.clear();
          // Adding the numbers back into the arraylist 'uncovered' 
          for (int i = 1; i <= 12; i++){
            uncovered.add(i); }}}
    } while (play); // While the player wants to play, the game will continue 
  }
}