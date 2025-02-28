package Aplicacion.Excepciones;

public class PrestamosVencido extends Exception {

    public PrestamosVencido(){
        super("\n" + "NO SE PERMITE PRÉSTAMO POR EXISTENCIA DE PRÉSTAMOS VENCIDOS" + "\n");
    }
}

