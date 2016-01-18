package vocales;
import java.lang.Math;

public  class BackpropagationNN {
    
    static double [][]W1=new double [20][40];//matrix de pesos W1
    static double [][]B1=new double [20][1];//vector de umbral b1
    static double [][]W2=new double [10][20];//matrix de pesos w2
    static double [][]W2T=new double [20][10];//matrix de pesos trasnpuesta w2
    static double [][]B2=new double [10][1];//vector de umbral b2
        
    static double [][]W2temp=new double [10][20];//requerida para sacar a w2
    static double [][]W1temp=new double [20][40];//requerida para sacar a w1
        
    static double [][]F1=new double [20][20];//matrix de diagonales f1
    static double [][]F2=new double [10][10];//matrix de diagonales f2
        
    static double [][]S1=new double [20][1];//valor de correccion s1
    static double [][]S2=new double [10][1];//valor de correccion s2
       
    static double [][]Error=new double [10][1];//error general
    static double [][]E=new double [10][1];//error de targets cambiable
        
    static double [][]P=new double[40][1];//almacena el vector de entrada
    static double [][]PT=new double[1][40];//vector de entrada transpuesto
    static double [][]A1=new double[20][1];//salida de la primera BackpropagationNN
    static double [][]A1T=new double[1][20];//salida transpuesta de la primera BackpropagationNN
    static double [][]A2=new double[10][1];//salida de la segunda BackpropagationNN
    
    static int aceptable=0,parada=0;//valores de control para detener el aprendizaje
    
    public static void entrenar(){
        int epocas=10000;     
        
        //rellenamos aleratoriamente (0 o 1) las matrices w1,w2,b1 y b2---------
        for(int i = 0; i < 20;i++){
            for(int j=0;j<40;j++){
                W1[i][j]=(int)(Math.random() * 2);
            }
        }       
        for(int i = 0; i <20;i++){
            for(int j=0;j<1;j++){
                B1[i][j]=(int)(Math.random() * 2);
            }
        }
        for(int i = 0; i < 10;i++){
            for(int j=0;j<20;j++){
                W2[i][j]=(int)(Math.random() * 2);
            }
        }
        for(int i = 0; i <10;i++){
            for(int j=0;j<1;j++){
                B2[i][j]=(int)(Math.random() * 2);
            }
        }
        //----------------------------------------------------------------------
        
        //comenzamos el entrenamiento por iteraciones y epocas
        do{
            parada=0;
            //comenzamos las diez iteraciones-----------------------------------
            for(int I = 0; I <10; I++){
                switch(I){
                    case 0://iteracion del 0
                        P= Numbers.p0;
                        E= Numbers.t0;//Target=Numbers.t0;
                        Error=iteracion(E,0);
                        retropropagacion(Error,0);//mandamos a llamar la funcion de retropropagacion enviando el error
                    break;
                    case 1://iteracion del 1
                        P= Numbers.p1;
                        E= Numbers.t1;//Target=Numbers.t;
                        Error=iteracion(E,1);
                        retropropagacion(Error,1);//mandamos a llamar la funcion de retropropagacion enviando el error
                    break;
                    case 2://iteracion del //iteracion del 2
                        P= Numbers.p2;
                        E= Numbers.t2;//Target=Numbers.t;
                        Error=iteracion(E,2);
                        retropropagacion(Error,2);//mandamos a llamar la funcion de retropropagacion enviando el error
                    break;
                    case 3://iteracion del 3
                        P= Numbers.p3;
                        E= Numbers.t3;//Target=Numbers.t;
                        Error=iteracion(E,3);
                        retropropagacion(Error,3);//mandamos a llamar la funcion de retropropagacion enviando el error
                    break;
                    case 4://iteracion del 4
                        P= Numbers.p4;
                        E= Numbers.t4;//Target=Numbers.t;
                        Error=iteracion(E,4);
                        retropropagacion(Error,4);//mandamos a llamar la funcion de retropropagacion enviando el error
                    break;
                    case 5://iteracion del 5
                        P= Numbers.p5;
                        E= Numbers.t5;//Target=Numbers.t;
                        Error=iteracion(E,5);
                        retropropagacion(Error,5);//mandamos a llamar la funcion de retropropagacion enviando el error
                    break;
                    case 6://iteracion del 6
                        P= Numbers.p6;
                        E= Numbers.t7;//Target=Numbers.t;
                        Error=iteracion(E,6);
                        retropropagacion(Error,6);//mandamos a llamar la funcion de retropropagacion enviando el error
                    break;
                    case 7://iteracion del 7
                        P= Numbers.p7;
                        E= Numbers.t7;//Target=Numbers.t;
                        Error=iteracion(E,7);
                        retropropagacion(Error,7);//mandamos a llamar la funcion de retropropagacion enviando el error
                    break;
                    case 8://iteracion del 8
                        P= Numbers.p8;
                        E= Numbers.t9;//Target=Numbers.t;
                        Error=iteracion(E,8);
                        retropropagacion(Error,8);//mandamos a llamar la funcion de retropropagacion enviando el error
                    break;
                    case 9://iteracion del 9
                        P= Numbers.p9;
                        E= Numbers.t9;//Target=Numbers.t;
                        Error=iteracion(E,9);
                        retropropagacion(Error,9);//mandamos a llamar la funcion de retropropagacion enviando el error
                    break;
                }       
            }
            //------------------------------------------------------------------
            
            //dividimos el error generadfo entre diez (promediampos por lotes)--
            for(int x=0;x<10;x++){
                for(int y=0;y<1;y++){
                    Error[x][y]=(Error[x][y])/10;
                }
            }//-----------------------------------------------------------------
            //epocas--;//restamos una epoca            
        }while(parada!=10);//termina de iterar si se ha llegado a un valor aceptable
        System.out.println("PRESTO");     
    }
    
