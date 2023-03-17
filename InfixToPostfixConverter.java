import java.util.*;

public class InfixToPostfixConverter {

    public static String convertToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                postfix.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }

                if (!stack.isEmpty() && stack.peek() != '(') {
                    return "Invalid expression";
                } else {
                    stack.pop();
                }
            } else {
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                    postfix.append(stack.pop());
                }

                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid expression";
            }

            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    private static int precedence(char ch) {
        switch(ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);

        System.out.print("Enter an infix expression: ");
        String infix = x.nextLine();

        String postfix = convertToPostfix(infix);

        System.out.println("Infix expression: " + infix);
        System.out.println("Postfix expression: " + postfix);

        x.close();
    }
}
