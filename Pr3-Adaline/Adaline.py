import numpy as np
import math
from random import random

__author__ = 'azu'


def activation_function(n):
    # Linear function returns the same value f(x) = x
    return n


class Net:
    def __init__(self, input_length, target_length):
        # Initialize the weights with random values
        self.weights = np.random.rand(target_length,input_length)
        self.threshold = np.random.rand(1,target_length)

    def auto_learning_speed(self):
        # TODO: Some cool stuff about the eigenvalues and correlation matrix
        # Use self.training_patterns to get the correlation matrix
        # self.alpha = ?
        pass

    def learn(self, training_patterns, training_targets, desired_error, alpha=None, weights=None, threshold=None):
        if weights is not None and threshold is not None:
            self.weights = weights
            self.threshold = threshold
        self.training_patterns = training_patterns
        self.training_targets = training_targets
        self.desired_error = np.matrix([[desired_error for x in range(len(training_targets))]])
        if alpha is not None:
            self.alpha = alpha
        else:
            self.auto_learning_speed()
        generacion = 0
        zeros = 0
        while zeros < len(self.training_targets):
            for p in range(len(training_patterns)):
                target = self.get_target(training_patterns[p])
                error = self.training_targets[p] - target
                if error**2 > desired_error:
                    self.update_weights(error, training_patterns[p])
                    zeros = 0
                else:
                    zeros += 1
            generacion += 1

        print 'Red entrenada en ' + generacion.__str__() + ' generaciones.'
        print 'W = ',self.weights,'\nb = ',self.threshold

    def get_target(self, input):
        print self.weights, input, self.threshold
        return activation_function((self.weights * input) + self.threshold)

    def update_weights(self, error, input):
        self.weights += 2 * self.alpha * error * input.transpose()
        self.threshold += (2 * self.alpha * error)

    def classify(self, patterns):
        print 'Classifying patterns...'
        return [self.get_target(p) for p in patterns]
