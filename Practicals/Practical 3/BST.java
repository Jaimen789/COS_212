import java.util.ArrayList;
import java.util.List;


public class BST<T extends Comparable<T>> {
    protected BSTNode<T> root;

    public BST(){};

    public boolean insert(T val){
        if(root == null){
            root = new BSTNode<>(val);
            return true;
        } else {
            return root.recInsert(val);
        }
    }

    //Place code below

    public int numNodes(){
        return countNodes(root);
    }

    public Object[] toArray(){

        int size = countNodes(root);
        Object[] arr = new Object[size];

        getArray(root, arr, new int[]{0});
        return arr;
    }

    public boolean contains(T val){
        
        return containsNode(root, val);
    }

    public boolean isEmpty(){
        if(root == null){
            return true;
        }

        return false;
    }

    public BSTNode<T> find(T val){
       
        return findNode(root, val);
    }

    public int height(){
        
        return getHeight(root);
    }

    public Object[] nodesOnHeight(int h) {
        List<Object> nodesList = new ArrayList<>();
        nodesOnHeightHelper(root, h, nodesList);
        return nodesList.toArray();
    }

    

    public String DFT(){
        
        StringBuilder result = new StringBuilder();

        inorderTraversal(root, result);

        return result.toString();
    }

    public String BFT(){
        
        StringBuilder result = new StringBuilder();

        int h  = getHeight(root);

        for(int i = 1; i <= h; i++){
            printCurrentLevel(root, i, result);
        }

        return result.toString();

    }

    public T maxVal(){
        
        return getMax(root);
    }

    public T minVal(){
        
        return getMin(root);

    }

    //ADD HELPER FUNCTIONS HERE
    public int countNodes(BSTNode<T> node){

        if(node == null){
            return 0;
        }

        int l = countNodes(node.left);
        int r = countNodes(node.right);


        return 1 + l + r;
    }

    public Object[] getArray(BSTNode<T> node,  Object[] dataArray, int [] index){

        if(node != null){
            getArray(node.left, dataArray, index);
            dataArray[index[0]++] = node.getVal();
            getArray(node.right, dataArray, index);
        }

        return dataArray;
        
    }

    public boolean containsNode(BSTNode<T> node, T val){
        
        while(node != null){
            if(node.getVal() == val) return true;
            if(node.getVal().compareTo(val) > 0) node = node.left;
            else node = node.right;
        }

        return false;
        
    }

    public BSTNode<T> findNode(BSTNode<T> node, T val){

        while(node != null){
            if(node.getVal() == val) return node;
            if(node.getVal().compareTo(val) > 0) node = node.left;
            else node = node.right;
        }

        return null;
    }

    public int countNodesAtLevel(BSTNode<T> node, int level){
        if(node == null){
            return 0;
        }

        int res = 0;

        if(level == 1){
            return 1;
        }else if(level > 1){

            int left_node = countNodesAtLevel(node.left, level - 1);
            int right_node =  countNodesAtLevel(node.right, level - 1);

            res = left_node + right_node;

        }

        return res;
    }

    private void nodesOnHeightHelper(BSTNode<T> node, int h, List<Object> nodesList) {
        if (node == null) {
            return;
        }

        if (h == 0) {
            nodesList.add(node.getVal());
        } else {
            nodesOnHeightHelper(node.left, h - 1, nodesList);
            nodesOnHeightHelper(node.right, h - 1, nodesList);
        }
    }

    public int getHeight(BSTNode<T> node){

        if(node == null){
            return 0;
        }else{
            int l_height = getHeight(node.left);
            int r_height = getHeight(node.right);

            if(l_height > r_height){
                return (l_height + 1);
            }else{
                return (r_height + 1);
            }
        }
    }

    public void inorderTraversal(BSTNode<T> node, StringBuilder res){

        if(node == null){
            return;
        }

        inorderTraversal(node.left, res);
        res.append(node.toString());
        inorderTraversal(node.right, res);

    }

    public void printCurrentLevel(BSTNode<T> node, int level, StringBuilder res){

        if(node == null) return;

        if(level == 1){
            res.append(node.toString());
        }else if(level > 1){
            printCurrentLevel(node.left, level - 1, res);
            printCurrentLevel(node.right, level - 1, res);
            
        }
    }

    public T getMax(BSTNode<T> node){

        BSTNode<T> curr = node;

        while(curr.right != null){
            curr = curr.right;
        }

        return curr.getVal();
    }

    public T getMin(BSTNode<T> node){

        BSTNode<T> curr = node;

        while(curr.left != null){
            curr = curr.left;
        }

        return curr.getVal();
    }
   
}
