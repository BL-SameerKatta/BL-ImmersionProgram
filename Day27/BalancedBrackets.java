import java.util.Stack;

/**
 * Validates if a string of code has properly balanced brackets '()', '{}', '[]'.
 * Uses a Stack data structure.
 */
public class BalancedBrackets {
    public static boolean isValid(String code) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : code.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } 
            else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty()) return false; 
                
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                    (c == '}' && top != '{') ||
                    (c == ']' && top != '[')) {
                    return false; 
                }
            }
        }
        return stack.isEmpty();
    }
}
