package modelo.conexion;

import java.sql.*;

import javax.swing.JOptionPane;

import java.util.Properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Conexion {
	private String nombreBd;
	private String usuario;
	private String password;
	private String url;

	private Connection conn=null;
        private Properties propiedades = new Properties();
        
        private void cargarCredenciales() {
            try {
                propiedades.load(new FileInputStream("src\\main\\java\\Properties\\db.properties"));
                this.nombreBd = propiedades.getProperty("db.database");
                this.usuario = propiedades.getProperty("db.user");
                this.password = propiedades.getProperty("db.pass");
                this.url = propiedades.getProperty("db.host") + this.nombreBd;
                System.out.println(this.url);
            } catch (FileNotFoundException e) {
                System.out.println("No se pudo leer el archivo "+e.getMessage());
                System.exit(1);
            } catch (IOException e) {
                System.out.println("Ocurrió un error al leer las credenciales: "+e.getMessage());
            }
        }
        
	//constructor de la clase
	public Conexion(){
            cargarCredenciales();
            try {
                //obtener el driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                //obtener la conexion
                conn=DriverManager.getConnection(url,usuario,password);
                if (conn!=null) {
                        System.out.println("Conexion Exitosa  a la BD: "+nombreBd);
                }
            } catch (ClassNotFoundException e) {
                System.out.println("ocurre una ClassNotFoundException : "+e.getMessage());
            } catch (SQLException e) {
                System.out.println("ocurre una SQLException: "+e.getMessage());
            }
	}
	public Connection getConnection(){
            return conn;
	}
	public void desconectar(){
            conn=null;
	}
}

