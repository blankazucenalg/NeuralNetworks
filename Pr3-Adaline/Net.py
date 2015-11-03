import numpy as np
import math
from random import random

__author__ = 'azu'


class Net:
    def __init__(self, numberOfInputs, numberOfClasses, desiredError):
        self.numberOfInputs = numberOfInputs
        self.numberOfClases = numberOfClasses
        self.desiredError = desiredError
        self.weights = [[random() for x in range(numberOfClasses)] for y in range(numberOfInputs)]
        self.threshold = [random() for x in range(numberOfClasses)]
        print self.weights, self.threshold

    def learn(self):
        self.generation = 0
        # while error > self.desiredError: # Keep making iterations till the error is acceptable


        pass

    def update_weights(self):
        pass

    def activation_function(self, n):
        # Linear function returns the same value f(x) = x
        return n

    def summatory(self, p, w):
        pass
