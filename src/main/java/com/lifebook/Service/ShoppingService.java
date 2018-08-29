package com.lifebook.Service;

import com.lifebook.Model.AppUser;
import com.lifebook.Model.Shopping.Cart;
import com.lifebook.Model.Shopping.Item;
import com.lifebook.Repositories.AppUserRepository;
import com.lifebook.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ShoppingService {

    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    ItemRepository itemRepository;
    public Cart priceCalculator(Cart myCart){
       double total=0;
       int totalItemPurchased=0;
       Set<Item> items=myCart.getItemPurchased();
       for(Item eachItem:items){

         // total=total + ((eachItem.getPrice()* 0.02))*  + eachItem.getPrice()*eachItem.getNumOfItem();
          total=total +(eachItem.getPrice()*eachItem.getNumOfItem()) *0.06  + eachItem.getPrice()*eachItem.getNumOfItem();
         totalItemPurchased=totalItemPurchased + eachItem.getNumOfItem();
         eachItem.setPurchased(true);
         itemRepository.save(eachItem);

       }
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Calendar c = Calendar.getInstance();
        myCart.setDateOfPurchase(c.getTime());
        c.add(Calendar.DATE, 3);
        myCart.setDeliveryDate(c.getTime());

        myCart.setTotalPrice(total);
        myCart.setNumItemPurchased(totalItemPurchased);

     return  myCart;
    }
    public Cart pricePlusNotification(Cart myCart,AppUser userNow){
       Set<Item>items= priceCalculator(myCart).getItemPurchased();
       for(Item eachItem:items){
            AppUser owner= appUserRepository.findByUsername(eachItem.getItemOwner());
            owner.setNotification("your" + eachItem.getNameOfItem() +" has been bought by " + userNow.getFirstName() + " "+ userNow.getLastName()
            + " his address for Delivery " + userNow.getStreetAddress() + " " + userNow.getZipCode());
       }
       if(userNow.getStreetAddress()!=null){
           myCart.setAddressFilled(true);
       }
       else {
           myCart.setAddressFilled(false);
        }
       return myCart;
    }

    public Cart cancelShoping(Cart myCart){
        myCart.setNumItemPurchased(0);
        for(Item eachItem:myCart.getItemPurchased()) {

            eachItem.setNumberInTheStock(eachItem.getNumberInTheStock()+eachItem.getNumOfItem());
            eachItem.setNumOfItem(0);
            eachItem.setSoldout(false);

            itemRepository.save(eachItem);
        }
        myCart.setNumItemPurchased(0);
        myCart.setTotalPrice(0);
        return myCart;
    }


    public Set<Item>filteredItemForYourOwn(AppUser usernow){
        Iterable<Item>items=itemRepository.findAll();
        Set<Item>filtered=new HashSet<>();
        for (Item eachItem:items){
            if(eachItem.getItemOwner().equalsIgnoreCase(usernow.getUsername())) {
                filtered.add(eachItem);
            }
        }
        return filtered;
    }
    public Set<Item>filteredItemForOtherUser(AppUser usernow){
        Iterable<Item>items=itemRepository.findAll();
        Set<Item>filtered=new HashSet<>();
        for (Item eachItem:items){
            if(!eachItem.getItemOwner().equalsIgnoreCase(usernow.getUsername())) {
                filtered.add(eachItem);
            }
        }
        return filtered;
    }


}
