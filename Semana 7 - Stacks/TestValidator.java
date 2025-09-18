public class TestValidator {
    public static void main(String[] args) {
        ParenthesisValidator validator = new ParenthesisValidator();

        // Test cases
        String[] validCases = {
            "()",
            "()[]{}",
            "{[()]}",
            "((()))",
            ""  // Empty string is valid
        };

        String[] invalidCases = {
            "(]",
            "([)]",
            "(((",
            ")))",
            "{[(])}"
        };

        // TODO: Test your validator with these cases
        // Print results for each test
    }
}