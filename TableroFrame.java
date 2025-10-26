import javax.swing.*;
import java.awt.*;
import java.io.*;

public class TableroFrame extends JFrame {

    public TableroFrame(String archivo, int tamaño) {
        setTitle("Tablero " + tamaño + "x" + tamaño);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelTablero = new JPanel(new GridLayout(tamaño, tamaño, 5, 5));
        panelTablero.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        int[][] matriz = cargarTablero(archivo, tamaño);

        if (matriz != null) {
            int n = tamaño - 1;
            int[][] valores = new int[n][n];
            int[] sumasFila = new int[n];
            int[] sumasColumna = new int[n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    valores[i][j] = matriz[i][j];
                }
                sumasFila[i] = matriz[i][n];
            }
            for (int j = 0; j < n; j++) {
                sumasColumna[j] = matriz[n][j];
            }

            EstadoRullo estadoInicial = new EstadoRullo(valores, sumasFila, sumasColumna);
            SolverRullo solver = new SolverRullo();
            EstadoRullo solucion = solver.resolver(estadoInicial);

            if (solucion != null) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        JLabel celda = new JLabel(String.valueOf(valores[i][j]), SwingConstants.CENTER);
                        celda.setFont(new Font("Arial", Font.BOLD, 20));
                        celda.setOpaque(true);
                        celda.setBackground(solucion.activas[i][j] ? Color.GREEN : Color.LIGHT_GRAY);
                        celda.setBorder(BorderFactory.createLineBorder(Color.PINK));
                        panelTablero.add(celda);
                    }
                    JLabel sumaFila = new JLabel(String.valueOf(sumasFila[i]), SwingConstants.CENTER);
                    sumaFila.setFont(new Font("Arial", Font.BOLD, 20));
                    sumaFila.setBackground(Color.YELLOW);
                    sumaFila.setOpaque(true);
                    panelTablero.add(sumaFila);
                }

                for (int j = 0; j < n; j++) {
                    JLabel sumaCol = new JLabel(String.valueOf(sumasColumna[j]), SwingConstants.CENTER);
                    sumaCol.setFont(new Font("Arial", Font.BOLD, 20));
                    sumaCol.setBackground(Color.YELLOW);
                    sumaCol.setOpaque(true);
                    panelTablero.add(sumaCol);
                }

                panelTablero.add(new JLabel(""));
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró solución para este tablero.");
            }
        }

        JButton volver = new JButton("Volver al menú");
        volver.addActionListener(e -> {
            new MenuRullo().setVisible(true);
            dispose();
        });

        add(panelTablero, BorderLayout.CENTER);
        add(volver, BorderLayout.SOUTH);
    }

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
