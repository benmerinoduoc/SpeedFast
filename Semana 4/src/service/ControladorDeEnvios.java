package service;

import interfaces.Cancelable;
import interfaces.Despachable;
import interfaces.Rastreable;
import model.Pedido;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControladorDeEnvios implements Despachable, Cancelable, Rastreable {

    private final List<String> historial = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void despachar(Pedido pedido) {
        if (pedido == null) {
            return;
        }

        if (pedido.isCancelado()) {
            return;
        }

        if (pedido.getRepartidor() == null || pedido.getRepartidor().isBlank()) {
            return;
        }

        pedido.marcarComoDespachado();

        historial.add(
                pedido.getClass().getSimpleName()
                        + " #" + String.format("%03d", pedido.getIdPedido())
                        + " – Entregado por " + pedido.getRepartidor()
        );

        System.out.println("Pedido despachado correctamente.\n");
    }

    @Override
    public void cancelar(Pedido pedido) {
        if (pedido == null) {
            return;
        }

        if (pedido.isDespachado()) {
            return;
        }

        pedido.marcarComoCancelado();

        historial.add(
                pedido.getClass().getSimpleName()
                        + " #" + String.format("%03d", pedido.getIdPedido())
                        + " – Cancelado"
        );

        System.out.println("→ Pedido cancelado.\n");
    }

    @Override
    public void verHistorial() {
        System.out.println("\nHistorial");
        synchronized (historial) {
            for (String h : historial) {
                System.out.println("- " + h);
            }
        }
        System.out.println();
    }
}
