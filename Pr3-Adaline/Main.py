from Adaline import Net
from Plotter import Plotter
import numpy as np

__author__ = 'azu'

''' File example for training:
    Each row is separated for a semi-colon ';' and each column is separed by a blank space.
    <Inputs> and <Targets> should be separated by a pipe '|'
    <Inputs>|<Targets>

    1; -1; -1|-1
    1; 1; -1|1
    ...


    File example for pattern classification:
    Each row is separated for a semi-colon ';' and each column is separed by a blank space
    <Inputs>

    1; -1; -1
    1; 1; -1
'''


class Main:
    def __init__(self):
        self.training_patterns = []
        self.training_targets = []
        self.desired_error = 0.00000000001

        # Reading the inputs from a file
        for line in open('train.txt', 'r'):
            self.training_patterns.append(np.matrix(line.split('|')[0]))
            self.training_targets.append(np.matrix(line.split('|')[1]))

        # Creating & training the network
        input_length = len(self.training_patterns[0])
        target_length = len(self.training_targets[0])
        net = Net(input_length, target_length)
        net.learn(self.training_patterns, self.training_targets, self.desired_error, alpha=0.2)
        # If alpha is not set, it would be calculated by the correlation matrix
        # You can also add a weights matrix and a threshold value to the network and add them to the learn() function
        #   w = np.matrix([[1, 0], [0, 1]])
        #   b = np.matrix([[1, 1]])
        # net.learn(self.training_patterns, self.training_targets, self.desired_error, alpha=0.2, weights=w, threshold=b)

        # Now the ADALINE is trained and we can get the results and save them in a file
        f = open('weights.txt', 'w')
        f.write('W = ' + net.weights.__str__() + '\n')
        f.write('b = ' + net.threshold.__str__())
        f.close()

        # Once the network is trained, classify some patterns
        self.patterns = []
        self.targets = []
        for line in open('inputs.txt', 'r'):
            self.patterns.append(np.matrix(line.split('|')[0]))
        self.targets = net.classify(self.patterns)
        print self.training_targets, self.targets

        # Now we can get the results
        f = open('results.txt', 'w')
        f.write(self.patterns.__str__() + '\n')
        f.write(self.targets.__str__())
        f.close()

        # Plotting the inputs and targets
        Plotter().plot3d(self.training_patterns, self.training_targets, 'Training patterns', weights=net.weights, threshold=net.threshold)
        Plotter().plot3d(self.patterns, self.targets,'Pattern classification', weights=net.weights, threshold=net.threshold)


Main()
