public class Main{
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.setMultiEdges(true);
//        graph.setDirected(true);

        graph.addEdge("a", "b");
        graph.addEdge("a", "c");
        graph.addEdge("a", "d");
        graph.addEdge("c", "d");

        // Adding duplicates  will not effect the graph unless it's multi edges
        graph.addEdge("a", "c");
        graph.addEdge("a", "c");

        graph.viewAdjacencyMatrix();
    }
}
