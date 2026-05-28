public class RB_Router_Tree extends Binary_Search_Tree<RB_Node> {

    //

    public RB_Node RB_Insert_Fixup(RB_Node node) {
        return new RB_Node(null, root, root, root);
    }

    @Override
    public RB_Node insert(RB_Node node, PacketRule rule) {

        RB_Node newNode = new RB_Node(rule, null, null, null);
        RB_Node leaf = null;
        RB_Node root = this.root; 

        while (root != null) {
            leaf = root;
            if (newNode.rule.getId() < root.rule.getId()) {
                root = root.left;
            } else if (newNode.rule.getId() > root.rule.getId()) {
                root = root.right;
            } else {
                return root; 
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
        return null;
    }

    public void remedleafDoubleRed(RB_Node node) {

    }

    public void Single_Left_Rotation(RB_Node node) {

    }

    public void Single_Right_Rotation(RB_Node node) {

    }

    public void Double_Left_Right_Rotation(RB_Node node) {

    }

    public void Double_Right_Left_Rotation(RB_Node node) {

    }

}
