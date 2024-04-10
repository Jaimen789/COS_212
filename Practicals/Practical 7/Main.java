public class Main {
    public static void main(String[] args) throws Exception {
        ExampleA();
        ExampleB();
    }

    public static void ExampleA() {
        Graph g = new Graph(7);
        g.addVertex("0", 2);
        g.addVertex("1", 2);
        g.addVertex("2", 2);
        g.addVertex("3", 1);
        g.addVertex("4", 0);
        //
        g.addVertex("5", 1);
        g.addVertex("6", 0);

        g.addEdge("0", "1", 1, "01");
        g.addEdge("0", "2", 10, "02");
        g.addEdge("1", "3", 2, "13");
        g.addEdge("2", "3", -10, "23");
        g.addEdge("3", "4", 3, "34");
        g.addEdge("2", "1", 3, "21");
        g.addEdge("1", "5", 1, "15");
        g.addEdge("5", "6", 7, "56");

        System.out.println(g.DFT(g.getVertex("0")));

        System.out.println("Contains Cycle: " + g.containsCycle(g.getVertex("0")));

       

        Vertex[] shortestPath = g.shortestPath(g.getVertex("2"), g.getVertex("6"));

        if (shortestPath != null) {
            System.out.print("Shortest Path from 2 to 6: ");
            for (Vertex vertex : shortestPath) {
                System.out.print(vertex.getVName() + " ");
            }
            System.out.println();
        } else {
            System.out.println("No path from 2 to 6");
        }

        float distance = g.shortestDistance(g.getVertex("2"), g.getVertex("6"));
        if (distance != Float.MAX_VALUE) {
            System.out.println("Shortest Distance from 2 to 6: " + distance);
        } else {
            System.out.println("No path from 2 to 6");
        }

        int edgeCount = g.countEdges();
        System.out.println("Total number of edges: " + edgeCount);

        g.listCycles();

        System.out.println(g.isAccessable(g.getVertex("0"), g.getVertex("1")));

    //    for(int i=0; i<6; i++){
    //        for(int j=0; j<5; j++){
    //            String first = Integer.toString(i);
    //            String second = Integer.toString(j);
    //            //System.out.println(first + " " + second);
    //            System.out.println("index " + i + " " + j + ": " + g.isAccessable(g.getVertex(first), g.getVertex(second)));
    //            //System.out.println("index " + 5 + " " + 5 + ": " + g.isAccessable(g.getVertex("5"), g.getVertex("5")));
    //        }
    //    }
    //    System.out.println(g.shortestDistance(g.getVertex("0"), g.getVertex("4")));
    //    for (Vertex v : g.shortestPath(g.getVertex("0"), g.getVertex("4"))) {
    //        System.out.print(v.getVName() + ";");
    //    }
        System.out.println();
}

    public static void ExampleB() {
        Graph g = new Graph(5);

        g.addVertex("0", 1);
        g.addVertex("1", 1);
        g.addVertex("2", 2);
        g.addVertex("3", 1);
        g.addVertex("4", 1);
        g.addEdge("0", "2", 3, "02");
        g.addEdge("1", "0", 3, "10");
        g.addEdge("2", "3", 2, "23");
        g.addEdge("2", "4", 3, "24");
        g.addEdge("3", "1", 3, "31");
        g.addEdge("4", "3", 3, "43");

        System.out.println(g.DFT(g.getVertex("0")));

        
        System.out.println("Contains Cycle: " + g.containsCycle(g.getVertex("0")));

        int edgeCount = g.countEdges();
        System.out.println("Total number of edges: " + edgeCount);
        

        g.listCycles();


    }

    /*
     * Expected output:
     * 3.0
     * 0;2;3;4;
     * 0-2-3-1-0
     * 0;2;3;1;4
     */
}