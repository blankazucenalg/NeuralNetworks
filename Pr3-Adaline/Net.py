import numpy as np
import math
from random import random

__author__ = 'azu'



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
        self.alpha = self.learning_speed(self.training_patterns)

    def learning_speed(self,training_patterns):
        # TODO: Some cool stuff about the eigenvalues and correlation matrix
        # return alpha
        pass

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
