/*
 * This code is used to implement a tic tac toe game.
 * That game is then played with a player and a computer.
 * The computer then learns over time to play better.
 */
package tictactoe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Owner
 */
public class loadSave {
    
    //To count how many moves the computer knows. -1 to account for
    //the first line explaining the file.
    public static int numLines = -1;
    
    //Use a try with resources to open a file with the array in it 
    //to load it into the system.
    public static void load()
    {
        try(BufferedReader br = new BufferedReader(new FileReader("MoveList.txt"))) 
        {     
            //Parse file ( open it up and read it)
        
            //Split file line by line.
            String strLine;
            
            //read each line
            while ((strLine = br.readLine()) != null)   {
                
                //Splits lines up into strings of numbers by spaces.
                String[] parts = strLine.split(" ");
                
                //If a line starts with #, that means it is a valid move
                //that has been learned.
                if(strLine.charAt(0) == '#')
                {
                    ArrayList temp = new ArrayList();
                    
                    //Getting the board information of the move.
                    temp.add(parts[1]);
                    
                    //Getting the move that was played at that point.
                    temp.add(parts[2]);
                    
                    //Adding in all of the letters of that move saying
                    //if it won, lost, or drawed.
                    for(int i = 3; i < parts.length; i++)
                    {
                        temp.add(parts[i]);
                    }
                    
                    //Adding 1 to the number of lines and adding the finished
                    //uploaded move to the computers known moves array.
                    numLines++;
                    game.cpuMoves.add(temp);
                }
            }
            
            //to say how many moves the computer knows
            System.out.println(numLines);
            
            //Is data still in file?
            if(br.read() != -1) {
                System.out.println("Some part of file could not be read.");
            }

        } 
        catch (FileNotFoundException ex)
        {
            System.out.println("File could not be found.\nCreating new file.");
            
            
            //If the try catch fails create a new .txt file to save the array
            //into for ater.
            //If the inside try catch fails then either the file is already
            //there and not able to load or a new file cannot be made.
            try (FileWriter fileOut = new FileWriter("MoveList.txt")) 
            {            
                String temp = "Each new move's line starts with a # to " +
                    "signify that that is the start of a valid move. " +
                    "the next long number is the state of the board " +
                    "when that move was played (1 = player moved there, " +
                    "2 = computer moved there). Then the huge line of " +
                    "letters say whether, when the moved was used, if " +
                    "it resulted in a win, lose, or draw.\r\n";
                
                fileOut.write(temp);
            }    
            catch (IOException e) 
            {
                System.out.println("New file could not be writen.");
            }
        }
        catch (IOException ex)
        {
            System.out.println("File could not be read.");
        }
    }
    
    //Save the array to the specified .txt file.
    public static void save()
    {   
        try (FileWriter fileOut = new FileWriter("MoveList.txt")) 
        {            
            String temp = "Each new move's line starts with a # to " +
                    "signify that that is the start of a valid move. " +
                    "the next long number is the state of the board " +
                    "when that move was played (1 = player moved there, " +
                    "2 = computer moved there). Then the huge line of " +
                    "letters say whether, when the moved was used, if " +
                    "it resulted in a win, lose, or draw.\r\n";
            
            //Iterate through every move the computer made that turn.
            for (Object cpuMove : game.cpuMoves)
            {
                ArrayList tList = (ArrayList) cpuMove;
                
                //Create the move in the correct formate.
                temp += "# " + tList.get(0) + " " + tList.get(1);
                
                //Set the correct letter to the moves corresponding to
                //if the computer won, lost, or drawed.
                for(Object moves : game.cpuMoveFiller)
                {
                    ArrayList tempArray = (ArrayList) moves;
                    
                    if(tempArray.get(0).equals(tList.get(0)) && 
                            tempArray.get(1).equals(tList.get(1)))
                    {
                        if(game.cpuWin)
                            tList.add("W");
                        else if(game.cpuLose)
                            tList.add("L");
                        else
                            tList.add("D");
                    }
                }
                
                //Put those letters into the moves.
                for(int i = 2; i < tList.size(); i++)
                {
                    temp += " " + tList.get(i);
                }
                
                //create a new line to start a new move.    
                temp += "\r\n";
            }
            
            //create the file
            fileOut.write(temp);
        }    
        catch (IOException e) 
        {
            System.out.println("The array could not be saved.");
        }
    }
}
