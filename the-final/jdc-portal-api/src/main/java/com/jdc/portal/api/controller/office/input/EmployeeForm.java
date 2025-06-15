package com.jdc.portal.api.controller.office.input;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdc.portal.api.model.dto.Contact;
import com.jdc.portal.api.model.entity.Account;
import com.jdc.portal.api.model.entity.Account.Role;
import com.jdc.portal.api.model.entity.Employee;
import com.jdc.portal.api.model.entity.Employee.Type;
import com.jdc.portal.api.utils.PasswordGenerator;
import com.jdc.portal.api.utils.validation.Required;

public record EmployeeForm(
		@Required(name = "email")
		String username,
		@Required(name = "employee type")
		Type type,
		@Required(name = "employee name")
		String name,
		LocalDate dob,
		@Required(name = "assign date")
		LocalDate assignAt,
		@Required(name = "phone")
		String phone,
		String address,
		String township,
		String region
		) {

	public Employee getEntity(PasswordEncoder encoder) {
		var entity = new Employee();
		entity.setType(type);
		entity.setName(name);
		entity.setDob(dob);
		entity.setAssignAt(assignAt);

		var account = new Account();
		account.setEmail(username);
		account.setPassword(encoder.encode(PasswordGenerator.generate(name)));
		account.setRole(Role.Employee);
		account.setRegisteredAt(LocalDateTime.now());
		
		if(LocalDate.now().equals(assignAt)) {
			account.setActivated(true);
			account.setActivatedAt(LocalDateTime.now());
		}

		entity.setAccount(account);
		
		var contact = new Contact();
		contact.setPhone(phone);
		contact.setAddress(address);
		contact.setTownship(township);
		contact.setRegion(region);
		entity.setContact(contact);
		
		return entity;
	}

	public void update(Employee entity) {

		entity.setType(type);
		entity.setName(name);
		entity.setDob(dob);
		entity.setAssignAt(assignAt);
		
		var account = entity.getAccount();
		account.setEmail(username);
		account.setRole(Role.Employee);
		account.setRegisteredAt(LocalDateTime.now());

		if(LocalDate.now().equals(assignAt)) {
			account.setActivated(true);
			account.setActivatedAt(LocalDateTime.now());
		}

		var contact = entity.getContact();
		contact.setPhone(phone);
		contact.setAddress(address);
		contact.setTownship(township);
		contact.setRegion(region);
	}

}
