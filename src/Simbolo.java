/**
 * Cada uno de los tokens se guardar�n con esta estructura.
 * @author INF-3631
 * @version 11/11/2020
 */

public class Simbolo {
	/**
	 * Ser� un 1 en caso de ser una variable. Ser� un 2 en caso de ser un
	 * literal. Ser� un 3 en caso de ser un n�mero.
	 */
	private int tipoSimbolo;
	/**
	 * N�mero de columna donde se encuentra el s�mbolo.
	 */
	private int numColumna;
	/**
	 * N�mero de l�nea donde se encuentra el s�mbolo.
	 */
	private int numLinea;
	/**
	 * El s�mbolo que insertamos.
	 */
	private String simbolo;
	/**
	 * El tipo del s�mbolo.
	 */
	private String tipo;
	/**
	 * Variable booleana que informa si el s�mbolo est� o no declarado.
	 */
	private boolean declarado;
	/**
	 * Variable booleana que informa si el s�mbolo est� o no inicializado.
	 */
	private boolean inicializado;
	/**
	 * M�todo constructor que crea el objeto S�mbolo
	 * @param tipoSimbolo Ser� un 1 en caso de ser una variable. Ser� un 2 en caso de ser un literal. Ser� un 3 en caso de ser un n�mero.
	 * @param numColumna N�mero de columna donde se encuentra el s�mbolo.
	 * @param numLinea N�mero de l�nea donde se encuentra el s�mbolo.
	 * @param simbolo El s�mbolo que insertamos.
	 * @param tipo El tipo del s�mbolo.
	 * @param declarado Variable booleana que informa si el s�mbolo est� o no declarado.
	 * @param inicializado Variable booleana que informa si el s�mbolo est� o no inicializado.
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
	 * M�todo que devuelve el valor de la variable declarado
	 * @return Variable booleana que informa si el s�mbolo est� o no declarado.
	 */
	public boolean isDeclarado() {
		return declarado;
	}
	/**
	 * M�todo que modifica el valor de la variable declarado
	 * @param declarado Variable booleana que informa si el s�mbolo est� o no declarado.
	 */
	public void setDeclarado(boolean declarado) {
		this.declarado = declarado;
	}
	/**
	 * M�todo que devuelve el valor de la variable inicializado
	 * @return Variable booleana que informa si el s�mbolo est� o no inicializado.
	 */
	public boolean isInicializado() {
		return inicializado;
	}
	/**
	 * M�todo que modifica el valor de la variable inicializado
	 * @param inicializado Variable booleana que informa si el s�mbolo est� o no inicializado.
	 */
	public void setInicializado(boolean inicializado) {
		this.inicializado = inicializado;
	}
	/**
	 * M�todo que devuelve el valor de la variable numColumna
	 * @return N�mero de columna donde se encuentra el s�mbolo.
	 */
	public int getNumColumna() {
		return numColumna;
	}
	/**
	 * M�todo que modifica el valor de la variable numColumna
	 * @param numColumna N�mero de columna donde se encuentra el s�mbolo.
	 */
	public void setNumColumna(int numColumna) {
		this.numColumna = numColumna;
	}
	//resto de m�todos get y set
	/**
	 * M�todo que devuelve el valor de la variable numLinea
	 * @return N�mero de l�nea donde se encuentra el s�mbolo.
	 */
	public int getNumLinea() {
		return numLinea;
	}
	/**
	 * M�todo que modifica el valor de la variable numLinea
	 * @param numLinea N�mero de l�nea donde se encuentra el s�mbolo.
	 */
	public void setNumLinea(int numLinea) {
		this.numLinea = numLinea;
	}
	/**
	 * M�todo que devuelve el valor de la variable simbolo
	 * @return El s�mbolo que insertamos.
	 */
	public String getSimbolo() {
		return simbolo;
	}
	/**
	 * M�todo que modifica el valor de la variable simbolo
	 * @param simbolo El s�mbolo que insertamos.
	 */
	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

	/**
	 * M�todo que devuelve el valor de la variable tipo
	 * @return El tipo del s�mbolo.
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * M�todo que modifica el valor de la variable tipo
	 * @param tipo El tipo del s�mbolo.
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * M�todo que devuelve el valor de la variable tipoSimbolo
	 * @return Ser� un 1 en caso de ser una variable. Ser� un 2 en caso de ser un literal. Ser� un 3 en caso de ser un n�mero.
	 */
	public int getTipoSimbolo() {
		return tipoSimbolo;
	}
	/**
	 * M�todo que modifica el valor de la variable tipoSimbolo
	 * @param tipoSimbolo Ser� un 1 en caso de ser una variable. Ser� un 2 en caso de ser un literal. Ser� un 3 en caso de ser un n�mero.
	 */
	public void setTipoSimbolo(int tipoSimbolo) {
		this.tipoSimbolo = tipoSimbolo;
	}
}