/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CapaPersistencia;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vsfs2
 */
public interface IDAO {
    void create(ArrayList datos); //Crear
    public List<List<String>>  consultar(); 
    //void delete(int id); //borrar
    //public void update(); //actualizar     
}
