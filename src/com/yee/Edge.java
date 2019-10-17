package com.yee;

public class Edge {
    private Vertex vertex;
    private double weight;

    Edge(Vertex vertex, double weight){
        this.vertex=vertex;
        this.weight=weight;
    }

    public Vertex getVertex() {
        return this.vertex;
    }

    public  double getWeight(){
        return this.weight;
    }
}
