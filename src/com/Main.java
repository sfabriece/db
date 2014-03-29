package com;

import db.Getter;
import model.Address;
import model.Book;
import model.Member;
import model.Section;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private Connection conn;

    public Main() {
        //get driver
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            System.err.println("could not instantiate: " + e);
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.err.println("illegal access: " + e);
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.err.println("class not found: " + e);
            System.exit(1);
        }

        //get Connection
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/bibliotek?user=root&password=developer");
        } catch (SQLException e) {
            System.err.println("error getting connection: " + e);
            System.exit(1);
        }

        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        System.out.println("Write inn your command: \n");
        //command = scanner.nextLine().trim();
        outer:
        while (scanner.hasNextLine()) {
            command = scanner.nextLine().trim();
            String[] commands = command.split(" ");
            switch (commands[0]) {
                case "quit":
                    break outer;
                case "bookl":
                case "BookL":
                case "Bookl":
                case "bl":
                case "BL":
                case "bookL":
                    System.out.printf("%-5s %-25s %-14s  %-14s %-14s %-14s %-14s %-14s %-14s%n", "Isbn", "Title", "Author", "Year", "genre", "Borrowed", "Section", "Borrower", "ReturnDate");

                    for (Book b : Getter.getBookList(conn)) {
                        if (b != null)
                            System.out.printf("%-5s %-25s %-14s  %-14s %-14s %-14s %-14s %-14s %-14s%n", b.getIsbn(), b.getTitle(), b.getAuthor(), b.getYear(), b.getGenre(),
                                    b.isBorrowed(), b.getSection().getAgeLimit(), b.getBorrower().getPnr(), b.getDate());
                    }
                    System.out.println();
                    break;
                case "bbl":
                case "BookBL":
                case "bookbl":
                case "bookBl":
                case "bookbL":
                case "bookBL":
                    System.out.printf("%-5s %-25s %-14s  %-14s %-14s %-14s %-14s %-14s %-14s%n", "Isbn", "Title", "Author", "Year", "genre", "Borrowed", "Section", "Borrower", "ReturnDate");
                    for (Book b : Getter.getBorrowedList(conn)) {
                        if (b != null)
                            System.out.printf("%-5s %-25s %-14s  %-14s %-14s %-14s %-14s %-14s %-14s%n", b.getIsbn(), b.getTitle(), b.getAuthor(), b.getYear(), b.getGenre(),
                                    b.isBorrowed(), b.getSection().getAgeLimit(), b.getBorrower().getPnr(), b.getDate());
                    }
                    System.out.println();
                    break;
                case "pb":
                case "PB":
                case "Pb":
                case "pB":
                case "personB"://who borrowed
                    if (commands.length < 2) {
                        System.out.println("isbn?");
                    } else {
                        System.out.printf("%-15s %-15s %-15s%n", "PNumber", "FirstName", "SurName");
                        if (commands[1] != null) {
                            Member m = null;
                            if ((m = Getter.whoBorrowed(conn, Integer.valueOf(commands[1]))) != null) {
                                System.out.printf("%-15s %-15s %-15s%n", m.getPnr(), m.getName(), m.getSurname());
                            }
                        }
                    }

                    System.out.println();
                    break;
                case "personO"://who owns
                    break;
                case "ab":
                case "AB":
                case "aB":
                case "Ab":
                case "addbook":
                    System.out.println("Help: isbn title author year genre borrowed sectionnr\n\nEnter book details:\n");
                    String c = scanner.nextLine();
                    String[] cs = c.split(" ");

                    if (cs.length < 7){
                        System.out.println("not enough arguments, continuing...");
                    }else{
                        System.out.printf("%-15s %-15s%n", "Title", "Status");
                        Book b = new Book(Integer.valueOf(cs[0]), cs[1], cs[2], Integer.valueOf(cs[3]), cs[4], Boolean.valueOf(cs[5]), new Section(Integer.valueOf(cs[6])), null, null);
                        if (Getter.addBook(conn, b.getIsbn(), b.getTitle(), b.getAuthor(), b.getYear(), b.getGenre(), b.isBorrowed() ? 1:0, b.getSection().getAgeLimit())) {
                            System.out.printf("%-15s %-15s%n", b.getTitle(), "Added..");
                        } else {
                            System.out.printf("%-15s %-15s%n", b.getTitle(), "Not added..");
                        }
                    }
                    System.out.println();
                    break;
                case "am":
                case "AM":
                case "aM":
                case "Am":
                case "addMember":
                    System.out.println("Help: PersonalNumber FirstName SurName Email TelefonNR Birthdate(yyyy-mm-dd) Age zip street city \n\nEnter Member details:\n");
                    String c1 = scanner.nextLine();
                    String[] cs1 = c1.split(" ");

                    if (cs1.length < 10){
                        System.out.println("not enough arguments, continuing...");
                    }else {
                        System.out.printf("%-15s %-15s%n", "Name", "Status");
                        Member m = new Member(Integer.valueOf(cs1[0]), cs1[1], cs1[2], cs1[3], Integer.valueOf(cs1[4]), Date.valueOf(cs1[5]), Integer.valueOf(cs1[6]), new Address(Integer.valueOf(cs1[7]), cs1[8], cs1[9]));
                        if (Getter.addMember(conn, m.getPnr(), m.getName(), m.getSurname(), m.getEmail(), m.getTlf(), m.getBdate(), m.getAge(), m.getAdress().getZip())){
                            System.out.printf("%-15s %-15s%n", m.getName(), "Added..");
                        }else {
                            System.out.printf("%-15s %-15s%n", m.getName(), "Not added..");
                        }
                    }
                    System.out.println();
                    break;
                case "as":
                case "AS":
                case "aS":
                    System.out.println("Help: Name agelimit capacity\n\nEnter Section details:\n");
                    String c2 = scanner.nextLine();
                    String[] cs2 = c2.split(" ");

                    if (cs2.length < 3){
                        System.out.println("not enough arguments...");
                    }else {
                        System.out.printf("%-15s %-15s%n", "Name", "Status");
                        Section s = new Section(Integer.valueOf(cs2[1]), cs2[0], Integer.valueOf(cs2[2]));
                        if (Getter.addSection(conn, s.getAgeLimit(), s.getName(), s.getCapacity())){
                            System.out.printf("%-15s %-15s%n", s.getName(), "Added..");
                        }else {
                            System.out.printf("%-15s %-15s%n", s.getName(), "Not added..");
                        }
                    }
                    System.out.println();
                    break;
                case "sl":
                case "sL":
                case "Sl":
                case "SL":
                    System.out.printf("%-15s %-15s %-15s%n", "Name", "AgeLimit", "Capacity");
                    for (Section s : Getter.SectionList(conn)){
                        System.out.printf("%-15s %-15s %-15s%n", s.getName(), s.getAgeLimit(), s.getCapacity());
                    }
                    System.out.println();
                    break;
                case "setOwner"://set the owner of a book
                    break;
                default:
                    break;
            }
            System.out.println("new command: ");
           // command = scanner.nextLine();
        }

        System.out.println("existed while loop");
        close();
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error closing connection");
        }
    }

    public static void main(String[] args) {
        new Main();
        //System.out.printf("%-14s %-14s %-14s  %-14s %-14s %-14s %-14s %-14s %-14s%n", "Isbn", "Title", "Author", "Year", "genre", "Borrowed","Section", "Borrower", "ReturnDate");
    }
}
