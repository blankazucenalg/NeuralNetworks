package vocales;

//Esta clase maneja los eventos del editor de bits, actualiza los 1's y 0's-----

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

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
            
            /*System.out.println(""+x);
            System.out.println(""+y);     */
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
            /*
            System.out.println(""+x);
            System.out.println(""+y);*/
            Main.matrix[x][y]=0;
        }
        //----------------------------------------------------------------------
    }
}
//------------------------------------------------------------------------------