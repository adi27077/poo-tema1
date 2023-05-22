package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

public class Administration {
    static HashMap<Integer, Book> bookList = new HashMap<>();
    static HashMap<Integer, Language> languageList = new HashMap<>();
    static HashMap<Integer, Author> authorList = new HashMap<>();
    static HashMap<Integer, EditorialGroup> editorialGroupList = new HashMap<>();
    static HashMap<Integer, PublishingBrand> publishingBrandList = new HashMap<>();
    static HashMap<Integer, PublishingRetailer> publishingRetailerList = new HashMap<>();
    static HashMap<Integer, Countries> countriesList = new HashMap<>();

    public static void init() {

        //citire carti
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/company/books.in"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");
                if (tokens[0].equals("ID"))
                    continue;
                int ID = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                String subtitle = tokens[2];
                String ISBN = tokens[3];
                int pageCount = Integer.parseInt(tokens[4]);
                String keywords = tokens[5];
                int languageID = Integer.parseInt(tokens[6]);
                String created = tokens[7];

                //creare data carte
                String[] dateTokens = created.split("[:.\\s]");
                Calendar date = Calendar.getInstance();

                date.set(Integer.parseInt(dateTokens[2]), Integer.parseInt(dateTokens[1]) - 1,
                        Integer.parseInt(dateTokens[0]), Integer.parseInt(dateTokens[3]),
                        Integer.parseInt(dateTokens[4]), Integer.parseInt(dateTokens[5]));

                Book book = new Book(ID, name, subtitle, ISBN, pageCount, keywords, languageID, date, null);

                bookList.put(ID, book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //citire limbi
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/company/languages.in"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");
                if (tokens[0].equals("Id"))
                    continue;
                int ID = Integer.parseInt(tokens[0]);
                String code = tokens[1];
                String name = tokens[2];

                Language language = new Language(ID, code, name);

