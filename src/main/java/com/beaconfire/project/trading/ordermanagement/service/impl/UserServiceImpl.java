package com.beaconfire.project.trading.ordermanagement.service.impl;

import com.beaconfire.project.trading.ordermanagement.dto.user.UserDetailDto;
import com.beaconfire.project.trading.ordermanagement.entity.User;
import com.beaconfire.project.trading.ordermanagement.entity.UserAccount;
import com.beaconfire.project.trading.ordermanagement.exception.ResourceNotFoundException;
import com.beaconfire.project.trading.ordermanagement.repository.UserAccountRepository;
import com.beaconfire.project.trading.ordermanagement.repository.UserRepository;
import com.beaconfire.project.trading.ordermanagement.response.GeneralResponse;
import com.beaconfire.project.trading.ordermanagement.service.AccountService;
import com.beaconfire.project.trading.ordermanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserAccountRepository userAccountRepository;
    AccountService accountService;

    @Override
    public UserDetailDto getCurrentUser() {
        User user = userRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("User not Found."));
        return UserDetailDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .isAdmin(user.getIsAdmin())
                .build();
    }

    @Override
    public List<String> findAccountIdsByUserId(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No user with id " + userId));
        List<UserAccount> accounts = user.getUserAccounts();
        return accounts.stream().map(UserAccount::getAccountId).collect(Collectors.toList());
    }
    @Override
    public GeneralResponse getAllAccountsOfUser(Integer userId) {
        List<String> accountIds = findAccountIdsByUserId(userId);
        return accountService.fetchAccountInformationV2(accountIds);
    }

    @Override
    public List<UserDetailDto> getUserByEmail(String email) {
        List<User> users = userRepository.findByEmail(email);
        return users.stream()
                .map(user -> new UserDetailDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getIsAdmin()))
                .collect(Collectors.toList());
    }
}
