package com.company;

import java.util.Arrays;
import java.util.Calendar;

public class Book implements IPublishingArtifact {
    private int ID;
    private String name;
    private String subtitle;
    private String ISBN;
    private int pageCount;
    private String keywords;
    private int languageID;
    private Calendar createdOn;
    private String[] authors;

    public Book(int ID, String name, String subtitle, String ISBN, int pageCount,
                String keywords, int languageID, Calendar createdOn, String[] authors) {
        this.ID = ID;
        this.name = name;
        this.subtitle = subtitle;
        this.ISBN = ISBN;
        this.pageCount = pageCount;
        this.keywords = keywords;
        this.languageID = languageID;
        this.createdOn = createdOn;
        this.authors = authors;
    }

    public Book() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getLanguageID() {
        return languageID;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
    }

    public Calendar getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Calendar createdOn) {
        this.createdOn = createdOn;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }


    @Override
    public String Publish() {
        String publish;
        String date = "" + createdOn.get(Calendar.DAY_OF_MONTH) + "/" + (createdOn.get(Calendar.MONTH) + 1) +
                "/" + createdOn.get(Calendar.YEAR) + " " + createdOn.get(Calendar.HOUR_OF_DAY) +
                ":" + createdOn.get(Calendar.MINUTE) + ":" + createdOn.get(Calendar.SECOND);

        publish = "<xml>\n    <title>" + name + "</title>\n" +
                "    <subtitle>" + subtitle + "</subtitle>\n" +
                "    <isbn>" + ISBN + "</isbn>\n" +
                "    <pageCount>" + pageCount + "</pageCount>\n" +
                "    <keywords>" + keywords + "</keywords>\n" +
                "    <languageID>" + languageID + "</languageID>\n" +
                "    <createdOn>" + date + "</createdOn>\n" +
                "    <authors>" + Arrays.toString(authors) + "</authors>\n" +
                "</xml>\n";

        return publish;
    }
}
