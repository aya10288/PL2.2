/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private String status;

    public Book(String id, String title, String author, String genre, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // تحويل من وإلى CSV string
    public static Book fromCSV(String line) {
        String[] parts = line.split(",");
        if(parts.length < 5) return null;
        return new Book(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }

    public String toCSV() {
        return String.join(",", id, title, author, genre, status);
    }
}

