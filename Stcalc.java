import java.util.Scanner;

public class Stcalc {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите строковое выражение в двойных кавычках");
        String input = scn.nextLine();

        String result = calc(input);

        System.out.println("Результат: " + result);
    }

    public static String calc(String input) throws Exception {
        char action;
        String[] data;
        if (input.contains(" + ")) {
            data = input.split(" \\+ ");
            action = '+';
        } else if (input.contains(" - ")) {
            data = input.split(" - ");
            action = '-';
        } else if (input.contains(" * ")) {
            data = input.split(" \\* ");
            action = '*';
        } else if (input.contains(" / ")) {
            data = input.split(" / ");
            action = '/';
        } else {
            throw new Exception("Некорректный знак действия");
        }

        if (action == '*' || action == '/') {
            if (data[1].contains("\""))
                throw new Exception("Строчку можно делить или умножать только на число");
            else {
                int number = Integer.parseInt(data[1]);
                if (number < 1 || number > 10) {
                    throw new Exception("Число может быть только от 1 до 10 включительно");
                }
            }
        }

        if (action == '+' || action == '-') {
            if (!data[0].contains("\"") || !data[1].contains("\""))
                throw new Exception("строчку можно вычитать и складывать только со строчкой");
        }

        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "");
            if (data[i].length() > 10) {
                throw new Exception("Строка не должна быть длиннее 10 символов");
            }
        }

        if (action == '+') {
            String result = data[0] + data[1];
            if (result.length() > 40) {
                result = result.substring(0, 40) + "...";
            }
            return result;
        } else if (action == '*') {
            int multiplier = Integer.parseInt(data[1]);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < multiplier; i++) {
                sb.append(data[0]);
            }
            String result = sb.toString();
            if (result.length() > 40) {
                result = result.substring(0, 40) + "...";
            }
            return result;
        } else if (action == '-') {
            int index = data[0].indexOf(data[1]);
            if (index == -1) {
                return data[0];
            } else {
                String result = data[0].substring(0, index);
                result += data[0].substring(index + data[1].length());
                if (result.length() > 40) {
                    result = result.substring(0, 40) + "...";
                }
                return result;
            }
        } else {
            int newLen = data[0].length() / Integer.parseInt(data[1]);
            String result = data[0].substring(0, newLen);
            if (result.length() > 40) {
                result = result.substring(0, 40) + "...";
            }
            return result;
        }
    }
}
