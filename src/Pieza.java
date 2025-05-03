import java.awt.*;

public class Pieza {
    private int[][] forma;
    private int fila, columna;
    private Color color;

    public Pieza(int[][] forma, Color color){
        this.forma = forma;
        this.color = color;
        this.fila = 0;
        this.columna = 4;
    }

    public Pieza(Pieza otra) {
        this.forma = Rotador.clonarMatriz(otra.forma);
        this.fila = otra.fila;
        this.columna = otra.columna;
        this.color = otra.color;
    }

    public int[][] getForma(){
        return forma;
    }

    public void rotar(){
        forma = Rotador.rotarMatriz(forma);
    }

    public Color getColor(){
        return color;
    }

    public int getFila(){
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void moverAbajo() {
        fila++;
    }

    public void moverArriba(){
        fila--;
    }

    public void moverIzquierda() {
        columna--;
    }

    public void moverDerecha() {
        columna++;
    }
}
