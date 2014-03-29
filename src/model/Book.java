package model;

import java.sql.Date;
import java.util.GregorianCalendar;

/**
 * Created by helifab on 28.03.2014.
 */
public class Book {
    private int isbn;
    private String title;
    private String author;
    private int year;
    private String genre;
    private boolean borrowed;
    private Section section;
    private Member borrower;
    private Date date;

    public Book(int isbn, String title, String author, int year, String genre, boolean borrowed, Section sectionnr, Member borrower, Date date) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.borrowed = borrowed;
        this.section = sectionnr;
        this.borrower = borrower;
        this.date = date;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public Member getBorrower() {
        return borrower;
    }

    public void setBorrower(Member borrower) {
        this.borrower = borrower;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (borrowed != book.borrowed) return false;
        if (borrower != book.borrower) return false;
        if (isbn != book.isbn) return false;
        if (section != book.section) return false;
        if (year != book.year) return false;
        if (!author.equals(book.author)) return false;
        if (date != null ? !date.equals(book.date) : book.date != null) return false;
        if (!genre.equals(book.genre)) return false;
        if (!title.equals(book.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = isbn;
        result = 31 * result + title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + year;
        result = 31 * result + genre.hashCode();
        result = 31 * result + (borrowed ? 1 : 0);
        result = 31 * result + (borrower != null ? borrower.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
