package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите функцию (например, sin(x) or cos(x)+sin(x)): ");
        String function = scanner.nextLine();

        System.out.print("Введите значение a начало отрезка: ");
        double a = scanner.nextDouble();

        System.out.print("Введите значение b конец отрезка: ");
        double b;
        String input = scanner.next();
        if (input.equalsIgnoreCase("pi")) {
            b = Math.PI;
        } else {
            b = Double.parseDouble(input);
        }

        System.out.print("Введите значение h шаг: ");
        double h = scanner.nextDouble();

        System.out.print("Введите количество потоков: ");
        double maxThreads = scanner.nextDouble();

        for (int threadCount = 1; threadCount <= maxThreads; threadCount++) {
            CalcThread.calculateIntegrals(a, b, h, threadCount, function);
        }

    }
}
