import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");

        Logic logic = new Logic();
        View view = new View(logic);
        logic.setView(view);

        CardLayout cards = new CardLayout();
        JPanel container = new JPanel(cards);

        // ---- Главное меню ----
        JPanel menu = new JPanel(new GridBagLayout());
        menu.setBackground(new Color(112, 197, 206));
        menu.setPreferredSize(new Dimension(logic.frameWidth, logic.frameHeight));

        JLabel title = new JLabel("FLAPPY BIRD", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);

        JButton startButton = new JButton("Start");
        JButton exitButton = new JButton("Exit");
        Dimension btnSize = new Dimension(160, 45);
        startButton.setPreferredSize(btnSize);
        exitButton.setPreferredSize(btnSize);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.insets = new Insets(15, 0, 15, 0);
        c.gridy = 0; menu.add(title, c);
        c.gridy = 1; menu.add(startButton, c);
        c.gridy = 2; menu.add(exitButton, c);

        startButton.addActionListener(e -> {
            cards.show(container, "game");
            logic.startGame();
            view.requestFocusInWindow();
        });
        exitButton.addActionListener(e -> System.exit(0));

        container.add(menu, "menu");
        container.add(view, "game");
        cards.show(container, "menu");

        frame.add(container);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}