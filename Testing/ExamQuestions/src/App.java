import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.text.Utilities;

public class App {

    public static void main(String[] args) throws Exception {
       
        BNode root = new BNode(4, new BNode(5, null, null), new BNode(6, null, null));

        System.out.println("See right child exits: " + root.right.data);

        test(root.right);

        System.out.println("See right child exits: " + root.right.data);

    }

    private static void test(BNode cur){
        cur = null;
    }
    
    private static class BNode {

        private int data;
        private BNode left;
        private BNode right;

        

        public BNode(int data, BNode left, BNode right){
            this.data = data;
            this.left = left;
            this.right = right;
        }

    }

}