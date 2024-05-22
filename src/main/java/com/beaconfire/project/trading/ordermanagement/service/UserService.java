package com.beaconfire.project.trading.ordermanagement.service;

import com.beaconfire.project.trading.ordermanagement.dto.user.UserDetailDto;
import com.beaconfire.project.trading.ordermanagement.response.GeneralResponse;

import java.util.List;

public interface UserService {
    public UserDetailDto getCurrentUser();
    public List<String> findAccountIdsByUserId(Integer userId);
    public GeneralResponse getAllAccountsOfUser(Integer userId);
    public List<UserDetailDto> getUserByEmail(String email);
}
