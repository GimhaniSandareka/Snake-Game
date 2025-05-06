import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75;

    // 2 arrays that are going to hold all the coordinates for all the body parts of the snake,
    // including the head of the snake

    // Setting the size of array to game units because snake isn't going to be bigger than the game itself
    final int x[] = new int[GAME_UNITS]; // holds all the x coordinates of the snake body parts + head
    final int y[] = new int[GAME_UNITS]; // holds all the y coordinates of the snake body parts + head

    int bodyParts = 6;
    int applesEaten = 0;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    boolean gameOver = false;
    boolean paused = false;

    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

        startGame();
    }

    public void startGame(){

        // Reset snake position
        for (int i = 0; i < bodyParts; i++) {
            x[i] = 100 - i * UNIT_SIZE;
            y[i] = 100;
        }
        newApple();
        running = true;
        direction = 'R';
//        timer = new Timer(DELAY, this);

        String[] options = {"Easy", "Medium", "Hard"};
        int choice = JOptionPane.showOptionDialog(null, "Select Difficulty Level", "Snake Game",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        int delay;
        switch (choice) {
            case 0: delay = 150; break;
            case 1: delay = 100; break;
            case 2: delay = 50; break;
            default: delay = 100;
        }

        timer = new Timer(delay, this);


        timer.start();


    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){

        if(running){
            // Create a grid
//            for (int i = 0; i < SCREEN_HEIGHT/UNIT_SIZE; i++){
//                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT); // Add lines to y-axis
//                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
//            }

            // Apple
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // Snake
            for (int i = 0; i < bodyParts; i++){
                if (i == 0){
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180,0));
                    //g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255))); // randomly change snake body color
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }

            g.setColor(Color.red);
            g.setFont(new Font("Times New Roman", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " +  applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " +  applesEaten))/2, g.getFont().getSize());



        } else {
            gameOver(g);
        }

        // Show "Game Paused" message when the game is paused
        if (paused) {
            g.setColor(Color.yellow);
            g.setFont(new Font("Times New Roman", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Game Paused", (SCREEN_WIDTH - metrics.stringWidth("Game Paused")) / 2, SCREEN_HEIGHT / 2);
        }
    }

    // Generate the coordinates of a new apple whenever the method is called
    public void newApple(){
        appleX = random.nextInt((int) (SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

    }

    // Moving the snake
    public void move(){

        for (int i = bodyParts; i > 0; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(direction){
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple(){
        if ((x[0] == appleX) && (y[0] == appleY)){
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions(){
        // Checks if head collides with body
        for (int i = bodyParts; i > 0; i--){
            if ((x[0]==x[i]) && (y[0]==y[i])){
                running = false;
            }
        }
        if (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
            gameOver = true;
        }
    }

    public void gameOver(Graphics g){
        // Score
        g.setColor(Color.yellow);
        g.setFont(new Font("Times New Roman", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " +  applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " +  applesEaten))/2, g.getFont().getSize());

        // Game Over Text
        g.setColor(Color.red);
        g.setFont(new Font("Times New Roman", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);

        g.setColor(Color.green);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        FontMetrics metrics3 = getFontMetrics(g.getFont());
        g.drawString("Press 'R' to Restart", (SCREEN_WIDTH - metrics3.stringWidth("Press 'R' to Restart"))/2, SCREEN_HEIGHT / 2 + 60);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (running){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (gameOver) {
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    restartGame();
                }
            } else {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (direction != 'R') {
                            direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'L') {
                            direction = 'R';
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (direction != 'D') {
                            direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'U') {
                            direction = 'D';
                        }
                        break;
                    case KeyEvent.VK_SPACE: // Pause and Resume
                        if (running) {
                            paused = !paused;  // Toggle pause state
                            if (paused) {
                                timer.stop();  // Stop the game timer
                            } else {
                                timer.start();  // Resume the game timer
                            }
                            repaint();  // Ensure the screen is redrawn immediately after toggling pause
                        }
                        break;
                }
            }
        }
    }

    public void restartGame() {
        bodyParts = 6;
        applesEaten = 0;
        direction = 'R';
        gameOver = false;
        paused = false;
        running = true;
        startGame();
        repaint();
    }
}