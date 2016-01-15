
import static java.lang.Math.abs;
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
public class ArrayUtils {
    
    public static int min(int[] src) {
        int[] dest = new int[src.length];
        System.arraycopy( src, 0, dest, 0, src.length );
        Arrays.sort(dest);
        return dest[0];
    }
    
    public static int firstFound(int[] array, int number) {
        for(int i=0; i < array.length; i++){ 
            if(array[i]==number){
                return i;
            }
        }
        return -1;
    }
    
    public static int indexOf(int[] array, int number) {
        int count=0;
        for(int i=0; i < array.length; i++){ 
            if(array[i]==number){
                count++;
            }
        }
        switch(count){
            case 1:
                for(int i=0; i < array.length; i++){ 
                    if(array[i]==number){
                        return i;
                    }
                }
                break;
            case 2: 
                return -2; 
            default:
                return -1;
        }
        return -1;
    }
    
    public static int absAdding(int[] array){
        int suma = 0;
        for(int i=0; i < array.length; i++){
            suma += abs(array[i]);
        }
        return suma;
    }
    
    public static int[] substract(int[] a, int[] b){
        int[] result = new int[a.length];
        for(int i=0; i< result.length; i++){
                result[i] = a[i] - b[i];
        }
        return result;
    }
    
    public static int[] add(int[] a, int[] b){
        int[] result = new int[a.length];
        for(int i=0; i< result.length; i++){
                result[i] = a[i] + b[i];
        }
        return result;
    }

    static boolean equals(int[] error, int[] perfect) {
        return Arrays.equals(error, perfect);
    }
    
    public static String toString(int[] array){
        return Arrays.toString(array);
    }
}
