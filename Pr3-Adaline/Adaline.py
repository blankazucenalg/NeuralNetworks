import logging
import numpy as np
import math
from random import random

__author__ = 'azu'


def activation_function(n):
    # Linear function returns the same value f(x) = x
    return n


class Net:
    logging.basicConfig(filename='adaline.log', level=logging.DEBUG)

    def __init__(self, input_length, target_length):
        # Initialize the weights with random values
        self.weights = np.random.rand(target_length, input_length)
        self.threshold = np.random.rand(target_length, 1)

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
        self.desired_error = np.empty((len(training_targets[0]), 1))
        self.desired_error.fill(desired_error)
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
                if (np.power(error, 2) > self.desired_error).all():
                    self.update_weights(error, training_patterns[p])
                    zeros = 0
                else:
                    zeros += 1
            generacion += 1

        logging.info("-----------------------------------------------------------")
        logging.info('---           The ADALINE network was trained           ---')
        logging.info("-----------------------------------------------------------")
        logging.info(" alpha = %s", self.alpha)
        logging.info(' desired error = %s', self.desired_error)
        logging.info(' W = %s', self.weights)
        logging.info(' b = %s',self.threshold)
        logging.info(' Error: %s',error.__str__())
        logging.info(' The network was trained in %s generations.', generacion.__str__())

    def get_target(self, input):
        return activation_function((self.weights * input) + self.threshold)

    def update_weights(self, error, input):
        self.weights += 2 * self.alpha * error * input.transpose()
        self.threshold += (2 * self.alpha * error)

    def classify(self, patterns):
        return [self.get_target(p) for p in patterns]
