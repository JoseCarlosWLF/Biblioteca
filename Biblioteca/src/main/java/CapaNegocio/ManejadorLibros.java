/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;
import CapaPersistencia.ConexionDB;
import CapaPersistencia.LibroDAOImpl;
import CapaPersistencia.PrestamoDAOImpl;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 *
 * @author vsfs2
 */
public class ManejadorLibros {
    
    
    public String registrarDevolucion(int id_prestamo, int id_usuario, int id_libro ){
       LibroDAOImpl l = new LibroDAOImpl();
       PrestamoDAOImpl p = new PrestamoDAOImpl();
       
       if ((l.consultarLibroID(id_libro))){  // Validar si el libro existe 
           if(p.consultarPrestamoLibro(id_libro,id_usuario)){ // Verifica si el libro está en los prestamos del usuario  
               l.devolucion(id_prestamo,id_usuario,id_libro); // Genera la devolución en la base de datos  
               return "DEVOLUCIÓN EXITOSA";
           }else{
            return "EL LIBRO NO ESTÁ EN LOS PRESTAMOS DEL USUARIO";
    
            }
        } else{
           return "LIBRO NO EXISTE";  
       }
    }
     
    
        public String registrarPrestamo(ArrayList<Object> datos) {
        try {
            LocalDate fechaPrestamo = LocalDate.now();
            LocalDate fechaLimite = fechaPrestamo.plus(2, ChronoUnit.WEEKS); // Fecha límite es dos semanas después
            
            datos.add(fechaPrestamo);
            datos.add(fechaLimite);
            
            PrestamoDAOImpl registro = new PrestamoDAOImpl();
            registro.create(datos);
            return "Registro Completado correctamente"; // Retornamos la confirmación directamente
            
        } catch (Exception e) {  // Captura una excepción más general
            return "Error al registrar el préstamo: " + e.getMessage();
        }
    }
        
        
     
   public long generarMulta(long diasDiferencia){
        long multa = diasDiferencia*2; 
        return multa;
    }
   
} 