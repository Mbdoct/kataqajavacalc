import java.util.Scanner;

public class StrochnbIKalk {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите выражение для строкового калькулятора: ");
        String input = scan.nextLine();

        String[] tokens = input.split(" ");

        if (tokens.length != 3) {
            throw new Exception("Некорректное выражение");
        }

        String operand1 = tokens[0];
        String operator = tokens[1];
        String operand2 = tokens[2];

        if (!operand1.startsWith("\"") || !operand1.endsWith("\"")) {
            throw new Exception("Первый операнд должен быть строкой в двойных кавычках");
        }

        operand1 = operand1.substring(1, operand1.length() - 1);

        if (!isInteger(operand2) || Integer.parseInt(operand2) < 1 || Integer.parseInt(operand2) > 10) {
            throw new Exception("Второй операнд должен быть целым числом от 1 до 10");
        }

        int number = Integer.parseInt(operand2);
        String result = "";

        if (operator.equals("+")) {
            result = operand1 + operand2;
        } else if (operator.equals("-")) {
            if (!operand1.contains(operand2)) {
                result = operand1;
            } else {
                result = operand1.replace(operand2, "");
            }
        } else if (operator.equals("*")) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < number; i++) {
                sb.append(operand1);
            }
            result = sb.toString();
        } else if (operator.equals("/")) {
            if (operand1.length() <= number) {
                throw new Exception("Делитель больше или равен длине строки");
            }
            result = operand1.substring(0, operand1.length() / number);
        } else {
            throw new Exception("Некорректная операция");
        }

        if (result.length() > 40) {
            result = result.substring(0, 40) + "...";
        }

        System.out.println("Результат: " + result);
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
