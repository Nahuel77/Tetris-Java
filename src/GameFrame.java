import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        setTitle("Tetris por Nahuel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        add(new GamePanel());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
