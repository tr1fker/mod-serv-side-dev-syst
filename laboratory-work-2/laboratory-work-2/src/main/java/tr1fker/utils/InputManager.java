package tr1fker.utils;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Класс InputManager предназначен для обработки входной информации
 */
public class InputManager {
    public static int getNextInt() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Необходимо целое. Попробуй снова: ");
            }
        }
    }
    public static int getNextIntInRange(int min, int max) {
        while (true) {
            int input = getNextInt();
            if (input < min || input > max) {
                System.out.print("Введённое находится за пределами. Попробуй снова: ");
            } else {
                return input;
            }
        }
    }
    public static long getNextLong() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return scanner.nextLong();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Необходимо длинное целое. Попробуй снова: ");
            }
        }
    }
    public static double getNextDouble() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Необходимо вещественное. Попробуй снова: ");
            }
        }
    }
    public static String getNextLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    // При вводе "-" возвращает пустую строку
    public static String getNextLineWithSkip() {
        String line = getNextLine();
        if (line.equals("-")) {
            return "";
        }
        return line;
    }
}