/**
 * Program Description: This class is used to construct a pair object which will act as a Key in out ordered dictionary.
 * @author roykim
 * @date Sunday, November 18, 2018
 * Student Number: 250 967 821
 **/
public class Pair {

    private String word;
    private String type;

    /**
     * Constructor which creates the Pair object
     * @param word The word contained in the pair
     * @param type The type contained in the pair, can be text, audio, image
     */
    public Pair(String word, String type) {
        // Changes all words to lower case
        word = word.toLowerCase();
        this.word = word;
        this.type = type;
    }

    /**
     * Getter method to return the word stored in the pair object
     * @return The word stored in the pair
     */
    public String getWord() {
        return this.word;
    }

    /**
     * Getter method to return the type stored in the pair object
     * @return The type stored in the pair
     */
    public String getType() {
        return this.type;
    }

    /**
     * Compares the pair object. First compares the words then if those are the same then compares the type.
     * @param k The Pair to compare with
     * @return A value of less than 0, equal to 0, or greater than 0 depending on the comparison.
     */
    public int compareTo(Pair k) {

        int compareword = this.word.compareTo(k.getWord());

        // If the words are the same then compare the types.
        if(compareword == 0) {
            int comparetype = this.type.compareTo(k.getType());

            if (comparetype == 0) {
                return 0;
            }
            return comparetype;
        }
        return compareword;
    }
}