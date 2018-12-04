// Import statements used to take input from the user and read files.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Program Description: This class is used to allow the user to interact with the ordered dictionary using commands
 * @author roykim
 * @date Sunday, November 18, 2018
 * Student Number: 250 967 821
 **/
public class UserInterface {

    // the main method which is executed when the program is run
    public static void main(String[] args) {

        // creates a new ordered dictionary
        OrderedDictionary dict = new OrderedDictionary();
        BufferedReader reader;
        StringReader keyboard = new StringReader();
        String[] tokens;

        // try to open the file other wise catch the IOException
        try {
            // Opens the file
            reader = new BufferedReader(new FileReader(args[0]));
            String line1,line2;
            Pair pair;
            Record record;
            String type;

            // reading the input from the file two lines at a time which contains the word and the data. Then inserts the record into the ordered dictionary.
            while((line1 = reader.readLine()) !=null && (line2 = reader.readLine()) != null){

                // Gets the type from the data string. Takes the last three characters which contain the extensions for the files
                type = line2.substring(line2.length()-3);

                // if the extension is a jpg or gid then the type is an image.
                if(type.equals("jpg") || type.equals("gif")){

                    // Creates a new pair and record containing the word, type, and data
                    pair = new Pair(line1, "image");
                    record = new Record(pair, line2);

                    // Stores the record in the dictionary
                    dict.put(record);
                }

                // if the extension is a wav or mid then the type is an audio.
                else if(type.equals("wav") || type.equals("mid")){

                    // Creates a new pair and record containing the word, type, and data
                    pair = new Pair(line1, "audio");
                    record = new Record(pair, line2);

                    // Stores the record in the dictionary
                    dict.put(record);
                }

                // Otherwise it is just text with no extension
                else{

                    // Creates a new pair and record containing the word, type, and data
                    pair = new Pair(line1,  "text");
                    record = new Record(pair, line2);

                    // Stores the record in the dictionary
                    dict.put(record);
                }
            }

            // Takes the users commands until the quit
            int control = 1;
            while(control == 1){

                // Read the command from the keyboard
                String line = keyboard.read("Enter next command: ");

                // split the command the contents of the command every space and stores them in a string array named tokens
                tokens = line.split("\\s+");

                // switch cases for all the possible commands.
                switch (tokens[0].toLowerCase()) {

                    // if the command is get then this case is run
                    case "get":

                        // creation of a new SoundPlayer and PictureViewer objects. This allows us to display images and play audio.
                        SoundPlayer player = new SoundPlayer();
                        PictureViewer pic = new PictureViewer();

                        // three new pair objects that have the possibility of all three types
                        Pair text = new Pair(tokens[1],"text");
                        Pair audio = new Pair(tokens[1],"audio");
                        Pair img = new Pair(tokens[1],"image");

                        // three new record objects that look for the possible pairs
                        Record textFound = dict.get(text);
                        Record audioFound = dict.get(audio);
                        Record imgFound = dict.get(img);

                        // if the pair is not found in the dictionary then let the user know that the word is not in the dictionary. Then print the preceding and following word.
                        if(textFound == null && audioFound == null && imgFound == null){
                            System.out.println("word is not in the dictionary!");
                            System.out.println("Preceding word: " + dict.predecessor(audio).getKey().getWord());
                            System.out.println("Following word: " + dict.successor(text).getKey().getWord());
                        }

                        // if there is a text file with the given word the print the data of that record
                        if(textFound!=null){
                            System.out.println(textFound.getData());
                        }

                        // if there is a image file with the given word then show the image
                        if(imgFound != null) {
                            pic.show(imgFound.getData());
                        }

                        // if there is a audio file with the given word then play the sound
                        if(audioFound != null){
                            player.play(audioFound.getData());
                        }

                        break;

                    // if the command is delete then this case is run
                    case "delete":

                        // creation of a new pair object that contains the word and the type to remove
                        Pair removalPair = new Pair(tokens[1], tokens[2]);

                        // look for the pair in the dictionary if it does not exist then prompt the user that the pair does not exist in the dictionary
                        if(dict.get(removalPair) == null){
                            System.out.println("No record exists with that key in the dictionary!");
                            break;
                        }

                        // if there pair exists then remove the pair that the user entered
                        dict.remove(removalPair);
                        break;

                    // if the command is put then this case is run
                    case "put":

                        // creation of a new pair object that contains the word and the type to insert
                        Pair insertPair = new Pair(tokens[1],tokens[2]);

                        // if the pair does not already exist in the dictionary then insert the record that contains the pair and the data
                        if(dict.get(insertPair) == null){

                            // create the new record to insert that consists of the pair and the data that corresponds to it
                            Record insertRecord = new Record(insertPair, tokens[3]);

                            // put the record in the dictionary
                            dict.put(insertRecord);
                            break;
                        }

                        // If the pair already exists in the dictionary prompt the user accordingly
                        System.out.println("A record with the given key is already in the dictionary!");
                        break;

                    // if the command is list then this case is run
                    case "list":

                        // take the prefix and store it
                        String prefix = tokens[1];

                        // create a new pair object
                        Pair textpair = new Pair(prefix,"text");

                        // look for the successors until the prefix is not satisfied for the string
                        Record suc = dict.successor(textpair);

                        // this will print all the words that match the prefix provided by the user
                        while( suc !=null&& suc.getKey().getWord().startsWith(prefix)){
                            System.out.println(suc.getKey().getWord());
                            suc = dict.successor(suc.getKey());
                        }
                        break;

                    // if the command is smallest then this case is run
                    case "smallest":

                        // get the smallest record in the dictionary
                        Record smallest = dict.smallest();

                        // print the contents of the smallest node. Print the word, type, and the data.
                        System.out.println(smallest.getKey().getWord() + ", " + smallest.getKey().getType()+ ", " +smallest.getData());
                        break;

                    // if the command is largest then this case is run
                    case "largest":

                        // get the largest record in the dictionary
                        Record largest = dict.largest();

                        // print the contents of the largest node. Print the word, type, and the data.
                        System.out.println(largest.getKey().getWord() + ", " + largest.getKey().getType() + ", " + largest.getData());
                        break;

                    // if the command is finish then this case is run
                    case "finish":

                        // stop asking the user for input, then exit the program
                        control = 0;
                        System.exit(0);

                    // if any other command is input by the user then let the user know that it is an invalid command and then ask them for a different command
                    default:
                        System.out.println("That is an invalid command!");
                }
            }
        }
        // Catches if the file to read the input from cannot be found.
        catch (IOException e) {
            System.out.println("file not found!");
        }
        // If the audio or image file could not be found or opened.
        catch (MultimediaException e) {
            System.out.println("file cannot be found!");
        }
    }
}