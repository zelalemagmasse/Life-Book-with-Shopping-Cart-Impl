package com.lifebook.Model.Shopping;

import com.lifebook.Model.AppUser;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @OneToMany(mappedBy = "cartToPurchase")
    private Set<Item> itemPurchased;
    private double totalPrice;
    private int numItemPurchased;
    @OneToOne(mappedBy = "userCart")
    private AppUser purchaser;

    public Cart() {
        this.itemPurchased = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Item> getItemPurchased() {
        return itemPurchased;
    }

    public void setItemPurchased(Set<Item> itemPurchased) {
        this.itemPurchased = itemPurchased;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNumItemPurchased() {
        return numItemPurchased;
    }

    public void setNumItemPurchased(int numItemPurchased) {
        this.numItemPurchased = numItemPurchased;
    }

    public AppUser getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(AppUser purchaser) {
        this.purchaser = purchaser;
    }
}
