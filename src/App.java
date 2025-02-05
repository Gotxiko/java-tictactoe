import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {

    private static Integer numeroParejas;
    private static ArrayList<Character> baraja = new ArrayList<>();
    private static Integer primeraCarta;
    private static Integer segundaCarta;

    public static void main(String[] args) throws Exception {
        numeroParejas = Integer.parseInt(cuantasParejas());
        generarBaraja();
        barajar();
        eligeCartas();
    }

    /**
     * Preguntamos al usuario el número de parejas que se quieren usar.
     * 
     * @return
     * @throws Exception
     */
    public static String cuantasParejas() throws Exception {
        System.out.println("Introduce el número de parejas");
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        return r.readLine();
    }

    /**
     * Generamos la baraja en base al número de parejas.
     * 
     * @throws Exception
     */
    public static void generarBaraja() throws Exception {
        int i = 0;
        while (i < numeroParejas * 2) {
            baraja.add((char)('A' + i / 2));
            i++;
        }
    }

    /**
     * Barajamos la baraja (xD)
     * 
     * En base al tamaño de la baraja elegimos una posición aleatoria, la eliminamos y la añadimos al final.
     * Se podría hacer algún sort random raro, pero no nos vamos a comer la cabeza.
     * @throws Exception
     */
    public static void barajar() throws Exception {
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
    public static void eligeCartas() throws Exception {
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

    public static void mostrarBaraja(int carta) {
        for (int i = 0; i < baraja.size(); i++) {
            if(i == carta) {
                System.out.print("[Seleccionada] "); 
            } else if(baraja.get(i) == 'X') { 
                System.out.print("[X] "); 
            } else {
                System.out.print("[" + i + "] ");
            }
        }
    }

    public static Integer preguntaCarta(int cartaElegida) throws Exception {
        System.out.println("Elige una carta:");
        mostrarBaraja(cartaElegida);
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int carta = Integer.parseInt(r.readLine());

        if (carta < 0) {
            System.out.println("El número de carta no puede ser negativo:");
            return preguntaCarta(cartaElegida);
        }

        if(carta >= baraja.size()) {
            System.out.println("No existe esa carta:");
            return preguntaCarta(cartaElegida);
        }

        if(baraja.get(carta) == 'X') {
            System.out.println("Esa carta ya se ha levantado:");
            return preguntaCarta(cartaElegida);
        }

        if(cartaElegida == carta) {
            System.out.println("Ya has elegido esa carta, elige otra:");
            return preguntaCarta(cartaElegida);
        }

        return carta;
    }

}
