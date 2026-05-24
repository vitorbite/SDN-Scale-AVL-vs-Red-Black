public class AVL_Router_Tree extends Binary_Search_Tree<AVLNode> {

    public AVLNode Rebalance(AVLNode node) {
        node.height = 1 + Math.max(node.getHeight(node.left), node.getHeight(node.right));
        int FB = node.calcular_FB(node);
        if (FB > 1) { // Esquerda pesada
            if (node.calcular_FB(node.left) >= 0) {
                return Single_Right_Rotation(node);
            } else {
                return Double_Left_Right_Rotation(node);
            }
        }
        if (FB < -1) { // Direita pesada
            if (node.calcular_FB(node.right) <= 0) {
                return Single_Left_Rotation(node);
            } else {
                return Double_Right_Left_Rotation(node);
            }
        }
        return node;
    }

    @Override
    public AVLNode insert(AVLNode root, int value) {
        if (root == null) {
            root = new AVLNode(value, null, null);
        }
        if (value < root.value) {
            root.left = insert(root.left, value);
        } else if (value > root.value) {
            root.right = insert(root.right, value);
        }

        root.height = 1 + Math.max(root.getHeight(root.left), root.getHeight(root.right));
        return Rebalance(root);
    }

    @Override
    public AVLNode delete(AVLNode root, int id) {
        if (root == null) return null;
        if (id < root.value) {
            root.left = delete(root.left, id);
        } else if (id > root.value) {
            root.right = delete(root.right, id);
        } else {

            if (root.left == null && root.right == null) {
                AVLNode temp = (root.left != null) ? root.left : root.right;
                if (temp == null) {
                    root = null;
                }else{
                    root = temp;
                }

            }else{
                AVLNode sucessor = findMin(root.right);
                root.value = sucessor.value;
                root.right = delete(root.right, sucessor.value);
            }
        }
        if (root == null) return null;

        root.height = 1 + Math.max(root.getHeight(root.left), root.getHeight(root.right));
        return Rebalance(root);
    }

    public AVLNode Single_Left_Rotation(AVLNode node) {
        AVLNode B = (AVLNode) node.right;
        AVLNode T2 = B.left;

        B.left = node;
        node.right = T2;

        node.height = 1 + Math.max(node.getHeight((AVLNode) node.left), node.getHeight((AVLNode) node.right));
        B.height = 1 + Math.max(node.getHeight((AVLNode) B.left), node.getHeight((AVLNode) B.right));
        return B;
    }

    public AVLNode Single_Right_Rotation(AVLNode node) {
        AVLNode B = (AVLNode) node.left;
        AVLNode T2 = B.right;

        B.right = node;
        node.left = T2;

        node.height = 1 + Math.max(node.getHeight((AVLNode) node.left), node.getHeight((AVLNode) node.right));
        B.height = 1 + Math.max(node.getHeight((AVLNode) B.left), node.getHeight((AVLNode) B.right));
        return B;
    }

    public AVLNode Double_Left_Right_Rotation(AVLNode node) {
        node.left = Single_Left_Rotation((AVLNode) node.left);
        return Single_Right_Rotation(node);
    }

    public AVLNode Double_Right_Left_Rotation(AVLNode node) {
        node.right = Single_Right_Rotation((AVLNode) node.right);
        return Single_Left_Rotation(node);
    }

    public AVLNode findMin(AVLNode root) {
        if (root == null) {
            return null;
        }
        AVLNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
}