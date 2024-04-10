import java.util.NoSuchElementException;
import java.util.Random;

@SuppressWarnings("unchecked")
public class SkipList<T extends Comparable<? super T>>
{

	public int maxLevel;
	public SkipListNode<T>[] root;
	private int[] powers;
	private Random rd = new Random();

	SkipList(int i)
	{
		maxLevel = i;
		root = new SkipListNode[maxLevel];
		powers = new int[maxLevel];
		for (int j = 0; j < i; j++)
			root[j] = null;
		choosePowers();
		rd.setSeed(1230456789);
	}

	SkipList()
	{
		this(4);
	}

	public void choosePowers()
	{
		powers[maxLevel-1] = (2 << (maxLevel-1)) - 1;
		for (int i = maxLevel - 2, j = 0; i >= 0; i--, j++)
			powers[i] = powers[i+1] - (2 << j);
	}

	public int chooseLevel()
	{
		int i, r = Math.abs(rd.nextInt()) % powers[maxLevel-1] + 1;
		for (i = 1; i < maxLevel; i++)
		{
			if(r < powers[i])
				return i-1;
		}
		return i-1;
	}

	public boolean isEmpty(){
		for(int i = 0; i < maxLevel; i++){
			if(root[i] != null){
				return false;
			}
		}

		return true;
	}

	public void insert(T key)
	{
		
		if(isEmpty()){
			int level = chooseLevel();

			SkipListNode<T> newNode = new SkipListNode<T>(key, level + 1);

			for(int i = 0; i <= level; i++){
				root[i] = newNode;
			}
			
		}else{

			int level = chooseLevel();

			SkipListNode<T> newNode = new SkipListNode<T>(key, level + 1);

			SkipListNode<T>[] curr = new SkipListNode[maxLevel];
			SkipListNode<T>[] prev = new SkipListNode[maxLevel];

			// Initialize curr and prev pointers
			for (int i = 0; i < maxLevel; i++) {
				curr[i] = root[i];
				prev[i] = null;
			}

			// Search for the correct position to insert the new node
			for (int i = maxLevel - 1; i >= 0; i--) {
				while (curr[i] != null && curr[i].key.compareTo(key) < 0) {
					prev[i] = curr[i];
					curr[i] = curr[i].next[i];
				}
			}

			// Update pointers to insert the new node
			for (int i = 0; i <= level; i++) {
				newNode.next[i] = curr[i];
				if (prev[i] == null) {
					root[i] = newNode;
				} else {
					prev[i].next[i] = newNode;
				}
			}
		}

	}		

	public T first() {
		if (root[0] != null && root[0].key != null) {
			return root[0].key;
		}
		return null;
	}
	

	public T last() {
		SkipListNode<T> lastNonNullNode = null;
	
		// Iterate through levels
		for (int i = maxLevel - 1; i >= 0; i--) {
			SkipListNode<T> curr = root[i];
	
			// Iterate through nodes at the current level
			while (curr != null) {
				lastNonNullNode = curr;
				curr = curr.next[i];
			}
		}
	
		if (lastNonNullNode != null) {
			return lastNonNullNode.key;
		} else {
			return null;
		}
	}
	


	public T search(T key)
{
    SkipListNode<T>[] curr = new SkipListNode[maxLevel];

    for (int i = 0; i < maxLevel; i++) {
        curr[i] = root[i];
    }

    for (int i = maxLevel - 1; i >= 0; i--) {
        while (curr[i] != null && curr[i].key.compareTo(key) < 0) {
            curr[i] = curr[i].next[i];
        }
    }

    // After finding the node with the given key, check if the key matches
    if (curr[0] != null && curr[0].key.equals(key)) {
        return curr[0].key;
    } else {
        return null;
    }
}

}