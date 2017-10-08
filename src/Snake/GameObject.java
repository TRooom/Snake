package Snake;

public class GameObject extends Point
{
    public GameObjectType getGameObjectType()
    {
        return gameObjectType;
    }

    private GameObjectType gameObjectType;

    public GameObject(int x, int y, GameObjectType gameObjectType)
    {
        super(x, y);
        this.gameObjectType = gameObjectType;
    }
}