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
        setBackground(Color.DARK_GRAY);
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dibujarTablero(g);
        dibujarPiezaActual(g);
    }

    private void dibujarTablero(Graphics g) {
        Color[][] matriz = tablero.getMatriz();

        for (int i = 0; i < Tablero.ROWS; i++) {
            for (int j = 0; j < Tablero.COLUMNS; j++) {
                g.setColor(Color.GRAY);
                g.drawRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                if (matriz[i][j] != null) {
                    g.setColor(matriz[i][j]);
                    g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    private void dibujarPiezaActual(Graphics g) {
        int[][] forma = piezaActual.getForma();
        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                if (forma[i][j] != 0) {
                    int x = (piezaActual.getColumna() + j) * TILE_SIZE;
                    int y = (piezaActual.getFila() + i) * TILE_SIZE;
                    g.setColor(piezaActual.getColor());
                    g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        piezaActual.moverAbajo();
        tablero.eliminarFilasCompletas();
        if (tablero.colisiona(piezaActual)) {
            piezaActual.moverArriba();
            tablero.colocarPieza(piezaActual);
            piezaActual = BolsaPiezas.crearPiezaAleatoria();
            if (tablero.colisiona(piezaActual)) {
                timer.stop();
                JOptionPane.showMessageDialog(this, "Game Over");
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        Pieza copia = new Pieza(piezaActual); // ← Clonar pieza

        switch (key) {
            case KeyEvent.VK_LEFT:
                copia.moverIzquierda();
                break;
            case KeyEvent.VK_RIGHT:
                copia.moverDerecha();
                break;
            case KeyEvent.VK_DOWN:
                copia.moverAbajo();
                break;
            case KeyEvent.VK_SPACE:
                copia.rotar();
                break;
        }

        if (!tablero.colisiona(copia)) {
            piezaActual = copia;
            repaint();
        }
    }
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
