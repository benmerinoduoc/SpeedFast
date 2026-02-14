package service;

import model.EstadoPedido;
import model.Pedido;

import java.util.LinkedList;
import java.util.Queue;

public class ZonaDeCarga {

    private final Queue<Pedido> cola = new LinkedList<>();

    public ZonaDeCarga() {
        System.out.println("[Zona de carga inicializada]\n");
    }

    public synchronized void agregarPedido(Pedido p) {
        if (p == null) return;

        p.setEstado(EstadoPedido.PENDIENTE);
        cola.add(p);

        System.out.println("Pedido #" + p.getId() + " agregado. Destino: " + p.getDireccionEntrega());
    }

    public synchronized Pedido retirarPedido() {
        Pedido p = cola.poll();
        if (p == null) {
            return null;
        }
        p.setEstado(EstadoPedido.EN_REPARTO);
        return p;
    }

    public synchronized boolean estaVacia() {
        return cola.isEmpty();
    }
}
