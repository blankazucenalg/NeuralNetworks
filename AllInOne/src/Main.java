import Backpropagation.BackpropagationNN;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by azu on 17/01/16.
 */
public class Main {
    private JButton perceptron;
    private JButton adaline;
    private JLabel ipn;
    private JLabel escom;
    private JLabel neuralNetworks;
    private JLabel title;
    private JLabel team;
    private JButton backpropagation;
    private JButton competitive;
    private JButton hopfield;
    private JPanel contentPane;

    public Main() {
        perceptron.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Perceptron.Main().main(null);
            }
        });
        adaline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeCommand("python /home/azu/Proyectos/NeuralNetworks/Adaline/Main.py");
            }
        });
        backpropagation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Backpropagation.Main().main(null);
            }
        });
        competitive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeCommand("python /home/azu/Proyectos/NeuralNetworks/Competitive/Main.py");
            }
        });
        hopfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Hopfield.Main().main(null);
            }
        });
    }

    public void executeCommand(String command){
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec(command);
            // Read the output
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String line = "";
            while((line = reader.readLine()) != null) {
                System.out.print(line + "\n");
            }
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
