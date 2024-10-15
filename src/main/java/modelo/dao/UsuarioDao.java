package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import modelo.conexion.Conexion;
import modelo.vo.UsuarioVo;

import controlador.Coordinador;

public class UsuarioDao {
    private Coordinador miCoordinador;

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador=miCoordinador;
    }

    public String registrarUsuario(UsuarioVo miUsuarioVo) {
        String resultado="";

        Connection connection=null;
        Conexion conexion= this.miCoordinador.getConexion();
        PreparedStatement preStatement=null;

        connection=conexion.getConnection();
        String consulta="INSERT INTO usuario (documento,nombre,profesion,edad,direccion,telefono,tipo,estado) VALUES (?,?,?,?,?,?,?,1)";
        if (this.miCoordinador.validarTipoUsuario(miUsuarioVo.getTipo())) {
            try {
                preStatement = connection.prepareStatement(consulta);
                preStatement.setString(1, miUsuarioVo.getDocumento());
                preStatement.setString(2,miUsuarioVo.getNombre());
                preStatement.setString(3,miUsuarioVo.getProfesion());
                preStatement.setInt(4, miUsuarioVo.getEdad());
                preStatement.setString(5, miUsuarioVo.getDireccion());
                preStatement.setString(6, miUsuarioVo.getTelefono());
                preStatement.setInt(7, miUsuarioVo.getTipo());
                preStatement.execute();

                resultado="ok";
            } catch (SQLException e) {
                System.out.println("No se pudo registrar el dato: "+e.getMessage());
                e.printStackTrace();
                resultado="error";
            }
        } else
            resultado = "error";

        return resultado;
    }

    public UsuarioVo consultarUsuario(String doc) {
        return consultarUsuario(doc, 1);
    }
    
    public UsuarioVo consultarUsuario(String doc, int rol) {
        Connection connection=null;
        Conexion miConexion= this.miCoordinador.getConexion();
        PreparedStatement statement=null;
        ResultSet result=null;

        UsuarioVo miUsuario=new UsuarioVo();

        connection=miConexion.getConnection();

        String consulta="SELECT * FROM usuario where documento = ?";
        if (rol == 2)
            consulta+=" AND estado=1";
        ArrayList<UsuarioVo> listUser=new ArrayList<UsuarioVo>();
        try {
            if (connection!=null) {
                statement=connection.prepareStatement(consulta);
                statement.setString(1, doc);

                result=statement.executeQuery();

                while(result.next()==true){
                    miUsuario=new UsuarioVo();
                    miUsuario.setDocumento(result.getString("documento"));
                    miUsuario.setNombre(result.getString("nombre"));
                    miUsuario.setProfesion(result.getString("profesion"));
                    miUsuario.setEdad(result.getInt("edad"));
                    miUsuario.setDireccion(result.getString("direccion"));
                    miUsuario.setTelefono(result.getString("telefono"));		
                    miUsuario.setTipo(result.getInt("tipo"));
                    miUsuario.setEstado(result.getInt("estado"));

                    listUser.add(miUsuario);
                }
            }else{
                miUsuario=null;
            }

        } catch (SQLException e) {
            System.out.println("Error en la consulta del usuario: "+e.getMessage());
        }

        return miUsuario;
    }

    public String actualizaUsuario(UsuarioVo miUsuarioVo) {
        String resultado="";
        Connection connection=null;
        Conexion miConexion=this.miCoordinador.getConexion();
        connection=miConexion.getConnection();
        if (this.miCoordinador.validarTipoUsuario(miUsuarioVo.getTipo())) {
            try{
                String consulta="UPDATE usuario SET documento= ? ,nombre = ? , profesion=? , edad=? , direccion=? ,telefono= ?, tipo= ?, estado=1 WHERE documento= ? ";
                PreparedStatement preStatement = connection.prepareStatement(consulta);

                preStatement.setString(1, miUsuarioVo.getDocumento());
                preStatement.setString(2,miUsuarioVo.getNombre());
                preStatement.setString(3,miUsuarioVo.getProfesion());
                preStatement.setInt(4, miUsuarioVo.getEdad());
                preStatement.setString(5, miUsuarioVo.getDireccion());
                preStatement.setString(6, miUsuarioVo.getTelefono());
                preStatement.setInt(7, miUsuarioVo.getTipo());
                preStatement.setString(8, miUsuarioVo.getDocumento());
                preStatement.executeUpdate();

                resultado="ok";
            }catch(SQLException e){
                System.out.println(e);
                resultado="error";
            }
        } else
            resultado = "error";
        return resultado;
    }

    public String desactivarUsuario(String documento) {
        Connection connection=null;
        Conexion miConexion=this.miCoordinador.getConexion();
        connection=miConexion.getConnection();

        String resp="";
        try {
            String sentencia="UPDATE usuario SET estado=0 WHERE documento= ? ";

            PreparedStatement statement = connection.prepareStatement(sentencia);
            statement.setString(1, documento);

            statement.executeUpdate();

            resp="ok";
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            resp="error";
        }
        return resp;
    }
    
    public String activarUsuario(String documento) {
        Connection connection=null;
        Conexion miConexion=this.miCoordinador.getConexion();
        connection=miConexion.getConnection();

        String resp="";
        try {
            String sentencia="UPDATE usuario SET estado=1 WHERE documento= ? ";

            PreparedStatement statement = connection.prepareStatement(sentencia);
            statement.setString(1, documento);

            statement.executeUpdate();

            resp="ok";
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            resp="error";
        }
        return resp;
    }
}
