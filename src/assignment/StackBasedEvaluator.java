package assignment;

import java.util.Stack;

public class StackBasedEvaluator {
    public double evaluate(String input) {
        Stack<Double> stack = new Stack<Double>();

        for (String s : input.split(" ")) {
            double n2;
            double n1;

            switch (s) {
                case "**":
                    n2 = stack.pop();
                    n1 = stack.pop();
                    stack.push(Math.pow(n1, n2));
                    break;
                case "%":
                    n2 = stack.pop();
                    n1 = stack.pop();
                    stack.push(n1 % n2);
                    break;
                case "//":
                    n1 = stack.pop();
                    stack.push(Math.sqrt(n1));
                    break;
                case "*":
                    n2 = stack.pop();
                    n1 = stack.pop();
                    stack.push(n1 * n2);
                    break;
                case "/":
                    n2 = stack.pop();
                    n1 = stack.pop();
                    stack.push(n1 / n2);
                    break;
                case "+":
                    n2 = stack.pop();
                    n1 = stack.pop();
                    stack.push(n1 + n2);
                    break;
                case "-":
                    n2 = stack.pop();
                    n1 = stack.pop();
                    stack.push(n1 - n2);
                    break;
                default:
                    stack.push(Double.valueOf(s));
                    break;
            }
        }

        return stack.pop();
    }
}
