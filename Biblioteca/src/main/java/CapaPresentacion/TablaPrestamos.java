package Formularios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import CapaPersistencia.ConexionDB;

/**
 *
 * @author 03_06_2023
 */
public class TablaPrestamos extends JFrame {
    private JTextField campoBusqueda;
    private JTable tablaPrestamos;
    private JButton btnBuscar;
    private DefaultTableModel modeloPrestamos;

    public TablaPrestamos() {
        setTitle("Tabla de Préstamos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        inicializarComponentes();
        setVisible(true); // Muestra la ventana después de inicializar los componentes
    }

    private void inicializarComponentes() {
        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        campoBusqueda = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(new JLabel("Buscar: "));
        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(btnBuscar);

        // Tabla y modelo
        String[] columnas = {"ID préstamo", "ID usuario", "ID libro", "Fecha préstamo", "Fecha límite"};
        modeloPrestamos = new DefaultTableModel(columnas, 0);
        tablaPrestamos = new JTable(modeloPrestamos);
        JScrollPane scrollPane = new JScrollPane(tablaPrestamos); // Para desplazamiento

        // Llenar la tabla con datos de la base de datos
        llenarTablaPrestamos();

        // Evento del botón de búsqueda
        btnBuscar.addActionListener(e -> buscarPrestamos(campoBusqueda.getText()));

        // Diseño del JFrame
        setLayout(new BorderLayout());
        add(panelBusqueda, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void llenarTablaPrestamos() {
        modeloPrestamos.setRowCount(0);
        Connection conexion = new ConexionDB().obtenerConexion();

        if (conexion != null) {
            try {
                String sql = "SELECT id_prestamo, id_usuario, id_libro, fecha_prestamo, fecha_limite FROM Prestamo";
                PreparedStatement pst = conexion.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    modeloPrestamos.addRow(new Object[]{
                        rs.getInt("id_prestamo"),
                        rs.getInt("id_usuario"),
                        rs.getInt("id_libro"),
                        rs.getString("fecha_prestamo"),
                        rs.getString("fecha_limite")
                    });
                }

                rs.close();
                pst.close();
                conexion.close();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al obtener datos: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo conectar a la base de datos.");
        }
    }

    private void buscarPrestamos(String consulta) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modeloPrestamos);
        tablaPrestamos.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + consulta));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TablaPrestamos::new);
    }
}
