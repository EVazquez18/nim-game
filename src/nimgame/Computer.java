package nimgame;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *Author: Esme E. Vazquez
 * Date: 01/15/18, Edited 01/29/18
 *Title: Nim Game
 *Purpose: A Java program that plays the game of Nim in several different modes.
 *These modes include:
 * Player vs. Player
 * Player vs. Random Computer
 * Player vs. Trained AI 
 */


public class Computer 
{
    private int computerMove;

    public Computer(int sticksLef, int[][][] hatArray, int gameMo, boolean track)
    {
        if(gameMo == 2)
        {
            if(sticksLef >= 5)
            {
                //creating a random move between 1 to 5 sticks 
                computerMove = ThreadLocalRandom.current().nextInt(1,6);
            }
        
            else 
            {
            	computerMove = ThreadLocalRandom.current().nextInt(1,sticksLef + 1);
            }  
        }
        
        else
        {
            ArrayList<Integer> possibleMoves = new ArrayList<>();
        
            if(sticksLef >= 5)
            {
            
                for(int i = 1; i <= 5; i++)
                {
              
                    for(int j = 1; j <= hatArray[sticksLef - 1][i - 1][0]; j++)
                    {
                        possibleMoves.add(i);
                    }

                    possibleMoves.add(i);

                }
            
                computerMove = possibleMoves.get(ThreadLocalRandom.current().nextInt(0,possibleMoves.size()));
            }
        
            else
            {
                //adds all possible moves to the array
                for(int i = 1; i <= sticksLef; i++)
                {
                    //counts how many wins the computer has and adds to possibleMoves array
                    for(int j = 1; j <= hatArray[sticksLef - 1][i - 1][0]; j++)
                    {
                        possibleMoves.add(i);
                    }
                    possibleMoves.add(i);
                }
            
                computerMove = possibleMoves.get(ThreadLocalRandom.current().nextInt(0,possibleMoves.size()));
            }
        }
        
        if(gameMo == 2)
        {
            //print statement reporting the computer 
            System.out.print("The computer, Marty, took "+ computerMove + " sticks from the table.");
        }
        else
        {
            if(gameMo < 4)
            {
               System.out.print("The computer, Doc Brown, took "+ computerMove + " sticks from the table.");  
            }
        }
        
        if(gameMo < 4)
        {
            //to create spacing
            System.out.println(""); 
        }
        
    }
    
    //sending the move data of the computer back to Game.java
    public int computerMoveData()
    {
        return computerMove;
    }
    
  
    
}
