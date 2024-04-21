import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String infix = s.nextLine();
        String postfix = ReversePolish.infixToPostfix(infix);
        System.out.println(postfix);
        System.out.println(ReversePolish.evaluation(postfix));
    }
}


