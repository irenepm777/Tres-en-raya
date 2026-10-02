public class Tablero {

    private Ficha[][] tablero;

    public Tablero(int dimension) {
        tablero = new Ficha[dimension][dimension]; // Obliga a la matriz a ser cuadrada
    }


    // Indica si la jugada se puede hacer (lo indicado en Partida.java)
    public boolean jugar(Ficha ficha, int fila, int columna) {

        if (!posicionValida(fila, columna)) {
            return false;
        }

        if (tablero[fila][columna] != null) {
            return false;
        }

        tablero[fila][columna] = ficha;
        // Si la jugada es apta, esa posición es ocupada por la ficha de turno

        return true;
    }


    private boolean posicionValida(int fila, int columna) {

        return fila >= 0
                && fila < tablero.length
                && columna >= 0
                && columna < tablero.length;
    }


    public boolean estaLleno() {

        for (Ficha[] fila : tablero) { // Primero recorre todas las filas del tablero

            for (Ficha ficha : fila) { // Después las fichas de esas filas

                if (ficha == null) {
                    return false;
                } // Si se encuentra algún null, significa que el tablero no está lleno
            }
        }

        return true;
    }


    public boolean gana(Ficha ficha) {

        return ganaHorizontal(ficha)
                || ganaVertical(ficha)
                || ganaDiagonalDirecta(ficha)
                || ganaDiagonalIndirecta(ficha);
    } // Si alguna de las formas se cumplen, gana() devuelve true


    protected boolean ganaHorizontal(Ficha ficha) {

        for (int fila = 0; fila < tablero.length; fila++) {

            if (comprobarLinea(ficha, fila, 0, 0, 1)) {
                return true;
            }
        }

        return false;
    }
    // Por cada fila llama comprobarLinea(ficha, filaInicial, columnaInicial, desplazamientoFila, desplazamientoColumna)
    // Los dos últimos números indican si ha habido un desplazamiento vertical u horizontal


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
    // Empieza en [0,0] y aumentan fila+1 y columna+1 hasta [2,2] en este caso


    protected boolean ganaDiagonalIndirecta(Ficha ficha) {

        return comprobarLinea(
                ficha,
                0,
                tablero.length - 1, // Para coger el valor máx de columnas en el tablero, se coge el número de columnas y se le resta 1 porque el tablero comienza en 0
                1,
                -1
        );
    }


    // En vez de repetir casi el mismo bucle cuatro veces, he creado un método que recorre una línea a partir de una posición inicial y una dirección
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

        resultado.append("   "); // Deja espacio para la numeración de filas

        for (int columna = 0; columna < tablero.length; columna++) {
            resultado.append(" ").append(columna).append("  ");
        } // Hace que salgan el "0 1 2" sobre el tablero, con espacios intercalados para separar los números

        resultado.append("\n  +");

        for (int columna = 0; columna < tablero.length; columna++) {
            resultado.append("---+");
        } //Genera los bordes del tablero

        resultado.append("\n");

        for (int fila = 0; fila < tablero.length; fila++) { // Recorremos filas tablero

            resultado.append(fila).append(" |");

            for (int columna = 0; columna < tablero[fila].length; columna++) { // Recorremos cada casilla

                resultado
                        .append(" ")
                        .append(valueOf(tablero[fila][columna])) // con esto "null" se transforma en " "
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