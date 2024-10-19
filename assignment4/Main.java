/* CRITTERS Main.java
 * ECE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student Name>
 * <Student EID>
 * Slip days used: <0>
 * Fall 2024
 */
package assignment4; // cannot be in default package
import java.util.Scanner;
import java.io.*;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;  // scanner connected to keyboard input, or input file
    private static String inputFile;    // input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;      // if test specified, holds all console output
    private static String myPackage;    // package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;        // if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name,
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) {
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */

        while (true) {
            System.out.print("critters> ");
            String input = kb.nextLine().trim();
            String[] tokens = input.split("\\s+");

            if (tokens.length == 0 || tokens[0].equals("")) {
                continue;
            }

            String command = tokens[0].toLowerCase();

            try{
                switch (command) {

                    case "quit":
                        System.out.flush();
                        return;
                    case "show":
                        Critter.displayWorld();
                        break;
                    case "step":
                        handleStep(tokens);
                        break;
                    case "make":
                        handleMake(token);
                        break;
                    case "stats":
                        handleStats(token);
                        break;
                    default:
                        System.out.println("Invalid command: " + command);
                }
            } catch (Exception e) {
                System.out.println("Error processing command: " + input);
            }
        }
    }

    private static void handleStep(String[] tokens) {
        int count = 1;
        if (tokens.length > 1) {
            try{
                count = Integer.parseInt(tokens[1]);
            } 
            catch (NumberFormatException e){
                 System.out.println("Error: Invalid step count");
                return;
            }
        }
        for (int i =0; i < count; i++){
            Critter.worldTimeStep();
        }
    }
    private void handleMake(String[] tokens) {
        if (tokens.length < 2) {
            System.out.println("Error: Missing critter class name");
            return;
        }

        String critterClassName = tokens[1];
        int count = 1;

        if (tokens.length > 2) {
            try {
                count = Integer.parseInt(tokens[2]);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid count");
                return;
            }
        }
        try {
            for (int i = 0; i < count; i++) {
                Critter.makeCritter(critterClassName);
            }
        } catch (InvalidCritterException e) {
            System.out.println("Error: Invalid critter class name: " + critterClassName);
        }

        
    }
    private static void handleStats(String[] tokens) {
        if (tokens.length < 2) {
            System.out.println("Error: Missing critter class name");
            return;
        }
    
        String critterClassName = tokens[1];
    
        try {
            List<Critter> instances = Critter.getInstances(critterClassName);
            Class<?> critterClass = Class.forName(myPackage + "." + critterClassName);
            java.lang.reflect.Method statsMethod = critterClass.getMethod("runStats", List.class);
            statsMethod.invoke(null, instances);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Critter class not found");
        } catch (NoSuchMethodException e) {
            System.out.println("Error: runStats method not found for class: " + critterClassName);
        } catch (Exception e) {
            System.out.println("Error: Failed to invoke runStats method");
        }
    }
}
