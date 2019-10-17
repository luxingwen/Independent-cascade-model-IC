package com.yee;

public class Vertex {
    String label;
    Vertex(String label){
        this.label=label;
    }
    @Override
    public int hashCode() {
        return  ((label == null) ? 0 : label.hashCode());
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return label;
    }

}
