package ui;

import model.Pedido;
import service.GestorPedidos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaListaPedidos extends JFrame {

    private final GestorPedidos gestor;
    private final DefaultTableModel modelo;

    public VentanaListaPedidos(GestorPedidos gestor) {
        this.gestor = gestor;

        setTitle("Lista de pedidos");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(700, 320);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{"ID", "Direccion", "Tipo", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JButton btnRefrescar = new JButton("Refrescar");
        JPanel abajo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        abajo.add(btnRefrescar);
        add(abajo, BorderLayout.SOUTH);

        btnRefrescar.addActionListener(e -> refrescar());

        Timer t = new Timer(600, e -> refrescar());
        t.start();
    }

    public void refrescar() {
        List<Pedido> pedidos = gestor.obtenerPedidosSnapshot();

        SwingUtilities.invokeLater(() -> {
            modelo.setRowCount(0);
            for (Pedido p : pedidos) {
                String tipo = p.getTipo() == null ? "" : p.getTipo().toString();
                String estado = p.getEstado() == null ? "" : p.getEstado().toString();
                modelo.addRow(new Object[]{p.getId(), p.getDireccionEntrega(), tipo, estado});
            }
        });
    }
}
