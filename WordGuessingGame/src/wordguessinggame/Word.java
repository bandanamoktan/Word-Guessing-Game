/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordguessinggame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author bandanamoktan
 */
// Bandana Moktan
// Final Project: Word Guessing Game
// 05/5/17
// word class contains wordsFromSelectedCategory, selected word
// it contains mutator and accessor methods. 
public class Word {
    public String[] wordsFromSelectedCategory;
    public String selectedWord;
    
    public Word(){
        wordsFromSelectedCategory = new String[10];
     
    }
    
    public void setSelectedWord(String[] categoryWords){
        Random rn = new Random();
        int randomNumber = rn.nextInt(10);
        selectedWord = categoryWords[randomNumber];
    }
    
    public String getSelectedWord(){
       
        return selectedWord;
    }
    
    public void setWordsFromSelectedCategory(int userPick) throws FileNotFoundException{
            String fileName = "";
            switch (userPick) {
                case 1:  fileName = "animals.txt";
                         break;
                case 2:  fileName = "colors.txt";
                         break;
                case 3:  fileName = "sportsTeams.txt";
                         break;
            }
            
            File inputFile = new File(fileName);
            Scanner sc = new Scanner(inputFile);
          
            int i = 0;
            while(sc.hasNext()){
                String temp = sc.nextLine();
               
                wordsFromSelectedCategory[i] = temp;
                i++;
            }
    }
    public String[] getWordsFromSelectedCategory(){
       
        return wordsFromSelectedCategory;
    }
    
    public int getWordLength(){
        int lengthOfWord = selectedWord.length();
        return lengthOfWord;
    }
}

