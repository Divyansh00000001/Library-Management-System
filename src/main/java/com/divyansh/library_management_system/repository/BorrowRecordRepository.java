package com.divyansh.library_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.divyansh.library_management_system.entity.BorrowRecord;

import java.util.Optional;

public interface BorrowRecordRepository
        extends JpaRepository<BorrowRecord, Long> {
	
	Optional<BorrowRecord> findByIdAndUserEmail(
	        Long id,
	        String userEmail);

}