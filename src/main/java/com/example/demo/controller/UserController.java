package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<User> showAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow( () ->
                new RuntimeException("NO USER FOUND WITH ID: " + id)
        );
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserRequest request){
        User updatedUser = userRepository.findById(id).orElseThrow( () ->
                new RuntimeException("NO USER FOUND WITH ID: " + id)
        );

        updatedUser.setUsername(request.getUsername());
        updatedUser.setEmail(request.getEmail());
        updatedUser.setPassword(request.getPassword());

        return updatedUser;
    }

    @PostMapping
    public User insertUser(@RequestBody UserRequest request){

            User newUser = new User();
            newUser.setUsername(request.getUsername());
            newUser.setEmail(request.getEmail());
            newUser.setPassword(request.getPassword());
            newUser.setCreatedAt(LocalDateTime.now());
            return userRepository.save(newUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    public static class UserRequest {

        private String username;

        private String email;

        private String password;

        public String getPassword() {
            return password; }

        public String getEmail() {
            return email; }

        public String getUsername() {
            return username; }

    }


}
