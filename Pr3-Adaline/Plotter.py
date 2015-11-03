from random import random
import numpy as np
from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt
import math

__author__ = 'azu'


class Plotter:
    def __init__(self):
        pass

    def plot3d(self, inputs, targets):
        fig = plt.figure()
        ax = fig.add_subplot(111, projection='3d')
        c = ['r', 'b', 'g', 'c', 'm', 'y', 'k', 'w']
        m = ['o', '^', '8', 'v', 's', 'p', '*', 'h', 'H', 'D', 'd', '+', 'x', '<', '>']
        for i, t in zip(inputs, targets):
            ax.scatter(i[0], i[1], i[2], c=c[t % len(c)], marker=m[t % len(m)])

        ax.set_xlabel('Px')
        ax.set_ylabel('Py')
        ax.set_zlabel('Pz')

        plt.show()
