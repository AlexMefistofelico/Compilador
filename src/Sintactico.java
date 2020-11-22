/**DESDE AQUI GLC*/
/**
<programa>				->	begin <declaraciones><ordenes> end
<declaraciones>			->	<declaracion>;<sig_declaraciones>
<sig_declaraciones>		->	<declaracion>;<sig_declaraciones>| ? 
<declaracion>			->	<tipo><lista_variables>
<tipo>					->	entero|real
<lista_variables>		->	identificador <sig_lista_variables>
<sig_lista_variables>	->	,<lista_variables>| ?
<ordenes>				->	<orden>;<sig_ordenes>
<sig_ordenes>			->	<orden>;<sig_ordenes>| ?
<orden>					->	<condicion>|<bucle_while>|<asignar>
<condicion>				->	if(<comparacion>)<ordenes><sig_condicion>
<sig_condicion>			->	end|else <ordenes> end
<comparacion>			->	<operador><condicion_op><operador>
<condicion_op>			->	=|<=|>=|<>|<|>
<operador>				->	identificador|<numeros>
<numeros>				->	num_entero|num_real
<bucle_while>			->	while(<comparacion>)<ordenes>endwhile
<asignar>				->	identificador := <expresion_arit>
<expresion_arit>		->	(<expresion_arit><operador_arit><expresion_arit>)<exp_arit>|identificador <exp_arit>|<numeros><exp_arit>
<exp_arit>				->	<operador_arit><expresion_arit><exp_arit>| ?
<operador_arit>			->	+|*|-|/




PARA NUESTRO PROPOSITO....



<programa>				->	begin <declaraciones><ordenes> end
<declaraciones>			->	<declaracion>;<sig_declaraciones>
<sig_declaraciones>		->	<declaracion>;<sig_declaraciones>
<sig_declaraciones>		->	? 
<declaracion>			->	<tipo><lista_variables>
<tipo>					->	entero
<tipo>					->	real
<lista_variables>		->	identificador <sig_lista_variables>
<sig_lista_variables>	->	,<lista_variables>
<sig_lista_variables>	->	?
<ordenes>				->	<orden>;<sig_ordenes>
<sig_ordenes>			->	<orden>;<sig_ordenes>
<sig_ordenes>			->	?
<orden>					->	<condicion>
<orden>					->	<bucle_while>
<orden>					->	<asignar>
<condicion>				->	if(<comparacion>)<ordenes><sig_condicion>
<sig_condicion>			->	end
<sig_condicion>			->	else <ordenes> end
<comparacion>			->	<operador><condicion_op><operador>
<condicion_op>			->	=
<condicion_op>			->	<=
<condicion_op>			->	>=
<condicion_op>			->	<>
<condicion_op>			->	<
<condicion_op>			->	>
<operador>				->	identificador
<operador>				->	<numeros>
<numeros>				->	num_entero
<numeros>				->	num_real
<bucle_while>			->	while(<comparacion>)<ordenes>endwhile
<asignar>				->	identificador := <expresion_arit>
<expresion_arit>		->	(<expresion_arit><operador_arit><expresion_arit>)<exp_arit>
<expresion_arit>		->	identificador <exp_arit>
<expresion_arit>		->	<numeros><exp_arit>
<exp_arit>				->	<operador_arit><expresion_arit><exp_arit>
<exp_arit>				->	?
<operador_arit>			->	+
<operador_arit>			->	*
<operador_arit>			->	-
<operador_arit>			->	/


*/


import java.util.*;
/**
 * Clase que simula el comportamiento del Analizador Sintáctico
 * @author INF-3631
 * @version 11/11/2020
 */
public class Sintactico{
	/**
	 * Número máximo de errores que puede tener la compilación antes de saltar.
	 */
	int NROERRORES = 10;	
	/**
	 * Número de elementos terminales que tiene la gramática
	 */
	int nroTerminales = 28;
	/**
	 * Número de no terminales que tiene la gramática
	 */
	int nroNoTerminales = 21;
	/**
	 * Tamaño máximo de una producción de nuestra gramática (número máximo de terminales y no terminales que puede tener)
	 */
	int produccion = 6;
	/**
	 * Número de errores que tiene el código en cada momento de compilación
	 */
	int contError;
	/**
	 * Tabla de Análsis Sintáctico Predictivo y No Recursivo
	 */
	String tablaAS[][][] = new String[nroNoTerminales][nroTerminales][produccion];
	/**
	 * Pila del Analizador Sintáctico Predictivo y No Recursivo
	 */
	ArrayList Pila = new ArrayList();
	/**
	 * Variable donde almacenar el token que se está evaluando en ese momento
	 */
	Token token;
	/**
	 * Variable donde almacenar el token que se va a evaluar a continuación
	 * (sólo se usa para la gestión de errores)
	 */
	Token tokenSig;
	/**
	 * Variable que me indica si el siguiente token está en tokenSig o lo tengo que pedir mediante nextToken()
	 */
	boolean pedido=false;
	/**
	 * Vector que contiene los errores léxicos y sintácticos de la fase de compilación
	 */
	ArrayList errores ;
	/**
	 * Objeto que gestiona el comportamiento del Analizador Léxico
	 */
	Lexico analizadorLexico;
	/**
	 * Vector donde se guarda el estado de la pila en cada iteración del analizador sintáctico (sólo sirve para mostrar dicha información por pantalla)
	 */
	ArrayList EstadoPila = new ArrayList();	
	/**
	 * Variable que guarda los terminales y no terminales de la siguientes producción a realizar (sólo sirve para mostrar dicha información por pantalla)
	 */
	String gramatica;					
	/**
	 * Vector que guarda todas las producciones dadas por la variable "gramática" (sólo sirve para mostrar dicha información por pantalla)
	 */
	ArrayList Producciones = new ArrayList();
	
