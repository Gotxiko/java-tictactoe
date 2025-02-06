import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {

    private static Integer numeroFilas;
    private static Integer numeroColumnas;
    private static Character[][] tablero;
    private static Integer numeroParejas;
    private static ArrayList<Character> baraja = new ArrayList<>();
    private static Integer primeraCarta;
    private static Integer segundaCarta;

    public static void main(String[] args) throws Exception {
        App game = new App();

        /** Preguntamos al usuario cuántas parejas se tienen que generar en la baraja */
        game.cuantasParejas();

        /** Preparamos la baraja */
        game.prepararBaraja();

        /** Comenzamos con el juego */
        game.eligeCartas();
    }

    /**
     * Preguntamos al usuario el número de parejas que se quieren usar.
     * 
     * @return
     * @throws Exception
     */
    public void cuantasParejas() throws Exception {
        System.out.println("Introduce el número de parejas");
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        numeroParejas = Integer.parseInt(r.readLine());
    }

    /**
     * Generamos la baraja en base al número de parejas.
     * 
     * @throws Exception
     */
    public void prepararBaraja() throws Exception {
        int i = 0;
        while (i < numeroParejas * 2) {
            baraja.add((char)('A' + i / 2));
            i++;
        }
        barajar();
        poblarTablero();
    }

    public void poblarTablero() throws Exception {

        /** 
         * Asumamos un número de columnas de 4 
         * En ese caso el número de filas tendrá que ser el tamaño de la baraja entre las columnas más 1 por si el número de parejas es impar.
        */
        numeroColumnas = 4;
        numeroFilas = (baraja.size() / numeroColumnas) + 1;

        tablero = new Character[numeroFilas][numeroColumnas];
        Integer counter = 0;
        
        for(int x = 0; x < numeroFilas; x++) {
            for(int y = 0; y < numeroColumnas; y++) {
                if(counter < baraja.size()) {
                    tablero[x][y] = baraja.get(counter);
                    counter++;
                }
            }
        }
    }

    /**
     * Barajamos la baraja (xD)
     * 
     * En base al tamaño de la baraja elegimos una posición aleatoria, la eliminamos y la añadimos al final.
     * Se podría hacer algún sort random raro, pero no nos vamos a comer la cabeza.
     * @throws Exception
     */
    public void barajar() throws Exception {
        int length = baraja.size();
        int j = 0;
        // Barajamos dos veces por los jajas
        while (j <= length * 2) {
            int posicionRandom = (int) (Math.random() * length);
            Character contenidoPosicion = baraja.get(posicionRandom);
            baraja.remove(posicionRandom);
            baraja.add(contenidoPosicion);
            j++;
        }
    }

    /**
     * Función de elegir cartas, compararlas y levantarlas.
     * 
     * @throws Exception
     */
    public void eligeCartas() throws Exception {
        // Enviamos size + 1 como si fuese "no se ha elegido carta"
        primeraCarta = preguntaCarta(baraja.size() + 1);
        segundaCarta = preguntaCarta(primeraCarta);

        /** Revisamos lo que se ha elegido */
        if(baraja.get(primeraCarta) == baraja.get(segundaCarta)) {
            baraja.set(primeraCarta, 'X');
            baraja.set(segundaCarta, 'X');
            System.out.println("ACERTASTE!");
        } else {
            System.out.println("Ups, sigue probando.");
        }

        boolean juegoCompletado = true;
        for (int i = 0; i < baraja.size(); i++) {
            if (baraja.get(i) != 'X') {
                juegoCompletado = false;
                break;
            }
        }
        if (juegoCompletado) {
            System.out.println("¡Felicidades! Has completado el juego.");
            System.exit(0);
        }

        eligeCartas();
    }

    /**
     * Imprimimos la baraja en un tablero de 4xN
     * 
     * @param carta
     */
    public void mostrarTablero(int carta) {
        Integer counter = 0;
        for(int i = 0; i < numeroFilas; i++) {
            for(int j = 0; j < numeroColumnas; j++) {
                if(counter < baraja.size()) {
                    if(counter == carta) {
                        System.out.print("[" + baraja.get(counter) + "] "); 
                    } else if (baraja.get(counter) == 'X') {
                        System.out.print("[X] ");
                    } else {
                        System.out.print("[" + counter + "] ");
                    }
                    counter++;
                }
            }
            System.out.println();
        }
    }

    /**
     * Preguntamos por la carta a seleccionar.
     * Comprobamos que la carta seleccionada es una carta válida.
     * - Tiene que ser un número positivo.
     * - Tiene que existir
     * - Tiene que estar dada la vuelta (no seleccionada)
     * 
     * Si la validación falla, preguntamos de nuevo llamando de nuevo preguntaCarta()
     *  
     * @param cartaElegida
     * @return Integer
     * @throws Exception
     */
    public Integer preguntaCarta(int cartaElegida) throws Exception {

        System.out.println("\n");
        mostrarTablero(cartaElegida);
        System.out.println("\nElige una carta:");
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int carta = Integer.parseInt(r.readLine());

        if (carta < 0) {
            System.out.println("El número de carta no puede ser negativo");
            return preguntaCarta(cartaElegida);
        }

        if(carta >= baraja.size()) {
            System.out.println("No existe esa carta");
            return preguntaCarta(cartaElegida);
        }

        if(baraja.get(carta) == 'X') {
            System.out.println("Esa carta ya se ha levantado");
            return preguntaCarta(cartaElegida);
        }

        if(cartaElegida == carta) {
            System.out.println("Ya has elegido esa carta, elige otra");
            return preguntaCarta(cartaElegida);
        }

        System.out.println("\nHas levantado la carta " + baraja.get(carta) + " en la posición " + carta);
        return carta;
    }

}
