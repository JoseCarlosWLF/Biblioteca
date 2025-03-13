/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
* @author: Diego
*/
package CapaPresentacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import CapaPersistencia.ConexionDB;

public class GestorPermisos extends JFrame {
    private JTextField campoBusqueda;
    private JTable tablaUsuarios;
    private JButton btnBuscar, btnGuardar;
    private JPanel panelPermisos;
    private DefaultTableModel modeloUsuarios;

    public GestorPermisos() {
        setTitle("Gestión de Permisos de Usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        campoBusqueda = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(new JLabel("Buscar: "));
        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(btnBuscar);

        String[] columnas = {"ID", "Nombre", "Apellidos", "Correo"};
        modeloUsuarios = new DefaultTableModel(columnas, 0);
        tablaUsuarios = new JTable(modeloUsuarios);
        JScrollPane scrollUsuarios = new JScrollPane(tablaUsuarios);
        llenarTablaUsuarios();

        panelPermisos = new JPanel();
        panelPermisos.setLayout(new BoxLayout(panelPermisos, BoxLayout.Y_AXIS));
        String[] funcionalidades = {"Acceso a Biblioteca", "Prestamo de Libros", "Consulta de Acervo", "Renovacion de Prestamos", "Reserva de Libros", "Edicion de Perfil"};
        for (String funcionalidad : funcionalidades) {
            JCheckBox checkBox = new JCheckBox(funcionalidad);
            panelPermisos.add(checkBox);
        }

        btnGuardar = new JButton("Guardar");

        JSplitPane panelDividido = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollUsuarios, panelPermisos);
        panelDividido.setDividerLocation(500);

        getContentPane().add(panelBusqueda, BorderLayout.NORTH);
        getContentPane().add(panelDividido, BorderLayout.CENTER);
        getContentPane().add(btnGuardar, BorderLayout.SOUTH);

        btnBuscar.addActionListener(e -> buscarUsuarios(campoBusqueda.getText()));

        tablaUsuarios.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && tablaUsuarios.getSelectedRow() >= 0) {
                cargarPermisosUsuarioSeleccionado();
            }
        });

        btnGuardar.addActionListener(e -> guardarPermisos());
    }

    private void llenarTablaUsuarios() {
        modeloUsuarios.setRowCount(0);
        Connection conexion = new ConexionDB().obtenerConexion();
        if (conexion != null) {
            try {
                String sql = "SELECT id_usuario, nombre, apellido_pat, apellido_mat, correo FROM Usuario";
                PreparedStatement pst = conexion.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    modeloUsuarios.addRow(new Object[]{rs.getInt("id_usuario"), rs.getString("nombre"), rs.getString(("apellido_pat")), rs.getString("correo")});
                }
                rs.close();
                pst.close();
                conexion.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al obtener datos de usuarios: " + e.getMessage());
            }
        }
    }

    private void buscarUsuarios(String consulta) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloUsuarios);
        tablaUsuarios.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + consulta));
    }

    private void cargarPermisosUsuarioSeleccionado() {
        // Lógica de carga de permisos desde BD
    }

    private void guardarPermisos() {
        // Lógica de guardar permisos en BD
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GestorPermisos().setVisible(true));
    }
}
