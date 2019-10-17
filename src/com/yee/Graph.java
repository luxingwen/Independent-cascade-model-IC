package com.yee;

import java.util.ArrayList;
import java.util.*;
import java.util.Map;

public class Graph {
    private Map<Vertex, List<Edge>> adjVertices;

    Graph(){
        this.adjVertices=new HashMap<Vertex, List<Edge>>();
    }

    public void addVertex(String label) {
        this.adjVertices.put(new Vertex(label), new ArrayList<Edge>());
    }

    public void addEdge(String label1, String label2, double weight){
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        Edge e1 = new Edge(v2,weight);
        this.adjVertices.get(v1).add(e1);
    }

    public void addEdge(String label, Edge edge){
        Vertex v = new Vertex(label);
        this.adjVertices.get(v).add(edge);
    }

    public void printGraph(){
        this.adjVertices.forEach((vertex, edges) -> {
            for(int i = 0 ;i < edges.size(); i++){
                Edge e = edges.get(i);
                System.out.println(vertex.label + " -> " + e.getVertex().label + "-> weight: "+ e.getWeight());
            }
        });
    }

    public Map<Vertex, List<Edge>> getG(){
        return this.adjVertices;
    }

}
