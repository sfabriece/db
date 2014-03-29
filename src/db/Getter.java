package db;

import model.Address;
import model.Book;
import model.Member;
import model.Section;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by helifab on 28.03.2014.
 */
public class Getter {
    private static Statement stmt;
    private static ResultSet rs;

    public static ArrayList<Book> getBookList(Connection conn){
        ArrayList<Book> list = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM book");

            rs.beforeFirst();
            while (rs.next()){
                list.add(getBook(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        }
        finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    //ignore
                }
            }

            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                   //ignore
                }
            }
        }

        return list;
    }

    private static Book getBook(ResultSet rs) {
        try {
            Book b = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                    rs.getBoolean(6), new Section(rs.getInt(7), "", 0), new Member(rs.getInt(8)), rs.getDate(9));
            return b;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Book> getBorrowedList(Connection conn){
        ArrayList<Book> list = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM book where borrowed=1");

            rs.beforeFirst();
            while (rs.next()){
                list.add(getBook(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return list;
        }
        finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }

        return list;
    }

    public static Member whoBorrowed(Connection conn, int isbn){
        Member member = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT pnr, name, surname FROM member, book where book.borrower=member.pnr and book.borrowed=1 and isbn=" + isbn);

            rs.beforeFirst();
            if (rs.next()){
                member = new Member(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return member;
        }
        finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }

        return member;
    }

    /*public static Member whoOwns(Connection conn, String bookTitle){
        //TODO
    }*/

    public static boolean addBook(Connection conn, int isbn, String title, String author, int year, String genre, int borrowed, int sectionNr){
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO book (isbn, title, author, year, genre, borrowed, sectionnr) values (" +
                    "'" + isbn + "','" + title + "','" + author + "','" + year + "','" + genre + "','" + borrowed + "','" + sectionNr +"')");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }
        return true;
    }

    /*public static boolean addborrowerToBook(Connection conn, int isbn, Date returnDate){
        //TODO
    }*/

    public static boolean addMember(Connection conn, int pnr, String fName, String lName, String email, int tlf, Date bDay, int age, int adress){
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO member (pnr, name, surname, email, ttlf, birthdate, age, address) values (" +
                "'" + pnr + "','"+ fName + "','" + lName + "','"+ email + "','" + tlf + "','"+ bDay + "','" + age + "','"+ adress + "')");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }
        return true;
    }

    public static boolean addSection(Connection conn, int ageLimit, String name, int capacity){
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO section (agelimit, name, capacity) values (" +
                "'" + ageLimit + "','" + name + "','" + capacity + "')");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }
        return true;
    }

    public static ArrayList<Section> SectionList(Connection conn){
        ArrayList<Section> list = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM section");

            rs.beforeFirst();
            while (rs.next()){
                list.add(getSection(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static Section getSection(ResultSet rs) {
        try {
            return new Section(rs.getInt(1), rs.getString(2), rs.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
