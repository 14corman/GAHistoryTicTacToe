/*
 * This code is used to implement a tic tac toe game.
 * That game is then played with a player and a computer.
 * The computer then learns over time to play better.
 */
package tictactoe;

import java.util.Scanner;

/**
 *
 * @author Cory Edwards
 */

//Code for player input.
public class input {

    //Will be called when the player has to make his/her turn.
    public static int turn()
    {
        try
        {
            Scanner move = new Scanner(System.in);
            return move.nextInt();
        } 
        catch (Exception e)
        {
            //If something happened, return a 10. Since it is out of the
            //scope between 1 - 9, the computer will say it is an invalid move.
            return 10;
        }
    }
    
    public static int cTurn()
    {
        //Loos at the bridgem.txt file for the move.
        return outIn.read();
    }
    
}
