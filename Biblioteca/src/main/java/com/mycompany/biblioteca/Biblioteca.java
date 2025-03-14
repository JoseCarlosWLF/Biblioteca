package com.mycompany.biblioteca;
import CapaNegocio.ManejadorLibros;
import CapaPersistencia.LibroDAOImpl;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author vsfs2
 */
public class Biblioteca {

    public static void main(String[] args) {
        
        ManejadorLibros manejador  = new ManejadorLibros();
        
        ArrayList<Object> datos1 = new ArrayList<>();
        ArrayList<Object> datos2 = new ArrayList<>();       
        
        datos1.add(2);
        datos1.add(1);
        
        datos2.add(2);
        datos2.add(2);
        
        System.out.println(manejador.registrarPrestamo(datos1));
        System.out.println(manejador.registrarPrestamo(datos2));
        
        
        
        
         //System.out.println(p.consultar());
         
        
       LibroDAOImpl p = new LibroDAOImpl();
       p.devolucion(0,1,2);
        
        
         
    }
}