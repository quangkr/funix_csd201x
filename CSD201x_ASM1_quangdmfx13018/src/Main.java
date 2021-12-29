import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static File inFile = new File("input.txt");
    private static File outFile1 = new File("output1.txt");
    private static File outFile2 = new File("output2.txt");
    private static File outFile3 = new File("output3.txt");
    private static File outFile4 = new File("output4.txt");
    private static File outFile5 = new File("output5.txt");
    private static int inputLength = 0;
    private static double[] theInputs = new double[0];
    private static SimpleSort theSorter;

    public static void main(String args[]) {
        // Welcome message
        printMessage(60, "Welcome to Simple Sort program!!!");

        int choice;
        String promptMsg = "\n  Your selection (1 -> 9, 0 to print help menu, -1 to exit): ";
        String invalidChoiceMsg = "Invalid choice. Try again.";

        // print the menu the first time program run
        printMenu();

        double searchValue;
        boolean willContinue = true;
        while (willContinue) {
            // prompt user for functionality selection
            choice = getInputInt(promptMsg, invalidChoiceMsg);
            System.out.println();

            switch (choice) {
            case 1: {
                inputLength = getInputInt("How many numbers you want to enter?");
                theInputs = getInputArrayDouble(inputLength);
                Stream<Double> stream = Arrays.stream(theInputs).boxed();
                List<String> strings = stream.map(String::valueOf).collect(Collectors.toList());
                strings.add(0, String.valueOf(inputLength));

                writeListToFile(inFile, strings);
            }
                break;

            case 2: {
                readFileToArray(inFile);
                theSorter = new SimpleSort(theInputs);
                theSorter.display();
            }
                break;

            case 3:
            case 4:
            case 5:
                try {
                    doSort(choice - 2);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

                break;

            case 6: {
                try {
                    searchValue = getInputDouble("Which value do you want to search for?");
                    List<Integer> results = theSorter.linearSearchGreater(searchValue);
                    List<String> strings = new ArrayList<>(1);
                    if (results.size() > 0) {
                        strings = results.stream().map(Object::toString).collect(Collectors.toList());
                    } else {
                        strings.add("There's no element matched the criteria.");
                    }

                    strings.stream().map(s -> s + " ").forEach(System.out::print);
                    System.out.println();

                    writeListToFile(outFile4, strings);
                } catch (NullPointerException e) {
                    System.out.println("There was an error searching for the element.");
                    System.out.println("Please try entering new data or reading data from disk.");
                }
            }
                break;

            case 7: {
                try {
                    searchValue = getInputDouble("Which value do you want to search for?");
                    int result = theSorter.binarySearch(searchValue, true);
                    List<String> strings = new ArrayList<>(1);
                    if (result != -1) {
                        strings.add(String.valueOf(result));
                    } else {
                        strings.add("There's no element matched the criteria.");
                    }

                    System.out.print(strings.get(0));
                    System.out.println();

                    writeListToFile(outFile5, strings);
                } catch (NullPointerException e) {
                    System.out.println("There was an error searching for the element.");
                    System.out.println("Please try entering new data or reading data from disk.");
                }
            }
                break;
                
            case 8: { 
                    File comparisonInputFile;
                    String[] messages = new String[]{
                            "10K entries, unsorted",
                            "10K entries, ascesdingly sorted",
                            "10K entries, desendingly sorted",
                            "50K entries, unsorted",
                            "50K entries, ascesdingly sorted",
                            "50K entries, desendingly sorted",
                            "100K entries, unsorted",
                            "100K entries, ascesdingly sorted",
                            "100K entries, desendingly sorted",
                            "Generated entries"
                    };

                    System.out.println("Which input file should be used for comparison?");
                    for (int i=0; i<messages.length; i++) {
                        System.out.format("    %d. %s\n", i+1, messages[i]);
                    }
                    int comparisonChoice = getInputInt("Your choice?");
                    
                    System.out.format("\nDoing comparison for %s\n", messages[comparisonChoice-1]);
                    switch (comparisonChoice) {
                    case 1:
                        comparisonInputFile = new File("input_10k_random.txt");
                        break;
                    case 2:
                        comparisonInputFile = new File("input_10k_sorted.txt");
                        break;
                    case 3:
                        comparisonInputFile = new File("input_10k_reverse.txt");
                        break;
                    case 4:
                        comparisonInputFile = new File("input_50k_random.txt");
                        break;
                    case 5:
                        comparisonInputFile = new File("input_50k_sorted.txt");
                        break;
                    case 6:
                        comparisonInputFile = new File("input_50k_reverse.txt");
                        break;
                    case 7:
                        comparisonInputFile = new File("input_100k_random.txt");
                        break;
                    case 8:
                        comparisonInputFile = new File("input_100k_sorted.txt");
                        break;
                    case 9:
                        comparisonInputFile = new File("input_100k_reverse.txt");
                        break;
                    case 10:
                        comparisonInputFile = new File("generatedRandomNumbers.txt");
                        break;
                    default:
                        comparisonInputFile = inFile;
                        break;
                    }
                    doSortComparison(comparisonInputFile);
                }
                break;
                
            case 9: {
                int numOfValues = getInputInt("How many values do you want to generate?");
                double maxValue = getInputDouble("Maximum generated value?");
                List<String> tmp = new ArrayList<>(numOfValues);
                
                tmp.add(String.valueOf(numOfValues));
                for (int i=0; i<numOfValues; i++) {
                    tmp.add(String.valueOf(Math.random()*maxValue));
                }
                
                writeListToFile(new File("generatedRandomNumbers.txt"), tmp);
            }
                break;

            case 0:
                printMenu();
                break;
            case -1:
                printMessage(60, "See you later!!!");
                willContinue = false;
                break;
            default:
                System.out.println(invalidChoiceMsg);
                break;
            }
        }

        sc.close();
    }

    /**
     * Print a message enclosed in ASCII box
     * 
     * @param length
     * @param message
     */
    private static void printMessage(int length, String message) {
        length = Math.max(message.length() + 4, length);
        int pad = length - (message.length() + 2);

        for (int i = 0; i < length; i++)
            System.out.print("=");
        System.out.print("\n|");
        for (int i = 0; i < pad / 2; i++)
            System.out.print(" ");
        System.out.print(message);
        for (int i = 0; i < pad / 2; i++)
            System.out.print(" ");
        // extra space in case padding was odd
        if (pad % 2 == 1)
            System.out.print(" ");
        System.out.print("|\n");
        for (int i = 0; i < length; i++)
            System.out.print("=");
    }

    /**
     * Print the help menu
     */
    private static void printMenu() {
        System.out.println("\n Choose your option:");
        System.out.println("  1. Enter new data");
        System.out.println("  2. Read and display data from file");
        System.out.println("  3. Bubble sort");
        System.out.println("  4. Selection sort");
        System.out.println("  5. Insertion sort");
        System.out.println("  6. Search for numbers greater than ... (Linear seearch)");
        System.out.println("  7. Search for exact number (Binary search)");
        System.out.println("  8. Compare sort types");
        System.out.println("  9. Generate random numbers");
    }
    
    /**
     * Perform all 3 types of sort on the given data
     * then print the runtime of each sort
     * 
     * @param inputFile The file to read data from
     */
    private static void doSortComparison(File inputFile) {
        long startTime;
        long endTime;
        SimpleSort sorter;
        String sortTypeName = "";
        readFileToArray(inputFile);
        
        for (int i=1; i<=3; i++) {
            sorter = new SimpleSort(theInputs);

            startTime = System.nanoTime();
            switch (i) {
            case 1:
                sortTypeName = "Bubble";
                sorter.bubbleSort();
                break;
            case 2:
                sortTypeName = "Selection";
                sorter.selectSort();
                break;
            case 3:
                sortTypeName = "Insertion";
                sorter.insertSort();
                break;
            }
            endTime = System.nanoTime();

            System.out.format("%s sort completed in %,d nanoseconds\n", sortTypeName, endTime - startTime);
        }
    }

    /**
     * Re-initialize sorter from stored array, then perform the sort
     * 
     * @param type Type of the sort - 1: bubble, 2: selection, 3: insertion
     * @throws IllegalArgumentException
     */
    private static void doSort(int type) throws IllegalArgumentException {
        if (theInputs.length == 0)
            System.out.println("There's no element in the array");

        long startTime;
        long endTime;
        theSorter = new SimpleSort(theInputs);
        String sortTypeName = "";
        File outputFile = outFile1;
        List<String> outputs = new ArrayList<>(0);

        startTime = System.nanoTime();
        switch (type) {
        case 1:
            sortTypeName = "Bubble";
            outputFile = outFile1;
            outputs = theSorter.bubbleSort(true);
            break;

        case 2:
            sortTypeName = "Selection";
            outputFile = outFile2;
            outputs = theSorter.selectSort(true);
            break;

        case 3:
            sortTypeName = "Insertion";
            outputFile = outFile3;
            outputs = theSorter.insertSort(true);
            break;

        default:
            throw new IllegalArgumentException("Sort type must be an interger from 1-3");
        }
        endTime = System.nanoTime();

        outputs.stream().forEach(System.out::println);

        System.out.format("%s sort completed in %,d nanoseconds\n", sortTypeName, endTime - startTime);
        writeListToFile(outputFile, outputs);
    }

    /**
     * Write a list of strings to file line by line
     * 
     * @param file  The file to write in to
     * @param input List of strings to be written
     */
    private static void writeListToFile(File file, List<String> input) {
        try {
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < input.size(); i++) {
                bw.write(input.get(i));
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * Read content of a file and save it to the inputs array
     * 
     * @param file The file to read from
     */
    private static void readFileToArray(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            inputLength = Integer.parseInt(br.readLine());

            theInputs = new double[inputLength];
            for (int i = 0; i < inputLength; i++) {
                theInputs[i] = Double.parseDouble(br.readLine());
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * Get inputs from the user
     * 
     * @param n Number of numbers
     * @return Array of entered numbers
     */
    private static double[] getInputArrayDouble(int n) {
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = getInputDouble(String.format("Enter the #%02d number of the array", i + 1));
        }

        return result;
    }

    /**
     * Prompt user for an integer, and keep doing it in case of invalid input
     * 
     * @param message      Prompt message
     * @param errorMessage Error message, in case the input is invalid
     * @return Entered number
     */
    private static int getInputInt(String message, String errorMessage) {
        System.out.print(message + " ");
        int result = 0;
        boolean willContinue = true;
        while (willContinue) {
            try {
                result = sc.nextInt();
                willContinue = false;
            } catch (InputMismatchException e) {
                System.out.print(errorMessage + " ");
            } finally {
                sc.nextLine();
            }
        }

        return result;
    }

    /**
     * Prompt user for an integer, and keep doing it in case of invalid input
     * 
     * @param message Prompt message
     * @return Entered number
     */
    private static int getInputInt(String message) {
        return getInputInt(message, "Please enter a valid integer number.");
    }

    /**
     * Prompt user for an double number, and keep doing it in case of invalid input
     * 
     * @param message      Prompt message
     * @param errorMessage Error message, in case the input is invalid
     * @return Entered number
     */
    private static double getInputDouble(String message, String errorMessage) {
        System.out.print(message + " ");
        double result = 0;
        boolean willContinue = true;
        while (willContinue) {
            try {
                result = sc.nextDouble();
                willContinue = false;
            } catch (InputMismatchException e) {
                System.out.print(errorMessage + " ");
            } finally {
                sc.nextLine();
            }
        }

        return result;
    }

    /**
     * Prompt user for an double number, and keep doing it in case of invalid input
     * 
     * @param message Prompt message
     * @return Entered number
     */
    private static double getInputDouble(String message) {
        return getInputDouble(message, "Please enter a valid double number.");
    }
}
