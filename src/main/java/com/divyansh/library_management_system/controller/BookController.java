package com.divyansh.library_management_system.controller;

import java.util.List;

import org.springframework.security.core.Authentication;

import jakarta.validation.Valid;
import com.divyansh.library_management_system.dto.BookRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.divyansh.library_management_system.entity.Book;
import com.divyansh.library_management_system.service.BookService;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book addBook(@Valid @RequestBody BookRequest request) {

        Book book = new Book();

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setQuantity(request.getQuantity());
        book.setAvailableQuantity(
                request.getAvailableQuantity());

        return bookService.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
    
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
    
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {

        bookService.deleteBook(id);

        return "Book deleted successfully";
    }
    
    @PutMapping("/{id}")
    public Book updateBook(
            @PathVariable Long id,
            @RequestBody Book book) {

        return bookService.updateBook(id, book);
    }
    
    @PostMapping("/borrow/{bookId}")
    public String borrowBook(
            @PathVariable Long bookId,
            @RequestParam String userEmail) {

        return bookService.borrowBook(bookId, userEmail);
    }
    
    @PostMapping("/return/{borrowRecordId}")
    public String returnBook(
            @PathVariable Long borrowRecordId,
            Authentication authentication) {

        String userEmail =
                authentication.getName();

        return bookService.returnBook(
                borrowRecordId,
                userEmail);
    }
    
    @GetMapping("/search/title")
    public List<Book> searchByTitle(
            @RequestParam String title) {

        return bookService.searchByTitle(title);
    }
    
    @GetMapping("/search/author")
    public List<Book> searchByAuthor(
            @RequestParam String author) {

        return bookService.searchByAuthor(author);
    }
    
    @GetMapping("/search/isbn")
    public List<Book> searchByIsbn(
            @RequestParam String isbn) {

        return bookService.searchByIsbn(isbn);
    }
    
    @GetMapping("/page")
    public Page<Book> getBooksPaginated(
            @RequestParam int page,
            @RequestParam int size) {

        return bookService
                .getBooksPaginated(page, size);
    }
}