	/**
	 * Método constructor que crea el objeto Sintáctico
	 *@param codigo código fuente a compilar
	 */
	public Sintactico(String []codigo){
		analizadorLexico = new Lexico(codigo); 			//Objeto que simula el Analizador Léxico
		int filaTabla=0, columnaTabla=0;				// Columna y fila de la tabla de AS.
		String cimaPila;								// Cima de la pila.
		token=analizadorLexico.nextToken();				//Leo el primer token del programa a compilar
		errores = analizadorLexico.errores;				// Tabla de errores.
		contError = errores.size();						// Contador de errores iniciliazado con el numero de errores léxicos.
		// Insertamos el primer elemento de la pila que es $.
		Pila.add("$");

		// Insertamos el segundo elemento de la pila que es la variable inicial de la gramática.
		Pila.add("<programa>");
		
		// Creamos la tabla de Análisis Sintáctico.
		crearTablaDeAnalisisSintactico();
		String pilaActual;
		/*Este bucle lo utilizamos para que el programa no se salga de la función
		mientra el elemento cima de pila sea distinto de $ y el número de errores sea
		distinto al número máximo*/
		do{
			
			//Esto sólo sirve para mostrar dicha información por pantalla
			pilaActual="";
			//Estado de la pila en cada iteraccion.
			for(int i=0; i<Pila.size();i++)
				pilaActual += (String)Pila.get(i);
			pilaActual += "\n";
			// La guardamos en el vector.
			EstadoPila.add(pilaActual);
			
			
			// Sacamos la cima de la pila.
			cimaPila = (String)Pila.get(Pila.size()-1);			
			
			// Si el primer elemento de la pila es Terminal o $
			if(esTerminal(cimaPila) ||  cimaPila.equals("$")){
				// Si la cima el $ o "begin end" (que es estado nulo), entonces lo eliminamos.
				if(cimaPila.equals("beginend") ||  cimaPila.equals("$")){
				
					eliminarPila();
					// Esto sólo sirve para mostrar dicha información por pantalla
					Producciones.add("Eliminar terminal -> " + cimaPila);
                
                }else{
					// Si elemento pila es igual al elemento de la tabla token
				
					if(cimaPila.equals(token.getTok())){
						eliminarPila();    // Eliminar elemento de la pila
						//Esto sólo sirve para mostrar dicha información por pantalla
						Producciones.add("Eliminar terminal -> " + cimaPila);
						
						if(pedido!=true)
							token=analizadorLexico.nextToken();
						else{
							token=tokenSig;
							pedido=false;
						}
					}else{ // Sino entonces error.
						gestionError(cimaPila, 0, 0);
					}
				}
	      	
	      	}else /* Nos encontramos que la cima es un No terminal*/{
	   		
	   			// Calcular fila de la posicion en la tabla
	        	filaTabla = posicionFila(cimaPila);	
				// Calcular columna de la posicion en la tabla
				columnaTabla = posicionColumna(token.getTok());
				
				//Casos especiales en los que el nulo
				if(filaTabla==2 && (columnaTabla!=3&& columnaTabla!=4)){
					columnaTabla = 26;
				}
			
				if(filaTabla==6 && columnaTabla!=6){
					columnaTabla = 26;
				}
			
				if(filaTabla==8 && (columnaTabla!=5&& columnaTabla!=7&& columnaTabla!=19)){
					columnaTabla = 26;
				}
			
				if(filaTabla==19 && (columnaTabla!=22&& columnaTabla!=23&& columnaTabla!=24&& columnaTabla!=25)){
					columnaTabla = 26;
				}
				
				/*
				 * Si la tabla de AS en la posicion indicada contiene 
				 * una producción, entonces continuamos insertandola en la pila 
				 * y aplicando el semantico.
				 */
				if(tablaAS[filaTabla][columnaTabla][0]!=null){
					// Eliminamos no terminal de la pila.
					eliminarPila();
				
					//Esto sólo sirve para mostrar dicha información por pantalla
                    Producciones.add("Eliminar no terminal -> " + cimaPila);
				
					// Insertamos la produccion correspondiente.
					insertarPila(filaTabla, columnaTabla, cimaPila);
				
				}else{	// Gestion de errores sintaticos.
					
					gestionError(cimaPila, filaTabla, columnaTabla);
					
				}
	      	}
	  	// Mientras que cima pila es $ y numero de errores es 10
		}while(cimaPila.equals("$")!=true && contError < NROERRORES && token!=null);  
	} // Fin del sintactico.
	
