package com.example.digitallibrary.ui.question;

public class questionmodel {

    String bookurl,sem,year;
    public questionmodel() {
    }

    public questionmodel(String bookurl, String sem, String year) {
        this.bookurl = bookurl;
        this.sem = sem;
        this.year = year;
    }

    public String getBookurl() {
        return bookurl;
    }

    public void setBookurl(String bookurl) {
        this.bookurl = bookurl;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
