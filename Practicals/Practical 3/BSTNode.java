public class BSTNode<T extends Comparable<T>> {
    private T value;
    public BSTNode<T> right;
    public BSTNode<T> left;

    public boolean recInsert(T val){
        if(val.compareTo(value) == 0)
            return false;

        if(val.compareTo(value) < 0)
        {
            if(left == null)
            {
                left = new BSTNode<>(val);
                return true;
            } else {
                return left.recInsert(val);
            }
        } else {
            if(right == null)
            {
                right = new BSTNode<>(val);
                return true;
            } else {
                return right.recInsert(val);
            }
        }
    }

    //Implement the following

    public BSTNode(T val){
       value = val;
    }

    public T getVal(){
       return this.value;
    }

    public String toString(){
        String left_node = "";
        String right_node = "";
        String current_node = "";

        String res = "";

        if(this.left == null){
            left_node = "L[]";
        }else{
            left_node = "L" + "[" + this.left.value.toString() + "]";
        }

        if(this.right == null){
            right_node = "R[]";
        }else{
            right_node = "R" + "[" + this.right.value.toString() + "]";
        }

        if(this.value == null){
            current_node = "V[]";
            left_node = "L[]";
            right_node = "R[]";
        }else{
            current_node = "V" + "[" + this.value.toString() +"]";
        }

        res = left_node + current_node + right_node + ";";

        return res;


    }

    //ADD HELPER FUNCTIONS HERE
}
