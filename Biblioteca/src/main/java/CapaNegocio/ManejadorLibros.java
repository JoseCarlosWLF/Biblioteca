/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;
import CapaPersistencia.ConexionDB;
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
    
    
    public void validarMaterial(){
        // Valida si el codigo del libro ingresado existe en la base de datos
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