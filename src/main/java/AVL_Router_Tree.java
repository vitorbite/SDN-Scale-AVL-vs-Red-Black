public class AVL_Router_Tree extends Binary_Search_Tree {

    protected static class AVLNode extends Node {
        protected int height;

        AVLNode(int element, Node left, Node right) {
            super(element, left, right);
            
            height = 0;
            if (left != null) {
                height = Math.max(height, 1 + ((AVLNode) left).getHeight());
            }
            if (right != null) {
                height = Math.max(height, 1 + ((AVLNode) right).getHeight());
            }
        }
        public void setHeight(int height) {
            this.height = height;
        }
        public int getHeight() {
            return height;
        }
    }

    @Override
    public void insert(int key) {

    }

    @Override
    public void delete(int key) {

    }

    @Override
    public void rotateLeft(Node node) {

    }

    @Override
    public void rotateRight(Node node) {

    }

}