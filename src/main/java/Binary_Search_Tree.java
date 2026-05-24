public abstract class Binary_Search_Tree {
    protected Node root;

    public Node search(Node node, int target){
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

    public abstract Node insert(Node node, int key);
    public abstract void delete(int key);

}

