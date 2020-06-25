import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Vista extends Canvas implements PropertyChangeListener {
    public static int sizecasilla = 20;
    private Graphics2D g2d;
    private Tablero tablero;
    private boolean win=false;
    private boolean lost=false;
    Image img;
    public Vista(Tablero tablero) {
        this.tablero = tablero;
         img=Toolkit.getDefaultToolkit().getImage("bandera.png");

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //solo manejar fin lo demas por referencia, pintar el modelo en cada iteracion
        if(evt.getPropertyName().equals("fin"))
            lost=true;
        if( evt.getPropertyName().equals("win"))
            win=true;
        repaint();
    }

    public void paint(Graphics g){
        g2d=(Graphics2D) g;

        if(!lost && !win){
            g2d.setColor(new Color(194, 194, 194));
            for (int i = 0; i < tablero.getSize(); i++) {
                for (int j = 0; j < tablero.getSize(); j++) {
                    g2d.fillRect(i*sizecasilla, j*sizecasilla, sizecasilla, sizecasilla);
                }
            }
            g2d.setColor(Color.BLACK);
            for (int i = 0; i < tablero.getSize()+1; i++) {
                g2d.drawLine(0, i*sizecasilla, sizecasilla*tablero.getSize(), i*sizecasilla);
                g2d.drawLine(i*sizecasilla, 0, i*sizecasilla, sizecasilla*tablero.getSize());
            }
            for (int i = 0; i < tablero.getSize(); i++) {
                for (int j = 0; j < tablero.getSize(); j++) {
                    switch(tablero.getCasilla(i, j).getValor()){
                        case 0:
                            g2d.setColor(Color.black);
                            break;
                        case 1:
                            g2d.setColor(Color.blue);
                            break;
                        case 2:
                            g2d.setColor(new Color(24, 125, 29));
                            break;
                        case 3:
                            g2d.setColor(Color.red);
                            break;
                    }
                    if(tablero.getCasilla(i, j).isBandera()){
                        g2d.drawImage(img, j*sizecasilla, i*sizecasilla, sizecasilla, sizecasilla, this);
                    }
                    else if(tablero.getCasilla(i, j).isVisible()){
                        Integer in=tablero.getCasilla(i, j).getValor();
                        g2d.setFont(new Font(Font.SANS_SERIF,Font.BOLD,sizecasilla));
                        g2d.drawString(in.toString(), j*sizecasilla, i*sizecasilla+sizecasilla);
                    }
                }
            }
        }
        else if(lost){
            //mostrar fin de partida
            g2d.setColor(new Color(255, 18, 22));
            g2d.drawString("Perdiste", tablero.getSize()*sizecasilla/2, tablero.getSize()*sizecasilla/2);
        }
        else{
            g2d.setColor(Color.green);
            g2d.drawString("Ganaste", tablero.getSize()*sizecasilla/2, tablero.getSize()*sizecasilla/2);
        }
    }




}
