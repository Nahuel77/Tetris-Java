import java.awt.*;
import java.util.Random;

public class BolsaPiezas {
    private static final int[][] FORMA_T = {
            {0,1,0},
            {1,1,1}
    };
    private static final int[][] FORMA_I = {
            {2,2,2,2}
    };
    private static final int[][] FORMA_L = {
            {0,0,3},
            {3,3,3}
    };
    private static final int[][] FORMA_J = {
            {4,0,0},
            {4,4,4}
    };
    private static final int[][] FORMA_O = {
            {5,5},
            {5,5}
    };
    private static final int[][] FORMA_S = {
            {0,6,6},
            {6,6,0}
    };
    private static final int[][] FORMA_Z = {
            {7,7,0},
            {0,7,7}
    };

    public static Pieza crearPiezaAleatoria(){
        TipoPieza tipo = TipoPieza.values()[new Random().nextInt(7)];
        return crearPieza(tipo);
    }

    public static Pieza crearPieza(TipoPieza tipo){
        switch (tipo){
            case I: return new Pieza(FORMA_I, Color.RED);
            case O: return new Pieza(FORMA_O, Color.BLUE);
            case T: return new Pieza(FORMA_T, Color.YELLOW);
            case S: return new Pieza(FORMA_S, Color.MAGENTA);
            case Z: return new Pieza(FORMA_Z, Color.GREEN);
            case J: return new Pieza(FORMA_J, Color.ORANGE);
            case L: return new Pieza(FORMA_L, Color.CYAN);
        }
        return null;
    }
}