	/**
	 * Metodo público para crear la tabla de simbolos.
	 */
	public void crearTablaDeAnalisisSintactico(){
		/*
		 * Produccion
		 * <programa> -> begin <declaraciones><ordenes> end
		 */
		tablaAS[0][0][0] = "begin";
		tablaAS[0][0][1] = "<declaraciones>";
		tablaAS[0][0][2] = "<ordenes>";
		tablaAS[0][0][3] = "end";
		/*
		 * Produccion
		 * <declaraciones> -> <declaracion>;<sig_declaraciones>
		 */
		tablaAS[1][3][0] = "<declaracion>";
		tablaAS[1][3][1] = ";";
		tablaAS[1][3][2] = "<sig_declaraciones>";
		/*
		 * Produccion
		 * <declaraciones> -> <declaracion>;<sig_declaraciones>
		 */
		tablaAS[1][4][0] = "<declaracion>";
		tablaAS[1][4][1] = ";";
		tablaAS[1][4][2] = "<sig_declaraciones>";
		/*
		 * Produccion
		 *	<sig_declaraciones> -> <declaracion>;<sig_declaraciones>
		 */
		tablaAS[2][3][0] = "<declaracion>";
		tablaAS[2][3][1] = ";";
		tablaAS[2][3][2] = "<sig_declaraciones>";
		/*
		 * Produccion 
		 * <sig_declaraciones> -> <declaracion>;<sig_declaraciones>
		 */
		tablaAS[2][4][0] = "<declaracion>";
		tablaAS[2][4][1] = ";";
		tablaAS[2][4][2] = "<sig_declaraciones>";
		/*
		 * Produccion
		 * <sig_declaraciones> -> ? 
		 */
		tablaAS[2][26][0] = "beginend";
		/*
		 * Produccion
		 * <declaracion> -> <tipo><lista_variables>
		 */
		tablaAS[3][3][0] = "<tipo>";
		tablaAS[3][3][1] = "<lista_variables>";
		/*
		 * Produccion
		 * <declaracion> -> <tipo><lista_variables>
		 */
		tablaAS[3][4][0] = "<tipo>";
		tablaAS[3][4][1] = "<lista_variables>";
		/*
		 * Produccion 
		 * <tipo> -> entero
		 */
		tablaAS[4][3][0] = "entero";
		/*
		 * <tipo> -> real
		 */
		tablaAS[4][4][0] = "real";
		/*
		 * Produccion <lista_variables> -> identificador <sig_lista_variables>
		 */
		tablaAS[5][5][0] = "identificador";
		tablaAS[5][5][1] = "<sig_lista_variables>";
		/*
		 * Produccion
		 * <sig_lista_variables> -> ,<lista_variables>
		 */
		tablaAS[6][6][0] = ",";
		tablaAS[6][6][1] = "<lista_variables>";
		/*
		 * Produccion
		 * <sig_lista_variables> -> ?
		 */
		tablaAS[6][26][0] = "beginend";
		/*
		 * Produccion
		 * <ordenes> -> <orden>;<sig_ordenes>
		 */
		tablaAS[7][5][0] = "<orden>";
		tablaAS[7][5][1] = ";";
		tablaAS[7][5][2] = "<sig_ordenes>";
		/*
		 * Produccion
		 * <ordenes> -> <orden>;<sig_ordenes>
		 */
		tablaAS[7][7][0] = "<orden>";
		tablaAS[7][7][1] = ";";
		tablaAS[7][7][2] = "<sig_ordenes>";
		/*
		 * Produccion
		 * <ordenes> -> <orden>;<sig_ordenes>
		 */
		tablaAS[7][19][0] = "<orden>";
		tablaAS[7][19][1] = ";";
		tablaAS[7][19][2] = "<sig_ordenes>";
		/*
		 *	Produccion
		 * <ordenes> -> <orden>;<sig_ordenes>
		 */
		tablaAS[8][5][0] = "<orden>";
		tablaAS[8][5][1] = ";";
		tablaAS[8][5][2] = "<sig_ordenes>";
		/*
		 * Produccion
		 * <sig_ordenes> -> <orden>;<sig_ordenes>
		 */
		tablaAS[8][7][0] = "<orden>";
		tablaAS[8][7][1] = ";";
		tablaAS[8][7][2] = "<sig_ordenes>";
		/*
		 * Produccion 
		 * <sig_ordenes> -> <orden>;<sig_ordenes>
		 */
		tablaAS[8][19][0] = "<orden>";
		tablaAS[8][19][1] = ";";
		tablaAS[8][19][2] = "<sig_ordenes>";
		/*
		 * Produccion 
		 * <sig_ordenes> -> ?
		 */
		tablaAS[8][26][0] = "beginend";
		/*
		 * Produccion
		 * <orden> -> <asignar>
		 */
		tablaAS[9][5][0] = "<asignar>";
		/*
		 * Produccion 
		 * <orden> -> <condicion>
		 */
		tablaAS[9][7][0] = "<condicion>";
		/*
		 * Produccion
		 * <orden> -> <bucle_while>
		 */
		tablaAS[9][19][0] = "<bucle_while>";
		/*
		 * Produccion 
		 * <condicion> -> if(<comparacion>)<ordenes><sig_condicion>
		 */
		tablaAS[10][7][0] = "if";
		tablaAS[10][7][1] = "(";
		tablaAS[10][7][2] = "<comparacion>";
		tablaAS[10][7][3] = ")";
		tablaAS[10][7][4] = "<ordenes>";
		tablaAS[10][7][5] = "<sig_condicion>";
		/*
		 * Produccion
		 * <sig_condicion> -> end
		 */
		tablaAS[11][1][0] = "end";
		/*
		 * Produccion
		 * <sig_condicion> -> else <ordenes> end
		 */
		tablaAS[11][10][0] = "else";
		tablaAS[11][10][1] = "<ordenes>";
		tablaAS[11][10][2] = "end";
		/*
		 * Produccion 
		 * <comparacion> -> <operador><condicion_op><operador>
		 */
		tablaAS[12][5][0] = "<operador>";
		tablaAS[12][5][1] = "<condicion_op>";
		tablaAS[12][5][2] = "<operador>";
		/*
		 * Produccion
		 * <comparacion> -> <operador><condicion_op><operador>
		 */
		tablaAS[12][17][0] = "<operador>";
		tablaAS[12][17][1] = "<condicion_op>";
		tablaAS[12][17][2] = "<operador>";
		/*
		 * Produccion
		 * <comparacion> -> <operador><condicion_op><operador>
		 */
		tablaAS[12][18][0] = "<operador>";
		tablaAS[12][18][1] = "<condicion_op>";
		tablaAS[12][18][2] = "<operador>";
		/*
		 * Produccion
		 * <condicion_op> -> =
		 */
		tablaAS[13][11][0] = "=";
		/*
		 * Produccion
		 * <condicion_op> -> <=
		 */
		tablaAS[13][12][0] = "<=";
		/*
		 * Produccion
		 * <condicion_op> -> >=
		 */
		tablaAS[13][13][0] = ">=";
		/*
		 * Produccion
		 * <condicion_op> -> <>
		 */
		tablaAS[13][14][0] = "<>";
		/*
		 * Produccion
		 * <condicion_op> -> <
		 */
		tablaAS[13][15][0] = "<";
		/*
		 * Produccion
		 * <condicion_op> -> >
		 */
		tablaAS[13][16][0] = ">";
		/*
		 * Produccion
		 * <operador> -> identificador
		 */
		tablaAS[14][5][0] = "identificador";
		/*
		 * Produccion 
		 * <operador> -> <numeros>
		 */
		tablaAS[14][17][0] = "<numeros>";
		/*
		 * Produccion 
		 * <operador> -> <numeros>
		 */
		tablaAS[14][18][0] = "<numeros>";
		/*
		 * Produccion 
		 * <numeros> -> num_entero
		 */
		tablaAS[15][17][0] = "num_entero";
		/*
		 * Produccion
		 * <numeros> -> num_real
		 */
		tablaAS[15][18][0] = "num_real";
		/*
		 * Produccion 
		 * <bucle_while> -> while(<comparacion>)<ordenes>endwhile
		 */
		tablaAS[16][19][0] = "while";
		tablaAS[16][19][1] = "(";
		tablaAS[16][19][2] = "<comparacion>";
		tablaAS[16][19][3] = ")";
		tablaAS[16][19][4] = "<ordenes>";
		tablaAS[16][19][5] = "endwhile";
		/*
		 * Produccion 
		 * <asignar> -> identificador := <expresion_arit>
		 */
		tablaAS[17][5][0] = "identificador";
		tablaAS[17][5][1] = ":=";
		tablaAS[17][5][2] = "<expresion_arit>";
		/*
		 * Produccion 
		 * <expresion_arit> -> identificador <exp_arit>
		 */
		tablaAS[18][5][0] = "identificador";
		tablaAS[18][5][1] = "<exp_arit>";
		/*
		 * Produccion
		 * <expresion_arit> -> (<expresion_arit><operador_arit><expresion_arit>)<exp_arit>
		 */
		tablaAS[18][8][0] = "(";
		tablaAS[18][8][1] = "<expresion_arit>";
		tablaAS[18][8][2] = "<operador_arit>";
		tablaAS[18][8][3] = "<expresion_arit>";
		tablaAS[18][8][4] = ")";
		tablaAS[18][8][5] = "<exp_arit>";
		/*
		 * Produccion 
		 * <expresion_arit> -> <numeros><exp_arit>
		 */
		tablaAS[18][17][0] = "<numeros>";
		tablaAS[18][17][1] = "<exp_arit>";
		/*
		 * Produccion
		 * <expresion_arit> -> <numeros><exp_arit>
		 */
		tablaAS[18][18][0] = "<numeros>";
		tablaAS[18][18][1] = "<exp_arit>";
		/*
		 * Produccion 
		 * <exp_arit> -> <operador_arit><expresion_arit><exp_arit>
		 */
		tablaAS[19][22][0] = "<operador_arit>";
		tablaAS[19][22][1] = "<expresion_arit>";
		tablaAS[19][22][2] = "<exp_arit>";
		/*
		 * Produccion
		 * <exp_arit> -> <operador_arit><expresion_arit><exp_arit>
		 */
		tablaAS[19][23][0] = "<operador_arit>";
		tablaAS[19][23][1] = "<expresion_arit>";
		tablaAS[19][23][2] = "<exp_arit>";
		/*
		 * Produccion 
		 * <exp_arit> -> <operador_arit><expresion_arit><exp_arit>
		 */
		tablaAS[19][24][0] = "<operador_arit>";
		tablaAS[19][24][1] = "<expresion_arit>";
		tablaAS[19][24][2] = "<exp_arit>";
		/*
		 * Produccion 
		 * <exp_arit> -> <operador_arit><expresion_arit><exp_arit>
		 */
		tablaAS[19][25][0] = "<operador_arit>";
		tablaAS[19][25][1] = "<expresion_arit>";
		tablaAS[19][25][2] = "<exp_arit>";
		/*
		 * Produccion 
		 * <exp_arit> -> ?
		 */
		tablaAS[19][26][0] = "beginend";
		/*
		 * Produccion 
		 * <operador_arit> -> +
		 */
		tablaAS[20][22][0] = "+";
		/*
		 * Produccion
		 * <operador_arit> -> *
		 */
		tablaAS[20][23][0] = "*";
		/*
		 * Produccion 
		 * <operador_arit> -> -
		 */
		tablaAS[20][24][0] = "-";
		/*
		 * Produccion 
		 * <operador_arit> -> /
		 */
		tablaAS[20][25][0] = "/";
	}
	
