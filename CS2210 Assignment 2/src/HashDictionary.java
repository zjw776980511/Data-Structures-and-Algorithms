/**
 * This is the HashDictionary Class that implements a DictionaryADT. The purpose of this class is to create the dictionary with the specified size to store the values.
 * @author roykim
 * @date October 17, 2018
 * Student Number: 250 967 821
 */

public class HashDictionary implements DictionaryADT {

	/**
	 * These instance variables will be used later in the code to be able to reference certain nodes so I declare them here.
	 * @tableSize This will store the table size.
	 * @nodeArray This will store all the nodes in the array.
	 */
	private int tableSize;
	Node[] nodeArray;

	/**
	 * This constructs a HashDictionary with a specified size.
	 * @param size This will be the size of the HashDictionary.
	 */
	public HashDictionary(int size) {
		
		this.tableSize = size;
		
		// Initializes the table with the specified size.
		nodeArray = new Node[this.tableSize]; 
	}

	/**
	 * This method will insert the data of Configuration type into the dictionary. An exception will be thrown if the given data item is already in the dictionary.
	 * @param data A object of configuration type that contains the string and the score. 
	 * @exception DictionaryException An exception that will be thrown if the given data already exists in the dictionary.
	 * @return 0 If there isn't a collision.
	 * @return 1 If there is a collision.
	 */
	public int put(Configuration data) throws DictionaryException {

		// Determines the index in the nodeArray where the data configuration goes. Sends two arguments, the string configuration of data and the length of the string.
		int index = hashFunction(data.getStringConfiguration(),data.getStringConfiguration().length());

		// Declaring new Nodes named current and newNode to be used later in this method.
		Node current;
		Node newNode;
		
		// If the first element at the index of nodeArray is null then that index is empty and there are no nodes stored there.
		if (nodeArray[index] == null) {
			
			// Will create a new node containing the data passed in the parameter, with a pointer of null because there is no node after.
			newNode = new Node(data, null);
			
			// This will set the newNode to the first element at that index of nodeArray.
			nodeArray[index] = newNode;
			
			// Because there was no collision we will return 0.
			return 0;
		}
		
		// Because the first element at the index of nodeArray is not null then that index contains other Configuration objects. So this else statement will run, this means a collision has occurred.
		else {
			
			// This will make the current node equal to the first element at nodeArray[index].
			current = nodeArray[index];
			
			//If the current nodes string configuration is not equal to the string configuration of data in the parameter then this if statement will run.
			if(!(current.getData().getStringConfiguration().equals(data.getStringConfiguration())) ) {
				
				// While the next node of current is not null then continue to traverse the linked list. To get to the end of the list to insert the parameter data.
				while(current.getNext()!=null) {
					
					// Will set the current to the next node from the current node.
					current = current.getNext();
				}
				
				// Creates a new node containing the data passed in the parameter, with a pointer of null because ther is no node after because it is at the end of the linked list.
				newNode = new Node(data, null);
				
				// Sets the current nodes next node to the new node.
				current.setNext(newNode);
				
				// Returns 1 because there was a collision that occurred.
				return 1;
			}
			
			// This will throw an exception if the current Configuration object's string exists in the dictionary already and will send the Configuration data as an argument.
			else {
				throw new DictionaryException(data);
			}
		}

	}

	/** 
	 * Removes the entry with configuration string config from the dictionary.
	 * @param config The configuration string that needs to be removed from the dictionary.
	 * @exception DictionaryException Throws a Dictionary Exception if the configuration string cannot be found in the dictionary.
	 */
	public void remove(String config) throws DictionaryException {
		
		// Determines the index in the nodeArray where the String configuration is. Sends two arguments, the string configuration and the length of the string.
		int index = hashFunction(config,config.length());
		
		// Declaration and initialization of two node variables. The head node as the first element of nodeArray[index], and the prev node as null for if the node to be removed is at the head itself.
		Node head = nodeArray[index];
		Node prev = null;
		
		// If the first element of nodeArray[index] is null then it means the position is empty and therefore implying that the configuration string is not in the dictionary. Will throw a DictionaryException 
		// if the configuration string doesn't exist.
		if (nodeArray[index] == null) {
			throw new DictionaryException("This config was not in the dictionary.");
		}
		
		// While the head is not null this will loop to traverse the linked list at nodeArray[index]. 
		while (head != null) {
			
			// If the configuration string is found at the head of the linked list.
			if (head.getData().getStringConfiguration().equals(config)) {
				
				// Will set the new head to the head after the current one. This will remove the link to the current head.
				head.setNext(head.getNext());
				
				// Then the while loop will break because the object has been removed.
				break;
			} 
			
			// This will remove the link to the element in the linked list and it will re-link accordingly.
			else {
				prev = head;
				head = head.getNext();
			}
		}

	}

	/**
	 * Gets the score of the configuration string passed in the parameter.
	 * @param config The configuration string we need to retrieve the corresponding score for.
	 * @return score Will return the score of the configuration string if is found in the dictionary.
	 * @return -1 If the configuration string is not found in the dictionary then -1 is returned.
	 */
	public int getScore(String config) {
		
		// Determines the index in the nodeArray where the String configuration is. Sends two arguments, the string configuration and the length of the string.
		int index = hashFunction(config,config.length());
		
		// Declaration and initialization of the node head. The head node as the first element of nodeArray[index].
		Node head = nodeArray[index];
		
		// While the head is not null this will loop to traverse the linked list at nodeArray[index]. 
		while (head != null) {
			
			// If the configuration string is found at the head of the linked list.
			if (head.getData().getStringConfiguration().equals(config)) {
				
				// Returns the score of the string.
				return head.getData().getScore();
			} 
			
			// Otherwise it will continue to traverse the linked list until the configuration string is found or until the end of the linked list has been reached.
			else {
				head = head.getNext();
			}
		}
		
		// Returns -1 if the string could not be found in the dictionary.
		return -1;
	}

	/**
	 * Calculates the hash code(the index) where the configuration string should be with the least number of collisions and as evenly spread out as possible.
	 * @param config The configuration string we need to calculate the hash code for.
	 * @param stringLength The length of the configuration string.
	 * @return val The hash code(the index) where the string should be in nodeArray.
	 */
	private int hashFunction(String config,int stringLength) {
		
		int constant = 10;
		
		// Value of the first character of the string, casted as an integer. Stored in a variable named val.
		int val= (int) config.charAt(0);

		// This for loop is used to go through the string and then will calculate the hash code which is the index where the node needs to be placed in nodeArray.
		for (int i = 1; i < stringLength - 2; i++) {
			val = (val * constant + (int) config.charAt(i)) % this.tableSize;
		}

		// Returns the hash code to determine where in the nodeArray the node needs to be placed.
		return val;
	}
}
