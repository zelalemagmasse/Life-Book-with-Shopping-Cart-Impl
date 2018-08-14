package com.lifebook.Model.Shopping;
import javax.persistence.*;



@Entity
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String nameOfItem;
    private int numberInTheStock;


    public boolean isSoldout() {
        return soldout;
    }

    public void setSoldout(boolean soldout) {
        this.soldout = soldout;
    }

    private String description;
    private String tags;
    private double price;
    private int numOfItem=1;
    private boolean soldout;
    private String productImage;
    private boolean purchased;

    private double totalPrice;

    private boolean added;
    private int totalItemPuchased;
    private String itemOwner;

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public String getItemOwner() {
        return itemOwner;
    }

    public void setItemOwner(String itemOwner) {
        this.itemOwner = itemOwner;
    }

    public int getNumberInTheStock() {
        return numberInTheStock;
    }

    public void setNumberInTheStock(int numberInTheStock) {
        this.numberInTheStock = numberInTheStock;
    }

    public int getTotalItemPuchased() {
        return totalItemPuchased;
    }

    public void setTotalItemPuchased(int totalItemPuchased) {
        this.totalItemPuchased = totalItemPuchased;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }


    public int getNumOfItem() {
        return numOfItem;
    }

    public void setNumOfItem(int numOfItem) {
        this.numOfItem = numOfItem;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameOfItem() {
        return nameOfItem;
    }

    public void setNameOfItem(String nameOfItem) {
        this.nameOfItem = nameOfItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }



    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", nameOfItem='" + nameOfItem + '\'' +
                ", description='" + description + '\'' +
                ", tags='" + tags + '\'' +
                ", price=" + price +
                ", Soldout=" + soldout +
                '}';
    }



}
