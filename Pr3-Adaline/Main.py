import Tkinter
import time
from Adaline import Net
from Plotter import Plotter
import numpy as np
import logging
from config import weights, threshold, alpha, desired_error

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
        logging.basicConfig(filename='adaline.log', level=logging.DEBUG)

        self.training_patterns = []
        self.training_targets = []
        self.desired_error = 1e-10
        if desired_error is not None:
            self.desired_error = desired_error
        self.alpha = alpha
        logging.info("-----------------------------------------------------------")
        logging.info("         A D A L I N E    E X A M P L E")
        logging.info("-----------------------------------------------------------")

        # Creating GUI
        top = Tkinter.Tk()
        top.wm_title("ADALINE Example")
        Tkinter.Button(top, text="Entrenar red", command=self.train_the_net).pack()
        Tkinter.Button(top, text="Clasificar patrones", command=self.classify_patterns).pack()
        self.label = Tkinter.StringVar()
        Tkinter.Label(top, textvariable=self.label).pack()
        top.mainloop()

    def train_the_net(self):
        start_time = time.time()
        # Reading the inputs from a file
        logging.info("---               Training patterns                     ---")
        logging.info("-----------------------------------------------------------")
        logging.info(" Pattern\t\tTarget")
        for line in open('train.txt', 'r'):
            self.training_patterns.append(np.matrix(line.split('|')[0]))
            self.training_targets.append(np.matrix(line.split('|')[1]))
            logging.info("%s", line.replace('\n', '').replace('|', '\t\t'))

        # Creating & training the network
        input_length = len(self.training_patterns[0])
        target_length = len(self.training_targets[0])
        self.net = Net(input_length, target_length)
        self.net.learn(self.training_patterns, self.training_targets, self.desired_error, alpha=self.alpha,
                       weights=weights, threshold=threshold)
        # If alpha is not set, it would be calculated by the correlation matrix
        # You can also add a weights matrix and a threshold value to the network and add them to the learn() function. Eg
        # net.learn(self.training_patterns, self.training_targets, self.desired_error, alpha=0.4, weights=w, threshold=b)

        # Now the ADALINE is trained and we can get the results and save them in a file
        f = open('weights.txt', 'w')
        f.write('W = ' + self.net.weights.__str__() + '\n')
        f.write('b = ' + self.net.threshold.__str__())
        f.close()

        # Showing weights and threshold in GUi
        self.label.set(
            'alpha = ' + self.net.alpha.__str__() + '\nW = ' + self.net.weights.__str__() + '\nb = ' + self.net.threshold.__str__())
        # Plotting the inputs and targets
        logging.info("---    Training finished in %s seconds    ---", (time.time() - start_time))
        logging.info("-----------------------------------------------------------")
        Plotter().plot3d(self.training_patterns, self.training_targets, 'Training patterns', weights=self.net.weights,
                         threshold=self.net.threshold)

    def classify_patterns(self):
        start_time = time.time()
        # Once the network is trained, classify some patterns
        self.patterns = []
        self.targets = []
        for line in open('inputs.txt', 'r'):
            self.patterns.append(np.matrix(line.split('|')[0]))
        self.targets = self.net.classify(self.patterns)

        # Now we can get the results
        logging.info("-----------------------------------------------------------")
        logging.info("---           Pattern classification results           ---")
        logging.info("-----------------------------------------------------------")
        logging.info(" Pattern \t\t Target")
        f = open('results.txt', 'w')
        pat = [[round(q, 4) for q in p] for p in self.patterns]
        tar = [[round(q, 4) for q in p] for p in self.targets]
        for p, t in zip(pat, tar):
            f.write(p.__str__().replace('[', '').replace(']', '').replace(',', ';') + "|" + t.__str__() + "\n")
            logging.info(p.__str__().replace('[', '').replace(']', '').replace(',', ';') + " \t " + t.__str__())
        f.close()
        logging.info("---    Classifying finished in %s seconds    ---", (time.time() - start_time))
        logging.info("-----------------------------------------------------------")
        # Plotting the inputs and targets
        Plotter().plot3d(self.patterns, self.targets, 'Pattern classification', weights=self.net.weights,
                         threshold=self.net.threshold)


Main()
