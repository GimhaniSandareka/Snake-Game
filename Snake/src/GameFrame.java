import javax.swing.*;

public class GameFrame extends JFrame {

    GameFrame() {

//        GamePanel panel = new GamePanel();
//        this.add(panel);
        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack(); // Take the JFrame and fit it snugly around all the components that we add to the frame
        this.setVisible(true);
        this.setLocationRelativeTo(null); // To make the window appear at the middle of the screen
    }
}
