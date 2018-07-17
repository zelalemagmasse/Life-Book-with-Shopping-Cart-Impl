package com.lifebook.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    private String newsType;

}
