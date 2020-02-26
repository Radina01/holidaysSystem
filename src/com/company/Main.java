package com.company;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.util.Scanner;
import java.util.Vector;

public class Main {
    static String[][] secondOptionTableRows= new String[999][3];
    static int rows = 0;
    static int col = 0;

    static String[] names = new String[999];

    static int index = 0;

    public static void main(String[] args) {
        while(true) {
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
        names[index] = input.next();
        index++;
        System.out.print("Имейл: ");
        String email = input.next();
        System.out.print("ЕГН: ");
        String egn = input.next();
        System.out.print("Вид на отпуската");
        secondOptionTableRows[rows][col] = input.next();
        col++;
        System.out.println("start");
        secondOptionTableRows[rows][col] = input.next();
        col++;
        System.out.println("end");
        secondOptionTableRows[rows][col] = input.next();
        col++;
        if (col>=3){
            col = 0;
            rows++;
        }

    }

    //TODO make the connection between the first and the second option and continue to fill the table with information
    //TODO learn how to make the array endless
    //secondOption
    public static void printTableForYourHolidays() {
        String[] columnNames = {"Заявки", "Начало(дата)", "Край(дата)"};

        new doTable(secondOptionTableRows, columnNames);
    }

    //thirdOption
    public static void printTableForOtherEmployee() {
        String[] columnNames = {"Заявки", "Начало(дата)", "Край(дата)"};
        int request = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("Въведи име на служител: ");
        String nameOfEmployee = input.next();
        String[][] employeeRequests = new String[rows][3];
        for (int i = 0; i < names.length; i++) {
            if (nameOfEmployee.equals(names[i])){
                for (int j = 0; j < 3; j++) {
                    employeeRequests[request][j] = secondOptionTableRows[i][j];
                }
                request++;
            }
        }
        new doTable(employeeRequests, columnNames);
    }

    //TODO forth option

    //fifth option
    public static void exitProgram() {
        System.exit(0);
    }

    //TODO format everything

}

