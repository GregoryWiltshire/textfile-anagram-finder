import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *This class contains the methods to sort and print the anagrams from the input txt, the anagrams are printed and saved to a output.txt file
 */
public class anagramObj {
    public anagramObj(File txt) throws IOException {

        Scanner textFile = null;
        try {
            textFile = new Scanner(txt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //tests for an empty file, if empty will exit program
        if (!textFile.hasNext()) {
            System.out.println("the input file is empty");
            System.exit(0);
        }

        //if the txt file has input then the loop is executed
        if (textFile.hasNext()) {

            String[] originalWords = new String[50];
            String[] signature = new String[50];
            int count = 0;


            //still goes out of bounds when runs out of words from text file, also need to strip words of capital letters and punctuation
            //need to write a method to iterate through string and strip out punctuation
            while (count < 50 && textFile.hasNext()) {

                //takes in the next word from the txt file
                String word = textFile.next();
                //

                //Ignores words longer than 15 characters
                //if the word is 15 or less characters it is placed into the array at slot count and count is incremented
                if (word.length() <= 15) {

                    //stores the original word with punctuation
                    originalWords[count] = word;

                    //removes the words punctuation for signature processing
                    word = removePunctuation(word);
                    signature[count] = word;

                    count++;

                    //once the arrays have been filled test to see if the input txt has next input
                    //and if so prints message that there are too many words in the file and exits
                    if (count == 49 && textFile.hasNext()) {
                        System.out.println("There are more than 50 words in the input file!");
                        System.exit(0);
                    }

                }

            }


            System.out.println("Words stored in arrays");

            //sorts the two arrays in alphabetical order
            //Arrays.sort(originalWords);
            // Arrays.sort(signature);
            //method alphabetizes the signature array strings
            signature = alphabetizedChars(signature, count);
           // System.out.println(signature.toString());

            signature = alphabetizedChars(signature, count);
            try {
                findAnagrams(originalWords, signature  , count);
            } catch (IOException e) {
                e.printStackTrace();
            }


            System.exit(0);
        }


    }



    //this method will strip the input word of any punctuation and convert to lowercase
    public String removePunctuation(String inputString) {


        String modifiedString = inputString.replaceAll("\\p{Punct}+", "").toLowerCase();
        return modifiedString;
    }

    //method to alphabetize a string by character
    public String[] alphabetizedChars(String[] words, int count) {

        String[] sorted = new String[50];
        for (int i = 0; i < count; i++) {

            String word = words[i];

            char[] chArray = word.toCharArray();
            Arrays.sort(chArray);
            word = new String(chArray);
            sorted[i] = word;


        }


        //String modifiedString = inputString.replaceAll("\\p{Punct}+", "").toLowerCase();
        return sorted;
    }


    //This method takes in two parallel arrays and iterates through the signatures array search for dupes from count up
    //all dupes will then be printed as "x" is an anagram of "y"
    public void findAnagrams(String[] originalWords, String[] signatures, int count) throws IOException {
        File file = new File("output.txt");
        FileWriter fileWriter = new FileWriter(file);



        for (int i = 0; i < count; i++) {

            //sets the string pattern for comparison to other signatures
            String pattern = signatures[i];

            //must satisfy different indexPointer && .equals the signature to print the anagram

            //loop to iterate the array and compare the pattern to the array values
            for (int j = 0; j < count; j++) {
                //System.out.println("loopfor");
                //System.out.println(signatures[j]);

                String temp = signatures[j];

                if (pattern.equals(temp)  && !(j == i) ) {
                    //Print the indices from original words array
                   System.out.println(originalWords[j] + " is an anagram of " + originalWords[i]);
                    fileWriter.write(originalWords[j] + " is an anagram of " + originalWords[i]+"\n");
                }
            }



        }


        fileWriter.flush();
        fileWriter.close();
    }



}