public class OrganisingList {

    public ListNode root = null;

    public OrganisingList() {}
    
    /**
     * Calculate the sum of keys recursively, starting with the given node until the end of the list
     * @return The sum of keys from the current node to the last node in the list
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int sumRec(ListNode node) {
        if(node.next == null){
            return node.key;
        }else{
            return sumRec(node.next) + node.key;
        }
    }

    /**
     * Calculate and set the data for the given node.
     * @return The calculated data for the given node
     * NOTE: 'int' and not 'Integer' here because it cannot return 'null'
     */
    public static int dataRec(ListNode node) {
        
        if(node.next == null){
            node.data = node.key;
            return node.key;
        }else{
            node.data = (node.key * sumRec(node) - dataRec(node.next));
            return (node.key * sumRec(node) - dataRec(node.next));
        }
        
    }

    /**
     * Calculate the data field of all nodes in the list using the recursive functions.
     * DO NOT MODIFY!
     */
    public void calculateData() {
        if (root != null) {
            dataRec(root);
        }
    }

    /**
     * Retrieve the data for the node with the specified key and swap the
     * accessed node with its predecessor, then recalculate data fields.
     * @return The data of the node before it has been moved,
     * otherwise 'null' if the key does not exist.
     */
    public Integer getData(Integer key) {
        //if root

        if(root.key == key){
            int ret = root.data;
            calculateData();
            return ret;
        }else{
            ListNode temp = root;
            ListNode curr = null;
            ListNode prev = null;
            ListNode prevprev = null;
            

            while(temp != null){
                if(temp.key == key){
                    curr = temp;
                }

                temp = temp.next;
            }

            if (curr == null) {
                System.out.println("Key not found");
                return null; 
            }

            temp = root;

            while(temp.key != key){
                prev = temp;
                temp = temp.next;
            }
             
            temp = root;

            while(temp.key != prev.key){
                prevprev = temp;
                temp = temp.next;
            }

            if(prevprev == null){
                
                prev.next = curr.next;
                curr.next = prev;
                root = curr;

                int ret = curr.data;
                calculateData();

                return ret;
            }
        
            
            prevprev.next = curr;
            prev.next = curr.next;
            curr.next = prev;
            
            int ret = curr.data;
            
            calculateData();

            return ret;

        }


    }

    /**
     * Delete the node with the given key.
     * calculateData() should be called after deletion.
     * If the key does not exist, do nothing.
     */
    public void delete(Integer key) {
        ListNode temp = root;

        if(root == null){ return;}

        if(temp.key == key){
            root = root.next;
        }else{

            ListNode prev = null;
            ListNode next = null;

            ListNode temp2 = root;



            while(temp != null){
                if(temp.key == key){
                    next = temp.next;
                }
                temp = temp.next;
            }

            while(temp2.key != key){
                prev = temp2;

                temp2 = temp2.next;
            }

            prev.next = next;

        }
    }


    /**
     * Insert a new key into the linked list.
     * 
     * New nodes should be inserted to the back
     * Duplicate keys should not be added.
     */
    public void insert(Integer key) {
        // Your code here...
        
        ListNode newNode = new ListNode(key);

        if(contains(key)) return;

        if(root == null){
            root = newNode;
            calculateData();
        }else{

            ListNode lastNode = root;

            while(lastNode.next != null){
                lastNode = lastNode.next;
            }

            lastNode.next = newNode;    
            calculateData();
       
        }
        
    }

    /**
     * Check if a key is contained in the list
     * @return true if the key is in the list, otherwise false
     */
    public Boolean contains(Integer key) {
        // Your code here...
        ListNode curr = root;

        while(curr != null){
            if(curr.key == key){
                return true;
            }

            curr = curr.next;
        }

        return false;
    }


    /**
     * Return a string representation of the Linked List.
     * DO NOT MODIFY!
     */
    public String toString() {
        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result.concat("[K: " + node.key + ", D: " + node.data + "]");

            if (node.next != null) {
                result = result.concat(" ");
            }
        }

        return result;
    }

    /**
     * Return a string representation of the linked list, showing only the keys of nodes.
     * DO NOT MODIFY!
     */
    public String toStringKeysOnly() {

        if (root == null) {
            return "List is empty";
        }

        String result = "";
        for (ListNode node = root; node != null; node = node.next) {
            result = result + node.key;

            if (node.next != null) {
                result = result.concat(", ");
            }
        }

        return result;
    }
    
}