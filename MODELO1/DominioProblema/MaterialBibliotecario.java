package DominioProblema;

import java.io.*;
import java.util.*;

/**
 * 
 */
public abstract class MaterialBibliotecario implements Serializable {

    private static final long serialVersionUID = 2L;
    
    public MaterialBibliotecario() {
    }

    protected String titulo;
    protected int id;
    protected String editorial;

     //Getters y setters de los atributos propios del MaterialBibliotecario 

    public String getTítulo() {
        return titulo;
    }

    public void setTítulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    

}