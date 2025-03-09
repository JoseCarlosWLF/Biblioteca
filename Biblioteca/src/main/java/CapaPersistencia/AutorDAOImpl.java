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

/**
 *
 * @author vsfs2
 */
public class AutorDAOImpl implements IDAO{
    
    private ConexionDB conexion;
    
    public AutorDAOImpl(){
        conexion = new ConexionDB(); 
        System.out.println("com.mycompany.usuariodao.UserDaoImpl.<init>()");
    }

    @Override
    public void create(ArrayList datos) {
        
        
       String sql = 
                     "INSERT INTO autor (nombre,apellido, nacionalidad) VALUES (?, ?,?)";

        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql)) {

   
            // Asignar valores a los parámetros de la consulta
            
            consulta.setString(1, (String) datos.get(0));
            consulta.setString(2, (String) datos.get(1));
            consulta.setString(3, (String) datos.get(2));


            // Ejecutar la consulta
            int rowsAffected = consulta.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Autor guardado exitosamente.");
            } else {
                System.out.println("No se pudo guardar el autor.");
            }
 
       } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al guardar el autor", ex);
        }
    }

    @Override
    public List<List<String>> consultar() {
    String sql = 
                "SELECT * FROM autor";
        
        List<List<String>> listaAutor = new ArrayList<>();
        
        try (Connection objConexion = conexion.obtenerConexion();
             PreparedStatement consulta = objConexion.prepareStatement(sql);
               ResultSet resultado = consulta.executeQuery()) {
            
       int columnas = resultado.getMetaData().getColumnCount();
               
        while (resultado.next()) { // Iterar sobre cada fila obtenida
                    List<String> fila = new ArrayList<>();
                    for (int i = 1; i <= columnas; i++) {
                        fila.add(resultado.getString(i)); // Agregar cada campo como String
                    }
                    listaAutor.add(fila);
                }

        System.out.println("Lista de usuarios cargada correctamente");

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, "Error al eliminar el usuario", ex);
        }
        return listaAutor;
    }
    
}
