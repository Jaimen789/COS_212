import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A B+ tree generic node
 * Abstract class with common methods and data. Each kind of node implements this class.
 * @param <TKey> the data type of the key
 * @param <TValue> the data type of the value
 */
abstract class BPTreeNode<TKey extends Comparable<TKey>, TValue> {
	
	protected Object[] keys;
	protected int keyTally;
	protected int m;
	protected BPTreeNode<TKey, TValue> parentNode;
	protected BPTreeNode<TKey, TValue> leftSibling;
	protected BPTreeNode<TKey, TValue> rightSibling;
	protected static int level = 0; // do not modify this variable's value as it is used for printing purposes. You can create another variable with a different name if you need to store the level.

	protected BPTreeNode() 
	{
		this.keyTally = 0;
		this.parentNode = null;
		this.leftSibling = null;
		this.rightSibling = null;
	}

	public int getKeyCount() 
	{
		return this.keyTally;
	}
	
	@SuppressWarnings("unchecked")
	public TKey getKey(int index) 
	{
		return (TKey)this.keys[index];
	}

	public void setKey(int index, TKey key) 
	{
		this.keys[index] = key;
	}

	public BPTreeNode<TKey, TValue> getParent() 
	{
		return this.parentNode;
	}

	public void setParent(BPTreeNode<TKey, TValue> parent) 
	{
		this.parentNode = parent;
	}	
	
	public abstract boolean isLeaf();
	
	/**
	 * Print all nodes in a subtree rooted with this node
	 */
	@SuppressWarnings("unchecked")
	public void print(BPTreeNode<TKey, TValue> node)
	{
		level++;
		if (node != null) {
			System.out.print("Level " + level + " ");
			node.printKeys();
			System.out.println();

			// If this node is not a leaf, then 
        		// print all the subtrees rooted with this node.
        		if (!node.isLeaf())
			{	BPTreeInnerNode inner = (BPTreeInnerNode<TKey, TValue>)node;
				for (int j = 0; j < (node.m); j++)
    				{
        				this.print((BPTreeNode<TKey, TValue>)inner.references[j]);
    				}
			}
		}
		level--;
	}

	/**
	 * Print all the keys in this node
	 */
	protected void printKeys()
	{
		System.out.print("[");
    		for (int i = 0; i < this.getKeyCount(); i++)
    		{
        		System.out.print(" " + this.keys[i]);
    		}
 		System.out.print("]");
	}

	public void printValues() {
		for (int i = 0; i < this.getKeyCount(); i++) {
			System.out.print(getValue(i) + " ");
		}
		System.out.println();
	}
	


	////// You may not change any code above this line. You may add extra variables if need be //////

	////// Implement the functions below this line //////

	public abstract void setValue(int index, TValue value);

	public abstract TValue getValue(int index);

	public abstract BPTreeNode<TKey, TValue> getChild(int index);

	public abstract void setChild(int index, BPTreeNode<TKey, TValue> child);
	
	/**
	 * Search a key on the B+ tree and return its associated value using the index set. If the given key 
	 * is not found, null should be returned.
	 */

	@SuppressWarnings("unchecked")
	public TValue search(TKey key) 
	{
		int i = 0;

		while (i < keyTally && getKey(i).compareTo(key) < 0) {
			i++;
		}

		if(i < keyTally && ((Comparable<TKey>) keys[i]).compareTo(key) == 0){
			return this.getValue(i);
		}

		if(isLeaf()) return null;

		return getChild(i).search(key);


	}


	/**
	 * Insert a new key and its associated value into the B+ tree. The root node of the
	 * changed tree should be returned.
	 */
	
	public BPTreeNode<TKey, TValue> insert(TKey key, TValue value) {

		if(this.isLeaf()){

			return insertInToLeaf(key, value);


		}else if(!this.isLeaf()){

			int i = 0;

			while (i < keyTally && getKey(i).compareTo(key) < 0) {
				i++;
			}


			BPTreeNode<TKey, TValue> child = getChild(i);
			BPTreeNode<TKey, TValue> result = child.insert(key, value);
	
			// Check if the child node split and a new root is created
			if (result != child) {
				// Update the reference to the new root node
				return result;
			} else {
				// No split occurred, return this node
				//this.printKeys(); // Debugging purpose
				return this;
			}

		}

		return null;

		
	}

	public BPTreeNode<TKey, TValue> insertInToLeaf(TKey key, TValue value){

		keys[keyTally] = key;
		this.setValue(keyTally, value);

		keyTally++;

		this.insertionSort();

		System.out.println();

		if(keyTally == m){
			
			return splitNode();

		}


		return this;
	}

