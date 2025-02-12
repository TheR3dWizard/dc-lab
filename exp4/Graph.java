package exp4;

import java.util.HashSet;


import java.util.HashMap;


public class Graph<T> {
    private HashSet<T> nodes;
    private HashMap<T, HashSet<T>> edges;

    public Graph(){
        nodes = new HashSet<>();
        edges = new HashMap<>();
    }

    public void addNode(T node){
        nodes.add(node);
        edges.putIfAbsent(node, new HashSet<T>());
    }

    public void addEdge(T node1, T node2){
        if(!nodes.contains(node1) || !nodes.contains(node2)){
            System.out.println("Node not found");
            return;
        }
        edges.get(node1).add(node2);
    }

    public void removeEdge(T node1,T node2){
        if(!nodes.contains(node1) || !nodes.contains(node2)){
            System.out.println("Node not found");
            return;
        }
        edges.get(node1).remove(node2);
    }

    public boolean isCyclic(){
        HashSet<T> visited = new HashSet<>();
        for (T node : nodes) {
            if (!visited.contains(node)){
                if (isCyclicUtil(node, visited, null)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCyclicUtil(T node, HashSet<T> visited, T parent){
        visited.add(node);
        for (T neighbour : edges.get(node)) {
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
        for (T node : g1.nodes) {
            union.addNode(node);
        }
        for (T node : g2.nodes) {
            union.addNode(node);
        }
        for (T node : g1.nodes) {
            for (T neighbour : g1.edges.get(node)) {
                union.addEdge(node, neighbour);
            }
        }
        for (T node : g2.nodes) {
            for (T neighbour : g2.edges.get(node)) {
                union.addEdge(node, neighbour);
            }
        }
        return union;
    }

    public HashSet<T> GetNodes(){
        return nodes;
    }
}
