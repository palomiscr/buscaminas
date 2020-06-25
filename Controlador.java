import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeSupport;

public class Controlador implements MouseListener {
    private PropertyChangeSupport supportcontrol;
    public Controlador(PropertyChangeSupport p){
        supportcontrol=p;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p= e.getPoint();
        if(e.getButton()==MouseEvent.BUTTON1){
            supportcontrol.firePropertyChange("accion", 0, p);
        }
        else if(e.getButton()==MouseEvent.BUTTON3){
            supportcontrol.firePropertyChange("bandera", 0, p);

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