	/**
	 * Método que nos devuelve si la la cima de pila es o no terminal
	 * @param cima Cima de pila en ese instante
	 * @return variable booleana que indica si la cima es un terminal o un no terminal
	 */
	public boolean esTerminal(String cima)
	{
		if(cima.equals("<declaraciones>") || cima.equals("<declaracion>") || cima.equals("<tipo>") || cima.equals("<lista_variables>") || 
		   cima.equals("<ordenes>") || cima.equals("<orden>") || cima.equals("<condicion>") || cima.equals("<comparacion>") || 
		   cima.equals("<condicion_op>") || cima.equals("<operador>") || cima.equals("<numeros>") || cima.equals("<bucle_while>") || 
		   cima.equals("<asignar>") || cima.equals("<expresion_arit>") || cima.equals("<operador_arit>") || 
		   cima.equals("<sig_declaraciones>") || cima.equals("<sig_lista_variables>") || cima.equals("<sig_ordenes>") || cima.equals("<programa>") || 
		   cima.equals("<exp_arit>") || cima.equals("<sig_condicion>"))
		   		return false;
		   else
		   		return true;
	}
	
	/**
	 * Método que inserta una nueva producción en la pila.
	 * @param Pila Pila del analizador sintáctico predictivo no recursivo
	 * @param fila Fila en la que se encuentra la cima de pila en la tabla de símbolos
	 * @param columna Columna en la que se encuentra la cima de pila en la tabla de símbolos
	 * @param cima Elemento que hay en la cia de pila en ese momento
	 */
	public void insertarPila(int fila, int columna, String cima){
		int i = 0;			// indice
		int contPila=0;		// Indicará el número de elementos que tiene la produción.
		gramatica = "";		// Se almacenará la produccion (sólo sirve para mostrar dicha información por pantalla)

		// Obtenemos el numero maximo de elementos de la produccion.
		while(contPila<produccion && (tablaAS[fila][columna][contPila])!=null){
			contPila++;
		}

		contPila--;

		for(i = contPila; i>=0; i--){
			// Esto sólo sirve para mostrar dicha información por pantalla
			gramatica = (tablaAS[fila][columna][i]) + " " + gramatica;
			//Inserto la producción en la pila
			Pila.add(tablaAS[fila][columna][i]);
		}
			
		// Esto sólo sirve para mostrar dicha información por pantalla
		gramatica = cima + " --> " + gramatica;
		Producciones.add(gramatica);
	}
	
