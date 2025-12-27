package com.example.demo.controller;

import java.math.BigDecimal;

public class CategoryController {

    public static class CategoryRequest {
        private String name;

        public void setName(String name){
            this.name = name; }

        public String getName(){
            return name; }
    }


}
