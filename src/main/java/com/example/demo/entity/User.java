package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    // the primary key with autogeneration from JPA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    // without this column JPA will change the name by default :(
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<Expense> expenses;

    @OneToMany(mappedBy = "user")
    private List<Category> categories;

    // Boiler plate code below this point
    public Long getId() {
        return id; }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt; }

    public LocalDateTime getCreatedAt() {
        return createdAt; }

    public String getPassword() {
        return password; }

    public void setPassword(String password) {
        this.password = password; }

    public String getEmail() {
        return email; }

    public void setEmail(String email) {
        this.email = email; }

    public String getUsername() {
        return username; }

    public void setUsername(String username) {
        this.username = username; }
}
