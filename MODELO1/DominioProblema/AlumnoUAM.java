package DominioProblema;

import java.io.*;
import java.util.*;

/**
 * 
 */
public class AlumnoUAM extends PersonaUAM {

    //private long matricula;

    private static final long serialVersionUID = 6L;

    public AlumnoUAM(String nombre, long id, String correo, int nip) {
        this.nombre = nombre;
        this.id = id;
        this.correo = correo; 
        this.prestamos = new ArrayList<Préstamo>(); 
        this.nip = nip; 
    }

    // Método toString para generar una representación legible del objeto AlumnoUAM
    @Override
    public String toString() {
        return "Alumno: " + nombre + "\n" +  // Nombre del alumno
           "Matrícula: " + id + "\n" +  // Matrícula del alumno
           "Email: " + correo  + "\n" + "\n" ;
    }

}