import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static int calculate(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        char[] array = str.toCharArray();

        boolean isNumber = false;
        int number = 0;
        Queue<Integer> numbers = new LinkedList<>();
        Queue<Character> operations = new LinkedList<>();

        for (int i = 0; i < array.length; i++) {
            if (array[i] > '0' && array[i] < '9') {
                number = number * 10 + array[i] - '0';
                isNumber = true;
            } else {
                if (isNumber) {
                    numbers.add(number);
                    number = 0;
                    isNumber = false;
                }
                operations.add(array[i]);
            }
            if (i + 1 == array.length && isNumber) {
                numbers.add(number);
            }
        }

        if (numbers.isEmpty()) {
            return 0;
        }

        int result = numbers.poll();
        for (char operation : operations) {
            if (operation == '+') {
                result += numbers.poll();
            } else if (operation == '-') {
                result -= numbers.poll();
            } else if (operation == '*') {
                result *= numbers.poll();
            } else if (operation == '/') {
                result /= numbers.poll();
            }
        }
        return result;
    }

    public static void fillArrayWithIncreasingNumbers(int[] array) {
        int middle = array.length / 2;
        if (array.length % 2 == 0) {
            for (int i = middle, j = 0; i < array.length; ++i, j++) {
                array[i] = j;
            }
            for (int i = 0, j = middle - 1; i < middle; ++i, j--) {
                array[i] = j;
            }
        } else {
            for (int i = 0; i <= middle; i++) {
                array[middle + i] = i;
                array[middle - i] = i;
            }
        }
    }

    public static void task1() throws java.io.IOException {
        System.out.println("Please, write the equation: ");
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        String example = reader.readLine();

        System.out.println("The read line is: " + example);
        System.out.println("Result: " + calculate(example));
    }

    public static void task2() {
        int[] array = new int[10];
        fillArrayWithIncreasingNumbers(array);
        for (int num : array) {
            System.out.print(num + " ");
        }
    }

    public static String enterNewValue(String str) throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();

        String stringForInsert = " " + inputString.substring(1);

        if (inputString.startsWith("+")) {
            if (!str.isEmpty()) {
                str += stringForInsert;
            } else {
                str = stringForInsert.substring(1);
            }
        } else if (inputString.startsWith("-")) {
            if (str.contains(stringForInsert)) {
                str = str.replace(stringForInsert, "");
            } else {
                System.out.println("There is no corresponding word in the collection");
            }
        } else {
            throw new IllegalArgumentException("String incorrect");
        }
        return str;
    }

    public static void task3() throws IllegalArgumentException {
        String str = "";
        Scanner scanner = new Scanner(System.in);

        finishProgram:
        while (true) {
            System.out.println("1 – ввести нове значення");
            System.out.println("2 – вивести на екран всі введені слова через кому");
            System.out.println("3 – вихід з програми");

            int answer = scanner.nextInt();
            switch (answer) {
                case 1:
                    str = enterNewValue(str);
                    break;
                case 2:
                    if (str.contains(" ")) {
                        System.out.println(str.replace(" ", ", "));
                    } else {
                        System.out.println(str);
                    }
                    break;
                case 3:
                    break finishProgram;
                default:
                    System.out.println("Incorrect input. Please, try again");
            }
        }
    }

    public static void main(String[] args) {
        try {
            task1();
            task2();
            task3();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}