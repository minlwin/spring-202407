package com.jdc.spring.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.controller.input.RegistrationForm;
import com.jdc.spring.controller.input.RegistrationSearch;
import com.jdc.spring.controller.output.RegistrationDetails;
import com.jdc.spring.controller.output.RegistrationInfo;
import com.jdc.spring.model.entity.Registration;
import com.jdc.spring.model.entity.RegistrationPk;
import com.jdc.spring.model.entity.Registration_;
import com.jdc.spring.model.repo.RegistrationRepo;
import com.jdc.spring.model.repo.SectionRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
@Transactional(readOnly = true)
public class RegistrationService {
	
	@Autowired
	private RegistrationRepo registrationRepo;
	@Autowired
	private SectionRepo sectionRepo;

	public RegistrationForm findForEdit(Integer sectionId) {
		return sectionRepo.findById(sectionId)
			.map(RegistrationForm::from)
			.orElseThrow();
	}

	public List<RegistrationInfo> search(RegistrationSearch search) {
		
		Function<CriteriaBuilder, CriteriaQuery<RegistrationInfo>> queryFunc = cb -> {
			var cq = cb.createQuery(RegistrationInfo.class);
			var root = cq.from(Registration.class);
			
			RegistrationInfo.select(cq, root);
			cq.where(search.where(cb, root));
			
			cq.orderBy(cb.desc(root.get(Registration_.registedAt)));
			
			return cq;
		};
		
		return registrationRepo.search(queryFunc);
	}

	public RegistrationDetails findById(String id) {
		return registrationRepo.findById(RegistrationPk.from(id))
				.map(RegistrationDetails::from)
				.orElseThrow();
	}

	@Transactional
	public void save(RegistrationForm form) {
		// TODO Auto-generated method stub
		
	}

}
