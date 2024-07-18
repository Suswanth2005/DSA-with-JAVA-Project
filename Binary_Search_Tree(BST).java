class TreeNode {
    int value;
    TreeNode leftChild, rightChild;

    public TreeNode(int item) {
        value = item;
        leftChild = rightChild = null;
    }
}

class BST {
    TreeNode rootNode;

    BST() {
        rootNode = null;
    }

    // Insert a new value
    void add(int value) {
        rootNode = addRecursive(rootNode, value);
    }

    TreeNode addRecursive(TreeNode currentNode, int value) {
        if (currentNode == null) {
            return new TreeNode(value);
        }
        if (value < currentNode.value)
            currentNode.leftChild = addRecursive(currentNode.leftChild, value);
        else if (value > currentNode.value)
            currentNode.rightChild = addRecursive(currentNode.rightChild, value);

        return currentNode;
    }

    // Search for a value
    boolean containsNode(int value) {
        return containsNodeRecursive(rootNode, value) != null;
    }

    TreeNode containsNodeRecursive(TreeNode currentNode, int value) {
        if (currentNode == null || currentNode.value == value)
            return currentNode;
        if (currentNode.value > value)
            return containsNodeRecursive(currentNode.leftChild, value);
        return containsNodeRecursive(currentNode.rightChild, value);
    }

    // Delete a value
    void remove(int value) {
        rootNode = removeRecursive(rootNode, value);
    }

    TreeNode removeRecursive(TreeNode currentNode, int value) {
        if (currentNode == null) return currentNode;
        if (value < currentNode.value)
            currentNode.leftChild = removeRecursive(currentNode.leftChild, value);
        else if (value > currentNode.value)
            currentNode.rightChild = removeRecursive(currentNode.rightChild, value);
        else {
            if (currentNode.leftChild == null) return currentNode.rightChild;
            else if (currentNode.rightChild == null) return currentNode.leftChild;
            currentNode.value = findMinValue(currentNode.rightChild);
            currentNode.rightChild = removeRecursive(currentNode.rightChild, currentNode.value);
        }
        return currentNode;
    }

    int findMinValue(TreeNode currentNode) {
        int minValue = currentNode.value;
        while (currentNode.leftChild != null) {
            minValue = currentNode.leftChild.value;
            currentNode = currentNode.leftChild;
        }
        return minValue;
    }

    // InOrder Traversal
    void traverseInOrder() {
        traverseInOrderRecursive(rootNode);
    }

    void traverseInOrderRecursive(TreeNode currentNode) {
        if (currentNode != null) {
            traverseInOrderRecursive(currentNode.leftChild);
            System.out.print(currentNode.value + " ");
            traverseInOrderRecursive(currentNode.rightChild);
        }
    }

    // PreOrder Traversal
    void traversePreOrder() {
        traversePreOrderRecursive(rootNode);
    }

    void traversePreOrderRecursive(TreeNode currentNode) {
        if (currentNode != null) {
            System.out.print(currentNode.value + " ");
            traversePreOrderRecursive(currentNode.leftChild);
            traversePreOrderRecursive(currentNode.rightChild);
        }
    }

    // PostOrder Traversal
    void traversePostOrder() {
        traversePostOrderRecursive(rootNode);
    }

    void traversePostOrderRecursive(TreeNode currentNode) {
        if (currentNode != null) {
            traversePostOrderRecursive(currentNode.leftChild);
            traversePostOrderRecursive(currentNode.rightChild);
            System.out.print(currentNode.value + " ");
        }
    }

    // Driver code
    public static void main(String[] args) {
        BST bst = new BST();

        bst.add(50);
        bst.add(30);
        bst.add(20);
        bst.add(40);
        bst.add(70);
        bst.add(60);
        bst.add(80);

        System.out.println("InOrder traversal:");
        bst.traverseInOrder();

        System.out.println("\n\nPreOrder traversal:");
        bst.traversePreOrder();

        System.out.println("\n\nPostOrder traversal:");
        bst.traversePostOrder();

        System.out.println("\n\nSearch for 40: " + bst.containsNode(40));
        System.out.println("Search for 100: " + bst.containsNode(100));

        bst.remove(20);
        System.out.println("\nInOrder traversal after deleting 20:");
        bst.traverseInOrder();

        bst.remove(30);
        System.out.println("\nInOrder traversal after deleting 30:");
        bst.traverseInOrder();

        bst.remove(50);
        System.out.println("\nInOrder traversal after deleting 50:");
        bst.traverseInOrder();
    }
}