    public static double[][] iteracion(double[][] E,int iteracion){
        A1=logsig(MatrixUtils.add((MatrixUtils.multiply(W1,P)),B1));//obtenemos la salida de la primera BackpropagationNN
        A2=logsig(MatrixUtils.add((MatrixUtils.multiply(W2,A1)),B2));// obtenemos la salida de la segunda BackpropagationNN
        aceptable=MatrixUtils.substracterror(E, A2, iteracion);
        parada=parada+aceptable;
        E=MatrixUtils.substract(E,A2);//obtenemos el error, restando el target con a2
        return E;
    }
    
    public static double[][]retropropagacion(double[][] E,int parada){
        
            switch(parada){
                case 0:
                    P= Numbers.p0;
                break;
                case 1:
                    P= Numbers.p1;
                break;
                case 2:
                    P= Numbers.p2;
                break;
                case 3:
                    P= Numbers.p3;
                break;
                case 4:
                    P= Numbers.p4;
                break;
                case 5:
                    P= Numbers.p5;
                break;
                case 6:
                    P= Numbers.p6;
                break;
                case 7:
                    P= Numbers.p7;
                break;
                case 8:
                    P= Numbers.p8;
                break;
                case 9:
                    P= Numbers.p9;
                break;
            }
            A1=logsig(MatrixUtils.add((MatrixUtils.multiply(W1,P)),B1));//obtenemos a1 con la matrix acttual
            A2=logsig(MatrixUtils.add((MatrixUtils.multiply(W2,A1)),B2));//obtenemos a2 con la matrix y puntos acutal
            
            int control=0;
            for(int x=0;x<10;x++){
                for(int y=0;y<10;y++){
                    if(control==x&&control==y){
                        F2[x][y]=A2[x][0];
                        control++;
                    }
                    else{
                        F2[x][y]=0;
                    }
                }
            }//saca la matrix diagonal de S2
            control=0;
            for(int x=0;x<20;x++){
                for(int y=0;y<20;y++){
                    if(control==x&&control==y){
                        F1[x][y]=A1[x][0];
                        control++;
                    }
                    else{
                        F1[x][y]=0;
                    }
                }
            }//saca la matrix diagonal de S1 
            
            //saca S2-----------------------------------------------------------
            S2=(MatrixUtils.multiply(F2,E));
            for(int x=0;x<10;x++){
                S2[x][0]=(S2[x][0])*(-2);
            }//multiplica por menos 2 a s2--------------------------------------
                        
            for(int x=0;x<20;x++){
                for(int y=0;y<10;y++){
                    W2T[x][y]=W2[y][x];
                }
            }//transpone la segunda matrix
           S1=MatrixUtils.multiply((MatrixUtils.multiply(F1,W2T)),S2);// obtenemos s1
           
           ////////////////////////////////////////////////////////
                        
           //obtenemos la nueva matrix W2//////////////////////////
           for(int x=0;x<1;x++){
               for(int y=0;y<20;y++){
                   A1T[x][y]=A1[y][x];
               }
           }//transpone la A1 
           W2temp= MatrixUtils.multiply(S2,A1T);
           for(int x=0;x<10;x++){
               for(int y=0;y<20;y++){
                   W2temp[x][y]=(W2temp[x][y])*(.01);
               }
           }//multiplica W2temp por el factor de aprendizaje
           W2=MatrixUtils.substract(W2,W2temp);
           ////////////////////////////////////////////////////////
                        
           //obtenemos la nueva matrix b2//////////////////////////
           for(int x=0;x<10;x++){
               S2[x][0]=(S2[x][0])*(.01);
           }//multiplica S2 por el factor de aprendizaje
           B2=MatrixUtils.substract(B2,S2);
           ////////////////////////////////////////////////////////
                        
           //obtenemos la nueva matrix W1//////////////////////////
           for(int x=0;x<1;x++){
               for(int y=0;y<40;y++){
                   PT[x][y]=P[y][x];
               }
           }//transpone la P 
           W1temp= MatrixUtils.multiply(S1,PT);
           for(int x=0;x<20;x++){
               for(int y=0;y<40;y++){
                   W1temp[x][y]=(W1temp[x][y])*(.01);
               }
           }//multiplica W1temp por el factor de aprendizaje
           W1=MatrixUtils.substract(W1,W1temp);
           ////////////////////////////////////////////////////////
                        
           //obtenemos la nueva matrix b1//////////////////////////
           for(int x=0;x<20;x++){
               S1[x][0]=(S1[x][0])*(.01);
           }//multiplica S1 por el factor de aprendizaje
           B1=MatrixUtils.substract(B1,S1);
           ////////////////////////////////////////////////////////
        return null;                        
    }
    
