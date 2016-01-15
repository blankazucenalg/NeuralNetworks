import Tkinter
import logging
import numpy as np
from copy import copy

from CompetitiveNN import Competitive
from Plotter import Plotter

__author__ = 'azu'

logging.basicConfig(filename='logger.log', level=logging.DEBUG)
logging.getLogger().addHandler(logging.StreamHandler())


def get_patterns(file):
    p = []
    for line in open(file, 'r'):
        p.append(np.matrix(line.split('|')[0]))
    return np.array(p)


def get_targets(file):
    p = []
    for line in open(file, 'r'):
        p.append(np.matrix(line.split('|')[1]))
    return np.array(p)

def plot_init_data():
    Plotter().plot_2d(patterns,w,title="Parametros iniciales")

def plot_final_data():
    net.learn(patterns)
    Plotter().plot_2d(patterns,net.w,title="Competitive network")

# Get data
w = np.matrix([[0.7071, -0.7071], [0.7071, 0.7071], [-1, 0]])
patterns = get_patterns('inputs.txt')
neurons = 3
epochs = 300

# Creating the network
net = Competitive(len(patterns[0]), w=copy(w), epochs=epochs)
w = copy(net.w)

#Creating GUI
top = Tkinter.Tk()
top.wm_title("Red neuronal competitiva")
Tkinter.Button(top, text="Mostrar parametros iniciales", command=plot_init_data).pack()
Tkinter.Button(top, text="Mostrar parametros finales", command=plot_final_data).pack()
label = Tkinter.StringVar()
var = "Numero de neuronas: "+neurons.__str__()+"\nEpocas: "+epochs.__str__()
label.set(var)
Tkinter.Label(top, textvariable=label).pack()
top.mainloop()

logging.info("Parametros iniciales: %s",w)
logging.info("Parametros finales: %s",net.w)
