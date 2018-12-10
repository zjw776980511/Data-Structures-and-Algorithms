/**
 * Program Description: This class is the implementation of the graphs edge.
 * @author roykim
 * Date: December 6, 2018
 * Student Number: 250 967 821
 */
public class GraphEdge {

    // Declaration of the GraphNode variables that is the start and the end. The char is
    // bus line that the GraphNodes are connected by which is the GraphEdge.
    private GraphNode start;
    private GraphNode end;
    private char busLine;

    /**
     * Constructor for the class which initializes a new GraphEdge object which stores the start, end GraphNodes and the
     * bus line character that connects the nodes.
     * @param u The start of the edge
     * @param v The end of the edge
     * @param busLine The character that connects the start and end
     */
    public GraphEdge(GraphNode u, GraphNode v, char busLine){
        this.start = u;
        this.end = v;
        this.busLine = busLine;
    }

    /**
     * Returns the start of the edge
     * @return The GraphNode that starts the edge
     */
    public GraphNode firstEndpoint(){
        return this.start;
    }

    /**
     * Returns the end of the edge
     * @return The GraphNode that ends the edge
     */
    public GraphNode secondEndpoint(){
        return this.end;
    }

    /**
     * Returns the char that connects the start and end GraphNodes
     * @return The char that connects the edge
     */
    public char getBusLine(){
        return this.busLine;
    }

}
