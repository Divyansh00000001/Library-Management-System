package com.divyansh.library_management_system.service;

import java.time.LocalDate;

import com.divyansh.library_management_system.exception.ResourceNotFoundException;

import com.divyansh.library_management_system.entity.BorrowRecord;
import com.divyansh.library_management_system.repository.BorrowRecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divyansh.library_management_system.entity.Book;
import com.divyansh.library_management_system.repository.BookRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id " + id));
    }
    
    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
    @Override
    public Book updateBook(Long id, Book book) {

    	Book existingBook = bookRepository
    	        .findById(id)
    	        .orElseThrow(() ->
    	                new ResourceNotFoundException(
    	                        "Book not found with id " + id));

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setQuantity(book.getQuantity());
        existingBook.setAvailableQuantity(book.getAvailableQuantity());

        return bookRepository.save(existingBook);
    }
    
    @Override
    public String borrowBook(Long bookId, String userEmail) {

        Book book = bookRepository.findById(bookId)
                .orElse(null);

        if (book == null) {
            return "Book not found";
        }

        if (book.getAvailableQuantity() <= 0) {
            return "Book not available";
        }

        book.setAvailableQuantity(
                book.getAvailableQuantity() - 1);

        bookRepository.save(book);

        BorrowRecord record = new BorrowRecord();

        record.setUserEmail(userEmail);
        record.setBookTitle(book.getTitle());
        record.setBorrowDate(LocalDate.now());
        record.setReturned(false);

        borrowRecordRepository.save(record);

        return "Book borrowed successfully";
    }
    
    @Override
    public String returnBook(Long borrowRecordId) {

        BorrowRecord record = borrowRecordRepository
                .findById(borrowRecordId)
                .orElse(null);

        if (record == null) {
            return "Borrow record not found";
        }

        if (record.isReturned()) {
            return "Book already returned";
        }

        Book book = bookRepository.findAll()
                .stream()
                .filter(b -> b.getTitle()
                        .equals(record.getBookTitle()))
                .findFirst()
                .orElse(null);

        if (book == null) {
            return "Book not found";
        }

        book.setAvailableQuantity(
                book.getAvailableQuantity() + 1);

        bookRepository.save(book);

        record.setReturned(true);
        record.setReturnDate(LocalDate.now());

        borrowRecordRepository.save(record);

        return "Book returned successfully";
    }
    
    @Override
    public String returnBook(
            Long borrowRecordId,
            String userEmail) {

        BorrowRecord record =
                borrowRecordRepository
                        .findByIdAndUserEmail(
                                borrowRecordId,
                                userEmail)
                        .orElse(null);

        if (record == null) {
            return "Borrow record not found";
        }

        if (record.isReturned()) {
            return "Book already returned";
        }

        Book book = bookRepository.findAll()
                .stream()
                .filter(b -> b.getTitle()
                        .equals(record.getBookTitle()))
                .findFirst()
                .orElse(null);

        if (book == null) {
            return "Book not found";
        }

        book.setAvailableQuantity(
                book.getAvailableQuantity() + 1);

        bookRepository.save(book);

        record.setReturned(true);
        record.setReturnDate(LocalDate.now());

        borrowRecordRepository.save(record);

        return "Book returned successfully";
    }
    
    @Override
    public List<Book> searchByTitle(String title) {
        return bookRepository
                .findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Book> searchByAuthor(String author) {
        return bookRepository
                .findByAuthorContainingIgnoreCase(author);
    }

    @Override
    public List<Book> searchByIsbn(String isbn) {
        return bookRepository
                .findByIsbnContainingIgnoreCase(isbn);
    }
    
    @Override
    public Page<Book> getBooksPaginated(int page, int size) {

        return bookRepository.findAll(
                PageRequest.of(page, size));
    }
}