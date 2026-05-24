public class Node<T>{
    protected PacketRule rule;
    protected T left;
    protected T right;
    public Node(PacketRule rule, T left, T right){
        this.rule = rule;
        this.left = left;
        this.right = right;
    }
}
