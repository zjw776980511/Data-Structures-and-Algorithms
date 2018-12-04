/**
 * Program Description: This is the dictionary exception class which catch's when the key exists or doesn't exist in the dictionary
 * @author roykim
 * @date Sunday, November 18, 2018
 * Student Number: 250 967 821
 **/
public class DictionaryException extends RuntimeException{

    /**
     * If the key already exists in the dictionary
     * @param r The record that already exists in the dictionary
     */
    public DictionaryException(Record r){
        super("The key already exists in the dictionary!");
    }

    /**
     * If the key doesn't exist in the dictionary
     * @param k The key that doesn't exist in the dictionary
     */
    public DictionaryException(Pair k){
        super("That record is not in the dictionary!");
    }
}
