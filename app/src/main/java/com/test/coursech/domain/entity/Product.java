package com.test.coursech.domain.entity;

public class Product {
    private int id;
    private String name;
    private int preview;

    public Product(int id, String name, int preview){
        this.id=id;
        this.name=name;
        this.preview=preview;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPreview() {
        return this.preview;
    }

    public void setPreview(int preview) {
        this.preview = preview;
    }

}
