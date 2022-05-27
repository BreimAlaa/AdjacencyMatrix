import javafx.util.Pair;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class Graph {
    private int[][] A;
    private int lastIndex;
    private boolean directed = false;
    private boolean multiEdges = false;
    private boolean updated;

    HashMap<String, Integer> verticesIndex;
    HashMap<Pair<String, String>, Integer> edgesFrequency;
    HashSet<Pair<String, String>> edges;

    public Graph() {
        verticesIndex = new HashMap<>();
        edges = new HashSet<>();
        edgesFrequency = new HashMap<>();
    }

    private void addVertex(String vName){
        verticesIndex.put(vName, lastIndex++);
    }

    public void addEdge(String v1, String v2) {
        updated = false;
        if (!verticesIndex.containsKey(v1)) addVertex(v1);
        if (!verticesIndex.containsKey(v2))addVertex(v2);
        try {
            int prevFreq = edgesFrequency.get(new Pair<>(v1,v2));
            edgesFrequency.put(new Pair<>(v1,v2), ++prevFreq);

        }catch (NullPointerException e){
            edgesFrequency.put(new Pair<>(v1,v2), 1);
        }
        edges.add(new Pair<>(v1,v2));
    }

    private void createAdjacencyMatrix(){
        updated = true;
        A = new int[verticesIndex.size()][verticesIndex.size()];
        for (Pair<String, String> i : edgesFrequency.keySet()){
            if (multiEdges) A[verticesIndex.get(i.getKey())][verticesIndex.get(i.getValue())] += edgesFrequency.get(i) ;
            else A[verticesIndex.get(i.getKey())][verticesIndex.get(i.getValue())] = 1;
            if (!directed) A[verticesIndex.get(i.getValue())][verticesIndex.get(i.getKey())] = A[verticesIndex.get(i.getKey())][verticesIndex.get(i.getValue())];
        }
    }

    public void viewAdjacencyMatrix(){
        if (!updated) createAdjacencyMatrix();
        String str = "";
        System.out.print("   ");
        for(Map.Entry<String, Integer> m : verticesIndex.entrySet()){
            System.out.print((m.getKey()) + "  ");
            str += "\n" + m.getKey() + " " + Arrays.toString(A[m.getValue()]);
        }
        System.out.println(str);
    }

    public int[][] getAAdjacencyMatrix() {
        return A;
    }

    public void setDirected(boolean directed) {
        this.directed = directed;
    }

    public void setMultiEdges(boolean multiEdges) {
        this.multiEdges = multiEdges;
    }
}
