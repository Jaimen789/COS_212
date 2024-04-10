public class SplayTree<T extends Comparable<T>> {

    public TreeNode<T> root;
    TreeNode<T> pre;
    

    public SplayTree() {
        // Your code here...
        this.root = null;
    }

    /**
     * Returns true if the key exists in the tree, otherwise false
     */
    public Boolean contains(T key) {
        // Your code here...
        
        return containNode(root, key);
        
    }

    public Boolean containNode(TreeNode<T> node, T key){

        if(node == null) return false;

        if(node.key == key) return true;

        Boolean res1 = containNode(node.left, key);

        if(res1) return true;

        Boolean res2 = containNode(node.right, key);

        return res2;


    }
    /**
     * Insert the given key into the tree.
     * Duplicate keys should be ignored.
     * No Splaying should take place.
     */
    public void insert(T key) {
        // Your code here
        root = insertRec(root, key);
    }

    public void splay(T key) {
        root = splay(root, key);
    }

    /**
     * Return the Predecessor of the given key.
     * If the given key does not exist return null.
     * If the given key does not have a Predecessor, return null.
     */

    void access(T key){

        moveToRoot(root, key);
    }

    void moveToRoot(TreeNode<T> node, T key){
        splay(node, key);
    }



    public T findPredecessor(T key) {
        // Your code here...
        
        return findPred(root, key);

    }

    public T findPred(TreeNode<T> node, T key){

        

        if(node == null) return null;

        if(node.key == key){
            if(node.left != null){
                TreeNode<T> temp = node.left;

                while(temp.right != null){
                    temp = temp.right;
                }

                pre = temp;
            }
        }

        if(node.key.compareTo(key) < 0){
            pre = node;
            findPred(node.right, key);
        }

        return pre.key;
    }
    

    public TreeNode<T> insertRec(TreeNode<T> node, T key) {
        if (node == null) {
            return new TreeNode<T>(key);
        }
    
        int cmp = key.compareTo(node.key);
    
        if (cmp == 0) {
            // Key already exists, do nothing or handle duplicates as needed
            return node;
        } else if (cmp < 0) {
            // Insert into the left subtree
            node.left = insertRec(node.left, key);
        } else {
            // Insert into the right subtree
            node.right = insertRec(node.right, key);
        }
    
        return node;
    }
    

    public TreeNode<T> splay(TreeNode<T> node, T key) {
        if (node == null || node.key.compareTo(key) == 0) {
            return node;
        }
    
        int cmp1 = key.compareTo(node.key);
    
        if (cmp1 < 0) {
            if (node.left == null) {
                return node;
            }
    
            int cmp2 = key.compareTo(node.left.key);
    
            if (cmp2 < 0) {
                node.left.left = splay(node.left.left, key);
                node = rightRotate(node);
            } else if (cmp2 > 0) {
                node.left.right = splay(node.left.right, key);
                if (node.left.right != null) {
                    node.left = leftRotate(node.left);
                }
            }
    
            return (node.left == null) ? node : rightRotate(node);
        } else if (cmp1 > 0) {
            if (node.right == null) {
                return node;
            }
    
            int cmp2 = key.compareTo(node.right.key);
    
            if (cmp2 < 0) {
                node.right.left = splay(node.right.left, key);
                if (node.right.left != null) {
                    node.right = rightRotate(node.right);
                } else if (cmp2 > 0) {
                    node.right.right = splay(node.right.right, key);
                    node = leftRotate(node);
                }
            }
    
            return (node.right == null) ? node : leftRotate(node);
        }
    
        return node;
    }
    
    

    

    public TreeNode<T> rightRotate(TreeNode<T> x){
        TreeNode<T> y = x.left;
        x.left = y.right;
        y.right = x;

        return y;
    }

    public TreeNode<T> leftRotate(TreeNode<T> x){
        TreeNode<T> y = x.right;
        x.right = y.left;
        y.left = x;

        return y;
    }


    public void print(TreeNode<T> node){

        if(node != null){
            print(node.left);
            System.out.println(node.toString());
            print(node.right);
        
        }
    }


}