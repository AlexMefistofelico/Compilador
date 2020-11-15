/**
 * Cada uno de los tokens se guardarán con esta estructura.
 * @author INF-3631
 * @version 11/11/2020
 */

public class Simbolo {
	/**
	 * Será un 1 en caso de ser una variable. Será un 2 en caso de ser un
	 * literal. Será un 3 en caso de ser un número.
	 */
	private int tipoSimbolo;
	/**
	 * Número de columna donde se encuentra el símbolo.
	 */
	private int numColumna;
	/**
	 * Número de línea donde se encuentra el símbolo.
	 */
	private int numLinea;
	/**
	 * El símbolo que insertamos.
	 */
	private String simbolo;
	/**
	 * El tipo del símbolo.
	 */
	private String tipo;
	/**
	 * Variable booleana que informa si el símbolo está o no declarado.
	 */
	private boolean declarado;
	/**
	 * Variable booleana que informa si el símbolo está o no inicializado.
	 */
	private boolean inicializado;
	/**
	 * Método constructor que crea el objeto Símbolo
	 * @param tipoSimbolo Será un 1 en caso de ser una variable. Será un 2 en caso de ser un literal. Será un 3 en caso de ser un número.
	 * @param numColumna Número de columna donde se encuentra el símbolo.
	 * @param numLinea Número de línea donde se encuentra el símbolo.
	 * @param simbolo El símbolo que insertamos.
	 * @param tipo El tipo del símbolo.
	 * @param declarado Variable booleana que informa si el símbolo está o no declarado.
	 * @param inicializado Variable booleana que informa si el símbolo está o no inicializado.
	 */
	public Simbolo(int tipoSimbolo, int numColumna, int numLinea, String simbolo, String tipo, boolean declarado, boolean inicializado) {
		this.tipoSimbolo = tipoSimbolo;
		this.numColumna = numColumna;
		this.numLinea = numLinea;
		this.simbolo = simbolo;
		this.tipo = tipo;
		this.declarado = declarado;
		this.inicializado = inicializado;
	}
	/**
	 * Método que devuelve el valor de la variable declarado
	 * @return Variable booleana que informa si el símbolo está o no declarado.
	 */
	public boolean isDeclarado() {
		return declarado;
	}
	/**
	 * Método que modifica el valor de la variable declarado
	 * @param declarado Variable booleana que informa si el símbolo está o no declarado.
	 */
	public void setDeclarado(boolean declarado) {
		this.declarado = declarado;
	}
	/**
	 * Método que devuelve el valor de la variable inicializado
	 * @return Variable booleana que informa si el símbolo está o no inicializado.
	 */
	public boolean isInicializado() {
		return inicializado;
	}
	/**
	 * Método que modifica el valor de la variable inicializado
	 * @param inicializado Variable booleana que informa si el símbolo está o no inicializado.
	 */
	public void setInicializado(boolean inicializado) {
		this.inicializado = inicializado;
	}
	/**
	 * Método que devuelve el valor de la variable numColumna
	 * @return Número de columna donde se encuentra el símbolo.
	 */
	public int getNumColumna() {
		return numColumna;
	}
	/**
	 * Método que modifica el valor de la variable numColumna
	 * @param numColumna Número de columna donde se encuentra el símbolo.
	 */
	public void setNumColumna(int numColumna) {
		this.numColumna = numColumna;
	}
	//resto de métodos get y set
	/**
	 * Método que devuelve el valor de la variable numLinea
	 * @return Número de línea donde se encuentra el símbolo.
	 */
	public int getNumLinea() {
		return numLinea;
	}
	/**
	 * Método que modifica el valor de la variable numLinea
	 * @param numLinea Número de línea donde se encuentra el símbolo.
	 */
	public void setNumLinea(int numLinea) {
		this.numLinea = numLinea;
	}
	/**
	 * Método que devuelve el valor de la variable simbolo
	 * @return El símbolo que insertamos.
	 */
	public String getSimbolo() {
		return simbolo;
	}
	/**
	 * Método que modifica el valor de la variable simbolo
	 * @param simbolo El símbolo que insertamos.
	 */
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	/**
	 * Método que devuelve el valor de la variable tipo
	 * @return El tipo del símbolo.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Método que modifica el valor de la variable tipo
	 * @param tipo El tipo del símbolo.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Método que devuelve el valor de la variable tipoSimbolo
	 * @return Será un 1 en caso de ser una variable. Será un 2 en caso de ser un literal. Será un 3 en caso de ser un número.
	 */
	public int getTipoSimbolo() {
		return tipoSimbolo;
	}
	/**
	 * Método que modifica el valor de la variable tipoSimbolo
	 * @param tipoSimbolo Será un 1 en caso de ser una variable. Será un 2 en caso de ser un literal. Será un 3 en caso de ser un número.
	 */
	public void setTipoSimbolo(int tipoSimbolo) {
		this.tipoSimbolo = tipoSimbolo;
	}
}