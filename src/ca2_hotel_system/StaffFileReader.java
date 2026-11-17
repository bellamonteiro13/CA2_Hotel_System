package ca2_hotel_system;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//file - represents the file on the computer
//FileNotFoundException - the error java throws if the file doesnt exist!
//it is also a form of validation
//ArrayList - the array growing list
//Scanner - for reading

// here it is my class StaffFileReader, it works like the person who OPENS the delivery
// and reads the delivery note line by line
// its purpose: open the Applicants_Form.txt file and give back ALL its lines in a list
// why do i need it? because my hotel staff starts in a file, not typed by hand (UC1, FR7)
// it ONLY reads, it doesnt judge the names: checking them is the job of my InputValidator (the bouncer)
// each class has ONE job, so if one day the file changes, i only change this class
public class StaffFileReader {

    //public static ArrayList<String> readLines(String fileName)
    //is a method that receives a file name and gives back a list of texts
    // throws = in case the file doesnt exist, it shouts the problems
    //without breaking the code
    public static ArrayList<String> readLines(String fileName) throws FileNotFoundException {

        //ArrayList<String> lines = new ArrayList<>();
        //it is an empty list ready to be filled with names
        ArrayList<String> lines = new ArrayList<>();

        // new Scanner(new File(fileName)) = point the scanner to the file
        Scanner fileScanner = new Scanner(new File(fileName));

        // while (fileScanner.hasNextLine()) = keep reading while there is still another line
        // lines.add(fileScanner.nextLine()) = read it and also add to the list
        while (fileScanner.hasNextLine()) {
            lines.add(fileScanner.nextLine());
        }

        // fileScanner.close() = close the file; avoid errors
        fileScanner.close();

        // return lines = give back the full list
        return lines;
    }

}
