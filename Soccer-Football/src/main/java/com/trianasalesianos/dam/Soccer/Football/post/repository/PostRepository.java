package com.trianasalesianos.dam.Soccer.Football.post.repository;

import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostRepository extends JpaRepository<Post, Long> , JpaSpecificationExecutor<Post> {
}
