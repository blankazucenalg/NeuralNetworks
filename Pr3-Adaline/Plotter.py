from random import random
import numpy as np
from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt
import math

__author__ = 'azu'


class Plotter:
    def __init__(self):
        pass

    def plot3d(self, inputs, targets, title, weights, threshold):
        fig = plt.figure()
        ax = fig.add_subplot(111, projection='3d')

        # Plot the decision boundary
        rows = len(weights)
        cols = len(weights[0])
        roots = [sum([weights[row][col] for row in range(len(weights))]) for col in range(len(weights[0]))]
        p = [roots[0], 0 , 0]
        x = np.arange(max(inputs[0]))
        y = np.arange(max(inputs[1]))
        a = weights[0][0]
        b = weights[0][1]
        c = weights[0][2]
        d = -a*p[0] -b*p[1] -c*p[2]
        z = (-d -a*x -b*y) / c
        ax.plot_surface(x, y, z, rstride=4, cstride=4, color='b')

        print "raices ",roots

        c = ['r', 'b', 'g', 'c', 'm', 'y', 'k', 'w']
        m = ['o', '^', '8', 'v', 's', 'p', '*', 'h', 'H', 'D', 'd', '+', 'x', '<', '>']
        for i, t in zip(inputs, targets):
            ax.scatter(i[0], i[1], i[2], c=c[int(t % len(c))], marker=m[int(t % len(m))])

        ax.set_xlabel('Px')
        ax.set_ylabel('Py')
        ax.set_zlabel('Pz')
        ax.set_title(title)
        plt.show()
