package com.company;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("------------------------------------\n" +
                "   1. Заяви отпуска\n" +
                "   2. Виж всички отпуски\n" +
                "   3. Виж отпуска за служител\n" +
                "   4. Промени статус на отпуска\n" +
                "   5. Изход\n" +
                "------------------------------------");

        Scanner input = new Scanner(System.in);
        System.out.print("Choose one option: ");
        byte chosenOption = input.nextByte();
        switch (chosenOption) {
            case 1:
                enterDataAndCheckWhetherItIsTrue();
                break;
            case 2:
                printTableForYourHolidays();
                break;
            case 3:
                printTableForOtherEmployee();
                break;
            case 4:
                System.out.println("");
                break;
            case 5:
                exitProgram();
                break;
            default:
                do {
                    System.out.print("Въведената цифра не отговаря на нито една опция! " +
                            "Моля, въведете пак:");
                    chosenOption = input.nextByte();
                } while (chosenOption > 5 || chosenOption < 1);
        }
    }

    public static class doTable {
        // frame
        JFrame f;
        // Table
        JTable j;

        // Constructor
        doTable(String[][] data, String[] columnNames) {

            // Frame initialization
            f = new JFrame();
            // Frame Title
            f.setTitle("Holiday System");

            // Initializing the JTable
            j = new JTable(data, columnNames);
            j.setBounds(30, 40, 200, 300);

            // adding it to JScrollPane
            JScrollPane sp = new JScrollPane(j);
            f.add(sp);
            // Frame Size
            f.setSize(500, 200);
            // Frame Visible = true
            f.setVisible(true);
        }
    }

    //TODO finish the check of the elements in the first option
    //first Option
    public static void enterDataAndCheckWhetherItIsTrue() {
        Scanner input = new Scanner(System.in);
        System.out.print("Име: ");
        String name = input.next();
        System.out.print("Имейл: ");
        String email = input.next();
        System.out.print("ЕГН: ");
        String egn = input.next();
        System.out.print("");

    }

    //TODO make the connection between the first and the second option and continue to fill the table with information
    //TODO learn how to make the array endless
    //second Option
    public static void printTableForYourHolidays() {
        String[][] secondOptionTableRows = {
                {"1", "zdr", "zdr",},
                {null, null, null, null},
                {null, null, null, null}
        };
        String[] columnNames = {"Заявки", "Начало(дата)", "Край(дата)"};

        System.out.println(new doTable(secondOptionTableRows, columnNames));
    }

    //thirdOption
    public static void printTableForOtherEmployee() {
        String[][] thirdOptionTableRows = {};
        String[] columnNames = {};
    }

    //TODO forth option

    //fifth option
    public static void exitProgram() {
        System.exit(0);
    }

    //TODO format everything

}

