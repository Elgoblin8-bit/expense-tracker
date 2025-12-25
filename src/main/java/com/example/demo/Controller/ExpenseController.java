package com.example.demo.Controller;

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
@RequestMapping("/api/expenses")
public class ExpenseController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    // Show all expenses -- these are public because these are what I want people to have access to
    @GetMapping
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }

    @PostMapping
    public Expense createExpense(@RequestBody ExpenseRequest request){

        User user = userRepository.findById(1L).orElseGet( () ->  {
            User newUser = new User();
            newUser.setUsername("testuser");
            newUser.setEmail("test@gmail.com");
            newUser.setPassword("testing");
            newUser.setCreatedAt(LocalDateTime.now());
            return userRepository.save(newUser); });

        Category category = categoryRepository.findById(1L).orElseGet( () -> {
            Category newCategory = new Category();
            newCategory.setName("testRunCategory");
            newCategory.setUser(user);
            return categoryRepository.save(newCategory); });

        Expense newExpense = new Expense();
        newExpense.setAmount(request.getAmount());
        newExpense.setDescription(request.getDescription());
        newExpense.setUser(user);
        newExpense.setCategory(category);
        newExpense.setCreatedAt(LocalDateTime.now());
        return expenseRepository.save(newExpense);
    }

    // This helps keep people from having too much access to my code
    public static class ExpenseRequest {
        private BigDecimal amount;

        private String description;

        public BigDecimal getAmount() {
            return amount; }

        public void setAmount(BigDecimal amount) {
            this.amount = amount; }

        public String getDescription() {
            return description; }

        public void setDescription(String description) {
            this.description = description; }
    }
}

