/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPersistencia;

import CapaNegocio.Libro;
import CapaNegocio.Sucursal;
import CapaNegocio.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vsfs2
 */
public class PrestamoDAOImpl implements IDAO{
    
    private ConexionDB conexion;

    
    public PrestamoDAOImpl(){
        conexion = new ConexionDB(); 
        System.out.println("com.mycompany.usuariodao.UserDaoImpl.<init>()");
    }

  @Override
   public void create(ArrayList<Object> datos) {
       
       String sql = 
                "INSERT INTO prestamo (id_libro, id_usuario, fecha_prestamo, fecha_limite) VALUES (?, ?, ?,?)";

        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql)) {

   
            // Asignar valores a los parámetros de la consulta
            
            consulta.setInt(1,(int)datos.get(0));
            consulta.setInt(2, (int)datos.get(1));
            consulta.setString(3, datos.get(2).toString());
            consulta.setString(4, datos.get(3).toString());
      

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
                "UPDATE libro SET cantidad = cantidad - 1 WHERE id_libro= ?";
        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql2)) {
    
    
                // Asignar valores a los parámetros de la consulta
                
                consulta.setInt(1,(int)datos.get(0));
        
    
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

         //return "Préstamo registrado correctamente";
        
    }

   
   //Metodo que guarda todos los prestamos de la biblioteca en una lista de listas 
   @Override
    public List<List<String>> consultar() {
        
        String sql = 
                "SELECT * FROM prestamo";
        
        List<List<String>> listaPrestamo = new ArrayList<>();
        
        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql);
               ResultSet resultado = consulta.executeQuery()) {
            
            
            
       int columnas = resultado.getMetaData().getColumnCount();
               
        while (resultado.next()) { // Iterar sobre cada fila obtenida
                    List<String> fila = new ArrayList<>();
                    for (int i = 1; i <= columnas; i++) {
                        fila.add(resultado.getString(i)); // Agregar cada campo como String
                    }
                    listaPrestamo.add(fila);
                }

        System.out.println("Lista de usuarios cargada correctamente");
        return listaPrestamo;

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al eliminar el usuario", ex);
        }
        return null;
       
    }
    
    // Método que guarda todos los prestamos de un usuario en particular en una lista de listas. Cada lista es una fila de la tabla. 
    public List<List<String>> consultarUsuarioPrestamos(int id) {
        
    String sql = "SELECT * FROM prestamo WHERE id_usuario = ?";
    List<List<String>> listaPrestamo = new ArrayList<>();

    try (Connection objConexion = conexion.obtenerConexion();
         PreparedStatement consulta = objConexion.prepareStatement(sql)) {
        
        consulta.setInt(1, id); // Primero se asigna el parámetro

        try (ResultSet resultado = consulta.executeQuery()) { // Se ejecuta correctamente después de asignar el parámetro
            
            int columnas = resultado.getMetaData().getColumnCount();

            while (resultado.next()) { // Iterar sobre cada fila obtenida
                List<String> fila = new ArrayList<>();
                for (int i = 1; i <= columnas; i++) {
                    fila.add(resultado.getString(i)); // Agregar cada campo como String
                }
                listaPrestamo.add(fila);
            }
        }
        
        System.out.println("Lista de prestamos cargada correctamente");
        return listaPrestamo;

    } catch (SQLException ex) {
        Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al consultar los préstamos", ex);
    }

    return null;
}
    
   // Método que consulta la existencia de un prestamo  de un libro de un usuario
public boolean consultarPrestamoLibro(int id_libro, int id_usuario) {
    String sql = "SELECT 1 FROM prestamo WHERE id_libro = ? AND id_usuario = ? LIMIT 1"; // Solo devuelve un valor si existe
    
    try (Connection objConexion = conexion.obtenerConexion();
         PreparedStatement consulta = objConexion.prepareStatement(sql)) {
        
        consulta.setInt(1, id_libro); // Asignar el valor del parámetro
        consulta.setInt(2, id_usuario);
        try (ResultSet resultado = consulta.executeQuery()) {
            return resultado.next(); // Si hay al menos un resultado, el libro existe
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al consultar el libro", ex);
    }
    
    return false; // Si ocurre un error o no se encuentra el libro, devolver false
}

 
    
}
