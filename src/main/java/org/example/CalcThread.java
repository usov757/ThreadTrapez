package org.example;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.DoubleUnaryOperator;

public class CalcThread {
    public static void calculateIntegrals(double a, double b, double n, double numThreads, String function) {
        long nano_startTime1 = System.nanoTime();
//        System.out.println("Результат: " + CalcMethods.Trapeze(a, b, n));
        long nano_endTime1 = System.nanoTime();
//        System.out.println("Время для главного потока: " + (nano_endTime1 - nano_startTime1));

        ExecutorService executor = Executors.newFixedThreadPool((int) numThreads);

        double iterationsPerThread = n / numThreads;
        double h = (b - a) / (double) n;
        double[] results = new double[(int) numThreads];
        final long[] allTime = {0};

        for (int i = 0; i < numThreads; i++) {
            final int threadNumber = i;
            int finalI = i + 1;

            Future<Double> future = executor.submit(() -> {
                double sum = 0.0;
                double startIndex = threadNumber * iterationsPerThread;
                double endIndex = (threadNumber == numThreads - 1) ? n : startIndex + iterationsPerThread;

                System.out.printf("%s стартовал\n", Thread.currentThread().getName());
                long nano_startTime = System.nanoTime();

                for (double j = startIndex; j < endIndex; j++) {
                    double subintervalStart = a + j * h;
                    double subintervalEnd = a + (j + 1) * h;
                    sum += CalcMethods.Trapeze(subintervalStart, subintervalEnd, 1, function);
                }

                System.out.printf("%s завершился\n", Thread.currentThread().getName());
                long nano_endTime = System.nanoTime();
                System.out.println("Время выполнения потока #" + finalI + ": " + (nano_endTime - nano_startTime));

                synchronized (allTime) {
                    allTime[0] += (nano_endTime - nano_startTime);
                }
                return sum;
            });

            results[i] = 0.0;

            try {
                results[i] = future.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        System.out.println("-------" );
        System.out.println("Время для главного потока: " + (nano_endTime1 - nano_startTime1));
        System.out.println("-------" );
        System.out.println("Время выполнения всех потоков: " + allTime[0]);

        double finishresult = 0.0;
        for (int i = 0; i < numThreads; i++) {
            finishresult += results[i];
        }
        System.out.println("-------" );
        System.out.println("Результат: " + CalcMethods.Trapeze(a, b, n,function));
        System.out.println("-------" );
        System.out.println("Результат!: " + finishresult);



        System.out.println("----------------------------------" );
    }
}