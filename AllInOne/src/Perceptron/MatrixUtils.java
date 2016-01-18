package Perceptron;

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
    
    public static int[][] multiply(int[] error, int[] p) {
        int[][] result = new int[error.length][p.length];
        for(int i=0; i< result.length; i++){
            for(int j=0; j < result[0].length; j++){
                result[i][j] = error[i] * p[j];
            }
        }
        return result;
    }
    
    public static int[][] substract(int[][] a, int[][] b){
        int[][] result = new int[a.length][b[0].length];
        for(int i=0; i< result.length; i++){
            for(int j=0; j < result[0].length; j++){
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }
    
    public static int[][] add(int[][] a, int[][] b){
        int[][] result = new int[a.length][b[0].length];
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
