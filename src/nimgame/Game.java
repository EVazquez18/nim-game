package nimgame;

import java.util.ArrayList;
import java.util.List;

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

public class Game 
{
    //declare the variables needed to play the game 
    //number of sticks on table
    private int numOfSticks;
    //player selected game mode, a value 1-3
    private int gameMode;
    //player number 
    private int playerNumber;
    //player's move
    private int playerMove;
    //random computer's move
    private int computerMove;
    //this is the number of sticks left on the table
    private int sticksLeft; 
    private boolean playerWin;
    //to specify this boolean value could be either for a computer win or Player 2 win
    private boolean playerTwoOrComputerWin;
    
    //creating the array for the AI moves 
    private int[][][] hatsArray;
    //storing the amount of sticks on the board
    private ArrayList<Integer> initialsHats;
    //to store the computer move 
    private ArrayList<Integer> computerTook;
    
    private boolean trainingTracker;
    
    
    
    public Game(int numSticks, int mode, int[][][] hatAr)
    {
        //instantiating the variables and a new ArrayList 
        gameMode = mode;
        numOfSticks = numSticks; 
        sticksLeft = numOfSticks;
        playerWin = false;
        playerTwoOrComputerWin = false;
        playerNumber = 1;
        
        //specifying the values
        //max sticks at 100 
        //max move at 5
        //max computer move at 1 
        hatsArray = hatAr;
        initialsHats = new ArrayList<>();
        computerTook = new ArrayList<>();
        
        trainingTracker = true;
       
    }
     
    //Note to self: set input, get output 
    //No need to set number of sticks on the board or game mode, done already 

    public void setPlayerMove() {
        //Player always goes first in player versus computer
        //Otherwise they are known as Player 1
        //method to ask for player number
        Player newPlayer = new Player(playerNumber, sticksLeft);
        //sending player move data to Game 
        playerMove = newPlayer.playerMoveData();
    }

    public void setComputerMove() 
    {
        //Computer always goes second in player versus computer
        //Otherwise computer becomes Player 2 in player versus player mode 
        //To recycle variables and save memory 
        //add sticks values to the array
        if(trainingTracker == true)
        {
           initialsHats.add(sticksLeft);
        }
        
        Computer newComputer = new Computer(sticksLeft, hatsArray, gameMode, trainingTracker);
        computerMove = newComputer.computerMoveData();
        
        if(trainingTracker == true)
        {
           computerTook.add(computerMove);
        }
        
        
    }
    
    //To recycle through the play state after the player/player 1 and computer/player 2 until someone wins
    public void refreshTurn()
    {
        switch(gameMode)
        {
            case 1:turnAgainstPlayer();
            break;
            case 2:turnAgainstComputer();
            break;
            case 3:turnAgainstComputer();
            break;
            case 4:computerTraining();
            break;
        }
    }
    
    //Game State 1
    //to specify the turn type based on the game mode player v. player 
    public void turnAgainstPlayer()
    {
        //logic to determine whether player 1 or player 2 has won 
        //first the player selects the amount of sticks
        playerNumber = 1;
        setPlayerMove();
        sticksLeft = sticksLeft - playerMove;
        //if statement to test whether or not the player loses
        if (sticksLeft < 1)
        {
            playerTwoOrComputerWin = true;
        }
        else 
        {
            //second the computer selects the amount of sticks
            playerNumber = 2;
            setPlayerMove();
            sticksLeft = sticksLeft - playerMove;  
            //if statement to test whether or not the computer loses 
            if (sticksLeft < 1)
            {
                playerWin = true;
            }
        }
    }
    
    //Game State 2 (Later on 2 & 3) 
    //to specify the turn type based on the game mode player v. random computer
    public void turnAgainstComputer()
    {
        //logic to determine whether player or computer has won 
        //first the player selects the amount of sticks
        setPlayerMove();
        sticksLeft = sticksLeft - playerMove;
        //if statement to test whether or not the player loses
        if (sticksLeft < 1)
        {
            playerTwoOrComputerWin = true;
            //call a function that writes the information to the array
            writeData();
        }
        else 
        {
            //second the computer selects the amount of sticks
            setComputerMove();
            sticksLeft = sticksLeft - computerMove;  
            //if statement to test whether or not the computer loses 
            if (sticksLeft < 1)
            {
                playerWin = true;
            }
        }
    }
    
    public void computerTraining()
    {
        trainingTracker = false;
        setComputerMove();
        sticksLeft = sticksLeft - computerMove;
        //if statement to test whether or not the player loses
        if (sticksLeft < 1)
        {
            playerTwoOrComputerWin = true;
            //call a function that writes the information to the array
            writeData();
        }
        
        else 
        {
            //second the computer selects the amount of sticks
            trainingTracker = true;
            setComputerMove();
            sticksLeft = sticksLeft - computerMove;  
            //if statement to test whether or not the computer loses 
            if (sticksLeft < 1)
            {
                playerWin = true;
            }
        }
    }
    
    public void writeData()
    {
        //loop through every move computer took 
        for(int i = 0; i <= computerTook.size() - 1; i++)
        {
            hatsArray[initialsHats.get(i) - 1][computerTook.get(i) - 1][0] = hatsArray[initialsHats.get(i) - 1][computerTook.get(i) - 1][0] + 1 ;
            //test prints output 
            //System.out.println(initialsHats.get(i) + " " + computerTook.get(i) + " " + hatsArray[initialsHats.get(i) - 1][computerTook.get(i) - 1][0]);
        }
    }

    public int[][][] getHatsArray() {
        return hatsArray;
    }
    
    
    public int getSticksLeft() 
    {
        return sticksLeft;
    }

    public int getGameMode() 
    {
        return gameMode;
    }

    public int getPlayerNumber() 
    {
        return playerNumber;
    }
    
    public boolean getplayerWin()
    {
        return playerWin;
    }

    public boolean getPlayerTwoOrComputerWin() 
    {
        return playerTwoOrComputerWin;
    }
    
    
}
