package players;
import java.util.List;

public class StaticNode extends MMNode{
    public String move;
    public StaticNode(SnakePlayer[] snakes){
        Snakes = snakes;
        Children = new List<MMNode>
    }
    public void AddChild(StaticNode child){
        Children.add(child);
    }
}
