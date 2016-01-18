package Perceptron;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author azu
 */
public class Vowels {
    private int[] pa = {1,1,1,1,1,0,
                        0,0,0,0,1,1,
                        0,1,1,1,1,1,
                        1,1,0,0,1,1,
                        1,1,0,0,1,1,
                        0,1,1,1,1,1};
    
    private int[] ta = {1,0,0,0,0};
    
    private int[] pe = {0,1,1,1,1,0,
                        1,1,0,0,1,1,
                        1,1,1,1,1,1,
                        1,1,0,0,0,0,
                        1,1,0,0,1,1,
                        0,1,1,1,1,0};
    
    private int[] te = {0,1,0,0,0};
    
    private int[] pi = {0,0,1,1,0,0,
               0,0,1,1,0,0,
               0,0,1,1,0,0,
               0,0,1,1,0,0,
               0,0,1,1,0,0,
               0,0,1,1,0,0};
    
    private int[] ti = {0,0,1,0,0};
    
    private int[] po = {0,1,1,1,1,0,
               1,1,0,0,1,1,
               1,1,0,0,1,1,
               1,1,0,0,1,1,
               1,1,0,0,1,1,
               0,1,1,1,1,0};
    
    private int[] to = {0,0,0,1,0};
    
    private int[] pu = {1,1,0,0,1,1,
               1,1,0,0,1,1,
               1,1,0,0,1,1,
               1,1,0,0,1,1,
               1,1,0,1,1,1,
               0,1,1,1,1,1};
    
    private int[] tu = {0,0,0,0,1};
    
    private Input a;
    private Input e;
    private Input i;
    private Input o;
    private Input u;
    
    public Vowels(){
        a = new Input(pa,ta);
        e = new Input(pe,te);
        i = new Input(pi,ti);
        o = new Input(po,to);
        u = new Input(pu,tu);
    }

    public Input getA() {
        return a;
    }

    public Input getE() {
        return e;
    }

    public Input getI() {
        return i;
    }

    public Input getO() {
        return o;
    }

    public Input getU() {
        return u;
    }
    
    public Input[] getVowels(){
        Input[] vowels = {a,e,i,o,u};
        return vowels;
    }
}
