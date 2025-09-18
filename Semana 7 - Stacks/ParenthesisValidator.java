
import java.util.Stack;
import java.util.StringTokenizer;

public class ParenthesisValidator {
    // TODO: Add any necessary attributes
    public ParenthesisValidator() {
        // TODO: Initialize your validator
    }

    public boolean isValid(String expression) {
        Stack<String> stackValidator = new Stack<String>();
        StringTokenizer tokenizer = new StringTokenizer(expression);
        while(tokenizer.hasMoreTokens()) {
            String bracer = tokenizer.nextToken();
        }
        return false;
    }

    // TODO: Add any helper methods you need
    ;

}