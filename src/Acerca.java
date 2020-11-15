import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.*
;

public class Acerca extends Dialog {

    public Acerca(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    private void initComponents() {
        label2 = new Label();
        label3 = new Label();
        label1 = new Label();
        label4 = new Label();
        
        setLayout(null);
        
        //COLOR FONDO DIALOGO
        setBackground(new Color(166, 190, 215));

        setTitle("Acerca de ...");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                closeDialog(evt);
            }
        });
        
       
        label2.setFont(new java.awt.Font("Dialog", 1, 12));
        label2.setForeground(java.awt.Color.white);
        label2.setText("                  Auxiliatura  -  INF-3631:  Diseño de Compiladores");
        add(label2);
        label2.setBounds(0, 70, 400, 20);
        
        label3.setFont(new java.awt.Font("Dialog", 1, 12));
        label3.setForeground(java.awt.Color.white);
        label3.setText("                                              Fines Academimos ");
        add(label3);
        label3.setBounds(0, 90, 400, 20);
        
        label1.setFont(new java.awt.Font("Dialog", 1, 12));
        label1.setText("Programa realizado para:");
        add(label1);
        label1.setBounds(120, 40, 144, 21);
        
        label4.setFont(new java.awt.Font("Dialog", 1, 12));
        label4.setText("   Ingenieria Informática");
        add(label4);
        label4.setBounds(120, 120, 283, 21);
        
        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(new java.awt.Dimension(400, 150));
        setLocation((screenSize.width-400)/2,(screenSize.height-150)/2);
    }
    
    private void closeDialog(java.awt.event.WindowEvent evt) {
        setVisible(false);
        dispose();
    }
    
    public static void main(String args[]) {
        new Acerca(new Frame(), true).show();
    }

    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label1;
    private java.awt.Label label4;
 
}
