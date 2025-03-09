/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaNegocio;

/**
 *
 * @author vsfs2
 */
public class ManejadorUsuario {
    
    public void guardarUsuario(){  
    }; 
    
    public void verificarUsuario(){
    }; //Verifica si el usuario existe en la base de datos 
    
    public void verificarContraseña(){ 
    //Verifica que la contraseña ingresada coincide con la registrada en la cuenta del usuario   
    }
    
    public void validarPermisosPrestamo(){ 
    /*Verifica si el usuario cumple los siguientes criterios:  
        1. No tiene préstamos vencidos
        2. No ha acumulado la cantidad máxima de prestamos (5)
    */
    }
    public void validarPermisosDevolucion(){ 
        /*Verifica que el usuario tenga préstamos activos (devoluciones pendientes)*/
    }
    
    public void validarDevolucion(){
        /*Verifica que el libro que quiere devvolver el usuario, sea uno de los que tiene prestados*/
    }
    
      
}
