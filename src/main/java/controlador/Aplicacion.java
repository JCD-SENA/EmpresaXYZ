package controlador;

import modelo.Logica;
import modelo.dao.UsuarioDao;
import modelo.dao.ProductoDao;
import modelo.conexion.Conexion;
import vista.VentanaConsultaIndividual;
import vista.VentanaLogin;
import vista.VentanaPrincipal;
import vista.VentanaRegistro;
import vista.VentanaProductos;
import vista.VentanaRegistroProductos;

public class Aplicacion {
    public void iniciarSistema(Conexion miConexion){
        //Instanciamos las clases unicas
        VentanaPrincipal miVentana=new VentanaPrincipal();
        VentanaLogin miLogin=new VentanaLogin(miVentana, true);
        Coordinador miCoordinador=new Coordinador();
        Logica miLogica=new Logica();
        VentanaRegistro miVentanaRegistro=new VentanaRegistro(miVentana, true);
        VentanaConsultaIndividual miVentanaConsultaIndividual=new VentanaConsultaIndividual(miVentana, true);
        VentanaProductos miVentanaProductos = new VentanaProductos(miVentana, true);
        VentanaRegistroProductos miVentanaRegistroProductos = new VentanaRegistroProductos(miVentana, true);
        UsuarioDao miUsuarioDao=new UsuarioDao();
        ProductoDao miProductoDao=new ProductoDao();
        Carrito miCarrito = new Carrito();

        //Relacionamos las clases con el coordinador				
        miVentana.setCoordinador(miCoordinador);
        miLogin.setCoordinador(miCoordinador);
        miLogica.setCoordinador(miCoordinador);
        miVentanaRegistro.setCoordinador(miCoordinador);
        miVentanaConsultaIndividual.setCoordinador(miCoordinador);
        miUsuarioDao.setCoordinador(miCoordinador);
        miProductoDao.setCoordinador(miCoordinador);
        miVentanaProductos.setCoordinador(miCoordinador);
        miVentanaRegistroProductos.setCoordinador(miCoordinador);
        
        //Relacionamos el Coordinador con las Clases
        miCoordinador.setVentanaPrincipal(miVentana);
        miCoordinador.setVentanaLogin(miLogin);
        miCoordinador.setLogica(miLogica);
        miCoordinador.setVentanaRegistro(miVentanaRegistro);
        miCoordinador.setVentanaConsultaIndividual(miVentanaConsultaIndividual);
        miCoordinador.setUsuarioDao(miUsuarioDao);
        miCoordinador.setProductoDao(miProductoDao);
        miCoordinador.setConexion(miConexion);
        miCoordinador.setVentanaProductos(miVentanaProductos);
        miCoordinador.setVentanaRegistroProductos(miVentanaRegistroProductos);

        miVentana.setVisible(true);
        miLogin.setVisible(true);
    }
}
