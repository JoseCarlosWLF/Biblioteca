package Aplicacion;

import Interfaz.Entrada;
import Interfaz.InterfazUsuario;
import Interfaz.Salida;
import java.io.*;
import java.util.*;

import Aplicacion.Excepciones.*;
import DominioProblema.*;


public class Aplicacion {

        private   BaseDeDatos BD;
        private   static InterfazUsuario UI;
        private ManejadorDevoluciónPréstamo manejador; 

                
            public Aplicacion() {
                UI = new InterfazUsuario();
                BD = new BaseDeDatos(); 
                manejador = new ManejadorDevoluciónPréstamo(BD,UI);
            } //Fin constructor
        
        //----------------------Flujo principal-----------------------------------
            public void ejecutar() {
                int opcion = 0;  //Opción elegida por parte del usuario
                long identificador; // Matrícula o número de empleado
                PersonaUAM persona = null;

                // Bucle para validar el usuario antes de acceder al sistema
                do {
                    try {
                        identificador = UI.datosUsuario(); // Solicita identificación al usuario
                        persona = manejador.validarUsuario(identificador,2);// // Verifica existencia del usuario. tipoBusqueda es para saber en qué arreglo de BD buscar
                        } catch (UsuarioNoEncontrado e) { 
                            manejarExcepcion(e);
                        }
                    
                    } while(persona == null);
                
                
                if (persona.getId() != 0000){ //Checar si el id corresponde a un administrador. Administrador puede ver las 3 bases de datos.             
                    do {
                        opcion = UI.menuPrincipal(persona.getnombre()); // Devuelve la opción del menú principal que el usuario eligió
                        switch (opcion) {

                        case 1:
                            prestamo(persona);
                            break;

                        case 2:
                            devolucion(persona);
                            break;

                        } 

                    } while (opcion != 3); // Mientras el usuario no elija terminar 

                    
                } else { // El usuario es administradora
                            
                    do {
                        opcion = UI.menuAdministrador();
                        switch (opcion) {

                            case 1: // Verla lista de personas 
                                ArrayList<PersonaUAM> personas = (ArrayList<PersonaUAM>)manejador.verListas(4); 
                                for(PersonaUAM personita: personas)  {
                                    UI.imprimeSalida("------------------------------------------------------------------------");
                                    UI.imprimeSalida(personita);
                                    ArrayList<Préstamo> prestamos = personita.getPrestamos();
                                    UI.imprimeSalida("*********************Lista de Préstamos de " + personita.getnombre() + "**********************");
                                    for(Préstamo p: prestamos){
                
                                        UI.imprimeSalida(p);
                                    }
                                }
                                break;
                                
                            case 2: // Ver lista de material bibliográfico
                                ArrayList<MaterialBibliotecario> MB = (ArrayList<MaterialBibliotecario>)manejador.verListas(3); 
                                for(MaterialBibliotecario M: MB)  {
                                    UI.imprimeSalida(M + "\n");
                                }
                                break;

                            case 4: // Resetear la lista 
                                manejador.resetearBD();
                                break;

                            case 5: // Ver lista de devoluciones
                                ArrayList<Devolución> dev = (ArrayList<Devolución>)manejador.verListas(6); 
                                    for(Devolución dev1: dev)  {
                                        UI.imprimeSalida(dev1);
                                    }
                                    break;     
                                    
                            case 6:
                                System.exit(0);


                            } 

                        } while (opcion != 3);
                }


            
            } //Fin ejecutar()
        
