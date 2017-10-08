package Snake;

import static Snake.Direction.*;
import static Snake.GameObjectType.*;

public class Game
{
    private static Snake getNewSnake(GameState state)
    {
        Snake snake = state.getSnake();
        int dx = 0, dy = 0, x = 0, y = 0;
        Direction dir = snake.getDirection();
        dx = dir == Right ? 1 : (dir == Left ? -1 : 0);
        dy = dir == Down ? 1 : (dir == Up ? -1 : 0);
        x = snake.getX() + dx;
        y = snake.getY() + dy;
        if (x >= state.getDimensions().getX())
            x = 0;
        else if (x < 0)
            x = state.getDimensions().getX() - 1;
        if (y >= state.getDimensions().getY())
            y = 0;
        else if (y < 0)
            y = state.getDimensions().getY() - 1;
        return snake.setLocation(x, y);
    }

    public static GameState update(GameState gameState)
    {
        Snake snake = gameState.getSnake();
        if (!snake.isAlive())
            return gameState;
        Snake newSnake = getNewSnake(gameState);
        for (int i = 0; i < gameState.getObjects().size(); i++)
        {
            GameObject go = gameState.getObjects().get(i);
            boolean willTouch = newSnake.getX() == go.getX() && go.getY() == newSnake.getY();
            if (willTouch)
            {
                GameObject go2;
                switch (go.getGameObjectType())
                {
                    case Wall:
                        return gameState.setSnake(newSnake.setAlive(false));
                    case Stone:
                        return gameState.setSnake(newSnake.setAlive(false));
                    case Apple:
                         go2 = new GameObject(0, 0, go.getGameObjectType());
                        return gameState
                                .setSnake(snake.addScore(10).increase(go))
                                .removeObject(i)
                                .addObject(go2);
                    case Strawberry:
                         go2 = new GameObject(0, 0, go.getGameObjectType());
                        return gameState
                                .setSnake(snake.addScore(10).increase(go))
                                .removeObject(i)
                                .addObject(go2);
                    case Pill:
                         go2 = new GameObject(0, 0, go.getGameObjectType());
                        return gameState
                                .setSnake(snake.addScore(10).increase(go))
                                .removeObject(i)
                                .addObject(go2);
                }
                break;
            }
        }
        for (Point point : snake.getPath())
        {
            if (point.equals(snake))
                return gameState.setSnake(newSnake.setAlive(false));
        }
        return gameState.setSnake(newSnake);
    }
}