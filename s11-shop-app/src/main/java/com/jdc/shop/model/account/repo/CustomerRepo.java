package com.jdc.shop.model.account.repo;

import java.util.Optional;
import java.util.UUID;

import com.jdc.shop.model.BaseRepository;
import com.jdc.shop.model.account.entity.Customer;

public interface CustomerRepo extends BaseRepository<Customer, UUID>{

	Optional<Customer> findOneByAccountEmail(String email);
}
