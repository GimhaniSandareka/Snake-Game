
# Snake Game 🍎🐍

This is a simple Snake game built using **Java** and **Swing**. The game features a dynamic, real-time interface where players control a snake to collect apples while avoiding collisions with itself and the screen borders. The game includes a game-over screen, scoring system, and the ability to restart after a game over.


## Features

- **Difficulty Selection:** Choose between Easy, Medium, and Hard difficulty levels at the start
- **High Score Tracking:** The game keeps track of the highest score using a file-based system (highscore.txt)
- **Arrow Key Navigation:** Uses the arrow keys to control the snake's direction (up, down, left, right)
- **Score Tracking:** Apple collection with score tracking
- **Pause/ Resume:** Press the Space key to pause the game during play
- **Restart:** Press 'R' after the game is over to restart the game
- **Improved Collison Detection:** The snake now detects collisions with itself and the boundaries of the game area.
- **Game Over Screen:** Displays the player's score, high score, and an option to restart the game
- **Sound Effects:** Plays Sound effects when the snake eats apples and game over
## Gameplay

- **Objective:** The player controls a snake that grows longer with each apple eaten. The game ends when the snake collides with itself or the screen boundaries.

- **Controls:**

  -  Arrow keys (`←`, `→`, `↑`, `↓`) to move the snake.

  - Press `Space` to pause or resume the game.

  - Press `R` to restart the game after a Game Over.
## File Structure

```
Snake/
 ├── src/
 │    ├── SnakeGame.java        # Main class to start the game
 │    ├── GameFrame.java        # JFrame to set up the game window
 │    └── GamePanel.java        # Contains game logic, rendering, and user input handling
 ├── resources/
 │    ├── eat.wav               # Sound when snake eats an apple
 │    └── gameover.wav          # Sound when the game is over
 └── highscore.txt              # Stores the highest score achieved
```

## Tech Stack
- Language: Java
- GUI Toolkit: Swing (Javax Swing)
- Audio: `javax.sound.sampled` package

## Getting Started
1. Clone the repository:
```
git clone https://github.com/GimhaniSandareka/Snake-Game.git
```
2. Open the project in your preferred Java IDE

3. Compile and run the `SnakeGame.java` (Main class)

4. Play the game and enjoy!

## Dependencies
- Java (JDK 8 or higher)


## Sound Credits
- `eat.wav` (Apple_Crunch_16.wav) by **Koops** – freesound.org

gameover.wav – Custom combination using two free sounds from Mixkit
## Inspiration
Inspired by the Bro Code YouTube tutorial on creating a Snake Game in Java.
This version has been expanded with new few features, improved logic, and personalized enhancements beyond the original implementation.
