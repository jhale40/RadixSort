// Term: Spring 2023
// Name: Justin Hale
// Program Number: Assignment Sorting
// IDE: IntelliJ IDEA 2021.3.1 (Community Edition), openjdk-17 Oracle OpenJDK version17.0.2)

import java.util.Scanner;

public class RadixSort {
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {

        String choice;
        int[] inputs;

        do {
            System.out.print("How many integer numbers do you have?: ");
            int count = in.nextInt();
            in.nextLine();

            inputs = new int[count];

            fillArray(inputs, count);
            in.nextLine();

            System.out.print("------------------------------------------------------\n" +
                    "Inputs array before sorting (radix):  ");
            printArray(inputs);
            System.out.print("\nInputs array after sorting (radix):   ");

            int max = max(inputs);
            int digitCount = DigitCount(max);
            radixSort(inputs, digitCount);
            printArray(inputs);

            System.out.print("\nDo you want to continue? (Y/N): ");
            choice = in.nextLine();

        } while (!(choice.equalsIgnoreCase("N")));

    }//main

    // Returns max number from array of integers
    public static int max(int[] array) {
        int max = 0;
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    /*Radix sort requires digit extraction. For example, given a value 149,
    you need to extract 1, 4, 9 separately from the value.*/
    public static int ExtractDigit(int number, int digitCount) {

        int position = (int) Math.pow(10, digitCount);
        int digit = (number / position) % 10;

        return digit;
    }

    // Count and return how many digits are in an integer. 7261 = 4 digits
    public static int DigitCount(int number) {
        double log = Math.log10(number);
        int digitCount = (int) (log % 10)+1;
        return digitCount;
    }

    // Takes in array of integers and sorts them with Radix Sort to ascending order
    public static void radixSort(int[] inputs, int digitCount) {

        // Create a Queue array of size 10 called bucketArray
        Queue[] bucketArray = new Queue[10];

        //Fill bucketArray with buckets(Queues)
        for (int i = 0; i < 10; i++) {
            bucketArray[i] = new Queue<Integer>();
        }


        // Outer For Loop controls how many times bucketArray will be sorted based on digitCount
        for(int i = 0; i < digitCount; i++) {

            // Inner For Loop fills buckets with corresponding digit
            for (int j = 0; j < inputs.length; j++) {
                int digit = ExtractDigit(inputs[j], i);

                bucketArray[digit].enqueue(inputs[j]); //// Enqueue
            }

            // Inner For Loop. Dequeue buckets back into array to be processed again for next digit
            // K indexes bucketArray and count indexes inputs array of integers
            int count = 0;
            for (int k = 0; k < 10; k++) {

                while(!(bucketArray[k].isEmpty())) {
                    inputs[count] = (int) bucketArray[k].dequeue();
                    count++;
                }
            }
        }
    }



    // Fill inputs array with set of integers typed by user
    public static void fillArray(int[] array, int count) {

        System.out.print("Enter " + count + " integer numbers: ");

        for (int i = 0; i < count; i++) {
            int integer = in.nextInt();
            array[i] = integer;
        }
    }

    // Print elements of array
    public static void printArray(int[] array) {

        for (int i = 0; i < array.length-1; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.print(array[array.length-1]);
    }


}//class