public abstract class Binary_Search_Tree<T extends Node<T>> {
    protected T root;

    public T search(T node, int target){
        node = root;
        while (node != null && target != node.value) {
            if (target < node.value) {
                node = node.left;
            }else{
                node = node.right;
            }
        }
        return node;
    }

    public abstract T insert(T node, int key);
    public abstract void delete(int key);

}

