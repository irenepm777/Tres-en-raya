public class Tablero {

    private Ficha[][] tablero;

    public Tablero(int dimension) {
        tablero = new Ficha[dimension][dimension];
    }


    public boolean jugar(Ficha ficha, int fila, int columna) {

        if (!posicionValida(fila, columna)) {
            return false;
        }

        if (tablero[fila][columna] != null) {
            return false;
        }

        tablero[fila][columna] = ficha;

        return true;
    }


    private boolean posicionValida(int fila, int columna) {

        return fila >= 0
                && fila < tablero.length
                && columna >= 0
                && columna < tablero.length;
    }


    public boolean estaLleno() {

        for (Ficha[] fila : tablero) {

            for (Ficha ficha : fila) {

                if (ficha == null) {
                    return false;
                }
            }
        }

        return true;
    }


    public boolean gana(Ficha ficha) {

        return ganaHorizontal(ficha)
                || ganaVertical(ficha)
                || ganaDiagonalDirecta(ficha)
                || ganaDiagonalIndirecta(ficha);
    }


    protected boolean ganaHorizontal(Ficha ficha) {

        for (int fila = 0; fila < tablero.length; fila++) {

            if (comprobarLinea(ficha, fila, 0, 0, 1)) {
                return true;
            }
        }

        return false;
    }


    protected boolean ganaVertical(Ficha ficha) {

        for (int columna = 0; columna < tablero.length; columna++) {

            if (comprobarLinea(ficha, 0, columna, 1, 0)) {
                return true;
            }
        }

        return false;
    }


    protected boolean ganaDiagonalDirecta(Ficha ficha) {

        return comprobarLinea(
                ficha,
                0,
                0,
                1,
                1
        );
    }


    protected boolean ganaDiagonalIndirecta(Ficha ficha) {

        return comprobarLinea(
                ficha,
                0,
                tablero.length - 1,
                1,
                -1
        );
    }


    private boolean comprobarLinea(
            Ficha ficha,
            int filaInicial,
            int columnaInicial,
            int desplazamientoFila,
            int desplazamientoColumna) {

        for (int i = 0; i < tablero.length; i++) {

            int fila = filaInicial + i * desplazamientoFila;
            int columna = columnaInicial + i * desplazamientoColumna;

            if (tablero[fila][columna] != ficha) {
                return false;
            }
        }

        return true;
    }


    private Object valueOf(Ficha ficha) {
        return ficha == null ? " " : ficha;
    }


    @Override
    public String toString() {

        StringBuilder resultado = new StringBuilder();

        resultado.append("   ");

        for (int columna = 0; columna < tablero.length; columna++) {
            resultado.append(" ").append(columna).append("  ");
        }

        resultado.append("\n  +");

        for (int columna = 0; columna < tablero.length; columna++) {
            resultado.append("---+");
        }

        resultado.append("\n");

        for (int fila = 0; fila < tablero.length; fila++) {

            resultado.append(fila).append(" |");

            for (int columna = 0; columna < tablero[fila].length; columna++) {

                resultado
                        .append(" ")
                        .append(valueOf(tablero[fila][columna]))
                        .append(" |");
            }

            resultado.append("\n  +");

            for (int columna = 0; columna < tablero.length; columna++) {
                resultado.append("---+");
            }

            resultado.append("\n");
        }

        return resultado.toString();
    }
}