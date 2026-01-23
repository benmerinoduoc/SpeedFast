package app;

import model.*;
import service.ControladorDeEnvios;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        ControladorDeEnvios controlador = new ControladorDeEnvios();

        List<Pedido> pedidos = new ArrayList<>();

        pedidos.add(new PedidoComida(1, "Av. El Planeta Gol", 4));
        pedidos.add(new PedidoEncomienda(2, "Av. Brasil", 6));
        pedidos.add(new PedidoExpress(3, "Av. Sarasota", 7));

        for (Pedido p : pedidos) {

            //Pedido Express se cancela
            if (p instanceof PedidoExpress) {
                System.out.println("Cancelando Pedido Express #"
                        + String.format("%03d", p.getIdPedido()) + "...");
                controlador.cancelar(p);
                continue;
            }

            System.out.println();
            p.mostrarResumen();
            p.asignarRepartidor();
            System.out.println("Tiempo estimado: "
                    + p.calcularTiempoEntrega() + " minutos");
            controlador.despachar(p);
        }

        controlador.verHistorial();
    }
}
