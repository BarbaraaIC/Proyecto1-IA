import javax.swing.*;
import java.awt.*;

// Enum para dificultad
enum Dificultad {
    FACIL, MEDIA, DIFICIL
}

// Clase simulada para Tablero
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

// Clase del menú
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

        // Panel tamaño
        JPanel tamañoPanel = new JPanel();
        tamañoPanel.add(new JLabel("Tamaño del tablero:"));
        tamañoMatriz = new JComboBox<>(new String[] { "5x5", "6x6", "7x7", "8x8" });
        tamañoPanel.add(tamañoMatriz);
        add(tamañoPanel);

        // Panel dificultad
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

        // Botón
        iniciarBtn = new JButton("Iniciar juego");
        iniciarBtn.addActionListener(e -> iniciarJuego());
        add(iniciarBtn);
    }

    private void iniciarJuego() {
        String tamaño = (String) tamañoMatriz.getSelectedItem(); // Ej: "5x5"
        String tamañoSolo = tamaño.split("x")[0];                // Extrae "5"
        int tamañoInt = Integer.parseInt(tamañoSolo);            // Convierte a entero
        int tamañoReal = tamañoInt + 1;                          // Incluye sumas

        String rango = facilBtn.isSelected() ? "(2-4)" :
                       medioBtn.isSelected() ? "(1-9)" :
                       dificilBtn.isSelected() ? "(1-19)" : null;

        if (rango == null) {
            JOptionPane.showMessageDialog(this, "Selecciona una dificultad");
            return;
        }

        String archivo = tamañoSolo + rango + ".txt";            // Ej: "5(1-9).txt"

        // Abrir la ventana del tablero con tamaño real
        new TableroFrame(archivo, tamañoReal).setVisible(true);
        dispose();
    }
}

// Clase principal
public class Rullo {
    public static void main(String[] args) {
        System.out.println("Welcome to Rullo!");
        SwingUtilities.invokeLater(() -> new MenuRullo().setVisible(true));
    }
}
