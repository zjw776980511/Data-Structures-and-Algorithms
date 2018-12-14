/**
 * This class implements the DictionaryException thrown by the HashDictionary class.
 * @author roykim
 * @date October 17, 2018
 * Student Number: 250 967 821
 */
public class DictionaryException extends RuntimeException {
	
	/**
	 * A constructor for DictionaryException class when Configuration object is passed.
	 * @param data The configuration data.
	 */
	public DictionaryException(Configuration data) {
		super("That configuration object is already in the dictionary.");
	}
	
	/**
	 * A constructor for DictionaryException class when a String is passed.
	 * @param errorMessage A string with an error message.
	 */
    public DictionaryException (String errorMessage){
        super (errorMessage);
    }
}
