package com.jdc.spring.trx.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.spring.trx.model.repo.TrxHistoryRepo;

@Service
public class TrxHistoryServiceImpl implements TrxHistoryService {
	
	@Autowired
	private TrxHistoryRepo repo;

	@Override
	public int createHistory(String description, int amount, String status) {
		return repo.create(description, amount, status);
	}

	@Override
	public void updateStatus(int id, String status) {
		repo.update(id, status);
	}

	@Override
	public void updateStatus(int id, String status, String message) {
		repo.update(id, status, message);
	}

}