                languageList.put(ID, language);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //citire autori
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/company/authors.in"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");
                if (tokens[0].equals("Id"))
                    continue;
                int ID = Integer.parseInt(tokens[0]);
                String firstName = tokens[1];
                String lastName = tokens[2];

                Author author = new Author(ID, firstName, lastName);

                authorList.put(ID, author);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creare legaturi intre carte si autori
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/company/books-authors.in"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");
                if (tokens[0].equals("BookId"))
                    continue;
                int bookID = Integer.parseInt(tokens[0]);
                int authorID = Integer.parseInt(tokens[1]);

                Book book = bookList.get(bookID);
                Author author = authorList.get(authorID);
                String authorName = author.getFirstName() + " " + author.getLastName();
                if( book.getAuthors() == null) {
                    String[] bookAuthors = new String[1];
                    bookAuthors[0] = authorName;
                    book.setAuthors(bookAuthors);
                } else {
                    String[] bookAuthors = book.getAuthors();
                    String[] newAuthors = Arrays.copyOf(bookAuthors, bookAuthors.length + 1);
                    newAuthors[newAuthors.length - 1] = authorName;
                    book.setAuthors(newAuthors);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //citire editorial groups
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/company/editorial-groups.in"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");
                if (tokens[0].equals("Id"))
                    continue;
                int ID = Integer.parseInt(tokens[0]);
                String name = tokens[1];

                EditorialGroup group = new EditorialGroup(ID, name, null);

                editorialGroupList.put(ID, group);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //citire publishing brands
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/company/publishing-brands.in"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");
                if (tokens[0].equals("Id"))
                    continue;
                int ID = Integer.parseInt(tokens[0]);
                String name = tokens[1];

                PublishingBrand brand = new PublishingBrand(ID, name, null);

                publishingBrandList.put(ID, brand);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creare legaturi intre editorial group si carti
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/company/editorial-groups-books.in"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");
                if (tokens[0].equals("GroupId"))
                    continue;
                int groupID = Integer.parseInt(tokens[0]);
                int bookID = Integer.parseInt(tokens[1]);

                EditorialGroup group = editorialGroupList.get(groupID);
                Book book = bookList.get(bookID);
                if( group.getBooks() == null) {
                    Book[] books = new Book[1];
                    books[0] = book;
                    group.setBooks(books);
                } else {
                    Book[] books = group.getBooks();
                    Book[] newBooks = Arrays.copyOf(books, books.length + 1);
                    newBooks[newBooks.length - 1] = book;
                    group.setBooks(newBooks);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creare legaturi intre publishing brand si carti
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/company/publishing-brands-books.in"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");
                if (tokens[0].equals("PublisherId"))
                    continue;
                int publisherID = Integer.parseInt(tokens[0]);
                int bookID = Integer.parseInt(tokens[1]);

                PublishingBrand publisher = publishingBrandList.get(publisherID);
                Book book = bookList.get(bookID);
                if( publisher.getBooks() == null) {
                    Book[] books = new Book[1];
                    books[0] = book;
                    publisher.setBooks(books);
                } else {
                    Book[] books = publisher.getBooks();
                    Book[] newBooks = Arrays.copyOf(books, books.length + 1);
                    newBooks[newBooks.length - 1] = book;
                    publisher.setBooks(newBooks);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //citire publishing retailers
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/company/publishing-retailers.in"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");
                if (tokens[0].equals("Id"))
                    continue;
                int ID = Integer.parseInt(tokens[0]);
                String name = tokens[1];

                PublishingRetailer retailer = new PublishingRetailer(ID, name, null, null);

                publishingRetailerList.put(ID, retailer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //citire tari
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/company/countries.in"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");
                if (tokens[0].equals("ID"))
                    continue;
                int ID = Integer.parseInt(tokens[0]);
                String code = tokens[1];

                Countries country = new Countries(ID, code);

                countriesList.put(ID, country);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creare legaturi intre publishing retailer si carti
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/company/publishing-retailers-books.in"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");
                if (tokens[0].equals("RetailerId"))
                    continue;
                int retailerID = Integer.parseInt(tokens[0]);
                int bookID = Integer.parseInt(tokens[1]);

                PublishingRetailer retailer = publishingRetailerList.get(retailerID);
                Book book = bookList.get(bookID);
                if( retailer.getPublishingArtifacts() == null) {
                    IPublishingArtifact[] artifactBooks = new IPublishingArtifact[1];
                    artifactBooks[0] = book;
                    retailer.setPublishingArtifacts(artifactBooks);
                } else {
                    IPublishingArtifact[] artifactBooks = retailer.getPublishingArtifacts();
                    IPublishingArtifact[] newArtifactBooks = Arrays.copyOf(artifactBooks, artifactBooks.length + 1);
                    newArtifactBooks[newArtifactBooks.length - 1] = book;
                    retailer.setPublishingArtifacts(newArtifactBooks);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creare legaturi intre publishing retailer si editorial groups
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/company/publishing-retailers-editorial-groups.in"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");
                if (tokens[0].equals("RetailerId"))
                    continue;
                int retailerID = Integer.parseInt(tokens[0]);
                int groupID = Integer.parseInt(tokens[1]);

                PublishingRetailer retailer = publishingRetailerList.get(retailerID);
                EditorialGroup group = editorialGroupList.get(groupID);
                if( retailer.getPublishingArtifacts() == null) {
                    IPublishingArtifact[] artifactGroups = new IPublishingArtifact[1];
                    artifactGroups[0] = group;
                    retailer.setPublishingArtifacts(artifactGroups);
                } else {
                    IPublishingArtifact[] artifactGroups = retailer.getPublishingArtifacts();
                    IPublishingArtifact[] newArtifactGroups = Arrays.copyOf(artifactGroups, artifactGroups.length + 1);
                    newArtifactGroups[newArtifactGroups.length - 1] = group;
                    retailer.setPublishingArtifacts(newArtifactGroups);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creare legaturi intre publishing retailer si publishing brands
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/company/publishing-retailers-publishing-brands.in"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");
                if (tokens[0].equals("RetailerId"))
                    continue;
                int retailerID = Integer.parseInt(tokens[0]);
                int publisherID = Integer.parseInt(tokens[1]);

                PublishingRetailer retailer = publishingRetailerList.get(retailerID);
                PublishingBrand brand = publishingBrandList.get(publisherID);
                if( retailer.getPublishingArtifacts() == null) {
                    IPublishingArtifact[] artifactBrands = new IPublishingArtifact[1];
                    artifactBrands[0] = brand;
                    retailer.setPublishingArtifacts(artifactBrands);
                } else {
                    IPublishingArtifact[] artifactBrands = retailer.getPublishingArtifacts();
                    IPublishingArtifact[] newArtifactBrands = Arrays.copyOf(artifactBrands, artifactBrands.length + 1);
                    newArtifactBrands[newArtifactBrands.length - 1] = brand;
                    retailer.setPublishingArtifacts(newArtifactBrands);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creare legaturi intre publishing retailer si tari
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/company/publishing-retailers-countries.in"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("###");
                if (tokens[0].equals("RetailerId"))
                    continue;
                int retailerID = Integer.parseInt(tokens[0]);
                int countryID = Integer.parseInt(tokens[1]);

                PublishingRetailer retailer = publishingRetailerList.get(retailerID);
                Countries country = countriesList.get(countryID);
                if( retailer.getCountries() == null) {
                    Countries[] countries = new Countries[1];
                    countries[0] = country;
                    retailer.setCountries(countries);
                } else {
                    Countries[] countries = retailer.getCountries();
                    Countries[] newCountries = Arrays.copyOf(countries, countries.length + 1);
                    newCountries[newCountries.length - 1] = country;
                    retailer.setCountries(newCountries);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Integer, Book> getBooksForPublishingRetailerID(int publishingRetailerID) {
        HashMap<Integer, Book> books = new HashMap<>();

        IPublishingArtifact[] artifacts = publishingRetailerList.get(publishingRetailerID).getPublishingArtifacts();

        for(IPublishingArtifact artifact : artifacts) {
            if (artifact instanceof Book) {
                books.put(((Book) artifact).getID(), (Book)artifact);
            } else if (artifact instanceof EditorialGroup) {
                Book[] groupBooks = ((EditorialGroup) artifact).getBooks();
                for(Book book : groupBooks) {
                    books.put(book.getID(), book);
                }
            } else if (artifact instanceof PublishingBrand) {
                Book[] brandBooks = ((PublishingBrand) artifact).getBooks();
                for(Book book : brandBooks) {
                    books.put(book.getID(), book);
                }
            }
        }

        return books;
    }

    public static HashMap<Integer, Language> getLanguagesForPublishingRetailerID (int publishingRetailerID) {
        HashMap<Integer, Language> languages = new HashMap<>();

        IPublishingArtifact[] artifacts = publishingRetailerList.get(publishingRetailerID).getPublishingArtifacts();

        for(IPublishingArtifact artifact : artifacts) {
            if (artifact instanceof Book) {
                languages.put(((Book) artifact).getLanguageID(), languageList.get(((Book) artifact).getLanguageID()));
            } else if (artifact instanceof EditorialGroup) {
                Book[] groupBooks = ((EditorialGroup) artifact).getBooks();
                for(Book book : groupBooks) {
                    languages.put(book.getLanguageID(), languageList.get(book.getLanguageID()));
                }
            } else if (artifact instanceof PublishingBrand) {
                Book[] brandBooks = ((PublishingBrand) artifact).getBooks();
                for(Book book : brandBooks) {
                    languages.put(book.getLanguageID(), languageList.get(book.getLanguageID()));
                }
            }
        }

        return languages;
    }

    public static HashMap<Integer, Countries> getCountriesForBookID (int bookID) {
        HashMap<Integer, Countries> countries = new HashMap<>();

        for (PublishingRetailer retailer : publishingRetailerList.values()) {
            IPublishingArtifact[] artifacts = retailer.getPublishingArtifacts();
            for (IPublishingArtifact artifact : artifacts) {
                if (artifact instanceof Book) {
                    if (bookID == ((Book) artifact).getID()) {
                        Countries[] retailerCountries = retailer.getCountries();
                        for (Countries country : retailerCountries) {
                            countries.put(country.getID(), country);
                        }
                        break;
                    }
                } else if (artifact instanceof EditorialGroup) {
                    Book[] groupBooks = ((EditorialGroup) artifact).getBooks();
                    boolean bookFoundInThisRetailer = false;
                    for (Book book : groupBooks) {
                        if (bookID == book.getID()) {
                            Countries[] retailerCountries = retailer.getCountries();
                            for (Countries country : retailerCountries) {
                                countries.put(country.getID(), country);
                            }
                            bookFoundInThisRetailer = true;
                            break;
                        }
                    }
                    if (bookFoundInThisRetailer)
                        break;
                } else if (artifact instanceof PublishingBrand) {
                    Book[] brandBooks = ((PublishingBrand) artifact).getBooks();
                    boolean bookFoundInThisRetailer = false;
                    for (Book book : brandBooks) {
                        if (bookID == book.getID()) {
                            Countries[] retailerCountries = retailer.getCountries();
                            for (Countries country : retailerCountries) {
                                countries.put(country.getID(), country);
                            }
                            bookFoundInThisRetailer = true;
                            break;
                        }
                    }
                    if (bookFoundInThisRetailer)
                        break;
                }
            }
        }

        return countries;
    }

    public static HashMap<Integer, Book> getCommonBooksForRetailerIDs (int retailerID1, int retailerID2) {
        HashMap<Integer, Book> common = new HashMap<>();

        HashMap<Integer, Book> books1 = getBooksForPublishingRetailerID(retailerID1);
        HashMap<Integer, Book> books2 = getBooksForPublishingRetailerID(retailerID2);

        for(int key : books1.keySet()) {
            if(books1.containsKey(key) && books2.containsKey(key)) {
                common.put(key, books1.get(key));
            }
        }

        return common;
    }

    public static HashMap<Integer, Book> getAllBooksForRetailerIDs (int retailerID1, int retailerID2) {
        HashMap<Integer, Book> books1 = getBooksForPublishingRetailerID(retailerID1);
        HashMap<Integer, Book> books2 = getBooksForPublishingRetailerID(retailerID2);

        books1.putAll(books2);

        return books1;
    }
}
