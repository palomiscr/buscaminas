public class Casilla {
    private int valor;
    private boolean visible;
    private boolean bandera;

    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
    }

    public Casilla(int valor) {
        this.valor = valor;
        this.visible = false;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public void incrementavalor(){
        valor++;
    }
}
