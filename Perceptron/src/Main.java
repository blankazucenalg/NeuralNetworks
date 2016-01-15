import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Main extends JPanel{
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    Handler consoleHandler = null;
    Handler fileHandler  = null;
                
    public static int [][]matrix= new int [7][7];//Matriz global
    private int[][] pattern = new int[6][6];
    private int entradas = 36;
    private int clases = 5;
    private Net red;
    
    
    //Elementos del SWING-------------------------------------------------------
    Dimension labelPrefSize = new Dimension(50,50);//tamaño de cuadros   
    GridLayout experimentLayout = new GridLayout(6,6);//tamaño de la malla
    final JButton entrenarRed = new JButton("Entrenar red");
    final JButton applyButton = new JButton("Reconocer patron");
    final JButton clean = new JButton("Limpiar cuadriculas");
    final JLabel infoLabel = new JLabel("Entrene la red");
    final JPanel malla = new JPanel();//Malla de labels para la entrada
    final JPanel malla2 = new JPanel();//Malla de labels para la letra reconocida
    final JPanel controls = new JPanel();
    public static HashMap<String,JPanel> panels = new HashMap<String,JPanel>();
    public static HashMap<String,JPanel> panels2 = new HashMap<String,JPanel>();
    //Elementos del SWING-------------------------------------------------------
  
//Constructor-------------------------------------------------------------------    
    public Main(){
        try {
            //Creating consoleHandler and fileHandler
            consoleHandler = new ConsoleHandler();
            fileHandler  = new FileHandler("./vowels.log");

            //Assigning handlers to LOGGER object
            LOGGER.addHandler(consoleHandler);
            LOGGER.addHandler(fileHandler);

            //Setting levels to handlers and LOGGER
            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.red = new Net(entradas, clases);
        controls.setLayout(new GridLayout(8,1));//ordena los botones en malla
        malla.setLayout(experimentLayout);//asigna la malla de entrada el grid
        malla2.setLayout(experimentLayout);//asigna la malla de salida el grid
        MouseAdapter myMA = new MouseAdapterMod();//crea un objeto de evento para los cuadros
        
        //crea la malla de entrada----------------------------------------------
        for (int i = 1; i < 7; i++){
            for (int j = 1; j < 7; j++){
                JPanel btnPanel = new JPanel();
                panels.put(""+i+j+"",btnPanel);
                btnPanel.setName(""+i+j);//asigna nombre de cordenada 
                btnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                btnPanel.setPreferredSize(labelPrefSize);
                btnPanel.setBackground(Color.white);
                btnPanel.setOpaque(true);    
                btnPanel.addMouseListener(myMA);//agrega escucha
                malla.add(btnPanel);//agrega al panel
            }
        }
        //----------------------------------------------------------------------
        
        //crea la malla de salida-----------------------------------------------
        for (int i = 1; i < 7; i++){
            for (int j = 1; j < 7; j++){
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
        controls.add(entrenarRed);
        JLabel espacio = new JLabel();
        espacio.setText("");
        controls.add(espacio);
        controls.add(applyButton);
        JLabel espacio2 = new JLabel();
        espacio.setText("");
        controls.add(espacio2);
        controls.add(clean);
        JLabel espacio3 = new JLabel();
        espacio.setText("");
        controls.add(espacio3);
        controls.add(infoLabel);
        //----------------------------------------------------------------------
        
        //Agrega panels al Frame------------------------------------------------
        add(malla,BorderLayout.NORTH);
        add(malla2,BorderLayout.CENTER);
        add(controls,BorderLayout.SOUTH);
        //----------------------------------------------------------------------
        
        //Limpia las mallas de la interfaz
        clean.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JPanel malla1 = new JPanel();
                for (int i = 0; i < 6; i++){
                    for (int j = 0; j < 6; j++){
                            malla1=panels.get(""+(i+1)+(j+1)+"");
                            malla1.setBackground(Color.white);
                    }
                }
                JPanel malla2 = new JPanel();
                for (int i = 0; i < 6; i++){
                    for (int j = 0; j < 6; j++){
                            malla2=panels2.get(""+(i+1)+(j+1)+"");
                            malla2.setBackground(Color.white);
                    }
                }
                Arrays.fill(matrix[0], 0);
                Arrays.fill(matrix[1], 0);
                Arrays.fill(matrix[2], 0);
                Arrays.fill(matrix[3], 0);
                Arrays.fill(matrix[4], 0);
                Arrays.fill(matrix[5], 0);
                Arrays.fill(matrix[6], 0);
            }
        });
        //Reconoce al patron entrado despues de haber entrenado la neurona------
        applyButton.addActionListener(new ActionListener(){            
            public void actionPerformed(ActionEvent e){
                if(!MatrixUtils.equals(matrix, MatrixUtils.toZeros(matrix.length, matrix[0].length))){
                    for(int i = 1; i < 7; i++){
                        for(int j = 1; j < 7; j++){
                            pattern[i-1][j-1] = matrix[i][j];
                        }
                    }
                    Input entrada = new Input(pattern,clases);
                    entrada.setTarget(red.getTarget(entrada)); //Obtiene el target de la entrada calculado por la red
                    LOGGER.log(Level.FINER, "Reconociendo patr\u00f3n: \n{0}", entrada.toString());
                    Vowels vocales = new Vowels();
                    int index = red.clasify(entrada.getTarget(),vocales.getVowels()); //Con el target indica a que letra se parece más
                    switch(index){
                        case 0:
                            infoLabel.setText("Se parece a una 'a'");
                            break;
                        case 1:
                            infoLabel.setText("Se parece a una 'e'");
                            break;
                        case 2:
                            infoLabel.setText("Se parece a una 'i'");
                            break;
                        case 3:
                            infoLabel.setText("Se parece a una 'o'");
                            break;
                        case 4:
                            infoLabel.setText("Se parece a una 'u'");
                            break;
                        case -2:
                            char[] letters = {'a','e','i','o','u'};
                            index=ArrayUtils.firstFound(entrada.getTarget(),1);
                            infoLabel.setText("Se parece a una "+letters[index]);
                            break;
                        default: 
                            infoLabel.setText("Patrón desconocido");
                            index=4;
                            break;
                    }
                    Main.printMatrix(vocales.getVowels()[index].getDataMatrix()); 
                } else {
                    infoLabel.setText("Introduzca un patrón");
                }
            }
        });
        //----------------------------------------------------------------------
        entrenarRed.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Vowels vocales = new Vowels();
                infoLabel.setText("Entrenando la red...");  
                
                int iterations = red.learn(vocales.getVowels()); //Se entrena la red con los datos de las vocales
                LOGGER.log(Level.FINER, "Red entrenada en {0} iteraciones \n{1}", new Object[]{iterations, red.toString()}); //Imprime el vector de pesos y el vector de umbral
                infoLabel.setText("Red entrenada");
            }
        });
    }
