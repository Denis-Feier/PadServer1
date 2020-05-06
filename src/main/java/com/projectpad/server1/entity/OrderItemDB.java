package com.projectpad.server1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "order_items_tbl")
public class OrderItemDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iid")
    private int iid;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "pid")
    private int pid;

    @Column(name = "o_id_fik")
    private int oidFik;

}
