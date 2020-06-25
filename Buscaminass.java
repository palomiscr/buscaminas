import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeSupport;

public class Buscaminass {
    public static void main(String[] args) {
        Buscaminass b=new Buscaminass();
    }
    public Buscaminass(){
        JFrame frame1=new JFrame();
        String aux=JOptionPane.showInputDialog(frame1,"introduce el tamaño del tablero" );

        JFrame frame2=new JFrame();
        String aux2=JOptionPane.showInputDialog(frame2,"introduce la cantidad de minas" );

        int size=Integer.parseInt(aux);
        int cantidad= Integer.parseInt(aux2);

        Tablero tablero=new Tablero(size,cantidad );
        PropertyChangeSupport supportmodelo=new PropertyChangeSupport(this);
        PropertyChangeSupport supportcontrol=new PropertyChangeSupport(this);
        Modelo modelo=new Modelo(tablero,supportmodelo );
        Controlador controlador=new Controlador(supportcontrol);
        Vista vista=new Vista(tablero);
        vista.addMouseListener(controlador);
        supportcontrol.addPropertyChangeListener(modelo);
        supportmodelo.addPropertyChangeListener(vista);

        Frame frame =new Frame();
        frame.setSize(size*Vista.sizecasilla, size*Vista.sizecasilla+40);
        frame.add(vista);
        frame.setVisible(true);


        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

    }
}
