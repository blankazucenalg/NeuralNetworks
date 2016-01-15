
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author azu
 */
public class Net {
    private int[][] w;
    private int[] b;
    private int inputs;
    private int classes;
    
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    Handler consoleHandler = null;
    Handler fileHandler  = null;

    public Net(int[][] w, int[] b) {
        this.w = w;
        this.b = b;
        this.inputs = w[0].length;
        this.classes = b.length;
        
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
    
    public Net(int numberOfInputs, int numberOfClasses){
        this.inputs = numberOfInputs;
        this.classes = numberOfClasses;
        w = new int[classes][inputs];
        b = new int[classes];
        for(int i=0; i < classes; i++){
            for(int j=0; j < inputs; j++){
                w[i][j] = 0;
                b[i] = 0;
            }
        }
        
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

    public int[][] getWeights() {
        return w;
    }

    public int[] getThreshold() {
        return b;
    }
    
    public int learn(Input[] entradas){
        int[] perfect = {0,0,0,0,0};
        int generacion = 0;
        int[] error = {1,1,1,1,1};
        int zeros = 0;
        while(zeros < 5){
            for(int i = 0; i < entradas.length; i++){
                int[] a = getTarget(entradas[i]);
                error = ArrayUtils.substract(entradas[i].getTarget(),a);
                if(!ArrayUtils.equals(error, perfect)){
                    this.updateWeights(error,entradas[i].getDataArray());  
                    zeros = 0;
                } else {
                    zeros++;
                }
                //LOGGER.log(Level.FINER, "Iteraci\u00f3n: {0} Error: [{1},{2},{3},{4},{5}]\n", new Object[]{generacion*5+i+1, error[0], error[1], error[2], error[3], error[4]});
            }
            generacion++;
        }
        System.out.println("Red entrenada\n");
        return generacion*entradas.length;
    }
    
    public int[] getTarget(Input input){
        int[] n = summatory(input.getDataArray(), this.w);
        return activationFunction(n);
    }
    
    private void updateWeights(int[] error, int[] p) {
        this.w = MatrixUtils.add(w, MatrixUtils.multiply(error,p));
        this.b = ArrayUtils.add(b,error);
    }
    
    private int[] summatory(int[] p, int[][] w){
        int[] result = new int[w.length];
        for(int i = 0; i < w.length; i++){
            int suma = 0;
            for(int j= 0; j < w[0].length; j++){
                suma += p[j] * w[i][j];
            }
            result[i] = suma;
        }
        return result;
    }
    
    private int[] activationFunction(int[] n){
        /*HARD-LIMIT FUNCTION*/
        int[] a = new int[classes];
        for(int i=0; i < n.length; i++ ){
            a[i] = hardLimit(n[i]);
        }
        return a;
    }
    
    private int hardLimit(int number){
        if(number < 0){
            return 0;
        } else {
            return 1;
        }
    }
    
    public int clasify(int[] target, Input[] trainers) {
        int[] comparison = new int[trainers.length];
        for(int i=0; i < trainers.length; i++){
            comparison[i] = ArrayUtils.absAdding(ArrayUtils.substract(target,trainers[i].getTarget()));
        }
        System.out.println(""+comparison[0]+""+comparison[1]+""+comparison[2]+""+comparison[3]+""+comparison[4]);
        int index = ArrayUtils.indexOf(comparison,ArrayUtils.min(comparison));
        return index;
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
        print += "]\nb=[";
        for(int i=0; i < b.length; i++){
            print += b[i]+",";
        }
        return print+"]\n";
    }
}
