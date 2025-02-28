package DominioProblema;

import java.io.*;
import java.util.ArrayList;


public abstract class PersonaUAM implements Serializable{

    private static final long serialVersionUID = 1L;
    protected String nombre;
    protected String correo;
    protected long id; 
    protected ArrayList<Préstamo>prestamos; 
    protected int nip; 


     //Getters y setters de los atributos propios de  PersonaUAM 

    public String getnombre(){
        return nombre;
    }

    public void setnombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getcorreo(){
        return correo;
    }

    public void setcorreo(String correo){
        this.correo = correo;
    }

    public ArrayList<Préstamo> getPrestamos(){
        return prestamos;
    }

    public void setPrestamos(ArrayList<Préstamo>prestamos){
        this.prestamos = prestamos;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public int getNip(){
        return nip;
    }

    public void setNip(int nip){
        this.nip = nip;
    }


}