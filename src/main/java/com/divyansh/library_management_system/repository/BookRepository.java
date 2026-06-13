package com.divyansh.library_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.divyansh.library_management_system.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
	
	List<Book> findByTitleContainingIgnoreCase(String title);

	List<Book> findByAuthorContainingIgnoreCase(String author);

	List<Book> findByIsbnContainingIgnoreCase(String isbn);
	
	Page<Book> findAll(Pageable pageable);
	
	long countByAvailableQuantityGreaterThan(int quantity);

	long countByAvailableQuantityLessThan(int quantity);

}