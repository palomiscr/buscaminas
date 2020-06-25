import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Modelo implements PropertyChangeListener {
    private Tablero tablero;
    PropertyChangeSupport supportmodelo;

    public Modelo(Tablero tablero, PropertyChangeSupport p) {
        this.tablero = tablero;
        supportmodelo = p;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Point p = (Point) evt.getNewValue();
        int x = (int) p.getX() / Vista.sizecasilla;
        int y = (int) p.getY() / Vista.sizecasilla;
        if (evt.getPropertyName().equals("accion")) {
            tablero.getCasilla(y, x).setVisible(true);

            if (hasganado()) {
                supportmodelo.firePropertyChange("win", 0, 1);
            } else if (tablero.getCasilla(y, x).getValor() == 0) {
                descubrelazona(y, x);//poner esos a visibles

                supportmodelo.firePropertyChange("sigue", 0, 1);

            } else if (tablero.getCasilla(y, x).getValor() == Tablero.mina) {
                supportmodelo.firePropertyChange("fin", 0, 1);
            } else {
                supportmodelo.firePropertyChange("sigue", 0, 1);
            }
        } else {//evento bandera ojo la casilla bandera no es visible
            if (tablero.getCasilla(y, x).isBandera()) {
                tablero.getCasilla(y, x).setBandera(false);
            } else {
                tablero.getCasilla(y, x).setBandera(true);


            }
            supportmodelo.firePropertyChange("sigue", 0, 1);

        }
    }

    private boolean hasganado() {
        int i = 0;
        int j = 0;
        boolean sigo = true;
        while (i < tablero.getSize() && sigo) {
            while (j < tablero.getSize() && sigo) {
                if (tablero.getCasilla(i, j).getValor() != Tablero.mina && !(tablero.getCasilla(i, j).isVisible()))
                    sigo = false;

                j++;
            }
            i++;
        }
        System.out.println(sigo);
        return sigo;
    }

    private void descubrelazona(int i, int j) {
        int[] di = {-1, -1, -1, 0, 0, +1, +1, +1};
        int[] dj = {-1, 0, +1, -1, +1, -1, 0, +1};
        tablero.getCasilla(i, j).setVisible(true);
        if (tablero.getCasilla(i, j).getValor() != 0) {
            return;
        } else {
            for (int k = 0; k < 8; k++) {
                if (i + di[k] >= 0 && j + dj[k] >= 0 && i + di[k] < tablero.getSize() && j + dj[k] < tablero.getSize()
                        && !tablero.getCasilla(i + di[k], j + dj[k]).isVisible())
                    descubrelazona(i + di[k], j + dj[k]);

            }

        }
    }

}

