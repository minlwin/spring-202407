package com.jdc.shop.model.transaction;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.shop.model.transaction.entity.SalePk;
import com.jdc.shop.model.transaction.entity.SaleSeq;
import com.jdc.shop.model.transaction.repo.SaleSeqRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleIdGenerator {

	private final SaleSeqRepo seqRepo;
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
	public SalePk next(LocalDate today) {
		
		var seq = seqRepo.findById(today).orElseGet(() -> {
			var entity = new SaleSeq();
			entity.setIssueAt(today);
			return seqRepo.save(entity);
		});
		
		return seq.next();
	}
}
