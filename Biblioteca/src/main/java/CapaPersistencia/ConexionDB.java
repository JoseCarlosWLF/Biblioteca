/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaPersistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author vsfs2
 */
public class ConexionDB {
    
    private Connection conexion;
    public static final String URL = "jdbc:mysql://localhost/db_modelo1";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    //conn =DriverManager.getConnection("jdbc:mysql://localhost/test?" +"user=minty&password=greatsqldb");
    public Connection obtenerConexion() {
        conexion = null;
        try { // Se carga el driver JDBC-ODBC
            Class.forName(DRIVER);
        } catch (Exception e) {
            System.out.println("No se pudo cargar el driver JDBC");
        }
        try { // Se establece la conexión con la base de datos
            conexion = DriverManager.getConnection(URL + "?" + "user=" + USER + "&" + "password=" + PASSWORD);
            System.out.println("Conexion completada.");
        } catch (SQLException ex) {
            System.out.println("No hay conexion con la base de datos.");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("Vendor Error: " + ex.getErrorCode());
        }
        return conexion;
    }
    
}
