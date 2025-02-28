package DominioProblema;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Libro extends MaterialBibliotecario {
    
    private static final long serialVersionUID = 4L;
    private int añoPublicacion;
    private String autor;
    
    
    public Libro(String titulo, String autor, int añoPublicacion, int id, String editorial) {

        this.titulo = titulo;
        this.autor = autor;
        this.añoPublicacion = añoPublicacion;
        this.id = id;
        this.editorial = editorial; 
    }

    //Getters y setters de los atributos propios del libro 

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setId(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setEstado(String autor) {
        this.autor = autor;
    }


    // Método toString para darle formato a la impresión del objeto 
    @Override
    public String toString() {
        return "Libro: " + titulo + "\n" +  
           "Autor: " + autor + "\n" +  
           "Año: " + añoPublicacion  + "\n" + 
           "idLibro: " + id ;
    }


}