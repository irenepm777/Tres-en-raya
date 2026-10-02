# Tres-en-raya

Ejercicio de repaso de **Despliegue de Aplicaciones Web 2026/27**.

El proyecto implementa el juego **Tres en Raya en Java**, separando la lógica del juego en distintas clases según las responsabilidades indicadas en el diagrama del ejercicio.


## Clases

### `Ficha`

Enumerado que representa las dos fichas posibles:

* `X`
* `O`

También determina cuál es el siguiente turno mediante:

```java
public Ficha siguiente()
```

---

### `Tablero`

Gestiona el estado del tablero y las reglas relacionadas con las posiciones.

Sus principales responsabilidades son:

* colocar una ficha en una posición válida
* impedir que se sobrescriban casillas ocupadas
* comprobar si el tablero está lleno
* detectar victorias horizontales
* detectar victorias verticales
* detectar victorias en ambas diagonales
* representar gráficamente el estado del tablero mediante `toString()`

La comprobación de las distintas líneas se centraliza en un método auxiliar para evitar repetir lógica.

---

### `Partida`

Controla el desarrollo de una partida.

Se encarga de:

* mantener el tablero
* controlar el turno actual
* solicitar una jugada al tablero
* cambiar de turno únicamente cuando la jugada es válida
* comprobar si la partida ha terminado
* determinar la ficha ganadora
* detectar un empate
* mostrar el estado actual de la partida

---

### `Main`

Contiene el punto de entrada del programa.

Utiliza `Scanner` para solicitar al usuario:

```text
Fila:
Columna:
```

y mantiene el juego activo hasta que existe un ganador o el tablero está lleno.

También valida que la entrada introducida sea numérica.

## Funcionamiento

El jugador `X` comienza la partida.

En cada turno se introducen las coordenadas de la casilla:

```text
Introduce la posición:
Fila: 0
Columna: 1
```

El tablero utiliza índices desde `0`, por lo que en una partida de 3×3 las posiciones válidas son:

```text
0  1  2
```

Ejemplo de representación:

```text
    0   1   2
  +---+---+---+
0 | X |   | O |
  +---+---+---+
1 |   | X |   |
  +---+---+---+
2 | O |   |   |
  +---+---+---+

Turno: X
```

Cuando termina la partida se muestra el ganador:

```text
Ganador: X
```

o, si se completa el tablero sin ninguna línea ganadora:

```text
Empate
```

## Reglas implementadas

Una ficha gana cuando completa una línea en cualquiera de estas direcciones:

* horizontal
* vertical
* diagonal directa
* diagonal indirecta

Una jugada no se realiza si:

* la fila o columna está fuera del tablero
* la casilla seleccionada ya está ocupada
* la partida ya ha terminado

El turno solo cambia después de una jugada válida.

## Ejecución

Compilar los archivos Java:

```bash
javac src/*.java
```

Ejecutar el programa:

```bash
java -cp src Main
```

También puede ejecutarse directamente desde un IDE como Eclipse.