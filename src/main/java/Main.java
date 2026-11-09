import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        int n = random.nextInt(96) + 5;

        List<Integer> lista = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            lista.add(random.nextInt(100) + 1);
        }

        System.out.println("=== Ordenacion por mezcla concurrente ===");
        System.out.println("Lista aleatoria de " + n + " numeros entre 1 y 100, repartida en " + (2 * n - 1) + " hebras.");

        System.out.println("Lista original (" + n + " elementos):");
        System.out.println(lista);

        Nodo raiz = new Nodo(lista);
        raiz.start();
        raiz.join();

        System.out.println("Lista ordenada:");
        System.out.println(lista);
    }
}
