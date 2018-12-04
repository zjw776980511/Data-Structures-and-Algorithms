/**
 * Program Description: This class is used to construct a OrderedDictionary object. This OrderedDictionary will store all the nodes in the binary search tree.
 * Each node will store a Record object and the leaves will store null Record objects.
 * @author roykim
 * @date Sunday, November 18, 2018
 * Student Number: 250 967 821
 **/
public class OrderedDictionary implements OrderedDictionaryADT {

    private Node node;

    /**
     * This is the constructor which creates a new OrderedDictionary object which is an empty binary search tree.
     */
    public OrderedDictionary() {
        node = new Node(null, null, null, null);
    }

    /**
     * Returns the Record object with the corresponding key k, otherwise the Pair k is not in the dictionary and returns null
     * @param k The key stored in a Record Object in the dictionary
     * @return Record object if the key exists in the dictionary otherwise, returns null
     */
    public Record get(Pair k) {

        // The node used to traverse the tree.
        Node travelNode = node;

        // if the tree is not empty.
        if(travelNode.getRec() != null){
            // traverses the binary search tree, will run until a leaf has been reached.
            while (travelNode.getRec() != null) {
                // if the key was found in the tree return the record that stores it.
                if (travelNode.getRec().getKey().compareTo(k) == 0) {
                    return travelNode.getRec();
                }

                else if (travelNode.getRec().getKey().compareTo(k) < 0) {
                    travelNode = travelNode.getRight();
                }

                else{
                    travelNode = travelNode.getLeft();
                }
            }
        }
        // returns null if the record is not in the tree.
        return null;
    }

    /**
     * Inserts a record into the dictionary. If a record with the same key already exists then throw an exception
     * @param r The new record to be stored into the binary search tree
     * @throws DictionaryException if a record with the same key already exists in the dictionary
     */
    public void put(Record r) throws DictionaryException {
        Node travelNode = node;
        Node left;
        Node right;

        // if the binary search tree is empty insert the record as the root node, and set both children to null records.
        if(travelNode.getRec() == null){

            travelNode.setRec(r);
            travelNode.setParent(null);

            left = new Node(null,null,null, travelNode);
            right = new Node(null,null,null, travelNode);

            travelNode.setLeft(left);
            travelNode.setRight(right);

        }

        else{
            // while the tree is not empty traverse it
            while (travelNode.getRec() != null) {

                // if a node with the same key is found then throw a dictionary exception
                if (travelNode.getRec().getKey().compareTo(r.getKey()) == 0) {
                    throw new DictionaryException(r);
                }

                else if (travelNode.getRec().getKey().compareTo(r.getKey()) < 0) {
                    travelNode = travelNode.getRight();
                }

                else {
                    travelNode = travelNode.getLeft();
                }
            }

            // put the record into the tree and set two null record children
            travelNode.setRec(r);
            travelNode.setParent(travelNode.getParent());

            left = new Node(null, null, null, travelNode);
            right = new Node(null, null, null, travelNode);
            travelNode.setLeft(left);
            travelNode.setRight(right);
        }
    }

    /**
     * Removes the node that contains the k from the dictionary. Throws an exception if it doesn't exist in the dictionary.
     * @param k The key that corresponds to the record we are trying to remove from the dictionary.
     * @throws DictionaryException If the key doesn't exist in the dictionary then throw a dictionary exception
     */
    public void remove(Pair k) throws DictionaryException {

        // calls a helper method to find the node
        Node travelNode = getNode(k);

        // if the node cannot be found in the dictionary throw and exception
        if(travelNode.getRec() == null){
            throw new DictionaryException(k);
        }

        else{
            // if either child of the node is a leaf
            if(travelNode.getLeft().getRec() == null || travelNode.getRight().getRec() == null){

                // removing the root node from the tree
                if(node.getRec() !=null && (node.getRec().getKey().compareTo(travelNode.getRec().getKey()) == 0)){
                    // if the left child is not null
                    if(travelNode.getLeft() != null){
                        node.setRec(travelNode.getLeft().getRec());
                        node.setLeft(travelNode.getLeft());
                    }

                    // if the right child is not null
                    else{
                        node.setRec(travelNode.getRight().getRec());
                        node.setLeft(travelNode.getRight());
                    }
                }

                else{
                    // get the parent of the node
                    Node parent = travelNode.getParent();

                    if(travelNode.getRec() == parent.getLeft().getRec()){
                        parent.setLeft(travelNode.getRight());
                    }
                    else{
                        parent.setRight(travelNode.getLeft());
                    }
                }
            }

            else{
                Node smallest = travelNode.getRight();

                // gets the smallest of the right node
                while(smallest.getLeft().getRec() != null){
                    smallest = smallest.getLeft();
                }

                // finds the smallest node in the subtree
                smallest = smallest.getParent();
                Node parentSmallest = smallest.getParent();

                // sets the record to the smallest to replace the one that needs to be removed
                travelNode.setRec(smallest.getRec());

                // new null nodes to set as children
                Node left = new Node(null,null,null, parentSmallest);
                Node right = new Node(null,null,null, parentSmallest);

                // sets the null nodes as children
                parentSmallest.setLeft(left);
                parentSmallest.setRight(right);
            }
        }
    }

