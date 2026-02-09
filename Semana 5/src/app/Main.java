package app;

import model.Pedido;
import model.Repartidor;
import service.ZonaDeCarga;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        ZonaDeCarga zona = new ZonaDeCarga();

        Repartidor chupete = new Repartidor("Chupete Suazo", zona);
        Repartidor mago = new Repartidor("Mago Valdivia", zona);
        Repartidor chino = new Repartidor("Chino Rios", zona);

        zona.agregarPedido(new Pedido(1, "Santiago Centro"));
        zona.agregarPedido(new Pedido(2, "Providencia"));
        zona.agregarPedido(new Pedido(3, "Ñuñoa"));
        zona.agregarPedido(new Pedido(4, "Recoleta"));
        zona.agregarPedido(new Pedido(5, "Las Condes"));

        System.out.println();

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(chupete);
        executor.execute(mago);
        executor.execute(chino);

        executor.shutdown();

        try {
            executor.awaitTermination(2, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        if (zona.estaVacia()) {
            System.out.println("\n[Zona de carga vacía]");
        }

        System.out.println("Todos los pedidos han sido entregados correctamente");
    }
}
