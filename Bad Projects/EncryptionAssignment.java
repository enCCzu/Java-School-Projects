/*
 * Encryption Assignment 
 * Read from a file and encrypt/decrypt a string before writing encrypted/decrypted string into txt file
 * ///
 * Erin Zhou
 * December 1, 2020
 */ 

// Importing Packages //
import java.util.Scanner; // Importing scanner class for file input and user input
import java.io.*; // Importing file system package for PrintWriter and 

// Class //
public class EncryptionAssignment{
  
  // Initializing Scanner
  static Scanner scan = new Scanner(System.in);
  
  // ================================================================================================================= //
  
  // METHODS //
  
  // Method to check if file exists
  public static File secretMessage(File message) {
    
    // Variable Declaration
    String fileName; // String to hold file name
    
    // Print statement
    System.out.println("Welcome! Please enter a file to encrypt or decrypt.");
    
    // Asks user to enter a file 
    do { // Loop repeats until a proper file is entered 
      
      // Print statement 
      System.out.println("Please enter file name without the file extension:");
      fileName = scan.next(); // Recieves user input for file name 
      message = new File(fileName + ".txt"); // Creates file object with file name 
      
      // Does the file exist? 
      if (message.exists() && !message.isDirectory()){ // Checks if file exists and makes sure it is not a directory
        break;} // If it exists, loop ends 
      else {
        System.out.println("File not found!");} // If it doesn't exist, loop restarts 
    } while(true);
    
    // Print statement 
    System.out.println("File status: exists!");
    
    return message;
  }
  
  // Asking For User Choice // 
  public static int choice(int option){
    // Encryption or decryption? 
    do { // Loops until a proper option is chosen 
      // Print statement 
      System.out.println("Input 1 to encrypt message or input 2 to decrypt message."); 
      option = scan.nextInt(); // Recieves user input for option choice 
      
      if (option != 1 && option != 2) { // Checks if option is NOT valid 
        System.out.println("That's not an option! Try again.");} // If it is not, loop restarts 
      else {
        break;} // If it is valid, loop ends 
    } while(true); 
    return option;
  }
  
  // Assigning copy array's elements assignment  
  public static void assignElements(char[] copyArray, char[] originalArray){
    // For loop to assign 
    for (int i = 0; i < originalArray.length; i++){
      copyArray[i] = originalArray[i]; //Copying each element
    }
  }
  
  // Encryption & Decryption Method //
  public static void translation(String messageLine, char[] checkedCharacters, char[] translationKey, int option, String fileName) throws Exception 
  { // This method encrypts or decrypts one line of the message at a time 
    
    // - Initialization -
    // Declaring variables
    String tempString; // (Temporary string to convert char to string)
    String translatedLine = ""; // To store encrypted line of the message + (Variable to store written file's name)
    Boolean contains = false; // Boolean to check for characters that aren't in the 'characters' array
    
    // - Print Writer Initialization -
    PrintWriter output = new PrintWriter(new FileWriter(fileName + ".txt", true)); // Declaring file writer 
    
    // - Translation Loops -
    for (int i = 0; i < messageLine.length(); i++)
    { // This loops through each character of a line in the message
      // --> If encryption is the procedure, the loop must check all characters in the 'checkedCharacters' array to find the character's position (index)
      // --> If decryption is the procedure, the loop must check all characters in the 'translationKey' array to find the character's position (index)
      
      contains = false; // Sets 'contains' to false each time a new character is checked
      
      for (int j = 0; j < checkedCharacters.length; j++) 
      { // This loops through each character in the array 'checkedCharacters' or 'translationKey' 
        // --> both array lengths are equal (66 elements)
        
        if (messageLine.charAt(i) == checkedCharacters[j]){ // Checks if a character in message matches a character in the listed characters
          contains = true; // Turns 'contains' to true if the 'checkedCharacters' array contains the message's character 
          // - Actual Encryption or Decryption - 
          tempString = Character.toString(translationKey[j]); // Turns translation key's corresponding char into a string to be used 
          translatedLine += tempString;}} // Adds the character to the encrypted or decrypted line String
      
      if (contains == false){ // If the character in message doesn't belong to 'characters' array...
        translatedLine += messageLine.charAt(i);} // Character stays the same and is added to encrypted line
      
    }
    // - Printing -
    output.println(translatedLine); // Prints the encrypted line to the specified file
    
    // - Closing -
    output.close(); // Closing print writer 
  }
  
  // ================================================================================================================= //
  
  // Main Method //
  public static void main(String[] args) throws Exception {
    
    // - Initializing Arrays - 
    char[] characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 .,-".toCharArray();
    char[] encryptionKey = "BDRPOVZMXHSLUYTFNKGJQCAIWEbdrpovzmxhsluytfnkgjqcaiwe3052947618,-. ".toCharArray();
    // --> .toCharArray() converts each char in the string to an element of the arrays
    
    // - File -
    // Declaring Variables  
    File message = new File("EmptyFileObject"); // Creating an empty file object
    // Checks if file exists 
    message = secretMessage(message); // Calling method 
    
    // - Encryption or Decryption -
    // Variable declaration
    int option = 0; // Variable to store user input 
    //Asks for user's choice (encryption/decryption)
    option = choice(option); // Calling method 
    
    // - Encryption or Decryption? -
    // Initialization 
    char[] checkedCharacters = new char[66]; // Making a copy array
    char[] translationKey = new char[66]; // Making a copy array
    String procedure = ""; // String for print statement used to inform user of translation completion
    String fileName = ""; // A string to store file name "enc" or "dec"
    
    // If statements to check which procedure was performed 
    if (option == 1) { // If encryption was chosen...
      procedure = "Encryption"; // Procedure String is assigned corresponding message 
      fileName = "enc"; // Procedure String is assigned corresponding message
      // Assigning elements to arrays 
      assignElements(checkedCharacters, characters); // Assigning characters array elements to checkedCharacters array
      assignElements(translationKey, encryptionKey);} // Assigning encryptionKey elements to translationKey array
    
    else {
      procedure = "Decryption"; // Procedure String is assigned corresponding message 
      fileName = "dec"; //Procedure String is assigned corresponding message
      // Assigning elements to arrays
      assignElements(checkedCharacters, encryptionKey); // Assigning encryptionKey elements to checkedCharacters array
      assignElements(translationKey, characters);} // Assigning translationKey elements to characters array
    
    // - Performing Translation -
    // Initialization 
    Scanner fileInput = new Scanner(message); // Creates a scanner to read file 
    String messageLine; // Creates a string to store a line of the message  
    
    // To check if the file contains any data
    if (!fileInput.hasNext()){ // If it doesn't, we refer the user to another encryption program 
      System.out.println("Sorry! We cannot encrypt/decrypt ghost text! Please try Underworld-Encrypter-444.");}
    
    else{ // If it does contain data, it will be encrypted/decrypted
      // Loop to perform procedures 
      while (fileInput.hasNext()) // Checks if file contains more data 
      {
        messageLine = fileInput.nextLine(); // Stores a line of the data (the message) 
        // Calling method to complete requested procedure 
        translation(messageLine, checkedCharacters, translationKey, option, fileName); // Translation method with variables and arrays passed through as actual parameters
      }  
      
      // Final Print Statement
      System.out.println(procedure + " complete!");} // Informs user about the procedure being complete
  }
}