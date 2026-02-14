package model;

public class Pedido {

    private int id;
    private String direccionEntrega;
    private EstadoPedido estado;
    private TipoPedido tipo;

    public Pedido(int id, String direccionEntrega) {
        this(id, direccionEntrega, TipoPedido.COMIDA);
    }

    public Pedido(int id, String direccionEntrega, TipoPedido tipo) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.tipo = tipo == null ? TipoPedido.COMIDA : tipo;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public int getId() {
        return id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public TipoPedido getTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public void setEstado(EstadoPedido nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void setTipo(TipoPedido tipo) {
        this.tipo = tipo == null ? TipoPedido.COMIDA : tipo;
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " - Destino: " + direccionEntrega + " - Tipo: " + tipo + " - Estado: " + estado;
    }
}
