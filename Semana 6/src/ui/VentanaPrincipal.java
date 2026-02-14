package ui;

import service.GestorPedidos;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private final GestorPedidos gestor;
    private VentanaListaPedidos ventanaLista;

    public VentanaPrincipal() {
        this.gestor = new GestorPedidos();

        setTitle("SpeedFast - Gestion de Entregas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(520, 220);
        setLocationRelativeTo(null);

        JPanel centro = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnRegistrar = new JButton("Registrar pedido");
        JButton btnListar = new JButton("Listar pedidos");
        JButton btnIniciar = new JButton("Asignar repartidor / Iniciar entrega");

        centro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        centro.add(btnRegistrar);
        centro.add(btnListar);
        centro.add(btnIniciar);

        add(centro, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> {
            VentanaRegistroPedido v = new VentanaRegistroPedido(this, gestor);
            v.setVisible(true);
        });

        btnListar.addActionListener(e -> {
            if (ventanaLista == null || !ventanaLista.isDisplayable()) {
                ventanaLista = new VentanaListaPedidos(gestor);
            }
            ventanaLista.setVisible(true);
            ventanaLista.refrescar();
        });

        btnIniciar.addActionListener(e -> {
            gestor.iniciarEntregas();
            if (ventanaLista != null && ventanaLista.isDisplayable()) {
                ventanaLista.refrescar();
            }
            JOptionPane.showMessageDialog(this, "Entregas iniciadas.");
        });
    }

    public void notificarCambioDatos() {
        if (ventanaLista != null && ventanaLista.isDisplayable()) {
            ventanaLista.refrescar();
        }
    }
}
