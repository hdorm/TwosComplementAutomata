import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TwosComplementAutomata {
    // Global variable for storing the binary digits
    static List<Integer> binaryList = new ArrayList<>();

    // Method that presents menu and carries out operations
    public static void runProgram() {
        // Variables necessary for operation of program
        boolean cont = true;
        int numbersInput;
        Scanner scanOne = new Scanner(System.in);
        // While loop which keeps displaying menu until program exit
        while (cont) {
            System.out.println("---------------MAIN MENU---------------");
            System.out.println("1. Read input binary number");
            System.out.println("2. Compute and display two's complement of input binary number");
            System.out.println("3. Exit program");
            System.out.println();
            System.out.print("Enter option number: ");
            numbersInput = scanOne.nextInt();
            // Takes input from user if option 1 is chosen and converts the input into an array list of integers
            if (numbersInput == 1) {
                // Clears the list at the beginning of each loop to prevent double input
                binaryList.clear();
                // Boolean to stop program if the user input is not a binary number
                boolean stop = false;
                // More necessary variable declarations for receiving user input
                String userInput;
                Scanner scanTwo = new Scanner(System.in);
                System.out.print("Please enter a binary number: ");
                userInput = scanTwo.nextLine();
                // Adds zeroes to the array list so the indices can be modified with the user data
                for (int j = 0; j < userInput.length(); j++) {
                    binaryList.add(0);
                }
                // Converts the user's inputted String into individual integers and adds them to the array list
                for (int i = 0; i < userInput.length(); i++) {
                    binaryList.set(userInput.length() - i - 1, Integer.parseInt(String.valueOf(userInput.charAt(userInput.length() - i - 1))));
                }
                // Checks the array list for anything besides a zero or one
                for (Integer integer : binaryList) {
                    if (integer == 0) {
                        stop = false;
                    } else stop = integer != 1;
                }
                // Checks if something besides a zero or one was found. Clears the list and presents an error if true
                if (stop) {
                    System.out.println("The number you entered was not binary!");
                    binaryList.clear();
                }
                System.out.println();
                // Takes the array list of integers and sends it to state A of the agent
            } else if (numbersInput == 2) {
                // Checks that the array list contains data
                if (binaryList.size() > 0) {
                    // Creates StringBuilder for building final output
                    StringBuilder twosComplement = new StringBuilder();
                    // Creates the array with the final data by sending the array list to state A of the agent
                    int[] finalArray = stateA((ArrayList<Integer>) binaryList);
                    // Loop which builds string for output with the completed two's complement data
                    for (int i : finalArray) {
                        twosComplement.append(i);
                    }
                    // Displays the completed String
                    System.out.println("The two's complement of the entered binary number is: " + twosComplement);
                    System.out.println();
                }
                // Throws error if the array list does not contain data
                else {
                    System.out.println("You need to enter a binary number before running this option!");
                    System.out.println();
                }
                // Exits the program
            } else if (numbersInput == 3) {
                cont = false;
            }
        }
    }

    // First state of the agent which adds output to new complete array until a one is encountered
    public static int[] stateA(ArrayList<Integer> array) {
        int[] newArray = new int[array.size()];
        int[] twosComplement = new int[array.size()];
        // Loop continues until a one is found
        for (int i = 0; i < array.size(); i++) {
            int currentIndexValue = array.get(array.size() - i - 1);
            // Goes to next iteration of loop if the digit found is zero
            if (currentIndexValue == 0) {
                newArray[array.size() - i - 1] = 0;
                // Transfers to state B by breaking the loop when a one is found after adding the found one to the completed array
            } else if (currentIndexValue == 1) {
                newArray[array.size() - i - 1] = 1;
                // New array which holds the completed array after state B of the agent returns it after finishing
                twosComplement = stateB(array, newArray, array.size() - i - 2);
                break;
            }
        }
        // Returns the final completed two's complement array so StringBuilder can turn it into a string for output
        return twosComplement;
    }

    // State B of the agent which flips the data found after state A is done
    public static int[] stateB(ArrayList<Integer> arrayOne, int[] arrayTwo, int currentIndex) {
        // Loops until the index of the array reaches zero as that means all data has been gone over
        while (currentIndex >= 0) {
            // Sets next output to zero if the current input is one
            if (arrayOne.get(currentIndex) == 1) {
                arrayTwo[currentIndex] = 0;
                // Sets next output to one if the current input is zero
            } else if (arrayOne.get(currentIndex) == 0) {
                arrayTwo[currentIndex] = 1;
            }
            // Decrements the current index
            currentIndex--;
        }
        // Returns the completed array so it can be outputted
        return arrayTwo;
    }

    // Main method which runs the method containing the menu options
    public static void main(String[] args) {
        runProgram();
    }
}