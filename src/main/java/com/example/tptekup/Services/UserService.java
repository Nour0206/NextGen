package com.example.tptekup.Services;

import com.example.tptekup.Entities.User;
import com.example.tptekup.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User addUser(User user){
        return userRepository.save(user);
    }
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User getById(long id){
        return userRepository.findById(id).get();
    }
    public User updateUser(User user){ return  userRepository.saveAndFlush(user);}
    public void DeleteUser(long id){
        userRepository.deleteById(id);
    }

}
