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
public class UserDAOImpl implements IDAO{
    ConexionDB conexion;
    
    public UserDAOImpl(){
        conexion = new ConexionDB(); 
        System.out.println("com.mycompany.usuariodao.UserDaoImpl.<init>()");
    }
    
     @Override
     
    public void create(ArrayList<String> datos) {//crear nuevo usuario con los datos que hay en el arreglo
             String sql = 
                     "INSERT INTO usuario (nombre, apellido_pat, apellido_mat, correo, telefono, contraseña) VALUES (?, ?, ?, ?, ?,?)";

        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql)) {

            
            // Asignar valores a los parámetros de la consulta
            
            consulta.setString(1, (String) datos.get(0));
            consulta.setString(2, (String) datos.get(1));
            consulta.setString(3, (String) datos.get(2));
            consulta.setString(4, (String) datos.get(3));
            consulta.setString(5, (String) datos.get(4));
            consulta.setString(6, (String) datos.get(6));

            // Ejecutar la consulta
            int rowsAffected = consulta.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario guardado exitosamente.");
            } else {
                System.out.println("No se pudo guardar el usuario.");
            }
 
       } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al guardar el usuario", ex);
        }

    }
    
    //@Override
    public void delete(int id) {
        String sql = 
                "DELETE FROM usuario WHERE id_usuario = ?";
    
        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql)) {

            consulta.setInt(1, id); // Establecer el ID del usuario a eliminar

            int rowsAffected = consulta.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuario eliminado exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el usuario.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al eliminar el usuario", ex);
        }
    }

    @Override
    public List<List<String>> consultar() {
        String sql = 
                "SELECT * FROM usuario";
        
        List<List<String>> listaUsuarios = new ArrayList<>();
        
        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql);
               ResultSet resultado = consulta.executeQuery()) {
            
       int columnas = resultado.getMetaData().getColumnCount();
               
        while (resultado.next()) { // Iterar sobre cada fila obtenida
                    List<String> fila = new ArrayList<>();
                    for (int i = 1; i <= columnas; i++) {
                        fila.add(resultado.getString(i)); // Agregar cada campo como String
                    }
                    listaUsuarios.add(fila);
                }

        System.out.println("Lista de usuarios cargada correctamente");

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al eliminar el usuario", ex);
        }
        return listaUsuarios;
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
    }

    @Override
    public void devolucion(Libro libro, Usuario usuario) {
        String sql = 
                "DELETE FROM prestamo WHERE id_libro = ? AND id_usuario = ?";
        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql)) {
    
                consulta.setInt(1, libro.getId());
                consulta.setInt(2, usuario.getId());
    
                int rowsAffected = consulta.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Devolucion exitosa");
                } else {
                    System.out.println("No se pudo devolver el libro");
                }
    
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al devolver el libro", ex);
            }
    }

    @Override
    public void multa(long diasDiferencia, Usuario usuario) {
        String sql = 
                "INSERT INTO multa (id_usuario, dias_diferencia) VALUES (?, ?)";
        try (Connection objConexion = conexion.obtenerConexion();
                PreparedStatement consulta = objConexion.prepareStatement(sql)) {
    
                consulta.setInt(1, usuario.getId());
                consulta.setLong(2, diasDiferencia);
    
                int rowsAffected = consulta.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Multa guardada");
                } else {
                    System.out.println("No se pudo guardar la multa");
                }
    
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al guardar la multa", ex);
            }
    }

    @Override
    public void consularLibroSucursal(Sucursal sucursal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consularLibroSucursal'");
    }

        
    }
    
    

 
    
 
    

    
   