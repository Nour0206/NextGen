package com.example.tptekup.controllers;

import com.example.tptekup.Entities.Post;
import com.example.tptekup.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    @RequestMapping("/addPost")
    public String addPost(Model model) {
        Post post = new Post();
        model.addAttribute("PostForm", post);
        return "new_post";
    }

    @RequestMapping(value = "/savePost", method = RequestMethod.POST)
    public String savePost(@ModelAttribute("PostForm") Post post) {
        postService.createPost(post);
        return "redirect:/allPosts";
    }

    @RequestMapping("/post/list")
    public String listPosts(Model model) {
        List<Post> listPosts = postService.getAllPosts();
        model.addAttribute("listPosts", listPosts);
        return "list_posts";
    }

    @GetMapping("deletePost/{id}")
    public String deletePost(@PathVariable("id") long id) {
        postService.deletePost(id);
        return "redirect:/allPosts";
    }

    @GetMapping("editPost/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "update_post";
    }

    @PostMapping("updatePost/{id}")
    public String updatePost(@PathVariable("id") long id, Post post) {
        postService.updatePost(post);
        return "redirect:/allPosts";
    }
}
