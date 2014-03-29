package model;

import java.sql.Date;
import java.util.GregorianCalendar;

/**
 * Created by helifab on 28.03.2014.
 */
public class Member {
    private int pnr;
    private String name;
    private String surname;
    private String email;
    private int tlf;
    private Date bdate;
    private int age;
    private Address adress;

    public Member(int pnr, String name, String surname, String email, int tlf, Date bdate, int age, Address adress) {
        this.pnr = pnr;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.tlf = tlf;
        this.bdate = bdate;
        this.age = age;
        this.adress = adress;
    }

    public Member(int pnr, String name, String surname){
        this.pnr = pnr;
        this.name = name;
        this.surname = surname;
    }
    public Member(int pnr){
        this.pnr = pnr;
    }

    public int getPnr() {
        return pnr;
    }

    public void setPnr(int pnr) {
        this.pnr = pnr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTlf() {
        return tlf;
    }

    public void setTlf(int tlf) {
        this.tlf = tlf;
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;

        Member member = (Member) o;

        if (age != member.age) return false;
        if (pnr != member.pnr) return false;
        if (tlf != member.tlf) return false;
        if (!adress.equals(member.adress)) return false;
        if (!bdate.equals(member.bdate)) return false;
        if (!email.equals(member.email)) return false;
        if (!name.equals(member.name)) return false;
        if (!surname.equals(member.surname)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pnr;
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + tlf;
        result = 31 * result + bdate.hashCode();
        result = 31 * result + age;
        result = 31 * result + adress.hashCode();
        return result;
    }
}
