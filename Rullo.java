import javax.swing.*;
import java.awt.*;

enum Dificultad {
    FACIL, MEDIA, DIFICIL
}
<<<<<<< HEAD
=======

>>>>>>> 63385696cead72e4007ce5eb696650553c47fd23
class Tablero {
    private int filas;
    private int columnas;
    private Dificultad dificultad;

    public Tablero(int filas, int columnas, Dificultad dificultad) {
        this.filas = filas;
        this.columnas = columnas;
        this.dificultad = dificultad;
    }

    public void imprimir() {
        System.out.println("Tablero de " + filas + "x" + columnas + " con dificultad " + dificultad);
    }
}
class MenuRullo extends JFrame { 
    private JComboBox<String> tamañoMatriz;
    private JRadioButton facilBtn, medioBtn, dificilBtn;
    private JButton iniciarBtn;

    public MenuRullo() {  
        setTitle("Configuración de Rullo:");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));

<<<<<<< HEAD
=======
        
>>>>>>> 63385696cead72e4007ce5eb696650553c47fd23
        JPanel tamañoPanel = new JPanel();
        tamañoPanel.add(new JLabel("Tamaño del tablero:"));
        tamañoMatriz = new JComboBox<>(new String[] { "5x5", "6x6", "7x7", "8x8" });
        tamañoPanel.add(tamañoMatriz);
        add(tamañoPanel);

        JPanel dificultadPanel = new JPanel();
        dificultadPanel.add(new JLabel("Dificultad:"));
        facilBtn = new JRadioButton("2-4");
        medioBtn = new JRadioButton("1-9");
        dificilBtn = new JRadioButton("1-19");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(facilBtn);
        grupo.add(medioBtn);
        grupo.add(dificilBtn);
        dificultadPanel.add(facilBtn);
        dificultadPanel.add(medioBtn);
        dificultadPanel.add(dificilBtn);
        add(dificultadPanel);

        iniciarBtn = new JButton("Iniciar juego");
        iniciarBtn.addActionListener(e -> iniciarJuego());
        add(iniciarBtn);
    }

    private void iniciarJuego() {
<<<<<<< HEAD
        String tamaño = (String) tamañoMatriz.getSelectedItem();
        int filas = Integer.parseInt(tamaño.split("x")[0]);
        int columnas = Integer.parseInt(tamaño.split("x")[1]);

        Dificultad dificultad = facilBtn.isSelected() ? Dificultad.FACIL :
                                medioBtn.isSelected() ? Dificultad.MEDIA :
                                dificilBtn.isSelected() ? Dificultad.DIFICIL : null;
=======
        String tamaño = (String) tamañoMatriz.getSelectedItem(); 
        String tamañoSolo = tamaño.split("x")[0];              
        int tamañoInt = Integer.parseInt(tamañoSolo);            
        int tamañoReal = tamañoInt + 1;                          

        String rango = facilBtn.isSelected() ? "(2-4)" :
                    medioBtn.isSelected() ? "(1-9)" :
                    dificilBtn.isSelected() ? "(1-19)" : null;
>>>>>>> 63385696cead72e4007ce5eb696650553c47fd23

        if (dificultad == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una dificultad");
            return;
        }

<<<<<<< HEAD
        Tablero tablero = new Tablero(filas, columnas, dificultad);
        tablero.imprimir();
=======
        String archivo = tamañoSolo + rango + ".txt";            

        
        new TableroFrame(archivo, tamañoReal).setVisible(true);
        dispose();
>>>>>>> 63385696cead72e4007ce5eb696650553c47fd23
    }
    
}
public class Rullo {
    public static void main(String[] args) {
        System.out.println("Welcome to Rullo!");
        SwingUtilities.invokeLater(() -> new MenuRullo().setVisible(true));
    }
}

//ola