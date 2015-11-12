# Widrow-Hoff algorithm implementation (ADALINE)

## Objective
Implement the Widrow-Hoff learning algorithm  to classify linear patterns;
with the following characteristics: 

1. Allows N input patterns, with three elements for each pattern Pi = [x,y,z]. 
2. Allows to initialize the values (W y b) of the net Adaline with small random values
 and also with user given values.
3. Allows to plot the patterns in three dimensions,  
including the desicion boundary, and it gives the result for the matrix 
(Wy b) that solves the problem.
4. Allows to input a set of patterns to classify them using the trained net.

## Language
Python 2.7.6

## Description
This program uses the patterns given in the file *train.txt*, written like the example 
below. It includes the values with the format<inputs>|<target>
```
1; -1; -1|-1
1; 1; -1|1
```
The results for the weight matrix and threshold are written in the file *weights.txt* 
and the plot is saved in the image: *Training patterns.png*

Once the ADALINE net is trained using the training patterns, it can classify a set of 
patterns given in the file *inputs.txt* that is written like the following example.
```
1;-1;-2
1; 1; 1
-2; 1;-1
-2;-1; 1
1; -1;-1
1; 1; -1
```
Where each line represents the set of points of a pattern [x,y,z]
The results of the classification, and the targets gotten are written in the file 
*results.txt* and the plot is saved in the image *Pattern classification.png*

To get more information about the process, you can visualize the outputs saved in
the log file *adaline.log* 