	@SuppressWarnings("unchecked")
	private BPTreeNode<TKey, TValue> splitNode() {
		
		if(this.isLeaf() && parentNode == null){

			BPTreeInnerNode<TKey, TValue> parent = new BPTreeInnerNode<>(m);

			int mid = m / 2;
			
			TKey medianKey = (TKey) keys[mid];

			BPTreeLeafNode<TKey, TValue> newLeafNode = new BPTreeLeafNode<>(m);
			
			newLeafNode.keyTally = m - mid;

			newLeafNode.parentNode = parent;

			newLeafNode.leftSibling = this;

			newLeafNode.rightSibling = rightSibling;

			if (rightSibling != null) {
				rightSibling.leftSibling = newLeafNode;
			}

			rightSibling = newLeafNode;

			// Move keys and values to the new leaf node
			for (int i = mid, j = 0; i < m; i++, j++) {
				newLeafNode.keys[j] = keys[i];
				newLeafNode.setValue(j, getValue(i));
			}
	
			// Update key tally for the original leaf node
			keyTally = mid;
	
			// Set the parent node reference for the original leaf node
			parentNode = parent;
	
			// Move the median key to the parent node
			parent.keys[0] = medianKey;
			parent.keyTally++;
	
			// Set child references for the parent node
			parent.references[0] = this;
			parent.references[1] = newLeafNode;
			
	
			return parent;

		}else if(this.isLeaf() && parentNode != null){

			int mid = (m) / 2;

			//System.out.println("mid: " + mid);
			TKey medianKey = (TKey) keys[mid];
			//System.out.println("median: " + medianKey);

			// Create a new leaf node for the right side of the split
			BPTreeLeafNode<TKey, TValue> newLeafNode = new BPTreeLeafNode<>(m);
			newLeafNode.keyTally = m - mid;

			// Move keys and values to the new leaf node
			for (int i = mid, j = 0; i < m; i++, j++) {
				newLeafNode.keys[j] = keys[i];
				newLeafNode.setValue(j, getValue(i));
			}

			// Update key tally for the original leaf node
			keyTally = mid;

			BPTreeLeafNode<TKey, TValue> lastLeafNode = (BPTreeLeafNode<TKey, TValue>) parentNode.getChild(parentNode.getKeyCount());

			//lastLeafNode.printKeys();

			// Insert the median key into the parent node
			BPTreeInnerNode<TKey, TValue> parent = (BPTreeInnerNode<TKey, TValue>) parentNode;

			
			int insertIndex = findInsertIndex(parent, medianKey);

			shiftKeysRight(parent, insertIndex);
			//shiftReferencesRight(parent, insertIndex + 1);

			System.arraycopy(parent.references, insertIndex + 1, parent.references, insertIndex + 2, parent.keyTally - insertIndex);
        	parent.references[insertIndex + 1] = newLeafNode;
			
			parent.keys[insertIndex] = medianKey;
			parent.keyTally++;

		if(parentNode.keyTally >= m){

			System.out.println("overflow");

			BPTreeInnerNode<TKey, TValue> inner1 = new BPTreeInnerNode<TKey, TValue>(m);
			BPTreeInnerNode<TKey, TValue> inner2 = new BPTreeInnerNode<TKey, TValue>(m);

			BPTreeInnerNode<TKey, TValue> parentTemp = (BPTreeInnerNode<TKey, TValue>) parentNode;

			//     [inner 1]
			//     /     \
			// [parent] [inner2]

			TKey middleKey = (TKey) parentTemp.keys[mid];

			System.out.println("middle key: " + middleKey);

			inner2.keyTally = m - mid;

			// Move keys and values to the new leaf node
			for (int i = mid, j = 0; i < m; i++, j++) {
				inner2.keys[j] = parentTemp.keys[i];
				inner2.setValue(j, getValue(i));
				//inner2.setChild(j, getChild(i));
			}

			parentTemp.keyTally = mid;

			int insertIndex1 = findInsertIndex(inner1, middleKey);

			shiftKeysRight(inner1, insertIndex1);

			inner1.keys[insertIndex1] = middleKey;
			inner1.keyTally++;

			System.arraycopy(parentTemp.references, mid + 1, inner2.references, 0 , parent.keyTally - insertIndex);
			
			Arrays.fill(parentTemp.references, mid + 1 , m + 1, null);
			


			parentTemp.setParent(inner1);
			inner2.setParent(inner1);

			inner1.setChild(0, parentTemp);
			inner1.setChild(1, inner2);
		

			return inner1;


		}

			return parent;

		}

		return this;
	}

	@SuppressWarnings("unchecked")
	private int findInsertIndex(BPTreeInnerNode<TKey, TValue> node, TKey key) {

		//node.printKeys();

		int index = 0;
		while (index < node.keyTally && ((Comparable<TKey>) node.keys[index]).compareTo(key) < 0) {
			index++;

		}

		return index;
	}
	

	private void shiftKeysRight(BPTreeInnerNode<TKey, TValue> node, int startIndex) {
		for (int i = node.keyTally; i > startIndex; i--) {
			node.keys[i] = node.keys[i - 1];
		}
	}

	
	
	

	@SuppressWarnings("unchecked")
	public void insertionSort(){

		int n = keyTally;

		for(int i = 1; i < n; i++){

			TKey key = (TKey) keys[i]; 
			TValue value = this.getValue(i);

			int j = i - 1;

			while(j >= 0 && getKey(j).compareTo(key) > 0){
				
				keys[j + 1] = keys[j];

				setValue(j + 1, getValue(j));

				j--;
			}

			keys[j + 1] = key;

			setValue(j + 1, value);
		}

	}


	/**
	 * Delete a key and its associated value from the B+ tree. The root node of the
	 * changed tree should be returned.
	 */

	// public BPTreeNode<TKey, TValue> delete(TKey key) 
	// {

	// }

	

	/**
	 * Return all associated key values on the B+ tree in ascending key order using the sequence set. An array
	 * of the key values should be returned.
	 */

	@SuppressWarnings("unchecked")
	public TValue[] values() {
		List<TValue> valueList = new ArrayList<>();
		traverseAndCollectValues(this, valueList);
		return valueList.toArray((TValue[]) new Object[valueList.size()]);
	}

	private void traverseAndCollectValues(BPTreeNode<TKey, TValue> node, List<TValue> valueList) {
		if (node == null) {
			return;
		}
		if (node.isLeaf()) {
			for (int i = 0; i < node.getKeyCount(); i++) {
				valueList.add(node.getValue(i));
			}
		} else {
			for (int i = 0; i <= node.getKeyCount(); i++) {
				traverseAndCollectValues(node.getChild(i), valueList);
			}
		}
}



	//HELPER FUNCTIONS


}