/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author azu
 */
class Input {
    private int[] data;
    private int[] target;
    
    public Input(int[] input, int classes) {
        this.data = input;
        this.target = new int[classes];
    }
    
    public Input(int[] input, int[] target) {
        this.data = input;
        this.target = new int[target.length];
        this.target = target;
    }

    Input(int[][] pattern, int classes) {
        int length = pattern.length * pattern[0].length;
        this.data = new int[length];
        int row = 0;
        for(int i = 0; i < length; i++){
            this.data[i] = pattern[row][i % pattern[0].length];
            if((i+1) % pattern[0].length == 0){
                row++;
            }
        }
        this.target = new int[classes];
    }

    public int[] getDataArray() {
        return data;
    }
    
    public int[][] getDataMatrix(){
        int cols = 6;
        int[][] matrix = new int[data.length / cols][cols];
        int row = 0;
        for(int i=0; i <data.length; i++){
            matrix[row][i%cols] = data[i];
            if((i+1)%cols == 0){
                row++;
            }
        }
        return matrix;
    }

    public int[] getTarget() {
        return target;
    }

    public void setTarget(int[] target) {
        this.target = target;
    }
    
    @Override
    public String toString(){
        String print =  "";
        int newline = data.length / 6;
        for(int i = 0; i<data.length; i++){
            print += data[i]+" ";
            if((i+1) % newline == 0)
                print += "\n";
        }
        print += "\n t= ";
        for(int i=0; i < target.length; i++){
            print += target[i];
        }
        return print+"\n";
    }
}
