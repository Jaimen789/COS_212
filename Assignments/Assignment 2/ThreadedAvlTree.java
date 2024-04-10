public class ThreadedAvlTree<T extends Comparable<T>> {
    public Node<T> root;

    public ThreadedAvlTree() {
        this.root = null;
    }

    //do a inorder traversal 

    // left -> inorder predecessor
        //if none -> set left to null
        //else -> set left to predecessor

    //right -> inorder successor
        //if none -> set right to null
        //else -> set right to sucessor

    void convertAVLtoThreaded(Node<T> node){

        morrisTraversal(node);
        reverseMorrisTraversal(node);

    }

    public void morrisTraversal(Node<T> node) {
        System.out.println("morrisTraversal");
    
        Node<T> curr = node;
    
        while (curr != null) {
            if (curr.left == null) {
                // If the left child is null, establish thread to the right child
                System.out.println(curr.data + " -> " + "THREAD to " + (curr.right != null ? curr.right.data : "null"));
                curr = curr.right;
            } else {
                Node<T> prev = curr.left;
    
                // Find the inorder predecessor
                while (prev.right != null && prev.right != curr) {
                    prev = prev.right;
                }
    
                if (prev.right == null) {
                    // If right of predecessor is null, establish thread and move left
                    prev.right = curr;
                    prev.rightThread = true; // Set right thread flag
                    curr = curr.left;
                } else {
                    // If right of predecessor is not null, remove thread and move right
                    prev.right = null;
                    prev.rightThread = false; // Clear right thread flag
                    curr = curr.right;
                }
            }
        }
    }

    
    public void reverseMorrisTraversal(Node<T> node) {
        System.out.println("reverseMorrisTraversal");
    
        Node<T> curr = node;
    
        while (curr != null) {
            if (curr.right == null) {
                // If the right child is null, establish thread to the left child
                System.out.println(curr.data + " -> " + "THREAD to " + (curr.left != null ? curr.left.data : "null"));
                curr = curr.left;
            } else {
                Node<T> prev = curr.right;
    
                // Find the reverse inorder successor
                while (prev.left != null && prev.left != curr) {
                    prev = prev.left;
                }
    
                if (prev.left == null) {
                    // If left of reverse successor is null, establish thread and move right
                    prev.left = curr;
                    prev.leftThread = true; // Set left thread flag
                    curr = curr.right;
                } else {
                    // If left of reverse successor is not null, remove thread and move left
                    prev.left = null;
                    prev.leftThread = false; // Clear left thread flag
                    curr = curr.left;
                }
            }
        }
    }

    void print(Node<T> node){

        if(node != null){
            print(node.left);
            System.out.println("node: " + node.data);
            print(node.right);
        }
    }

    int getHeight(Node<T> N){

        if (N == null)
            return 0;

        return N.height;
        
    }

    int height(Node<T> node){

        if(node == null){
            return 0;
        }

        int l = height(node.left);
        int r = height(node.right);

        if(l > r){
            return l + 1;
        }else{
            return r + 1;
        }
    }

    int max(int a, int b){

        if(a > b){
            return a;
        }

        return b;
    }

    Node<T> rightRotate(Node<T> y){

        Node<T> x = y.left;
        Node<T> t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = max(height(y.left), height(y.right));
        x.height = max(height(x.left), height(x.right));

        return x;
    }  

    Node<T> leftRotate(Node<T> x){

        Node<T> y = x.right;
        Node<T> t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = max(height(x.left), height(x.right));
        y.height = max(height(y.left), height(y.right));

        return y;

    }

    int getBalance(Node<T> N){

        if(N == null){
            return 0;
        }

        return height(N.left) - height(N.right);
    }

    
    Node<T> insert(Node<T> node, T data){

        if(node == null){
            node = new Node<T>(data);
            node.height = 0;
            return node;
        }

        if(data.compareTo(node.data) < 0){

            node.left = insert(node.left, data);

        }else if(data.compareTo(node.data) > 0){
            
            node.right = insert(node.right, data);
            
        }else{

            return node;
        }

        node.height = 1 + max(getHeight(node.left), getHeight(node.right));

        int balance = getBalance(node);

        if(balance > 1 && data.compareTo(node.left.data) < 0){
            return rightRotate(node);
        }

        if(balance < -1 && data.compareTo(node.right.data) > 0){
            return leftRotate(node);
        }

        if(balance > 1 && data.compareTo(node.left.data) > 0){
            
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if(balance < -1 && data.compareTo(node.right.data) < 0){
            
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        convertAVLtoThreaded(node);

        return node;

    }

    Node<T> minValue(Node<T> node){

        Node<T> current = node;

        while(current.left != null){
            current = current.left;
        }

        return current;
    }

    Node<T> remove(Node<T> root, T data){

        if(root == null){
            return root;
        }

        if(data.compareTo(root.data) < 0){

            root.left = remove(root.left, data);

        }else if(data.compareTo(root.data) > 0){

            root.right = remove(root.right, data);

        }else{

            if(root.left == null || root.right == null){

                Node<T> temp = null;

                if(temp == root.left){

                    temp = root.right;

                }else{

                    temp = root.left;

                }

                if(temp == null){

                    temp = root;
                    root = null;

                }else{

                    root = temp;

                }

            }else{

                Node<T> temp = minValue(root.right);

                root.data = temp.data;

                root.right = remove(root.right, temp.data);
            }
        }

        if(root == null){

            return root;

        }

        root.height = max(height(root.left), height(root.right));

        int balance = getBalance(root);

        if(balance > 1 && getBalance(root.left) >= 0){
            return rightRotate(root);

        }

        if(balance > 1 && getBalance(root.left) < 0){
            root.left = leftRotate(root.left);
            return rightRotate(root);

        }

        if(balance < -1 && getBalance(root.right) <= 0){
            return leftRotate(root);

        }

        if(balance < -1 && getBalance(root.right) > 0){
            root.right = rightRotate(root.right);
            return leftRotate(root);

        }

        convertAVLtoThreaded(root);

        return root;

    }
    
    

    

}
