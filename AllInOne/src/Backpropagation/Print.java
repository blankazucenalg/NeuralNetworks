package Backpropagation;

import javax.swing.*;
import java.awt.*;

import static Backpropagation.Main.matrix;

public class Print {
    static public void print(){
        int contador=0;
        JPanel panel = new JPanel();
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 6; j++){
                if(Main.matrix[i][j]==0){
                    panel= Main.panels2.get(""+i+j+"");
                    panel.setBackground(Color.white);
                }
                else{
                    panel= Main.panels2.get(""+i+j+"");
                    panel.setBackground(Color.black);
                }
            }
        }
        for(int i = 1; i < 9; i++){
                    for(int j = 1; j < 6; j++){
                        System.out.print(matrix[i][j] + " ");
                        Main.entradas[contador][0]=matrix[i][j] ;
                        contador++;
                    }
                    System.out.print("\n");
        }
    }
    static public void entrada(){
        int contador=0;
        JPanel panel = new JPanel();
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 6; j++){
                    panel= Main.panels1.get(""+i+j+"");
                if(panel.getBackground().equals(Color.white)){ 
                    matrix[i][j]=0;
                    Main.entradas[contador][0]=matrix[i][j] ;
                    contador++;
                }
                else{
                    matrix[i][j]=1;
                    Main.entradas[contador][0]=matrix[i][j] ;
                    contador++;
                }
            }
        }
        for(int i = 1; i < 9; i++){
                    for(int j = 1; j < 6; j++){
                        System.out.print(matrix[i][j] + " ");
                    }
                    System.out.print("\n");
        }
    }
    static public void reconocido(){
        JPanel panel = new JPanel();
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 6; j++){
                if(Main.matrix[i][j]==0){
                    panel= Main.panels2.get(""+i+j+"");
                    panel.setBackground(Color.white);
                }
                else{
                    panel= Main.panels2.get(""+i+j+"");
                    panel.setBackground(Color.black);
                }
            }
        }
    }
}
