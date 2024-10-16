package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.conexion.Conexion;

public class Carrito {
    private Coordinador miCoordinador;
    
    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }
    
    public boolean comprar (String idProducto, String documento) {
        boolean resultado = false;
        Connection connection=null;
        Conexion conexion= this.miCoordinador.getConexion();
        PreparedStatement preStatement=null;

        connection=conexion.getConnection();
        String consulta="INSERT INTO usuario_tiene_producto (idProducto, documento) VALUES (?,?)";
        
        try {
            preStatement = connection.prepareStatement(consulta);
            preStatement.setString(1, idProducto);
            preStatement.setString(2, documento);

            preStatement.execute();
            resultado = true;
        } catch (SQLException e) {
            System.out.println("No se pudo registrar el dato: "+e.getMessage());
            e.printStackTrace();
            resultado=false;
        }
        return resultado;
    }
}
