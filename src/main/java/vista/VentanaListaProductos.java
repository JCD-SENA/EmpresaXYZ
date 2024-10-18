package vista;

import controlador.Coordinador;
import modelo.vo.ProductoVo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Frame;

import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VentanaListaProductos extends JDialog  implements ActionListener{
    private Coordinador miCoordinador;
    
    private JPanel panelLista;
    private JLabel lblTitulo;
    private JButton btnRefrescar;
    private JTable listaProductos;
    private JScrollPane escroleable;
    private DefaultTableModel modelo;
    
    public VentanaListaProductos(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(500,320);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
        panelLista = new JPanel();
        lblTitulo = new JLabel();
        btnRefrescar = new JButton();
        escroleable = new JScrollPane();

        String[] header = {"ID", "Nombre", "Precio", "Cantidad"};
        modelo = new DefaultTableModel(null, header);
        listaProductos = new JTable(modelo);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        panelLista.setBackground(new java.awt.Color(204, 204, 204));
        panelLista.setLayout(null);
        
        btnRefrescar.setFont(new Font("Verdana", 0, 14));
        btnRefrescar.setText("Refrescar");
        panelLista.add(btnRefrescar);
        btnRefrescar.setBounds(170, 80, 150, 25);
        btnRefrescar.addActionListener(this);
        
        lblTitulo.setFont(new Font("Tempus Sans ITC", 1, 36));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Lista de productos");
        lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelLista.add(lblTitulo);
        lblTitulo.setBounds(20, 10, 440, 60);

        escroleable.setViewportView(listaProductos);
        panelLista.add(escroleable);
        escroleable.setBounds(20, 115, 440, 130);
        
        getContentPane().add(panelLista);
        panelLista.setBounds(0, 0, 500, 350);
        pack();
    }
    
    public void refrescar() {
        ArrayList<ProductoVo> productos = this.miCoordinador.listarProductos();
        this.modelo.setRowCount(0);
        if (productos!=null) {
            if (productos.size() > 0) {
                for (ProductoVo producto : productos) {
                    String[] info = {producto.getIdProducto(), producto.getNombre(), Integer.toString(producto.getPrecio()), Integer.toString(producto.getCantidad())};
                    this.modelo.addRow(info);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al cargar la lista de productos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador=miCoordinador;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRefrescar) {
            refrescar();
        }
    }
}