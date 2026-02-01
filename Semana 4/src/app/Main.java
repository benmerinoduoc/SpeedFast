package app;

import model.*;
import service.ControladorDeEnvios;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        System.out.println();

        ControladorDeEnvios controlador = new ControladorDeEnvios();

        String planetaGol = "Av. El Planeta Gol";
        String brasil = "Av. Brasil";
        String sarasota = "Av. Sarasota";

        Repartidor chupete = new Repartidor(
                "Chupete Suazo",
                List.of(
                        new PedidoComida(1, planetaGol, 4),
                        new PedidoEncomienda(2, planetaGol, 6)
                ),
                controlador
        );

        Repartidor mago = new Repartidor(
                "Mago Valdivia",
                List.of(
                        new PedidoComida(3, brasil, 3),
                        new PedidoExpress(4, brasil, 7)
                ),
                controlador
        );

        Repartidor chino = new Repartidor(
                "Chino Rios",
                List.of(
                        new PedidoEncomienda(5, sarasota, 8),
                        new PedidoExpress(6, sarasota, 2)
                ),
                controlador
        );

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

        controlador.verHistorial();
    }
}
