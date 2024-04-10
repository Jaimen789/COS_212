public class Vertex {
    private String vName;
    private Edge[] edges;
    public Vertex(String vName, int numedges){
        this.vName = vName;
        edges = new Edge[numedges];
    }

    public String getVName(){
        return vName;
    }

    public Edge[] getEdges(){
        return edges;
    }

    public boolean addEdge(Edge e){
        if(e == null)
            return false;

        for(int i=0; i < edges.length; i++)
        {
            if(edges[i] == null)
            {
                edges[i] = e;
                return true;
            }
        }
        return false;
    }

    //Do not change the above functions
    //Implement the functions below 

    //DFS through the edges
    public boolean isAccessable(Vertex destination){
        
        boolean visited [] = new boolean[edges.length];

        return dfs(destination, visited);
    }

    public boolean dfs(Vertex destination, boolean [] visited){

        for(int i = 0; i < edges.length; i++){
            if(edges[i] != null && !visited[i]){
                visited[i] = true;
            }

            if(edges[i].pointB == destination){
                return true;
            }

            if(edges[i].pointB.isAccessable(destination)){
                return true;
            }
        }

        return false;
    }

    

    

   
}
