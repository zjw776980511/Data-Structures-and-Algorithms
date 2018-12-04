/**
 * Program Description: The node class that stores the record objects and connects these nodes in a binary search tree
 * @author roykim
 * @date Sunday, November 18, 2018
 * Student Number: 250 967 821
 **/
public class Node {

    private Record record;
    private Node leftNode, rightNode, parent;

    /**
     * Constructor for the Node class that creates a new Node object with the specified record, leftNode, rightNode, and parent.
     * @param record The record stored in the node
     * @param leftNode The reference to the left child
     * @param rightNode The reference to the right child
     * @param parent The reference to the parent of this node
     */
    public Node(Record record, Node leftNode, Node rightNode, Node parent){
        this.record = record;
        this.leftNode = null;
        this.rightNode = null;
        this.parent = parent;
    }

    /**
     * Sets a new reference to the left child
     * @param left The new reference to the left child
     */
    public void setLeft(Node left){
        this.leftNode = left;
    }

    /**
     * Sets a new reference to the right child
     * @param right The new reference to the right child
     */
    public void setRight(Node right){
        this.rightNode = right;
    }

    /**
     * Sets a new record for the Node
     * @param rec The new record for the node
     */
    public void setRec(Record rec){
        this.record = rec;
    }

    /**
     * Sets the parent for the Node
     * @param parent The new parent for the Node
     */
    public void setParent(Node parent){
        this.parent = parent;
    }

    /**
     * Gets the parent of the Node
     * @return the reference to the parent Node
     */
    public Node getParent(){
        return this.parent;
    }

    /**
     * Gets the leftChild of the Node
     * @return the reference to the left Node
     */
    public Node getLeft(){
        return this.leftNode;
    }

    /**
     * Gets the rightChild of the Node
     * @return the reference to the right Node
     */
    public Node getRight(){
        return this.rightNode;
    }

    /**
     * Gets the record of the Node
     * @return the record stored in the Node
     */
    public Record getRec(){
        return this.record;
    }

}
