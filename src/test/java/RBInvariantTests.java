import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RBInvariantTests {

    private boolean rootIsBlack(RB_Router_Tree tree) {
        return tree.root == null || tree.root.isBlack();
    }

    private boolean noRedRed(RB_Node node) {
        if (node == null) {
            return true;
        }
        if (node.isRed()) {
            if ((node.left != null && node.left.isRed()) || (node.right != null && node.right.isRed())) {
                return false;
            }
        }
        return noRedRed(node.left) && noRedRed(node.right);
    }

    private int blackHeight(RB_Node node) {
        if (node == null) {
            return 1;
        }

        int leftBlackHeight = blackHeight(node.left);
        int rightBlackHeight = blackHeight(node.right);

        if (leftBlackHeight == 0 || rightBlackHeight == 0 || leftBlackHeight != rightBlackHeight) {
            return 0;
        }
        return leftBlackHeight + (node.isBlack() ? 1 : 0);
    }

    private boolean validBlackHeight(RB_Node node) {
        return blackHeight(node) != 0;
    }
}