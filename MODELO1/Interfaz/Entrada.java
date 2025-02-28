package Interfaz;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Entrada {

    private Scanner e; // Instancia del objeto Scanner para leer la entrada del usuario

    // Constructor que inicializa el Scanner
    public Entrada(){
        e = new Scanner(System.in); // Crear un nuevo objeto Scanner que lee desde la entrada estándar
    }

    // Método para leer una cadena de texto (String) del usuario
    public String leeString(){
        String cadena = ""; // Variable para almacenar la cadena leída
        cadena = e.nextLine(); // Leer la línea completa del usuario
        return cadena; // Retornar la cadena leída
    }

    // Método para leer un número entero (int) del usuario
    public int leeInt(){
        boolean flag = false; // Bandera para controlar la entrada válida
        int numero = 0; // Variable para almacenar el número leído
        while(flag == false){ // Continuar pidiendo la entrada hasta que sea válida
            try {
                numero = e.nextInt(); // Intentar leer un número entero
                e.nextLine(); // Limpiar el buffer del Scanner después de leer el número
                flag = true; // Si la entrada es válida, cambiar la bandera a true
            } catch (InputMismatchException a) { // Capturar la excepción si la entrada no es un número entero
                System.out.println("Por favor ingresa un numero entero"); // Mensaje de error
                e.nextLine(); // Limpiar el buffer del Scanner para evitar el bucle infinito
            }
        }
        return numero; // Retornar el número entero leído
    }

    // Método para leer un número largo (long) del usuario
    public long leeLong(){
        long numero = 0; // Variable para almacenar el número largo leído
        boolean flag = false; // Bandera para controlar la entrada válida

        while(flag == false){ // Continuar pidiendo la entrada hasta que sea válida
            try {
                numero = e.nextLong(); // Intentar leer un número largo
                e.nextLine(); // Limpiar el buffer del Scanner después de leer el número
                flag = true; // Si la entrada es válida, cambiar la bandera a true
            } catch (InputMismatchException a) { // Capturar la excepción si la entrada no es un número largo
                System.out.println("Por favor ingresa un numero entero"); // Mensaje de error
                e.nextLine(); // Limpiar el buffer del Scanner para evitar el bucle infinito
            }
        }
        return numero; // Retornar el número largo leído
    }

}