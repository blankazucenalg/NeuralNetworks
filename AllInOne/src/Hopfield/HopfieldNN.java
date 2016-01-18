package Hopfield;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author azu
 */
public class HopfieldNN {
    private int[][] w;
    private int neurons;
    
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    Handler consoleHandler = null;
    Handler fileHandler  = null;

    public HopfieldNN(int[][] w, int neurons) {
        this.w = w;
        this.neurons = neurons;
        
        try {
            //Creating consoleHandler and fileHandler
            consoleHandler = new ConsoleHandler();
            fileHandler  = new FileHandler("./vowels.log");

            //Assigning handlers to LOGGER object
            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);

            //Setting levels to handlers and LOGGER
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public HopfieldNN(int neurons){
        this.neurons = neurons;
        LOGGER.log(Level.FINER,"Creating a Hopfield Neural Network with "+neurons+" neurons.");
        w = new int[neurons][neurons];
        for(int i=0; i < neurons; i++){
            for(int j=0; j < neurons; j++){
                w[i][j] = 0;
            }
        }
        
        try {
            //Creating consoleHandler and fileHandler
            consoleHandler = new ConsoleHandler();
            fileHandler  = new FileHandler("./logger.log");

            //Assigning handlers to LOGGER object
            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);

            //Setting levels to handlers and LOGGER
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int[][] getWeights() {
        return w;
    }
    
    public int learn(int[] pattern){
        for(int i=0; i< this.neurons; i++){
            for(int j=0; j< this.neurons; j++){
                int Vi = pattern[i];
                int Vj = pattern[j];
                if(i != j){
                    this.w[i][j] += (2*Vi-1)*(2*Vj-1);
                }
            }
        }
        LOGGER.log(Level.FINER,"Patron aprendido: {0}", Arrays.toString(pattern));
        return 0;
    }
    
    public int[] test(int[] pattern){
        int[] n = MatrixUtils.dotProduct(this.w, pattern);
        return activationFunction(n);
    }
    
    private int[] activationFunction(int[] n){
        /*HARD-LIMIT FUNCTION*/
        int[] a = new int[neurons];
        for(int i=0; i < n.length; i++ ){
            a[i] = hardLimit(n[i]);
        }
        LOGGER.log(Level.FINER,"El patron encontrado fue: "+Arrays.toString(a));
        return a;
    }
    
    private int hardLimit(int number) {
        if (number < 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public String toString(){
        String print =  "W=[";
        for(int i = 0; i<w.length; i++){
            for(int j=0; j < w[0].length; j++){
                print += w[i][j]+",";
            }
                print += "\n";
        }
        return print+"]\n";
    }
}
