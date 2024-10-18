package vista;

import controlador.Coordinador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;

public class VentanaCarrito extends JDialog  implements ActionListener{
    private Coordinador miCoordinador;
    
    private JPanel panelCarrito;
    private JLabel lblTitulo;
    private JLabel lblTotal;
    private JLabel lblTotalValor;
    private JButton btnRefrescar;
    private JList listaCompras;
    private JScrollPane escroleable;
    private DefaultListModel modelo;
    
    public VentanaCarrito(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(500,320);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        panelCarrito = new JPanel();
        lblTitulo = new JLabel();
        lblTotal = new JLabel();
        lblTotalValor = new JLabel();
        btnRefrescar = new JButton();
        listaCompras = new JList();
        escroleable = new JScrollPane();
        modelo = new DefaultListModel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        panelCarrito.setBackground(new java.awt.Color(204, 204, 204));
        panelCarrito.setLayout(null);
        
        btnRefrescar.setFont(new Font("Verdana", 0, 14));
        btnRefrescar.setText("Refrescar");
        panelCarrito.add(btnRefrescar);
        btnRefrescar.setBounds(170, 80, 150, 25);
        btnRefrescar.addActionListener(this);
        
        lblTitulo.setFont(new Font("Tempus Sans ITC", 1, 36));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Carrito");
        lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelCarrito.add(lblTitulo);
        lblTitulo.setBounds(20, 10, 440, 60);

        escroleable.setViewportView(listaCompras);
        listaCompras.setLayoutOrientation(JList.VERTICAL);
        listaCompras.setModel(modelo);
        panelCarrito.add(escroleable);
        escroleable.setBounds(20, 115, 440, 130);
        
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setFont(new Font("Verdana", 1, 14));
        lblTotal.setText("Precio total: ");
        panelCarrito.add(lblTotal);
        lblTotal.setBounds(20, 250, 260, 20);
        
        lblTotalValor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotalValor.setFont(new Font("Verdana", 0, 14));
        lblTotalValor.setText("0$");
        panelCarrito.add(lblTotalValor);
        lblTotalValor.setBounds(280, 250, 200, 20);
        
        getContentPane().add(panelCarrito);
        panelCarrito.setBounds(0, 0, 500, 350);
        pack();
    }
    
    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador=miCoordinador;
    }
    
    public void refrescar() {
        this.modelo.clear();
        this.modelo.addElement("No hay productos comprados");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
    }
}
