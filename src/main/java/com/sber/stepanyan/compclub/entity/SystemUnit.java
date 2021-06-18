package com.sber.stepanyan.compclub.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SystemUnit {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
}
