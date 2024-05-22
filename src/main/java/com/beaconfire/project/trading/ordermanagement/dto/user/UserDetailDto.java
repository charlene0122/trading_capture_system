package com.beaconfire.project.trading.ordermanagement.dto.user;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class UserDetailDto {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isAdmin;
}