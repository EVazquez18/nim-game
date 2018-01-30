package nimgame;

import java.util.Scanner;

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

public class Player 
{
    private int playerNumber; 
    private int playerMove; 
    
    public Player(int playerNumber, int sticksLef)
    {
        //establishing a Scanner for input of player sticks 
        Scanner player = new Scanner(System.in); 
        
        //to create spacing
        System.out.println(""); 
            
        //print statement asking for player move 
        System.out.print("Player " + playerNumber + " how many sticks will you take? (1-5) ");
        
        //to take player sticks input
        playerMove = player.nextInt();
        
         //a while loop to check to make sure the player inputs are between 1 and 5
        while(playerMove < 1 || playerMove > 5 || playerMove > sticksLef)
        {
             //to create spacing
            System.out.println(""); 
        
            System.out.print("That is an invalid move, please enter another value. (1-5)");
            playerMove = player.nextInt();
        }
        
    }
    
    //return the value from the player back to game to subtract this value from the total number of sticks 
    public int playerMoveData()
    {
        return playerMove; 
    }
    
}
