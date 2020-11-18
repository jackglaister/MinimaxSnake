package players;

import java.util.List;
import snake.GameState;
import snake.Snake;

public class MinimaxPlayer extends SnakePlayer {
    /**
     * protected GameState state;
     * protected GameDisplay display;
     * protected Snake game;
     * protected int index;
     **/

    public StaticNode newRoot;

    public MinimaxPlayer(GameState state, int index, Snake game, SnakePlayer[] players) {
        super(state, index, game, players);
    }

    public StaticNode GenerateTree(int level, StaticNode node) {
        if (level < 10) {
            for (int i = 0; i < Snake.players.size(); i++) {
                int counter = level;
                node = node.addLegalChildren(node, i*level, state, i);
                for (StaticNode child : node.Children){
                    child = GenerateTree(counter+1,child);
                }
            }
        }
        return node;
    }
    public void doMove(){
        return;
    }

    public StaticNode addLegalChildren(StaticNode node, int counter, GameState currentState, int current){
        int[] Legal = new int[4];
        int[] Potential = {1,2,3,4};
        for (int loop : Potential){
            if(state.isLegalMove(current, loop)) {
                int posy = currentState.getPlayerY(current).get(0);
                int posx = currentState.getPlayerX(current).get(0);
                int avoidable = state.getLastOrientation(index);
                switch (loop) {
                    case 1:
                        posy++;
                        currentState.PlayerY(current).set(0,currentState.getPlayerY(current).get(0)+1);
                        node.AddChild(GenerateTree(counter, new StaticNode(currentSnakes), Legal));
                        break;
                    case 2:
                        posx--;
                        currentState.PlayerX(current).set(0,currentState.getPlayerX(current).get(0)-1);
                        node.AddChild(GenerateTree(counter, new StaticNode(currentSnakes), Legal));
                        break;
                    case 3:
                        posy--;
                        currentState.PlayerY(current).set(0,currentState.getPlayerY(current).get(0)-1);
                        node.AddChild(GenerateTree(counter, new StaticNode(currentSnakes), Legal));
                        break;
                    case 4:
                        posx++;
                        currentState.PlayerX(current).set(0,currentState.getPlayerX(current).get(0)+1);
                        node.AddChild(GenerateTree(counter, new StaticNode(currentSnakes), Legal));
                    default:
                        break;
                }
            }
        }
        return node;
    }
    public StaticNode TraverseAndFill(StaticNode node, int counter){
        if (counter < 40 && newRoot.Children.size() == 0){
            node = GenerateTree(counter,node);
        }
        return node;
    }
    public int PickOptimum(){
        try{
            newRoot = TraverseAndFill(newRoot, 0);
        }
        catch (Exception e) {
            newRoot = TraverseAndFill(new StaticNode(players), 0);
        }

    }
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
