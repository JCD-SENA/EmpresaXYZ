package modelo;

import javax.swing.JOptionPane;

import modelo.vo.UsuarioVo;
import modelo.vo.ProductoVo;
import controlador.Coordinador;

public class Logica {
	
    final int SELECCION=0;
    final int ADMINISTRADOR=1;
    final int USUARIO=2;
    final int SECRETARIA=3;
	
    private Coordinador miCoordinador;
     // End of variables declaration               

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador=miCoordinador;
    }

    public String validarIngreso(int index, String pass){
        String retorno="";
        if (index==SELECCION) {//seleccion es 1
            retorno="error";
        }else{
            retorno=validarPass(index, pass);
        }
        return retorno;
    }

    private String validarPass(int index, String pass) {
        UsuarioVo miUsuarioVo=miCoordinador.consultarUsuario(pass);
        String retorno="";

        if (miUsuarioVo!=null) {
            if ( (index==ADMINISTRADOR && index==miUsuarioVo.getTipo() )|| (index==USUARIO && index==miUsuarioVo.getTipo() ) || (index==SECRETARIA && index==miUsuarioVo.getTipo() )) {
                if (pass.equals(miUsuarioVo.getDocumento())) {
                    if (miUsuarioVo.getEstado() == 1)
                        retorno=miUsuarioVo.getNombre();
                    else
                        retorno="desactivado";
                }else{
                    retorno="invalido";
                }
            }else{
                retorno="invalido";
            }
        }else{
            retorno="desconectado";
        }
        return retorno;
    }
    
    public boolean validarCamposProducto(ProductoVo miProductoVo) {
        boolean validarNombre = false;
        boolean validarID = false;
        boolean validarPrecio = false;
        boolean validarCantidad = false;
        
        try {
            String id = miProductoVo.getIdProducto();
            String nombre = miProductoVo.getNombre();
            int cantidad = miProductoVo.getCantidad();
            int precio = miProductoVo.getPrecio();
            
            validarID = (id!=null && !id.equals(""));
            validarNombre = (nombre!=null && !nombre.equals(""));
            validarPrecio = (precio >= 0);
            validarCantidad = (cantidad >= 0);
        } catch(Exception e) {
            return false;
        }
        
        return (validarID && validarNombre && validarPrecio && validarCantidad);
    }

    public boolean validarCampos(UsuarioVo miUsuarioVo) {
        boolean validarNombre=false;
        boolean validarDocumento=false;

        String documento=miUsuarioVo.getDocumento();
        String nombre=miUsuarioVo.getNombre();

        validarDocumento= (documento!=null && !documento.equals(""));
        validarNombre= (nombre!=null && !nombre.equals(""));

        return (validarDocumento==true && validarNombre==true);
    }

    public Integer validarEdad(String edadIngresada) {
        Integer edad=null;
        try {
            edad=Integer.parseInt(edadIngresada);
        } catch (Exception e) {
            edad=null;
        }			
        return edad;
    }
    
    public boolean validarTipoUsuario(int tipoUsuario) {
        return (tipoUsuario >= 1 && tipoUsuario <= 3);
    }
}
