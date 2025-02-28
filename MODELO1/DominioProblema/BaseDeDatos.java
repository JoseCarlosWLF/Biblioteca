package DominioProblema;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * 
 */
public class BaseDeDatos {

   
    public BaseDeDatos() {
        // Cargo las listas desde archivo 
        listaMaterialBibliotecario = cargarLista("ListaMaterialBibliotecario.dat");
        listaPersonas = cargarLista("ListaPersonasUAM.dat");
        listaDevolucion = cargarLista("ListaDevoluciones.dat");        
    }

    private ArrayList<MaterialBibliotecario>listaMaterialBibliotecario; //Almacena todo el material bibliotecario
    private ArrayList<PersonaUAM> listaPersonas; //Almacena los datos y préstamos de las personas 
    private ArrayList<Devolución> listaDevolucion; //Almacena todas las devoluciones 

    public void agregar(int Lista, Object dato) { //Lista es para saber en qué lista se debe agregar algo y dato es de tipo Object es para hacer el casting correspondiente dependiendo de la lista. 

        switch (Lista) {
            case 1: //Agregar Material bibliotecario a listaMaterialBibliotecario
                MaterialBibliotecario MB = (MaterialBibliotecario) dato; 
                listaMaterialBibliotecario.add(MB);
                break;
        
            case 2: //Agregar AlumnoUAM a listaEmpleados
                AlumnoUAM alumno = (AlumnoUAM)dato;
                listaPersonas.add(alumno);
                break;
            
            case 3: //Agregar EmpleadoUAM a listaEmpleados
                EmpleadoUAM empleado = (EmpleadoUAM)dato;
                listaPersonas.add(empleado);
                break;

            case 4: //Agregar una devolución al arreglo de devoluciones 
                Devolución devolucion = (Devolución)dato;
                listaDevolucion.add(devolucion); 
                break; 

        }
        
    }// Termina agregar
    
    public Object consultar(Object dato, int tipoBusqueda) {
        
        switch (tipoBusqueda) {

            case 1: //Consultar un objeto en listaMaterialBibliotecario
            int idMaterial = (int)dato;
            for(MaterialBibliotecario material: listaMaterialBibliotecario){
                if(material.getId() == idMaterial){
                    return material;
                }
            }
            return null;

            case 2: // Consultar un objto en ListaPersonas 
            long id = (long)dato;
            for(PersonaUAM persona: listaPersonas){
                if(persona.getId() == id){
                    return persona;
                }
            }   
            return null; 

            case 3: //ConsultarListaMaterial
                return listaMaterialBibliotecario;

            case 4: //ConsultarPersonas
                return listaPersonas;

            case 6: //Consultar devoluciones 
                return listaDevolucion; 

            default:
            return null; // Retorna null si tipoBusqueda no es válido
        }

        
    } //Fin consultar

    public void actualizarBD(PersonaUAM persona, Object objeto, int tipoActualizacion){

        switch (tipoActualizacion) {
            case 1: //Agrego un préstamo y pido que se guarde en archivo
                Préstamo prestamo = (Préstamo) objeto; 
                ArrayList<Préstamo> arregloPrestamos= persona.getPrestamos();
                arregloPrestamos.add(prestamo);
                guardarArchivo("ListaPersonasUAM.dat", listaPersonas);
                break;

            case 2: //Remuevo un préstamo cuando ya se hizo la devolución y pido que se guarde en archivo
                Préstamo prestamo1 = (Préstamo) objeto; 
                ArrayList<Préstamo> arregloPrestamos1= persona.getPrestamos();
                arregloPrestamos1.remove(prestamo1);
                guardarArchivo("ListaPersonasUAM.dat", listaPersonas);
                break;

            case 3: //Pido que la devolución se guarde en archivo

                guardarArchivo("ListaDevoluciones.dat", listaDevolucion);
                break;
                

            default:
                break;
        }
        
    }//Fin actualizar

    public <T> void guardarArchivo(String nombreArchivo, ArrayList<T> lista){

        try(ObjectOutputStream OOS = new ObjectOutputStream(new FileOutputStream(nombreArchivo))){
            OOS.writeObject(lista); // Guardar la lista en el archivo
        } catch(IOException e){ // Manejo de errores en caso de fallo al guardar
            System.err.println("Error al guardar el archivo" + e.getMessage());
        }
    }// FIN GUARDAR ARCHIVO

    public static <T> ArrayList<T> cargarLista(String nombreArchivo) {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (ArrayList<T>) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar la lista: " + e.getMessage());
            return new ArrayList<>(); // Retorna una lista vacía en caso de error
        }
    }// FIN CARGAR LISTA


