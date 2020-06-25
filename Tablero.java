public class Tablero {
    public static int bandera=-5;
    public static int mina=-1;

    private int size;
    private Casilla[][] tablero;
    private int numminas;
    public Tablero(int size, int numminas ){
        this.size=size;
        tablero=new Casilla[size][size];
         this.numminas=numminas;
        for (int i = 0; i <size ; i++) {
            for (int j = 0; j < size; j++) {
                tablero[i][j]=new Casilla(0);
                tablero[i][j].setVisible(false);
            }
        }
        int i=0;
        while ( i < numminas) {
            int x=(int)Math.floor(Math.random()*size);
            int y=(int)Math.floor(Math.random()*size);
            if(tablero[x][y].getValor()!=mina) {
                tablero[x][y].setValor(mina);//una mina se puede colocar en una zona amenazada
                actualizaramenazas(x, y);
                i++;
            }

        }


    }
    private void actualizaramenazas(int x, int y){
        int dx[]={-1, -1, -1, 0, 0, +1, +1, +1};
        int dy[]={-1, 0, +1, -1, +1, -1, 0, +1};
        for (int i = 0; i < 8; i++) {
            if(x+dx[i]>=0 && y+dy[i]>=0 && x+dx[i]<size && y+dy[i]<size && tablero[x+dx[i]][y+dy[i]].getValor()!=mina){
                tablero[x+dx[i]][y+dy[i]].incrementavalor();
            }
        }
    }

    public static int getBandera() {
        return bandera;
    }

    public static void setBandera(int bandera) {
        Tablero.bandera = bandera;
    }

    public static int getMina() {
        return mina;
    }

    public static void setMina(int mina) {
        Tablero.mina = mina;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Casilla[][] getTablero() {
        return tablero;
    }

    public void setTablero(Casilla[][] tablero) {
        this.tablero = tablero;
    }

    public int getNumminas() {
        return numminas;
    }

    public void setNumminas(int numminas) {
        this.numminas = numminas;
    }
    public Casilla getCasilla(int x, int y){
        return tablero[x][y];
    }
}
