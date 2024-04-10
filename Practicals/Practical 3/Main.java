public class Main {
    public static void main(String[] args){
        BST<Integer> root = new BST<>();

        root.insert(73);
        root.insert(11); 
        root.insert(95);
        root.insert(7);
        root.insert(32);
        root.insert(82);
        root.insert(60);
        root.insert(87);
        root.insert(72);

        Object[] array = root.toArray();
        System.out.println("Array elements:");
        for (Object value : array) {
            System.out.print(value + " ");
        }

        System.out.println();

        if(root.contains(100)){
            System.out.println("yes");
        }else{
            System.out.println("no");
        }

        System.out.println(root.find(60).getVal());

        System.out.println(root.height());

        root.height();

        System.out.println("DFS");
        System.out.println(root.DFT());

        System.out.println();
        System.out.println("BFT");
        System.out.println(root.BFT());

        System.out.println();
        System.out.println("GetMax");
        System.out.println(root.maxVal());

        System.out.println();
        System.out.println("GetMin");
        System.out.println(root.minVal());

        System.out.println();
        System.out.println("NumNode at Height");
        System.out.println(root.countNodesAtLevel(root.root, 2));

        System.out.println();
        System.out.println("Get all Nodes at Height");
        Object [] res = root.nodesOnHeight(1);

        for (Object node : res) {
            System.out.print(node + " ");
        }

        System.out.println();


        // System.out.println(root.numNodesAtHeight(root.root, 4));

        //int numNode = root.numNodesAtHeight(root.root, 3);




        // root.nodesOnHeight(1);
        
        // root.nodesOnHeight(0);
        
        // System.out.println("min is: " + root.minVal());
        System.out.println(root.numNodes());
        //System.out.println("Contains is: " + root.height());

    }
}
