package com.lifebook.Service;

import com.lifebook.Model.Shopping.Cart;
import com.lifebook.Model.Shopping.Item;
import com.lifebook.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ShoppingService {

    @Autowired
    AppUserRepository appUserRepository;
    public Cart priceCalculator(Cart myCart){
       double total=0;
       Set<Item> items=myCart.getItemPurchased();
       for(Item eachItem:items){

          total=total + (eachItem.getPrice()* 0.02) + eachItem.getPrice();

       }
        myCart.setTotalPrice(total);
        myCart.setNumItemPurchased(myCart.getItemPurchased().size());
     return  myCart;
    }
}
