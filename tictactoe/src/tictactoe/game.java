/*
 * This code is used to implement a tic tac toe game.
 * That game is then played with a player and a computer.
 * The computer then learns over time to play better.
 */
package tictactoe;

import java.util.ArrayList;
import java.util.List;
import org.jgap.IChromosome;

//The format that this code uses is each cell in a tic tac toe
//game represents a number. This number is used when making moves.
//    1 | 2 | 3
//    --|---|--
//    4 | 5 | 6
//    --|---|--
//    7 | 8 | 9

/**
 *
 * @author Cory Edwards
 */
public class game {

    //What holds all the information for every move known.
    public static ArrayList cpuMoves = new ArrayList();
    
    //The spaces left in play.
    public static ArrayList availableSpace = new ArrayList();
    
    //The moves the computer has played.
    public static ArrayList cpu = new ArrayList();
    
    //The moves the player has made.
    public static ArrayList player = new ArrayList();
    
    //For holding the moves that the computer moves to give them their
    //W, D, or L depending on the ending of the game.
    public static ArrayList cpuMoveFiller = new ArrayList();
    
    //If false you will play the random computer, and if true
    //you play against the learning computer.
    public static boolean learning = true;
    
    //To have either the learning computer face another computer for 
    //fast learning (true), or you face the learning computer (false).
    public static boolean rvl = false;
    
    //If the computer wins.
    public static boolean cpuWin = false;
    
    //If the computer loses.
    public static boolean cpuLose = false;
    
    /**
     * @param args the command line arguments
     */
    
    //Shows the current board after every move.
    public static void showBoard()
    {
        //These string change to show who moves where.
        String a = "1", b = "2", c = "3", d = "4", e = "5", f = "6", 
                g = "7", h = "8", i = "9";
        
        //Go through the board string to see if someone actually moved.
        for(int n = 0; n < arrayManager.board.length(); n++)
        {
            char[] temp = arrayManager.board.toCharArray();
            
            //If the player moved.
            if(temp[n] == '1')
            {
                switch(n)
                {
                    case 0:
                        a = "X";
                        break;
                
                    case 1:
                        b = "X";
                        break;
                        
                    case 2:
                        c = "X";
                        break;    
                     
                    case 3:
                        d = "X";
                        break;
                        
                    case 4:
                        e = "X";
                        break;
                        
                    case 5:
                        f = "X";
                        break;
                        
                    case 6:
                        g = "X";
                        break;
                        
                    case 7:
                        h = "X";
                        break;
                        
                    case 8:
                        i = "X";
                        break;    
                }
            }
            
            //If the computer moved.
            if(temp[n] == '2')
            {
                switch(n)
                {
                    case 0:
                        a = "O";
                        break;
                
                    case 1:
                        b = "O";
                        break;
                        
                    case 2:
                        c = "O";
                        break;    
                     
                    case 3:
                        d = "O";
                        break;
                        
                    case 4:
                        e = "O";
                        break;
                        
                    case 5:
                        f = "O";
                        break;
                        
                    case 6:
                        g = "O";
                        break;
                        
                    case 7:
                        h = "O";
                        break;
                        
                    case 8:
                        i = "O";
                        break;    
                }
            }
        }
        
        //Show the board
        System.out.println(" " + a + " | " + b + " | "  + c + " ");
        System.out.println("---|---|---");
        System.out.println(" " + d + " | " + e + " | "  + f + " ");
        System.out.println("---|---|---");
        System.out.println(" " + g + " | " + h + " | "  + i + " \n");
    }
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        //Load all the moves the computer knows into cpuMoves
        loadSave.load();
 
        //Start a new thread to constantly write out the board information. 
        outIn mt = new outIn("wrtier");
        Thread newThrd = new Thread(mt);
        newThrd.start();
        
        //Start the genetic programming system.
        computer.generateChromosomes();
        
        //This boolean says who goes first.
        boolean random = true;
        if((Math.random() * 100) <= 50)
        {
            random = false;
        }
        
        System.out.println("Press the number of the corresponding space"
                + " you want to move, then press enter to make a move.");
        
        showBoard();
        
        //Adds all the moves to be able to play.
        availableSpace.add(1);
        availableSpace.add(2);
        availableSpace.add(3);
        availableSpace.add(4);
        availableSpace.add(5);
        availableSpace.add(6);
        availableSpace.add(7);
        availableSpace.add(8);
        availableSpace.add(9);
        
        //Boolean to stay in the game.
        boolean exit = true;
        
