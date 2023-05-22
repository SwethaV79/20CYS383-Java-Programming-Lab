import java.util.Scanner;


interface Calculator {
    double calculate(double num1, double num2, String operator);
}


class BasicCalculator implements Calculator {
    @Override
    public double calculate(double num1, double num2, String operator) {
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
public class CalculatorExample {
    public static void main(String[] args) {

        Calculator calculator = new BasicCalculator();


        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter the first number: ");
        double num1 = scanner.nextDouble();


        System.out.print("Enter the operator (+, -, *, /): ");
        String operator = scanner.next();


        System.out.print("Enter the second number: ");
        double num2 = scanner.nextDouble();


        double result = calculator.calculate(num1, num2, operator);


        System.out.println("Result: " + result);


        scanner.close();
    }
}
