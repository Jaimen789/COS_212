@SuppressWarnings("unchecked")
class BTreeNode<T extends Comparable<T>> {
	boolean leaf;
	int keyTally;
	Comparable<T> keys[];
	BTreeNode<T> references[];
	int m;
	static int level = 0;
	// Constructor for BTreeNode class
	public BTreeNode(int order, boolean leaf1)
	{
    		// Copy the given order and leaf property
			m = order;
    		leaf = leaf1;
 
    		// Allocate memory for maximum number of possible keys
    		// and child pointers
    		keys =  new Comparable[2*m-1];
    		references = new BTreeNode[2*m];
 
    		// Initialize the number of keys as 0
    		keyTally = 0;
	}

	// Function to print all nodes in a subtree rooted with this node
	public void print()
	{
		level++;
		if (this != null) {
			System.out.print("Level " + level + " ");
			this.printKeys();
			System.out.println();

			// If this node is not a leaf, then 
        	// print all the subtrees rooted with this node.
        		if (!this.leaf)
			{	
				for (int j = 0; j < 2*m; j++)
    				{
        				if (this.references[j] != null)
						this.references[j].print();
    				}
			}
		}
		level--;
	}

	// A utility function to print all the keys in this node
	private void printKeys()
	{
		System.out.print("[");
    		for (int i = 0; i < this.keyTally; i++)
    		{
        		System.out.print(" " + this.keys[i]);
    		}
 		System.out.print("]");
	}

	// A utility function to give a string representation of this node
	public String toString() {
		String out = "[";
		for (int i = 1; i <= (this.keyTally-1); i++)
			out += keys[i-1] + ",";
		out += keys[keyTally-1] + "] ";
		return out;
	}

	////// You may not change any code above this line //////

	////// Implement the functions below this line //////

	// Function to insert the given key in tree rooted with this node

	public BTreeNode<T> insert(T key) {
		// space in leaf
		if (leaf && keyTally <= m - 1) {
			return insertInLeaf(key);
		}
	
		// no space in leaf, split
		if (leaf && keyTally > m - 1) {
			return splitNode();
		}
	
		// find the child index to insert the key
		int i = keyTally - 1;
		while (i >= 0 && keys[i].compareTo(key) > 0) {
			i--;
		}
		i++;
	
		// get the updated child from recursive insert
		BTreeNode<T> child = references[i];
		BTreeNode<T> updatedChild = child.insert(key);
	
		// check if the child was split
		if (updatedChild != child) {
			// shift keys and references to make space for the new child
			for (int j = keyTally; j > i; j--) {
				keys[j] = keys[j - 1];
				references[j + 1] = references[j];
			}
	
			// insert the key and the new child
			keys[i] = updatedChild.keys[0];
			references[i + 1] = updatedChild.references[1];
			keyTally++;
		}
	
		return this;
	}
	
	public BTreeNode<T> insertInLeaf(T key){

		keys[keyTally] = key;
		keyTally++;

		if (keyTally > m - 1) {
			return splitNode();
		}

		insertionSort();

		return this;
	}

	private void insertionSort() {
        int n = keyTally;

        for (int i = 1; i < n; ++i) {
            Comparable<T> key = keys[i];
            int j = i - 1;

            // Move elements of keys[0..i-1] that are greater than key
            // to one position ahead of their current position
            while (j >= 0 && (keys[j]).compareTo((T) key) > 0) {
                keys[j + 1] = keys[j];
                j = j - 1;
            }
            keys[j + 1] = key;
        }
    }
	

	private BTreeNode<T> splitNode() {

		BTreeNode<T> parent = new BTreeNode<>(m, false);
		

		int mid = (m - 1) / 2;

		parent.keys[0] = this.keys[mid];
		parent.keyTally++;

		BTreeNode<T> rightChild = new BTreeNode<>(m, leaf);
		for(int i = mid + 1; i < keyTally; i++){
			rightChild.keys[rightChild.keyTally] = this.keys[i];
			rightChild.keyTally++;
		}

		Comparable<T> leftChildKeys [] = new Comparable[2*m-1];

		for(int i = 0; i < mid; i++){
			leftChildKeys[i] = keys[i];
		}

		this.keys = leftChildKeys;
		this.keyTally = mid;

		for (int i = 0; i <= mid; i++) {
			parent.references[i] = this.references[i];
		}
		for (int i = mid + 1, j = 0; i < 2 * m; i++, j++) {
			rightChild.references[j] = this.references[i];
		}

		parent.references[0] = this;
		parent.references[1] = rightChild;


	
		return parent;
	}
	

	// Function to search a key in a subtree rooted with this node
	public BTreeNode<T> search(T key)
	{
		int i = 0;

		while(i < keyTally && keys[i].compareTo(key) < 0){
			i++;
		}

		if(i < keyTally && keys[i].compareTo(key) == 0){
			return this;
		}

		if(leaf) return null;

		return references[i].search(key);
	}

	// Function to traverse all nodes in a subtree rooted with this node
	public void traverse(){

		int i = 0;

		for(i = 0; i < keyTally; i++){
			if(!leaf){
				references[i].traverse();
			}

			System.out.print(keys[i] + " ");
		}

		

		if(!leaf){
			references[i].traverse();;
		}
	}

	



}