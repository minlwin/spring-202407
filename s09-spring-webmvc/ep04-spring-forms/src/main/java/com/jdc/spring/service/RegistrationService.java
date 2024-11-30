package com.jdc.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jdc.spring.controller.input.RegistrationForm;
import com.jdc.spring.controller.input.RegistrationSearch;
import com.jdc.spring.controller.output.RegistrationDetails;
import com.jdc.spring.controller.output.RegistrationInfo;
import com.jdc.spring.model.entity.RegistrationPk;
import com.jdc.spring.model.repo.RegistrationRepo;
import com.jdc.spring.model.repo.SectionRepo;

@Service
@Transactional(readOnly = true)
public class RegistrationService {
	
	@Autowired
	private RegistrationRepo registrationRepo;
	@Autowired
	private SectionRepo sectionRepo;

	public RegistrationForm findForEdit(String id, Integer sectionId) {
		
		if(StringUtils.hasLength(id)) {
			return registrationRepo.findById(RegistrationPk.from(id))
					.map(RegistrationForm::from)
					.orElseThrow();
		}
		
		return sectionRepo.findById(sectionId)
				.map(RegistrationForm::from)
				.orElseThrow();
	}

	public List<RegistrationInfo> search(RegistrationSearch search) {
		// TODO Auto-generated method stub
		return null;
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
