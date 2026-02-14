package ui;

import model.Pedido;
import model.TipoPedido;
import service.GestorPedidos;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private final VentanaPrincipal principal;
    private final GestorPedidos gestor;

    private final JTextField txtId = new JTextField();
    private final JTextField txtDireccion = new JTextField();
    private final JComboBox<String> cboTipo = new JComboBox<>(new String[]{"Comida", "Encomienda", "Express"});

    public VentanaRegistroPedido(VentanaPrincipal principal, GestorPedidos gestor) {
        this.principal = principal;
        this.gestor = gestor;

        setTitle("Registrar pedido");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(420, 240);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        form.add(new JLabel("ID:"));
        form.add(txtId);

        form.add(new JLabel("Direccion:"));
        form.add(txtDireccion);

        form.add(new JLabel("Tipo:"));
        form.add(cboTipo);

        JButton btnGuardar = new JButton("Guardar");
        JPanel abajo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        abajo.add(btnGuardar);

        add(form, BorderLayout.CENTER);
        add(abajo, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> guardar());
    }

    private void guardar() {
        String idTxt = txtId.getText() == null ? "" : txtId.getText().trim();
        String dir = txtDireccion.getText() == null ? "" : txtDireccion.getText().trim();
        String tipoTxt = (String) cboTipo.getSelectedItem();

        if (idTxt.isEmpty() || dir.isEmpty() || tipoTxt == null || tipoTxt.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idTxt);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser numerico.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (gestor.existeId(id)) {
            JOptionPane.showMessageDialog(this, "Ya existe un pedido con ese ID.", "Validacion", JOptionPane.WARNING_MESSAGE);
            return;
        }

        TipoPedido tipo = TipoPedido.fromLabel(tipoTxt);
        Pedido p = new Pedido(id, dir, tipo);
        gestor.agregarPedido(p);

        JOptionPane.showMessageDialog(this, "Pedido registrado correctamente.");
        principal.notificarCambioDatos();
        dispose();
    }
}
