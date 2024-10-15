package vista;

import controlador.Coordinador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Frame;

import javax.swing.JDialog;

public class VentanaProductos extends JDialog  implements ActionListener{
    private Coordinador miCoordinador;
    
    public VentanaProductos(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(705,350);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    private void initComponents() {
    }
    
    public void setCoordinador(Coordinador miCoordinador) {
        this.miCoordinador=miCoordinador;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
    }
}