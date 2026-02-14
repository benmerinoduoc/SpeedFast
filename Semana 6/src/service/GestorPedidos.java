package service;

import model.Pedido;
import model.Repartidor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GestorPedidos {

    private final List<Pedido> pedidos = Collections.synchronizedList(new ArrayList<>());
    private final ZonaDeCarga zona = new ZonaDeCarga();
    private ExecutorService executor;
    private boolean entregasIniciadas;

    public void agregarPedido(Pedido p) {
        if (p == null) return;
        pedidos.add(p);
    }

    public List<Pedido> obtenerPedidosSnapshot() {
        synchronized (pedidos) {
            return new ArrayList<>(pedidos);
        }
    }

    public boolean existeId(int id) {
        synchronized (pedidos) {
            for (Pedido p : pedidos) {
                if (p.getId() == id) return true;
            }
        }
        return false;
    }

    public void iniciarEntregas() {
        if (entregasIniciadas) return;

        List<Pedido> snapshot = obtenerPedidosSnapshot();
        for (Pedido p : snapshot) {
            if (p.getEstado() != null && p.getEstado().name().equals("PENDIENTE")) {
                zona.agregarPedido(p);
            }
        }

        entregasIniciadas = true;
        executor = Executors.newFixedThreadPool(3);
        executor.execute(new Repartidor("Chupete Suazo", zona));
        executor.execute(new Repartidor("Mago Valdivia", zona));
        executor.execute(new Repartidor("Chino Rios", zona));

        executor.shutdown();
    }
}
