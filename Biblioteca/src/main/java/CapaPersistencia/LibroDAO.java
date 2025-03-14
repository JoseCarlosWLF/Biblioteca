package CapaPersistencia;
import CapaNegocio.Libro;

import java.util.ArrayList;

public interface LibroDAO {
    public Libro buscarLibro(String isbn);
    public ArrayList<Libro> listarLibrosSucursal(int idSucursal);
}
