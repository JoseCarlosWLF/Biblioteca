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
public interface IDAO {
    void create(ArrayList<String> datos); //Crear
    public List<List<String>>  consultar(); 
    void prestamo(Libro libro, Usuario usuario);
    void devolucion(Libro libro, Usuario usuario);  
    void multa(long diasDiferencia, Usuario usuario);
    void consularLibroSucursal(Sucursal sucursal);  
}
