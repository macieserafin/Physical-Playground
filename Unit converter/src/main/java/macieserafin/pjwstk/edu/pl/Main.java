package macieserafin.pjwstk.edu.pl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        UnitsDb db = UnitsLoader.load();
        ConverterService conv = new ConverterService(db);

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("Welcome to Mini Unit Converter");

        while (isRunning) {
            double value = readDouble(scanner, "Enter value: ");
            String fromSymbol = readNonEmpty(scanner, "Enter from symbol: ");
            String toSymbol = readNonEmpty(scanner, "Enter to symbol: ");

            try {
                double result = conv.convert(value, fromSymbol, toSymbol);
                System.out.println("Result: " + result);
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

            String answer = readNonEmpty(scanner, "Would you like to continue? (y/n): ");
            if (answer.equalsIgnoreCase("n")) {
                isRunning = false;
            }
        }

        scanner.close();
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readNonEmpty(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) return line;
            System.out.println("Value cannot be empty.");
        }
    }
}
