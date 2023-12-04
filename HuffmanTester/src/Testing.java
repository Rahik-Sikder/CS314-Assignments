
public class Testing {
    public static void main(String[] args) {
        System.out.println("" + ((int) '0'));
    }

    private static void runMyPriorityQueueTests() {
        MyPriorityQueue<TreeNode> queue = new MyPriorityQueue<>();

        System.out.println(queue.size());

        queue.offer(new TreeNode(0, 0));
        queue.offer(new TreeNode(0, 1));
        queue.offer(new TreeNode(1, 1));
        queue.offer(new TreeNode(2, 1));
        queue.offer(new TreeNode(3, 1));
        queue.offer(new TreeNode(0, 2));
        queue.offer(new TreeNode(0, 3));
        queue.offer(new TreeNode(1, 3));
        queue.offer(new TreeNode(0, 4));
        queue.offer(new TreeNode(2, 3));
        queue.offer(new TreeNode(0, 9));
        queue.offer(new TreeNode(0, 19));

        MyPriorityQueue<TreeNode> queue2 = new MyPriorityQueue<>();
        queue2.offer(new TreeNode(0, 19));
        queue2.offer(new TreeNode(0, 9));
        queue2.offer(new TreeNode(0, 3));
        queue2.offer(new TreeNode(0, 4));
        queue2.offer(new TreeNode(1, 3));
        queue2.offer(new TreeNode(2, 3));
        queue2.offer(new TreeNode(0, 1));
        queue2.offer(new TreeNode(1, 1));
        queue2.offer(new TreeNode(0, 0));
        queue2.offer(new TreeNode(2, 1));
        queue2.offer(new TreeNode(3, 1));
        queue2.offer(new TreeNode(0, 2));

        System.out.println(queue);
        System.out.println();
        System.out.println(queue2);
    }




}



/*
 * 
 * Prints out tree in SimpleHuffProcessor:
 *     
 * 
   // TODO: REMOVE ONLY HERE FOR DEBUGGING PURPOSES
    private void printTree(TreeNode root) {
        printTree(root, "\t");
    }

    private void printTree(TreeNode n, String spaces) {
        if (n != null) {
            printTree(n.getRight(), spaces + "  ");
            showString(spaces + ((char) n.getValue()) + " " + n.getFrequency());
            printTree(n.getLeft(), spaces + "  ");
        }
    }
 * 
 * 
 */