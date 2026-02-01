package model;

import service.ControladorDeEnvios;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Repartidor implements Runnable {

    private final String nombre;
    private final List<Pedido> pedidos;
    private final ControladorDeEnvios controlador;

    public Repartidor(String nombre, List<Pedido> pedidos, ControladorDeEnvios controlador) {
        this.nombre = nombre;
        this.pedidos = pedidos;
        this.controlador = controlador;
    }

    @Override
    public void run() {
        for (Pedido pedido : pedidos) {

            pedido.asignarRepartidor(nombre);

            System.out.println("[Repartidor: " + nombre + "] Entregando "
                    + pedido.getClass().getSimpleName()
                    + " #" + String.format("%03d", pedido.getIdPedido())
                    + " en " + pedido.getDireccionEntrega() + "...");

            if (pedido.debeCancelarse()) {
                controlador.cancelar(pedido);
                continue;
            }

            simularEntrega(pedido);

            System.out.println("[Repartidor: " + nombre + "] Pedido #"
                    + String.format("%03d", pedido.getIdPedido())
                    + " entregado.");

            controlador.despachar(pedido);
        }
    }

    private void simularEntrega(Pedido pedido) {
        try {
            int tiempoEstimadoMin = pedido.calcularTiempoEntrega();
            int extra = ThreadLocalRandom.current().nextInt(200, 701);
            int base = tiempoEstimadoMin * 50;
            Thread.sleep(base + extra);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
