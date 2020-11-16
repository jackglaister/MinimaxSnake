public class StaticNode extends MMNode{}{
    public int PosY;
    public int PosX;
    public String move;
    public List<StaticNode> Children;
    public StaticNode(int posY, int posX){
        PosY = posY; PosX = posX;
    }
    public void AddChild(StaticNode child){
        Children.add(child);
    }
}
