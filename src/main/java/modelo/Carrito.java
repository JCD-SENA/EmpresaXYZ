package modelo;

import java.util.ArrayList;

import controlador.Coordinador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.conexion.Conexion;

public class Carrito {
    private Coordinador miCoordinador;
    
    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }
    
    public ArrayList<String> listar (String documento) {
        ArrayList<String> lista = new ArrayList<String>();
        try {
            Connection connection=null;
            Conexion miConexion= this.miCoordinador.getConexion();
            PreparedStatement statement=null;
            ResultSet result=null;
            connection=miConexion.getConnection();
            String consulta="SELECT P.nombre, P.precio FROM producto P JOIN usuario_tiene_producto UtP ON UtP.idProducto = P.idProducto WHERE UtP.documento = ?";
            if (connection!=null) {
                statement=connection.prepareStatement(consulta);
                statement.setString(1, documento);
                result=statement.executeQuery();
                while(result.next()==true){
                    lista.add(result.getString("nombre")+ " "+result.getInt("precio")+"$");
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
        return lista;
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
