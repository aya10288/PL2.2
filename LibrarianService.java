/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Book;
import files.FileStorage;
import java.util.*;

public class LibrarianService {
    private FileStorage storage;
    private String booksFile = "books.txt";
    private String checkoutFile = "checkout.txt";
    private String reserveFile = "reservations.txt";

    public LibrarianService(FileStorage storage) {
        this.storage = storage;
    }

    // Checkout Book
    public boolean checkoutBook(String patronID, String bookID) {
        List<String> books = storage.readLines(booksFile);
        for (int i = 0; i < books.size(); i++) {
            Book b = Book.fromCSV(books.get(i));
            if (b != null && b.getId().equals(bookID) && b.getStatus().equalsIgnoreCase("available")) {
                b.setStatus("checked_out");
                books.set(i, b.toCSV());
                storage.writeLines(booksFile, books);
                storage.appendLine(checkoutFile, patronID + "," + bookID);
                return true;
            }
        }
        return false;
    }

    // Return Book
    public boolean returnBook(String patronID, String bookID) {
        List<String> books = storage.readLines(booksFile);
        for (int i = 0; i < books.size(); i++) {
            Book b = Book.fromCSV(books.get(i));
            if (b != null && b.getId().equals(bookID) && b.getStatus().equalsIgnoreCase("checked_out")) {
                b.setStatus("available");
                books.set(i, b.toCSV());
                storage.writeLines(booksFile, books);
                
                // إزالة من checkout.txt
                List<String> checkouts = storage.readLines(checkoutFile);
                checkouts.removeIf(line -> line.equals(patronID + "," + bookID));
                storage.writeLines(checkoutFile, checkouts);
                return true;
            }
        }
        return false;
    }

    // Reserve Book
    public boolean reserveBook(String patronID, String bookID) {
        List<String> books = storage.readLines(booksFile);
        for (Book b : books.stream().map(Book::fromCSV).filter(Objects::nonNull).toList()) {
            if (b.getId().equals(bookID) && !b.getStatus().equalsIgnoreCase("available")) {
                storage.appendLine(reserveFile, patronID + "," + bookID);
                return true;
            }
        }
        return false;
    }
}
