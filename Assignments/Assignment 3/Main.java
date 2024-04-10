public class Main {
	

    public static void main(String[] args) 
    {
	//General tree
	//BPTree<Integer, Integer> tree = new BPTree<Integer, Integer>(4); // A B+ Tree with order 4

   
	// tree.insert(20, 20);
	// tree.insert(10, 10);
	// tree.insert(30, 30);
	// tree.insert(5, 5);

	// tree.print();
	// tree.insert(50, 50);

	//my own
//		tree.insert(5, 1000);
//		tree.insert(60, 8000);
//		tree.insert(70, 8000);
//		tree.insert(80, 8000);
//		tree.insert(90, 8000);
//		tree.insert(100, 8000);
//		tree.insert(101, 8000);
//		tree.insert(25, 8000);
//		tree.insert(7, 8000);
//		tree.insert(35, 8000);
//		tree.insert(22, 8000);
//		tree.insert(23, 8000);
//		tree.insert(31, 8000);
//		tree.insert(32, 8000);
//		tree.insert(33, 8000);
//		tree.insert(36, 8000);
//		tree.insert(37, 8000);
//		tree.insert(11, 8000);
//		tree.insert(13, 8000);
//		tree.insert(14, 8000);
//		tree.insert(27, 8000);

//		System.out.println("Structure of the constucted tree is: ");
//		tree.print();

	// tree.insert(40, 2000);
	// tree.insert(60, 7000);
	// tree.insert(35, 3000);
	// tree.insert(65, 3000);
	// tree.insert(70, 6000);
	// tree.insert(80, 4000);

	// System.out.println("Structure of the constucted tree is: ");
	// tree.print();

//	tree.delete(20);
//	tree.delete(50);
//	tree.insert(65,99);
//	tree.delete(20);
//	tree.delete(35);
//	tree.delete(35);
//	tree.delete(10);
//		tree.delete(35);

		// System.out.println("Structure of the constucted tree is: ");
		// tree.print();

		// Object[] arr = tree.values();

		// for(int i=0; i<arr.length; i++){
		// 	System.out.println("value at: " + i + " is: " + arr[i]);
		// }

	/*Integer value = 70; // Delete leaf also internal
	tree.delete(value);
	System.out.println("Structure of the tree after delete of: " + value);
	tree.print();

	value = 60; // Delete leaf
	tree.delete(value);
	System.out.println("Structure of the tree after delete of: " + value);
	tree.print();

	value = 50; // Delete leaf, underflow, borrow left
	tree.delete(value);
	System.out.println("Structure of the tree after delete of: " + value);
	tree.print();

	value = 40; // Delete leaf, underflow, borrow right
	tree.delete(value);
	System.out.println("Structure of the tree after delete of: " + value);
	tree.print();

	System.out.println("Search the tree for 80: ");
    	Integer result = (Integer)tree.search(80);
	if (result != null)
		System.out.println("Found key with value " + result);
	else
		System.out.println("Key not found!");

	System.out.println("Search the tree for 100: ");
    	result = (Integer)tree.search(100);
	if (result != null)
		System.out.println("Found key with value " + result);
	else
		System.out.println("Key not found!");

	System.out.println("Search the tree for 40: ");
	result = (Integer)tree.search(40);
	if (result != null)
		System.out.println("Found key with value " + result);
	else
		System.out.println("Key not found!");*/

	// DB student table indexes
	BPTree<Integer, Integer> pktree = new BPTree<Integer, Integer>(4); // A B+ Tree with order 4
	pktree.insert(16230943, 1);
	pktree.insert(17248830, 2);
	pktree.insert(16094340, 3);
	pktree.insert(17012340, 4);

	pktree.insert(15010300, 5);

	pktree.insert(17221030, 6);
	
	pktree.insert(17481030, 7);

	pktree.insert(17338030, 8);

	pktree.insert(17211030, 9);

	pktree.insert(16000000, 10);

	pktree.insert(16100000, 11);
	
	pktree.insert(16040000, 12);

	pktree.insert(16050000, 13);

	pktree.insert(16060000, 14);

	pktree.insert(16010000, 15);



	System.out.println();
	System.out.println("Structure of the constucted index is: ");
	pktree.print();
	
	Integer studentid = 16150000;
	System.out.println("Search the index tree for student: " + studentid);
	Integer result = (Integer)pktree.search(studentid);
	if (result != null)
		System.out.println("Found key with value " + result);
	else
		System.out.println("Key not found!");


	

	System.out.println("Return index tree values ordered by student id: ");
	Object[] array = pktree.values();
	if (array != null) {
		for (int i = 0; i < array.length; i++)
			System.out.println("Value " + array[i]);
	} else
		System.out.println("Index empty!");

	BPTree<String, Integer> sktree = new BPTree<String, Integer>(4); // A B+ Tree with order 4
	sktree.insert("Molefe", 1);
	sktree.insert("Muller", 2);
	sktree.insert("Botha", 3);
	sktree.insert("Evans", 4);
	System.out.println();
	System.out.println("Structure of the constucted index is: ");
	sktree.print();
	
	String surname = "Botha";
	System.out.println("Search the index tree for student: " + surname);
	result = (Integer)sktree.search(surname);
	if (result != null)
		System.out.println("Found key with value " + result);
	else
		System.out.println("Key not found!");

	System.out.println("Return index tree values ordered by surname: ");
	array = sktree.values();
	if (array != null) {
		for (int i = 0; i < array.length; i++)
			System.out.println("Value " + array[i]);
	} else
		System.out.println("Index empty!");
	}

	
	/* Expected Output:
	Structure of the constucted tree is:
	Level 1 [ 30 50 70]
	Level 2 [ 10 20]
	Level 2 [ 30 40]
	Level 2 [ 50 60]
	Level 2 [ 70 80 90]

	Structure of the tree after delete of: 70
	Level 1 [ 30 50 70]
	Level 2 [ 10 20]
	Level 2 [ 30 40]
	Level 2 [ 50 60]
	Level 2 [ 80 90]

	Structure of the tree after delete of: 60
	Level 1 [ 30 50 70]
	Level 2 [ 10 20]
	Level 2 [ 30 40]
	Level 2 [ 50]
	Level 2 [ 80 90]

	Structure of the tree after delete of: 50
	Level 1 [ 30 40 70]
	Level 2 [ 10 20]
	Level 2 [ 30]
	Level 2 [ 40]
	Level 2 [ 80 90]

	Structure of the tree after delete of: 40
	Level 1 [ 30 40 90]
	Level 2 [ 10 20]
	Level 2 [ 30]
	Level 2 [ 80]
	Level 2 [ 90]

	Search the tree for 80:
	Found key with value 4000
	Search the tree for 100:
	Key not found!
	Search the tree for 40:
	Key not found!

	Structure of the constucted index is:
	Level 1 [ 17012340]
	Level 2 [ 16094340 16230943]
	Level 2 [ 17012340 17248830]

	Search the index tree for student: 17248830
	Found key with value 2
	Return index tree values ordered by student id:
	Value 3
	Value 1
	Value 4
	Value 2

	Structure of the constucted index is :
	Level 1 [ Molefe]
	Level 2 [ Botha Evans]
	Level 2 [ Molefe Muller]

	Search the index tree for student: Botha
	Found key with value 3
	Return index tree values ordered by surname:
	Value 3
	Value 4
	Value 1
	Value 2
	*/



    
}