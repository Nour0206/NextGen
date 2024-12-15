package com.example.tptekup.Services;

import com.example.tptekup.Entities.Post;
import com.example.tptekup.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post getPostById(long id) {
        return postRepository.findById(id).get();
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void deletePost(long id) {
        postRepository.deleteById(id);
    }

    public Post updatePost(Post post) {
        return postRepository.saveAndFlush(post);
    }

    public List<Post> getPostsByBrandId(long brandId) {
        return postRepository.findByBrandId(brandId);
    }
}
