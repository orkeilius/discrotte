package com.discrotte.backend.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.discrotte.backend.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	Optional<Message> findById(int id);
	
	Optional<Message> findFirstByDateLessThan(Date date);
	
	Optional<Message> findFirstByDateGreaterThan(Date date);
}