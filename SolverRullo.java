public class SolverRullo {

    public EstadoRullo resolver(EstadoRullo inicial) {
        int maxProfundidad = inicial.valores.length * inicial.valores[0].length;
        for (int limite = 0; limite <= maxProfundidad; limite++) {
            EstadoRullo resultado = dfsLimitado(inicial, 0, 0, limite);
            if (resultado != null) return resultado;
        }
        return null;
    }

    private EstadoRullo dfsLimitado(EstadoRullo estado, int fila, int col, int limite) {
        if (estado.esSolucion()) return estado;
        if (limite == 0 || fila >= estado.valores.length) return null;

        int siguienteFila = col == estado.valores[0].length - 1 ? fila + 1 : fila;
        int siguienteCol = col == estado.valores[0].length - 1 ? 0 : col + 1;

        EstadoRullo con = estado.clonar();
        con.activas[fila][col] = true;
        EstadoRullo resultado = dfsLimitado(con, siguienteFila, siguienteCol, limite - 1);
        if (resultado != null) return resultado;

        EstadoRullo sin = estado.clonar();
        sin.activas[fila][col] = false;
        return dfsLimitado(sin, siguienteFila, siguienteCol, limite - 1);
    }
}
