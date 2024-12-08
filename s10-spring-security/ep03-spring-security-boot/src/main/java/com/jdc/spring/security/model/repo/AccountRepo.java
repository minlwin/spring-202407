package com.jdc.spring.security.model.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jdc.spring.security.model.entity.Account;

public interface AccountRepo extends JpaRepository<Account, UUID>{

	Optional<Account> findOneByEmail(String email);
}
