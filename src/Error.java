
/**
 * Cada uno de los errores se guardará con esta estructura.
 * @author INF-3631
 * @version 11/11/2020
 */
public class Error {
	/**
	 * Número de columna donde se encuentra.
	 */
	private int numColumna;

	/**
	 * Número de línea donde se encuentra.
	 */
	private int numLinea;

	/**
	 * El mensaje de error que tenemos.
	 */
	private String error;

	/**
	 * Método constructor que crea el objeto Error
	 * @param numColumna Número de columna donde se encuentra.
	 * @param numLinea Número de línea donde se encuentra.
	 * @param error El mensaje de error que tenemos.
	 */
	public Error(int numColumna, int numLinea, String error) {
		this.numColumna = numColumna;
		this.numLinea = numLinea;
		this.error = error;
	}

	/**
	 * Método que devuelve el valor de la variable error
	 * @return El mensaje de error que tenemos.
	 */
	public String getError() {
		return error;
	}

	/**
	 * Método que modifica el valor de la variable error
	 * @param error El mensaje de error que tenemos.
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Método que devuelve el valor de la variable numColumna
	 * @return Número de columna donde se encuentra.
	 */
	public int getNumColumna() {
		return numColumna;
	}
	/**
	 * Método que modifica el valor de la variable numColumna
	 * @param numColumna Número de columna donde se encuentra.
	 */
	public void setNumColumna(int numColumna) {
		this.numColumna = numColumna;
	}
	/**
	 * Método que devuelve el valor de la variable numLinea
	 * @return Número de línea donde se encuentra.
	 */
	public int getNumLinea() {
		return numLinea;
	}

	/**
	 * Método que modifica el valor de la variable numLinea
	 * @param numLinea Número de línea donde se encuentra.
	 */
	public void setNumLinea(int numLinea) {
		this.numLinea = numLinea;
	}
}