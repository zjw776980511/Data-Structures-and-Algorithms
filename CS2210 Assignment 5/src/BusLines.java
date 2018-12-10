/**
 * Program Description: This class is the implementation of the BusLines object which is used to build the map from the inputfile.
 * @author roykim
 * Date: December 6, 2018
 * Student Number: 250 967 821
 */

// Imports used in the program
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

public class BusLines {

    // Declaration of the BusLines variables
    private Graph graph; // The graph
    private int c, width, length, maxbuschanges, size, startp, endp; // Stores information about the map

    public BusLines(String inputFile) throws MapException, GraphException {

        try {

            // Read the input file
            BufferedReader in = new BufferedReader(new FileReader(inputFile));

            // Read the first line and get the specifications of the map
            String firstline[] = in.readLine().split(" ");

            // Set the maps parameters with the specified values from the input file
            c = Integer.parseInt(firstline[0]); // Value not used
            width = Integer.parseInt(firstline[1]); // The width of the graph
            length = Integer.parseInt(firstline[2]); // The length of the graph
            maxbuschanges = Integer.parseInt(firstline[3]); // The max number of bus changes allowed
            size = width * length; // The size of the graph

            graph = new Graph(size); // Sets the size of the graph

            String line1;
            String line2;

            int counter = 0;

            // While the file is not empty read the file while the first line read is not equal to null
            while ((line1 = in.readLine()) != null) {

                // Read the second line of the file
                line2 = in.readLine();

                // For the width of the file check each index
                for (int i = 0; i < width * 2 - 1; i++) {

                    // If the index is even
                    if (i % 2 == 0) {

                        // If we read a starting point
                        if (line1.charAt(i) == 'S') {
                            startp = counter;
                        }

                        // If we read a end point
                        if (line1.charAt(i) == 'D') {
                            endp = counter;
                        }

                        // While line2 is not null we can still read it because of the odd line numbers
                        if (line2 != null) {

                            // Read the symbol at the current index
                            char symbol = line2.charAt(i);

                            // If the symbol is not a house then insert a edge between the two nodes, into the graph with the given symbol
                            if (symbol != ' ') {
                                graph.insertEdge(graph.getNode(counter), graph.getNode(counter + width), symbol);
                            }
                        }
                    }

                    // If the index is odd
                    else {

                        // Read the symbol at the current index
                        char symbol = line1.charAt(i);

                        // If the symbol is not a house then insert a edge between the two nodes, into the graph with the given symbol
                        if (symbol != ' ') {
                            graph.insertEdge(graph.getNode(counter), graph.getNode(counter + 1), symbol);
                        }
                        counter++;
                    }
                }
                counter++;
            }
            // Close the file reader because we are done reading the file
            in.close();

        }
        // If file could not be opened
        catch (IOException e) {
            throw new MapException("The Map could not be created!");
        }
        catch (GraphException e){
            throw new GraphException("The graph could not be created!");
        }
    }

    /**
     * Gets the Graph that was created
     * @return The Graph
     * @throws MapException
     */
    public Graph getGraph() throws MapException {

        // If there is a graph then return it
        if (graph != null) {
            return graph;
        }
        // If there is not a graph then throw a MapException
        else {
            throw new MapException("The graph does not exist!");
        }
    }

    /**
     * Helper method that finds a path from the starting node to the end node.
     * @param stack Holds the path
     * @param start The start
     * @param dest The destination
     * @param buslines The number of current busline changes
     * @param line The current busline character of the edge
     * @return returns true if there is a solution otherwise, returns false.
     * @throws GraphException
     */
    private boolean triphelper(Stack<GraphNode> stack, GraphNode start, GraphNode dest, int buslines, char line) throws GraphException {

        // Push the current node onto the stack
        stack.push(start);

        // If the number of current busline changes exceeds the maximum number of bus line changes then return false
        if (buslines > maxbuschanges) {
            return false;
        }

        // If we have reached the destination and so we have found a path.
        if (start == dest) {
            return true;
        }

        // Set the node to true, meaning that we have visited it
        start.setMark(true);

        // Grab the incident edges around the current node.
        Iterator<GraphEdge> incidentedges = graph.incidentEdges(start);

        // While there are different paths to check run to determine a valid path.
        while (incidentedges.hasNext()) {

            // Get the edge between the two nodes
            GraphEdge edge = incidentedges.next();

            // The second connecting node to the original node and the edge
            GraphNode connectnode = edge.secondEndpoint();

            // The busline character that corresponds to the edge between the first and second node.
            char busline = edge.getBusLine();

            // If the node has not alreaady been visited
            if (!connectnode.getMark()) {

                // If we are at the starting node then set the busline character.
                if (start.getName() == startp) {
                    line = edge.getBusLine();
                }

                // If they are on the same bus line
                if (line == busline) {
                    // Recursively call the helper method to find a path, if a path is found return true
                    if (triphelper(stack, connectnode, dest, buslines, line)) {
                        return true;
                    }
                }

                // If they are not on the same busline
                else {
                    // Check if we can make another busline change
                    if (buslines < maxbuschanges) {
                        // Recursively call the helper method to find a path and increment the number of buschanges made because we made a change. If there is a path then return true.
                        if (triphelper(stack,connectnode, dest, buslines+1, busline)) {
                            return true;
                        }
                    }
                }
            }
        }

        // Backtracking, if there is no current solution for the path then backtrack and look for another way.
        start.setMark(false); // Set the node we were at to false
        stack.pop(); // Remove the path that does not work from the stack.
        return false; // If there is no path then return false
    }

    /**
     * Creates a path from the start to the end point with the max number of bus changes in mind
     * @return A iterator that contains the path the stack took or returns null if no path could be found.
     * @throws GraphException
     */
    public Iterator<GraphNode> trip() throws GraphException {

        // Creates a stack that will store the path.
        Stack<GraphNode> solutionstack = new Stack<>();

        // Calls a helper method and if the method returns true then return the path.
        if (triphelper(solutionstack, graph.getNode(startp), graph.getNode(endp), 0, ' ')) {
            return solutionstack.iterator(); // Return the path
        }

        // If a path could not be found.
        return null;
    }
}