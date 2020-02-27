package com.company;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.io.*;

import java.util.Arrays;
import java.util.Scanner;


public class Main {
    static String[][] secondOptionTableRows = new String[999][4];
    static int rows = 0;
    static int col = 0;

    static String[] names = new String[999];

    static int index = 0;

    public static void main(String[] args) {
        getRequests();

        while (true) {
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
                    changeStatusWithSpecialNumber();
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

    public static void writeFile() {
        BufferedWriter outputWriter = null;
        try {
            outputWriter = new BufferedWriter(new FileWriter("filename.txt"));
            for (int i = 0; i < secondOptionTableRows.length; i++) {
                for (int j = 0; j < secondOptionTableRows[i].length; j++) {

                    outputWriter.write(secondOptionTableRows[i][j] + " ");

                }

                outputWriter.newLine();
            }

            outputWriter.flush();
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void nextIndex() {
        col++;
        if (col >= secondOptionTableRows[0].length) {
            col = 0;
            rows++;
        }
    }

    public static void getRequests() {
        try {
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (!data.equals("null null null ")) {
                    String[] words = data.split("\\s+");
                    for (int i = 0; i < words.length; i++) {
                        words[i] = words[i].replaceAll("[^\\w]", "");
                        secondOptionTableRows[rows][col] = words[i];
                        nextIndex();
                    }
                } else return;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int getNumberLength(int number) {
        int length = 1;
        while (number / 10 != 0) {
            length++;
            number /= 10;
        }
        return length;
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
        nextIndex();
        System.out.println("start");
        secondOptionTableRows[rows][col] = input.next();
        nextIndex();
        System.out.println("end");
        secondOptionTableRows[rows][col] = input.next();
        nextIndex();
        secondOptionTableRows[rows][col] = "pending";
        nextIndex();
    }

    //secondOption
    public static void printTableForYourHolidays() {
        String[] columnNames = {"Заявки", "Начало(дата)", "Край(дата)", "Статус"};

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
            if (nameOfEmployee.equals(names[i])) {
                for (int j = 0; j < 3; j++) {
                    employeeRequests[request][j] = secondOptionTableRows[i][j];
                }
                request++;
            }
        }
        new doTable(employeeRequests, columnNames);
    }

    //TODO forth option
    public static void changeStatusWithSpecialNumber() {
        String[] columnNames = {"Номер", "Заявки", "Начало(дата)", "Край(дата)", "Статус"};
        String[][] specialNumberTable = new String[rows + 1][5];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < specialNumberTable[i].length; j++) {
                if (j == 0) {
                    double specialNumber = Math.pow(10, (specialNumberTable[i].length - 1)) + i;
                    String specialString = (int) specialNumber + "";
                    specialNumberTable[i][j] = specialString + "" + j;

                } else {
                   // System.out.println(secondOptionTableRows[i][j - 1]);
                    if (!secondOptionTableRows[i][j - 1].equals("null "))
                    specialNumberTable[i][j] = secondOptionTableRows[i][j - 1];
                }
            }
        }
        new doTable(specialNumberTable, columnNames);
    }

    //fifth option
    public static void exitProgram() {
        writeFile();
        System.exit(0);
    }

    //TODO format everything

}

