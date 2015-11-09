import numpy as np
import math
from random import random,uniform

__author__ = 'azu'

#Global Vars
matriz3 =[[0 for x in range(3)] for y in range(3)]
n = 0
i = 0
trans_p = [3]

def new_matrix(p, q):
    matrix = [[0 for row in range(p)] for col in range(q)]
    return matrix

mult_ma = new_matrix(3,3)
mult_mb = new_matrix(3,3)

def compute_R(training_patterns, p):
    mult_mct = new_matrix(3,3)
    mult_mca = new_matrix(3,3)
    mult_mc = new_matrix(3,3)
    print "p", p
    while p < len(training_patterns):
        trans_p = training_patterns[p]
        i = 0

        while i < 3:
            for j in range(0,3):
                elem = trans_p[i] * trans_p[j]
                mult_ma[i][j] = elem*0.5 #E = 0.5, from R = E[PP^T]
            i += 1

        p += 1

        if(p < len(training_patterns)):
            trans_p = training_patterns[p]
            i = 0
            while i < 3:
                for j in range(0,3):
                    elem = trans_p[i] * trans_p[j]
                    mult_mb[i][j] = elem*0.5 #E = 0.5, from R = E[PP^T]
                i += 1
            mult_mct = sumar_matrices(mult_ma,mult_mb)
            mult_mc = sumar_matrices(mult_mca,mult_mct)
            for i in range(3):
                for j in range(3):
                    mult_mca[i][j] = mult_mc[i][j]
            p += 1

        elif(p == len(training_patterns)):
            mult_mct = mult_ma
            mult_mc = sumar_matrices(mult_mca,mult_mct)
            break
    print mult_mc
    return mult_mc

def sumar_matrices(ma,mb):
    #Se utiliza un ciclo para realizar la suma
    for i in range(3):
        for j in range(3):
            matriz3[i][j] = ma[i][j]+ mb[i][j]
    return matriz3

def eigenvalues(md): #P = |A - lambda*I|
    #Function linalg.eig(a) from Numpy library.
    comp = 0
    e = np.linalg.eig(md)
    print "eigenvalues = ", e[0]
    e_elem = e[0]
    for i in range(len(e_elem)):
        if(e_elem[i] > comp):
            comp = e_elem[i]
        else:
            comp = comp

    alpha_interval = 1/comp
    print "alpha_interval = 0 to", alpha_interval

    return uniform(0.0,alpha_interval)

class Net:

    def __init__(self, training_patterns, training_targets, desiredError):
        self.training_patterns = training_patterns
        self.training_targets = training_targets
        self.input_length = len(self.training_patterns[0])
        self.desiredError = desiredError
        if isinstance(training_targets[0],int):
            self.target_length = 1
        else:
            self.target_length = len(training_targets[0])
        self.weights = [[random() for x in range(self.target_length) for y in range(self.input_length)]]
        self.threshold = [random() for x in range(self.target_length)]
        print self.weights, self.threshold
        self.alpha = self.learning_speed(self.training_patterns,0)

    def learning_speed(self,training_patterns,p):
        # TODO: Some cool stuff about the eigenvalues and correlation matrix
        alpha = eigenvalues(compute_R(training_patterns,0))
        print "alpha = ", alpha

        return alpha

    def learn(self):
        print 'Training the network...'
        generation = 0
        # while error > self.desiredError: # Keep making iterations till the error is acceptable
            # update_weights()

        pass

    def update_weights(self):
        # self.weights = self.weights + 2*alpha*error*input
        pass

    def activation_function(self, n):
        # Linear function returns the same value f(x) = x
        return n

    def summatory(self, p, w):
        # return the sum between p and w
        pass

    def classify(self, p):
        print 'Classifying patterns...'
        # return [targets] for p
        pass