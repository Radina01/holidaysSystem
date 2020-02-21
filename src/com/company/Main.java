package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("------------------------------------\n" +
                            "   1. Заяви отпуска\n" +
                            "   2. Виж всички отпуски\n" +
                            "   3. Виж отпуска за служител\n" +
                            "   4. Промени статус на отпуска\n" +
                           "------------------------------------");

        Scanner input = new Scanner(System.in);
        System.out.print("Choose one option: ");
        byte chosenOption = input.nextByte();

    }
    public static void firstOption (String data){

    }
}
