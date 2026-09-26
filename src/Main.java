import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        Partida partida = new Partida(3);

        while (!partida.terminada()) {

            System.out.println(partida);

            System.out.println("\nIntroduce la posición:");

            int fila = leerNumero(teclado, "Fila: ");
            int columna = leerNumero(teclado, "Columna: ");

            partida.jugar(fila, columna);

            System.out.println();
        }

        System.out.println(partida);

        teclado.close();
    }


    private static int leerNumero(Scanner teclado, String mensaje) {

        while (true) {

            System.out.print(mensaje);

            if (teclado.hasNextInt()) {
                return teclado.nextInt();
            }

            System.out.println("Debes introducir un número.");

            teclado.next();
        }
    }
}