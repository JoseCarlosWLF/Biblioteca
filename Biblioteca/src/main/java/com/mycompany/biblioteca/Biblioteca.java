package com.mycompany.biblioteca;

import CapaPersistencia.AutorDAOImpl;
import CapaPersistencia.LibroDAOImpl;
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
        
        
LibroDAOImpl prueba3 = new LibroDAOImpl();

System.out.println(prueba3.consultar()); 



}
}