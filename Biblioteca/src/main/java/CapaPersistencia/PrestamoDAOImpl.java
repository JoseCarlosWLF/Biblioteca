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

  
   public String prestamo(ArrayList<Object> datos) {
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

         return "Préstamo registrado correctamente";
    }

    
   @Override
    public List<List<String>> consultar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     @Override
    public void multa(long diasDiferencia, Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void consularLibroSucursal(Sucursal sucursal) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  
 

    @Override
    public void devolucion(Libro libro, Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void prestamo(Libro libro, Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void create(ArrayList<Object> datos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
    
}
