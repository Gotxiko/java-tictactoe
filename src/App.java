import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {

    private static Integer numeroParejas;
    private static ArrayList<Character> baraja = new ArrayList<>();

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

        /** Seleccionamos la primera carta */
        System.out.println("Elige una carta:");
        for (int i = 0; i < baraja.size(); i++) {
            if(baraja.get(i) == 'X') { 
                System.out.print("[X] "); 
            } else {
                System.out.print("[" + i + "] ");
            }
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int primeraCarta = Integer.parseInt(r.readLine());

        /** Seleccionamos la segunda carta */
        System.out.println("Elige otra carta:");
        for (int i = 0; i < baraja.size(); i++) {
            if(i == primeraCarta) { 
                System.out.print("[Seleccionada] "); 
            } else if(baraja.get(i) == 'X') { 
                System.out.print("[X] "); 
            } else {
                System.out.print("[" + i + "] ");
            }
        }
        BufferedReader r2 = new BufferedReader(new InputStreamReader(System.in));
        int segundaCarta = Integer.parseInt(r2.readLine());

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
}
