public abstract class Binary_Search_Tree<T extends Node<T>> {
    protected T root;

    public T search(T node, int target){
        while (node != null && target != node.rule.getId()) {
            if (target < node.rule.getId()) {
                node = node.left;
            }else{
                node = node.right;
            }
        }
        return node;
    }

    public abstract T insert(T node, PacketRule rule);
    public abstract T delete(T node, PacketRule rule);

}

