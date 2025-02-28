package Aplicacion.Excepciones;

public class SinDevolucionesPendientes extends Exception {
    public SinDevolucionesPendientes() {
        super("\n" + "NO HAY DEVOLUCIONES PENDIENTES" + "\n");
    }
    
}