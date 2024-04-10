public class Graph {
    private Vertex[] vertices;
    public Graph(int numVertex){
        vertices = new Vertex[numVertex];
    }

    public boolean addVertex(String nName, int numVertices){
        int openPos = -1;
        for(int i=0; i < vertices.length; i++)
        {
            if(vertices[i] == null){
                if(openPos == -1)
                    openPos = i;
            } else {
                if(vertices[i].getVName().equals(nName)){
                    return false;
                }
            }
        }
        if(openPos == -1)
            return false;

        vertices[openPos] = new Vertex(nName, numVertices);
        return true;
    }

    public Vertex getVertex(String nName){
        for(int i=0; i < vertices.length; i++){
            if(vertices[i] != null && vertices[i].getVName().equals(nName)){
                return vertices[i];
            }
        }
        return null;
    }

    public boolean addEdge(String pointA, String pointB, float value, String vName){
        Vertex pA = getVertex(pointA);
        Vertex pB = getVertex(pointB);
        if(pA == null || pB == null)
            return false;

        Edge v = new Edge(value, vName);
        v.pointA = pA;
        v.pointB = pB;
        pA.addEdge(v);
        return true;
    }

    //Do not change the above functions
    //Implement the functions below
    public boolean isAccessable(Vertex vertexFrom, Vertex vertexTo){

        return vertexFrom.isAccessable(vertexTo);
    }


    public float shortestDistance(Vertex vertexFrom, Vertex vertexTo) {
        int numVertices = vertices.length;
    
        // Create arrays to track distances and visited vertices
        float[] distance = new float[numVertices];
        boolean[] visited = new boolean[numVertices];
    
        // Initialize arrays
        for (int i = 0; i < numVertices; i++) {
            distance[i] = Float.MAX_VALUE;  // Initialize distances to infinity
            visited[i] = false;
        }
    
        // Set the distance from the source vertex (vertexFrom) to itself to 0
        distance[getIndex(vertexFrom)] = 0;
    
        // Main loop to find the shortest path
        for (int i = 0; i < numVertices - 1; i++) {
            // Find the vertex with the minimum distance value among the unvisited vertices
            int minDistanceIndex = minDistance(distance, visited);
    
            // Mark the selected vertex as visited
            visited[minDistanceIndex] = true;
    
            // Update the distance values of the neighboring vertices
            for (Edge edge : vertices[minDistanceIndex].getEdges()) {
                if (edge != null && !visited[getIndex(edge.pointB)]) {
                    float newDistance = distance[minDistanceIndex] + edge.getValue();
                    if (newDistance < distance[getIndex(edge.pointB)]) {
                        distance[getIndex(edge.pointB)] = newDistance;
                    }
                }
            }
        }
    
        // Return the shortest distance to the target vertex (vertexTo)
        return distance[getIndex(vertexTo)];
    }
    
    private int minDistance(float[] distance, boolean[] visited) {
        float min = Float.MAX_VALUE;
        int minIndex = -1;
    
        for (int i = 0; i < distance.length; i++) {
            if (!visited[i] && distance[i] < min) {
                min = distance[i];
                minIndex = i;
            }
        }
    
        return minIndex;
    }
    

  

    public Vertex[] shortestPath(Vertex vertexFrom, Vertex vertexTo) {
        int numVertices = vertices.length;
    
        // Initialize arrays for distances and previous vertices
        float[] distance = new float[numVertices];
        int[] previousVertex = new int[numVertices];
    
        // Initialize distances to infinity and previous vertices to -1
        for (int i = 0; i < numVertices; i++) {
            distance[i] = Float.POSITIVE_INFINITY;
            previousVertex[i] = -1;
        }
    
        // Set the distance from vertexFrom to itself to 0
        distance[getIndex(vertexFrom)] = 0.0f;
    
        // Relax edges repeatedly
        for (int i = 0; i < numVertices - 1; i++) {
            for (Vertex currentVertex : vertices) {
                int currentVertexIndex = getIndex(currentVertex);
    
                for (Edge edge : currentVertex.getEdges()) {
                    if (edge != null) {
                        Vertex neighbor = edge.pointB;
                        int neighborIndex = getIndex(neighbor);
                        float edgeWeight = edge.getValue();
    
                        // Relax the edge
                        if (distance[currentVertexIndex] != Float.POSITIVE_INFINITY
                                && distance[currentVertexIndex] + edgeWeight < distance[neighborIndex]) {
                            distance[neighborIndex] = distance[currentVertexIndex] + edgeWeight;
                            previousVertex[neighborIndex] = currentVertexIndex;
                        }
                    }
                }
            }
        }
    
        // Check for negative cycles
        for (Vertex currentVertex : vertices) {
            int currentVertexIndex = getIndex(currentVertex);
    
            for (Edge edge : currentVertex.getEdges()) {
                if (edge != null) {
                    Vertex neighbor = edge.pointB;
                    int neighborIndex = getIndex(neighbor);
                    float edgeWeight = edge.getValue();
    
                    // If a shorter path is found, there is a negative cycle
                    if (distance[currentVertexIndex] != Float.POSITIVE_INFINITY
                            && distance[currentVertexIndex] + edgeWeight < distance[neighborIndex]) {
                        // Handle negative cycle as needed (e.g., throw an exception)
                        System.out.println("Negative cycle detected!");
                        return null;
                    }
                }
            }
        }
    
        // Reconstruct the path from vertexFrom to vertexTo
        int currentIndex = getIndex(vertexTo);
        if (distance[currentIndex] == Float.POSITIVE_INFINITY) {
            // No path exists
            return null;
        }
    
        // Count the number of vertices in the path
        int pathLength = 1;
        while (previousVertex[currentIndex] != -1) {
            pathLength++;
            currentIndex = previousVertex[currentIndex];
        }
    
        // Create the path array
        Vertex[] path = new Vertex[pathLength];
        currentIndex = getIndex(vertexTo);
        // Populate the path array in reverse order
        for (int i = pathLength - 1; i >= 0; i--) {
            path[i] = vertices[currentIndex];
            currentIndex = previousVertex[currentIndex];
        }
    
        return path;
    }
    


    public boolean containsCycle(Vertex startingVertex){
        
        int numVertices = vertices.length;
        
        boolean [] visited = new boolean[numVertices];
        boolean [] path_visited = new boolean[numVertices];

        for(int i = 0; i < numVertices; i++){
            if(!visited[i]){
                if(dfs_cycle_detection(startingVertex, i, vertices, visited, path_visited) == true){
                    return true;
                }
            }
        }

        return false;
    }



    public boolean dfs_cycle_detection(Vertex start, int index, Vertex[] vertices, boolean[] visited, boolean[] path_visited) {
        visited[index] = true;
        path_visited[index] = true;
    
        // traverse adjacent nodes
        for (Edge edge : start.getEdges()) {
            if (edge != null) {
                int nextIndex = getIndex(edge.pointB);
    
                if (!visited[nextIndex]) {
                    if (dfs_cycle_detection(edge.pointB, nextIndex, vertices, visited, path_visited)) {
                        //System.out.println(vertices[nextIndex].getVName());
                        return true;
                    }
                } else if (path_visited[nextIndex]) {
                    //System.out.println(vertices[nextIndex].getVName());
                    return true;
                }
            }
        }
    
        path_visited[index] = false;
        return false;
    }
    

    public void listCycles() {
        int numVertices = vertices.length;
        boolean[] visited = new boolean[numVertices];
        boolean[] stack = new boolean[numVertices];
    
        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && dfsCycleDetectionAndPrint(vertices[i], visited, stack)) {
                System.out.println();
            }
        }
    }

    private boolean dfsCycleDetectionAndPrint(Vertex start, boolean[] visited, boolean[] stack) {
        int index = getIndex(start);
    
        if (!visited[index]) {
            visited[index] = true;
            stack[index] = true;
    
            for (Edge edge : start.getEdges()) {
                if (edge != null) {
                    int nextIndex = getIndex(edge.pointB);
    
                    if (!visited[nextIndex]) {
                        if (dfsCycleDetectionAndPrint(edge.pointB, visited, stack)) {
                            System.out.print(vertices[nextIndex].getVName() + ";");
                            return true;
                        }
                    } else if (stack[nextIndex]) {
                        System.out.print(vertices[nextIndex].getVName() + ";");
                        return true;
                    }
                }
            }
        }
    
        stack[index] = false;
        return false;
    }


    public int countEdges() {
        int edgeCount = 0;
    
        for (Vertex vertex : vertices) {
            if (vertex != null) {
                edgeCount += vertex.getEdges().length;
            }
        }
    
        // Since each edge is counted twice (once for each endpoint), divide by 2
        return edgeCount;
    }
    



    public String DFT(Vertex startVertex){

        int numVertices = vertices.length;

        StringBuilder result = new StringBuilder();

        boolean [] visited = new boolean[numVertices];

        DFTRecursion(startVertex, visited, result);

        return result.toString();

    }

    private void DFTRecursion(Vertex v, boolean [] visited, StringBuilder result){

        result.append(v.getVName()).append(";");
        visited[getIndex(v)] = true;

        for(Edge edge: v.getEdges()){
            if(edge != null && !visited[getIndex(edge.pointB)]){
                DFTRecursion(edge.pointB, visited, result);
            }
        }



    }


    private int getIndex(Vertex v){

        for(int i = 0; i < vertices.length; i++){
            if(vertices[i] == v){
                return i;
            }
        }

        return -1;
    }

 
}
