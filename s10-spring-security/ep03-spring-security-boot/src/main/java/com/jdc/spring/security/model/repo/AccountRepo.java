package com.jdc.spring.security.model.repo;

import java.util.Optional;
import java.util.UUID;

import com.jdc.spring.security.model.BaseRepository;
import com.jdc.spring.security.model.entity.Account;

public interface AccountRepo extends BaseRepository<Account, UUID>{

	Optional<Account> findOneByEmail(String email);
}
