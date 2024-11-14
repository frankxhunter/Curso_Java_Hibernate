package org.frank.webapp.servelet.models;

import java.util.Objects;

import org.frank.webapp.servelet.models.Entities.Product;

public class ItemCart {
    private int cant ;
    private Product product;

    public ItemCart(int cant, Product product) {
        this.cant = cant;
        this.product = product;
    }

    public ItemCart() {
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getTotal(){
        if(product != null && cant > 0){
            return product.getPrice() * cant;
        }
        else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)return true;
        if(obj == null || !(obj instanceof ItemCart)) return false;

        return Objects.equals((((ItemCart)obj).getProduct()).getId(), product.getId());
    }
    public void increaseCant(int num){
        this.cant += num;
    }
}
