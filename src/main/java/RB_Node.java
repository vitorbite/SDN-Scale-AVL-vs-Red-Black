public class RB_Node extends Node<RB_Node>{
    private boolean isRed;
    protected RB_Node parent;

    RB_Node(PacketRule rule, RB_Node parent, RB_Node left, RB_Node right){
        super(rule, left, right);
        this.parent = parent;
        isRed = false;
    }
    public boolean isRed(){
        return isRed;
    }
    public void makeRed(){
        isRed = true;
    }
    public void makeBlack(){
        isRed = false;
    }
}
