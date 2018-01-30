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

public class GameDriver 
{
    private static int rules; 
    private static int numOfSticks;
    private static int gameMode;
    
    private static int[][][] hatsArray;
    
    public static void main(String[] args) 
    {
        hatsArray = new int[100][5][1];
        HatData();
        
        //establishing a Scanner for input
        Scanner nim = new Scanner(System.in);
        
        System.out.println("Welcome to the game of Nim!");
        
        //adding rules to Nim in order to optimize player experience 
        System.out.println("Would you like to hear the rules?");
        System.out.print("Input 1 for yes and 2 for no. ");
        
        //to take player rule input
        rules = nim.nextInt();
        
        //if statement to determine if player asked for rules
        if (rules == 1)
        {
            //to create spacing from the rules
            System.out.println("");
            
            //rules description
            System.out.println("In the game of Nim there is a heap of sticks on the board.");
            System.out.println("The player can input how many sticks are on the board, a value from 10-100."); 
            System.out.println("On their turn, each player (or computer) picks up from 1 to 5 sticks.");
            System.out.println("The last player who has to pick the final stick will be the loser.");
        }
        
        gameMode = 0;
        
        while(gameMode < 5 && gameMode >= 0)
        {
            
            //to create spacing
            System.out.println("");
            
          //swtich case for game mode, and game mode descriptions
            System.out.println("Select a game mode:");
        
            //to create spacing
            System.out.println("");
        
            System.out.println("Play against a friend (Mode 1)");
            System.out.println("Play against the computer, Marty, with randomized moves (Mode 2)");
            System.out.println("Play against the trained computer, Doc Brown (Mode 3)");
            System.out.println("Train Doc Brown to win against Biff Tannen.(Mode 4)");
            System.out.println("To exit type -1");
        
            //to create spacing
            System.out.println("");
        
            //asking for player input on game mode
            System.out.print("Which mode would you like to play? (1-4) ");
        
            //to take player game mode input
            gameMode = nim.nextInt();
        
            //to create spacing
            System.out.println("");
        
            
            if(gameMode != -1)
            {
            	//player choses how many sticks are on the table
                System.out.print("How many sticks are you playing with on the table? (10-100) ");
            
                //to take player stick input
                numOfSticks = nim.nextInt();
            
                //a while loop to check to make sure the player inputs at least 10 sticks but less than 100 sticks
                while(numOfSticks > 100 || numOfSticks < 10)
                {
                	System.out.print("That is an invalid number of sticks, please enter another value between 10 and 100. ");
                	numOfSticks = nim.nextInt();
                }
            
                //to create spacing
                System.out.println("");
            	//report the initial number of sticks on the board 
            	System.out.println("There are " + numOfSticks + " sticks on the board.");

            }
        
            
            if(gameMode == 4)
            {
            	//to ask the player for the number of runs
            	System.out.println("How many games would you like to train Doc Brown on? Enter a value.");
            	//to take the number of training runs
            	Integer numberOfRuns = nim.nextInt();
            
            	for(int i = 0; i < numberOfRuns; i++)
            	{
            		Game newGame = new Game(numOfSticks, gameMode, hatsArray);
            		while(newGame.getPlayerTwoOrComputerWin() == false && newGame.getplayerWin() == false)
            		{  
            			//restart the game to train the AI multiple times
            			newGame.refreshTurn();
            		}

            		hatsArray = newGame.getHatsArray();  
            	}
                
                //a test print of the data in the array to test to see if the AI is learning 
            	//for(int s = 0; s < 100; s++)
                //{
                    //for(int j = 0; j < 5; j++)
                    //{
                        //System.out.println("hatsArray[" + (s) + "][" + (j) + "][0] = " + hatsArray[s][j][0] + ";");
                    //}
                //}
             
            	System.out.println("Doc Brown's training is complete! You have trained him for a total of " + numberOfRuns + " games.");
            }
        
            if(gameMode <= 3 && gameMode > 0)
            {
            	//creating a new game object 
            	Game newGame  = new Game(numOfSticks, gameMode, hatsArray);
            
            	//tests to see if someone wins, if not it refreshes the game 
            	while(newGame.getPlayerTwoOrComputerWin() == false && newGame.getplayerWin() == false)
            	{  
            		//start the game
            		newGame.refreshTurn();

            		//to create spacing
            		System.out.println("");

            		//report the number of sticks on the board 
            		System.out.println("There are " + newGame.getSticksLeft() + " sticks on the board.");
                
            		if(newGame.getPlayerTwoOrComputerWin() == true)
            		{
            			//to create spacing
            			System.out.println(""); 
                    
            			//to display the computer name 
            			if(gameMode > 1) 
            			{
            				if(gameMode == 2)
            				{
            					System.out.println("The computer, Marty, wins!");
            				}
            				else 
            				{
            					System.out.println("The computer, Doc Brown, wins!");  
            				}    
            			}

            			//to display Player 2 name in player v. player
            			else
            			{
            				System.out.println("Player 2, you win!");
                   
            			} 
            			//to create spacing
            			System.out.println(""); 
                    
            			System.out.println("Player 1, you lose");
                    
            			//to create spacing
            			System.out.println(""); 
            		}
            		if(newGame.getplayerWin()== true)
            		{
            			//to create spacing
            			System.out.println(""); 
                    
            			System.out.println("Player 1, you win!");
                    
            			//to create spacing
            			System.out.println(""); 
                    
            			//to display the computer name 
            			if(gameMode > 1) 
            			{
            				if(gameMode == 2)
            				{
            					System.out.println("The computer, Marty, loses!");
            				}
            				else 
            				{
            					System.out.println("The computer, Doc Brown, loses!");  
            				}    
            			}
                    
            			//to display Player 2 name in player v. player
            			else
            			{
            				System.out.println("Player 2, you lose!");
                   
            			} 
                    
                    
            			//to create spacing
            			System.out.println(""); 
            		}
            	}
            }
            else
            {
            	if(gameMode != 4 && gameMode != -1)
            	{
            		//to create spacing
            		System.out.println("");
            
            		//to let the player know if they enter an invalid game mode number
            		System.out.println("This input is invalid, please enter another value (1-3).");
            		gameMode = nim.nextInt();
            	}
            }
        }  
    }
    