        //As long as there are still spaces left and the player does
        //not enter 0 to end the game, continue the game.
        while(exit && !availableSpace.isEmpty())
        {   
            //Where you want to move on the board.
            int place = 10;
            
            //The player's block of code.
            if(random)
            {
                if(!rvl)
                {
                    //Input a number to play and see if it is available.
                    place = input.turn();
                }
                else
                {
                    place = 0;
                    try
                    {
                        //Get the 50 fittest chromosomes in the population
                        //which is the entire population.
                        List cpuMovesFromChrom = computer.population.getFittestChromosomes(100);

                        for (Object cpuMove : cpuMovesFromChrom) {
                            IChromosome temp = (IChromosome) cpuMove;
                            if(availableSpace.contains((int) (temp.getGene(0).getAllele())))
                            {
                                place = (int) temp.getGene(0).getAllele();
                                break;
                            }     
                        }
                        
                        //If a move cannot be played, then throw an expception
                        //to make a random move.
                        if(place == 0)
                            throw new Exception();
                    }
                    catch(Exception ex)
                    {
                        do
                        {    
                           place = computer.move();
                        } while(!availableSpace.contains(place) && !availableSpace.isEmpty());
                    }
                }
                
                //If the player made a valid move.
                if(availableSpace.contains(place))
                {
                    //remove the number
                    availableSpace.remove(availableSpace.indexOf(place));
                   
                    //add the number to the player's moves
                    player.add(place);
                    arrayManager.registerMove();
                    showBoard();
                    
                    //Show the player's moves (for debugging)
//                    for(int i = 0; i < player.size(); i++)
//                    {
//                        System.out.println(player.get(i));
//                    }
//                    System.out.println();

                    //check if the player won
                    if(turnCheck.check(player))
                    {
                        System.out.println("Player has won!!");
                        
                        cpuLose = true;
                        
                        //register the last move that was played
                        arrayManager.registerMove();
                       
                        showBoard();
                        
                        //save the moves to the file and close the program
                        loadSave.save();
                        System.exit(0);
                    }

                    //set the boolean to false to let the computer play
                    random = false;
                } 
                
                //If the player entered 0, end the game.
                else if(place == 0)
                {
                    System.exit(0);
                }
                
                //The player entered an invalid number or entered a 
                //move that has already been played.
                else
                {
                    System.out.println("Please enter a valid move");
                }
            }   
            
            //Evolve the populaion based on the current board.
            computer.population.evolve();
            
            //computer's block of code
            if(!random)
            {
                //Temporary array to hold the move the computer plays this turn.
                ArrayList moveHolder = new ArrayList();
                
                if(!learning)
                {
                    System.out.println("Random");
                    //Keep getting a number until a real move can be played.
                    do
                    {    
                        place = computer.move();
                    } while(!availableSpace.contains(place) && !availableSpace.isEmpty());
                    
                    moveHolder.add(arrayManager.board);
                    moveHolder.add(Integer.toString(place)); 
                    
                    //If there is a move to make then do everything the player
                   //had to do.
                   if(!availableSpace.isEmpty())
                   { 
                        System.out.println(place);
                        availableSpace.remove(availableSpace.indexOf(place));
                        arrayManager.addMove(place);
                        cpu.add(place);
                        
                        //to debug the moves the cpu has made
                        //for(int i = 0; i < cpu.size(); i++)
                        //{
                        //    System.out.println(cpu.get(i));
                        //}
                        //System.out.println();

                        if(turnCheck.check(cpu))
                        {
                            System.out.println("Computer has won!!");
                           
                            cpuWin = true;
                            
                            //register the last computer move
                            arrayManager.registerMove();
                            
                            //Take the last move's information and put it in
                            //the holder.
                            cpuMoveFiller.add(moveHolder);
                            showBoard();
                            loadSave.save();
                            System.exit(0);
                        }
                   }
                }
                else
                {
                    place = 0;
                    try
                    {
                        //If there is a move to play.
                        boolean success = false;
                        
                        //The probability of the current move that can be
                        //played. When a new move gets found its probability 
                        //will replace the old move.
                        int probability = -1;
                        
                        //The string that will actually be the move played.
                        String move = "";
                        
                        //What the board looks like when the move is played.
                        String boardView = "";
                        
                        //Go through every move the computer knows.
                        for(Object list : cpuMoves)
                        {
                            //A current moves probability of success.
                            int tempProbability = -1;
                            
                            //To iterate through each move's information.
                            ArrayList temp = (ArrayList) list;
                            
                            //Seeig if the current board layout matches a move's
                            //board layout.
                            boolean boardMoves = temp.get(0).equals(arrayManager.board);
                        
                            if (boardMoves && availableSpace.contains(Integer.parseInt((String) temp.get(1)))) 
                            {
                                //Setting the move's probability to 0 so
                                //even if it just draws the move can still
                                //be played.
                                tempProbability += 1;
                                for(int i = 2; i < temp.size(); i++)
                                {
                                    if("W".equals((String) temp.get(i)))
                                        tempProbability += 2;

                                    if("D".equals((String) temp.get(i)))
                                        tempProbability -= 1;
                                    
                                    if("L".equals((String) temp.get(i)))
                                        tempProbability -= 3;

                                    //for debugging if any moves are getting
                                    //a normal readout
                                    //System.out.println(tempProbability + " : "+ temp.get(i));
                                }
                            }
                            
                            //If the current move has at least drawed in the 
                            //past or won, or if it's probability beats a 
                            //past move's probability then make it the 
                            //move to play.
                            if(tempProbability > probability)
                            {
                                move = (String) temp.get(1);
                                probability = tempProbability;
                                boardView = (String) temp.get(0);
                            }
                        }
                        
                        //Make sure there is a move to play.
                        if(!move.equals(""))
                        {
                            //set the move
                            place = Integer.parseInt(move);
                            
                            //Add the move to the holder for later.
                            moveHolder.add(boardView);
                            moveHolder.add(move);
                            success = true;
                            
                            //For debugging to see if the math is working
                            //correctly.
                            //System.out.println("The winning prob. is: " + probability);
                        }

                        //If it could not find a move to play then look
                        //at the genetic programming to see if it knows of a 
                        //move to play.
                        if(!success)
                        {
                            //Get all of the possible chromosomes to look at.
                            List cpuMovesFromChrom = computer.population.getFittestChromosomes(100);
                            
                            //To debug and see what all the chromosomes 
                            //look like.
                            //for(int i = 0; i < cpuMovesFromChrom.size(); i++)
                            //{
                            //    IChromosome temp = (IChromosome) cpuMovesFromChrom.get(i);
                            //    System.out.println(temp);
                            //}
                            
                            //Since the moves are ordered from greatest fittness
                            //to least, go through the list until it finds
                            //a suitable move to play then play the move.
                            for (Object cpuMove : cpuMovesFromChrom) 
                            {
                                IChromosome temp = (IChromosome) cpuMove;
                                if(availableSpace.contains((int) (temp.getGene(0).getAllele())))
                                {
                                    place = (int) temp.getGene(0).getAllele();
                                    moveHolder.add(arrayManager.board);
                                    moveHolder.add(Integer.toString((int) temp.getGene(0).getAllele()));
                                    break;
                                }     
                            }
                            
                            //If a move cannot be played, then throw an expception
                            //to make a random move.
                            if(place == 0)
                                throw new Exception();
                        }
                    }
                    catch(Exception ex)
                    {
                        //If no prior move can be found then make a random
                        //move.
                        do
                        {    
                           place = computer.move();
                        } while(!availableSpace.contains(place) && !availableSpace.isEmpty());
                        moveHolder.add(arrayManager.board);
                        moveHolder.add(Integer.toString(place));   
                    }        
                    
                   //If there is a move to make then do everything the player
                   //had to do.
                   if(!availableSpace.isEmpty())
                   { 
                        System.out.println(place);
                        availableSpace.remove(availableSpace.indexOf(place));
                        arrayManager.addMove(place);
                        cpu.add(place);
                        
                        //to debug the moves the cpu has made
                        //for(int i = 0; i < cpu.size(); i++)
                        //{
                        //    System.out.println(cpu.get(i));
                        //}
                        //System.out.println();

                        if(turnCheck.check(cpu))
                        {
                            System.out.println("Computer has won!!");
                           
                            cpuWin = true;
                            
                            //register the last computer move
                            arrayManager.registerMove();
                            
                            //Take the last move's information and put it in
                            //the holder.
                            cpuMoveFiller.add(moveHolder);
                            showBoard();
                            loadSave.save();
                            System.exit(0);
                        }
                   }
                }
                
               cpuMoveFiller.add(moveHolder);
               random = true;
               arrayManager.registerMove();
               showBoard();
            }
            computer.population.evolve();
        }
        
        //If there are no more moves to make and no one won then it is a draw.
        if(availableSpace.isEmpty())
            System.out.println("The game is a draw!!");
        
        //Start a thread to save the array into the .txt file.
        loadSave.save();
        
        Thread.sleep(10000);
        System.exit(0);
    }   
}