package com.divyansh.library_management_system.service;

import java.util.List;

import com.divyansh.library_management_system.entity.Book;
import org.springframework.data.domain.Page;

public interface BookService {

    Book addBook(Book book);

    List<Book> getAllBooks();
    
    Book getBookById(Long id);
    
    void deleteBook(Long id);
    
    Book updateBook(Long id, Book book);
    
    String borrowBook(Long bookId, String userEmail);
    
    String returnBook(Long borrowRecordId);
    
    List<Book> searchByTitle(String title);

    List<Book> searchByAuthor(String author);

    List<Book> searchByIsbn(String isbn);
    
    Page<Book> getBooksPaginated(int page, int size);
    
    String returnBook(Long borrowRecordId, String userEmail);
}