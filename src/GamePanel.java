import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final int TILE_SIZE = 30;
    private Pieza piezaActual = BolsaPiezas.crearPiezaAleatoria();
    private Tablero tablero;

    private Timer timer;

    public GamePanel() {
        tablero = new Tablero();
        setPreferredSize(new Dimension(Tablero.COLUMNS * TILE_SIZE, Tablero.ROWS * TILE_SIZE));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //plantear la posibilidad de implementar clase de diagrama y control de tablero
        // y a su vez dibujado, tdo en uno

        for (int r = 0; r < Tablero.ROWS; r++) {
            for (int c = 0; c < Tablero.COLUMNS; c++) {
                    g.setColor(Color.DARK_GRAY);
                    g.drawRect(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        //Cuadrado, aqui implementar llamado de piezas
        int[][] forma = piezaActual.getForma();
        g.setColor(piezaActual.getColor());

        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                if (forma[i][j] == 1) {
                    int x = (piezaActual.getColumna() + j) * TILE_SIZE;
                    int y = (piezaActual.getFila() + i) * TILE_SIZE;
                    g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //todo: agregar if que detenga la pieza en colision con mapa o piezas ubicadas
        // y dispare el guardado de la pieza en el mapeo


        int[][] forma = piezaActual.getForma();
        if (piezaActual.getFila() >= Tablero.ROWS-forma.length) {//todo: error de fondo
            piezaActual = BolsaPiezas.crearPiezaAleatoria();
            tablero.colocarPieza(piezaActual);
        }else{
            piezaActual.moverAbajo();
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        int[][] forma = piezaActual.getForma();

        if (key == KeyEvent.VK_LEFT) {
            if(piezaActual.getColumna()>0){
                piezaActual.moverIzquierda();
            }
        } else if (key == KeyEvent.VK_RIGHT) {
            if(piezaActual.getColumna() < (Tablero.COLUMNS-forma[0].length)) {
                piezaActual.moverDerecha();
            }
        } else if (key == KeyEvent.VK_DOWN) {
            piezaActual.moverAbajo();
        } else if (key == KeyEvent.VK_SPACE) {
            piezaActual.rotar();
        }

        repaint();
    }
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
