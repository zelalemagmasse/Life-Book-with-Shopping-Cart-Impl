package com.lifebook.Repositories;

import com.lifebook.Model.AppUser;
import com.lifebook.Model.Shopping.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Long> {
    List<Item> findAllByNameOfItemContainingIgnoreCase(String s);
    List<Item> findAllByTagsContainingIgnoreCase(String s);
    @Query(value = "select top 10 * from Item", nativeQuery = true)
    Iterable<Item> getTop10();



}
