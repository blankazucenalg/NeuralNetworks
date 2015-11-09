from Net import Net
from Plotter import Plotter

__author__ = 'azu'

''' File example for training:
    [<inputs>]<Target>

    [1, 2, 3]0
    [1, 1, 1]1
    ...


    File example for pattern classification:
    [<inputs>]

    [1, 4, 3]
    [2, 3, 4]
'''

class Main:
    def __init__(self):
        self.training_patterns = []
        self.training_targets = []
        self.desired_error = 0.1

        # Reading the inputs from a file
        for line in open('train.txt', 'r'):
            self.training_patterns.append([int(i) for i in line.replace('\n', '').split(']')[0].replace('[', '').split(',')])
            self.training_targets.append(int(line.replace('\n', '').split(']')[1]))
        print self.training_patterns, self.training_targets

        # Creating the Network
        net = Net(self.training_patterns, self.training_targets, self.desired_error)
        net.learn()

        # Once the network is trained, classify some patterns
        self.patterns = []
        self.targets = []
        for line in open('inputs.txt','r'):
            self.patterns.append([int(i) for i in line.replace('[','').replace('\n','').split(']')[0].split(',')])
        self.targets = net.classify(self.patterns)

        # Plotting the inputs and targets
        Plotter().plot3d(self.training_patterns, self.training_targets,'Training patterns')
        # Plotter().plot3d(self.patterns, self.targets,'Pattern classification')


Main()