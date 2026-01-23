package model;

public class PedidoExpress extends Pedido {

    public PedidoExpress(int idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        int base = 10;
        if (distanciaKm > 5) {
            base += 5;
        }
        return base;
    }

    @Override
    public void asignarRepartidor() {
        asignarRepartidor("Chino Rios");
    }
}
