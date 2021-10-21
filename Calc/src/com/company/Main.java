package com.company;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        double result=0, num1, num2;
        char operation;
        while(true) {
            menu();
            String select = scanner.next();
            switch (select) {
                case "1" -> {
                    num1 = getNumber();
                    num2 = getNumber();
                    operation = getOperation();
                    result = calc(num1, num2, operation);
                    System.out.println("Результат = "+result);
                }
                case "2" -> {
                    if (result == 0) {
                        System.out.println("Це перша операція.");
                        break;
                    }
                    num1 = result;
                    System.out.println("Перше число = "+num1);
                    num2 = getNumber();
                    operation = getOperation();
                    result = calc(num1, num2, operation);
                    System.out.println("Результат = "+result);
                }
                case "3" -> System.exit(1);
            }
        }
    }

    public static void menu(){
        System.out.println("Калькулятор");
        System.out.println("1. Розпочати");
        System.out.println("2. Продовжити з попереднім результатом");
        System.out.println("3. Вихід");
    }

    public static double getNumber() {
        System.out.print("Введіть число: ");
        double num;
        if (scanner.hasNextDouble()) {
            num = scanner.nextDouble();
        } else {
            System.out.println("Помилка!!! Введено не число.\n" +
                    "Якщо ви ввели дробове число спробуйте поставити " +
                    "кому замість крапки перед дробовою частиною!!!");
            scanner.next();
            num = getNumber();
        }
        return num;
    }

    public static char getOperation() {
        System.out.print("Введіте операцію: ");
        char operation;
        if (scanner.hasNext()) {
            operation = scanner.next().charAt(0);
        } else {
            System.out.println("Помилка, оберіть будь ласка з доступних операцій: + - * /");
            scanner.next();
            operation = getOperation();
        }
        return operation;
    }

    public static double calc(double num1, double num2, char operation) {
        double result;
        switch (operation) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if(num2==0){
                    System.out.println("Ділення на 0 заборонено!!!");
                    result = 0;
                }
                else {
                    result = num1 / num2;
                }
                break;
            default:
                System.out.println("Помилка, оберіть будь ласка з доступних операцій: + - * /");
                result = calc(num1, num2, getOperation());
        }
        return result;
    }
}
