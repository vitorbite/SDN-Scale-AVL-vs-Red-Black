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

    @Test
    public void testRootIsBlackAfterInsertions() {
        RB_Router_Tree tree = new RB_Router_Tree();

        tree.insert(tree.root, new PacketRule(10,"A","B",1));
        tree.insert(tree.root, new PacketRule(20,"A","B",1));
        tree.insert(tree.root, new PacketRule(30,"A","B",1));

        assertTrue(rootIsBlack(tree));
    }

    @Test
    public void testNoRedRedViolation() {
        RB_Router_Tree tree = new RB_Router_Tree();

        for (int i = 1; i <= 50; i++) {
            tree.insert(tree.root, new PacketRule(i,"A","B",1));
        }

        assertTrue(noRedRed(tree.root));
    }

    @Test
    public void testBlackHeightInvariant() {
        RB_Router_Tree tree = new RB_Router_Tree();

        for (int i = 1; i <= 50; i++) {
            tree.insert(tree.root, new PacketRule(i,"A","B",1));
        }

        assertTrue(validBlackHeight(tree.root));
    }

    @Test
    public void testSearchAfterInsertions() {
        RB_Router_Tree tree = new RB_Router_Tree();

        tree.insert(tree.root, new PacketRule(10,"A","B",1));
        tree.insert(tree.root, new PacketRule(20,"A","B",1));
        tree.insert(tree.root, new PacketRule(30,"A","B",1));

        RB_Node found = tree.search(tree.root, 20);

        assertNotNull(found);
        assertEquals(20, found.rule.getId());
    }

    @Test
    public void testDeleteMaintainsProperties() {
        RB_Router_Tree tree = new RB_Router_Tree();

        tree.insert(tree.root, new PacketRule(10,"A","B",1));
        tree.insert(tree.root, new PacketRule(20,"A","B",1));
        tree.insert(tree.root, new PacketRule(30,"A","B",1));
        tree.insert(tree.root, new PacketRule(40,"A","B",1));

        tree.delete(tree.root, new PacketRule(20,"A","B",1));

        RB_Node found = tree.search(tree.root, 20);

        assertNull(found);
        assertTrue(rootIsBlack(tree));
        assertTrue(noRedRed(tree.root));
        assertTrue(validBlackHeight(tree.root));
    }
}