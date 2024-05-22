package com.beaconfire.project.trading.ordermanagement.repository;

import com.beaconfire.project.trading.ordermanagement.entity.User;
import com.beaconfire.project.trading.ordermanagement.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmail(String email);
}

