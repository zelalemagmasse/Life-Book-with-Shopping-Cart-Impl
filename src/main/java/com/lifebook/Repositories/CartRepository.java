package com.lifebook.Repositories;


import com.lifebook.Model.Shopping.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart,Long> {

}