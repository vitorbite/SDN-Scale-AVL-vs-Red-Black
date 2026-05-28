public class RB_Router_Tree extends Binary_Search_Tree<RB_Node> {

    public void RB_Insert_Fixup(RB_Node node) {
        // Método de Recoloração
        while (node.parent != null && node.parent.isRed()) {
            if (node.parent == node.parent.parent.left) {
                RB_Node uncle = node.parent.parent.right;

                if (uncle != null && uncle.isRed()) {
                    node.parent.makeBlack();
                    uncle.makeBlack();
                    node.parent.parent.makeRed();
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        Left_Rotation(node);
                    }
                    node.parent.makeBlack();
                    node.parent.parent.makeRed();
                    Right_Rotation(node.parent.parent);
                }
            } else {
                RB_Node uncle = node.parent.parent.left;

                if (uncle != null && uncle.isRed()) {
                    node.parent.makeBlack();
                    uncle.makeBlack();
                    node.parent.parent.makeRed();
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        Right_Rotation(node);
                    }
                    node.parent.makeBlack();
                    node.parent.parent.makeRed();
                    Left_Rotation(node.parent.parent);
                }
            }
        }
        this.root.makeBlack();
    }

    @Override
    public RB_Node insert(RB_Node node, PacketRule rule) {
        RB_Node newNode = new RB_Node(rule, null, null, null);
        RB_Node leaf = null;
        RB_Node currentRoot = this.root;

        while (currentRoot != null) {
            leaf = currentRoot;
            if (newNode.rule.getId() < currentRoot.rule.getId()) {
                currentRoot = currentRoot.left;
            } else if (newNode.rule.getId() > currentRoot.rule.getId()) {
                currentRoot = currentRoot.right;
            } else {
                return currentRoot;
            }
        }

        newNode.parent = leaf;

        if (leaf == null) {
            this.root = newNode;
        } else if (newNode.rule.getId() < leaf.rule.getId()) {
            leaf.left = newNode;
        } else {
            leaf.right = newNode;
        }

        newNode.left = null;
        newNode.right = null;
        newNode.makeRed();

        RB_Insert_Fixup(newNode);
        return newNode;
    }

    @Override
    public RB_Node delete(RB_Node node, PacketRule rule) {
        RB_Node root = this.root;
        while (root != null && root.rule.getId() != rule.getId()) {
            if (rule.getId() < root.rule.getId()) {
                root = root.left;
            } else {
                root = root.right;
            }
        }

        if (root == null)
            return null;

        RB_Node noDeletado = root;

        RB_Node y = root;
        boolean yOriginalColorIsRed = y.isRed();
        RB_Node x;
        RB_Node xParent;

        if (root.left == null) {
            x = root.right;
            xParent = root.parent;
            Transplant(root, root.right);
        } else if (root.right == null) {
            x = root.left;
            xParent = root.parent;
            Transplant(root, root.left);
        } else {
            y = findMin(root.right);
            yOriginalColorIsRed = y.isRed();
            x = y.right;

            if (y.parent == root) {
                xParent = y;
                if (x != null)
                    x.parent = y;
            } else {
                xParent = y.parent;
                Transplant(y, y.right);
                y.right = root.right;
                if (y.right != null)
                    y.right.parent = y;
            }

            Transplant(root, y);
            y.left = root.left;
            if (y.left != null) {
                y.left.parent = y;
            }
            if (root.isRed()) {
                y.makeRed();
            } else {
                y.makeBlack();
            }
        }

        if (!yOriginalColorIsRed) {

            RB_Delete_Fixup(x, xParent);
        }

        return noDeletado;
    }

    public void Transplant(RB_Node node, RB_Node v) {
        if (node.parent == null) {
            this.root = v;
        } else if (node == node.parent.left) {
            node.parent.left = v;
        } else {
            node.parent.right = v;
        }
        if (v != null) {
            v.parent = node.parent;
        }
    }

    public void RB_Delete_Fixup(RB_Node node, RB_Node parent) {

    }

    private RB_Node findMin(RB_Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void Left_Rotation(RB_Node node) {
        RB_Node y = node.right;
        node.right = y.left;

        if (y.left != null) {
            y.left.parent = node;
        }

        y.parent = node.parent;

        if (node.parent == null) {
            this.root = y;
        } else if (node == node.parent.left) {
            node.parent.left = y;
        } else {
            node.parent.right = y;
        }

        y.left = node;
        node.parent = y;
    }

    public void Right_Rotation(RB_Node node) {
        RB_Node y = node.left;
        node.left = y.right;

        if (y.right != null) {
            y.right.parent = node;
        }

        y.parent = node.parent;

        if (node.parent == null) {
            this.root = y;
        } else if (node == node.parent.right) {
            node.parent.right = y;
        } else {
            node.parent.left = y;
        }

        y.right = node;
        node.parent = y;
    }

}
