/**
 * A Node class that stores Configuration objects and links these nodes in a linked list.
 * @author roykim
 * @date October 17, 2018
 * Student Number: 250 967 821
 */
public class Node {

	/**
	 * Instance Variables declared for use later in the code.
	 * @data The Configuration objects data.
	 * @nextNode The reference to the next node in the linked list
	 */
    private Configuration data;
    private Node nextNode;

    /**
     * Constructor for the Node class that creates a new Node object with the specified Configuration data and reference to the next node.
     * @param data The Configuration data stored in the node.
     * @param nextNode The reference to the next node in the linked list.
     */
    public Node(Configuration data, Node nextNode){
        this.data = data;
        this.nextNode = nextNode;
    }

    /**
     * Returns the Configurations data in the Node
     * @return data Data of the configuration object.
     */
    public Configuration getData(){
        return data;
    }
    
    /**
     * Gets the reference to the next node.
     * @return nextNode the reference to the next node.
     */
    public Node getNext(){
        return nextNode;
    }

    /**
     * Sets new Configuration data for a Node.
     * @param newData The new data that needs to be set for a node.
     */
    public void setData(Configuration newData){
        data = newData;
    }

    /**
     * Sets the next node.
     * @param newNode New node to be linked to.
     */
    public void setNext(Node newNode){
        nextNode = newNode;
    }


}
