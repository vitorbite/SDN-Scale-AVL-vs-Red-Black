public class Node<T>{
    protected int value;
    protected T left;
    protected T right;
    public Node(int value, T left, T right){
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
