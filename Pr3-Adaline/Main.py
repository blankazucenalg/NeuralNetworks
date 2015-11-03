from Net import Net
from Plotter import Plotter

__author__ = 'azu'

''' File example:
    [<inputs>]<Target>

    [1, 2, 3]0
    [1, 1, 1]1
    ...
'''


class Main:
    def __init__(self):
        self.inputs = []
        self.targets = []

        # Reading the inputs from a file
        r = open('inputs.txt', 'r')
        for line in r.readlines():
            self.inputs.append([int(i) for i in line.replace('\n', '').split(']')[0].replace('[', '').split(',')])
            self.targets.append(int(line.replace('\n', '').split(']')[1]))
        print self.inputs, self.targets
        r.close()

        # Creating the Network
        net = Net(len(self.inputs[0]), max(self.targets)-min(self.targets), 0.1)




        # Plotting the inputs and targets
        Plotter().plot3d(self.inputs, self.targets)


Main()