    /**
     * The successor method which finds the record with the smallest key larger than k
     * @param k the successor we are trying to find for this key
     * @return the successor of the key k or null if the provided key is the largest in the tree or the tree is empty
     */
    public Record successor(Pair k) {

        // find the node that has the same key as k
        Node travelNode = getNode(k);
        Record suc =null;

        // if the node contains the largest key then return null or the tree is empty
        if(travelNode.getRec() != null&&largest().getKey().compareTo(travelNode.getRec().getKey()) == 0 || (node.getRec() == null)){
            return null;
        }

        // when the key is not in the dictionary but still need to find the successor of it
        else if (travelNode.getRec() == null) {

            // runs while there exists a parent
            while(travelNode.getParent().getRec() !=null){

                // move up the tree when possible to find the successor
                if (travelNode.getParent().getRec().getKey().compareTo(k) > 0) {

                    if(travelNode.getParent().getRec().getKey().compareTo(k) ==  0){
                        suc = travelNode.getParent().getParent().getRec();
                        break;
                    }

                    // set the successor
                    suc = travelNode.getParent().getRec();
                    break;
                }
                travelNode = travelNode.getParent();
            }
        }

        else{

            // When the node is not null and the right child of it is not null
            if(travelNode.getRec() != null && travelNode.getRight().getRec() != null){

                // traverse one to the right then the smallest of that subtree (i.e. down the left subtree)
                Node largest = travelNode.getRight();

                // going down the tree to find the successor
                while(largest.getRight().getRec() != null){
                    largest = largest.getRight();
                }
                // sets the successor
                suc = largest.getRec();
            }

            else{

                Node parent = travelNode.getParent();

                // climb up the tree while the node is not the parent and the node is the right child of its parent
                while(travelNode.getRec().getKey().compareTo(node.getRec().getKey()) != 0 && parent.getRight().getRec() !=null&&
                        parent.getRight().getRec().getKey().compareTo(travelNode.getRec().getKey()) ==0){

                    // move up the tree by shifting one up each time
                    travelNode = parent;
                    parent = travelNode.getParent();
                }

                // if the node has reached the root then return null
                if(travelNode.getRec().getKey().compareTo(node.getRec().getKey())==0){
                    return null;
                }

                else{
                    suc = parent.getRec();
                }
            }
        }

        // return the successor of the Pair k
        return suc;
    }

    /**
     * The predecessor method which finds the record with the largest key smallest than k
     * @param k the predecessor we are trying to find for this key
     * @return the predecessor of the key k or null if the provided key is the smallest in the tree or the tree is empty
     */
    public Record predecessor(Pair k) {
        // find the node that has the same key as k
        Node travelNode = getNode(k);
        Record pre =null;

        // if the node contains the smallest key then return null or the tree is empty
        if(travelNode.getRec() != null&&smallest().getKey().compareTo(travelNode.getRec().getKey()) == 0 || (node.getRec() == null)){
            return null;
        }

        // when the key is not in the dictionary but still need to find the successor of it
        else if (travelNode.getRec() == null) {

            // runs while there exists a parent, continues to loop until a predecessor is found
            while(travelNode.getParent().getRec() !=null){

                if (travelNode.getParent().getRec().getKey().compareTo(k) < 0) {

                    if(travelNode.getParent().getRec().getKey().compareTo(k) ==  0){
                        pre = travelNode.getParent().getParent().getRec();
                        break;
                    }

                    pre = travelNode.getParent().getRec();
                    break;
                }
                travelNode = travelNode.getParent();
            }
        }

        else{

            // when the node is not null and the left child is not null
            if(travelNode.getRec() != null && travelNode.getLeft().getRec() != null){

                // traverse one to the left then the smallest of that subtree (i.e. down the left subtree)
                Node smallest = travelNode.getLeft();

                // traversing will the predecessor is found
                while(smallest.getLeft().getRec() != null){
                    smallest = smallest.getLeft();
                }
                // sets the predecessor
                pre = smallest.getRec();
            }

            else{

                Node parent = travelNode.getParent();

                // climb up the tree while the node is not the parent and the node is the left child of its parent
                while(travelNode.getRec().getKey().compareTo(node.getRec().getKey()) != 0 && parent.getLeft().getRec() !=null &&
                        parent.getLeft().getRec().getKey().compareTo(travelNode.getRec().getKey()) ==0){

                    // move up the tree by shifting one up each time
                    travelNode = parent;
                    parent = travelNode.getParent();
                }

                // if the node has reached the root then return null
                if(travelNode.getRec().getKey().compareTo(node.getRec().getKey())==0){
                    return null;
                }

                else{
                    pre = parent.getRec();
                }
            }
        }
        // return the predecessor of the Pair k
        return pre;
    }

    /**
     * Finds the record with the smallest key in the dictionary
     * @return the record with the smallest key in the dictionary returns null if the dictionary is empty
     */
    public Record smallest() {
        Node travelNode = node;
        Node parent = travelNode;

        // traverses the left side of the tree because its a binary search tree.
        while (travelNode.getLeft() != null) {
            parent = travelNode;
            travelNode = travelNode.getLeft();
        }

        return parent.getRec();
    }

    /**
     * Find the record with the largest key in the dictionary
     * @return the record that contains the largest key in the ordered dictionary. Returns null if the dictionary is empty
     */
    public Record largest() {
        Node travelNode = node;
        Node parent = travelNode;

        // traverses the right side of the binary search tree
        while (travelNode.getRight() != null) {
            parent = travelNode;
            travelNode = travelNode.getRight();
        }

        return parent.getRec();
    }

    /**
     * Helper method to return the Node that contains the key (k) in the binary search tree
     * @param k The to find in the binary search tree
     * @return The node that matches the k or null if it could not be found.
     */
    private Node getNode(Pair k){
        Node travelNode = node;

        // if the tree is not empty
        if(travelNode != null){
            while (travelNode.getRec() != null) {

                // the node has been found so return the node
                if (travelNode.getRec().getKey().compareTo(k) == 0) {
                    return travelNode;
                }

                else if (travelNode.getRec().getKey().compareTo(k) < 0) {
                    travelNode = travelNode.getRight();
                }

                else{
                    travelNode = travelNode.getLeft();
                }
            }
        }

        // returns the found node or null if not found
        return travelNode;

    }
}
