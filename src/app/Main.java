package app;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Pedido> pedidos = new ArrayList<>();

        pedidos.add(new PedidoComida(1, "Av. El Planeta Gol", 4));
        pedidos.add(new PedidoEncomienda(2, "Av. Brasil", 6));
        pedidos.add(new PedidoExpress(3, "Av. Sarasota", 7));

        String[] repartidores = {
                "Chupete Suazo",
                "Mago Valdivia",
                "Chino Rios"
        };

        int i = 0;
        for (Pedido p : pedidos) {
            p.mostrarResumen();
            System.out.println("Tiempo estimado de entrega: "
                    + p.calcularTiempoEntrega() + " minutos");
            p.asignarRepartidor(repartidores[i]);
            System.out.println();
            i++;
        }
    }
}