	/**
	 * Método público para eliminar el primer elemento de la pila.
	 */
	public void eliminarPila()
	{
		int i = Pila.size();
		Pila.remove(i-1);
	}
	
	/**
	 * Método público para la gestión de los errores sintácticos.
	 * @param cima Elemento que hay en la cia de pila en ese momento
	 * @param fila Fila en la que se encuentra la cima de pila en la tabla de símbolos
	 * @param columna Columna en la que se encuentra la cima de pila en la tabla de símbolos
	 */
	public void gestionError(String cima, int fila, int columna){
		int colSig;
		String cimaSig;
		// Aumentamos el número de errores.
		contError++;
		
		if(esTerminal(cima)) //Si la cima es un terminal.
		{
		
			tokenSig=analizadorLexico.nextToken();
			pedido=true;
		
			if(cima.equals(tokenSig)) // Error por token excesivo.
			{
				errores.add(new Error(token.getNumColumna(), token.getNumLinea(),"ERROR Sintático.- Posible token extra."));			
				token=tokenSig;
				pedido=false;
		
			}
			else //Error por falta de parámetros.
			{
				errores.add(new Error(token.getNumColumna(), token.getNumLinea(),"ERROR Sintático.- Posible falta de un token"));			
				eliminarPila();
				//Esto sólo sirve para mostrar dicha información por pantalla
				Producciones.add("Eliminar terminal -> " + cima);
			}
		}
		else //Si la cima el un no terminal.
		{
			tokenSig=analizadorLexico.nextToken();
			pedido=true;
			colSig=this.posicionColumna(tokenSig.getTok());
			cimaSig=(String)(Pila.get(Pila.size()-2));
			
			if(tablaAS[fila][colSig][0]!=null){
				
				if(cimaSig.equals(token.getTok()))	// Falta de un parametro.
				{
					errores.add(new Error(token.getNumColumna(), token.getNumLinea(),"ERROR Sintático.- Posible token extra"));			
					eliminarPila();
					Producciones.add("Eliminar terminal -> " + cima);
				
				}
				else	//Error por párametros extra.
				{
				
					errores.add(new Error(token.getNumColumna(), token.getNumLinea(),"ERROR Sintático.- Posible token extra."));			
					token=tokenSig;
					pedido=false;
				}
			}
			else
			{	//Error en inicio de programa.
				if(cima.equals("<programa>")){
					
					errores.add(new Error(token.getNumColumna(), token.getNumLinea(),"ERROR Sintático.- Inicio de programa mal especificado, imposible continuar con la compilación."));			
					eliminarPila();
					Producciones.add("Eliminar terminal -> " + cima);
				
				}
				else //Error en lista de variables.
				if(cima.equals("<lista_variables>")){
					
					if(tokenSig.equals(",")||tokenSig.equals(";"))
						insertarPila(5, 5, cima);
					
					else{
						errores.add(new Error(token.getNumColumna(), token.getNumLinea(),"ERROR Sintático.- Posible error en lista de variables."));			
						eliminarPila();
						Producciones.add("Eliminar terminal -> " + cima);
					}
				}else //Error en declaraciones.
			
				if(cima.equals("<declaraciones>")){
					
					errores.add(new Error(token.getNumColumna(), token.getNumLinea(),"ERROR Sintático.- Posible error en declaraciones."));			
					eliminarPila();
					Producciones.add("Eliminar terminal -> " + cima);
					insertarPila(fila, 3, cima);
			
				}else  //Error en declaración.
				
				if(cima.equals("<declaracion>")){
					
					errores.add(new Error(token.getNumColumna(), (token).getNumLinea(),"ERROR Sintático.- Posible error en una declaración."));			
					eliminarPila();
					Producciones.add("Eliminar terminal -> " + cima);
					insertarPila(fila, 3, cima);
				
				}else //Error en el tipo de la declaracion.
				
				if(cima.equals("<tipo>")){
					
					errores.add(new Error(token.getNumColumna(), token.getNumLinea(),"ERROR Sintático.- Posible error en el tipo de declaración."));			
					eliminarPila();
					Producciones.add("Eliminar terminal -> " + cima);
					token=tokenSig;
					pedido=false;
				}else //Error en órdenes.
				
				if(cima.equals("<ordenes>")){
					
					errores.add(new Error(token.getNumColumna(), token.getNumLinea(),"ERROR Sintático.- Posible error en declaración de órdenes."));			
					insertarPila(fila, 5, cima);
				
				}else //Error en una orden.
				if(cima.equals("<orden>")){
					
					errores.add(new Error(token.getNumColumna(), token.getNumLinea(),"ERROR Sintático.- Posible error en el bloque de la orden."));			
					eliminarPila();
					Producciones.add("Eliminar terminal -> " + cima);
					token=tokenSig;
					pedido=false;
					while((token.getTok()).equals(";")!=true){
						token=analizadorLexico.nextToken();
					}
					token=analizadorLexico.nextToken();
					while(((String)Pila.get(Pila.size()-1)).equals(";")!=true){
						eliminarPila();
					}
					eliminarPila();
				}else  //Error en la continuación de la condición.
				
				if(cima.equals("<sig_condicion>")){
					
					errores.add(new Error(token.getNumColumna(), token.getNumLinea(),"ERROR Sintático.- Posible error 'else' o 'end'."));			
					eliminarPila();
					Producciones.add("Eliminar terminal -> " + cima);
					token=tokenSig;
					pedido=false;
					while(token.equals("end")!=true && token.equals("else")!=true){
						token=analizadorLexico.nextToken();
					}
				}
				else //Error en la comparación.
				if(cima.equals("<comparacion>")){
					
					errores.add(new Error(token.getNumColumna(), token.getNumLinea(),"ERROR Sintático.- Posible error de comparación."));			
					eliminarPila();
					Producciones.add("Eliminar terminal -> " + cima);
					token=tokenSig;
					pedido=false;
					while(token.equals("else")!=true){
						token=analizadorLexico.nextToken();
					}
				}else //Error en en operador de condición.
				
				if(cima.equals("<condicion_op>")){
					errores.add(new Error(token.getNumColumna(), token.getNumLinea(),"ERROR Sintático.- Posible error en el operador de comparación."));			
					eliminarPila();
					Producciones.add("Eliminar terminal -> " + cima);
				}else //Error en operador.
				
				if(cima.equals("<operador>")){
					String tokenActual="";
					errores.add(new Error(token.getNumColumna(), token.getNumLinea(),"ERROR Sintático.- Posible falta de operador."));			
					eliminarPila();
					Producciones.add("Eliminar terminal -> " + cima);
					token=tokenSig;
					pedido=false;
					tokenActual = token.getTok();
					while(tokenActual.equals(")")!=true && tokenActual.equals("=")!=true && tokenActual.equals("<=")!=true && 
					      tokenActual.equals(">=")!=true && tokenActual.equals("<>")!=true && tokenActual.equals("<")!=true && 
					      tokenActual.equals(">")!=true)
					{
						token=analizadorLexico.nextToken();
						tokenActual = token.getTok();
					}
				}else //Error en expresión aritmética.
				
				if(cima.equals("<expresion_arit>")){
					String tokenActual="";
					errores.add(new Error(token.getNumColumna(), token.getNumLinea(),"ERROR Sintático.- Posible error de expresión aritmética."));			
					
					eliminarPila();
					Producciones.add("Eliminar terminal -> " + cima);
					token=tokenSig;
					pedido=false;
					tokenActual = token.getTok();
					while(tokenActual.equals("+")!=true && tokenActual.equals("*")!=true && tokenActual.equals("-")!=true && 
					      tokenActual.equals("/")!=true && tokenActual.equals(";")!=true && tokenActual.equals(")")!=true)
					{
						token=analizadorLexico.nextToken();
						tokenActual = token.getTok();
					}
				}
			}
		}
	} //Fin de método
	
