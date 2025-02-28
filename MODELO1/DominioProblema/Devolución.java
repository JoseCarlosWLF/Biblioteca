package DominioProblema;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * 
 */
public class Devolución implements Serializable{

    private static final long serialVersionUID = 10L;

    private LocalDate fechaDevolución;
    private long multa;
    private Préstamo prestamo;

    // Constructor con parámetros
    public Devolución(LocalDate fechaDevolución, long multa2, Préstamo prestamo) {
        this.fechaDevolución = fechaDevolución;
        this.multa = multa2;
        this.prestamo = prestamo;
    }

    // Getters y Setters
    public LocalDate getFechaDevolución() {
        return fechaDevolución;
    }

    public void setFechaDevolución(LocalDate fechaDevolución) {
        this.fechaDevolución = fechaDevolución;
    }

    public long getMulta() {
        return multa;
    }

    public void setMulta(long multa) {
        this.multa = multa;
    }

    public Préstamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Préstamo prestamo) {
        this.prestamo = prestamo;
    }

    // Método toString
    @Override
    public String toString() {
        return "-------------------Devolución----------------------- " + "\n"+
                "idPrestamo: " + prestamo.getidPrestamo()+"\n"+
                "Hecho por: " + prestamo.getPersona().getnombre() +"\n"+
                "Material" + prestamo.getMaterial()+"\n"+
                "Fecha Límite: "+prestamo.getFechaLimite()+"\n"+
                "Fecha de préstamo: "+ prestamo.getFechaPrestamo() +"\n"+
                "Fecha de Devolución: " + fechaDevolución + "\n" +
                "Multa: " + multa + "\n";
    }

}
