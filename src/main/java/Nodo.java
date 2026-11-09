import java.util.ArrayList;
import java.util.List;

public class Nodo extends Thread {
    private List<Integer> lista;

    public Nodo(List<Integer> lista) {
        this.lista = lista;
    }

    private void anadir(List<Integer> l, int desde, int hasta) {
        l.addAll(lista.subList(desde, hasta));
    }

    private void mezcla(List<Integer> l1, List<Integer> l2) {
        lista.clear();
        int i = 0;
        int j = 0;

        while (i < l1.size() && j < l2.size()) {
            if (l1.get(i) <= l2.get(j)) {
                lista.add(l1.get(i));
                i++;
            } else {
                lista.add(l2.get(j));
                j++;
            }
        }
        lista.addAll(l1.subList(i, l1.size()));
        lista.addAll(l2.subList(j, l2.size()));
    }

    public void run() {
        if (lista.size() < 2) {
            return;
        }

        int mitad = lista.size()/2;

        List<Integer> izq = new ArrayList<>();
        List<Integer> der = new ArrayList<>();

        anadir(izq, 0, mitad);
        anadir(der, mitad, lista.size());

        Nodo n1 = new Nodo(izq);
        Nodo n2 = new Nodo(der);

        n1.start();
        n2.start();

        try {
            n1.join();
            n2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mezcla(izq, der);
    }
}
