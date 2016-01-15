import logging
import numpy as np
import math
from copy import copy
from random import random, uniform

__author__ = 'azu'


class Competitive:
    logging.basicConfig(filename='logger.log', level=logging.DEBUG)

    def compet(self, p):
        wp = self.w * p
        m = max(wp)
        for i in range(len(wp)):
            if wp[i] == m:
                return i

    def __init__(self, patterns_len, neurons=2, alpha=0.5, epochs=1, w=None):
        if w != None:
            self.w = w
        else:
            self.w = np.matrix(np.zeros([neurons, patterns_len]))
        self.alpha = alpha
        self.epochs = epochs

    def learn(self, patterns):
        epoch = 0
        diff = 1
        while epoch < self.epochs or diff.all() > 0:
            for p in patterns:
                old = copy(self.w)
                logging.info("----------------------")
                logging.info("Epoca %s",epoch+1)
                logging.info("w = %s",self.w)
                logging.info("p = %s",p)
                a = self.compet(p)
                logging.info("Neurona ganadora: %s", a + 1)
                self.w[a] += self.alpha * (p.transpose() - self.w[a])
                logging.info("Actualizando %sW",a+1)
                diff = abs(self.w - old)
            epoch += 1
        logging.info("--------------------------")
        logging.info("Red entrenada %s epocas",self.epochs)
        logging.info("--------------------------")
