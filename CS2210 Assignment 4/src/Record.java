/**
 * Program Description: This class is used to construct a Record object with stores the key and its corresponding data.
 * @author roykim
 * @date Sunday, November 18, 2018
 * Student Number: 250 967 821
 **/
public class Record {

    private Pair key;
    private String data;

    /**
     * This is the constructor which creates the Record object.
     * @param key The key for the record object
     * @param data The data that corresponds to the key in the record object
     */
    public Record(Pair key, String data){
        this.key = key;
        this.data = data;
    }

    /**
     * Getter method to return the key stored in the record object
     * @return The key stored in the record object
     */
    public Pair getKey(){
        return this.key;
    }

    /**
     * Getter method to return the data stored in the record object
     * @return The data stored in the record object
     */
    public String getData(){
        return this.data;
    }
}