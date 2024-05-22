package com.beaconfire.project.trading.ordermanagement.controller;

import com.beaconfire.project.trading.ordermanagement.dto.user.UserDetailDto;
import com.beaconfire.project.trading.ordermanagement.exception.ResourceNotFoundException;
import com.beaconfire.project.trading.ordermanagement.request.user.SearchByEmailRequest;
import com.beaconfire.project.trading.ordermanagement.response.GeneralResponse;
import com.beaconfire.project.trading.ordermanagement.response.ServiceStatus;
import com.beaconfire.project.trading.ordermanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    UserService userService;

    // 2.3.1 fetch current user info
    @GetMapping("/current")
    public ResponseEntity<GeneralResponse> getCurrentUser() {
        UserDetailDto user = userService.getCurrentUser();
        return ResponseEntity.ok(
                new GeneralResponse(
                        new ServiceStatus(true, "", ""),
                        user
                ));
    }

    // 2.3.2 fetch all account details of the target user
    @GetMapping("/account")
    public ResponseEntity<GeneralResponse> getAccountDetailsOfUser() {
        GeneralResponse response = userService.getAllAccountsOfUser(1);
        return ResponseEntity.ok(response);
    }

    // 2.3.3 fetch all account details of the target user
    @PostMapping("/search")
    public ResponseEntity<GeneralResponse> getUsersByEmail(@RequestBody SearchByEmailRequest request) {

        List<UserDetailDto> users = userService.getUserByEmail(request.getSearchContent());
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found for email " + request.getSearchContent());
        }
        return ResponseEntity.ok(
                new GeneralResponse(
                        new ServiceStatus(true, "", ""),
                        users
                ));
    }
}
