package Aplicacion.Excepciones;

public class DevolucionNoValida extends Exception {

    public DevolucionNoValida() {
        super("\n" + "Este material no se puede devolver porque no pertenece a sus prestamos" + "\n");
    }
    
}
