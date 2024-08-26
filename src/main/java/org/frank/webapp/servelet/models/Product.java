package org.frank.webapp.servelet.models;

import java.time.LocalDate;

public class Product {
    private Long id;
    private String name;
    private Category categoria;
    private int price;
    private String sku;
    private LocalDate registerDate;

    public Product() {
    }

    public Product(Long id, String nombre, Category tipo, int precio) {
        this.id = id;
        this.name = nombre;
        this.categoria = tipo;
        this.price = precio;
    }

    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category catogory) {
        this.categoria = catogory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

}
