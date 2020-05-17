package com.projectpad.server1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderMail {

    private int oid;

    private double totalPrice;

    private int nrItems;

    private String state;

    private int uid;

    private Date data;

    private String userEmail;

    private List<Product> orderItems;
}
