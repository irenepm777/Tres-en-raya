public class Partida {

    private Tablero tablero;
    private Ficha turno;


    public Partida(int dimension) {

        tablero = new Tablero(dimension);
        turno = Ficha.X;
    }


    public void jugar(int fila, int columna) {

        if (!terminada() && tablero.jugar(turno, fila, columna)) {

            turno = turno.siguiente();
        }
    }


    public boolean terminada() {

        return ganador() != null || tablero.estaLleno();
    }


    public Ficha ganador() {

        for (Ficha ficha : Ficha.values()) {

            if (tablero.gana(ficha)) {
                return ficha;
            }
        }

        return null;
    }


    @Override
    public String toString() {

        StringBuilder resultado = new StringBuilder();

        resultado.append(tablero);

        Ficha ganador = ganador();

        if (ganador != null) {

            resultado
                    .append("\nGanador: ")
                    .append(ganador);

        } else if (tablero.estaLleno()) {

            resultado.append("\nEmpate");

        } else {

            resultado
                    .append("\nTurno: ")
                    .append(turno);
        }

        return resultado.toString();
    }
}