    public static int reconocer(double[][] entradas){
        int numero=0;
        double actual,grande;
        P=entradas;
        A1=logsig(MatrixUtils.add((MatrixUtils.multiply(W1,P)),B1));
        A2=logsig(MatrixUtils.add((MatrixUtils.multiply(W2,A1)),B2));
        numero=0;
        System.out.println(A2[0][0]+" ");
        grande=A2[0][0];
        for(int x=1;x<10;x++){
            System.out.println(A2[x][0]+" ");
            actual=A2[x][0];
            if(actual>grande){
                numero=x;
                grande=actual;
                System.out.println("\n\n"+actual+" actual");
                System.out.println(A2[x-1][0]+" anterior");
                System.out.println(numero+" numero\n\n");
            }
        }
        return numero;
    }
    
    public static double[][] logsig(double[][] a){
        double temp=0,awe=0;
        double[][] result = a;
        for(int i=0; i< result.length; i++){
            for(int j=0; j < result[0].length; j++){
                temp=result[i][j];
                result[i][j]=((1)/(1+Math.exp(-temp)));
            }
        }
        return result;
    }
    
    
}

/*System.out.println("\n\nW2");
        for(int x=0;x<10;x++){
            for(int y=0;y<20;y++){
                System.out.print(W2[x][y]+" ");
            }
            System.out.print("\n");
        }
        System.out.println("\n\nB2");
        for(int x=0;x<10;x++){
            for(int y=0;y<1;y++){
                System.out.print(B2[x][y]+" ");
            }
            System.out.print("\n");
        }
        System.out.println("\n\nW1");
        for(int x=0;x<20;x++){
            for(int y=0;y<40;y++){
                System.out.print(W1[x][y]+" ");
            }
            System.out.print("\n");
        }
        System.out.println("\n\nB1");
        for(int x=0;x<20;x++){
            for(int y=0;y<1;y++){
                System.out.print(B1[x][y]+" ");
            }
            System.out.print("\n");
        }*/


            /*//ponemos el error global en 0 cada nueva epoca---------------------
            for(int i = 0; i < 10;i++){
                for(int j=0;j<1;j++){
                    Error[i][j]=0;
                }
            }*/