package com.example.tptekup.controllers;

import com.example.tptekup.Entities.Comment;
import com.example.tptekup.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping("/addComment")
    public String addComment(Model model) {
        Comment comment = new Comment();
        model.addAttribute("CommentForm", comment);
        return "new_comment";
    }

    @RequestMapping(value = "/saveComment", method = RequestMethod.POST)
    public String saveComment(@ModelAttribute("CommentForm") Comment comment) {
        commentService.createComment(comment);
        return "redirect:/allComments";
    }

    @RequestMapping("/allComments")
    public String listComments(Model model) {
        List<Comment> listComments = commentService.getAllComments();
        model.addAttribute("listComments", listComments);
        return "list_comments";
    }

    @GetMapping("deleteComment/{id}")
    public String deleteComment(@PathVariable("id") long id) {
        commentService.deleteComment(id);
        return "redirect:/allComments";
    }

    @GetMapping("editComment/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment", comment);
        return "update_comment";
    }

    @PostMapping("updateComment/{id}")
    public String updateComment(@PathVariable("id") long id, Comment comment) {
        commentService.updateComment(comment);
        return "redirect:/allComments";
    }
}
