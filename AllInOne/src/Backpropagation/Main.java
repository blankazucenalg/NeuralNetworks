package Backpropagation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.HashMap;


public class Main extends JPanel{
    public static int [][]matrix= new int [9][6];//Matriz global
    public static double [][]entradas=new double[40][1];
    
    //Elementos del SWING-------------------------------------------------------
    Dimension labelPrefSize = new Dimension(50,50);//tamaño de cuadros   
    GridLayout experimentLayout = new GridLayout(8,5);//tamaño de la malla
    final JButton reconocer = new JButton("reconocer");
    final JButton entrenar = new JButton("Entrenar");
    //final JButton applyButton = new JButton("Aplicar Neurona");
    final JPanel malla = new JPanel();//Malla de labels para la entrada
    final JPanel malla2 = new JPanel();//Malla de labels para la letra reconocida
    final JPanel controls = new JPanel();
    public static HashMap<String,JPanel> panels2 = new HashMap<String,JPanel>();
    public static HashMap<String,JPanel> panels1 = new HashMap<String,JPanel>();
    //Elementos del SWING-------------------------------------------------------
  
//Constructor-------------------------------------------------------------------    
    public Main(){
        controls.setLayout(new GridLayout(8,1));//ordena los botones en malla
        malla.setLayout(experimentLayout);//asigna la malla de entrada el grid
        malla2.setLayout(experimentLayout);//asigna la malla de salida el grid
        MouseAdapter myMA = new MouseAdapterMod();//crea un objeto de evento para los cuadros
        
        /*//crea la malla de entrada----------------------------------------------
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 6; j++){
                JPanel btnPanel = new JPanel();
                btnPanel.setName(""+i+j);//asigna nombre de cordenada 
                btnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                btnPanel.setPreferredSize(labelPrefSize);
                btnPanel.setBackground(Color.white);
                btnPanel.setOpaque(true);    
                btnPanel.addMouseListener(myMA);//agrega escucha
                malla.add(btnPanel);//agrega al panel
                //System.out.println(""+i+j);
            }
        }
        //----------------------------------------------------------------------*/
        
        //crea la malla de entrada----------------------------------------------
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 6; j++){
                JPanel btnPanel = new JPanel();
                panels1.put(""+i+j+"",btnPanel);
                btnPanel.setName(""+i+j);//asigna nombre de cordenada 
                btnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                btnPanel.setPreferredSize(labelPrefSize);
                btnPanel.setBackground(Color.white);
                btnPanel.setOpaque(true);    
                btnPanel.addMouseListener(myMA);//agrega escucha
                malla.add(btnPanel);//agrega al panel
                //System.out.println(""+i+j);
            }
        }
        //----------------------------------------------------------------------
        
        //crea la malla de salida-----------------------------------------------
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 6; j++){
                JPanel btnPanel = new JPanel();
                panels2.put(""+i+j+"",btnPanel);
                btnPanel.setName("2"+i+j);//asigna nombre 
                btnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                btnPanel.setPreferredSize(labelPrefSize);
                btnPanel.setBackground(Color.white);
                btnPanel.setOpaque(true);
                malla2.add(btnPanel);
            }
        }
        //----------------------------------------------------------------------
        
        //agrega botones a panel controls---------------------------------------
        controls.add(entrenar);
        controls.add(reconocer);
        JLabel espacio = new JLabel();
        espacio.setText("");
        controls.add(espacio);
        //controls.add(applyButton);
        //----------------------------------------------------------------------
        
        //Agrega panels al Frame------------------------------------------------
        add(malla,BorderLayout.NORTH);
        add(malla2,BorderLayout.CENTER);
        add(controls,BorderLayout.SOUTH);
        //----------------------------------------------------------------------
      
        //ENTRENA A T1----------------------------------------------------------
        entrenar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("entrenar");
                BackpropagationNN.entrenar();
            }
        });
        //----------------------------------------------------------------------
        
        //reconoce----------------------------------------------------------
        reconocer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int numero;
                System.out.println("Reconociendo patron de entrada");
                //System.out.println(""+net.reconocer(entradas));
                //Print.print();
                Print.entrada();
                numero= BackpropagationNN.reconocer(entradas);
                switch(numero){
                    case 0:
                        Numbers.asignar(Numbers.p0);
                        Print.reconocido();
                        break;
                    case 1:
                        Numbers.asignar(Numbers.p1);
                        Print.reconocido();
                        break;
                    case 2:
                        Numbers.asignar(Numbers.p2);
                        Print.reconocido();
                        break;
                    case 3:
                        Numbers.asignar(Numbers.p3);
                        Print.reconocido();
                        break;
                    case 4:
                        Numbers.asignar(Numbers.p4);
                        Print.reconocido();
                        break;
                    case 5:
                        Numbers.asignar(Numbers.p5);
                        Print.reconocido();
                        break;
                    case 6:
                        Numbers.asignar(Numbers.p6);
                        Print.reconocido();
                        break;
                    case 7:
                        Numbers.asignar(Numbers.p7);
                        Print.reconocido();
                        break;
                    case 8:
                        Numbers.asignar(Numbers.p8);
                        Print.reconocido();
                        break;
                    case 9:
                        Numbers.asignar(Numbers.p9);
                        Print.reconocido();
                        break;
                }
            }
        });
        //----------------------------------------------------------------------
        
        /*
        entrenarE.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Entrenar T2");
                //net.entrenar(2);
                Print.print();
            }
        });
        */
      

        //Reconoce al patron entrado despues de haber entrenado la red------
        /*applyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Reconociendo");
                Print.print();
            }
        });
        */
        

    }
//Fin del constructor-----------------------------------------------------------
    
    //metodo que crea al frame u agrega elementos-------------------------------
    private static void createAndShowUI() {
        JFrame frame = new JFrame("Clasificador backpropagation");
        frame.getContentPane().add(new Main());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    //--------------------------------------------------------------------------

    //metodo main, manda a llamar a create and showUI---------------------------
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                createAndShowUI();
            }
        });
    }
    //--------------------------------------------------------------------------
}