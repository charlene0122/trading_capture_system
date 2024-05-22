package com.beaconfire.project.trading.ordermanagement.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "User")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @OneToMany(mappedBy = "user")
    private List<UserAccount> userAccounts;

//    @OneToMany(mappedBy = "user")
//    private List<Order> orders;

}

