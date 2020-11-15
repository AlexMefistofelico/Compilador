
/**
 * Cada uno de los errores se guardar� con esta estructura.
 * @author INF-3631
 * @version 11/11/2020
 */
public class Error {
	/**
	 * N�mero de columna donde se encuentra.
	 */
	private int numColumna;

	/**
	 * N�mero de l�nea donde se encuentra.
	 */
	private int numLinea;

	/**
	 * El mensaje de error que tenemos.
	 */
	private String error;

	/**
	 * M�todo constructor que crea el objeto Error
	 * @param numColumna N�mero de columna donde se encuentra.
	 * @param numLinea N�mero de l�nea donde se encuentra.
	 * @param error El mensaje de error que tenemos.
	 */
	public Error(int numColumna, int numLinea, String error) {
		this.numColumna = numColumna;
		this.numLinea = numLinea;
		this.error = error;
	}

	/**
	 * M�todo que devuelve el valor de la variable error
	 * @return El mensaje de error que tenemos.
	 */
	public String getError() {
		return error;
	}

	/**
	 * M�todo que modifica el valor de la variable error
	 * @param error El mensaje de error que tenemos.
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * M�todo que devuelve el valor de la variable numColumna
	 * @return N�mero de columna donde se encuentra.
	 */
	public int getNumColumna() {
		return numColumna;
	}
	/**
	 * M�todo que modifica el valor de la variable numColumna
	 * @param numColumna N�mero de columna donde se encuentra.
	 */
	public void setNumColumna(int numColumna) {
		this.numColumna = numColumna;
	}
	/**
	 * M�todo que devuelve el valor de la variable numLinea
	 * @return N�mero de l�nea donde se encuentra.
	 */
	public int getNumLinea() {
		return numLinea;
	}

	/**
	 * M�todo que modifica el valor de la variable numLinea
	 * @param numLinea N�mero de l�nea donde se encuentra.
	 */
	public void setNumLinea(int numLinea) {
		this.numLinea = numLinea;
	}
}