    public static void HatData()
    {
    	hatsArray[0][0][0] = 0;
    	hatsArray[0][1][0] = 0;
    	hatsArray[0][2][0] = 0;
    	hatsArray[0][3][0] = 0;
    	hatsArray[0][4][0] = 0;
    	hatsArray[1][0][0] = 79;
    	hatsArray[1][1][0] = 0;
    	hatsArray[1][2][0] = 0;
    	hatsArray[1][3][0] = 0;
    	hatsArray[1][4][0] = 0;
    	hatsArray[2][0][0] = 4;
    	hatsArray[2][1][0] = 196;
    	hatsArray[2][2][0] = 0;
    	hatsArray[2][3][0] = 0;
    	hatsArray[2][4][0] = 0;
    	hatsArray[3][0][0] = 0;
    	hatsArray[3][1][0] = 1;
    	hatsArray[3][2][0] = 172;
    	hatsArray[3][3][0] = 0;
    	hatsArray[3][4][0] = 0;
    	hatsArray[4][0][0] = 7;
    	hatsArray[4][1][0] = 10;
    	hatsArray[4][2][0] = 0;
    	hatsArray[4][3][0] = 747;
    	hatsArray[4][4][0] = 0;
    	hatsArray[5][0][0] = 24;
    	hatsArray[5][1][0] = 1;
    	hatsArray[5][2][0] = 0;
    	hatsArray[5][3][0] = 0;
    	hatsArray[5][4][0] = 1332;
    	hatsArray[6][0][0] = 94;
    	hatsArray[6][1][0] = 36;
    	hatsArray[6][2][0] = 5;
    	hatsArray[6][3][0] = 7;
    	hatsArray[6][4][0] = 1;
    	hatsArray[7][0][0] = 248;
    	hatsArray[7][1][0] = 5;
    	hatsArray[7][2][0] = 5;
    	hatsArray[7][3][0] = 2;
    	hatsArray[7][4][0] = 7;
    	hatsArray[8][0][0] = 20;
    	hatsArray[8][1][0] = 615;
    	hatsArray[8][2][0] = 2;
    	hatsArray[8][3][0] = 23;
    	hatsArray[8][4][0] = 4;
    	hatsArray[9][0][0] = 12;
    	hatsArray[9][1][0] = 10;
    	hatsArray[9][2][0] = 447;
    	hatsArray[9][3][0] = 38;
    	hatsArray[9][4][0] = 4;
    	hatsArray[10][0][0] = 13;
    	hatsArray[10][1][0] = 26;
    	hatsArray[10][2][0] = 56;
    	hatsArray[10][3][0] = 140;
    	hatsArray[10][4][0] = 3;
    	hatsArray[11][0][0] = 22;
    	hatsArray[11][1][0] = 47;
    	hatsArray[11][2][0] = 18;
    	hatsArray[11][3][0] = 26;
    	hatsArray[11][4][0] = 477;
    	hatsArray[12][0][0] = 8;
    	hatsArray[12][1][0] = 140;
    	hatsArray[12][2][0] = 125;
    	hatsArray[12][3][0] = 11;
    	hatsArray[12][4][0] = 0;
    	hatsArray[13][0][0] = 31;
    	hatsArray[13][1][0] = 16;
    	hatsArray[13][2][0] = 22;
    	hatsArray[13][3][0] = 49;
    	hatsArray[13][4][0] = 148;
    	hatsArray[14][0][0] = 117;
    	hatsArray[14][1][0] = 134;
    	hatsArray[14][2][0] = 9;
    	hatsArray[14][3][0] = 36;
    	hatsArray[14][4][0] = 11;
    	hatsArray[15][0][0] = 72;
    	hatsArray[15][1][0] = 236;
    	hatsArray[15][2][0] = 113;
    	hatsArray[15][3][0] = 183;
    	hatsArray[15][4][0] = 20;
    	hatsArray[16][0][0] = 27;
    	hatsArray[16][1][0] = 10;
    	hatsArray[16][2][0] = 168;
    	hatsArray[16][3][0] = 80;
    	hatsArray[16][4][0] = 18;
    	hatsArray[17][0][0] = 20;
    	hatsArray[17][1][0] = 542;
    	hatsArray[17][2][0] = 23;
    	hatsArray[17][3][0] = 134;
    	hatsArray[17][4][0] = 113;
    	hatsArray[18][0][0] = 253;
    	hatsArray[18][1][0] = 11;
    	hatsArray[18][2][0] = 11;
    	hatsArray[18][3][0] = 90;
    	hatsArray[18][4][0] = 38;
    	hatsArray[19][0][0] = 145;
    	hatsArray[19][1][0] = 108;
    	hatsArray[19][2][0] = 118;
    	hatsArray[19][3][0] = 51;
    	hatsArray[19][4][0] = 22;
    	hatsArray[20][0][0] = 232;
    	hatsArray[20][1][0] = 8;
    	hatsArray[20][2][0] = 285;
    	hatsArray[20][3][0] = 26;
    	hatsArray[20][4][0] = 74;
    	hatsArray[21][0][0] = 67;
    	hatsArray[21][1][0] = 135;
    	hatsArray[21][2][0] = 158;
    	hatsArray[21][3][0] = 38;
    	hatsArray[21][4][0] = 16;
    	hatsArray[22][0][0] = 88;
    	hatsArray[22][1][0] = 8;
    	hatsArray[22][2][0] = 62;
    	hatsArray[22][3][0] = 121;
    	hatsArray[22][4][0] = 1;
    	hatsArray[23][0][0] = 87;
    	hatsArray[23][1][0] = 137;
    	hatsArray[23][2][0] = 191;
    	hatsArray[23][3][0] = 94;
    	hatsArray[23][4][0] = 30;
    	hatsArray[24][0][0] = 84;
    	hatsArray[24][1][0] = 56;
    	hatsArray[24][2][0] = 81;
    	hatsArray[24][3][0] = 42;
    	hatsArray[24][4][0] = 47;
    	hatsArray[25][0][0] = 92;
    	hatsArray[25][1][0] = 128;
    	hatsArray[25][2][0] = 1;
    	hatsArray[25][3][0] = 21;
    	hatsArray[25][4][0] = 321;
    	hatsArray[26][0][0] = 18;
    	hatsArray[26][1][0] = 2;
    	hatsArray[26][2][0] = 53;
    	hatsArray[26][3][0] = 58;
    	hatsArray[26][4][0] = 61;
    	hatsArray[27][0][0] = 65;
    	hatsArray[27][1][0] = 149;
    	hatsArray[27][2][0] = 38;
    	hatsArray[27][3][0] = 148;
    	hatsArray[27][4][0] = 20;
    	hatsArray[28][0][0] = 168;
    	hatsArray[28][1][0] = 93;
    	hatsArray[28][2][0] = 203;
    	hatsArray[28][3][0] = 8;
    	hatsArray[28][4][0] = 143;
    	hatsArray[29][0][0] = 129;
    	hatsArray[29][1][0] = 62;
    	hatsArray[29][2][0] = 49;
    	hatsArray[29][3][0] = 150;
    	hatsArray[29][4][0] = 179;
    	hatsArray[30][0][0] = 12;
    	hatsArray[30][1][0] = 32;
    	hatsArray[30][2][0] = 134;
    	hatsArray[30][3][0] = 9;
    	hatsArray[30][4][0] = 20;
    	hatsArray[31][0][0] = 54;
    	hatsArray[31][1][0] = 42;
    	hatsArray[31][2][0] = 127;
    	hatsArray[31][3][0] = 8;
    	hatsArray[31][4][0] = 8;
    	hatsArray[32][0][0] = 37;
    	hatsArray[32][1][0] = 15;
    	hatsArray[32][2][0] = 155;
    	hatsArray[32][3][0] = 242;
    	hatsArray[32][4][0] = 21;
    	hatsArray[33][0][0] = 140;
    	hatsArray[33][1][0] = 1;
    	hatsArray[33][2][0] = 38;
    	hatsArray[33][3][0] = 260;
    	hatsArray[33][4][0] = 105;
    	hatsArray[34][0][0] = 169;
    	hatsArray[34][1][0] = 46;
    	hatsArray[34][2][0] = 37;
    	hatsArray[34][3][0] = 97;
    	hatsArray[34][4][0] = 103;
    	hatsArray[35][0][0] = 56;
    	hatsArray[35][1][0] = 47;
    	hatsArray[35][2][0] = 137;
    	hatsArray[35][3][0] = 70;
    	hatsArray[35][4][0] = 5;
    	hatsArray[36][0][0] = 75;
    	hatsArray[36][1][0] = 145;
    	hatsArray[36][2][0] = 94;
    	hatsArray[36][3][0] = 13;
    	hatsArray[36][4][0] = 58;
    	hatsArray[37][0][0] = 62;
    	hatsArray[37][1][0] = 65;
    	hatsArray[37][2][0] = 195;
    	hatsArray[37][3][0] = 10;
    	hatsArray[37][4][0] = 154;
    	hatsArray[38][0][0] = 1;
    	hatsArray[38][1][0] = 73;
    	hatsArray[38][2][0] = 4;
    	hatsArray[38][3][0] = 87;
    	hatsArray[38][4][0] = 197;
    	hatsArray[39][0][0] = 5;
    	hatsArray[39][1][0] = 1;
    	hatsArray[39][2][0] = 104;
    	hatsArray[39][3][0] = 1;
    	hatsArray[39][4][0] = 14;
    	hatsArray[40][0][0] = 22;
    	hatsArray[40][1][0] = 54;
    	hatsArray[40][2][0] = 120;
    	hatsArray[40][3][0] = 159;
    	hatsArray[40][4][0] = 155;
    	hatsArray[41][0][0] = 85;
    	hatsArray[41][1][0] = 17;
    	hatsArray[41][2][0] = 60;
    	hatsArray[41][3][0] = 232;
    	hatsArray[41][4][0] = 8;
    	hatsArray[42][0][0] = 45;
    	hatsArray[42][1][0] = 48;
    	hatsArray[42][2][0] = 19;
    	hatsArray[42][3][0] = 14;
    	hatsArray[42][4][0] = 160;
    	hatsArray[43][0][0] = 44;
    	hatsArray[43][1][0] = 16;
    	hatsArray[43][2][0] = 49;
    	hatsArray[43][3][0] = 39;
    	hatsArray[43][4][0] = 207;
    	hatsArray[44][0][0] = 5;
    	hatsArray[44][1][0] = 26;
    	hatsArray[44][2][0] = 140;
    	hatsArray[44][3][0] = 224;
    	hatsArray[44][4][0] = 0;
    	hatsArray[45][0][0] = 68;
    	hatsArray[45][1][0] = 154;
    	hatsArray[45][2][0] = 111;
    	hatsArray[45][3][0] = 118;
    	hatsArray[45][4][0] = 105;
    	hatsArray[46][0][0] = 269;
    	hatsArray[46][1][0] = 125;
    	hatsArray[46][2][0] = 50;
    	hatsArray[46][3][0] = 79;
    	hatsArray[46][4][0] = 80;
    	hatsArray[47][0][0] = 12;
    	hatsArray[47][1][0] = 54;
    	hatsArray[47][2][0] = 106;
    	hatsArray[47][3][0] = 103;
    	hatsArray[47][4][0] = 58;
    	hatsArray[48][0][0] = 80;
    	hatsArray[48][1][0] = 39;
    	hatsArray[48][2][0] = 174;
    	hatsArray[48][3][0] = 69;
    	hatsArray[48][4][0] = 37;
    	hatsArray[49][0][0] = 142;
    	hatsArray[49][1][0] = 47;
    	hatsArray[49][2][0] = 473;
    	hatsArray[49][3][0] = 61;
    	hatsArray[49][4][0] = 36;
    	hatsArray[50][0][0] = 460;
    	hatsArray[50][1][0] = 26;
    	hatsArray[50][2][0] = 59;
    	hatsArray[50][3][0] = 5;
    	hatsArray[50][4][0] = 2;
    	hatsArray[51][0][0] = 51;
    	hatsArray[51][1][0] = 85;
    	hatsArray[51][2][0] = 50;
    	hatsArray[51][3][0] = 5;
    	hatsArray[51][4][0] = 78;
    	hatsArray[52][0][0] = 17;
    	hatsArray[52][1][0] = 262;
    	hatsArray[52][2][0] = 92;
    	hatsArray[52][3][0] = 149;
    	hatsArray[52][4][0] = 121;
    	hatsArray[53][0][0] = 54;
    	hatsArray[53][1][0] = 119;
    	hatsArray[53][2][0] = 18;
    	hatsArray[53][3][0] = 31;
    	hatsArray[53][4][0] = 18;
    	hatsArray[54][0][0] = 27;
    	hatsArray[54][1][0] = 52;
    	hatsArray[54][2][0] = 18;
    	hatsArray[54][3][0] = 211;
    	hatsArray[54][4][0] = 42;
    	hatsArray[55][0][0] = 17;
    	hatsArray[55][1][0] = 74;
    	hatsArray[55][2][0] = 35;
    	hatsArray[55][3][0] = 103;
    	hatsArray[55][4][0] = 27;
    	hatsArray[56][0][0] = 97;
    	hatsArray[56][1][0] = 2;
    	hatsArray[56][2][0] = 11;
    	hatsArray[56][3][0] = 143;
    	hatsArray[56][4][0] = 26;
    	hatsArray[57][0][0] = 6;
    	hatsArray[57][1][0] = 86;
    	hatsArray[57][2][0] = 3;
    	hatsArray[57][3][0] = 76;
    	hatsArray[57][4][0] = 449;
    	hatsArray[58][0][0] = 78;
    	hatsArray[58][1][0] = 57;
    	hatsArray[58][2][0] = 17;
    	hatsArray[58][3][0] = 6;
    	hatsArray[58][4][0] = 23;
    	hatsArray[59][0][0] = 9;
    	hatsArray[59][1][0] = 250;
    	hatsArray[59][2][0] = 59;
    	hatsArray[59][3][0] = 40;
    	hatsArray[59][4][0] = 288;
    	hatsArray[60][0][0] = 187;
    	hatsArray[60][1][0] = 64;
    	hatsArray[60][2][0] = 56;
    	hatsArray[60][3][0] = 26;
    	hatsArray[60][4][0] = 48;
    	hatsArray[61][0][0] = 98;
    	hatsArray[61][1][0] = 84;
    	hatsArray[61][2][0] = 17;
    	hatsArray[61][3][0] = 136;
    	hatsArray[61][4][0] = 89;
    	hatsArray[62][0][0] = 69;
    	hatsArray[62][1][0] = 72;
    	hatsArray[62][2][0] = 201;
    	hatsArray[62][3][0] = 43;
    	hatsArray[62][4][0] = 73;
    	hatsArray[63][0][0] = 30;
    	hatsArray[63][1][0] = 16;
    	hatsArray[63][2][0] = 113;
    	hatsArray[63][3][0] = 19;
    	hatsArray[63][4][0] = 36;
    	hatsArray[64][0][0] = 117;
    	hatsArray[64][1][0] = 85;
    	hatsArray[64][2][0] = 4;
    	hatsArray[64][3][0] = 72;
    	hatsArray[64][4][0] = 193;
    	hatsArray[65][0][0] = 49;
    	hatsArray[65][1][0] = 26;
    	hatsArray[65][2][0] = 57;
    	hatsArray[65][3][0] = 56;
    	hatsArray[65][4][0] = 36;
    	hatsArray[66][0][0] = 52;
    	hatsArray[66][1][0] = 148;
    	hatsArray[66][2][0] = 70;
    	hatsArray[66][3][0] = 189;
    	hatsArray[66][4][0] = 260;
    	hatsArray[67][0][0] = 89;
    	hatsArray[67][1][0] = 37;
    	hatsArray[67][2][0] = 125;
    	hatsArray[67][3][0] = 20;
    	hatsArray[67][4][0] = 87;
    	hatsArray[68][0][0] = 110;
    	hatsArray[68][1][0] = 21;
    	hatsArray[68][2][0] = 75;
    	hatsArray[68][3][0] = 79;
    	hatsArray[68][4][0] = 1;
    	hatsArray[69][0][0] = 103;
    	hatsArray[69][1][0] = 0;
    	hatsArray[69][2][0] = 111;
    	hatsArray[69][3][0] = 80;
    	hatsArray[69][4][0] = 51;
    	hatsArray[70][0][0] = 48;
    	hatsArray[70][1][0] = 88;
    	hatsArray[70][2][0] = 207;
    	hatsArray[70][3][0] = 301;
    	hatsArray[70][4][0] = 10;
    	hatsArray[71][0][0] = 11;
    	hatsArray[71][1][0] = 39;
    	hatsArray[71][2][0] = 46;
    	hatsArray[71][3][0] = 25;
    	hatsArray[71][4][0] = 167;
    	hatsArray[72][0][0] = 39;
    	hatsArray[72][1][0] = 47;
    	hatsArray[72][2][0] = 228;
    	hatsArray[72][3][0] = 19;
    	hatsArray[72][4][0] = 48;
    	hatsArray[73][0][0] = 48;
    	hatsArray[73][1][0] = 67;
    	hatsArray[73][2][0] = 86;
    	hatsArray[73][3][0] = 6;
    	hatsArray[73][4][0] = 18;
    	hatsArray[74][0][0] = 8;
    	hatsArray[74][1][0] = 41;
    	hatsArray[74][2][0] = 118;
    	hatsArray[74][3][0] = 84;
    	hatsArray[74][4][0] = 31;
    	hatsArray[75][0][0] = 10;
    	hatsArray[75][1][0] = 33;
    	hatsArray[75][2][0] = 78;
    	hatsArray[75][3][0] = 30;
    	hatsArray[75][4][0] = 416;
    	hatsArray[76][0][0] = 112;
    	hatsArray[76][1][0] = 83;
    	hatsArray[76][2][0] = 120;
    	hatsArray[76][3][0] = 153;
    	hatsArray[76][4][0] = 32;
    	hatsArray[77][0][0] = 81;
    	hatsArray[77][1][0] = 232;
    	hatsArray[77][2][0] = 31;
    	hatsArray[77][3][0] = 20;
    	hatsArray[77][4][0] = 71;
    	hatsArray[78][0][0] = 12;
    	hatsArray[78][1][0] = 150;
    	hatsArray[78][2][0] = 183;
    	hatsArray[78][3][0] = 79;
    	hatsArray[78][4][0] = 37;
    	hatsArray[79][0][0] = 176;
    	hatsArray[79][1][0] = 79;
    	hatsArray[79][2][0] = 15;
    	hatsArray[79][3][0] = 56;
    	hatsArray[79][4][0] = 56;
    	hatsArray[80][0][0] = 2;
    	hatsArray[80][1][0] = 153;
    	hatsArray[80][2][0] = 32;
    	hatsArray[80][3][0] = 38;
    	hatsArray[80][4][0] = 27;
    	hatsArray[81][0][0] = 3;
    	hatsArray[81][1][0] = 63;
    	hatsArray[81][2][0] = 3;
    	hatsArray[81][3][0] = 147;
    	hatsArray[81][4][0] = 228;
    	hatsArray[82][0][0] = 51;
    	hatsArray[82][1][0] = 191;
    	hatsArray[82][2][0] = 68;
    	hatsArray[82][3][0] = 79;
    	hatsArray[82][4][0] = 133;
    	hatsArray[83][0][0] = 83;
    	hatsArray[83][1][0] = 12;
    	hatsArray[83][2][0] = 84;
    	hatsArray[83][3][0] = 215;
    	hatsArray[83][4][0] = 25;
    	hatsArray[84][0][0] = 44;
    	hatsArray[84][1][0] = 123;
    	hatsArray[84][2][0] = 308;
    	hatsArray[84][3][0] = 3;
    	hatsArray[84][4][0] = 17;
    	hatsArray[85][0][0] = 17;
    	hatsArray[85][1][0] = 122;
    	hatsArray[85][2][0] = 73;
    	hatsArray[85][3][0] = 80;
    	hatsArray[85][4][0] = 7;
    	hatsArray[86][0][0] = 57;
    	hatsArray[86][1][0] = 278;
    	hatsArray[86][2][0] = 8;
    	hatsArray[86][3][0] = 127;
    	hatsArray[86][4][0] = 20;
    	hatsArray[87][0][0] = 45;
    	hatsArray[87][1][0] = 53;
    	hatsArray[87][2][0] = 32;
    	hatsArray[87][3][0] = 43;
    	hatsArray[87][4][0] = 117;
    	hatsArray[88][0][0] = 217;
    	hatsArray[88][1][0] = 212;
    	hatsArray[88][2][0] = 176;
    	hatsArray[88][3][0] = 14;
    	hatsArray[88][4][0] = 193;
    	hatsArray[89][0][0] = 58;
    	hatsArray[89][1][0] = 14;
    	hatsArray[89][2][0] = 109;
    	hatsArray[89][3][0] = 5;
    	hatsArray[89][4][0] = 104;
    	hatsArray[90][0][0] = 20;
    	hatsArray[90][1][0] = 183;
    	hatsArray[90][2][0] = 15;
    	hatsArray[90][3][0] = 104;
    	hatsArray[90][4][0] = 13;
    	hatsArray[91][0][0] = 60;
    	hatsArray[91][1][0] = 194;
    	hatsArray[91][2][0] = 561;
    	hatsArray[91][3][0] = 1;
    	hatsArray[91][4][0] = 75;
    	hatsArray[92][0][0] = 89;
    	hatsArray[92][1][0] = 8;
    	hatsArray[92][2][0] = 11;
    	hatsArray[92][3][0] = 3;
    	hatsArray[92][4][0] = 12;
    	hatsArray[93][0][0] = 4;
    	hatsArray[93][1][0] = 157;
    	hatsArray[93][2][0] = 59;
    	hatsArray[93][3][0] = 16;
    	hatsArray[93][4][0] = 43;
    	hatsArray[94][0][0] = 277;
    	hatsArray[94][1][0] = 53;
    	hatsArray[94][2][0] = 106;
    	hatsArray[94][3][0] = 65;
    	hatsArray[94][4][0] = 56;
    	hatsArray[95][0][0] = 105;
    	hatsArray[95][1][0] = 108;
    	hatsArray[95][2][0] = 42;
    	hatsArray[95][3][0] = 280;
    	hatsArray[95][4][0] = 25;
    	hatsArray[96][0][0] = 149;
    	hatsArray[96][1][0] = 78;
    	hatsArray[96][2][0] = 15;
    	hatsArray[96][3][0] = 103;
    	hatsArray[96][4][0] = 187;
    	hatsArray[97][0][0] = 6;
    	hatsArray[97][1][0] = 53;
    	hatsArray[97][2][0] = 162;
    	hatsArray[97][3][0] = 70;
    	hatsArray[97][4][0] = 189;
    	hatsArray[98][0][0] = 77;
    	hatsArray[98][1][0] = 73;
    	hatsArray[98][2][0] = 26;
    	hatsArray[98][3][0] = 115;
    	hatsArray[98][4][0] = 235;
    	hatsArray[99][0][0] = 0;
    	hatsArray[99][1][0] = 0;
    	hatsArray[99][2][0] = 0;
    	hatsArray[99][3][0] = 0;
    	hatsArray[99][4][0] = 0;
    }
}
