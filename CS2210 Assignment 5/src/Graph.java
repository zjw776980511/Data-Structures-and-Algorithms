/**
 * Program Description: This class is the implementation of a Graph Object. Which stores the edges between two nodes.
 * @author roykim
 * Date: December 6, 2018
 * Student Number: 250 967 821
 */

// Imports used in the program
import java.util.Iterator;
import java.util.Stack;

public class Graph {

    // Declaration of the Graph Variables
    private int numnodes; // Stores the number of nodes stored in the graph
    private GraphEdge[][] amatrix; // Stores the edges
    private GraphNode[] nodearray; // Stores the nodes

    /**
     * Constructor which initializes the sizes of the graph edges and the graph nodes.
     * @param n the size of the Graph that is being created.
     */
    public Graph(int n){
        this.numnodes = n; // Initializes the number of nodes in the Graph
        amatrix = new GraphEdge[n][n]; // Initializes the adjacency matrix with size n
        nodearray = new GraphNode[n]; // Initializes the array of nodes with size n

        // Stores new GraphNodes in the array up to the specified size of n.
        for (int i=0;i<n;i++){
            GraphNode newNode = new GraphNode(i);
            nodearray[i] = newNode;
        }
    }

    /**
     * Adds an edge which connects two GraphNodes with the specified busline character that corresponds to the edge.
     * @param u The first GraphNode
     * @param v The second GraphNode
     * @param busLine The busline character that corresponds to the edge
     * @throws GraphException
     */
    public void insertEdge(GraphNode u, GraphNode v, char busLine) throws GraphException{

        // Calls helper method to check if the nodes exist in the graph
        if(nodeExists(u, v)){
            int firstnodename = u.getName();
            int secondnodename = v.getName();

            // If they do exist then get the edge between the two nodes.
            GraphEdge edge = amatrix[firstnodename][secondnodename];

            // If there is already an edge stored between the two nodes throw a GraphException.
            if(edge != null){
                throw new GraphException("This edge already exists within the Graph");
            }

            // If there is no edge that exists in between those nodes then insert.
            else{
               amatrix[firstnodename][secondnodename] = new GraphEdge(u,v,busLine);
               amatrix[secondnodename][firstnodename] = new GraphEdge(v,u,busLine);
            }
        }
        // If the nodes dont exist in the graph throw a GraphException.
        else{
            throw new GraphException("The node does not exist in the graph.");
        }
    }

    /**
     * getNode method is used to determine whether the specified node exists.
     * @param name The node that we are searching for
     * @return nodearray[name] The node that we are looking for
     * @throws GraphException
     */
    public GraphNode getNode(int name) throws GraphException{

        // If the bounds for the size of the nodearray are not met then throw a GraphException because that means the
        // node doesnt exist in the graph.
        if(name < 0 || name >= numnodes){
            throw new GraphException("The GraphNode cannot be found");
        }

        // Checks the bounds for if the node is within the size of the nodearray. If it is return the name of the node.
        else{
            return nodearray[name];
        }
    }

    /**
     * Finds all the incident edges of the GraphNode
     * @param u The GraphNode we are finding incident edges for
     * @return An iterator which contains all of the incident edges
     * @throws GraphException
     */
    public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException{
        try {
            // Checks if the name exists in the graph
            getNode(u.getName());

            // Creates a new stack of GraphEdge objects
            Stack<GraphEdge> myStack = new Stack<>();

            int i = 0;

            while(i < numnodes){
                // if there is a incident edge then push it to the stack
                if(amatrix[u.getName()][i] != null){
                    myStack.push(amatrix[u.getName()][i]);
                }

                i++;
            }
            return myStack.iterator();
        }
        // Catches a GraphException
        catch (GraphException e){
            throw new GraphException("The GraphNode cannot be found");
        }
    }

    /**
     * Gets the connecting edge of two nodes
     * @param u The first GraphNode
     * @param v The second GraphNode
     * @return The edge in between the two GraphNodes
     * @throws GraphException
     */
    public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException{

        // Calls helper method to check if the nodes exist in the graph
        if(nodeExists(u,v)){
            int firstnodename = u.getName();
            int secondnodename = v.getName();

            // If there exists and edge between the two GraphNodes return it
            if(amatrix[firstnodename][secondnodename] != null){
                return amatrix[firstnodename][secondnodename];
            }
            // Throw an GraphException if there is no node between the two GraphNodes
            else{
                throw new GraphException("There is no edge that connects GraphNode u and GraphNode v");
            }

        }
        // Throw a GraphException if the nodes dont exist in the Graph
        else{
            throw new GraphException("The GraphNode cannot be found");
        }
    }

    /**
     * Checks if two GraphNodes are adjacent
     * @param u The first GraphNode
     * @param v The second GraphNode
     * @return A boolean, true if they are adjacent otherwise false
     * @throws GraphException
     */
    public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException{

        // Calls helper method to check if the nodes exist in the graph
        if(nodeExists(u,v)){
            int firstnodename = u.getName();
            int secondnodename = v.getName();

            // If there exists and edge between the two GraphNodes return it
            if(amatrix[firstnodename][secondnodename] != null){
                return true;
            }
            // Return false because they are not adjacent GraphNodes.
            else{
                return false;
            }
        }
        // Throw a GraphException if the nodes dont exist in the Graph
        else{
            throw new GraphException("There is no edge that connects GraphNode u and GraphNode v");
        }
    }

    /**
     * This method is used to determine if two GraphNodes exist is in the graph by trying to get the node using the getNode method.
     * If the method throws an exception then we know that the GraphNode does not exist.
     * @param u The first GraphNode that needs to be checked for existence
     * @param v The second GraphNode that needs to be checked for existence
     * @return If the GraphNode exists in the graph then return true otherwise an exception is caught and return false.
     */
    private boolean nodeExists(GraphNode u, GraphNode v){
        try {
            // If both GraphNodes exist in the graph then return true.
            getNode(u.getName());
            getNode(v.getName());
            return true;
        }
        // If one of these GraphNodes don't exist in the graph catch the exception that is thrown by the getNode method and return false.
        catch (GraphException e){
            return false;
        }
    }
}
