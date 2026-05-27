/**
 * Project5.java
 * 
 * This class handles input, parsing, and interaction with ArrayBinaryTree.
 * It reads prefix (with '!') or postfix (with '@') expressions from standard input,
 * builds a binary expression tree, and outputs:
 * - The converted expression in the opposite notation
 * - A graphical representation of the tree printed from left to right
 *   (Note: the printed tree's left-most value is the root).
 */
import java.util.*;

public class Project5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            char type = input.charAt(0);
            String expression = input.substring(1).trim();
            ArrayBinaryTree tree = new ArrayBinaryTree(1024);  // Increased capacity to support trees up to height 9

            try {
                if (type == '!') {
                    buildPrefixTree(tree, new StringTokenizer(expression), 0);
                    System.out.println("Postfix: " + tree.toPostfix());
                } else if (type == '@') {
                    List<String> tokens = new ArrayList<>();
                    StringTokenizer tokenizer = new StringTokenizer(expression);
                    while (tokenizer.hasMoreTokens()) tokens.add(tokenizer.nextToken());
                    int[] pos = {tokens.size() - 1};
                    buildPostfixTree(tree, tokens, 0, pos);
                    System.out.println("Prefix: " + tree.toPrefix());
                } else {
                    System.out.println("Invalid expression type.");
                    continue;
                }

                System.out.println("Graphical Tree:");
                tree.printGraphically();

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void buildPrefixTree(ArrayBinaryTree tree, StringTokenizer stream, int index) {
        if (!stream.hasMoreTokens() || index >= tree.length()) return;

        String token = stream.nextToken();
        tree.setElement(index, token);

        if (isOperator(token)) {
            buildPrefixTree(tree, stream, 2 * index + 1);
            buildPrefixTree(tree, stream, 2 * index + 2);
        }
    }

    public static void buildPostfixTree(ArrayBinaryTree tree, List<String> tokens, int index, int[] pos) {
        if (pos[0] < 0 || index >= tree.length()) return;

        String token = tokens.get(pos[0]--);
        tree.setElement(index, token);

        if (isOperator(token)) {
            buildPostfixTree(tree, tokens, 2 * index + 2, pos);
            buildPostfixTree(tree, tokens, 2 * index + 1, pos);
        }
    }

    public static boolean isOperator(String token) {
        return "+-*/".contains(token);
    }
}
