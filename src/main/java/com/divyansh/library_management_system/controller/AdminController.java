package com.divyansh.library_management_system.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divyansh.library_management_system.repository.BookRepository;
import com.divyansh.library_management_system.repository.UserRepository;

@RestController
@RequestMapping("/api/admin/stats")
public class AdminController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Map<String, Long> getStats() {

        Map<String, Long> stats = new HashMap<>();

        stats.put(
                "totalBooks",
                bookRepository.count());

        stats.put(
                "availableBooks",
                bookRepository
                        .countByAvailableQuantityGreaterThan(0));

        stats.put(
                "borrowedBooks",
                bookRepository
                        .countByAvailableQuantityLessThan(1));

        stats.put(
                "totalUsers",
                userRepository.count());

        return stats;
    }
}