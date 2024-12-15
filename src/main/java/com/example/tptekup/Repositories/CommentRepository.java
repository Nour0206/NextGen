package com.example.tptekup.Repositories;

import com.example.tptekup.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long postId);

    List<Comment> findByClientId(long clientId);
}
