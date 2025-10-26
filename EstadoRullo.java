public class EstadoRullo {
    public boolean[][] activas;
    public int[][] valores;
    public int[] sumasFila;
    public int[] sumasColumna;

    public EstadoRullo(int[][] valores, int[] sumasFila, int[] sumasColumna) {
        this.valores = valores;
        this.sumasFila = sumasFila;
        this.sumasColumna = sumasColumna;
        this.activas = new boolean[valores.length][valores[0].length];
        for (int i = 0; i < activas.length; i++) {
            for (int j = 0; j < activas[0].length; j++) {
                activas[i][j] = true;
            }
        }
    }

    public boolean esSolucion() {
        for (int i = 0; i < valores.length; i++) {
            int suma = 0;
            for (int j = 0; j < valores[0].length; j++) {
                if (activas[i][j]) suma += valores[i][j];
            }
            if (suma != sumasFila[i]) return false;
        }
        for (int j = 0; j < valores[0].length; j++) {
            int suma = 0;
            for (int i = 0; i < valores.length; i++) {
                if (activas[i][j]) suma += valores[i][j];
            }
            if (suma != sumasColumna[j]) return false;
        }
        return true;
    }

    public EstadoRullo clonar() {
        EstadoRullo copia = new EstadoRullo(valores, sumasFila, sumasColumna);
        for (int i = 0; i < activas.length; i++) {
            copia.activas[i] = activas[i].clone();
        }
        return copia;
    }
}
