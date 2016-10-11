import java.io.File;
import java.io.IOException;
import java.util.Scanner;


/**
 * This program will take an input txt file and print and save to output.txt the permutations of anagrams contained in the input txt.
 * to use the program simply direct the program to the correct filepath containing the txt or if the file is contained in the same directory simply type the "filename.txt"
 * This class is the driver program responsible for verifying the txt file and creating the anagram object
 *
 * The data structures used in this program include: Arrays
 */


public class anagram {


    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        boolean fileExists = false;
        System.out.println("Please enter the directory for the text file to be tested.");

        //Begin main loop
        while (!fileExists){
            String inputString = input.next();

            //loops until the file has been validated, changing boolean value for fileExists to true
            while (!fileExists){

                //throwing string into file to test for existence and correct file extension
                File inputFile = new File(inputString);

                if (!inputFile.exists() || inputString.trim().equals("//")){
                    System.out.println("Incorrect directory, file not found!");
                    System.out.println("Please enter the file path for the text file to be tested.");
                    break;
                }

                //checking the file extension for "txt" with the isExtension method
                if (inputFile.exists() && !isExtension(inputString, "txt")){
                    System.out.println("Not a valid file format, please select a file with the extension txt");
                    break;
                }

                //checking the file extension for "txt" with the isExtension method
                if (inputFile.exists() && isExtension(inputString, "txt")) {
                    fileExists = true;
                    System.out.println("the file has been validated");
                    //call method for dealing with txt file here
                    //only creates the object anagramObj and passes a file when file has been validated


                    try {
                        anagramObj t = new anagramObj(inputFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }



            }

        }


    }

    /*
        This is a method to determine if a user supplied string contains the given file extension type.
        Precondition:   When called this method is provided three strings, one for the

        Post condition:  A boolean is returned which states if the given file path contains the correct extension.
     */
    public static boolean isExtension(String usrInput, String fileExtString){
        String tempStringExtension =  usrInput.substring(usrInput.lastIndexOf('.')+1,(usrInput.lastIndexOf('.')+4));
        if (tempStringExtension.equalsIgnoreCase(fileExtString)) return true;
        else return false;
    }


}
