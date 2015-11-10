import logging
import numpy as np
import math
from random import random, uniform

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
        self.alpha = self.eigenvalues(self.compute_R(self.training_patterns, 0))

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
                #print target
                error = self.training_targets[p] - target
                if abs(error) > self.desired_error:
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
        logging.info(' b = %s', self.threshold)
        logging.info(' Error: %s', error.__str__())
        logging.info(' The network was trained in %s generations.', generacion.__str__())

    def get_target(self, input):
        return activation_function((self.weights * input) + self.threshold)

    def update_weights(self, error, input):
        self.weights += 2 * self.alpha * error * input.transpose()
        self.threshold += (2 * self.alpha * error)

    def classify(self, patterns):
        return [self.get_target(p) for p in patterns]

    def eigenvalues(self, md):  # P = |A - lambda*I|
        # Function linalg.eig(a) from Numpy library.
        comp = 0
        e = np.linalg.eig(md)
        # print "eigenvalues = ", e[0]
        e_elem = e[0]
        for i in range(len(e_elem)):
            if (e_elem[i] > comp):
                comp = e_elem[i]
            else:
                comp = comp

        alpha_interval = 1 / comp
        # print "alpha_interval = 0 to", alpha_interval
        return round(uniform(0.0, alpha_interval),2)

    def compute_R(self, training_patterns, p):
        mult_ma = np.zeros((3, 3))
        mult_mb = np.zeros((3, 3))
        mult_mca = np.zeros((3, 3))
        mult_mc = np.zeros((3, 3))
        # print "p", p
        while p < len(training_patterns):
            trans_p = training_patterns[p]
            i = 0

            while i < 3:
                for j in range(0, 3):
                    elem = trans_p[i] * trans_p[j]
                    mult_ma[i][j] = elem * 0.5  # E = 0.5, from R = E[PP^T]
                i += 1

            p += 1

            if (p < len(training_patterns)):
                trans_p = training_patterns[p]
                i = 0
                while i < 3:
                    for j in range(0, 3):
                        elem = trans_p[i] * trans_p[j]
                        mult_mb[i][j] = elem * 0.5  # E = 0.5, from R = E[PP^T]
                    i += 1
                mult_mct = mult_ma + mult_mb
                mult_mc = mult_mca + mult_mct
                for i in range(3):
                    for j in range(3):
                        mult_mca[i][j] = mult_mc[i][j]
                p += 1

            elif (p == len(training_patterns)):
                mult_mct = mult_ma
                mult_mc = mult_mca + mult_mct
                break
        # print mult_mc
        return mult_mc
