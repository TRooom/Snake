package Snake;

import java.util.ArrayList;

public class GameState
{
    private Snake snake;
    private Point dimensions;

    public GameState setSnake(Snake snake)
    {
        return new GameState(snake, getDimensions(), getObjects());
    }

    public GameState setDimensions(Point dimensions)
    {
        return new GameState(getSnake(), dimensions, getObjects());
    }

    public GameState setObjects(ArrayList<GameObject> objects)
    {
        return new GameState(getSnake(), getDimensions(), objects);
    }

    private ArrayList<GameObject> objects;

    public GameState(Snake snake, Point dimensions,
                     ArrayList<GameObject> objects)
    {
        this.snake = snake;
        this.dimensions = dimensions;
        this.objects = objects;
    }

    public Snake getSnake()
    {
        return snake;
    }

    public Point getDimensions()
    {
        return dimensions;
    }

    public ArrayList<GameObject> getObjects()
    {
        return objects;
    }

    public GameState removeObject(int index)
    {
        // todo
        return this;
    }

    public GameState addObject(GameObject object)
    {
        objects.add(object);
        return this;
    }
}