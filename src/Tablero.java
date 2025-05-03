import java.awt.*;

public class Tablero {
    private Color[][] tablero;
    public static final int ROWS = 20;
    public static final int COLUMNS = 10;

    public Tablero(){
        tablero = new Color[ROWS][COLUMNS];
    }

    public boolean estaOcupado(int fila, int columna){
        return tablero[fila][columna]!=null;
    }

    public void colocarPieza(Pieza pieza){
        int[][] forma = pieza.getForma();
        int fila = pieza.getFila();
        int columna = pieza.getColumna();

        for(int i=0; i<forma.length; i++){
            for(int j=0; j<forma[0].length; j++){
                if(forma[i][j]!=0){
                    tablero[fila + i][columna +j]=pieza.getColor();
                }
            }
        }
    }

    public boolean colisiona(Pieza pieza) {
        int[][] forma = pieza.getForma();
        int fila = pieza.getFila();
        int columna = pieza.getColumna();

        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[0].length; j++) {
                if (forma[i][j] != 0) {
                    int nuevaFila = fila + i;
                    int nuevaColumna = columna + j;

                    if (nuevaFila >= ROWS || nuevaColumna < 0 || nuevaColumna >= COLUMNS) {
                        return true;
                    }

                    if (nuevaFila >= 0 && tablero[nuevaFila][nuevaColumna] != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void eliminarFilasCompletas() {
        for (int fila = ROWS - 1; fila >= 0; fila--) {
            boolean completa = true;

            for (int columna = 0; columna < COLUMNS; columna++) {
                if (tablero[fila][columna] == null) {
                    completa = false;
                    break;
                }
            }

            if (completa) {
                eliminarFila(fila);
                fila++;
            }
        }
    }

    private void eliminarFila(int fila) {
        for (int i = fila; i > 0; i--) {
            for (int j = 0; j < COLUMNS; j++) {
                tablero[i][j] = tablero[i - 1][j];
            }
        }

        for (int j = 0; j < COLUMNS; j++) {
            tablero[0][j] = null;
        }
    }

    public Color[][] getMatriz(){
        return tablero;
    }
}
