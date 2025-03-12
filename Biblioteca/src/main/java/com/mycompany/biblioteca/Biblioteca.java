package com.mycompany.biblioteca;

import CapaNegocio.ManejadorLibros;
import CapaPersistencia.AutorDAOImpl;
import CapaPersistencia.LibroDAOImpl;
import CapaPersistencia.PrestamoDAOImpl;
import CapaPersistencia.UserDAOImpl;
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
        
        ArrayList datos1 = new ArrayList();
        ArrayList datos2 = new ArrayList();       
        
        datos1.add(2);
        datos1.add(1);
        
        datos2.add(2);
        datos2.add(2);
        
        System.out.println(manejador.registrarPrestamo(datos1));
        System.out.println(manejador.registrarPrestamo(datos2));
        
        
        
        
         //System.out.println(p.consultar());
         
        
       LibroDAOImpl p = new LibroDAOImpl();
       p.devolucion(1,2);
        
        
         
    }
}