//-------------------------Esto fue lo que utilicé para llenar las bases de datos------------------------
    public void cargarLista(){ //Métoo que llena la listas con valores de default 

        //Vacía las listas actuales
        listaMaterialBibliotecario.clear();
        listaPersonas.clear();
        listaDevolucion.clear();

        //Creo y agrego objetos para listaMaterialBibliotecario
        Libro libro1 = new Libro("A Clash of Kings", "George R.R. Martin", 1998, 955555,"Pacifico");
        agregar(1,libro1);

        Libro libro2 = new Libro("Todos los nombres", "José Saramago", 1997, 999999, "Planeta");
        agregar(1,libro2);

        Libro libro3 = new Libro("Cálculo con Geometría Analítica", "Dennis G. Zill", 1986, 988888,"Grupo Editorial");
        agregar(1,libro3);

        Libro libro4 = new Libro("Brief Answers to the Big Questions", "Stephen Hawking", 2018, 933333, "Batam Books");
        agregar(1,libro4);

        Revista revista1 = new Revista(1111, "Ciencia Hoy", 12, 5, "Marzo", "Editorial Alfa");
        agregar(1,revista1); 

        Revista revista2 = new Revista(1222, "Tecnología y Futuro", 8, 3, "Julio", "Editorial Beta");
        agregar(1,revista2);

        guardarArchivo("ListaMaterialBibliotecario.dat", listaMaterialBibliotecario);// Las guardo en archivo 

//------------------ Creo y agrego objetos para listaPersonas ------------------------------------------------------------------------

        AlumnoUAM alumno1 = new AlumnoUAM("Maddie Rebolledo", 1264, "cbi2193012236@izt.uam.mx",1234); 
        Préstamo prestamo1 = registrarPrestamo(alumno1, libro1, 19, 1, 2025, true);
        Préstamo prestamo2 = registrarPrestamo(alumno1, libro2, 2, 1, 2025, false);
        ArrayList<Préstamo> arreglo1 = new ArrayList<Préstamo>();
        arreglo1.add(prestamo1);
        arreglo1.add(prestamo2);
        alumno1.setPrestamos(arreglo1);
        agregar(2,alumno1);
       
        EmpleadoUAM empleado1 = new EmpleadoUAM("Alfonso Martínez Martíinez",9876, "almm@xanum.uam.mx",9876);
        Préstamo prestamo3 = registrarPrestamo(empleado1, libro1, 12, 1, 2025, true);
        Préstamo prestamo4 = registrarPrestamo(empleado1, libro2, 15, 1, 2025, true);
        ArrayList<Préstamo> arreglo2 = new ArrayList<Préstamo>();
        arreglo2.add(prestamo3);
        arreglo2.add(prestamo4);
        empleado1.setPrestamos(arreglo2);
        agregar(3,empleado1);

        AlumnoUAM alumno2 = new AlumnoUAM("Karla Bustillo", 7777, "cbs219325864@izt.uam.mx",5678); 
        agregar(2,alumno2);

        EmpleadoUAM empleado2 = new EmpleadoUAM("José Luis Munzón",2222, "jlm@xanum.uam.mx",9999);
        agregar(3,empleado2);

        EmpleadoUAM administrador = new EmpleadoUAM("Laura Jiménez", 0000, "adminBiblioteca@gmail.com",5555);
        agregar(3,administrador);

        
        //Actualizo los archivos 

        guardarArchivo("ListaPersonasUAM.dat", listaPersonas);
        guardarArchivo("ListaDevoluciones.dat", listaDevolucion);

    } 

    //Métodos en los que uso fechas específicas para crear los préstamos 
    public Préstamo registrarPrestamo(PersonaUAM persona, MaterialBibliotecario material, int dia, int mes, int año, boolean indicador){
        //boolean indicador;
        LocalDate fechaPrestamo = LocalDate.of(año, mes, dia);
        LocalDate fechaLimite= fechaPrestamo.plus(2, ChronoUnit.WEEKS);
        int idPrestamo = generarId(); 
        Préstamo prestamo = new Préstamo(persona, material, indicador, fechaPrestamo, fechaLimite, idPrestamo); 
        actualizarBD(persona, prestamo,1);
        //System.out.println(persona.getPrestamos());
        return prestamo;
 
     } //Fin RegistrarPrestamo

    //Genero los id
     public int generarId(){
        Random random = new Random();
        return 1000 + random.nextInt(9000); // Genera un número de 4 dígitos

     }

}


    



