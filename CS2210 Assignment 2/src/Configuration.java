/**
 * This is the Configuration class which will store the data that each entry of HashDictionary will contain. 
 * It will store two things the board configuration as a string and the score corresponding to the board as an integer.  
 * @author roykim
 * @date October 17, 2018
 * Student Number: 250 967 821
 */
public class Configuration {

	// Declaration of the integer score and the String config. The score variable will store the score of the board and the config variable will store the string configuration of the board.
    int score;
    String config;

    /**
     * The constructor of the Configuration class which will return a new Configuration object with the specified string of the board and the score corresponding to it.
     * @param config The string representation of the board.
     * @param score The score of the board.
     */
    public Configuration(String config, int score){
        this.config = config;
        this.score = score;
    }

    /**
     * Returns the string of the Configuration object.
     * @return this.config the string of the Configuration object.
     */
    public String getStringConfiguration(){
        return this.config;
    }

    /**
     * Returns the score of the Configuration object.
     * @return this.score the score of the Configuration object.
     */
    public int getScore(){
        return this.score;
    }

}
