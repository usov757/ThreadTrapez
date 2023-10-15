package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Main {
    public static void main(String[] args) {

        double a = 0;
        double b = 3;
        int n = 100;

        long nano_startTime1 = System.nanoTime();
        System.out.println(CalcMethods.Trapeze(a, b, n));
        long nano_endTime1 = System.nanoTime();
        System.out.println("Время выполнения одного потока " + (nano_endTime1 - nano_startTime1));

        int numThreads = 20; //

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        // Разделение числа n на подзадачи
        int iterationsPerThread = n / numThreads;

        double h = (b - a) / n;

        // Результаты вычислений каждого потока
        double[] results = new double[numThreads];

        final long[] Alltime = {0};
        for (int i = 0; i < numThreads; i++) {

            final int threadNumber = i;
            int finalI = i+1;
            Future<Double> future = executor.submit(() -> {
                double sum = 0.0;
                int startIndex = threadNumber * iterationsPerThread;
                int endIndex = (threadNumber == numThreads - 1) ? n : startIndex + iterationsPerThread;

                System.out.printf("%s started \n", Thread.currentThread().getName());
                long nano_startTime = System.nanoTime();

                for (int j = startIndex; j < endIndex; j++) {
                    double subintervalStart = a + j * h;
                    double subintervalEnd = a + (j + 1) * h;
                    sum += CalcMethods.Trapeze(subintervalStart, subintervalEnd, 1); // Вычисляем интеграл в пределах промежутка
                }

                System.out.printf("%s fiished... \n", Thread.currentThread().getName());

                long nano_endTime = System.nanoTime();
                System.out.println("Время выполнения потока #" + finalI + " " + (nano_endTime - nano_startTime));

                Alltime[0] += (nano_endTime - nano_startTime);
                return sum;
            });

            // Сохраняем будущие результаты
            results[i] = 0.0;

            try {
                results[i] = future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        //Общее время всех потоков
        System.out.println("Общее время всех потоков " + Alltime[0]);

        // Суммирование результатов
        double integral = 0.0;
        for (int i = 0; i < numThreads; i++) {
            integral += results[i];
        }

        executor.shutdown();

        System.out.println("Результат: " + integral);
    }


}
