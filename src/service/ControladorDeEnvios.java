package service;

import interfaces.*;
import model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class ControladorDeEnvios implements Despachable, Cancelable, Rastreable {

    private List<String> historial = new ArrayList<>();

    @Override
    public void despachar(Pedido pedido) {
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
        pedido.marcarComoCancelado();
        System.out.println("→ Pedido cancelado exitosamente.\n");
    }

    @Override
    public void verHistorial() {
        System.out.println("Historial:");
        for (String h : historial) {
            System.out.println("-" + h + "\n");
        }
    }
}
