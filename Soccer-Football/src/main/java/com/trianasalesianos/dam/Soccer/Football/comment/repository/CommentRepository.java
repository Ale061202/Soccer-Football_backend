package com.trianasalesianos.dam.Soccer.Football.comment.repository;

import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
