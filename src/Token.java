/**
 * Cada uno de los tokens se guardar� con esta estructura.
 * @author INF-3631
 * @version 11/11/2020
 */
public class Token {
	/**
	 * Ser� un:
	 * 0 en caso de ser una palabra reservada.
	 * 1 en caso de ser una variable.
	 * 2 en caso de ser un literal. 
	 * 3 en caso deser un n�mero.
	 */
	private int tipoToken;
	/**
	 * N�mero de columna donde se encuentra.
	 */
	private int numColumna;
	/**
	 * N�mero de l�nea donde se encuentra.
	 */
	private int numLinea;
	/**
	 * Posici�n en la tabla de simbolos, en caso de ser palabra reservada
	 * pondr� posici�n -1.
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
	 * M�todo constructor que crea el objeto Token
	 * @param tipoToken Ser� un 0 en caso de ser una palabra reservada. Ser� un 1 en caso de ser una variable. Ser� un 2 en caso de ser un literal. Ser� un 3 en caso de ser un n�mero.
	 * @param numColumna N�mero de columna donde se encuentra.
	 * @param numLinea N�mero de l�nea donde se encuentra.
	 * @param posicionSimbolo Posici�n en la tabla de simbolos, en caso de ser palabra reservada pondr� posici�n -1.
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
	 * M�todo que devuelve el valor de numColumna
	 * @return N�mero de columna donde se encuentra el token.
	 */
	public int getNumColumna() {
		return numColumna;
	}
	/**
	 * M�todo que modifica el valor de numColumna
	 * @param numColumna N�mero de columna donde se encuentra el token.
	 */
	public void setNumColumna(int numColumna) {
		this.numColumna = numColumna;
	}
	//resto de m�todos get y set

	/**
	 * M�todo que devuelve el valor de numLinea
	 * @return N�mero de l�nea donde se encuentra el token.
	 */
	public int getNumLinea() {
		return numLinea;
	}
	/**
	 * M�todo que modifica el valor de numLinea
	 * @param numLinea N�mero de l�nea donde se encuentra el token.
	 */
	public void setNumLinea(int numLinea) {
		this.numLinea = numLinea;
	}
	/**
	 * M�todo que devuelve el valor de posicionSimbolo
	 * @return Posici�n en la tabla de simbolos, en caso de ser palabra reservada pondr� posici�n -1.
	 */
	public int getPosicionSimbolo() {
		return posicionSimbolo;
	}
	/**
	 * M�todo que modifica el valor de posicionSimbolo
	 * @param posicionSimbolo Posici�n en la tabla de simbolos, en caso de ser palabra reservada pondr� posici�n -1.
	 */
	public void setPosicionSimbolo(int posicionSimbolo) {
		this.posicionSimbolo = posicionSimbolo;
	}
	/**
	 * M�todo que devuelve el valor de tipoToken
	 * @return Ser� un 0 en caso de ser una palabra reservada. Ser� un 1 en caso de ser una variable. Ser� un 2 en caso de ser un literal. Ser� un 3 en caso de ser un n�mero.
	 */
	public int getTipoToken() {
		return tipoToken;
	}
	/**
	 * M�todo que modifica el valor de tipoToken
	 * @param tipoToken Ser� un 0 en caso de ser una palabra reservada. Ser� un 1 en caso de ser una variable. Ser� un 2 en caso de ser un literal. Ser� un 3 en caso de ser un n�mero.
	 */
	public void setTipoToken(int tipoToken) {
		this.tipoToken = tipoToken;
	}
	/**
	 * M�todo que devuelve el valor de tok
	 * @return El valor que tiene terminal o no terminal
	 */
	public String getTok() {
		return tok;
	}
	/**
	 * M�todo que modifica el valor de tok
	 * @param tok El valor que tiene terminal o no terminal
	 */
	public void setTok(String tok) {
		this.tok = tok;
	}
	/**
	 * M�todo que devuelve el valor de token
	 * @return El valor que tiene el token.
	 */
	public String getToken() {
		return token;
	}
	/**
	 * M�todo que modifica el valor de token
	 * @param token El valor que tiene el token.
	 */
	public void setToken(String token) {
		this.token = token;
	}
}