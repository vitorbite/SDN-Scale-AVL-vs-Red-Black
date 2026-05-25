import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AVLInvariantTests {

    private boolean isBalanced(AVLNode node) {
        
        if (node == null) {
            return true;
        }

        int leftHeight = node.getHeight((AVLNode) node.left);
        int rightHeight = node.getHeight((AVLNode) node.right);
        int balanceFactor = leftHeight - rightHeight;

        if (Math.abs(balanceFactor) > 1) {
            return false;
        }

        return isBalanced((AVLNode) node.left) && isBalanced((AVLNode) node.right);
    }

    @Test
    public void testAVLBalanceAfterSequentialInsertions() {

        AVL_Router_Tree tree = new AVL_Router_Tree();
        AVLNode root = null;

        for (int i = 1; i <= 100; i++) {
            PacketRule rule =
                    new PacketRule(
                            i,
                            "192.168.0." + i,
                            "10.0.0." + i,
                            i
                    );

            root = tree.insert(root, rule);
        }

        assertNotNull(root);
        assertTrue(isBalanced(root),"A arvore AVL não está balanceada após as inserções sequenciais.");
    }
}