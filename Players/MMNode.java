package players;

import java.util.List;

public abstract class MMNode{
    public SnakePlayer[] Snakes;
    public List<MMNode> Children;
    public abstract void AddChild(MMNode child);
}

