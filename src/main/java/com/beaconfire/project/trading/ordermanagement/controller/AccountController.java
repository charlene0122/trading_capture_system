package com.beaconfire.project.trading.ordermanagement.controller;

import com.beaconfire.project.trading.ordermanagement.request.account.*;
import com.beaconfire.project.trading.ordermanagement.response.*;
import com.beaconfire.project.trading.ordermanagement.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@AllArgsConstructor
@Slf4j
public class AccountController {
    private AccountService accountService;

    // 2.2.1 list/search accounts give the search requirements
    @PostMapping("/search")
    public ResponseEntity<GeneralResponse> searchAccounts(@RequestBody AccountSearchRequest request) {
        GeneralResponse response = accountService.searchAccounts(request);
        return ResponseEntity.ok(response);
    }

    // 2.2.2 create financial account
    @PostMapping("")
    public ResponseEntity<GeneralResponse> createNewAccount(@RequestBody AccountCreationRequest request){
        String id = accountService.accountCreation(request.getNickname(), request.getUserId());
        if (id.equals("")) {
            return new ResponseEntity<>(new GeneralResponse(
                    new ServiceStatus(false, "", ""),
                    id
            ), HttpStatus.CONFLICT);
        }
        return ResponseEntity.ok(new GeneralResponse(
                new ServiceStatus(true, "", ""),
                id
        ));
    }

    // 2.2.3 fetch cash balance detail under account
    @PostMapping("/detail/{accountId}/cash-balance")
    public ResponseEntity<GeneralResponse> getCashBalance(@PathVariable String accountId, @RequestBody AccountRequest request) {
        // Assuming your service method handles pagination and returns a proper response
        GeneralResponse response = accountService.fetchCashBalance(accountId, request);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(response);
    }

    // 2.2.4 fetch cash balance detail under account
    @PostMapping("/detail/{accountId}/security-balance")
    public ResponseEntity<GeneralResponse> getSecurityBalance(@PathVariable String accountId, @RequestBody AccountRequest request) {
        // Assuming your service method handles pagination and returns a proper response
        GeneralResponse response = accountService.fetchSecurityBalance(accountId, request);

        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(response);
    }

    // 2.2.5 update account status
    @PatchMapping("/{accountId}/status")
    public ResponseEntity<GeneralResponse> updateAccountStatus(@PathVariable String accountId, @RequestBody StatusUpdateRequest request) {
        GeneralResponse response = accountService.updateAccountStatus(accountId, request);
        return ResponseEntity.ok(response);
    }

    // 2.2.6 add cash transaction under account
    @PostMapping("/cash")
    public ResponseEntity<GeneralResponse> addCashTransaction(@RequestBody AddCashTransactionRequest request) {
        GeneralResponse response = accountService.addCashTransaction(request);
        return ResponseEntity.ok(response);
    }

    // 2.2.7 add security transaction under account
    @PostMapping("/security")
    public ResponseEntity<GeneralResponse> addCashTransaction(@RequestBody AddSecurityTransactionRequest request) {
        GeneralResponse response = accountService.addSecurityTransaction(request);
        return ResponseEntity.ok(response);
    }

}
