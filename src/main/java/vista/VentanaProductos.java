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
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class VentanaProductos extends JDialog  implements ActionListener{
    private Coordinador miCoordinador;
    
    private JPanel panelProductos;
    private JLabel lblTitulo;
    private JLabel lblIngresoNombre;
    private JLabel lblIngresoID;
    
    public VentanaProductos(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(705,350);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        panelProductos = new JPanel();
        lblTitulo = new JLabel();
        lblIngresoNombre = new JLabel();
        lblIngresoID = new JLabel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        panelProductos.setBackground(new java.awt.Color(204, 204, 204));
        panelProductos.setLayout(null);
        
        lblTitulo.setFont(new java.awt.Font("Tempus Sans ITC", 1, 36)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Compra y consulta de productos");
        lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelProductos.add(lblTitulo);
        lblTitulo.setBounds(20, 10, 660, 60);
        
        lblIngresoNombre.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblIngresoNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIngresoNombre.setText("Ingresar el nombre del producto");
        panelProductos.add(lblIngresoNombre);
        lblIngresoNombre.setBounds(0, 90, 300, 20);
        
        lblIngresoID.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblIngresoID.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIngresoID.setText("Ingresar el ID del producto");
        panelProductos.add(lblIngresoID);
        lblIngresoID.setBounds(0, 120, 300, 20);
        
        getContentPane().add(panelProductos);
        panelProductos.setBounds(0, 0, 300, 350);
        pack();
    }
    
    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador=miCoordinador;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
    }
}