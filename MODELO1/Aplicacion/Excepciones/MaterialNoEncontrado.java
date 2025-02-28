package Aplicacion.Excepciones;

public class MaterialNoEncontrado extends Exception {
    public MaterialNoEncontrado() {
        super("\n" + "MATERIAL BIBLIOGRÁFICO NO ENCONTRADO" + "\n");
    }
    
}