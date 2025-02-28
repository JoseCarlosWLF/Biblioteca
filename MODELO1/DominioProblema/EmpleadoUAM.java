package DominioProblema;

import java.io.*;
import java.util.*;


public class EmpleadoUAM extends PersonaUAM implements Serializable{

    private static final long serialVersionUID = 7L;
    public EmpleadoUAM(String nombre, long id, String correo, int nip) {
        this.nombre = nombre;
        this.id = id; //En este caso es el numero de empleado 
        this.correo = correo; 
        this.prestamos = new ArrayList<Préstamo>();
        this.nip = nip; 
        
    }

    @Override
    public String toString() {
        return "Empleado: " + nombre + "\n" +  
           "Num. Empleado: " + id + "\n" +  
           "Email: " + correo  + "\n" + "\n";
           //+"Los préstamos son: " + "\n" + prestamos + "\n";
    }

}