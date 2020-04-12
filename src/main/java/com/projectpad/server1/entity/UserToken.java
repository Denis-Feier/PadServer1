package com.projectpad.server1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "token_tbl")
public class UserToken {

    @Id
    @Column(name = "tid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tid;

    @Column(name = "token")
    private String token;

    @Column(name = "uid_fk")
    private int uidFk;

}
