/**
 * ArrayBinaryTree.java
 * 
 * This class implements a binary tree using an array.
 * It supports setting elements, traversing in prefix/postfix order,
 * and printing the tree graphically.
 * 
 * Note: The printGraphically() method displays the tree in left-to-right order,
 * where the root appears at the **left-most** of the printed output.
 */
class ArrayBinaryTree {
    private String[] tree;

    public ArrayBinaryTree(int capacity) {
        tree = new String[capacity];
    }

    public void setElement(int index, String element) {
        if (index >= 0 && index < tree.length) {
            tree[index] = element;
        } else {
            throw new RuntimeException("Tree too deep — increase capacity.");
        }
    }

    public String getElement(int index) {
        if (index >= 0 && index < tree.length) {
            return tree[index];
        }
        return null;
    }

    public int length() {
        return tree.length;
    }

    public String toPrefix() {
        StringBuilder sb = new StringBuilder();
        preorder(0, sb);
        return sb.toString().trim();
    }

    public String toPostfix() {
        StringBuilder sb = new StringBuilder();
        postorder(0, sb);
        return sb.toString().trim();
    }

    public void printGraphically() {
        printTree(0, 0);
    }

    private void preorder(int index, StringBuilder sb) {
        if (index >= tree.length || tree[index] == null) return;
        sb.append(tree[index]).append(" ");
        preorder(2 * index + 1, sb);
        preorder(2 * index + 2, sb);
    }

    private void postorder(int index, StringBuilder sb) {
        if (index >= tree.length || tree[index] == null) return;
        postorder(2 * index + 1, sb);
        postorder(2 * index + 2, sb);
        sb.append(tree[index]).append(" ");
    }

    private void printTree(int index, int depth) {
        if (index >= tree.length || tree[index] == null) return;
        printTree(2 * index + 2, depth + 1);
        for (int i = 0; i < depth; i++) System.out.print("    ");
        System.out.println(tree[index]);
        printTree(2 * index + 1, depth + 1);
    }
}