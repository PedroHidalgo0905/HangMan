import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

class Main {
     static int  wrongCounter = 0;
  public static void main(String[] args) throws FileNotFoundException {
    // This is where the word gets picked from the txt file by random 
   
    
    ArrayList <String> Words = new ArrayList <String>();
    
    File fr = new File("Words.txt");
    Scanner fileScanner = new Scanner(fr);
    Scanner keyboard = new Scanner(System.in);
     
    while (fileScanner.hasNextLine()){
      Words.add(fileScanner.nextLine());
      
    }
    
    fileScanner.close();

    
    
    Random rand = new Random();
    String word = Words.get(rand.nextInt(148));
    List <Character> playerGuesses = new ArrayList<>();
   
    
  
    while(true){
      Main.printWordState(word,playerGuesses);
      Main.getPlayerGuesses(keyboard, word, playerGuesses);



      
      if ( Main.printWordState(word,playerGuesses)){
      
        break;
      } 
        
        drawHangman();
      
      System.out.println("Please enter the guess for the word:");
      
      if (keyboard.nextLine().equals(word)){
         System.out.println("You Win! :)");
        break;
      }else {
         System.out.println("Incorrect Try Again :(!");
      }
      if (wrongCounter==6){
        System.out.println("You Lose!");
        System.exit(0);
      }
    }
    
    
  }


  
  //This will draw the Hangman once the user starts putting in the wrong answer
        private static void drawHangman() {
    System.out.println(" -");
    System.out.println(" |   ");
    if (wrongCounter >= 1) {
      System.out.println(" O");
    }
    
    if (wrongCounter >= 2) {
      System.out.print("\\ ");
      if (wrongCounter >= 3) {
        System.out.println("/");
      }
      else {
        System.out.println("");
      }
    }
    
    if (wrongCounter >= 4) {
      System.out.println(" |");
    }
    
    if (wrongCounter >= 5) {
      System.out.print("/ ");
      if (wrongCounter >= 6) {
        System.out.println("\\");
      }
      else {
        System.out.println("");
      }
    }
    System.out.println("");
    System.out.println("");
  } 

  
  //This is trying to get the user input and relplace the dashes with the lettter with the player guesses.
  private static void getPlayerGuesses(Scanner keyboard, String word, List<Character> playerGuesses){
    
    System.out.println("Please enter a letter:");
    String letterGuess = keyboard.nextLine();
    playerGuesses.add(letterGuess.charAt(0));
     if (word.indexOf(letterGuess.charAt(0))==-1){
       wrongCounter++;
     }
   //Main.printWordState(word,playerGuesses);
    
  }

  
  
//this will be where the user inputs the letters to guess and replace the dash with the letter if its correct.
  private static boolean printWordState (String word, List <Character> playerGuesses){
    int CorrectCount = 0;
    for (int i = 0; i<word.length(); i++){
      if (playerGuesses.contains(word.charAt(i))){
        System.out.print(word.charAt(i));
        CorrectCount++;
      }
      else{
      System.out.print("-");
        
      }
    }
   
     System.out.println("");
    return (CorrectCount==word.length());
    
  }




  
}