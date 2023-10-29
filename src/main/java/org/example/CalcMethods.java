package org.example;

public class CalcMethods {

    static double f(String function, double x) {
        switch (function) {
            case "sin(x)":
                return Math.sin(x);
            case "cos(x)":
                return Math.cos(x);
            case "cos(x)+sin(x)":
                return Math.cos(x)+Math.sin(x);
            case "exp(x)":
                return Math.exp(x);
            case "exp(x)+x":
                return Math.exp(x)+x;
            case "cos(x)^2":
                return Math.pow(Math.cos(x),2);
            case "sin(x)^2":
                return Math.pow(Math.sin(x),2);
            case "cox(x^2)":
                return Math.cos(Math.pow(x,2));
            case "sin(x^2)":
                return Math.sin(Math.pow(x,2));
            default:
                return 0;
        }
    }

//    static double Left_Rect(double a, double b, int n){
//        double h = (b - a)/n;
//        double sum = 0.0;
//        for (int i = 0; i <=n-1; i++){
//            sum = sum + (h*f(a + i *h));
//        }
//        return sum;
//    }
//
//    static double Right_Rect( double a, double b, int n){
//        long nano_startTime = System.nanoTime();
//        double h = (b - a)/n;
//        double sum = 0.0;
//        for (int i = 1; i <=n; i++){
//            sum = sum + (h*f(a + i *h));
//        }
//        long nano_endTime = System.nanoTime();
//        System.out.println(nano_endTime-nano_startTime);
//        return sum;
//    }

    static double Trapeze(double a, double b, double n, String function) {
        double h = (b - a) / n;
        double sum = 0.0;

        for (int i = 0; i <= n - 1; i++) {
            sum = sum + 2 * f(function,a + i * h);
        }
        sum = sum * h / 2;
        return sum;
    }

}
