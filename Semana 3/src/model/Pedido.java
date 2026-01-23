package model;

public abstract class Pedido {

    protected int idPedido;
    protected String direccionEntrega;
    protected double distanciaKm;
    protected String repartidor;

    private boolean despachado;
    private boolean cancelado;

    public Pedido(int idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getRepartidor() {
        return repartidor;
    }

    public void mostrarResumen() {
        System.out.println("[" + getClass().getSimpleName().replace("Pedido", "Pedido ") + "]");
        System.out.println("Pedido #" + String.format("%03d", idPedido));
        System.out.println("Dirección: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
    }


    public void asignarRepartidor(String nombre) {
        this.repartidor = nombre;
        System.out.println("Repartidor asignado: " + nombre);
    }

    public abstract void asignarRepartidor();

    public abstract int calcularTiempoEntrega();

    public void marcarComoDespachado() {
        this.despachado = true;
    }

    public void marcarComoCancelado() {
        this.cancelado = true;
    }

    public boolean isDespachado() {
        return despachado;
    }

    public boolean isCancelado() {
        return cancelado;
    }
}
