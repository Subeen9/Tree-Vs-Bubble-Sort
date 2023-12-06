import java.io.FileWriter;
import java.io.IOException;

class BinaryTree {
    private TreeNode root;
    private int comparisons = 0;
    public int getNumberOfComparisons() {
        return comparisons;
    }
    public TreeNode getRoot() {
        return root;
    }
    private TreeNode makeTreeNode(int num) {
        TreeNode node = new TreeNode();
        node.data = num;
        return node;
    }

    public void buildTree(int num) {
        TreeNode current = root;

        if (root == null) {
            root = makeTreeNode(num);
        } else {
            while (true) {
                comparisons++;
                if (num == current.data) {
                    current.repeat++;
                    return;
                } else if (num < current.data) {
                    if (current.left == null) {
                        setLeft(current, num);
                        return;
                    }
                    current = current.left;
                } else {
                    if (current.right == null) {
                        setRight(current, num);
                        return;
                    }
                    current = current.right;
                }
            }
        }
    }


    public void printInOrder(TreeNode node) {
        if (node.left != null) {
            printInOrder(node.left);
        }
        System.out.println(node.data + " (Occurrences: " + node.repeat + ")");
        if (node.right != null) {
            printInOrder(node.right);
        }
    }
    public void printInOrderIterative() {
        TreeNode current;
        Stack stack = new Stack();
        stack.init();
        stack.push(root);
        current = root;

        do {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            if (!stack.isStackEmpty()) {
                current = stack.pop();
                System.out.println(current.data + " (Occurrences: " + current.repeat + ")");
                current = current.right;
            }
        } while (!stack.isStackEmpty() || (current != null));
    }



    private void setLeft(TreeNode node, int n) {
        if (node.left != null) {
            System.out.println("Left child already exists with value: " + n);
        } else {
            node.left = makeTreeNode(n);
        }
    }

    private void setRight(TreeNode node, int n) {
        if (node.right != null) {
            System.out.println("Right child already exists with value: " + n);
        } else {
            node.right = makeTreeNode(n);
        }
    }

    public void writeInOrderToFile(TreeNode node, FileWriter outputFile) throws IOException {
        TreeNode treeNode = node;

        if (treeNode.left != null) {
            writeInOrderToFile(treeNode.left, outputFile);
        }

        outputFile.write(String.valueOf(treeNode.data) + " (Occurrences: " + treeNode.repeat + ")\n");

        if (treeNode.right != null) {
            writeInOrderToFile(treeNode.right, outputFile);
        }
    }
}