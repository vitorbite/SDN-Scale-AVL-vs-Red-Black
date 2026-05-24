 final class AVLNode extends Node<AVLNode> {
        protected int height;

        AVLNode(int element, AVLNode left, AVLNode right) {
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

        public int getHeight(AVLNode node) {
            if (node == null)
                return -1;
            return node.height;
        }

        public int calcular_FB(AVLNode node) {
            if (node == null)
                return 0;
            return getHeight((AVLNode) node.left) - getHeight((AVLNode) node.right);
        }
    }