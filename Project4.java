// The main driver class for the Polynomial Manager application.
// Reads user commands from standard input and delegates them to PolyList methods.
import java.util.Scanner;

public class Project4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PolyList polyList = new PolyList();

        while(true){
            String input = scanner.nextLine().trim();
            if(input.startsWith("INSERT")){
                polyList.insert(input);
            } else if (input.startsWith("DELETE")){
                String[] parts = input.split(" ");
                polyList.delete(parts[1]);
            } else if (input.startsWith("SEARCH")){
                String[] parts = input.split(" ");
                polyList.search(parts[1]);
            } else if (input.startsWith("QUIT")){
                polyList.quit();
                break;
            }
        }
        scanner.close();
    }
    
}