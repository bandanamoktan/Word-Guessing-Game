/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordguessinggame;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author bandanamoktan
 */
// 
// Bandana Moktan
// Final Project: Word Guessing Game
// 05/5/17
// Game class contains the word object, variables: wordBeingPlayed,guessedLetter,
// gussesLeft, currentBoardConfiguration, numberofgameswon.
public class Game {
    public Word wordBeingPlayed;
    public String guessedLetter = "";
    public int guessesLeft = 4;
    public String[] currentBoardConfiguration;
    public int numberOfGamesWon = 0;
    
    public Game() throws FileNotFoundException{
        showWelcomeMessage();
        initializeGame();
    }
    public void initializeGame() throws FileNotFoundException{
        
        System.out.println("Enter the number of the category you wish to play:");
        System.out.println("1: Animals \n2: Colors \n3: Sports Teams");
        System.out.print(">> ");
        

        int userPick = takeInputForCategoryDesired();
        wordBeingPlayed = new Word();
        wordBeingPlayed.setWordsFromSelectedCategory(userPick);
        wordBeingPlayed.setSelectedWord(wordBeingPlayed.getWordsFromSelectedCategory());
        initializeBoardConfiguration();
        playGame();
    }
    
    public void initializeBoardConfiguration(){
        currentBoardConfiguration = new String[wordBeingPlayed.getWordLength()];
        for(int i = 0;i<currentBoardConfiguration.length;i++){
            currentBoardConfiguration[i] = "_";
            
        }
    }
    
    
    public void playGame() throws FileNotFoundException{
        if(isGameWon(currentBoardConfiguration)){
            numberOfGamesWon++;
            System.out.println("\nCongratulations! You have guessed the word: "+ wordBeingPlayed.getSelectedWord());
            System.out.println("So far, you have won "+numberOfGamesWon+" game(s).");
            displayEndGameMessage();
        }
        if(guessesLeft == 0){
            System.out.println("You have lost the game.");
            displayEndGameMessage();
        }
        
        System.out.println("\nChoose a letter to complete the word: ");
       
        for(int i = 0; i < currentBoardConfiguration.length;i++){
            System.out.print(currentBoardConfiguration[i]+" ");
        }
        System.out.println("\nThere are "+ wordBeingPlayed.getWordLength() +" letters in this word.");
        System.out.print(">> ");
        Scanner tp = new Scanner(System.in);
        String thisPick = tp.nextLine();
        
        

        Boolean existsInWord = false;
        Boolean existsInBoard = false;

        int[] countForSelectedLetterArray = new int[wordBeingPlayed.getWordLength()];
        String[] lettersInWord = new String[wordBeingPlayed.getWordLength()];
        lettersInWord = wordBeingPlayed.getSelectedWord().split("");
        for(int j = 0; j < lettersInWord.length; j++){
            if(lettersInWord[j] == null ? thisPick == null : lettersInWord[j].equals(thisPick)){
                existsInWord = true;
                countForSelectedLetterArray[j] = 1;
               
            }
        }
        for(String temp: currentBoardConfiguration){
            if(temp == null ? thisPick == null : temp.equals(thisPick)){
                existsInBoard = true;
                
                break;
            }
        }

        if(existsInWord){
           
            if(existsInBoard){
                guessesLeft--;
                System.out.println(thisPick + " is in the word but it has already been selected. You have " +guessesLeft+ " guess(es) left." );
            }
            else{
             
                for(int i = 0;i<currentBoardConfiguration.length;i++){
                    if(countForSelectedLetterArray[i] == 1){
                        currentBoardConfiguration[i] = thisPick;
                       
                    }
                    
                }
            }
            playGame();
        }
        else{
                guessesLeft--;
                System.out.println(thisPick + " is not in the word. You have " +guessesLeft+ " guess(es) left." );
                playGame();
        }
    }
    
    public Boolean isGameWon(String[] boardConfiguration){
        Boolean isGameWon = true;
        for(String temp : boardConfiguration){
            if(temp == "_"){
                isGameWon = false;
            }
        }
        return isGameWon;
    }

    public void displayEndGameMessage() throws FileNotFoundException{
        System.out.println("Type 'y' to play another game. Anything else to quit: ");
        Scanner temp = new Scanner(System.in);
        String endOfGamePick = temp.nextLine();
        if("y".equals(endOfGamePick)){
            initializeGame();
        }
        else{
            System.exit(0);
        }
    }
    public static void showWelcomeMessage(){
      String message;
      message = "\nWelcome to the Word Guessing Game! You will choose a category of words and try to guess the letters in the word. Good luck!\n";

      System.out.println(message);
    }
    
    public int takeInputForCategoryDesired(){
        Scanner sc = new Scanner(System.in);
        int userPick = sc.nextInt();
        
        return userPick;
    }
}





