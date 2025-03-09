/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;

/**
 *
 * @author vsfs2
 */
public class ManejadorLibros {
    
    public void consultarCatalogo(){
        
    }
    
    public void validarMaterial(){
        // Valida si el codigo del libro ingresado existe en la base de datos
    }
     
    
    public void registrarPréstamo(){
        
    }
    
    public void registrarDevolución(){
        
    }
    
   public long generarMulta(long diasDiferencia){
        long multa = diasDiferencia*2; 
        return multa;
    }
    
}
