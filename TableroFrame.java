import javax.swing.*;
import java.awt.*;
import java.io.*;

public class TableroFrame extends JFrame {

    // Constructor que recibe el nombre del archivo y el tamaño del tablero
    public TableroFrame(String archivo, int tamaño) {
        setTitle("Tablero " + (tamaño - 1) + "x" + (tamaño-1));
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Crear el panel del tablero
        JPanel panelTablero = new JPanel(new GridLayout(tamaño, tamaño, 5, 5));
        panelTablero.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        int[][] matriz = cargarTablero(archivo, tamaño);

        if (matriz != null) {
            for (int i = 0; i < tamaño; i++) {
                for (int j = 0; j < tamaño; j++) {
                    JLabel celda = new JLabel(String.valueOf(matriz[i][j]), SwingConstants.CENTER);
                    celda.setFont(new Font("Arial", Font.BOLD, 20));
                    celda.setOpaque(true);
                    celda.setBackground(Color.WHITE);
                    celda.setBorder(BorderFactory.createLineBorder(Color.PINK));
                    panelTablero.add(celda);
                }
            }
        }
        
        // Botón para volver al menu
        JButton volver = new JButton("Volver al menu");
        volver.addActionListener(_ -> {
            new MenuRullo().setVisible(true);
            dispose();
        });
        
        add(panelTablero, BorderLayout.CENTER);
        add(volver, BorderLayout.SOUTH);
    }

    // Metodo para cargar el tablero desde un archivo
    private int[][] cargarTablero(String archivo, int tamaño) {
        int[][] matriz = new int[tamaño][tamaño];
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int fila = 0;
            while ((linea = br.readLine()) != null && fila < tamaño) {
                String[] valores = linea.trim().split("\\s+");
                for (int col = 0; col < valores.length; col++) {
                    matriz[fila][col] = Integer.parseInt(valores[col]);
                }
                fila++;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el archivo:\n" + e.getMessage());
            return null;
        }
        return matriz;
    }
}