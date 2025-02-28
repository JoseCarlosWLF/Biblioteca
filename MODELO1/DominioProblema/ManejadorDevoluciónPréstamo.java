package DominioProblema;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import Aplicacion.Excepciones.*;
import Interfaz.InterfazUsuario;


public class ManejadorDevoluciónPréstamo implements Serializable{

    BaseDeDatos BD; 
    InterfazUsuario UI;

    public ManejadorDevoluciónPréstamo(BaseDeDatos BD, InterfazUsuario UI) {
        this.UI = UI;
        this.BD = BD;
    }

    // Validar si el usuario existe
    public PersonaUAM validarUsuario(long identificador,int tipoBusqueda) throws UsuarioNoEncontrado { //identificador es la matrícula o numero de empleado. tipoBusqueda es para saber en qué arreglo buscar
        PersonaUAM persona = (PersonaUAM)BD.consultar(identificador, tipoBusqueda); 
        if (persona != null){
            return persona;
        
        } else{
            throw new UsuarioNoEncontrado();
    }//Fin validarUsuario
}


    //  Validar si tiene permisos para préstamo (Que no tenga préstamos vencidos y que no haya alcanzado el número máximo de préstamos)
    public ArrayList<Préstamo> validarPermisosPrestamo(PersonaUAM persona) throws NumeroMaximoPrestamos{
        ArrayList<Préstamo> listaPrestamos = persona.getPrestamos();
        ArrayList<Préstamo> prestamosVencidos = new ArrayList<Préstamo>();

        if(listaPrestamos.size() == 2){   //Verifica el número de préstamos que tiene 
            throw new NumeroMaximoPrestamos();
        }

        for(Préstamo prestamo: listaPrestamos){ 
            if(!prestamo.getIndicador()){  // Verifica los préstamos vencidos 
                prestamosVencidos.add(prestamo);    
            }  
        } 
        return prestamosVencidos;

    } //Fin validarPrestamo


    // Validar si el material existe
    public MaterialBibliotecario validarMaterial(int idMaterial, int tipoBusqueda) throws MaterialNoEncontrado{
        MaterialBibliotecario material = (MaterialBibliotecario)BD.consultar(idMaterial, tipoBusqueda); //Consulta si el material existe en la base de datos 
        
        if (material != null){ // Si no es null, devuelve el material
            return material;
        } else{
            throw new MaterialNoEncontrado(); 
        }
    } //Fin Validar Material


    // Valida el nip ingresado
    public boolean confirmaNip(PersonaUAM persona, int nip) throws InputMismatchException{
        if(nip == persona.getNip()){
            return true;
        }
        else{
            throw new InputMismatchException("\n" + "NIP Incorrecto" +"\n"); 
        }
    }//Fin confirmaNip


    public Préstamo registrarPrestamo(PersonaUAM persona, MaterialBibliotecario material){
        LocalDate fechaPrestamo = LocalDate.now(); // fecha de hoy 
        LocalDate fechaLimite = fechaPrestamo.plus(2, ChronoUnit.WEEKS); //fecha límite es dos semanas después de la fecha del préstamo. 
        int idPrestamo = generarId(); // genera el id del préstamo
        Préstamo prestamo = new Préstamo(persona, material, true, fechaPrestamo, fechaLimite, idPrestamo); //crea el objeto préstamo
        BD.actualizarBD(persona, prestamo,1); //actualiza la lista de personas con el nuevo préstamo en la persona correspondiente 
        return prestamo;
     } //Fin RegistrarPrestamo


    // Genero el id del préstamo con un número random  
    public int generarId(){
        Random random = new Random();
        return 1000 + random.nextInt(9000); // Genera un número de 4 dígitos

    }


    public Devolución registrarDevolucion(PersonaUAM persona, Préstamo prestamo){

        LocalDate fechaDevolución = LocalDate.now(); // fecha de hoy 
        long multa = 0; 
        long diferencia = ChronoUnit.DAYS.between(prestamo.getFechaLimite(), fechaDevolución); // Calculo la diferencia en días de la fecha actual y la fecha límite 

            if(diferencia >0){ //Si la diferencia es mayor a 0, genero la multa. 
                multa = generarMulta(diferencia);
            } 
            
        Devolución devolucion = new Devolución(fechaDevolución, multa, prestamo); //Objeto devolución 
        BD.agregar(4, devolucion); //agrego la devolución a la lista de devoluciones 
        BD.actualizarBD(persona, devolucion,3); // guardo la lista actualizda 
        BD.actualizarBD(persona, prestamo, 2); // remuevo un préstamo de la lista de préstamos de una persona y guardo la lista
        
        return devolucion;
    } //Fin devolución 


    public <T> ArrayList verListas(int tipoBusqueda){ //Devuelvo las listas completas de la base de datos 
        return (ArrayList<T>)BD.consultar(1, tipoBusqueda); 

    }


    //Valido que la persona que quiere hacer una devoluciones tenga préstamos existentes 
    public ArrayList<Préstamo> validarPrestamos(PersonaUAM persona) throws SinDevolucionesPendientes{

        ArrayList<Préstamo> prestamos = persona.getPrestamos();
        if(prestamos.isEmpty()){
            throw new SinDevolucionesPendientes();
        }else{
            return prestamos; 
        }
    }

    //Valido que el material exista y que sea uno de los préstamos de la persona 
    public Préstamo validarCondicionesDevolucion(int idMaterial, PersonaUAM persona) throws DevolucionNoValida, MaterialNoEncontrado{
            
            validarMaterial(idMaterial,1); 
            for(Préstamo prestamo: persona.getPrestamos()){
                if(idMaterial == prestamo.getMaterial().getId()){       
                    return prestamo; 
                }
            }
            throw new DevolucionNoValida(); 
    }


    //Método que me genera una multa 
    public long generarMulta(long diasDiferencia){
        long multa = diasDiferencia*2; 
        return multa;

    }

    // Verifico que la pila de material devuelto está llena 
    public boolean verificarPilaDevoluciones() throws PilaDevolucionesLlena {

        ArrayList<Devolución> arregloDevoluciones= verListas(6);

        if (arregloDevoluciones.size()<4){
            return true;
        } else{
             throw new PilaDevolucionesLlena();
        }
    }


    // Cargo los arreglos con los objetos que coloqué de default 
    public void resetearBD(){
        BD.cargarLista();
    }
}
