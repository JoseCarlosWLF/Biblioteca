package Aplicacion.Excepciones;

public class PilaDevolucionesLlena extends Exception{

    public PilaDevolucionesLlena() {
        super("\n" + "PILA DE DEVOLUCIONES ESTÁ LLENA (4). NOTIFICANDO A PERSONAL BIBLIOTECARIO");
        
    }
    
}
