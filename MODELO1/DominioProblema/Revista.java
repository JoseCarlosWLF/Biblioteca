package DominioProblema;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class Revista extends MaterialBibliotecario {
    private static final long serialVersionUID = 8L;

    private int volumen;
    private int numero;
    private String mesPublicacion;

    public Revista(int id, String titulo, int volumen, int numero, String mesPublicacion, String editorial) {
        this.titulo = titulo;
        this.volumen = volumen;
        this.mesPublicacion = mesPublicacion;
        this.id = id;
        this.editorial = editorial;
    }

     //Getters y setters de los atributos propios de la revista 

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getMesPublicacion() {
        return mesPublicacion;
    }

    public void setMesPublicacion(String mesPublicacion) {
        this.mesPublicacion = mesPublicacion;
    }

    // Método toString para darle formato a la impresión del objeto 
    @Override
    public String toString() {
        return "Revista: " + titulo + "\n"+
               "idRevista: " + id + "\n" +
               "Volumen: " + volumen +  "\n" +
               "Número: " + numero + "\n" +
               "Mes de Publicacion: " + mesPublicacion +"\n" +
               "Editorial: " + editorial  ;
    }
}