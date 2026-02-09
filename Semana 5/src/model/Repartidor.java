package model;

import service.ZonaDeCarga;

import java.util.concurrent.ThreadLocalRandom;

public class Repartidor implements Runnable {

    private final String nombre;
    private final ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {
        while (true) {
            Pedido pedido = zonaDeCarga.retirarPedido();

            if (pedido == null) {
                break;
            }

            System.out.println("\n[Repartidor - " + nombre + "] Retirando pedido #" + pedido.getId() + "...");
            System.out.println("[Repartidor - " + nombre + "] Estado: " + pedido.getEstado());
            System.out.println("[Repartidor - " + nombre + "] Entregando pedido #" + pedido.getId() + "...");

            simularEntrega();

            pedido.setEstado(EstadoPedido.ENTREGADO);
            System.out.println("[Repartidor - " + nombre + "] Estado: " + pedido.getEstado());
        }
    }

    private void simularEntrega() {
        try {
            int ms = ThreadLocalRandom.current().nextInt(1200, 3001);
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
