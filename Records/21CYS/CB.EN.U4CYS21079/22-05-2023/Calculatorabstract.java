import java.util.Scanner;

// Abstract calculator class
abstract class Calculator {
    public abstract double calculate(double num1, double num2);
}


class BasicCalculator extends Calculator {
    private String operator;

    public BasicCalculator(String operator) {
        this.operator = operator;
    }

    @Override
    public double calculate(double num1, double num2) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new IllegalArgumentException("Cannot divide by zero!");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}

// Main class
public class Calculatorabstract {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter the first number: ");
        double num1 = scanner.nextDouble();


        System.out.print("Enter the operator (+, -, *, /): ");
        String operator = scanner.next();


        System.out.print("Enter the second number: ");
        double num2 = scanner.nextDouble();


        Calculator calculator = new BasicCalculator(operator);


        double result = calculator.calculate(num1, num2);


        System.out.println("Result: " + result);


        scanner.close();
    }
}
