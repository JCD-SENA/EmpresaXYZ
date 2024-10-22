package vista;

import java.util.ArrayList;
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
import javax.swing.JOptionPane;

public class VentanaCarrito extends JDialog implements ActionListener {
    private Coordinador miCoordinador;

    private JPanel panelCarrito;
    private JLabel lblTitulo;
    private JButton btnRefrescar;
    private JList<String> listaCompras;  // Especificamos el tipo JList<String>
    private JScrollPane escroleable;
    private DefaultListModel<String> modelo;  // Especificamos el tipo DefaultListModel<String>

    public VentanaCarrito(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(500, 320);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        panelCarrito = new JPanel();
        lblTitulo = new JLabel();
        btnRefrescar = new JButton();
        listaCompras = new JList<>();
        escroleable = new JScrollPane();
        modelo = new DefaultListModel<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        panelCarrito.setBackground(new java.awt.Color(204, 204, 204));
        panelCarrito.setLayout(null);

        btnRefrescar.setFont(new Font("Verdana", 0, 14));
        btnRefrescar.setText("Refrescar");
        panelCarrito.add(btnRefrescar);
        btnRefrescar.setBounds(170, 80, 150, 25);
        btnRefrescar.addActionListener(this);  // Mantenemos la implementación de ActionListener

        lblTitulo.setFont(new Font("Tempus Sans ITC", 1, 36));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Carrito");
        lblTitulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelCarrito.add(lblTitulo);
        lblTitulo.setBounds(20, 10, 440, 60);

        escroleable.setViewportView(listaCompras);
        listaCompras.setModel(modelo);
        panelCarrito.add(escroleable);
        escroleable.setBounds(20, 115, 440, 130);

        getContentPane().add(panelCarrito);
        panelCarrito.setBounds(0, 0, 500, 350);
        pack();
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador = miCoordinador;
    }

    public void refrescar() {
        ArrayList<String> compras = this.miCoordinador.listarCarrito();
        if (compras != null) {
            this.modelo.clear();  
            if (compras.size() > 0) {
                for (String producto : compras) {
                    this.modelo.addElement(producto);
                }
            } else {
                this.modelo.addElement("No hay productos en el carrito");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al cargar la lista de productos comprados", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRefrescar) {
            refrescar(); 
        }
    }
}
