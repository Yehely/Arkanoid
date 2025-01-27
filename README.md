# Arkanoid Game Project

This repository contains an implementation of the classic Arkanoid game, created as part of the "Object-Oriented Programming (OOP)" course.

## Game Description
Arkanoid is a brick-breaking game where a player controls a paddle to bounce a ball and break blocks. The game includes:
- **Multiple levels** with varying block arrangements.
- **Interactive gameplay mechanics**, such as block-breaking and scoring.
- **Object-Oriented Design**, with modular and reusable components.

## Features
1. **Game Mechanics**:
   - Ball and paddle interactions.
   - Blocks that disappear upon being hit.
   - Score tracking and display.
2. **Object-Oriented Structure**:
   - Separation of concerns using distinct classes for geometry, game environment, sprites, and listeners.
   - Interfaces and abstract classes for extensibility.
3. **Customizable Levels**:
   - Easy-to-modify block arrangements and game parameters.

## Project Structure
The project is organized into the following packages:

### 1. `geometry`
- `Ball.java`: Represents the ball in the game.
- `Block.java`: Represents the blocks to be destroyed.
- `Line.java`, `Point.java`, `Rectangle.java`: Handle geometric calculations and collisions.
- `Velocity.java`: Manages ball movement.

### 2. `gameEnv`
- `Game.java`: Core class managing the game loop and interactions.
- `GameEnvironment.java`: Manages the environment where the game takes place.
- `BallRemover.java`, `BlockRemover.java`: Handle removal of game elements.
- `ScoreIndicator.java`: Displays the current score.

### 3. `sprite`
- `Paddle.java`: Represents the player's paddle.
- `Sprite.java`, `SpriteCollection.java`: Manage game objects that are drawn and updated.
- `ScoreTrackingListener.java`: Updates the score based on game events.
- `Collidable.java`, `CollisionInfo.java`: Interfaces for handling collisions.

### 4. Main Class
- `Ass5Game.java`: The entry point to the game, initializing and running it.

## Requirements
- **Java Development Kit (JDK)** version 8 or higher.
- Any Java IDE (e.g., IntelliJ IDEA, Eclipse) or a terminal for compilation and execution.

## How to Run
1. Clone the repository or download the source code.
   ```bash
   git clone <repository-url>
   ```
2. Open the project in your preferred Java IDE.
3. Compile and run the `Ass5Game` class to start the game.

Alternatively, compile and run using the terminal:
```bash
javac -d bin src/**/*.java
java -cp bin Ass5Game
```

## Author
This project was developed as a learning exercise in the "Object-Oriented Programming" course.

---
Feel free to explore the code, modify levels, and enhance the game!
