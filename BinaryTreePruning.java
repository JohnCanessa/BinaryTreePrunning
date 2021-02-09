import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * 
 */
public class BinaryTreePruning {

    /**
     * Auxiliary method.
     */
    static boolean leafNode(TreeNode root) {

        // **** sanity check ****
        if (root.val == 1)
            return false;

        // **** check if end node ****
        if (root.left == null && root.right == null)
            return true;
  
        // **** not a leaf node ****
        return false;
    }


    /**
     * DFS traversal deleting nodes.
     * Recursive call.
     */
    static void prune(TreeNode root) {
        if (root != null) {

            // **** visit left sub tree ****
            prune(root.left);

            // **** delete left node (if needed) ****
             if (root.left != null && leafNode(root.left))
                root.left = null;

            // **** visit right sub tree ****
            prune(root.right);

            // **** delete right node (if needed) ****
            if (root.right != null && leafNode(root.right))
                root.right = null;
        } 
    } 


    /**
     * Return the same tree where every subtree (of the given tree) 
     * not containing a 1 has been removed.
     * 
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 36.5 MB, less than 77.69% of Java online submissions.
     */
    static TreeNode pruneTree(TreeNode root) {
      
        // **** sanity check ****
        if (root == null)
            return null;

        // **** recursive call ****
        prune(root);

        // **** delete root node (if needed) ****
        if (leafNode(root))
            root = null;

        // **** returned pruned BT ****
        return root;
    }


    /**
     * Test scaffolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read String[] with node values for binary tree ****
        String[] strArr = br.readLine().trim().split(",");

        // **** close buffered reader ****
        br.close();

        // ???? ????
        System.out.println("main <<< strArr: " + Arrays.toString(strArr));

        // **** create and populate Integer[] ****
        Integer[] arr = new Integer[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals("null"))
                arr[i] = null;
            else
                arr[i] = Integer.parseInt(strArr[i]);
        }

        // ???? ????
        System.out.println("main <<<    arr: " + Arrays.toString(arr));

        // **** create and populate a original binary tree ****
        BST bt = new BST();
        bt.root = bt.populate(arr);
    
        // ???? ????
        System.out.println("main <<< bt levelOrder:");
        System.out.println(bt.levelOrder());

        // **** prune the BT ****
        bt.root = pruneTree(bt.root);

        // ???? ????
        System.out.println("main <<< pruned levelOrder:");
        System.out.println(bt.levelOrder());
    }
}