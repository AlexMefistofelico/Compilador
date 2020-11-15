/**
 
NUESTRAS TERMINALES PARA NUESTRO PROPOSITO...

begin
end 
;
entero
real
identificador 
,
if
(
)
else
=
<=
>=
<>
<
>
num_entero
num_real
while
endwhile
:=
+
*
-
/
?   

*/

import java.io.*;
import java.util.*;


/**
 * Clase que simula el comportamiento del Analizador L�xico
 * @author INF-3631
 * @version 11/11/2020
 */
public class Lexico{
	/**
	 * Contenido de la l�nea del programa que estoy revisando l�xicamente
	 */
	String linea;
	/**
	 * Vector con los errores que van produci�ndose en la fase l�xica
	 */
	ArrayList errores;
	/**
	 * Vector con los s�mbolos del c�digo a compilar
	 */
	ArrayList simbolos;
	/**
	 * N�mero de columna en la que el an�lisis l�xico est� dentro del c�digo fuente a compilar
	 */
	int numColumna;
	/**
	 * N�mero de l�nea en la que el an�lisis l�xico est� dentro del c�digo fuente a compilar
	 */
	int numLinea;
	/**
	 * N�mero de errores l�xicos cometidos
	 */
	int numErrores;
	/**
	 * Totalidad del c�digo fuente a compilar, leido de un archivo de texto
	 */
	String[] codigo;
	/**
	 * Tabla con las palabras reservadas del lenguaje
	 */
	String[] tabla;
	/**
	 * Variable que ayuda a aportar el atributo posici�n en la tabla de s�mbolos a los diferentes s�mbolos que reconoce el analizador l�xico
	 */
	int posSimbolo;
	/**
	 * Estado actual del DT
	 */
	private int estado;
	/**
	 * Variable que se usa para mostrar por pantalla los tokens que el analizador l�xico ha detectado
	 */
	String listadoTokens="";
	
	/**
	 * M�todo contructor que crea el objeto Lexico (Analizador L�xico)
	 * @param codigo c�digo fuente a compilar
	 */
	public Lexico(String [] codigo){
		this.codigo=codigo;
		linea="";
		errores = new ArrayList();
		simbolos = new ArrayList();
		numColumna=0;
		numLinea=0;
		numErrores=0;
		posSimbolo = 0;
		crearTabla();
		estado=0;
	}

