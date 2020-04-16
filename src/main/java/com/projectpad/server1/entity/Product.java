package com.projectpad.server1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "product_tbl")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private int pid;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private double price;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "is_food")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean food; // aparent in mysql nu exista boolean.
    // Tre convertit dintr-un one bit int in boolean prin maparea asta

    @Column(name = "pic")
    private String pic;

}
