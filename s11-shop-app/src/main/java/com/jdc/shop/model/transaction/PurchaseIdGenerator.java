package com.jdc.shop.model.transaction;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.model.transaction.entity.PurchasePk;
import com.jdc.shop.model.transaction.entity.PurchaseSeq;
import com.jdc.shop.model.transaction.repo.PurchaseSeqRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseIdGenerator {
	
	private final PurchaseSeqRepo seqRepo;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
	public PurchasePk next(LocalDate today) {
		
		var seq = seqRepo.findById(today).orElseGet(() -> {
			var entity = new PurchaseSeq();
			entity.setIssueAt(today);
			return seqRepo.save(entity);
		});
		
		return seq.next();
	}

}
