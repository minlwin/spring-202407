package com.jdc.spring.trx.service;

import org.springframework.stereotype.Service;

import com.jdc.spring.trx.dto.input.TransferForm;
import com.jdc.spring.trx.dto.output.TransferDetails;

@Service
public class TransferServiceImpl implements TransferService{

	@Override
	public int transfer(TransferForm form) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TransferDetails findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
