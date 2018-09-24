package core.analizador;

import java.io.*;
import java.util.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Lexico {

	
	
	public static void lexico(String f) {
		
		String bufferIn;
        try{
            DataInputStream in=new DataInputStream(new FileInputStream(f));//leyendo archivo de entrada
            try{
                while((bufferIn=in.readLine())!=null){//mientras el archivo no este nulo
                    int i=0;
                    String cad=bufferIn.trim();
                    //quitando espacios en blanco
                    while(i<cad.length()){//recorremos la línea
                        char t=cad.charAt(i);//vamos leyendo caracter por caracter
                        if(Character.isDigit(t)){//comprobamos si es un digito
                            String ora="";
                            ora+=t;
                            int j=i+1;
                            while(Character.isDigit(cad.charAt(j))){
                            //mientras el siguiente elemento sea un numero
                                ora+=cad.charAt(j);//concatenamos
                                j++;
                                if(j==cad.length())
                                break;//se rompe el while al final de cada linea
                            }
                            i=j;//movemos a nuestra variable i en la cadena
                            System.out.println("Número-->"+ora);
                            continue;//pasamos al siguiente elemento
                        }//end if si es Digito
                        else if(Character.isLetter(t)){//comprobamos si es una letra
                            String ora="";
                            ora+=t;
                            int j=i+1;
                            while(Character.isLetterOrDigit(cad.charAt(j))){
                            //mientras el siguiente elemento sea una letra o un digito
                            //ya que las variables pueden ser con numeros
                                ora+=cad.charAt(j);
                                j++;
                                if(j==cad.length())break;
                            }
                            i=j;
                            if(palabraReservada(ora)){//comprobamos si es una palabra reservada
                                System.out.println("Palabra reservada="+ora);
                            }
                            else{//caso contrario es un identificador o variable
                                System.out.println("Identificador o variable-->"+ora);
                            }
                            continue;
                        }//end if si es variable
                        else if(!Character.isLetterOrDigit(t)){
                        //si no es letra ni digito entonces...
                            if(evaluarCaracter(t)){//¿es separador?
                                System.out.println("Separador-->"+evaluarSeparador(t));
                            }else{//¿o es un operador?
                                System.out.println("Operador-->"+evaluarOperador(t));
                            }
                            i++;
                            continue;
                        }//end if si es diferente de letra y digito
                    }
                }//end while
            }catch(IOException e){}
        }catch(FileNotFoundException e){}

		
	}
	
	
	 public static boolean evaluarCaracter(char c){
	        if(c=='(') return true;
	        else if(c==')')return true;
	        else if(c=='<')return false;
	        else if(c=='>')return false;
	        else return false;
	    }
	     
	    /**
	    retornamos nuestro caracter de operador
	    */
	    public static char evaluarOperador(char c){
	        char car=' ';
	        if(c=='<')car='<';
	        else if(c=='>')car='>';
	        return car;
	    }
	     
	    /**
	    retornamos nuestro caracter de separador
	    */
	    public static char evaluarSeparador(char c){
	        char car=' ';
	        if(c=='(') car='(';
	        else if(c==')')car=')';
	        return car;
	    }
	     
	    /**
	    buscamos si existe la palabra reservada
	    */
	    public static boolean palabraReservada(String cad){
	        if(cad.equalsIgnoreCase("if")) return true;
	        else if(cad.equalsIgnoreCase("else"))return true;
	        else if(cad.equalsIgnoreCase("break"))return true;
	        else if(cad.equalsIgnoreCase("private"))return true;
	        else if(cad.equalsIgnoreCase("switch"))return true;
	        else if(cad.equalsIgnoreCase("double"))return true;
	        else if(cad.equalsIgnoreCase("static"))return true;
	        else if(cad.equalsIgnoreCase("do"))return true;
	        
	        //con equalsIgnoreCase no  importa si esta en mayusculas o minusculas o alternadas
	        else return false;
	    }

	    
	    
	    
	
	
	//main para controlar excepciones de entrada/salida
	public static void main(String[] args) throws IOException {
		
	
		String contenido= "C:\\Users\\Oscar\\eclipse-workspace\\AnalizadorLexico\\src\\core\\analizador\\holamundo.txt";
		Lexico methods = null;
		Lexico.lexico(contenido);
		
		
		
		

	}

}
