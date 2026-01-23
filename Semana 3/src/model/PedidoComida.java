package model;

public class PedidoComida extends Pedido {

    public PedidoComida(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        return Math.round((float) (15 + 2 * distanciaKm));
    }

    @Override
    public void asignarRepartidor() {
        asignarRepartidor("Chupete Suazo");
    }
}
