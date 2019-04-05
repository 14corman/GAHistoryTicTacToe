/*
 * This code is used to implement a tic tac toe game.
 * That game is then played with a player and a computer.
 * The computer then learns over time to play better.
 */
package tictactoe;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class outIn implements Runnable {
    
    //The string that will become the thread's name.
    String thrdName;
    
    //contructor of the thread
    outIn(String name)
    {
        thrdName = name;
    }
    
    //executed when the thread is created
    public void run()
    {   
        //Give the c# program a chance to read the file, so
        //make the thread sleep for 100 milliseconds.
        long timeToSleep = 100;
        
        //Have the thread running until the program ends so it is constantly
        //writing out the board info.
        while(true)
        {
            //Make the thread sleep.
            try 
            {
                Thread.sleep(timeToSleep);
            } 
            catch (InterruptedException ex)
            {
                System.out.println("Porblem making the writing thread sleep.");
            }
            
            //Check to see if the file is open or not. If not then wait till
            //it is.
            while(!isFileClosed("C:\\Users\\Owner\\Documents\\tttTemp\\bridgeb.txt"))
            {}
            
            //Write to the file.
            try (PrintStream fileOut = new PrintStream(new FileOutputStream("C:\\Users\\Owner\\Documents\\tttTemp\\bridgeb.txt"))) 
            {
                String win = "false";
                String lose = "false";

                if(game.cpuWin)
                    win = "true";

                if(game.cpuLose)
                    lose = "true";

                String temp = arrayManager.board +
                        "\r\n" + win + "\r\n" + lose + "\r\n" 
                        + Integer.toString(loadSave.numLines);

                fileOut.write(temp.getBytes(Charset.forName("UTF-8")));
            }    
            catch (IOException e) 
            {
                System.out.println("File is open now.Please wait...");
            } 
        }
    }
    
    public static int read()
    {
        //Give the c# program a chance to change the bridgem file.
        long timeToSleep = 100;
        
        //Make the thread sleep.
        try 
        {
            Thread.sleep(timeToSleep);
        } 
        catch (InterruptedException ex)
        {
            System.out.println("Porblem making the main thread sleep.");
        }
        
        //Wait for the file to be open if it isn't already.
        while(!isFileClosed("C:\\Users\\Owner\\Documents\\tttTemp\\bridgem.txt"))
        {}
        
        //Read the bridgem file.
        try(BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("C:\\Users\\Owner\\Documents\\tttTemp\\bridgem.txt"), "UTF-8"))) 
        {     
            //Parse file ( open it up and read it)
        
            //Split file line by line.
            String strLine;
            
            strLine = br.readLine();
            
            int length = 1;
            
            //For debugging what is being inputed.
            //System.out.println(strLine);
            
            //Sometimes ther is an extra character at the beggining
            //of the number,so cut that off just to get the number.
            if(strLine.length() != 1 )
                length = strLine.length() - 1;
            
            //If there are 3 characters, then that means that the
            //c# program tried to put a 10 in there and the
            //extra character came with it so just return a 10.
            if(strLine.length() == 3)
                return 10;
            
            //Parse in the string as an int to make the move.
            int turn = Integer.parseInt(strLine.substring(length));
            
            return turn;
        } 
        catch (FileNotFoundException ex)
        {
            System.out.println("File could not be found.");
        }
        catch (IOException ex)
        {
            System.out.println("File could not be read.");
        }
        return 10;
    }        
    
    //Check to see if the inputed file is available or not.
    public static boolean isFileClosed(String file) {  
            boolean closed;
            FileChannel channel = null;
            try 
            {
                channel = new RandomAccessFile(file, "rw").getChannel();
                closed = true;
            } 
            catch(Exception ex)
            {
                //Could not open the file so it is unavailable.
                closed = false;
            }
            finally 
            {
                if(channel != null)
                {
                    try 
                    {
                        channel.close();
                    } 
                    catch (IOException ex)
                    {
                        System.out.println("While trying to check if "
                                + file + " was open or not, it could "
                                + "not be closed.");
                    }
                }
            }
            return closed;
       }
}