	/**
	 * Metodo público para averiguar la fila en la tabla de símbolos de la cima de la pila.
	 * @param cima Cima de la Pila.
	 * @return fila de la cima de la pila en la tabla de símbolos
	 */
	public int posicionFila(String cima){
		switch (cima) {
    		case "<programa>": 				return 0;
    		case "<declaraciones>": 		return 1;
    		case "<sig_declaraciones>": 	return 2;
    		case "<declaracion>": 			return 3;
    		case "<tipo>": 					return 4;
    		case "<lista_variables>": 		return 5;
    		case "<sig_lista_variables>":	return 6;
    		case "<ordenes>": 				return 7;
    		case "<sig_ordenes>": 			return 8;
    		case "<orden>": 				return 9;
    		case "<condicion>": 			return 10;
    		case "<sig_condicion>": 		return 11;
    		case "<comparacion>": 			return 12;
    		case "<condicion_op>": 			return 13;
    		case "<operador>": 				return 14;
    		case "<numeros>": 				return 15;
    		case "<bucle_while>": 			return 16;
    		case "<asignar>": 				return 17;
    		case "<expresion_arit>": 		return 18;
    		case "<exp_arit>": 				return 19;
    		case "<operador_arit>": 		return 20;
    		default :						return 0;
    	}
		/*
		int fila = 0;
		
		if(cima.equals("<programa>"))
			fila = 0;
		else if(cima.equals("<declaraciones>"))
			fila =  1;
		else if(cima.equals("<sig_declaraciones>"))
			fila =  2;
		else if(cima.equals("<declaracion>"))
			fila =  3;
		else if(cima.equals("<tipo>"))
			fila =  4;
		else if(cima.equals("<lista_variables>"))
			fila =  5;
		else if(cima.equals("<sig_lista_variables>"))
			fila =  6;
		else if(cima.equals("<ordenes>"))
			fila =  7;
		else if(cima.equals("<sig_ordenes>"))
			fila =  8;
		else if(cima.equals("<orden>"))
			fila =  9;
		else if(cima.equals("<condicion>"))
			fila =  10;
		else if(cima.equals("<sig_condicion>"))
			fila =  11;
		else if(cima.equals("<comparacion>"))
			fila =  12;
		else if(cima.equals("<condicion_op>"))
			fila =  13;
		else if(cima.equals("<operador>"))
			fila =  14;
		else if(cima.equals("<numeros>"))
			fila =  15;
		else if(cima.equals("<bucle_while>"))
			fila =  16;
		else if(cima.equals("<asignar>"))
			fila =  17;
		else if(cima.equals("<expresion_arit>"))
			fila =  18;
		else if(cima.equals("<exp_arit>"))
			fila =  19;
		else if(cima.equals("<operador_arit>"))
			fila =  20;	
		return fila;
		*/
	}

