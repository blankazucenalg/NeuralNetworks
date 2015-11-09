import logging
import numpy as np
from mpl_toolkits.mplot3d import Axes3D
import matplotlib.pyplot as plt

__author__ = 'azu'


class Plotter:
    logging.basicConfig(filename='adaline.log', level=logging.DEBUG)
    def __init__(self):
        pass

    def plot3d(self, inputs, targets, title, weights, threshold):
        logging.info("Plotting the decision boundary and the patterns.")
        rows = weights.shape[0]
        cols = weights.shape[1]
        weights = np.array(weights)

        if (cols == 3):  # Only can plot in 3 dimensions

            # Plot the decision boundary
            n = [sum([weights[row][col] for row in range(rows)]) for col in range(cols)]
            d = sum(threshold)
            x_bound,y_bound = np.meshgrid(range(-2,3), range(-2,3))
            z_bound = (-n[0] * x_bound - n[1] * y_bound - d) * 1. /n[2]

            fig = plt.figure()
            ax = fig.gca(projection='3d')
            plt.hold(True)

            ax.plot_surface(x_bound, y_bound, z_bound,color='c', alpha=0.3)

            # Plot the inputs
            c = ['r', 'b', 'g', 'c', 'm', 'y', 'k', 'w']
            m = ['o', '^', 's', 'v', '8', 'p', '*', 'h', 'H', 'D', 'd', '+', 'x', '<', '>']
            for i, t in zip(inputs, targets):
                ax.scatter(i[0], i[1], i[2], c=c[int(round(t))% len(c)], marker=m[int(round(t)) % len(m)])

            ax.set_xlabel('Px')
            ax.set_ylabel('Py')
            ax.set_zlabel('Pz')
            ax.set_title(title)
            plt.savefig(title)
            plt.show()


