/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package CapaPersistencia;
import CapaNegocio.Libro;
import CapaNegocio.Usuario;
import CapaNegocio.Sucursal;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vsfs2
 */
public interface IDAO { // Aquí sólo van las CRUD 
    void create(ArrayList<Object> datos); //Crear
    public List<List<String>>  consultar(); 
    //falta borrar y actualizar 
   
}
