/*
 * This code is used to implement a tic tac toe game.
 * That game is then played with a player and a computer.
 * The computer then learns over time to play better.
 */
package tictactoe;

import java.util.ArrayList;
import static java.util.Arrays.sort;
import org.jgap.IChromosome;

/**
 *
 * @author Cory Edwards
 */
public class turnCheck {
   
    //Checks if someone has won and sends an answer back in a boolean.
    public static boolean check(ArrayList list)
    {
        boolean win = false;
        
        if(list.contains(1) && list.contains(2) && list.contains(3))
            win = true;
        
        if(list.contains(4) && list.contains(5) && list.contains(6))
            win = true;
        
        if(list.contains(7) && list.contains(8) && list.contains(9))
            win = true;
        
        if(list.contains(1) && list.contains(4) && list.contains(7))
            win = true;
        
        if(list.contains(2) && list.contains(5) && list.contains(8))
            win = true;
        
        if(list.contains(3) && list.contains(6) && list.contains(9))
            win = true;
        
        if(list.contains(1) && list.contains(5) && list.contains(9))
            win = true;
        
        if(list.contains(3) && list.contains(5) && list.contains(7))
            win = true;
        
        return win;
    }
    
    //Evaluate function comes in here to create the fitness number.
    public static double fitCheck(IChromosome a_subject)
    {
        //Make the ArrayList of the 2 players into sorted arrays
        //for greater efficentcy.
        int[] player = arrayInit(game.player);
        int[] cpu = arrayInit(game.cpu);
        
        //Get the mve of the selected gene.
        int move = (int) a_subject.getGene(0).getAllele();
        
        //Get the fit value with each array and add them together
        //to make the overall fitness value.
        return fitAdder(player, move) + fitAdder(cpu, move);
    }
    
    //Get half of the fitness value.
    public static double fitAdder(int[] temp, int move)
    {
        ArrayList moves = new ArrayList();
        double fitness = 0;
        
        //Have each move that has been played iterate over each other 
        //move to see if there is a possiblity of winning/losing for
        //the computer.
        for(int h = 0; h < temp.length; h++)
        {
            for(int j = 0; j < temp.length; j++)
            {
                int i = temp[h];
                int x = temp[j];
                
                //Check to see if the next move will win.
                moves.add(i);
                moves.add(x);
                moves.add(move);
                
                if(check(moves))
                    fitness += 500;
                
                moves.clear();
                
                //Options where there is an open cell in between.
                if(i == 7 && x == 1 && move == 4)
                    fitness +=5;
                
                if(i == 1 && x == 7 && move == 4)
                    fitness +=5;
                
                if(i == 7 && x == 9 && move == 8)
                    fitness +=5;
                
                if(i == 9 && x == 7 && move == 8)
                    fitness +=5;
                
                if(i == 9 && x == 3 && move == 6)
                    fitness +=5;
                
                if(i == 3 && x == 9 && move == 6)
                    fitness +=5;
                
                if(i == 1 && x == 3 && move == 2)
                    fitness +=5;
                
                if(i == 3 && x == 1 && move == 2)
                    fitness +=5;
                
                if(i == 1 && x == 9 && move == 5)
                    fitness +=5;
                
                if(i == 9 && x == 1 && move == 5)
                    fitness +=5;
                
                if(i == 7 && x == 3 && move == 5)
                    fitness +=5;
                
                if(i == 3 && x == 7 && move == 5)
                    fitness +=5;
                
                //Options to win with cells side by side.
                if(i == 1 && x == 2 && move == 3)
                    fitness +=5;
                
                if(i == 1 && x == 2 && move == 3)
                    fitness +=5;
                
                if(i == 3 && x == 2 && move == 1)
                    fitness +=5;
                
                if(i == 2 && x == 3 && move == 1)
                    fitness +=5;
                
                if(i == 4 && x == 5 && move == 6)
                    fitness +=5;
                
                if(i == 5 && x == 4 && move == 6)
                    fitness +=5;
                
                if(i == 5 && x == 6 && move == 4)
                    fitness +=5;
                
                if(i == 6 && x == 5 && move == 4)
                    fitness +=5;
                
                if(i == 7 && x == 8 && move == 9)
                    fitness +=5;
                
                if(i == 8 && x == 7 && move == 9)
                    fitness +=5;
                
                if(i == 8 && x == 9 && move == 7)
                    fitness +=5;
                
                if(i == 9 && x == 8 && move == 7)
                    fitness +=5;
                
                if(i == 1 && x == 4 && move == 7)
                    fitness +=5;
                
                if(i == 4 && x == 1 && move == 7)
                    fitness +=5;
                
                if(i == 4 && x == 7 && move == 1)
                    fitness +=5;
                
                if(i == 7 && x == 4 && move == 1)
                    fitness +=5;
                
                if(i == 2 && x == 5 && move == 8)
                    fitness +=5;
                
                if(i == 5 && x == 2 && move == 8)
                    fitness +=5;
                
                if(i == 5 && x == 8 && move == 2)
                    fitness +=5;
                
                if(i == 8 && x == 5 && move == 2)
                    fitness +=5;
                
                if(i == 3 && x == 6 && move == 9)
                    fitness +=5;
                
                if(i == 6 && x == 3 && move == 9)
                    fitness +=5;
                
                if(i == 6 && x == 9 && move == 3)
                    fitness +=5;
                
                if(i == 9 && x == 6 && move == 3)
                    fitness +=5;
                
                //Options to win diagonally.
                if(i == 1 && x == 5 && move == 9)
                    fitness +=5;
                
                if(i == 5 && x == 1 && move == 9)
                    fitness +=5;
                
                if(i == 5 && x == 9 && move == 1)
                    fitness +=5;
                
                if(i == 9 && x == 5 && move == 1)
                    fitness +=5;
                
                if(i == 3 && x == 5 && move == 7)
                    fitness +=5;
                
                if(i == 5 && x == 3 && move == 7)
                    fitness +=5;
                
                if(i == 7 && x == 5 && move == 3)
                    fitness +=5;
                
                if(i == 5 && x == 7 && move == 3)
                    fitness +=5;
                
                //If the selected move has already been made, make its
                //fitness value 0 since it is obselete.
                if(!game.availableSpace.contains(move))
                    fitness = 0;
            }
        }    
        
        return fitness;
    }
    
    //Make the ArrayList into an array and sort it.
    public static int[] arrayInit(ArrayList list)
    {
        int[] temp = new int[list.size()];
        for (int i=0; i < temp.length; i++)
        {       
            temp[i] = (int) list.get(i);
        }
        
        sort(temp);
        
        return temp;
    }
}
