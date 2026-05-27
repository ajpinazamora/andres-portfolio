/* Homework 1
 * @author:  Andres Joab Pina Zamora
 * 
 * This Java program processes binary images from a text file (images.txt) or Keyboard input, 
 * identifies groups of connected white pixels (*), and labels them with 
 * unique characters (a, b, c, ...). It reads the number of images, 
 * their dimensions, and pixel data, storing each image in a 2D array. 
 * Using a recursive depth-first search, it labels all adjacent white pixels
 * connected in the north, south, east, or west directions. Once processed, 
 * the program prints the labeled images to the terminal, making it easy to 
 * visualize the connected components within each image.
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class hw1 {
    public static void main(String[] args) {
        Scanner  scanner;
        if (args.length > 0){
            try{
                // Reads from file if provided use java hw1 < images.txt 
                scanner = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e){
                System.out.println("Error: File not found");
                return;
            }
        } else{
            //Read from keybord if no file is given
            scanner = new Scanner(System.in);
            System.out.println("Enter the number of images followed by dymantion and images:");
        }

        //Read the number of images
        int n = scanner.nextInt();
        
        for(int i = 0; i < n; i++){
            // Print which image is being processed
            System.out.println("Processing Image" + (i+1) + ":");
            //Read dimension of the image
            int rows = scanner.nextInt();
            int cols = scanner.nextInt();
            scanner.nextLine();

            //Read the image into a 2d array
            char[][] image = new char[rows][cols];
            for(int r = 0; r < rows; r++){
                image[r] = scanner.nextLine().toCharArray();
            }
            //Process and Lable the image
            processImage(image);

            //Print the Labeled Immage
            printImage(image);
        }
        scanner.close();
        
    } 


    
    public static void labelConnectedPixels(char[][] image, int row, int col, char label){
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length || image[row][col] != '*'){
            return;
        }
         image[row][col] = label;

         labelConnectedPixels(image, row - 1, col, label); //North
         labelConnectedPixels(image , row + 1, col, label); //South
         labelConnectedPixels(image, row, col - 1, label); //West
         labelConnectedPixels(image, row, col + 1, label); //East
    }
    
    public static void processImage(char[][] image) {
        char label = 'a';

        for(int row = 0; row < image.length; row++){
            for(int col = 0; col < image[0].length; col++){
                if (image[row][col] == '*'){
                    labelConnectedPixels(image, row, col, label);
                    label++;
                }
            }
        }
        
    }

    public static void printImage(char[][] image){
        System.out.println("Labeled Image:");
        for (char[] row : image){
            System.out.println(new String(row));
        }
        System.out.println();
    }
}