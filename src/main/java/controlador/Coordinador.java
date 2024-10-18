package controlador;

import java.util.ArrayList;

import modelo.Carrito;
import modelo.Logica;
import modelo.dao.UsuarioDao;
import modelo.dao.ProductoDao;
import modelo.vo.UsuarioVo;
import modelo.vo.ProductoVo;
import modelo.conexion.Conexion;
import vista.VentanaConsultaIndividual;
import vista.VentanaLogin;
import vista.VentanaPrincipal;
import vista.VentanaRegistro;
import vista.VentanaProductos;
import vista.VentanaRegistroProductos;
import vista.VentanaCarrito;
import vista.VentanaListaProductos;
import vista.VentanaListaUsuarios;

public class Coordinador {
    private VentanaPrincipal miVentana;
    private VentanaLogin miLogin;
    private Logica miLogica;
    private VentanaRegistro miVentanaRegistro;
    private VentanaConsultaIndividual miVentanaConsultaIndividual;
    private UsuarioDao miUsuarioDao;
    private ProductoDao miProductoDao;
    private Conexion miConexion;
    private VentanaProductos miVentanaProductos;
    private VentanaRegistroProductos miVentanaRegistroProductos;
    private VentanaListaUsuarios miVentanaListaUsuarios;
    private Carrito miCarrito;
    private UsuarioVo usuario;
    private VentanaCarrito miVentanaCarrito;
    private VentanaListaProductos miVentanaListaProductos;
    
    public void setVentanaListaProductos(VentanaListaProductos miVentanaListaProductos) {
        this.miVentanaListaProductos = miVentanaListaProductos;
    }
   
    public void setVentanaListaUsuarios(VentanaListaUsuarios miVentanaListaUsuarios) {
        this.miVentanaListaUsuarios = miVentanaListaUsuarios;
    }
    
    public UsuarioVo getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(UsuarioVo usuario) {
        this.usuario = usuario;
    }

    public Carrito getCarrito() {
        return miCarrito;
    }

    public void setCarrito(Carrito miCarrito) {
        this.miCarrito = miCarrito;
    }

    public void setConexion (Conexion miConexion) {
        this.miConexion = miConexion;
    }
    
    public ArrayList<ProductoVo> listarProductos() {
        return this.miProductoDao.listar();
    }
    
    public ArrayList<String> listarCarrito() {
        return this.miCarrito.listar(this.usuario.getDocumento());
    }
    
    public ArrayList<UsuarioVo> listarUsusarios() {
        return this.miUsuarioDao.listar();
    }

    public Conexion getConexion () {
        return this.miConexion;
    }
    
    public void setVentanaCarrito(VentanaCarrito miVentanaCarrito) {
        this.miVentanaCarrito = miVentanaCarrito;
    }

    public void setVentanaProductos(VentanaProductos miVentanaProductos) {
        this.miVentanaProductos = miVentanaProductos;
    }

    public void setVentanaRegistroProductos(VentanaRegistroProductos miVentanaRegistroProductos) {
        this.miVentanaRegistroProductos = miVentanaRegistroProductos;
    }
    
    public void mostrarVentanaListaProductos () {
        this.miVentanaListaProductos.refrescar();
        this.miVentanaListaProductos.setVisible(true);
    }
    
    public void MostrarVentanaListaUsuarios () {
        this.miVentanaListaUsuarios.refrescar();
        this.miVentanaListaUsuarios.setVisible(true);
    }
    
    public void mostrarVentanaCarrito() {
        this.miVentanaCarrito.refrescar();
        this.miVentanaCarrito.setVisible(true);
    }

    public void mostrarVentanaRegistroProductos() {
        this.miVentanaRegistroProductos.setVisible(true);
    }

    public void mostrarVentanaProductos() {
        this.miVentanaProductos.setVisible(true);
    }

    public void setVentanaPrincipal(VentanaPrincipal miVentana) {
        this.miVentana=miVentana;
    }

    public void setVentanaLogin(VentanaLogin miLogin) {
        this.miLogin=miLogin;
    }

    public void setLogica(Logica miLogica) {
        this.miLogica=miLogica;
    }
    
    public boolean comprar (String id) {
        return this.miCarrito.comprar(id, this.usuario.getDocumento());
    }

    public String validarIngreso(int index, String pass) {		
        return miLogica.validarIngreso(index,pass);
    }

    public void cerrarVentanaLogin() {
        miLogin.limpiar();
        miLogin.dispose();
    }

    public void asignarPrivilegios() {
        miVentana.asignarPrivilegios(this.usuario.getTipo(), this.usuario.getNombre());
        miVentanaRegistro.asignarPrivilegios(this.usuario.getTipo());
        miVentanaConsultaIndividual.asignarPrivilegios(this.usuario.getTipo(), this.usuario.getDocumento());
    }

    public void mostrarLogin() {
        miLogin.limpiar();
        miLogin.setVisible(true);
    }

    public void setVentanaRegistro(VentanaRegistro miVentanaRegistro) {
        this.miVentanaRegistro=miVentanaRegistro;
    }

    public void setVentanaConsultaIndividual(VentanaConsultaIndividual miVentanaConsultaIndividual) {
        this.miVentanaConsultaIndividual=miVentanaConsultaIndividual;
    }

    public void mostrarVentanaRegistro() {
        miVentanaRegistro.setVisible(true);
    }

    public void mostrarVentanaConsulta() {
        miVentanaConsultaIndividual.setVisible(true);
    }

    public void setUsuarioDao(UsuarioDao miUsuarioDao) {
        this.miUsuarioDao=miUsuarioDao;
    }

    public void setProductoDao(ProductoDao miProductoDao) {
        this.miProductoDao=miProductoDao;
    }

    public String registrarUsuario(UsuarioVo miUsuarioVo) {
        return miUsuarioDao.registrarUsuario(miUsuarioVo);
    }

    public boolean registrarProducto(ProductoVo miProductoVo) {
        return miProductoDao.registrarProducto(miProductoVo);
    }

    public boolean validarCampos(UsuarioVo miUsuarioVo) {
        return miLogica.validarCampos(miUsuarioVo);
    }

    public boolean validarCamposProducto(ProductoVo miProductoVo) {
        return miLogica.validarCamposProducto(miProductoVo);
    }

    public boolean validarTipoUsuario(int tipoUsuario) {
        return miLogica.validarTipoUsuario(tipoUsuario);
    }

    public Integer validarEdad(String edadIngresada) {
        // TODO Auto-generated method stub
        return miLogica.validarEdad(edadIngresada);
    }

    public UsuarioVo consultarUsuario(String doc) {	
        return miUsuarioDao.consultarUsuario(doc);
    }

    public UsuarioVo consultarUsuario(String doc, int per) {	
        return miUsuarioDao.consultarUsuario(doc, per);
    }

    public ProductoVo consultarProducto(String id) {
        return miProductoDao.consultarProducto(id);
    }
    
    public ProductoVo consultarProductoPorNombre(String name) {
        return miProductoDao.consultarProductoPorNombre(name);
    }

    public String actualizaUsuario(UsuarioVo miUsuarioVo) {
        return miUsuarioDao.actualizaUsuario(miUsuarioVo);
    }

    public boolean actualizarProducto(ProductoVo miProductoVo) {
        return miProductoDao.actualizarProducto(miProductoVo);
    }

    public boolean eliminarProducto(String id) {
        return miProductoDao.eliminarProducto(id);
    }

    public String desactivarUsuario(String documento) {
        return miUsuarioDao.desactivarUsuario(documento);
    }

    public String activarUsuario(String documento) {
        return miUsuarioDao.activarUsuario(documento);
    }
}