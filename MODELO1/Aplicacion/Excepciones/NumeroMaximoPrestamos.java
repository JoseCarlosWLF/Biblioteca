package Aplicacion.Excepciones;

public class NumeroMaximoPrestamos extends Exception {
    public NumeroMaximoPrestamos() {
        super("\n"+"PRÉSTAMO NO PERMITIDO. NÚMERO MÁXIMO DE PRÉSTAMOS (2) ALCANZADO"+"\n");
    }
    
}