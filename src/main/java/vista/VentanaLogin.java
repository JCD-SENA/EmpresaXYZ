package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import controlador.Coordinador;

/**
 *
 * @author User
 */
public class VentanaLogin extends JDialog implements ActionListener, WindowListener{

    // Variables declaration - do not modify                     
    private javax.swing.JButton botonAceptar;
    private javax.swing.JPasswordField campoPass;
    private javax.swing.JComboBox comboUsuarios;
    private javax.swing.JLabel imagen;
    private javax.swing.JLabel labelPass;
    private javax.swing.JLabel labelUser;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JLabel tituloLogin;
    
    private Coordinador miCoordinador;
    
    private int intentos = 0;
    // End of variables declaration  
	
    /**
     * Creates new form VentanaLogin
     */
    public VentanaLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Login");
        setSize(275,330);
        setLocationRelativeTo(null);
        setResizable(false);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    public void cerrarTodo() {
        Object[] options = {"Continuar", "Cerrar"};
        int n = JOptionPane.showOptionDialog(null,"Seleccione un tipo de Usuario.\nSi sale el sistema se Cerrara","Confirmaci�n",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null,options,options[0]);

        if (n == JOptionPane.YES_OPTION){
            // *no hace nada*
        } else if (n == JOptionPane.NO_OPTION) {
            this.miCoordinador.getConexion();
            System.exit(0);
        }
    }
    
    @Override
    public void windowClosed(WindowEvent e) {
        //cerrarTodo();
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
        cerrarTodo();
    }

    private void initComponents() {

        panelLogin = new javax.swing.JPanel();
        tituloLogin = new javax.swing.JLabel();
        imagen = new javax.swing.JLabel();
        labelUser = new javax.swing.JLabel();
        labelPass = new javax.swing.JLabel();
        botonAceptar = new javax.swing.JButton();
        comboUsuarios = new javax.swing.JComboBox();
        campoPass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        this.addWindowListener(this);

        panelLogin.setBackground(new java.awt.Color(204, 204, 204));
        panelLogin.setLayout(null);

        tituloLogin.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        tituloLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloLogin.setText("Ventana Login");
        tituloLogin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelLogin.add(tituloLogin);
        tituloLogin.setBounds(5, 5, 250, 60);

        imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/candado.png"))); // NOI18N
        imagen.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        panelLogin.add(imagen);
        imagen.setBounds(5, 75, 250, 100);

        labelUser.setText("Usuario");
        panelLogin.add(labelUser);
        labelUser.setBounds(5, 190, 70, 20);

        labelPass.setText("Password");
        panelLogin.add(labelPass);
        labelPass.setBounds(5, 223, 70, 14);
        labelPass.setVisible(false);

        botonAceptar.setText("Aceptar");
        panelLogin.add(botonAceptar);
        botonAceptar.setBounds(75, 250, 110, 30);
        botonAceptar.addActionListener(this);

        comboUsuarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Administrador", "Usuario", "Secretaria"}));
        panelLogin.add(comboUsuarios);
        comboUsuarios.setBounds(65, 190, 190, 20);
        comboUsuarios.addActionListener(this);
        
        panelLogin.add(campoPass);
        campoPass.setBounds(65, 220, 190, 20);
        campoPass.setVisible(false);

        getContentPane().add(panelLogin);
        panelLogin.setBounds(0, 0, 270, 300);

        pack();
    }

    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador=miCoordinador;
    }
    
    private void reinicioContador() {
        this.intentos = 0;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource()==comboUsuarios) {
            mostrarElementos();
        }

        if (evento.getSource()==botonAceptar) {
            String resp=miCoordinador.validarIngreso(comboUsuarios.getSelectedIndex(),campoPass.getText());
            //System.out.println(comboUsuarios.getSelectedIndex());
            if (resp.equals("error")) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado un usuario","Advertencia",JOptionPane.WARNING_MESSAGE);
                this.intentos += 1;
            }else{
                if (resp.equals("invalido")) {
                    JOptionPane.showMessageDialog(null, "El pass no corresponde","Advertencia",JOptionPane.WARNING_MESSAGE);
                    this.intentos += 1;
                } else if (resp.equals("desconectado")) {
                    JOptionPane.showMessageDialog(null, "No se pudo conectar a la BD, verifique que se encuentre el linea","Error de Conexion",JOptionPane.ERROR_MESSAGE);
                    this.intentos += 1;
                } else if (resp.equals("desactivado")) {
                    JOptionPane.showMessageDialog(null, "El usuario está desactivado","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else{
                    miCoordinador.setUsuario(miCoordinador.consultarUsuario(campoPass.getText()));
                    miCoordinador.asignarPrivilegios();
                    miCoordinador.cerrarVentanaLogin();
                    reinicioContador();
                }
            }
        }
        if (intentos > 2) {
            JOptionPane.showMessageDialog(null, "Se ha intentado ingresar a la cuenta más de 3 veces\nCerrando el programa...","No más intentos",JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
	
    public void limpiar(){
        comboUsuarios.setSelectedIndex(0);
        campoPass.setText("");
        reinicioContador();
    }

    private void mostrarElementos() {
        String seleccion=(String) comboUsuarios.getSelectedItem();
        int index=comboUsuarios.getSelectedIndex();

        if (index==0) {
            labelPass.setVisible(false);
            campoPass.setVisible(false);
        }else{
            labelPass.setVisible(true);
            campoPass.setVisible(true);
        }
    }
        
    public void windowActivated(WindowEvent e) {}     
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {} 
    public void windowOpened(WindowEvent arg0) {}
}