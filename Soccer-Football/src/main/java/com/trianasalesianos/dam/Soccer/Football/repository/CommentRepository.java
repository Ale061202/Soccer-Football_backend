package com.trianasalesianos.dam.Soccer.Football.repository;

import com.trianasalesianos.dam.Soccer.Football.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
