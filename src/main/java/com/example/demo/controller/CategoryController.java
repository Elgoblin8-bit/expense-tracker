package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Expense;
import com.example.demo.entity.User;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    // Show a category by just giving "/<id #>"
    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id){
        return categoryRepository.findById(id).orElseThrow( () ->
                new RuntimeException("NO CATEGORY FOUND WITH ID: " + id)
        );
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody CategoryRequest request){
        Category updatedCategory = categoryRepository.findById(id).orElseThrow( () ->
                new RuntimeException("NO CATEGORY FOUND WITH ID: " + id));

        updatedCategory.setName(request.getName());

        return updatedCategory;
    }

    @PostMapping
    public Category insertCategory(@RequestBody CategoryRequest request){

        User user = userRepository.findById(1L).orElseGet( () ->  {
            User newUser = new User();
            newUser.setUsername("testBetaUser");
            newUser.setEmail("tesCategory@gmail.com");
            newUser.setPassword("testing");
            newUser.setCreatedAt(LocalDateTime.now());
            return userRepository.save(newUser); }
        );

            Category newCategory = new Category();
            newCategory.setUser(user);
            newCategory.setName(request.getName());
            return categoryRepository.save(newCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        // if (!){
        // throw new RuntimeException("CANNOT DELETE THIS CATEGORY. THERE IS AN EXPENSE ASSOCIATED.");
        // }
         categoryRepository.deleteById(id);
    }

    public static class CategoryRequest {
        private String name;

        public void setName(String name){
            this.name = name; }

        public String getName(){
            return name; }
    }

}
