package DominioProblema;
import java.util.Date;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class Préstamo implements Serializable{

    private static final long serialVersionUID = 3L;
    private MaterialBibliotecario material; //Material que se va a prestar
    private PersonaUAM persona; //persona que lo solicita
    private boolean indicador; // Si el préstamo está activo o no. true está activo, false está vencido. 
    private LocalDate fechaPrestamo; //Fecha en la que se realizó el préstamo
    private LocalDate  fechaLimite; //Fecha máximo para devolverlo antes de generar una multa
    public long idPrestamo; //identificador del prestamo


    public Préstamo(PersonaUAM persona, MaterialBibliotecario material, boolean indicador, LocalDate fechaPrestamo, LocalDate fechaLimite, long idPrestamo) {
        this.persona = persona;
        this.material = material;
        this.indicador = indicador; 
        this.fechaPrestamo = fechaPrestamo;
        this.fechaLimite = fechaLimite;
        this.idPrestamo = idPrestamo; 

    }

// Getters y setters propios del préstamo
    public MaterialBibliotecario getMaterial(){
        return material;
    }

    public void setMaterial(MaterialBibliotecario material){
        this.material = material;
    }


    public long getidPrestamo(){
        return idPrestamo;
    }

    public void setidPrestamo(long idPrestamo){
        this.idPrestamo = idPrestamo;
    }
    public PersonaUAM getPersona(){
        return persona;
    }

    public void setPersona(PersonaUAM persona){
        this.persona = persona;
    }

    public boolean getIndicador(){
        return indicador;
    }

    public void setIndicador(boolean indicador){
        this.indicador = indicador;
    }



    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }


    @Override
public String toString() {
    return "Préstamo: " + idPrestamo + "\n" +
            material + "\n" +
           "Fecha de préstamo: " + fechaPrestamo + "\n" +
           "Fecha de límite: " + fechaLimite + "\n";
           //"Vencido =" + indicador + "\n";
}

}