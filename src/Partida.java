public class Partida {

    // Atributos (privados por encapsulación, la clase controla su propio estado)
    private Tablero tablero;
    private Ficha turno;


    // Constructor de Partida
    public Partida(int dimension) {

        tablero = new Tablero(dimension); // Crea tablero
        turno = Ficha.X; // Establece que X empieza
    }
    /* 
       ¿Por qué Partida crea el tablero y no Main?

       Porque el tablero forma parte del estado interno de una partida.
       Main no necesita conocer cómo se crea ni cómo se gestiona internamente; solo crea una Partida.
    */

    // Método súper importante!!
    public void jugar(int fila, int columna) {

        if (!terminada() && tablero.jugar(turno, fila, columna)) {
         // Condición tablero.jugar(): Partida consulta a tablero si la ficha se puede colocar (comprueba si es su turno y si esa posición está disponible)

            turno = turno.siguiente();
        }
    }


    public boolean terminada() {

        return ganador() != null || tablero.estaLleno();
    }
    // Partida termina o porque alguien gana o porque el tablero está lleno


    public Ficha ganador() {

        for (Ficha ficha : Ficha.values()) {
            // Ficha.values() devuelve los valores del enum (X y O)

            if (tablero.gana(ficha)) {
                return ficha;
            }
        }

        return null; // Si alguien gana, se devuelve la ficha ganadora. si hay empate, no se devuelve ninguna
    }
    /*
    Podría haber usado 2 if, pero usar Ficha.values() evita escribir lógica específica 
    para cada valor del enum y hace que el código dependa menos de los valores concretos.
    */

    @Override
    public String toString() {

        StringBuilder resultado = new StringBuilder(); // Uso StringBuilder porque el texto se construye en varios pasos

        resultado.append(tablero); // Llama a tablero.toString, porque concatena al objeto dentro de una cadena
        
        /* He utilizado append() porque estoy construyendo la representación del tablero poco a poco.
           StringBuilder permite ir añadiendo texto al final de forma clara mediante append() y,
           al terminar,lo convierto a String con toString().
        */

        /* ¿Cómo sabe Java qué texto poner al hacer append(tablero)?

           Porque StringBuilder.append(Object) utiliza la representación textual del objeto,
           que en este caso está sobrescrita mediante Tablero.toString().
        */

        Ficha ganador = ganador(); // Guardar resultado para no llamar dos veces al método

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