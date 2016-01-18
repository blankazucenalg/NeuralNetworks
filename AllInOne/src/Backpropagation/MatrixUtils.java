package Backpropagation;

import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author azu
 */
public class MatrixUtils {
    
    
    public static double[][] multiply(double[][] m1, double[][] m2) {
        int fil_m1 = m1.length;
        int col_m1 = m1[0].length; 
        int fil_m2 = m2.length;
        int col_m2 = m2[0].length;
        if (col_m1 != fil_m2)
            throw new RuntimeException("No se pueden multiplicar las matrices");        
        double[][] multiplicacion = new double[fil_m1][col_m2];        
        for (int x=0; x < multiplicacion.length; x++) {
            for (int y=0; y < multiplicacion[x].length; y++) {
              for (int z=0; z<col_m1; z++) {
                multiplicacion [x][y] += m1[x][z]*m2[z][y]; 
              }
            }
        }        
        return multiplicacion;
    }
    
    public static double[][] substract(double[][] a, double[][] b){
        double[][] result = new double[a.length][b[0].length];
        for(int i=0; i< result.length; i++){
            for(int j=0; j < result[0].length; j++){
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }
    
    public static int substracterror(double[][] a, double[][] b,int iteracion){
        int verificacion=0,ok=0;
        double[][] result = new double[a.length][b[0].length];
        //quiotar//System.out.print("\n\n----iteracion: "+iteracion+"\n");
        for(int i=0; i< result.length; i++){
            for(int j=0; j < result[0].length; j++){
                //quiotar//System.out.println(""+Math.abs(a[i][j]-b[i][j]));
                if(Math.abs(a[i][j]-b[i][j])<=0.01/*||Math.abs(a[iteracion][j]-b[iteracion][j])>=0.7*/){
                    verificacion++;//System.out.print("OOOOOOOOOOOOOOO");
                }              
            }
        }
        if(verificacion==10){
            ok=1;
            //quiotar//System.out.print("\nse logro puta");
        }        
        //quiotar//System.out.print("\nverificacion "+verificacion+"\n");
        return ok;
    }
    
    public static double[][] add(double[][] a, double[][] b){
        int fil_m1 = a.length;
        int col_m1 = a[0].length;
        int fil_m2 = b.length;
        int col_m2 = b[0].length;
        double[][] result = new double[a.length][b[0].length];
        for(int i=0; i< result.length; i++){
            for(int j=0; j < result[0].length; j++){
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }
    
    public boolean isZeros(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j=0; j < matrix[0].length; j++){
                if(matrix[i][j] != 0)
                    return false;
            }
        }
        return true;
    }
    
    public static int[][] toZeros(int rows, int cols){
        int[][] matrix = new int[rows][cols];
        for(int row=0; row < rows; row++){
            Arrays.fill(matrix[row],0);
        }
        return matrix;
    }
    
    public static boolean equals(int[][] a, int[][] b){
        return Arrays.deepEquals(a,b);
    }
    
    public static String toString(int[][] matrix){
        return Arrays.deepToString(matrix);
    }
}
