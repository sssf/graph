
import java.util.*;

public class Graph {
//TODO: ShotgunList
    private List<Vertex> vertices = new LinkedList<Vertex>();      // use a set?
    private List<Edge> edges = new LinkedList<Edge>();

    private Vertex getVertex(String id) {
        for (Vertex v : this.vertices) {
            if (v.id.equals(id)) {
                //System.out.println("getVertex found");
                return v;
            }
            //System.out.println("getVertex loop");
        }
        //System.out.println("getVertex loop ended");
        return null;
    }

    public boolean addVertex(String id) {
        Vertex v = this.getVertex(id);
        if (v == null) {
            v = new Vertex(id);
            this.vertices.add(v);
            return true;
        }
        return false;
    }

    private Edge getEdge(Vertex from, Vertex to) {
        for (Edge e : this.edges) {
            if (e.from == from && e.to == to) {
                return e;
            }
        }
        return null;
    }

    public boolean connect(String from, String to, int weight) {
        Vertex a = this.getVertex(from);
        Vertex b = this.getVertex(to);
        Edge e = this.getEdge(a, b);
        if (e == null) {
            e = new Edge(a, b, weight);
            this.edges.add(e);
            return true;
        }
        return false;
    }

    public String toString() {
        String s = "";
        for (Edge e : this.edges) {
            s += e.from.id + " -> " + e.to.id + " : " + e.weight + "\n";
        }
        return s;
    }

    private void resetVertices() {
       for (Vertex v : this.vertices) {
            v.cost = -1;
            v.visited = false;
            v.previous = null;
       }
    }

    private List<Edge> getAdjecent(Vertex v) {
        List<Edge> l = new LinkedList<Edge>();
        for (Edge e : this.edges) {
            if (e.from.id.equals(v.id)) {
                l.add(e);
            }
        }
        return l;
    }

    private boolean hasUnvisited() {
        for (Vertex v : this.vertices) {
            if (!v.visited) {
                return true;
            }
        }
        return false;
    }

    private Vertex getMinCost() {
        Vertex min = this.vertices.get(0);
        for (Vertex v : this.vertices) {
            if (!v.visited) {
                if (v.cost != -1 && min.cost > v.cost) { // TODO: something is wrong, sooo wrong!
                    min = v;
                }
            }
        }
        return min;
    }

    public String search(String from, String to) {
        Vertex start = this.getVertex(from);
        Vertex stop  = this.getVertex(to);

        this.resetVertices();
        start.cost = 0;
        start.previous = null;

        List<Edge> adjacent = null;

        while (this.hasUnvisited()) {
            Vertex current = this.getMinCost();
            adjacent = this.getAdjecent(current);
            current.visited = true;
            System.out.print("prev: " + (current.previous != null ? current.previous.id : "null             "));
            System.out.println("\t\tid:   " + current.id);

            for (Edge e : adjacent) {
                if (e.to.cost == -1 || e.to.cost > current.cost + e.weight) {
                    e.to.cost = current.cost + e.weight;
                    e.to.previous = current;
                }
            }
        }
        String path = "";
        Vertex i = stop;
        while (i != null) {
            path= i.id + "\n" + path;
            i = i.previous;
        }
        System.out.println(path);
        return path;
    }




private class Vertex {
    public String id;
    public int cost = -1;
        public boolean visited = false;
        public Vertex previous = null;

        Vertex(String id) {
            this.id = id;
        }
    }

    private class Edge {
        public Vertex from;
        public Vertex to;
        public int weight;
        Edge(Vertex from, Vertex to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
