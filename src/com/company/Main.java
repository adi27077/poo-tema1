package com.company;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Administration.init();

        //Test Publish pe carti
        String test = Administration.bookList.get(204).Publish();
        System.out.println(test);
        test = Administration.bookList.get(224).Publish();
        System.out.println(test);
        test = Administration.bookList.get(262).Publish();
        System.out.println(test);

        //Test Publish pe un editorial group
        test = Administration.editorialGroupList.get(30).Publish();
        System.out.println(test);

        //Test Publish pe un publishing brand
        test = Administration.publishingBrandList.get(69).Publish();
        System.out.println(test);

        //Teste metoda getBooksForPublishingRetailerID
        System.out.println("Cartile publicate de retailer cu ID 33:\n");
        HashMap<Integer, Book> allBooks = Administration.getBooksForPublishingRetailerID(33);
        for(Book book : allBooks.values())
            System.out.println(book.Publish());

        System.out.println("Cartile publicate de retailer cu ID 1:\n");
        allBooks = Administration.getBooksForPublishingRetailerID(1);
        for(Book book : allBooks.values())
            System.out.println(book.Publish());

        System.out.println("Cartile publicate de retailer cu ID 7:\n");
        allBooks = Administration.getBooksForPublishingRetailerID(7);
        for(Book book : allBooks.values())
            System.out.println(book.Publish());

        System.out.println("Cartile publicate de retailer cu ID 16:\n");
        allBooks = Administration.getBooksForPublishingRetailerID(16);
        for(Book book : allBooks.values())
            System.out.println(book.Publish());

        System.out.println("Cartile publicate de retailer cu ID 22:\n");
        allBooks = Administration.getBooksForPublishingRetailerID(2);
        for(Book book : allBooks.values())
            System.out.println(book.Publish());

        //Teste metoda getLanguagesForPublishingRetailerID
        System.out.println("Limbile in care sunt plicate cartile retailerului cu ID 2:\n");
        HashMap<Integer, Language> languages = Administration.getLanguagesForPublishingRetailerID(2);
        for(Language language : languages.values())
            System.out.println(language.getCode() + " " + language.getName());

        System.out.println("Limbile in care sunt plicate cartile retailerului cu ID 5:\n");
        languages = Administration.getLanguagesForPublishingRetailerID(5);
        for(Language language : languages.values())
            System.out.println(language.getCode() + " " + language.getName());

        System.out.println("Limbile in care sunt plicate cartile retailerului cu ID 12:\n");
        languages = Administration.getLanguagesForPublishingRetailerID(12);
        for(Language language : languages.values())
            System.out.println(language.getCode() + " " + language.getName());

        System.out.println("Limbile in care sunt plicate cartile retailerului cu ID 21:\n");
        languages = Administration.getLanguagesForPublishingRetailerID(21);
        for(Language language : languages.values())
            System.out.println(language.getCode() + " " + language.getName());

        System.out.println("Limbile in care sunt plicate cartile retailerului cu ID 22:\n");
        languages = Administration.getLanguagesForPublishingRetailerID(22);
        for(Language language : languages.values())
            System.out.println(language.getCode() + " " + language.getName());

        //Teste metoda getCountriesForBookID
        System.out.println("Tarile in care este publicata cartea cu ID 770:\n");
        HashMap<Integer, Countries> countries = Administration.getCountriesForBookID(770);
        for(Countries country : countries.values())
            System.out.println(country.getCountryCode());

        System.out.println("Tarile in care este publicata cartea cu ID 695:\n");
        countries = Administration.getCountriesForBookID(695);
        for(Countries country : countries.values())
            System.out.println(country.getCountryCode());

        System.out.println("Tarile in care este publicata cartea cu ID 14418:\n");
        countries = Administration.getCountriesForBookID(14418);
        for(Countries country : countries.values())
            System.out.println(country.getCountryCode());

        System.out.println("Tarile in care este publicata cartea cu ID 9960:\n");
        countries = Administration.getCountriesForBookID(9960);
        for(Countries country : countries.values())
            System.out.println(country.getCountryCode());

        System.out.println("Tarile in care este publicata cartea cu ID 6942:\n");
        countries = Administration.getCountriesForBookID(6942);
        for(Countries country : countries.values())
            System.out.println(country.getCountryCode());

        //Teste metoda getCommonBooksForRetailerIDs
        System.out.println("Cartile comune ale retailerilor 1 si 2:\n");
        HashMap<Integer, Book> commonBooks = Administration.getCommonBooksForRetailerIDs(1, 2);
        if (!commonBooks.isEmpty()) {
            for (Book book : commonBooks.values())
                System.out.println(book.Publish());
        }
        System.out.println("Cartile comune ale retailerilor 10 si 20:\n");
        commonBooks = Administration.getCommonBooksForRetailerIDs(10, 20);
        if (!commonBooks.isEmpty()) {
            for (Book book : commonBooks.values())
                System.out.println(book.Publish());
        }

        System.out.println("Cartile comune ale retailerilor 6 si 9:\n");
        if (!commonBooks.isEmpty()) {
            for (Book book : commonBooks.values())
                System.out.println(book.Publish());
        }

        System.out.println("Cartile comune ale retailerilor 13 si 16:\n");
        if (!commonBooks.isEmpty()) {
            for (Book book : commonBooks.values())
                System.out.println(book.Publish());
        }

        System.out.println("Cartile comune ale retailerilor 20 si 30:\n");
        if (!commonBooks.isEmpty()) {
            for (Book book : commonBooks.values())
                System.out.println(book.Publish());
        }

        //Teste metoda getAllBooksForRetailerIDs
        System.out.println("Cartile retailerilor 1 si 2:\n");
        HashMap<Integer, Book> allBooks2 = Administration.getAllBooksForRetailerIDs(1, 2);
        for (Book book : allBooks2.values())
            System.out.println(book.Publish());

        System.out.println("Cartile retailerilor 10 si 20:\n");
        allBooks2 = Administration.getAllBooksForRetailerIDs(10, 20);
        for (Book book : allBooks2.values())
            System.out.println(book.Publish());

        System.out.println("Cartile retailerilor 6 si 9:\n");
        allBooks2 = Administration.getAllBooksForRetailerIDs(6, 9);
        for (Book book : allBooks2.values())
            System.out.println(book.Publish());

        System.out.println("Cartile retailerilor 13 si 16:\n");
        allBooks2 = Administration.getAllBooksForRetailerIDs(13, 16);
        for (Book book : allBooks2.values())
            System.out.println(book.Publish());

        System.out.println("Cartile retailerilor 20 si 30:\n");
        allBooks2 = Administration.getAllBooksForRetailerIDs(20, 30);
        for (Book book : allBooks2.values())
            System.out.println(book.Publish());

    }
}