  //----------------------------------------PRÉSTAMO-----------------------------------------------------------              
            public void prestamo(PersonaUAM persona) throws  IndexOutOfBoundsException{
                MaterialBibliotecario material = null;  
                ArrayList<Préstamo>prestamosVencidos = new ArrayList<>(); 
                int nip = 0; //Iniciaizar el nip
                boolean confirmacion = false;    //Indica true si el nip es correcto para confirmar el préstamo 
        
                try {   
                    prestamosVencidos= manejador.validarPermisosPrestamo(persona); //Primero revisa si la persona tiene las condiciones para tener un préstamo
                    int codigo = 0; // Código es el nip 
        
                    if(!prestamosVencidos.isEmpty()){ //Si el arreglo de prestamos venciddos NO está vacío
                        throw new IndexOutOfBoundsException("\n" + "NO SE PERMITE PRÉSTAMO POR EXISTENCIA DE PRÉSTAMOS VENCIDOS" + "\n");
                    }
        
                    // Ciclo para ingresar el id del libro
                    do {
                        try {
                            UI.imprimeSalida("Ingresa el código del material bibliográfico");
                            codigo = UI.leeInt();
                            material = manejador.validarMaterial(codigo, 1); //Busca el material en la base de datos 
                            
                            
                        } catch(MaterialNoEncontrado f){
                            manejarExcepcion(f);
                
                        }
                        
                    } while (material == null); //Sale cuando encuentrauna coindidencia 
        
            //-----------------------Verificar NIP-------------------------------------------------------------------        
                    
                    do {
                        try {
                            UI.imprimeSalida("\n"+"Ingresa tu nip para confirmar el préstamo de: " + "\n"+ "\n" + material + "\n");
                            nip = UI.leeInt();
                            confirmacion = manejador.confirmaNip(persona,nip); // Confirmo si el NIP ingresado coincide con el de la persona 

                        } catch (InputMismatchException e) {
                            UI.imprimeSalida(e.getMessage());
                        }
                    } while (confirmacion == false); //Me quedo en el ciclo mientras no se correcto
                
            //-------------------------Registrar préstamo----------------------------------------------------------------
                    UI.imprimeSalida(persona.getPrestamos() + "\n");
                    Préstamo prestamo = manejador.registrarPrestamo(persona,material); // Registro el préstamo en la base de datos 
                    UI.imprimirPrestamo(prestamo);
        
                } catch (NumeroMaximoPrestamos e) {
                    manejarExcepcion(e);
                    for(Préstamo prestamo : persona.getPrestamos()){
                        UI.imprimeSalida(prestamo);
                    }
                    
        
                } catch( IndexOutOfBoundsException a){ 
                    UI.imprimeSalida(a.getMessage() + "\n");
                    for(Préstamo prestamo : prestamosVencidos){
                        UI.imprimeSalida(prestamo);
                    }
                }               
            } // Fin prestamo()
        
 //-------------------------------------Devolución-------------------------------------------------------------------
            public void devolucion(PersonaUAM persona){

                try {
                    if(manejador.verificarPilaDevoluciones() == true){ // Verifico si la pila de material devuelto no stá llena. Se llena con 4 libros. 

                        try {
                            ArrayList<Préstamo> prestamos= manejador.validarPrestamos(persona); //Primero valida si la persona tiene préstamos pendientes
                            UI.imprimeSalida("----------------------------Devoluciones pendientes ----------------------"); //Imprimo las devoluciones pendientes 
                            for(Préstamo prestamo: prestamos){
                                UI.imprimeSalida("\n" +prestamo +  "\n") ;
                                UI.imprimeSalida("----------------------------------------------------------------------");
                            }
        
                            int idMaterial = UI.procesoCompuerta(); //Pido que el usuario que ingrese el material en la compuerta 
                            Préstamo prestamo = manejador.validarCondicionesDevolucion(idMaterial, persona); //Valido que el material exista y que sea uno de los préstamos del usuario. 
                            boolean confirmacion = false; //Confirmación de que el NIP es correcto o incorrecto. 
        
                            do {
                                try {
                                    UI.imprimeSalida("\n"+"Ingresa tu nip para confirmar la devolución de:  " + "\n"+ "\n" + prestamo.getMaterial().getTítulo() + "\n");
                                    int nip = UI.leeInt();
                                    confirmacion = manejador.confirmaNip(persona,nip); 
        
                                } catch (InputMismatchException e) {
                                    UI.imprimeSalida(e.getMessage());
                                }
                            } while (confirmacion == false); //Mientras el NIP no sea correcto lo sigue pidienddo 
        
                            Devolución devFinal = manejador.registrarDevolucion(persona,prestamo); //Registro la devolución en la base dde datos 
                            UI.imprimirDevolución(devFinal);
        
        
                            } catch (DevolucionNoValida e) {
                                manejarExcepcion(e);
                                
                            } catch (MaterialNoEncontrado a) {
                                manejarExcepcion(a);
                                
                            } catch (SinDevolucionesPendientes d) {
                                manejarExcepcion(d);    
                        }   

                    }

                } catch (PilaDevolucionesLlena e) {
                    manejarExcepcion(e);
                }

                
            } //Fin devolucion()
//-----------------------------------------------------------------------------------------------------------------------------------------
    

    public static void manejarExcepcion(Exception e) { //Manejador genérico de excepciones 
        UI.imprimeSalida(e.getMessage() + "\n");
    }

    public static void main(String[] args) {
        Aplicacion app = new Aplicacion();
        
        while (true){
            app.ejecutar();
        }       
    }
}