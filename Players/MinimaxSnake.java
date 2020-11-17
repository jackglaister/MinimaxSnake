import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import snake.GameState;
import snake.Snake;

public class MinimaxSnake extends SnakePlayer{
/**
    protected GameState state;
    protected GameDisplay display;
    protected Snake game;
    protected int index;
**/

    public MinimaxSnake(GameState state, int index, Snake game) {
        super(state, index, game);
    }

    public StaticNode GenerateTree(int level, StaticNode node, List<int> moves){
        if (level < 40){
            int counter = level;
            counter++;
            for (int move : moves) {
                int posy = state.getPlayerY(index).get(0);
                int posx = state.getPlayerX(index).get(0);
                int avoidable = state.getLastOrientation(index);
                int[] fullpos = {1,2,3,4};
                switch (move) {
                    case 1:
                        posy++;
                        List<int> Legal = new List<int>();
                        for (int possible : fullpos){
                            if (possible != avoidable){
                                Legal.add(possible);
                            }
                        }
                        node.AddChild(GenerateTree(counter, new StaticNode(posy,posx), Legal));
                        break;
                    case 2:
                        posx--;
                        List<int> Legal = new List<int>();
                        for (int possible : fullpos){
                            if (possible != avoidable){
                                Legal.add(possible);
                            }
                        }
                        node.AddChild(GenerateTree(counter, new StaticNode(posy,posx), Legal));
                        break;
                    case 3:
                        posy--;
                        List<int> Legal = new List<int>();
                        for (int possible : fullpos){
                            if (possible != avoidable){
                                Legal.add(possible);
                            }
                        }
                        node.AddChild(GenerateTree(counter, new StaticNode(posy,posx), Legal));
                        break;
                    case 4:
                        posx++;
                        node.AddChild(new StaticNode(posy, posx));
                        List<int> Legal = new List<int>();
                        for (int possible : fullpos){
                            if (possible != avoidable){
                                Legal.add(possible);
                            }
                        }
                        node.AddChild(GenerateTree(counter, new StaticNode(posy,posx), Legal));
                    default:
                        break;
                }
            }
        }
        return RootNode;
    }

    /**
    public StaticNode FindOptimum(StaticNode RootNode){
        int foodX = state.getTargetX(); int foodY = state.getTargetY();

        StaticNode RootNode = GenerateTree(0,RootNode);

        for (StaticNode child : RootNode.Children){

            PriorityQueue<Node> frontier = new PriorityQueue();
            Set<Position> closed = new HashSet();
            frontier.add(new Node(state.getPlayerX(index).get(0), state.getPlayerY(index).get(0), state.getTargetX(), state.getTargetY()));
            while (!frontier.isEmpty()) {
                Node n = frontier.poll();
                closed.add(new Position(n.x, n.y));
                if (state.getTargetX() == n.x && state.getTargetY() == n.y) {
                    return n;
                }
                else {
                    if (!isOccupied(n.x + 1, n.y, n.depth) && !closed.contains(new Position(n.x + 1, n.y))) {
                        frontier.add(new Node(n.x + 1, n.y, n));
                    }
                    if (!isOccupied(n.x - 1, n.y, n.depth) && !closed.contains(new Position(n.x - 1, n.y))) {
                        frontier.add(new Node(n.x - 1, n.y, n));
                    }
                    if (!isOccupied(n.x, n.y + 1, n.depth) && !closed.contains(new Position(n.x, n.y + 1))) {
                        frontier.add(new Node(n.x, n.y + 1, n));
                    }
                    if (!isOccupied(n.x, n.y - 1, n.depth) && !closed.contains(new Position(n.x, n.y - 1))) {
                        frontier.add(new Node(n.x, n.y - 1, n));
                    }
                }
            }
            return null;
        }
    }

    private boolean isOccupied(int x, int y, int step) {
        if (x < 0 || x >= state.getWidth() || y < 0 || y >= state.getHeight()) {
            return true;
        }
        else if (!state.isOccupied(x, y)) {
            return false;
        }
        int cutoff = positions.get(new Position(x, y));
        return step <= cutoff;
    }
    public abstract void doMove();
}

**/

/**
 The optimisation function should allow for the snake to be able to have a higher chance of winning.
    Some ideas could include:
        IF another snake is really close to an object, the AI ignores it so it is at a more advantageous point later on in the game and avoids death from hitting a snake
        
    Use tree to plot out routes and assign a score to each route.
        Hitting a snake = -1000
        Hitting a wall = -1000
        Moving a square away from the food = -5 (we might have to move if a snake is in the way)
        Getting to the food = 500

 **/