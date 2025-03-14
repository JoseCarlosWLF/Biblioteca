package CapaNegocio;

public class Libro {
    int id;
    String titulo;
    String editorial;
    int id_autor;
    String anio;
    int cantidad = 0;
    int id_sucursal;

    public Libro(int id, String titulo, String editorial, int id_autor, String anio, int cantidad, int id_sucursal) {
        this.id = id;
        this.titulo = titulo;
        this.editorial = editorial;
        this.id_autor = id_autor;
        this.anio = anio;
        this.cantidad = cantidad;
        this.id_sucursal = id_sucursal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public void setId_sucursal(int id_sucursal){
        this.id_sucursal = id_sucursal;
    }
    
    public int getId_sucursal(){
        return id_sucursal;
    }

    @Override
    public String toString() {
        return "Libro [id=" + id + ", titulo=" + titulo + ", editorial=" + editorial + ", id_autor=" + id_autor
                + ", anio=" + anio + "]";
    }

    
}
