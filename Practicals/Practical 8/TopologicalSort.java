public class TopologicalSort extends Sorting {
    public TopologicalSort(){
        this.name = "TopologicalSort";
    }

    public int index;
    public int vertexCount;

    @Override
    public String[] sortAcs(Vertex[] array) throws CycleException {

        int length = array.length;

        String[] result = new String[length];
        boolean[] visited = new boolean[length];
        boolean[] recStack = new boolean[length];

        // Initialize index to the last position in the result array
        index = length - 1;

        for (int i = 0; i < length; i++) {
            if (!visited[i]) {
                if (dfs(i, visited, recStack, array, result)) {
                    throw new CycleException();
                }
            }
        }

        return result;

    }

    @Override
    public String[] sortDsc(Vertex[] array) throws CycleException{

        String[] ascOrder = sortAcs(array);
        String[] dscOrder = new String[ascOrder.length];

        // Reverse the array to get descending order
        int j = 0;
        for (int i = ascOrder.length - 1; i >= 0; i--) {
            dscOrder[j++] = ascOrder[i];
        }

        return dscOrder;

    }


    private int findIndex(Vertex [] array, Vertex vertex){

        for (int i = 0; i < array.length; i++) {
            if (array[i] == vertex) {
                return i;
            }
        }
        return -1;

    }

    private boolean dfs(int v, boolean[] visited, boolean[] recStack, Vertex[] array, String[] result) throws CycleException{
        visited[v] = true;
        recStack[v] = true;

        for (Edge edge : array[v].getEdges()) {
            if (edge != null) {
                Vertex neighbor = (edge.pointA == array[v]) ? edge.pointB : edge.pointA;
                int neighborIndex = findIndex(array, neighbor);

                if (!visited[neighborIndex]) {
                    if (dfs(neighborIndex, visited, recStack, array, result)) {
                        return true;
                    }
                } else if (recStack[neighborIndex]) {
                    // Cycle detected
                    throw new CycleException();
                }
            }
        }

        recStack[v] = false;
        result[index--] = array[v].getVName();
        return false;
    }

    

}

