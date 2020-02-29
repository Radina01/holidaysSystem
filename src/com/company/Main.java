package com.company;

import javax.swing.JFrame;

import javax.swing.JScrollPane;

import javax.swing.JTable;

import java.io.*;

import java.text.DateFormat;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    static String[][] secondOptionTableRows = new String[999][4];
    static String[] names = new String[999];

    static int rows = 0;
    static int cols = 0;
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

            System.out.print("Избери една опция: ");
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

    public static void saveFile() {
        BufferedWriter outputWriter = null;
        BufferedWriter namesWriter = null;
        try {
            outputWriter = new BufferedWriter(new FileWriter("requestBase.txt"));
            namesWriter = new BufferedWriter(new FileWriter("namesBase.txt"));
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < secondOptionTableRows[i].length; j++) {
                    outputWriter.write(secondOptionTableRows[i][j] + " ");
                }
                namesWriter.write(names[i]);
                namesWriter.newLine();
                outputWriter.newLine();
            }
            namesWriter.flush();
            namesWriter.close();
            outputWriter.flush();
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nextIndex() {
        cols++;
        if (cols >= secondOptionTableRows[0].length) {
            cols = 0;
            rows++;
        }
    }

    public static void getRequests() {
        try {
            File myObj = new File("requestBase.txt");
            File namesObject = new File("namesBase.txt");
            Scanner myNames = new Scanner(namesObject);
            Scanner myRequest = new Scanner(myObj);
            while (myRequest.hasNextLine()) {
                String data = myRequest.nextLine();
                String[] words = data.split("\\s+");
                names[index] = myNames.nextLine();
                index++;
                for (int i = 0; i < words.length; i++) {
                    words[i] = words[i].replaceAll("[^\\w]", "");
                    secondOptionTableRows[rows][cols] = words[i];
                    nextIndex();
                }
            }
            myRequest.close();
            myNames.close();
        } catch (FileNotFoundException e) {
            System.out.println("Има грешка!");
            e.printStackTrace();
        }
    }

    //first Option
    public static void enterDataAndCheckWhetherItIsTrue() {
        System.out.print("Име(само едно име): ");
        names[index] = input.next();
        index++;
        String email = inputAndCheckEmail();
        String egn = inputEgn();
        secondOptionTableRows[rows][cols] = inputAndCheckTypeHoliday();
        nextIndex();
        secondOptionTableRows[rows][cols] = inputAndCheckFormatStartDate();
        nextIndex();
        secondOptionTableRows[rows][cols] = inputAndCheckFormatEndDate();
        nextIndex();
        secondOptionTableRows[rows][cols] = "pending";
        nextIndex();
    }

    public static String inputAndCheckEmail() {
        System.out.print("Имейл: ");
        String email = input.next();
        if (email.contains("@")) {
            return email;
        } else {
            System.out.println("Не е валиден имейл! Въведете отново!: ");
            return inputAndCheckEmail();
        }
    }

    public static String inputEgn() {
        System.out.print("ЕГН: ");
        String egn = input.next();
        char[] egnArray = egn.toCharArray();
        int flag = 0;
        if (egnArray.length == 10) {
            for (int i = 0; i < egnArray.length; i++) {
                if (egnArray[i] == '0'
                        || egnArray[i] == '1'
                        || egnArray[i] == '2'
                        || egnArray[i] == '3'
                        || egnArray[i] == '4'
                        || egnArray[i] == '5'
                        || egnArray[i] == '6'
                        || egnArray[i] == '7'
                        || egnArray[i] == '8'
                        || egnArray[i] == '9'
                )
                    flag++;
            }
        } else {
            System.out.println("Въведено е невалидно ЕГН! Въведете отново!: ");
            return inputEgn();
        }
        if (flag == egnArray.length) {
            return egn;
        } else {
            System.out.println("Въведено е невалидно ЕГН! Въведете отново!: ");
            return inputEgn();
        }
    }

    public static String inputAndCheckTypeHoliday() {
        System.out.print("Вид на отпуската(paid или notpaid): ");
        secondOptionTableRows[rows][cols] = input.next();
        if (secondOptionTableRows[rows][cols].equals("paid") || secondOptionTableRows[rows][cols].equals("notpaid")) {
            return secondOptionTableRows[rows][cols];
        } else {
            System.out.println("Въведена е невалиден вид отпуска! Въведете отново!: ");
            return inputAndCheckTypeHoliday();
        }
    }

    final static String DATE_FORMAT = "dd-MM-yyyy";

    public static String inputAndCheckFormatStartDate() {
        System.out.print("Начало(dd-mm-year): ");
        secondOptionTableRows[rows][cols] = input.next();
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(secondOptionTableRows[rows][cols]);
            return secondOptionTableRows[rows][cols];
        } catch (ParseException e) {
            System.out.println("Въведена е или грешна дата, или в грешен формат! Въведете отново: ");
            return inputAndCheckFormatStartDate();
        }
    }

    public static String inputAndCheckFormatEndDate() {
        System.out.print("Край(dd-mm-year): ");
        secondOptionTableRows[rows][cols] = input.next();
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(secondOptionTableRows[rows][cols]);
            return secondOptionTableRows[rows][cols];
        } catch (ParseException e) {
            System.out.println("Въведена е или грешна дата, или е грешен формат! Въведете отново: ");
            return inputAndCheckFormatEndDate();
        }
    }

    //secondOption
    public static void printTableForYourHolidays() {
        String[] columnNames = {"Заявки", "Начало(дата)", "Край(дата)", "Статус"};
        new doTable(secondOptionTableRows, columnNames);
    }

    //thirdOption
    public static void printTableForOtherEmployee() {
        String[] columnNames = {"Заявки", "Начало(дата)", "Край(дата)", "Статус"};
        int request = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("Въведи име на служител: ");
        String nameOfEmployee = input.next();
        String[][] employeeRequests = new String[rows][secondOptionTableRows[0].length];
        for (int i = 0; i < names.length; i++) {
            if (nameOfEmployee.equals(names[i])) {
                for (int j = 0; j < secondOptionTableRows[i].length; j++) {
                    employeeRequests[request][j] = secondOptionTableRows[i][j];
                }
                request++;
            }
        }
        new doTable(employeeRequests, columnNames);
    }

    //fourthOption
    public static void changeStatusWithSpecialNumber() {

        printTableWithSpecialNumbers();

        int member = inputSpecialNumber();
        String status = inputStatus();
        secondOptionTableRows[member][3] = status;
    }

    public static void printTableWithSpecialNumbers() {
        String[] columnNames = {"Номер", "Заявки", "Начало(дата)", "Край(дата)", "Статус"};
        String[][] specialNumberTable = new String[rows + 1][5];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < specialNumberTable[i].length; j++) {
                if (j == 0) {
                    double specialNumber = Math.pow(10, (specialNumberTable[i].length - 1)) + i;
                    String specialString = (int) specialNumber + "";
                    specialNumberTable[i][j] = specialString + "";
                } else {
                    specialNumberTable[i][j] = secondOptionTableRows[i][j - 1];
                }
            }
        }
        new doTable(specialNumberTable, columnNames);
    }

    public static String inputStatus() {
        System.out.print("Въведи статус на отпуската: approved, rejected or pending");
        String status = input.next();
        if (status.equals("approved") || status.equals("rejected") || status.equals("pending")) {
            return status;
        } else {
            System.out.println("Невалиден статус! Въведи отново!");
            return inputStatus();
        }
    }

    public static int inputSpecialNumber() {
        System.out.print("Въведи специалния номер: ");
        int number = input.nextInt();
        number = number % (int) Math.pow(10, (secondOptionTableRows[0].length));
        if (number < rows) {
            return number;
        } else {
            System.out.println("Няма отпуска с такъв специален номер! Въведи отново! ");
            return inputSpecialNumber();
        }
    }

    //fifth option
    public static void exitProgram() {
        saveFile();
        System.exit(0);
    }
}

