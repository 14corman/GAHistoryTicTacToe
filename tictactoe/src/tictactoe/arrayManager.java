/*
 * This code is used to implement a tic tac toe game.
 * That game is then played with a player and a computer.
 * The computer then learns over time to play better.
 */
package tictactoe;

import java.util.ArrayList;

/**
 *
 * @author Cory Edwards
 */
public class arrayManager {
    
    //000000000 means no one has made a move yet.
    public static String board = "000000000";
    
    //Check to see if the move the computer just made is in its knowledge
    //base of moves it knows.If not then put it in there.
    public static void addMove(int move)
    {
        boolean added = false;
        registerMove();
        for (Object cpuMove : game.cpuMoves) 
        {
            ArrayList temp = (ArrayList) cpuMove;
            
            //Check if the board of the move just made is the same
            //as the any move in the knowledge base.
            boolean boardMoves = board.equals(((String) temp.get(0)));
            
            //If the move matches number and board layout, then it has 
            //already been added.
            if(Integer.toString(move).equals((String) temp.get(1)) && boardMoves)
                added = true; 
        }
        
        if(!added)
        {
            ArrayList temp = new ArrayList();
            temp.add(board);
            temp.add(Integer.toString(move));
            game.cpuMoves.add(temp);
        }
    }
    
    //This changed the board layout each move to represent where 
    //a move has been played and by who.
    public static void registerMove()
    {
        //Turn the board into a character array to itterate over every cell.
        char[] charBoard = board.toCharArray();
        
        ///Checks every number of the player array. New numbers change the
        //spot on the board to a 1.
        for (Object player : game.player) {
            int x = (int) player;
            //System.out.println("number going in: " + x);
            charBoard[x - 1] = '1';
        }
        
        for (Object cpu : game.cpu) {
            int x = (int) cpu;
            //System.out.println("number going in: " + x);
            charBoard[x - 1] = '2';
        }
        
        //Reset the board then set the new changes.
        board = "";
        for(int i = 0; i < charBoard.length; i++)
        {
            board += charBoard[i];
        }
    }   
}
