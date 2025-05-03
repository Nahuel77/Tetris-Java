public class Tablero {
    private int[][] tablero;
    public static final int ROWS = 20;
    public static final int COLUMNS = 10;

    public Tablero(){
        tablero = new int[ROWS][COLUMNS];
    }

    public boolean estaOcupado(int fila, int columna){
        return tablero[fila][columna]!=0;
    }

    public void colocarPieza(Pieza pieza){
        int[][] forma = pieza.getForma();
        int fila = pieza.getFila();
        int columna = pieza.getColumna();

        for(int i=0; i<forma.length; i++){
            for(int j=0; j<forma[0].length; j++){
                if(forma[i][j]!=0){
                    tablero[fila + i][columna +j]=1;
                }
            }
        }
    }

    public int[][] getMatriz(){
        return tablero;
    }
}
