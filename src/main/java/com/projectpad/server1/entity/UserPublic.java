package com.projectpad.server1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class UserPublic {

    private int id;

    private String userName;

    private String email;

    private String role;

    private String token;

    private String pic;

}
