/**
 * Program Description: This node class is used to create Node objects that will store the name of the node
 * which is an integer value, and also store whether the node is marked or not.
 * @author roykim
 * Date: December 6, 2018
 * Student Number: 250 967 821
 */
public class GraphNode {

    // Declaration of a nodes attributes.
    private int name;
    private boolean mark;

    /**
     * Constructor for the GraphNode class. Initializes a new node with the name passed as the argument
     * and sets it to unmarked initially.
     * @param name The name that will label the node
     */
    public GraphNode(int name){
        this.name = name;
        mark = false;
    }

    /**
     * Sets the mark of a node
     * @param mark true or false depending on if the node is visited or not
     */
    public void setMark(boolean mark){
        this.mark = mark;
    }

    /**
     * Gets the mark of the node
     * @return the mark of a node
     */
    public Boolean getMark(){
        return this.mark;
    }

    /**
     * Gets the name of the node
     * @return the name of the node
     */
    public int getName(){
        return this.name;
    }
}
