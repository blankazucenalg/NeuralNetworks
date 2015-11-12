import logging
from mpl_toolkits.mplot3d import *
import matplotlib.pyplot as plt
import numpy as np
from random import random, seed
from matplotlib import cm


class Plotter:
    logging.basicConfig(filename='adaline.log', level=logging.DEBUG)

    def plot3d(self, inputs, targets, title, weights, threshold):
        logging.info("Plotting the decision boundary and the patterns.")
        rows = weights.shape[0]
        cols = weights.shape[1]
        weights = np.array(weights)
        threshold = np.array(threshold)

        if cols == 3:  # Only can plot in 3 dimensions
            # Plotting the desicion boundary
            fig = plt.figure()
            ax = fig.gca(projection='3d')  # to work in 3d
            plt.hold(True)

            a = round(weights[0][0], 4)
            b = round(weights[0][1], 4)
            c = round(weights[0][2], 4)
            d = round(threshold[0][0], 4)

            if c != 0:
                x = np.arange(-3, 3, 1)  # generate a mesh
                y = np.arange(-3, 3, 1)
                x, y = np.meshgrid(x, y)
                z = (-a * x - b * y - d) / c
            elif b != 0:
                x = np.arange(-3, 3, 1)  # generate a mesh
                z = np.arange(-3, 3, 1)
                x, z = np.meshgrid(x, z)
                y = (-a * x - c * z - d) / b
            else:
                y = np.arange(-3, 3, 1)  # generate a mesh
                z = np.arange(-3, 3, 1)
                y, z = np.meshgrid(y, z)
                x = (-b * y - c * z - d) / a

            ax.plot_surface(x, y, z, color='c', alpha=0.3)  # plot a 3d surface plot

            # Plot the inputs
            c = ['r', 'b', 'g', 'c', 'm', 'y', 'k', 'w']
            m = ['o', '^', 's', 'v', '8', 'p', '*', 'h', 'H', 'D', 'd', '+', 'x', '<', '>']
            for i, t in zip(inputs, targets):
                ax.scatter(i[0], i[1], i[2], c=c[int(round(t)) % len(c)], marker=m[int(round(t)) % len(m)])

            ax.set_xlabel('x label')
            ax.set_ylabel('y label')
            ax.set_zlabel('z label')
            ax.set_title(title)
            plt.savefig(title)
            plt.show()