	/**
	 * M�todo p�blico que genera cada uno de los tokens de los que consta el programa a compilar
	 * @return token el Token que acaba de generar, null si no hay m�s tokens
	 */
	public Token nextToken(){
		String palabra="";
		char letra=' ';
		char aux = ' ', letraAux = ' ', letraAux2 = ' ';
		Token t = null;
		Simbolo s = null;
		boolean tokenCompleto=false;
		
		while(numLinea<codigo.length && numErrores<10 && tokenCompleto==false){
			switch(estado){
				case 0:
					linea=codigo[numLinea];
					palabra="";
					if(numColumna<linea.length()){
						letra=sigCaracter();
						if(letra!=' ' && letra!='\t' && letra!='\n'){
							if(letra==';')
								estado=8;
							else if(letra==':')
								estado=9;
							else if(letra=='(')
								estado=11;
							else if(letra==')')
								estado=12;
							else if(letra=='<')
								estado=13;
							else if(letra=='=')
								estado=17;
							else if(letra=='>')
								estado=18;
							else if(letra=='+')
								estado=21;
							else if(letra=='-')
								estado=22;
							else if(letra=='*')
								estado=23;
							else if(letra=='/'){
								letraAux = sigCaracter();
								if(letraAux=='/'){
									numColumna=0;
									numLinea++;
									estado=0;
								}
								else if(letraAux=='*'){
									letraAux2=sigCaracter();
									do{
										if(numColumna>=linea.length()){
											numColumna=0;
											numLinea++;
											linea=codigo[numLinea];
										}
										letraAux=letraAux2;
										letraAux2=sigCaracter();
									}while(letraAux!='*'&&letraAux2!='/');
								}else{
									numColumna--;
									estado=24;
								}
							}
							else if(letra==',')
								estado=25;
							else if(Character.isDigit(letra)){
								estado=3;
							}
							else if(Character.isUpperCase(letra)||Character.isLowerCase(letra)){
								estado=1;
							}
							else{
								estado=0;
								numErrores++;
								Error er = new Error(numColumna, numLinea+1, "Error l�xico.- S�mbolo no conocido");
								errores.add(er);
							}
						}
						else{
							estado=0;
						}
					}
					else{
						numLinea++;
						numColumna=0;
						estado=0;
					}
					break;
					
				case 1:
					aux= ' ';
					do{
						palabra = palabra + letra;

						if(numColumna<linea.length()){
							aux = linea.charAt(numColumna);
						}
						if(Character.isUpperCase(aux) || Character.isLowerCase(aux) || aux == '_' || Character.isDigit(letra)){
							letra = sigCaracter();
							aux = letra;
						}
					}while(Character.isUpperCase(aux) || Character.isLowerCase(aux) || aux == '_' || Character.isDigit(letra));
					estado=2;
					break;
					
				case 2:
					estado=0;
					t=identificarId(palabra);
					tokenCompleto=true;
					break;

				case 3:
					aux = ' ';
					do{
						palabra = palabra + letra;

						if(numColumna<linea.length())
							aux = linea.charAt(numColumna);
						if(Character.isDigit(aux)){
							letra = sigCaracter();
							aux = letra;
						}
					}while(Character.isDigit(aux));

					if(aux == '.')
						estado = 5;
					else
						estado = 4;
					break;

				case 4:
					estado = 0;
					tokenCompleto=true;
					t = new Token(3,(numColumna-palabra.length())+1, numLinea+1, posSimbolo++,"Numero entero","num_entero");
					s = new Simbolo(3,(numColumna-palabra.length())+1, numLinea+1,palabra,"",false,false);
					simbolos.add(s);
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;

				case 5:
					letra=sigCaracter();
					palabra=palabra+letra;
					aux = ' ';
					letra = sigCaracter();
					if(Character.isDigit(letra)){
						estado=6;
					}
					else{
						estado=0;
						numErrores++;
						Error er = new Error(numColumna, numLinea+1, "Error l�xico.- Dato num�rico mal escrito");
						errores.add(er);
					}
					break;

				case 6:
					do{
						palabra = palabra + letra;

						if(numColumna<linea.length())
							aux = linea.charAt(numColumna);
						if(Character.isDigit(aux)){
							letra = sigCaracter();
							aux = letra;
						}
					}while(Character.isDigit(aux));
					estado = 7;
					break;

				case 7:
					estado=0;
					t = new Token(3,(numColumna-palabra.length())+1, numLinea+1, posSimbolo++,"Numero real","num_real");
					tokenCompleto=true;
					s = new Simbolo(3,(numColumna-palabra.length())+1, numLinea+1,palabra,"",false,false);
					simbolos.add(s);
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;

				case 8:
					palabra = palabra + letra;
					estado=0;
					t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1,palabra,";");
					tokenCompleto=true;
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;

				case 9:
					palabra = palabra + letra;
					letra = sigCaracter();
					if(letra == '='){
						estado=10;
					}
					else{
						numColumna--;
						estado=0;
						numErrores++;
						Error er = new Error(numColumna, numLinea+1, "Error l�xico.- Expresi�n mal construida");
						errores.add(er);
					}
					break;

				case 10:
					palabra = palabra + letra;
					estado=0;
					t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1,palabra,":=");
					tokenCompleto=true;
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;

				case 11:
					palabra = palabra + letra;
					estado=0;
					t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1,palabra,"(");
					tokenCompleto=true;
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;