//Fin del constructor-----------------------------------------------------------
    
    //metodo que crea al frame u agrega elementos-------------------------------
    private static void createAndShowUI() {
        JFrame frame = new JFrame("Clasificador de vocales");
        frame.getContentPane().add(new Main());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    static void printMatrix(int[][] dataMatrix) {
        //crea la malla de salida-----------------------------------------------
        JPanel panel = new JPanel();
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 6; j++){
                if(dataMatrix[i][j]==0){
                    panel=panels2.get(""+(i+1)+(j+1)+"");
                    panel.setBackground(Color.white);
                }
                else{
                    panel=panels2.get(""+(i+1)+(j+1)+"");
                    panel.setBackground(Color.black);
                }
            }
        }
    }
}

//Esta clase maneja los eventos del editor de bits, actualiza los 1's y 0's-----
class MouseAdapterMod extends MouseAdapter{
    public void mousePressed(MouseEvent e){
        String name;//string para el nombre del label
        int coordenadas,x=0,y=0;
        JPanel btnPanel = (JPanel)e.getSource();//obtiene la fuente
        
        //si el label era blanco, pasalo a negro--------------------------------
        if(btnPanel.getBackground().equals(Color.white)){
            btnPanel.setBackground(Color.black);//cambia el color a negro
            name=btnPanel.getName();//obtiene el nombre de coordenada
            coordenadas = Integer.parseInt(name);//pasa el nombre a int
            
            //divide el intero por numeros individuales-------------------------
            while (coordenadas > 0) {
                if(y==0)
                    y=coordenadas % 10;//asigna el valor de y
                else
                    x=coordenadas;//asigna el valor de x
                coordenadas = coordenadas / 10;
            }
            //------------------------------------------------------------------
            
            //System.out.println("("+x+","+y+")");  
            Main.matrix[x][y]=1;
        }
        //----------------------------------------------------------------------
        
        //si el label era negro, pasalo a blanco--------------------------------
        else if(btnPanel.getBackground().equals(Color.black)){
            btnPanel.setBackground(Color.white);//cambia el color a blanco
            name=btnPanel.getName();//obtiene el nombre de coordenada
            coordenadas = Integer.parseInt(name);//pasa el nombre a int
            
            //divide el intero por numeros individuales-------------------------
            while (coordenadas > 0) {
                if(y==0)
                    y=coordenadas % 10;//asigna el valor de y
                else
                    x=coordenadas;//asigna el valor de x
                coordenadas = coordenadas / 10;
            }
            //------------------------------------------------------------------
            
            //System.out.println("("+x+","+y+")");
            Main.matrix[x][y]=0;
        }
        //----------------------------------------------------------------------
    }
}
//------------------------------------------------------------------------------