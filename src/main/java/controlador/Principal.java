package controlador;

import javax.swing.JOptionPane;

import modelo.conexion.Conexion;

public class Principal {
    public static void main(String[] args) {
        Conexion miConexion = new Conexion();
        if (miConexion.getConnection() != null) {
            Aplicacion miAplicacion=new Aplicacion();
            miAplicacion.iniciarSistema(miConexion);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión con la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}