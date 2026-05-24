public class AVL_Router_Tree extends Binary_Search_Tree {

    protected class AVLNode extends Node {
        protected int height;

        AVLNode(int element, Node left, Node right) {
            super(element, left, right);

            height = 0;
            if (left != null) {
                height = Math.max(height, 1 + getHeight((AVLNode) left));
            }
            if (right != null) {
                height = Math.max(height, 1 + getHeight((AVLNode) right));
            }
        }
        public void setHeight(int height) {
            this.height = height;
        }
        public int getHeight(Node node) {
            if (node == null) return -1;
            return height;
        }
        public int calcular_FB(AVLNode node){
            if (node == null) return 0;
            return getHeight(node.left) - getHeight(node.right);
        }
    }

    public Node Rebalance(AVLNode node){
        node.height = 1 + Math.max(node.getHeight(node.left), node.getHeight(node.right));
        int FB = node.calcular_FB(node);
        if (FB > 1) { // Esquerda pesada
            if (node.calcular_FB((AVLNode) node.left) >= 0) {
                return Single_Right_Rotation(node);
            }else{
                return Double_Left_Right_Rotation(node);
            }
        }
        if (FB < -1) { // Direita pesada
            if (node.calcular_FB((AVLNode) node.right) <= 0) {
                return Single_Left_Rotation(node);
            }else{
                return Double_Right_Left_Rotation(node);
            }
        }
        return node;
    }

    @Override
    public Node insert(Node root, int value) {
        if (root == null) {
            root = new AVLNode(value, null, null);
        }
        if (value < root.value) {
            root.left = insert(root.left, value);
        }else if (value > root.value) {
            root.right = insert(root.right, value);
        }
        return root;
    }

    @Override
    public void delete(int key) {

    }

    public Node Single_Left_Rotation(Node node) {
        return node;
    }
    public Node Double_Left_Right_Rotation(Node node){
        return node;
    } 
    public Node Single_Right_Rotation(Node node) {
        return node;
    }
    public Node Double_Right_Left_Rotation(Node node){
        return node;
    }
}