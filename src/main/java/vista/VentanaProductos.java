package vista;

import controlador.Coordinador;
import modelo.dao.ProductoDao;
import modelo.vo.ProductoVo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JOptionPane;

public class VentanaProductos extends JDialog  implements ActionListener{
    private Coordinador miCoordinador;
    
    private JPanel panelProductos;
    private JLabel lblTitulo;
    private JLabel lblIngresoNombre;
    private JLabel lblIngresoID;
    private JLabel lblPrecio;
    private JLabel lblCantidad;
    private JLabel lblPrecioValue;
    private JLabel lblCantidadValue;
    private JTextField campoID;
    private JTextField campoNombre;
    private JButton btnConsultar;
    private JButton btnComprar;
    private JButton btnCarrito;
    private JButton btnProductos;
    private JSeparator separadorSuperior;
    private JSeparator separadorInferior;
    
    public VentanaProductos(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(710,320);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        panelProductos = new JPanel();
        lblTitulo = new JLabel();
        lblIngresoNombre = new JLabel();
        lblIngresoID = new JLabel();
        lblPrecio = new JLabel();
        lblCantidad = new JLabel();
        lblPrecioValue = new JLabel();
        lblCantidadValue = new JLabel();
        campoID = new JTextField();
        campoNombre = new JTextField();
        btnConsultar = new JButton();
        btnComprar = new JButton();
        btnCarrito = new JButton();
        btnProductos = new JButton();
        separadorSuperior = new JSeparator();
        separadorInferior = new JSeparator();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        panelProductos.setBackground(new java.awt.Color(204, 204, 204));
        panelProductos.setLayout(null);
        
        lblTitulo.setFont(new Font("Tempus Sans ITC", 1, 36));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Compra y consulta de productos");
        lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelProductos.add(lblTitulo);
        lblTitulo.setBounds(20, 10, 660, 60);
        
        lblIngresoNombre.setFont(new Font("Verdana", 1, 14));
        lblIngresoNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIngresoNombre.setText("Ingresar el nombre del producto");
        panelProductos.add(lblIngresoNombre);
        lblIngresoNombre.setBounds(0, 90, 300, 20);
        
        lblIngresoID.setFont(new Font("Verdana", 1, 14));
        lblIngresoID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIngresoID.setText("Ingresar el ID del producto");
        panelProductos.add(lblIngresoID);
        lblIngresoID.setBounds(0, 120, 300, 20);
        
        lblPrecio.setFont(new Font("Verdana", 1, 14));
        lblPrecio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPrecio.setText("Precio: ");
        panelProductos.add(lblPrecio);
        lblPrecio.setBounds(0, 160, 200, 20);
        
        lblCantidad.setFont(new Font("Verdana", 1, 14));
        lblCantidad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCantidad.setText("Cantidad: ");
        panelProductos.add(lblCantidad);
        lblCantidad.setBounds(0, 190, 200, 20);
        
        lblCantidadValue.setFont(new Font("Verdana", 0, 14));
        lblCantidadValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCantidadValue.setText("0 unidades");
        panelProductos.add(lblCantidadValue);
        lblCantidadValue.setBounds(200, 190, 200, 20);
        
        lblPrecioValue.setFont(new Font("Verdana", 0, 14));
        lblPrecioValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPrecioValue.setText("0$");
        panelProductos.add(lblPrecioValue);
        lblPrecioValue.setBounds(200, 160, 200, 20);
        
        panelProductos.add(separadorSuperior);
        separadorSuperior.setBounds(20, 150, 660, 10);
        panelProductos.add(separadorInferior);
        separadorInferior.setBounds(20, 225, 660, 10);
        panelProductos.add(campoNombre);
        campoNombre.setBounds(310, 90, 200, 20);
        panelProductos.add(campoID);
        campoID.setBounds(310, 120, 200, 20);
        
        btnConsultar.setFont(new Font("Verdana", 0, 14));
        btnConsultar.setText("Consultar");
        panelProductos.add(btnConsultar);
        btnConsultar.setBounds(530, 92, 140, 45);
        btnConsultar.addActionListener(this);
        
        btnComprar.setFont(new Font("Verdana", 0, 14));
        btnComprar.setText("Comprar");
        panelProductos.add(btnComprar);
        btnComprar.setBounds(420, 167, 140, 40);
        btnComprar.addActionListener(this);
        
        btnCarrito.setFont(new Font("Verdana", 0, 14));
        btnCarrito.setText("Ver carrito");
        panelProductos.add(btnCarrito);
        btnCarrito.setBounds(20, 240, 140, 30);
        btnCarrito.addActionListener(this);
        
        btnProductos.setFont(new Font("Verdana", 0, 14));
        btnProductos.setText("Ver lista de productos");
        panelProductos.add(btnProductos);
        btnProductos.setBounds(480, 240, 200, 30);
        btnProductos.addActionListener(this);
        
        getContentPane().add(panelProductos);
        panelProductos.setBounds(0, 0, 300, 350);
        pack();
    }
    
    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador=miCoordinador;
    }
    
    private void consultar () {
        String id = this.campoID.getText();
        String nombre = this.campoNombre.getText();
        ProductoVo miProductoVo = null;
        if (id.length() > 0) {
            miProductoVo = miCoordinador.consultarProducto(id);
        } else if (nombre.length() > 0) {
            miProductoVo = miCoordinador.consultarProductoPorNombre(nombre);
        } else {
            JOptionPane.showMessageDialog(null, "Se debe ingresar el ID o nombre del producto");
        }
        
        if (miProductoVo != null) {
            this.campoID.setText(miProductoVo.getIdProducto());
            this.campoNombre.setText(miProductoVo.getNombre());
            this.lblCantidadValue.setText(miProductoVo.getCantidad()+" unidades");
            this.lblPrecioValue.setText(miProductoVo.getPrecio()+"$");
        } else if (id.length() > 0 || nombre.length() > 0) {
            JOptionPane.showMessageDialog(null, "No se pudo encontrar el producto");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnConsultar) {
            consultar();
        }
        
        if (e.getSource() == this.btnComprar) {
        
        }
    }
}