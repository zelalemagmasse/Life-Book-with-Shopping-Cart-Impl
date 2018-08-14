package com.lifebook.Model.Shopping;

import com.lifebook.Model.AppUser;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToMany(cascade=CascadeType.ALL)
    private Set<Item> itemPurchased=new HashSet<>();
    private double totalPrice=0;
    private int numItemPurchased=0;
    private boolean checkout;
    private Date dateOfPurchase;
    private Date deliveryDate;

    @OneToOne(mappedBy = "userCart", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY )
    private AppUser purchaser;

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public boolean isCheckout() {
        return checkout;
    }

    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
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

