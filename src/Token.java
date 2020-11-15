/**
 * Cada uno de los tokens se guardará con esta estructura.
 * @author INF-3631
 * @version 11/11/2020
 */
public class Token {
	/**
	 * Será un:
	 * 0 en caso de ser una palabra reservada.
	 * 1 en caso de ser una variable.
	 * 2 en caso de ser un literal. 
	 * 3 en caso deser un número.
	 */
	private int tipoToken;
	/**
	 * Número de columna donde se encuentra.
	 */
	private int numColumna;
	/**
	 * Número de línea donde se encuentra.
	 */
	private int numLinea;
	/**
	 * Posición en la tabla de simbolos, en caso de ser palabra reservada
	 * pondrá posición -1.
	 */
	private int posicionSimbolo;
	/**
	 * El valor que tiene el token.
	 */
	private String token;
	/**
	 * El valor que tiene terminal o no terminal.
	 */
	private String tok;
		/**
	 * Método constructor que crea el objeto Token
	 * @param tipoToken Será un 0 en caso de ser una palabra reservada. Será un 1 en caso de ser una variable. Será un 2 en caso de ser un literal. Será un 3 en caso de ser un número.
	 * @param numColumna Número de columna donde se encuentra.
	 * @param numLinea Número de línea donde se encuentra.
	 * @param posicionSimbolo Posición en la tabla de simbolos, en caso de ser palabra reservada pondrá posición -1.
	 * @param token El valor que tiene el token.
	 * @param tok El valor que tiene terminal o no terminal.
	 */
	public Token(int tipoToken, int numColumna, int numLinea, int posicionSimbolo, String token, String tok) {
		this.tipoToken = tipoToken;
		this.numColumna = numColumna;
		this.numLinea = numLinea;
		this.posicionSimbolo = posicionSimbolo;
		this.token = token;
		this.tok = tok;
	}
	/**
	 * Método que devuelve el valor de numColumna
	 * @return Número de columna donde se encuentra el token.
	 */
	public int getNumColumna() {
		return numColumna;
	}
	/**
	 * Método que modifica el valor de numColumna
	 * @param numColumna Número de columna donde se encuentra el token.
	 */
	public void setNumColumna(int numColumna) {
		this.numColumna = numColumna;
	}
	//resto de métodos get y set

	/**
	 * Método que devuelve el valor de numLinea
	 * @return Número de línea donde se encuentra el token.
	 */
	public int getNumLinea() {
		return numLinea;
	}
	/**
	 * Método que modifica el valor de numLinea
	 * @param numLinea Número de línea donde se encuentra el token.
	 */
	public void setNumLinea(int numLinea) {
		this.numLinea = numLinea;
	}
	/**
	 * Método que devuelve el valor de posicionSimbolo
	 * @return Posición en la tabla de simbolos, en caso de ser palabra reservada pondrá posición -1.
	 */
	public int getPosicionSimbolo() {
		return posicionSimbolo;
	}
	/**
	 * Método que modifica el valor de posicionSimbolo
	 * @param posicionSimbolo Posición en la tabla de simbolos, en caso de ser palabra reservada pondrá posición -1.
	 */
	public void setPosicionSimbolo(int posicionSimbolo) {
		this.posicionSimbolo = posicionSimbolo;
	}
	/**
	 * Método que devuelve el valor de tipoToken
	 * @return Será un 0 en caso de ser una palabra reservada. Será un 1 en caso de ser una variable. Será un 2 en caso de ser un literal. Será un 3 en caso de ser un número.
	 */
	public int getTipoToken() {
		return tipoToken;
	}
	/**
	 * Método que modifica el valor de tipoToken
	 * @param tipoToken Será un 0 en caso de ser una palabra reservada. Será un 1 en caso de ser una variable. Será un 2 en caso de ser un literal. Será un 3 en caso de ser un número.
	 */
	public void setTipoToken(int tipoToken) {
		this.tipoToken = tipoToken;
	}
	/**
	 * Método que devuelve el valor de tok
	 * @return El valor que tiene terminal o no terminal
	 */
	public String getTok() {
		return tok;
	}
	/**
	 * Método que modifica el valor de tok
	 * @param tok El valor que tiene terminal o no terminal
	 */
	public void setTok(String tok) {
		this.tok = tok;
	}
	/**
	 * Método que devuelve el valor de token
	 * @return El valor que tiene el token.
	 */
	public String getToken() {
		return token;
	}
	/**
	 * Método que modifica el valor de token
	 * @param token El valor que tiene el token.
	 */
	public void setToken(String token) {
		this.token = token;
	}
}