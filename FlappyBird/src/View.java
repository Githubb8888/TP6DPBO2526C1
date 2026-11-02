import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    Logic logic;
    Image background;
    JLabel scoreLabel;

    public View(Logic logic) {
        this.logic = logic;
        setPreferredSize(new Dimension(logic.frameWidth, logic.frameHeight));
        background = new ImageIcon(getClass().getResource("assets/background.png")).getImage();

        setLayout(null);
        scoreLabel = new JLabel("0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 40));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(0, 20, logic.frameWidth, 50);
        add(scoreLabel);

        setFocusable(true);
        addKeyListener(logic);
    }

    public void updateScore(int score) {
        scoreLabel.setText(String.valueOf(score));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight(), null);
        }

        ArrayList<Pipe> pipes = logic.getPipes();
        if (pipes != null) {
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        if (logic.isGameOver()) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 230, getWidth(), 150);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.drawString("GAME OVER", 70, 290);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Score: " + logic.getScore(), 135, 325);
            g.drawString("Press R to restart", 105, 360);
        }
    }
}