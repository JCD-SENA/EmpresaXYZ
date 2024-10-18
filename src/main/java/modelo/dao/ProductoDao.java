package modelo.dao;

import java.util.ArrayList;

import modelo.conexion.Conexion;
import modelo.vo.ProductoVo;

import controlador.Coordinador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDao {
    private Coordinador miCoordinador;

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador=miCoordinador;
    }
    
    public boolean registrarProducto(ProductoVo miProducto) {
        boolean resultado=false;

        Connection connection=null;
        Conexion conexion= this.miCoordinador.getConexion();
        PreparedStatement preStatement=null;

        connection=conexion.getConnection();
        String consulta="INSERT INTO producto (idProducto, nombre,precio,cantidad) VALUES (?,?,?,?)";
        
        try {
            preStatement = connection.prepareStatement(consulta);
            preStatement.setString(1, miProducto.getIdProducto());
            preStatement.setString(2, miProducto.getNombre());
            preStatement.setInt(3, miProducto.getPrecio());
            preStatement.setInt(4, miProducto.getCantidad());
            
            preStatement.execute();
            resultado = true;
        } catch (SQLException e) {
            System.out.println("No se pudo registrar el dato: "+e.getMessage());
            e.printStackTrace();
            resultado=false;
        }
        
        return resultado;
    }
    
    public ArrayList<ProductoVo> listar () {
        ArrayList<ProductoVo> lista = new ArrayList<ProductoVo>();
        try {
            Connection connection=null;
            Conexion miConexion= this.miCoordinador.getConexion();
            PreparedStatement statement=null;
            ResultSet result=null;
            connection=miConexion.getConnection();
            String consulta="SELECT idProducto from Producto";
            if (connection!=null) {
                statement=connection.prepareStatement(consulta);
                result=statement.executeQuery();
                while(result.next()==true){
                    lista.add(this.consultarProducto(result.getString("idProducto")));
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
        return lista;
    }
    
    public ProductoVo consultarProductoPorNombre(String nombre) {
        Connection connection=null;
        Conexion miConexion= this.miCoordinador.getConexion();
        PreparedStatement statement=null;
        ResultSet result=null;

        ProductoVo miProducto=null;

        connection=miConexion.getConnection();

        String consulta="SELECT * FROM producto WHERE nombre = ?";
        try {
            if (connection!=null) {
                statement=connection.prepareStatement(consulta);
                statement.setString(1, nombre);
                result=statement.executeQuery();
                while(result.next()==true){
                    miProducto=new ProductoVo();
                    miProducto.setIdProducto(result.getString("idProducto"));
                    miProducto.setNombre(result.getString("nombre"));
                    miProducto.setCantidad(result.getInt("cantidad"));
                    miProducto.setPrecio(result.getInt("precio"));
                }
            } else {
                miProducto = null;
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta del usuario: "+e.getMessage());
            return null;
        }
        return miProducto;
    }
    
    public ProductoVo consultarProducto(String idProducto) {
        Connection connection=null;
        Conexion miConexion= this.miCoordinador.getConexion();
        PreparedStatement statement=null;
        ResultSet result=null;

        ProductoVo miProducto=null;

        connection=miConexion.getConnection();

        String consulta="SELECT * FROM producto WHERE idProducto = ?";
        try {
            if (connection!=null) {
                statement=connection.prepareStatement(consulta);
                statement.setString(1, idProducto);
                result=statement.executeQuery();
                while(result.next()==true){
                    miProducto=new ProductoVo();
                    miProducto.setIdProducto(result.getString("idProducto"));
                    miProducto.setNombre(result.getString("nombre"));
                    miProducto.setCantidad(result.getInt("cantidad"));
                    miProducto.setPrecio(result.getInt("precio"));
                }
            } else {
                miProducto = null;
            }
        } catch (SQLException e) {
            System.out.println("Error en la consulta del usuario: "+e.getMessage());
        }
        return miProducto;
    }
    
    public boolean actualizarProducto(ProductoVo miProducto) {
        boolean resultado = false;
        
        Connection connection=null;
        Conexion miConexion=this.miCoordinador.getConexion();
        connection=miConexion.getConnection();
        
        try{
            String consulta="UPDATE producto SET idProducto=?, nombre=?, cantidad=?, precio=? WHERE idProducto=? ";
            PreparedStatement preStatement = connection.prepareStatement(consulta);

            preStatement.setString(1, miProducto.getIdProducto());
            preStatement.setString(2, miProducto.getNombre());
            preStatement.setInt(3, miProducto.getCantidad());
            preStatement.setInt(4, miProducto.getPrecio());
            preStatement.setString(5, miProducto.getIdProducto());
            preStatement.executeUpdate();

            resultado=true;
        }catch(SQLException e){
            System.out.println(e);
            resultado=false;
        }
        
        return resultado;
    }
    
    public boolean eliminarProducto(String idProducto) {
        boolean resultado = false;
        
        Connection connection=null;
        Conexion miConexion=this.miCoordinador.getConexion();
        connection=miConexion.getConnection();
        
        try{
            String consulta1="DELETE FROM producto WHERE idProducto = ?";
            String consulta2="DELETE FROM usuario_tiene_producto WHERE idProducto = ?";
            
            PreparedStatement preStatement = connection.prepareStatement(consulta1);
            preStatement.setString(1, idProducto);
            preStatement.executeUpdate();
            
            preStatement = connection.prepareStatement(consulta2);
            preStatement.setString(1, idProducto);
            preStatement.executeUpdate();
            
            resultado = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            resultado = false;
        }
        
        return resultado;
    }
}
