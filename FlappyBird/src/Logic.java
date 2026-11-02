import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Logic implements ActionListener, KeyListener {
    int frameWidth = 360;
    int frameHeight = 640;

    int playerStartPosX = frameWidth / 2;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 432;

    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;
    int pipeVelocityX = -2;

    View view;
    Image birdImage;
    Player player;

    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe> pipes;

    boolean gameOver = false;
    int score = 0;

    Sound bgm, jumpSound, pointSound, hitSound;

    public Logic() {
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);

        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(1750, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });

        gameLoop = new Timer(1000 / 60, this);
        

        bgm = new Sound("assets/bgm.wav");
        jumpSound = new Sound("assets/jump.wav");
        pointSound = new Sound("assets/point.wav");
        hitSound = new Sound("assets/hit.wav");
    }

    public void setView(View view) { this.view = view; }
    public Player getPlayer() { return player; }
    public ArrayList<Pipe> getPipes() { return pipes; }
    public boolean isGameOver() { return gameOver; }
    public int getScore() { return score; }

    
    public void startGame() {
        player.setPosX(playerStartPosX);
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);
        pipes.clear();
        score = 0;
        gameOver = false;
        if (view != null) view.updateScore(score);
        pipesCooldown.restart();
        gameLoop.restart();
        bgm.loop();
    }

    public void placePipes() {
        int randomPosY = (int) (pipeStartPosY - (pipeHeight / 4) - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, randomPosY + openingSpace + pipeHeight,
                pipeWidth, pipeHeight, lowerPipeImage);
        pipes.add(lowerPipe);
    }

    private boolean collides(Player p, Pipe pipe) {
        Rectangle a = new Rectangle(p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight());
        Rectangle b = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
        return a.intersects(b);
    }

    private void endGame() {
        gameOver = true;
        gameLoop.stop();
        pipesCooldown.stop();
        bgm.stop();
        hitSound.play();
    }

    public void move() {
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        
        if (player.getPosY() + player.getHeight() >= frameHeight) {
            endGame();
            return;
        }

        
        for (int i = pipes.size() - 1; i >= 0; i--) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipeVelocityX);

            
            if (collides(player, pipe)) {
                endGame();
                return;
            }

           
            if (!pipe.passed && pipe.getImage() == upperPipeImage
                    && player.getPosX() > pipe.getPosX() + pipe.getWidth()) {
                pipe.passed = true;
                score++;
                pointSound.play();
                if (view != null) view.updateScore(score);
            }

            
            if (pipe.getPosX() + pipe.getWidth() < 0) {
                pipes.remove(i);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        view.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !gameOver) {
            player.setVelocityY(-10);
            jumpSound.play();
        }
        if (e.getKeyCode() == KeyEvent.VK_R && gameOver) {
            startGame();
            view.repaint();
        }
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
