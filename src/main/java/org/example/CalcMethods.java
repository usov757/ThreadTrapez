package org.example;

public class CalcMethods {
    static double f(double x){
        return 3*x+1; // наша функция
    }
    static double Left_Rect(double a, double b, int n){
        double h = (b - a)/n;
        double sum = 0.0;
        for (int i = 0; i <=n-1; i++){
            sum = sum + (h*f(a + i *h));
        }
        return sum;
    }

    static double Right_Rect( double a, double b, int n){
        long nano_startTime = System.nanoTime();
        double h = (b - a)/n;
        double sum = 0.0;
        for (int i = 1; i <=n; i++){
            sum = sum + (h*f(a + i *h));
        }
        long nano_endTime = System.nanoTime();
        System.out.println(nano_endTime-nano_startTime);
        return sum;
    }

    static double Trapeze( double a, double b, int n){
        double h = (b - a)/n;
        double sum = 0.0;
        for (int i = 0; i <=n-1; i++){
            sum = sum + 2*f(a + i *h);
        }
        sum = sum* h/2;
        return sum;
    }

}
