package com.example.demo.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Long getId() {
        return id; }

    public LocalDateTime getCreatedAt() {
        return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt; }

    public BigDecimal getAmount() {
        return amount; }

    public void setAmount(BigDecimal amount) {
        this.amount = amount; }


    public String getDescription() {
        return description; }

    public void setDescription(String description) {
        this.description = description; }

    public User getUser() {
        return user; }

    public void setUser(User user) {
        this.user = user; }

    public Category getCategory() {
        return category; }

    public void setCategory(Category category) {
        this.category = category; }
}
