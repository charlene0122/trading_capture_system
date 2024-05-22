package com.beaconfire.project.trading.ordermanagement.repository;

import com.beaconfire.project.trading.ordermanagement.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {
}
