import java.io.*;
import java.util.*;


/**
 * Clase donde insertar los métodos de lectura/escritura de archivos
 * @author INF-3631
 * @version 11/11/2020
 */
public class Archivos{
	/** 
	 * Metodo public que nos servirá para leer el archivo a compilar, de tal 
	 * manera que introducimos linea a linea en un vector.
	 * @param archivo la ruta del archivo donde está el código fuente a compilar
	 * @return el código fuente en un vector de tipo String (cada casilla es una fila del código)
	 */
	public String[] leerArchivo(String archivo) {
		Vector cadenas = new Vector();
        String fichero[];
        String aux;

        try{
	        File f= new File(archivo);
			FileInputStream FInputs= new FileInputStream(f);
			DataInputStream DInputs= new DataInputStream(FInputs);
			           
			aux = DInputs.readLine();
			
			do{ 
				// Introducimos las lineas en un vector DInputsamico.
				cadenas.addElement(new String(aux));
				aux = DInputs.readLine();
			}while(aux!=null);
			DInputs.close();
			FInputs.close();
		}catch(IOException e){ 
			       
		}             
		fichero = new String[cadenas.size()];
		// Introducimos las lineas del archivo en un vector.
		for(int i=0; i < cadenas.size(); i++)
			fichero[i] = (String)cadenas.elementAt(i);
		return fichero;
	
	}
}