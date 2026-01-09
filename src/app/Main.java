package app;

import model.PedidoComida;
import model.PedidoEncomienda;
import model.PedidoExpress;

public class Main {
    public static void main(String[] args) {

        PedidoComida pedido1 = new PedidoComida(1, "El Planeta Gol");
        PedidoEncomienda pedido2 = new PedidoEncomienda(2, "Avenida Brasil");
        PedidoExpress pedido3 = new PedidoExpress(3, "Miami, Florida");

        pedido1.asignarRepartidor();
        pedido1.asignarRepartidor("Chupete Suazo");

        System.out.println();

        pedido2.asignarRepartidor();
        pedido2.asignarRepartidor("Mago Valdivia");

        System.out.println();

        pedido3.asignarRepartidor();
        pedido3.asignarRepartidor("Chino Rios");
    }
}
