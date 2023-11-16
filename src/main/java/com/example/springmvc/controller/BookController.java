package com.example.springmvc.controller;

import com.example.springmvc.model.Book;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final List<Book> books = new ArrayList<>();

    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        book.setId((long) (books.size() + 1));
        books.add(book);
        return book;
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book existingBook = getBookById(id);
        if (existingBook != null) {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
        }
        return existingBook;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}