				case 12:
					palabra = palabra + letra;
					estado=0;
					t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1,palabra,")");
					tokenCompleto=true;
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;

				case 13:
					palabra = palabra + letra;
					letra = sigCaracter();
					if(letra == '>')
						estado = 15;
					else if(letra == '=')
						estado = 16;
					else{
						numColumna--;
						estado = 14;
					}
					break;

				case 14:
					estado = 0;
					t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1,palabra,"<");
					tokenCompleto=true;
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";;
					break;

				case 15:
					palabra = palabra + letra;
					estado=0;
					t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1,palabra,"<>");
					tokenCompleto=true;
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;

				case 16:
					palabra = palabra + letra;
					estado=0;
					t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1,palabra,"<=");
					tokenCompleto=true;
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;

				case 17:
					palabra = palabra + letra;
					estado=0;
					t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1,palabra,"=");
					tokenCompleto=true;
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;

				case 18:
					palabra = palabra + letra;
					letra = sigCaracter();
					if (letra == '=')
						estado = 20;
					else{
						numColumna--;
						estado = 19;
					}
					break;

				case 19:
					estado=0;
					t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1,palabra,">");
					tokenCompleto=true;
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;

				case 20:
					palabra = palabra + letra;
					estado=0;
					t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1, palabra, ">=");
					tokenCompleto=true;
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;

				case 21:
					palabra = palabra + letra;
					estado=0;
					t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1, palabra, "+");
					tokenCompleto=true;
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;

				case 22:
					palabra = palabra + letra;
					estado=0;
					t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1, palabra, "-");
					tokenCompleto=true;
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;

				case 23:
					palabra = palabra + letra;
					estado=0;
					t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1, palabra, "*");
					tokenCompleto=true;
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;

				case 24:
					palabra = palabra + letra;
					estado=0;
					t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1, palabra, "/");
					tokenCompleto=true;
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;
				case 25:
					palabra = palabra + letra;
					estado=0;
					t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1, palabra, ",");
					tokenCompleto=true;
					//Esto es s�lo para mostrar el listado de los tokens del c�digo fuente
					listadoTokens=listadoTokens + t.getTok() + "\n";
					break;
			}
		}
		return t;
	}
	
	/**
	 * M�todo que identifica si cada cadena de caracteres se corresponde con una palabra reservada, con una variable ya existente
	 * con una nueva variable o, si bien, es una nueva variable pero excede en tama�o
	 * @param palabra nuevo token formado por una cadena de caracteres
	 * @return token el Token que conforma esa cadena de caracteres, si es un error, devolver� null
	 */
	private Token identificarId(String palabra){
		int i, j=0;
		String token = ""/*, tok = ""*/;
		boolean igual1 = false, igual2 = false;
		Token t = null;
		Simbolo s = null;
		//Aqu� se comprueba si se trata de una palabra reservada del lenguaje
		for(i=0; i<tabla.length; i++)
			if(palabra.equals(tabla[i])){
				token = tabla[i];
				igual1 = true;
			}
		if(igual1){
			t = new Token(0,(numColumna-palabra.length())+1, numLinea+1, -1, token, token);
			listadoTokens=listadoTokens + t.getTok() + "\n";
		}
		else{
			//Si no es una palabra reservada, entonces ser� una variable
			//Compruebo que la longitud de la variable sea inferior a 11 caracteres
			if(palabra.length()<11){
				//Compruebo si la variable ya se hab�a incluido anteriormente en el vector de S�mbolos, sino pues lo hacemos ahora
				for(i=0; i<simbolos.size(); i++)
					if(palabra.equals(((Simbolo)simbolos.get(i)).getSimbolo())){
						igual2 = true;
						j = i;
					}
				if(igual2){

					t = new Token(1,(numColumna-palabra.length())+1, numLinea+1, j, "Variable", "identificador");
					listadoTokens=listadoTokens + t.getTok() + "\n";
				}
				else{
					s = new Simbolo(1,(numColumna-palabra.length())+1, numLinea+1, palabra, "", false, false);
					simbolos.add(s);
					t = new Token(1,(numColumna-palabra.length())+1, numLinea+1, posSimbolo++, "Variable", "identificador");
					listadoTokens=listadoTokens + t.getTok() + "\n";
				}
			}//Si la variable excede en tama�o, generamos un error
			else{
				numErrores++;
				Error er = new Error((numColumna-palabra.length())+1, numLinea+1,"Error l�xico.- Variable demasiada larga");
				errores.add(er);
			}
		}
		return t;
	}

	/**
	 * M�todo que devuelve el siguiente caracter de la l�nea que se est� analizando en ese momento del c�digo fuente
	 * @return caracter con el que trabajar
	 */
	private char sigCaracter(){
		char c = ' ';
		if(numColumna<linea.length())
			c = linea.charAt(numColumna++);
		return c;
	}

	/**
	 * M�todo que crea dos tablas con las palabras reservadas propias del lenguaje de programaci�n a compilar y sus correspondiente abreviaturas
	 * dadas en la fase de dise�o del compilador.
	 */
	private void crearTabla(){

  		tabla = new String[8];

		tabla[0] = "begin";
		tabla[1] = "end";
		tabla[2] = "entero";
		tabla[3] = "real";
		tabla[4] = "while";
		tabla[5] = "endwhile";
		tabla[6] = "if";
		tabla[7] = "else";
	}
}