	/**
	 * Metodo público para averiguar la columna en la tabla de símbolos del terminal de la producción.
	 * @param terminal terminal de la producción
	 * @return columna del terminal en la tabla de símbolos
	 */
	public int posicionColumna(String terminal){
		switch (terminal) {
    		case "begin": 			return 0;
    		case "end":				return 1;
    		case ";": 				return 2;
    		case "entero": 			return 3;
    		case "real": 			return 4;
    		case "identificador": 	return 5;
    		case ",": 				return 6;
    		case "if": 				return 7;
    		case "(": 				return 8;
    		case ")": 				return 9;
    		case "else": 			return 10;
    		case "=": 				return 11;
    		case "<=": 				return 12;
    		case ">=": 				return 13;
    		case "<>": 				return 14;
    		case "<": 				return 15;
    		case ">": 				return 16;
    		case "num_entero": 		return 17;
    		case "num_real": 		return 18;
    		case "while": 			return 19;
    		case "endwhile": 		return 20;
    		case ":=": 				return 21;
    		case "+": 				return 22;
    		case "*": 				return 23;
    		case "-": 				return 24;
    		case "/": 				return 25;
    		case "beginend": 		return 26;//? en la tabla esta asi
    		case "$": 				return 27;
    		default : 				return 0;
    	}
    	/*
		int columna = 0;
		if(terminal.equals("begin"))
			columna = 0;
		else if(terminal.equals("end"))
			columna =  1;
		else if(terminal.equals(";"))
			columna =  2;
		else if(terminal.equals("entero"))
			columna =  3;
		else if(terminal.equals("real"))
			columna =  4;
		else if(terminal.equals("identificador"))
			columna =  5;
		else if(terminal.equals(","))
			columna =  6;
		else if(terminal.equals("if"))
			columna =  7;
		else if(terminal.equals("("))
			columna =  8;
		else if(terminal.equals(")"))
			columna =  9;
		else if(terminal.equals("else"))
			columna =  10;
		else if(terminal.equals("="))
			columna =  11;
		else if(terminal.equals("<="))
			columna =  12;
		else if(terminal.equals(">="))
			columna =  13;
		else if(terminal.equals("<>"))
			columna =  14;
		else if(terminal.equals("<"))
			columna =  15;
		else if(terminal.equals(">"))
			columna =  16;
		else if(terminal.equals("num_entero"))
			columna =  17;
		else if(terminal.equals("num_real"))
			columna =  18;
		else if(terminal.equals("while"))
			columna =  19;
		else if(terminal.equals("endwhile"))
			columna =  20;
		else if(terminal.equals(":="))
			columna =  21;
		else if(terminal.equals("+"))
			columna =  22;
		else if(terminal.equals("*"))
			columna =  23;
		else if(terminal.equals("-"))
			columna =  24;
		else if(terminal.equals("/"))
			columna =  25;
		else if(terminal.equals("beginend"))
			columna =  26;
		else if(terminal.equals("$"))
			columna =  27;
		return columna;
		*/
	}
} //fin de clase.

