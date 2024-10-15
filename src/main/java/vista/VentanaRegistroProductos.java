package vista;

import controlador.Coordinador;
import modelo.dao.ProductoDao;
import modelo.vo.ProductoVo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class VentanaRegistroProductos extends JDialog  implements ActionListener{
    private Coordinador miCoordinador;
    
    private JPanel panelRegistroProducto;
    private JLabel tituloRegistro;
    private JLabel labelTexto;
    private JLabel labelID;
    private JLabel labelNombre;
    private JLabel labelPrecio;
    private JLabel labelCantidad;
    private JTextField campoID;
    private JTextField campoNombre;
    private JSpinner spinnerPrecio;
    private JSpinner spinnerCantidad;
    private JSeparator separadorSuperior;
    private JSeparator separadorInferior;
    private JButton btnConsultar;
    private JButton btnRegistrar;
    private JButton btnEliminar;
    private JButton btnActualizar;
    private JButton btnCancelar;
    
    public VentanaRegistroProductos(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(705,300);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        panelRegistroProducto = new JPanel();
        tituloRegistro = new JLabel();
        labelTexto = new JLabel();
        labelID = new JLabel();
        labelNombre = new JLabel();
        labelPrecio = new JLabel();
        labelCantidad = new JLabel();
        campoID = new JTextField();
        campoNombre = new JTextField();
        spinnerPrecio = new JSpinner();
        spinnerCantidad = new JSpinner();
        btnConsultar = new JButton();
        btnCancelar = new JButton();
        btnEliminar = new JButton();
        btnActualizar = new JButton();
        btnRegistrar = new JButton();
        separadorSuperior = new JSeparator();
        separadorInferior = new JSeparator();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
       // getContentPane().setLayout(null);

        panelRegistroProducto.setBackground(new java.awt.Color(204, 204, 204));
        panelRegistroProducto.setLayout(null);

        tituloRegistro.setFont(new java.awt.Font("Tempus Sans ITC", 1, 36)); // NOI18N
        tituloRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloRegistro.setText("Registro de productos");
        tituloRegistro.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelRegistroProducto.add(tituloRegistro);
        tituloRegistro.setBounds(20, 10, 660, 60);
        
        labelTexto.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        labelTexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTexto.setText("Diligencie el Formulario para registrar o consultar un producto en el sistema");
        panelRegistroProducto.add(labelTexto);
        labelTexto.setBounds(0, 90, 705, 20);
        
        labelID.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        labelID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelID.setText("*ID:");
        panelRegistroProducto.add(labelID);
        labelID.setBounds(360, 140, 100, 20);
        
        labelNombre.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        labelNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelNombre.setText("*Nombre:");
        panelRegistroProducto.add(labelNombre);
        labelNombre.setBounds(0, 140, 100, 20);
        
        labelPrecio.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        labelPrecio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelPrecio.setText("Precio:");
        panelRegistroProducto.add(labelPrecio);
        labelPrecio.setBounds(0, 170, 100, 20);
        
        labelCantidad.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        labelCantidad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelCantidad.setText("Cantidad:");
        panelRegistroProducto.add(labelCantidad);
        labelCantidad.setBounds(360, 170, 100, 20);
        
        btnConsultar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnConsultar.setText("Buscar");
        btnConsultar.addActionListener(this);
        panelRegistroProducto.add(btnConsultar);
        btnConsultar.setBounds(600, 140, 82, 20);   
        
        panelRegistroProducto.add(campoID);
        campoID.setBounds(470, 140, 120, 20);
        panelRegistroProducto.add(campoNombre);
        campoNombre.setBounds(110, 140, 210, 20);
        panelRegistroProducto.add(spinnerPrecio);
        spinnerPrecio.setBounds(110, 170, 210, 20);
        panelRegistroProducto.add(spinnerCantidad);
        spinnerCantidad.setBounds(470, 170, 210, 20);
        panelRegistroProducto.add(separadorSuperior);
        separadorSuperior.setBounds(20, 120, 660, 10);
        panelRegistroProducto.add(separadorInferior);
        separadorInferior.setBounds(20, 210, 660, 10);
        
        btnCancelar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        panelRegistroProducto.add(btnCancelar);
        btnCancelar.setBounds(570, 220, 110, 30);
        btnCancelar.addActionListener(this);
        
        btnEliminar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        panelRegistroProducto.add(btnEliminar);
        btnEliminar.setBounds(450, 220, 110, 30);
        btnEliminar.addActionListener(this);
        
        btnActualizar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnActualizar.setText("Actualizar");
        panelRegistroProducto.add(btnActualizar);
        btnActualizar.setBounds(330, 220, 110, 30);
        btnActualizar.addActionListener(this);
        
        btnRegistrar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        btnRegistrar.setText("Registrar");
        panelRegistroProducto.add(btnRegistrar);
        btnRegistrar.setBounds(210, 220, 110, 30);
        btnRegistrar.addActionListener(this);
        
        getContentPane().add(panelRegistroProducto);
        panelRegistroProducto.setBounds(0, 0, 705, 350);
        pack();
    }
    
    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador=miCoordinador;
    }
    
    public void limpiarVentana() {
        this.campoID.setText("");
        this.campoNombre.setText("");
        this.spinnerCantidad.setValue(0);
        this.spinnerPrecio.setValue(0);
    }
    
    
    private void registrar() {
        ProductoVo miProductoVo = new ProductoVo();
        miProductoVo.setIdProducto(this.campoID.getText());
        miProductoVo.setNombre(this.campoNombre.getText());
        miProductoVo.setCantidad((int)this.spinnerCantidad.getValue());
        miProductoVo.setPrecio((int)this.spinnerPrecio.getValue());
        if (miCoordinador.validarCamposProducto(miProductoVo)) {
            if (miCoordinador.registrarProducto(miProductoVo)) {
                JOptionPane.showMessageDialog(null, "Se ha registrado el producto con exito");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo registrar el producto","Error",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Uno de los valores no es valido","Advertencia",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void eliminaProducto() {
        String id=campoID.getText().trim();
        ProductoVo miProductoVo=miCoordinador.consultarProducto(id);
        if (miProductoVo != null) {
            if (JOptionPane.showConfirmDialog(null,"Esta seguro de eliminar el producto "+this.campoNombre.getText()+"?") == JOptionPane.OK_OPTION) {
                if (this.miCoordinador.eliminarProducto(id)) {
                    JOptionPane.showMessageDialog(null, "Se ha eliminado el producto con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar el producto","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe el producto","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void consultarProducto() {
        String id=campoID.getText().trim();
        ProductoVo miProductoVo=miCoordinador.consultarProducto(id);
        if (miProductoVo != null) {
            this.campoNombre.setText(miProductoVo.getNombre());
            this.spinnerCantidad.setValue(miProductoVo.getCantidad());
            this.spinnerPrecio.setValue(miProductoVo.getPrecio());
        } else {
            JOptionPane.showMessageDialog(null, "No existe el producto","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarProducto() {
        String id=campoID.getText().trim();
        ProductoVo miProductoVo=miCoordinador.consultarProducto(id);
        if (miProductoVo != null) {
            miProductoVo.setIdProducto(this.campoID.getText());
            miProductoVo.setNombre(this.campoNombre.getText());
            miProductoVo.setCantidad((int)this.spinnerCantidad.getValue());
            miProductoVo.setPrecio((int)this.spinnerPrecio.getValue());
            if (miCoordinador.validarCamposProducto(miProductoVo)) {
                if (miCoordinador.actualizarProducto(miProductoVo)) {
                    JOptionPane.showMessageDialog(null, "Se ha actualizado el producto con exito");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el producto","Error",JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Uno de los valores no es valido","Advertencia",JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe el producto","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancelar) {
            limpiarVentana();
            dispose();
        }
        
        if (e.getSource() == this.btnRegistrar) {
            registrar();
            limpiarVentana();
        }
        
        if (e.getSource() == this.btnEliminar) {
            eliminaProducto();
            limpiarVentana();
        }
        
        if (e.getSource() == this.btnActualizar) {
            actualizarProducto();
        }
        
        if (e.getSource() == this.btnConsultar) {
            consultarProducto();
        }
    }
}