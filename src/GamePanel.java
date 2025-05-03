import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private final int TILE_SIZE = 30;
    private final int ROWS = 20;
    private final int COLS = 10;
    private Pieza piezaActual = BolsaPiezas.crearPiezaAleatoria();
    //private int[][] forma = piezaActual.getForma();

    private Timer timer;

    public GamePanel() {
        setPreferredSize(new Dimension(COLS * TILE_SIZE, ROWS * TILE_SIZE));
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
        // y a su vez dibujado, todo en uno
        g.setColor(Color.DARK_GRAY);
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
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
        piezaActual.moverAbajo();
        int[][] forma = piezaActual.getForma();
        if (piezaActual.getFila() > ROWS-forma.length) {
            piezaActual = BolsaPiezas.crearPiezaAleatoria();
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
            if(piezaActual.getColumna() < (COLS-forma[0].length)) {
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
