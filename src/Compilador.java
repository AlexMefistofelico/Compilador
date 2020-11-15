


/**
 * Clase que muestra el entorno gráfico principal
 * @author  INF-3631
 * @version 11/11/2020
 */
import java.io.*;
import java.lang.Object;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Compilador extends Frame {
	/**
	 * Método constructor que crea el objeto Compilador
	 */
	
	public Compilador() {
		initComponents();
	}

	// Declaraciones
	String fileName = "";
	File f;
	FileInputStream FInputs;
	DataInputStream Dinputs;
	String archivo;
	String fichero[];

	/**
	 * este metodo es llamado cuando inicia el constructor del formulario.
	 */
	private void initComponents() {
		menuBar2 = new MenuBar();
		menu2 = new Menu();
		menuItem3 = new MenuItem();
		menuItem4 = new MenuItem();
		menuItem1 = new MenuItem();
		menuItem2 = new MenuItem();
		menu7 = new Menu();
		menuItem11 = new MenuItem();
		textAreaCodigo = new TextArea();
		textAreaErrores = new TextArea();
		textAreaProducciones = new TextArea();
		textAreaPila = new TextArea();
		buttonAbrir = new JButton();
		buttonGuardar = new JButton();
		buttonCompilar = new JButton();
		textAreaTokens = new TextArea();
		textAreaSimbolos = new TextArea();
		label1 = new Label();
		label2 = new Label();
		label3 = new Label();
		label4 = new Label();
		label5 = new Label();
		labelListadoErrores = new Label();

		menuBar2.setFont(new Font("Arial", 0, 12));
		menu2.setName("archivo");
		menu2.setLabel("Archivo");
		menuItem3.setLabel("Abrir archivo");
		/*
		menuItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//DDA
			}
		});
		*/
		menuItem3.addActionListener(evt -> {
				menuItem3ActionPerformed(evt);
			}
		);
		
		menu2.add(menuItem3);
		menuItem4.setLabel("Guardar como...");
		menuItem4.addActionListener(evt -> {	
				menuItem4ActionPerformed(evt);
			}
		);

		menu2.add(menuItem4);
		menuItem1.setLabel("Guardar");
		menuItem1.addActionListener(evt -> {	
				menuItem1ActionPerformed(evt);
			}
		);

		menu2.add(menuItem1);
		menuItem2.setLabel("Salir");
		menuItem2.addActionListener(evt -> {	
				menuItem2ActionPerformed(evt);
			}
		);

		menu2.add(menuItem2);
		menuBar2.add(menu2);
		menu7.setLabel("Ayuda");
		menuItem11.setLabel("Acerca de ...");
		menuItem11.addActionListener(evt -> {	
				menuItem11ActionPerformed(evt);
			}
		);

		menu7.add(menuItem11);
		menuBar2.add(menu7);

		setLayout(null);//anular 

		setBackground(new Color( 166, 190, 215));
		setTitle("Práctica de Analizador Sintáctico Predictivo y No Recursivo");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				exitForm(evt);
			}
		});

		textAreaCodigo.setColumns(50);
		add(textAreaCodigo);
		textAreaCodigo.setBounds(10, 100, 390, 485);

		textAreaErrores.setColumns(50);
		add(textAreaErrores);
		textAreaErrores.setBounds(10, 600, 780, 150);
		textAreaErrores.setEditable(false);

		add(textAreaProducciones);
		textAreaProducciones.setBounds(410, 270, 380, 150);
		textAreaProducciones.setEditable(false);
		
		add(textAreaPila);
		textAreaPila.setBounds(410, 440, 380, 145);
		textAreaPila.setEditable(false);

		buttonAbrir.setFont(new Font("Dialog", 1, 12));
		buttonAbrir.setLabel("Abrir");
		//buttonAbrir.setForeground(new Color(0, 102, 102));
		buttonAbrir.addActionListener(evt -> {
			
			buttonAbrirActionPerformed(evt);
			}
		);

		add(buttonAbrir);
		buttonAbrir.setBounds(90, 70, 80, 25);

		buttonGuardar.setFont(new Font("Dialog", 1, 12));
		buttonGuardar.setLabel("Guardar");
		
		buttonGuardar.addActionListener(evt -> {	
				buttonGuardarActionPerformed(evt);
			}
		);

		add(buttonGuardar);
		buttonGuardar.setBounds(175, 70, 80, 25);

		buttonCompilar.setFont(new Font("Dialog", 1, 12));
		buttonCompilar.setLabel("Compilar");
		buttonCompilar.setForeground(new Color(0, 102, 102));
	
		buttonCompilar.addActionListener(evt -> {	
		
			}
		);

		add(buttonCompilar);
		buttonCompilar.setBounds(260, 70, 85, 25);

		add(textAreaTokens);
		textAreaTokens.setBounds(410, 100, 190, 150);
		textAreaTokens.setEditable(false);
		
		add(textAreaSimbolos);
		textAreaSimbolos.setBounds(610, 100, 180, 150);
		textAreaSimbolos.setEditable(false);

		label1.setFont(new Font("Dialog", 1, 12));
		label1.setForeground(Color.white);
		label1.setText("EDITOR");
		add(label1);
		label1.setBounds(10, 80, 70, 21);

		label2.setFont(new Font("Dialog", 1, 12));
		label2.setForeground(Color.white);
		label2.setText("Tabla de Tokens");
		add(label2);
		label2.setBounds(410, 80, 97, 21);

		label3.setFont(new Font("Dialog", 1, 12));
		label3.setForeground(Color.white);
		label3.setText("Tabla de S\u00edmbolos");
		add(label3);
		label3.setBounds(610, 80, 108, 21);

		label4.setFont(new Font("Dialog", 1, 12));
		label4.setForeground(Color.white);
		label4.setText("Producciones Utilizadas");
		add(label4);
		label4.setBounds(410, 250, 141, 21);

		label5.setFont(new Font("Dialog", 1, 12));
		label5.setForeground(Color.white);
		label5.setText("Estado de la pila");
		add(label5);
		label5.setBounds(410, 420, 96, 21);

		labelListadoErrores.setFont(new Font("Dialog", 1, 12));
		labelListadoErrores.setForeground(Color.white);
		labelListadoErrores.setText("Listado de errores");
		add(labelListadoErrores);
		labelListadoErrores.setBounds(10, 582, 109, 21);

		setMenuBar(menuBar2);
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		///TAMAÑO VENTANA DEL PROGRAMA
		setSize(new Dimension(815,770));
		
		///POSICION INICIAL VENTANA
		setLocation((screenSize.width - 815) / 2, (screenSize.height - 770) / 2);
	}

	private void menuItem2ActionPerformed(ActionEvent evt) {
		System.exit(0);
	}

	private void menuItem1ActionPerformed(ActionEvent evt) {
		try {
			String text = textAreaCodigo.getText();
			FileWriter fileWriter = new FileWriter(fileName);
			BufferedWriter br = new BufferedWriter(fileWriter);
			br.write(text);
			br.close();
		} catch (Exception ioe) {
		}
	}
	
	private void menuItem3ActionPerformed(ActionEvent evt) {
		
		
		JFileChooser fileChooser = null;
		// Si no existe el file chooser, crea uno
		if (fileChooser == null) {
			fileChooser = new JFileChooser();
		}
		// Valor que retorna al elegir una opcion en el file chooser
		int retVal = fileChooser.showOpenDialog(this);
		// Si se escogio Ok, (o abrir)
		if (retVal == fileChooser.APPROVE_OPTION) {
			// El path absoluto del archivo elegido
			fileName = fileChooser.getSelectedFile().getAbsolutePath();
			try {
				f = new File(fileName);
				FInputs = new FileInputStream(f);
				Dinputs = new DataInputStream(FInputs);
				String aux = Dinputs.readLine();
				textAreaCodigo.setText("");
				while (aux != null) {
					textAreaCodigo.append(aux + "\n");
					aux = Dinputs.readLine();
				}
				Dinputs.close();
				FInputs.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "El archivo " + archivo
						+ " no se puede abrir", "Error",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

	}
	
	private void menuItem4ActionPerformed(ActionEvent evt) {
		
		
		
		
		JFileChooser fileChooser = null;
		fileName = "";
		// Si no existe, crea uno
		if (fileChooser == null)
			fileChooser = new JFileChooser();
		// Abre un diálogo de guardado de archivos
		int retVal = fileChooser.showSaveDialog(this);
		if (retVal == fileChooser.APPROVE_OPTION) {
			fileName = fileChooser.getSelectedFile().getAbsolutePath();
			try {
				String text = textAreaCodigo.getText();
				java.io.FileWriter fileWriter = new java.io.FileWriter(fileName);
				java.io.BufferedWriter br = new java.io.BufferedWriter(
						fileWriter);
				br.write(text);
				br.close();
			} catch (Exception ioe) {
			}
		}
	}
	
	private void menuItem11ActionPerformed(ActionEvent evt) {
		
		new Acerca(new Frame(), true).show();
	}
	
	private void buttonGuardarActionPerformed(ActionEvent evt) {
		
		try {
			String text = textAreaCodigo.getText();
			java.io.FileWriter fileWriter = new java.io.FileWriter(fileName);
			java.io.BufferedWriter br = new java.io.BufferedWriter(fileWriter);
			br.write(text);
			br.close();
		} catch (Exception ioe) {
		}

	}
	
	private void buttonAbrirActionPerformed(ActionEvent evt) {
		
		JFileChooser fileChooser = null;
		// Si no existe el file chooser, crea uno
		if (fileChooser == null) {
			fileChooser = new JFileChooser();
		}
		// Valor que retorna al elegir una opcion en el file chooser
		int retVal = fileChooser.showOpenDialog(this);
		// Si se escogio Ok, (o abrir)
		if (retVal == fileChooser.APPROVE_OPTION) {
			// El path absoluto del archivo elegido
			fileName = fileChooser.getSelectedFile().getAbsolutePath();
			try {
				f = new File(fileName);
				FInputs = new FileInputStream(f);
				Dinputs = new DataInputStream(FInputs);
				String aux = Dinputs.readLine();
				textAreaCodigo.setText("");
				while (aux != null) {
					textAreaCodigo.append(aux + "\n");
					aux = Dinputs.readLine();
				}
				Dinputs.close();
				FInputs.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "El archivo " + archivo
						+ " no se puede abrir", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}
	
	ArrayList error;
	ArrayList token;
	ArrayList simbolos;
	ArrayList gramatica;
	ArrayList pila;

	private void buttonCompilarActionPerformed(ActionEvent evt) {
	
	}
	/** salir de la palicacion*/
	private void exitForm(WindowEvent evt) {
		System.exit(0);
	}
	
	/**
	 * Método principal
	 * @param args Argumentos
	 */
	public static void main(String args[]) {
		new Compilador().show();
	}


	private MenuBar menuBar2;

	private Menu menu2;

	private MenuItem menuItem3;

	private MenuItem menuItem4;

	private MenuItem menuItem1;

	private MenuItem menuItem2;

	private Menu menu7;

	private MenuItem menuItem11;

	private TextArea textAreaCodigo;

	private TextArea textAreaErrores;

	private TextArea textAreaProducciones;

	private TextArea textAreaPila;

	private JButton buttonAbrir;

	private JButton buttonGuardar;

	private JButton buttonCompilar;

	private TextArea textAreaTokens;

	private TextArea textAreaSimbolos;

	private Label label1;

	private Label label2;

	private Label label3;

	private Label label4;

	private Label label5;

	private Label labelListadoErrores;
	
}
