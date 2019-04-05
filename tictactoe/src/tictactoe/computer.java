/*
 * This code is used to implement a tic tac toe game.
 * That game is then played with a player and a computer.
 * The computer then learns over time to play better.
 */
package tictactoe;

import java.util.ArrayList;
import org.jgap.*;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

/**
 *
 * @author Cory Edwards
 */

//Code for computer input.
public class computer extends FitnessFunction{
    
    //The population that will hold all the chromosomes and evolve each turn.
    public static Genotype population;
    
    //Lists of everything that will be brought in from main.
    public static ArrayList player;
    public static ArrayList cpu;
    public static ArrayList moves;
    
    //the constructor
    private computer(ArrayList player, ArrayList cpu, ArrayList move) 
    {
        computer.player = player;
        computer.cpu = cpu;
        computer.moves = move;
    }
    
    //Generates a random number for the computer's turn if learn is false.
    public static int move()
    {
        return (int) (Math.random() * 10);
    }
    
    //Generating and placing the chromosomes into the population.
    public static void generateChromosomes() throws Exception
    {
        // Start with a DefaultConfiguration, which comes setup with the
        // most common settings.
        Configuration conf = new DefaultConfiguration();

        // Set the fitness function to this class.
        FitnessFunction myFunc = new computer(game.player, game.cpu, game.cpuMoves);

        //Add the fitness function to the configuration.
        conf.setFitnessFunction(myFunc);

        //Setting how many genes are in each chromosome. In our case it's 1.
        Gene[] sampleGenes = new Gene[1];

        //Set each gene to be what kind of gene it is, give it the 
        //configuration, and then give it its parameters. Since we
        //only number options of 1 - 9 for moves to make that is what
        //we set our parameters. Also, since it is numbers we make it
        //an integer gene. Lastly, since we only have one gene per
        //chromosome, we only need to make gene[0].You could have as 
        //many genes as you need here.
        sampleGenes[0] = new IntegerGene(conf, 1, 9);

        //Create your chromosome with all of the now created genes inside it
        // with the configuration.
        Chromosome sampleChromosome = new Chromosome(conf, sampleGenes );

        //Using the now configured configuration, create a sample chromosome
        //that every other chromosome will be based off of.
        conf.setSampleChromosome(sampleChromosome);

        //Set the population size. This is a max size so the number will
        //correspond to how many chromosomes will be available to select 
        //from when you need to.Of course the more you have the better
        //the chance of picking the right one of the bat. However, the more
        //you make, the slower your program will be since it has to iterate
        //through ever chromosome.
        conf.setPopulationSize(100);

        //Radomize genes in first population.
        population = Genotype.randomInitialGenotype( conf );
    }
    
    //This is the fitness function for the population.The higher the
    //fitness value of a chromosome, the better that chromosome is.Make
    //sure that if multiple chromosomes have the same gene that they will
    //end up with the same fitness value at the end.
    public double evaluate( IChromosome a_subject )
    {
        //Call on another method to generate the fitness value.
        double fitness = turnCheck.fitCheck(a_subject);
        
        return fitness;
    }
}
