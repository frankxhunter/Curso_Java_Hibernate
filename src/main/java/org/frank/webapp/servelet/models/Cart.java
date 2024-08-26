package org.frank.webapp.servelet.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;


@org.frank.webapp.servelet.configs.Cart
public class Cart implements Serializable{
    private List<ItemCart> items;

    @Inject
    private transient Logger log;

    public Cart() {
    }

    public List<ItemCart> getItems() {
        return items;
    }
    public void addItem(ItemCart item){
        if (items.contains(item)){
            items.stream().filter(i -> i.equals(item)).findFirst().get().increaseCant(1);
        }
        else{
            items.add(item);
        }
    }

    public int getTotal(){
        return items.stream().mapToInt(i-> i.getTotal()).sum();
    }

    @PostConstruct
    public void init(){
        items = new ArrayList<>();
        log.info("Inicializando carro de compras");
    }

    @PreDestroy
    public void destroy(){
        log.info ("Eliminando el carro de compras");
    }

}
