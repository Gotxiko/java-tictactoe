import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {

    private static Integer numeroParejas;
    private static ArrayList<Integer> baraja;

    public static void main(String[] args) throws Exception {
        numeroParejas = Integer.parseInt(askUser());
        generarBaraja();
        System.out.println(baraja);
    }

    public static String askUser() throws Exception {
        System.out.println("Introduce el número de parejas");
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        return r.readLine();
    }

    public static void generarBaraja() throws Exception {
        int i = 0;
        while (i < numeroParejas * 2) {
            baraja.add(i / 2);
            i++;
        }
    }
}
