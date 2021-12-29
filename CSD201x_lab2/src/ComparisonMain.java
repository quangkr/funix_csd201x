import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ComparisonMain {
    private static int inputLength = 0;
    private static int[] theInputs = new int[0];
    private static SimpleSort theSorter;

    public static void main(String[] args) {
        int operation;
        int numberOfValues;
        int maximumValue;
        int sortType;
        File file = new File("generatedNumbers.txt");
        
        try {
            operation = Integer.parseInt(args[0]);
        } catch(IndexOutOfBoundsException e) {
            e.printStackTrace();
            return;
        }

        switch (operation) {
        case 1: { 
            try {
                numberOfValues = Integer.parseInt(args[1]);
                maximumValue = Integer.parseInt(args[2]);
                generateRandomNumbersToFile(numberOfValues, maximumValue, file);
            } catch(IndexOutOfBoundsException e) {
                e.printStackTrace();
                return;
            }
        }
            break;

        case 2: { 
            try {
                sortType = Integer.parseInt(args[1]);
                readFileToArray(file);
                doSort(sortType);
            } catch(IndexOutOfBoundsException e) {
                e.printStackTrace();
                return;
            }
        }
            break;

        default:
            return;
        }
    }

    /**
     * Re-initialize the sorter, then perform the sort
     * 
     * @param type Type of the sort - 1: bubble, 2: selection, 3: insertion
     * @throws IllegalArgumentException
     */
    private static void doSort(int type) throws IllegalArgumentException {
        long startTime;
        long endTime;
        theSorter = new SimpleSort(theInputs);
        String sortTypeName = "";
        File output = new File("output.txt");

        startTime = System.nanoTime();
        switch (type) {
        case 1:
            sortTypeName = "Bubble";
            theSorter.bubbleSort();
            break;

        case 2:
            sortTypeName = "Selection";
            theSorter.selectSort();
            break;

        case 3:
            sortTypeName = "Insertion1";
            theSorter.insertSort1();
            break;

        case 4:
            sortTypeName = "Insertion2";
            theSorter.insertSort2();
            break;

        case 5:
            sortTypeName = "Insertion3";
            theSorter.insertSort3();
            break;

        default:
            throw new IllegalArgumentException("Sort type must be an interger from 1-5");
        }
        endTime = System.nanoTime();

        output = new File("out" + type + sortTypeName + ".txt");
        writeArrayToFile(output, theSorter.get());

        System.out.format("%s sort completed in %,d nanoseconds\n", sortTypeName, endTime - startTime);
    }

    /**
     * Generate random numbers, then save them to a file
     * 
     * @param numberOfValues Number of values to be generated
     * @param maximumValue   Maximum value possible
     * @param file           The file to save data to
     */
    private static void generateRandomNumbersToFile(int numberOfValues, int maximumValue, File file) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            bw.write(String.valueOf(numberOfValues));
            bw.newLine();

            for (int i = 0; i < numberOfValues; i++) {
                bw.write(String.valueOf((int) (Math.random() * maximumValue)));
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

            theInputs = new int[inputLength];
            for (int i = 0; i < inputLength; i++) {
                theInputs[i] = Integer.parseInt(br.readLine());
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * Save content of the stored array to a file
     * 
     * @param file The file to write data to
     */
    private static void writeArrayToFile(File file, int[] array) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for (int i = 0; i < inputLength; i++) {
                bw.write(String.valueOf(array[i]));
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
