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
import java.time.LocalDate;

/**
 *
 * @author vsfs2
 */
public class LibroDAOImpl implements IDAO, LibroDAO{
    
    ConexionDB conexion;
    
    public LibroDAOImpl(){
        conexion = new ConexionDB(); 
        System.out.println("com.mycompany.usuariodao.UserDaoImpl.<init>()");
    }

    @Override
    public void create(ArrayList<Object> datos) {
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

    //@Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public boolean consultarLibroID(int id_libro) {
    String sql = "SELECT 1 FROM libro WHERE id_libro = ? LIMIT 1"; // Solo devuelve un valor si existe
    
    try (Connection objConexion = conexion.obtenerConexion();
         PreparedStatement consulta = objConexion.prepareStatement(sql)) {
        
        consulta.setInt(1, id_libro); // Asignar el valor del parámetro
        
        try (ResultSet resultado = consulta.executeQuery()) {
            return resultado.next(); // Si hay al menos un resultado, el libro existe
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al consultar el libro", ex);
    }
    
    return false; // Si ocurre un error o no se encuentra el libro, devolver false
}

    
    public void devolucion(int id_prestamo, int id_usuario, int id_libro) {
        String sql = 
                "DELETE FROM prestamo WHERE id_prestamo = ?";

        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql)) {

   
            // Asignar valores a los parámetros de la consulta
            
            consulta.setInt(1, id_prestamo);
            
      

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
        
     String sql2 = 
                     "INSERT INTO devolucion (id_usuario,fecha_devolucion) VALUES (?, ?)";

        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql2)) {

   
            // Asignar valores a los parámetros de la consulta
            
            consulta.setInt(1, id_usuario);
            consulta.setString(2, LocalDate.now().toString());

      

            // Ejecutar la consulta
            int rowsAffected = consulta.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Devolucion registrada");
            } else {
                System.out.println("No se pudo cargar la devolucion");
            }
 
       } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al guardar el libro", ex);
        }   
        
        
        String sql3=
                "UPDATE libro SET cantidad = cantidad + 1 WHERE id_libro= ?";
        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql3)) {
    
    
                // Asignar valores a los parámetros de la consulta
                
                consulta.setInt(1,id_libro);
        
    
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
    public Libro buscarLibro(String isbn) {
        String sql = "SELECT * FROM libro WHERE isbn = ? LIMIT 1"; // Solo devuelve un valor si existe
        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql)) {
            consulta.setString(1, isbn); // Asignar el valor del parámetro
            try (ResultSet resultado = consulta.executeQuery()) {
                if (resultado.next()) {
                    return new Libro(resultado.getInt("id_libro"), resultado.getString("titulo"), resultado.getString("editorial"), resultado.getInt("id_autor"), resultado.getString("anio"), resultado.getInt("cantidad"), resultado.getInt("id_sucursal"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al consultar el libro", ex);
        }
        return null; // Si ocurre un error o no se encuentra el libro, devolver null
    }

    @Override
    public ArrayList<Libro> listarLibrosSucursal(int idSucursal) {
        String sql = "SELECT * FROM libro WHERE id_sucursal = ?"; // Solo devuelve un valor si existe
        ArrayList<Libro> libros = new ArrayList<>();
        Libro libro = null;
        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql)) {
            consulta.setInt(1, idSucursal); // Asignar el valor del parámetro
            try (ResultSet resultado = consulta.executeQuery()) {
                while (resultado.next()) {
                    libro = new Libro(resultado.getInt("id_libro"), resultado.getString("titulo"), resultado.getString("editorial"), resultado.getInt("id_autor"), resultado.getString("anio"), resultado.getInt("cantidad"), resultado.getInt("id_sucursal"));
                    libros.add(libro);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al consultar el libro", ex);
        }
        return libros; // Si ocurre un error o no se encuentra el libro, devolver null
    }

}
    

