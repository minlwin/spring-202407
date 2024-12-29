package com.jdc.shop.model.account.repo;

import java.util.Optional;
import java.util.UUID;

import com.jdc.shop.model.BaseRepository;
import com.jdc.shop.model.account.entity.Account;

public interface AccountRepo extends BaseRepository<Account, UUID>{

	Optional<Account> findOneByEmail(String email);
}
