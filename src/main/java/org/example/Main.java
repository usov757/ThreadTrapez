package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner console = new Scanner(System.in);
        System.out.println("Введите начало отрезка");
        int a = console.nextInt();
        System.out.println("Введите конец отрезка");
        int b = console.nextInt();
        System.out.println("Введите кол-во потоков");
        int maxThreads = console.nextInt();

        for (int threadCount = 1; threadCount <= maxThreads; threadCount++) {
            CalcThread.calculateIntegrals(a, b, 1000, threadCount);
        }

    }
}
