package com.beaconfire.project.trading.ordermanagement.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "UserAccount")
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "account_id")
    private String accountId;
}
