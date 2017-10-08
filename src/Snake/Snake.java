package Snake;

import Snake.*;
import java.util.Queue;

public class Snake extends Point
{
    private int length;
    private Queue<Point> path;
    private boolean isAlive;
    private int score;
    private Direction direction;

    public Snake(int x, int y, int length, Queue<Point> path,
                 boolean isAlive, int score, Direction direction)
    {
        super(x, y);
        this.length = length;
        this.path = path;
        this.isAlive = isAlive;
        this.score = score;
        this.direction = direction;
    }

    public int getLength()
    {
        return path.size();
    }

    public Snake setLocation(int x, int y)
    {
        return new Snake(x, y, getLength(), getPath(),
                isAlive(), getScore(), getDirection());
    }

    public Queue<Point> getPath()
    {
        return path;
    }

    public Snake increase(Point head)
    {
        path.add(this);
        return new Snake(head.getX(), head.getY(), getLength(), path,
                isAlive(), getScore(), getDirection());
    }

    public Snake move(Point head)
    {
        path.poll();
        path.add(this);
        return new Snake(head.getX(), head.getY(), getLength(), path,
                isAlive(), getScore(), getDirection());
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public Snake setAlive(boolean isAlive)
    {
        return new Snake(getX(), getY(), getLength(), getPath(),
                isAlive, getScore(), getDirection());
    }

    public int getScore()
    {
        return score;
    }

    public Snake setScore(int score)
    {
        return new Snake(getX(), getY(), getLength(), getPath(),
                isAlive(), score, getDirection());
    }

    public Snake addScore(int score)
    {
        return setScore(getScore() + score);
    }

    public Direction getDirection()
    {
        return direction;
    }

    public Snake setDirection(Direction direction)
    {
        return new Snake(getX(), getY(), getLength(), getPath(),
                isAlive(), getScore(), direction);
    }
}