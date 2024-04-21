import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversePolish {
    public static Double evaluation(String postfix){
        Stack<Double> stack = new Stack<>();
        for (String element: postfix.split(" ")){
            if(isInteger(element)){
                stack.push(Double.parseDouble(element));
            }
            else {
                Double no1 = stack.pop();
                Double no2 = stack.pop();
                switch (element) {
                    case "+" -> stack.push(no2 + no1);
                    case "-" -> stack.push(no2 - no1);
                    case "*" -> stack.push(no2 * no1);
                    case "/" -> stack.push(no2 / no1);
                    case "%" -> stack.push(no2 % no1);
                }
            }
        }
        return stack.pop();
    }

    public static boolean isInteger(String element){
        try {
            Double.parseDouble(element);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public static String infixToPostfix(String infix){
        Stack<String> operators = new Stack<>();
        List<String> postfix = new ArrayList<>();
        for(String element: infix.split(" ")){
            if(isOperator(element)){
                while (!operators.empty() &&
                        hasLowerPrecedence(element, operators.peek())){
                    postfix.add(operators.pop());
                }
                operators.push(element);
            }
            else {
                postfix.add(element);
            }
        }
        while (!operators.empty()){
            postfix.add(operators.pop());
        }
        return String.join(" ", postfix);
    }

    public static boolean isOperator(String operator){
        return List.of("+", "-" , "*", "/" , "%").contains(operator);
    }

    public static boolean hasLowerPrecedence(String op1, String op2){
        return precedence(op1) < precedence(op2);
    }

    public static int precedence(String operator){
        return switch (operator) {
            case "+", "-" -> 1;
            case "%", "/", "*"-> 2;
            default -> 0;
        };
    }
}
