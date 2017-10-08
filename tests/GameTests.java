import Snake.*;
import org.junit.Test;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class GameTests
{
    @Test
    public void testMovements() throws Exception
    {
        Queue<Point> path = new ArrayDeque<>();
        Snake snake = new Snake(0, 0, 0, path, true, 0, Direction.Right);
        GameState initial = new GameState(snake, new Point(2, 2), new ArrayList<GameObject>());
        GameState moveRight = Game.update(initial);
        assertEquals(moveRight.getSnake().getX(), 1);
        assertEquals(moveRight.getSnake().getY(), 0);

        GameState moveDown = Game.update(initial.setSnake(moveRight.getSnake().setDirection(Direction.Down)));
        assertEquals(moveDown.getSnake().getX(), 1);
        assertEquals(moveDown.getSnake().getY(), 1);

        GameState moveLeft = Game.update(initial.setSnake(moveDown.getSnake().setDirection(Direction.Left)));
        assertEquals(moveLeft.getSnake().getX(), 0);
        assertEquals(moveLeft.getSnake().getY(), 1);

        GameState moveUp = Game.update(initial.setSnake(moveLeft.getSnake().setDirection(Direction.Up)));
        assertEquals(moveUp.getSnake().getX(), 0);
        assertEquals(moveUp.getSnake().getY(), 0);
    }

    @Test
    public void testMapBorderMovements()
    {
        Queue<Point> path = new ArrayDeque<>();
        Snake snake = new Snake(0, 0, 0, path, true, 0, Direction.Left);
        GameState map1x1 = new GameState(snake, new Point(1, 1), new ArrayList<GameObject>());
        GameState updated = Game.update(map1x1);
        assertEquals(updated.getSnake().getX(), 0);
        assertEquals(updated.getSnake().getY(), 0);

        GameState map2x2 = updated.setDimensions(new Point(2, 2));
        GameState updated2 = Game.update(map2x2);
        assertEquals(updated2.getSnake().getX(), 1);
        assertEquals(updated2.getSnake().getY(), 0);

        GameState updated3 = Game.update(map2x2.setSnake(updated2.getSnake().setDirection(Direction.Up)));
        assertEquals(updated3.getSnake().getX(), 1);
        assertEquals(updated3.getSnake().getY(), 1);
    }

    @Test
    public void testStoneConflict()
    {
        Queue<Point> path = new ArrayDeque<>();
        Snake snake = new Snake(0, 0, 0, path, true, 0, Direction.Right);
        GameObject stone = new GameObject(1, 0, GameObjectType.Stone);
        GameState initial = new GameState(snake, new Point(2, 2), new ArrayList<GameObject>());
        GameState updated = Game.update(initial.addObject(stone));
        assertEquals(updated.getSnake().isAlive(), false);
    }

    @Test
    public void testWallConflict()
    {
        Queue<Point> path = new ArrayDeque<>();
        Snake snake = new Snake(1, 1, 0, path, true, 0, Direction.Right);
        GameState initial = new GameState(snake, new Point(3, 3), new ArrayList<GameObject>());
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (i == 0 || j == 0 || i == 2 || j == 2)
                {
                    GameObject wall = new GameObject(i, j, GameObjectType.Wall);
                    initial = initial.addObject(wall);
                }
            }
        }
        GameState updated = Game.update(initial);
        assertEquals(updated.getSnake().isAlive(), false);

        snake.setAlive(true).setLocation(1, 1).setDirection(Direction.Left);    // REBIRTH!!!
        GameState updated2 = Game.update(updated);
        assertEquals(updated2.getSnake().isAlive(), false);

        snake.setAlive(true).setLocation(1, 1).setDirection(Direction.Up);    // REBIRTH!!!
        GameState updated3 = Game.update(updated2);
        assertEquals(updated3.getSnake().isAlive(), false);

        snake.setAlive(true).setLocation(1, 1).setDirection(Direction.Down);    // REBIRTH!!!
        GameState updated4 = Game.update(updated3);
        assertEquals(updated4.getSnake().isAlive(), false);
    }

    @Test
    public void testAppleConflict()
    {
/*        Queue<Point> path = new ArrayDeque<>();
        Snake snake = new Snake(0, 0, 1, path, true, 0, Direction.Right);
        GameObject apple = new GameObject(1, 1, GameObjectType.Apple);
        GameState initial = new GameState(snake, new Point(2, 2), new ArrayList<GameObject>());
        GameState updated = Game.update(initial.addObject)*/
    }
}