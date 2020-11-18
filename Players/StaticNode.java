import java.util.List;

public class StaticNode extends MMNode{
    public SnakePlayer snakes;
    public String move;
    public List<StaticNode> Children;
    public StaticNode(List<Snake> snakes){
        SnakePlayer = snakes;
    }
    public void AddChild(StaticNode child){
        Children.add(child);
    }
}
