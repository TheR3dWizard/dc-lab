package exp4.Centralised;

import java.util.HashSet;
import java.util.HashMap;


public class Graph<T> {
    private HashSet<Process> nodes;
    private HashMap<Process, HashSet<Process>> edges;

    public Graph(){
        nodes = new HashSet<>();
        edges = new HashMap<>();
    }

    public void addNode(Process node){
        nodes.add(node);
        edges.put(node, new HashSet<Process>());
    }

    public void addEdge(Process node1, Process node2){
        if(!nodes.contains(node1) || !nodes.contains(node2)){
            System.out.println("Node not found");
            return;
        }
        edges.get(node1).add(node2);
        edges.get(node2).add(node1);
    }

    public boolean isCyclic(){
        HashSet<Process> visited = new HashSet<>();
        for (Process node : nodes) {
            if (!visited.contains(node)){
                if (isCyclicUtil(node, visited, null)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCyclicUtil(Process node, HashSet<Process> visited, Process parent){
        visited.add(node);
        for (Process neighbour : edges.get(node)) {
            if (!visited.contains(neighbour)){
                if (isCyclicUtil(neighbour, visited, node)){
                    return true;
                }
            }
            else if (!neighbour.equals(parent)){
                return true;
            }
        }
        return false;
    }

    public Graph<T> Union(Graph<T> g1, Graph<T> g2){
        Graph<T> union = new Graph<>();
        for (Process node : g1.nodes) {
            union.addNode(node);
        }
        for (Process node : g2.nodes) {
            union.addNode(node);
        }
        for (Process node : g1.nodes) {
            for (Process neighbour : g1.edges.get(node)) {
                union.addEdge(node, neighbour);
            }
        }
        for (Process node : g2.nodes) {
            for (Process neighbour : g2.edges.get(node)) {
                union.addEdge(node, neighbour);
            }
        }
        return union;
    }
}
