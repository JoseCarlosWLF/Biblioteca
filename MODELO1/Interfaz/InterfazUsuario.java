package Interfaz;

import java.io.*;
import java.util.*;

import DominioProblema.Devolución;
import DominioProblema.PersonaUAM;
import DominioProblema.Préstamo;


public class InterfazUsuario {

    Entrada entrada;
    Salida salida;

    public InterfazUsuario() {

        entrada = new Entrada();
        salida = new Salida();
    }

    public long datosUsuario() {
        salida.imprimeSalida("\n"+"--------------------------------------Inicio Sesión--------------------------------------" + "\n");
        salida.imprimeSalida("Ingresa tu número de identificación" + "\n");
        long identificador = entrada.leeLong();
        return identificador;
    }

//---------------------Imprimir los datos --------------------------------------------------------------------------
    public void imprimeDatosUsuario(PersonaUAM persona){
        salida.imprimeSalida( "Hola" + persona.getnombre() + ""
        );

    }

//---------------------------Menu Administrador----------------------------------------------------------------------------------
    
        public int menuAdministrador(){
        salida.imprimeSalida("\n" + "--------------------------------------Menú Administradora-------------------------------------" + "\n");
        
        salida.imprimeSalida("Hola, administradora" + "\n");
        salida.imprimeSalida("1. Ver Lista Personas 2. Ver lista Material 3. Terminar Consulta 4. Cargar Base de Datos 5. Ver lista devoluciones 6. Cerrar Sistema" + "\n");
        int opcion= entrada.leeInt();
        return opcion;
    }


//---------------------------Menú Principal------------------------------------------------------------------------------------
    public int menuPrincipal(String nombre) {
        salida.imprimeSalida("\n"+"--------------------------------------Menú Principal-------------------------------------"+"\n");
        salida.imprimeSalida("Hola, " + nombre + "\n" + "\n" +
                            "¿Qué quieres hacer?" + "\n" +
                            "1. Préstamo" + "\n"+
                            "2. Devolución" + "\n"+
                            "3. Terminar" + "\n"
                            );

        int opcion = entrada.leeInt();
        return opcion; 
    }

    public void imprimeSalida(Object dato) {
        salida.imprimeSalida(dato);
    }


//-----------------------------------Proceso de Compuerta------------------------------------------------------
    public int procesoCompuerta(){
        salida.imprimeSalida("Por favor ingresa el libro en la compuerta" + "\n");

        try {
            //Ponemos a "Dormir" el programa durante los ms que queremos
            Thread.sleep(5*1000);
         } catch (Exception e) {
            imprimeSalida(e.getMessage());
         }

        salida.imprimeSalida("\n"+ "Por favor ingresa el código el libro");

        return entrada.leeInt(); 
    }
//------------------------------------------------------------------------------------------------------

    public int leeInt(){
        return entrada.leeInt();
    }

//--------------------------Imprimir Recibos-------------------------------------------------------------

    public void imprimirDevolución(Devolución devolución){
        salida.imprimeSalida("\n" +"DEVOLUCIÓN CONFIRMADA"+ "\n");
        salida.imprimeSalida("\n" +"-------------------------RECIBO--------------------------"+ "\n");
        salida.imprimeSalida(devolución + "\n");

        salida.imprimeSalida("Enviando recibo de devolución  a " + devolución.getPrestamo().getPersona().getcorreo() + "\n");
        salida.imprimeSalida("\n" +"--------------------------------------------------------------------"+ "\n");


    }

    public void imprimirPrestamo(Préstamo prestamo){
        salida.imprimeSalida("\n" +"PRÉSTAMO CONFIRMADA"+ "\n");
        salida.imprimeSalida("\n" +"-------------------------RECIBO PRÉSTAMO--------------------------"+ "\n");
        salida.imprimeSalida(prestamo + "\n");

        salida.imprimeSalida("Enviando recibo de préstamo  a " + prestamo.getPersona().getcorreo() + "\n");
        salida.imprimeSalida("\n" +"-------------------------------------------------------------------"+ "\n");


    }

}