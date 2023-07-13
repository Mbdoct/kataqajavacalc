import java.util.Scanner;

public class StrochnbIKalk {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in); // Эта строка создает новый объект Scanner с именем scan, который связан
                                               // с системным вводом.
        System.out.println("Введите числа для строкового калькулятора: ");
        String input = scan.nextLine(); // Эта строка считывает ввод пользователя с помощью метода nextLine() у объекта
                                        // scan и сохраняет введенную строку в переменной input.
        char operaciya; // Эта строка объявляет переменную operaciya типа char, которая будет содержать
                        // операцию (+, -, *, /).
        String[] operanda; // Эта строка объявляет массив строк operanda, который будет содержать операнды
                           // (числа или строки).
        if (input.contains(" + ")) { // Эта строка проверяет, содержит ли введенная строка операцию сложения. Если
                                     // да, то выполняется код внутри этого блока.
            operanda = input.split(" \\+ "); // Эта строка разделяет введенную строку на операнды (числа или строки) с
                                             // помощью метода split(). Разделитель "+ " используется в регулярном
                                             // выражении. Результат разделения сохраняется в массиве operanda.

            operaciya = '+';
        } else if (input.contains(" - ")) {
            operanda = input.split(" - ");
            operaciya = '-';
        } else if (input.contains(" * ")) {
            operanda = input.split(" \\* ");
            operaciya = '*';
        } else if (input.contains(" / ")) {
            operanda = input.split(" / ");
            operaciya = '/';
        } else {
            throw new Exception("Некорректная операция"); // Если ни одной операции не найдено, выполняется код внутри
                                                          // этого блока.
        }
        if (operaciya == '*' || operaciya == '/') { // Эта строка проверяет, является ли операция умножения или деления.
            if (operanda[1].contains("\"")) // Если второй операнд содержит символ кавычек, выполняется код внутри этого
                                            // блока.
                throw new Exception("Строку можно делить или умножать только на число");
        }
        for (int i = 0; i < operanda.length; i++) { // Эта строка начинает цикл for, который выполняется для каждого
                                                    // элемента в массиве operanda.
            operanda[i] = operanda[i].replace("\"", ""); // Эта строка заменяет символы кавычек в операнде пустой
                                                         // строкой с помощью метода replace().
        }

        if (operaciya == '+') {
            printInQuotes(operanda[0] + operanda[1]); // Выполняется конкатенация операндов operanda[0] и operanda[1], и
                                                      // результат передается в метод printInQuotes().
        } else if (operaciya == '*') {
            int multiplier = Integer.parseInt(operanda[1]); // Строка operanda[1] преобразуется в целое число с помощью
                                                            // метода parseInt(), и результат сохраняется в переменной
                                                            // multiplier.
            String resultat = "";
            for (int i = 0; i < multiplier; i++) { // Начинается цикл for, который будет выполнять итерации multiplier
                resultat += operanda[0]; // Каждый раз в цикле операнд operanda[0] добавляется к строке resultat.
            }
            printInQuotes(resultat);
        } else if (operaciya == '-') {
            int index = operanda[0].indexOf(operanda[1]); // Ищется индекс первого вхождения операнда operanda[1] в
                                                          // операнде operanda[0] с помощью метода indexOf().
            if (index == -1) {
                printInQuotes(operanda[0]); // Операнд operanda[0] передается в метод printInQuotes(), чтобы вывести его
                                            // на экран.
            } else {
                String resultat = operanda[0].substring(0, index); // Создается новая строка resultat, которая содержит
                                                                   // подстроку операнда operanda[0] от начала до
                                                                   // индекса.
                resultat += operanda[0].substring(index + operanda[1].length()); // К строке resultat добавляется
                                                                                 // подстрока операнда operanda[0] после
                                                                                 // индекса, не включая операнд
                                                                                 // operanda[1].
                printInQuotes(resultat);
            }
        } else {
            int newLen = operanda[0].length() / Integer.parseInt(operanda[1]); // Длина операнда operanda[0] делится на
                                                                               // целое значение второго операнда
                                                                               // Integer.parseInt(operanda[1]) и
                                                                               // результат сохраняется в переменной
                                                                               // newLen.
            String resultat = operanda[0].substring(0, newLen); // Создается новая строка resultat, которая содержит
                                                                // подстроку операнда operanda[0] от начала до новой
                                                                // длины.
            printInQuotes(resultat);
        }

    }

    static void printInQuotes(String text) { // Объявляется статический метод printInQuotes, который принимает аргумент
                                             // text типа String.
        System.out.println("\"" + text + "\""); // Выводится на экран содержимое переменной text, заключенное в двойные
                                                // кавычки с помощью метода println() класса System.out.
    }
}
