package com.company;

import java.util.Arrays;
import java.util.Calendar;

public class PublishingBrand implements IPublishingArtifact {
    private int ID;
    private String name;
    private Book[] books;

    public PublishingBrand(int ID, String name, Book[] books) {
        this.ID = ID;
        this.name = name;
        this.books = books;
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

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    @Override
    public String Publish() {
        String publish, date;

        publish = "<xml>\n    <publishingBrand>\n        <ID>" + ID + "</ID>\n" +
                "        <Name>" + name + "</Name>\n" +
                "    </publishingBrand>\n    <books>\n";

        for (Book book : books) {
            Calendar createdOn = book.getCreatedOn();

            date = "" + createdOn.get(Calendar.DAY_OF_MONTH) + "/" + (createdOn.get(Calendar.MONTH) + 1) +
                    "/" + createdOn.get(Calendar.YEAR) + " " + createdOn.get(Calendar.HOUR_OF_DAY) +
                    ":" + createdOn.get(Calendar.MINUTE) + ":" + createdOn.get(Calendar.SECOND);

            publish += "        <book>\n            <title>" + book.getName() + "</title>\n" +
                    "            <subtitle>" + book.getSubtitle() + "</subtitle>\n" +
                    "            <isbn>" + book.getISBN() + "</isbn>\n" +
                    "            <pageCount>" + book.getPageCount() + "</pageCount>\n" +
                    "            <keywords>" + book.getKeywords() + "</keywords>\n" +
                    "            <languageID>" + book.getLanguageID() + "</languageID>\n" +
                    "            <createdOn>" + date + "</createdOn>\n" +
                    "            <authors>" + Arrays.toString(book.getAuthors()) + "</authors>\n" +
                    "        </book>\n";
        }

        publish += "    </books>\n<xml>\n";

        return publish;
    }
}
