package Aplicacion.Excepciones;

public class UsuarioNoEncontrado extends Exception {
    public UsuarioNoEncontrado() {
        super("\n" + "USUARIO NO IDENTIFICADO"+"\n");
    }
    
}
