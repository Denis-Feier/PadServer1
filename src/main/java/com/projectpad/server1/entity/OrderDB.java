package com.projectpad.server1.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "order_tbl")
@Builder(toBuilder=true)
public class OrderDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "o_id")
    private int oid;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "nr_items")
    private int nrItems;

    @Column(name = "state")
    private String state;

    @Column(name = "u_id")
    private int uid;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data")
    private Date data;

    @OneToMany(targetEntity = OrderItemDB.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "o_id_fik", referencedColumnName = "o_id")
    private List<OrderItemDB> orderItems;
}
