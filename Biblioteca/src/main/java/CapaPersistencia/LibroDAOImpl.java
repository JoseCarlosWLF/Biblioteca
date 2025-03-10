/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPersistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import CapaNegocio.Libro;
import CapaNegocio.Sucursal;
import CapaNegocio.Usuario;

/**
 *
 * @author vsfs2
 */
public class LibroDAOImpl implements IDAO {
    
    ConexionDB conexion;
    
    public LibroDAOImpl(){
        conexion = new ConexionDB(); 
        System.out.println("com.mycompany.usuariodao.UserDaoImpl.<init>()");
    }

    @Override
    public void create(@SuppressWarnings("rawtypes") ArrayList datos) {
        String sql = 
                     "INSERT INTO libro (titulo, editorial, id_autor, anio, cantidad) VALUES (?, ?, ?,?,?)";

        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql)) {

   
            // Asignar valores a los parámetros de la consulta
            
            consulta.setString(1, (String) datos.get(0));
            consulta.setString(2, (String) datos.get(1));
            consulta.setInt(3,(int) datos.get(2));
            consulta.setInt(4,(int) datos.get(3));
            consulta.setInt(5,(int) datos.get(4));
      

            // Ejecutar la consulta
            int rowsAffected = consulta.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Libro guardado");
            } else {
                System.out.println("No se pudo guardar el libro");
            }
 
       } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al guardar el libro", ex);
        }

    }

    //@Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<List<String>> consultar() {
        String sql = 
                "SELECT * FROM libro";
        
        List<List<String>> listaLibros = new ArrayList<>();
        
        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql);
               ResultSet resultado = consulta.executeQuery()) {
            
       int columnas = resultado.getMetaData().getColumnCount();
               
        while (resultado.next()) { // Iterar sobre cada fila obtenida
                    List<String> fila = new ArrayList<>();
                    for (int i = 1; i <= columnas; i++) {
                        fila.add(resultado.getString(i)); // Agregar cada campo como String
                    }
                    listaLibros.add(fila);
                }

        System.out.println("Lista de libros cargada correctamente");

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al eliminar el libro", ex);
        }
        return listaLibros;
    }

    @Override
    public void prestamo(Libro libro, Usuario usuario) {
        String sql = 
                "INSERT INTO prestamo (id_libro, id_usuario, fecha_prestamo, fecha_devolucion) VALUES (?, ?, ?,?)";

        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql)) {

   
            // Asignar valores a los parámetros de la consulta
            
            consulta.setInt(1, libro.getId());
            consulta.setInt(2, usuario.getId());
            consulta.setString(3, "2021-10-10");
            consulta.setString(4, "2021-10-20");
      

            // Ejecutar la consulta
            int rowsAffected = consulta.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Prestamo guardado");
            } else {
                System.out.println("No se pudo guardar el prestamo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al guardar el prestamo", ex);
        }

        String sql2=
                "UPDATE libro SET cantidad = cantidad - 1 WHERE id = ?";
        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql2)) {
    
    
                // Asignar valores a los parámetros de la consulta
                
                consulta.setInt(1, libro.getId());
        
    
                // Ejecutar la consulta
                int rowsAffected = consulta.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Cantidad de libros actualizada");
                } else {
                    System.out.println("No se pudo actualizar la cantidad de libros");
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al actualizar la cantidad de libros", ex);
            }

    }

    @Override
    public void devolucion(Libro libro, Usuario usuario) {
        String sql = 
                "DELETE FROM prestamo WHERE id_libro = ? AND id_usuario = ?";

        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql)) {

   
            // Asignar valores a los parámetros de la consulta
            
            consulta.setInt(1, libro.getId());
            consulta.setInt(2, usuario.getId());
      

            // Ejecutar la consulta
            int rowsAffected = consulta.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Devolucion guardada");
            } else {
                System.out.println("No se pudo guardar la devolucion");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al guardar la devolucion", ex);

    }

}

    @Override
    public void consularLibroSucursal(Sucursal sucursal) {
        String sql = 
                "SELECT * FROM libro WHERE id_sucursal = ?";
        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql)) {

   
            // Asignar valores a los parámetros de la consulta
            
            consulta.setInt(1, sucursal.getId());
      

            // Ejecutar la consulta
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                System.out.println(resultado.getString("titulo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al consultar el libro", ex);
        }
    }

    @Override
    public void multa(long diasDiferencia, Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'multa'");
    }
}
    

