package com.example.tptekup.Services;

import com.example.tptekup.Entities.Comment;
import com.example.tptekup.Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment getCommentById(long id) {
        return commentRepository.findById(id).get();
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public void deleteComment(long id) {
        commentRepository.deleteById(id);
    }

    public Comment updateComment(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }

    public List<Comment> getCommentsByPostId(long postId) {
        return commentRepository.findByPostId(postId);
    }

    public List<Comment> getCommentsByClientId(long clientId) {
        return commentRepository.findByClientId(clientId);
    }
}
