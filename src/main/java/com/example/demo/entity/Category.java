package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "category")
    List<Expense> expenses;

    public String getName() {
        return name; }

    public void setName(String name) {
        this.name = name; }

    public Long getId() {
        return id; }

    public User getUser() {
        return user; }

    public void setUser(User user) {
        this.user = user; }
}
