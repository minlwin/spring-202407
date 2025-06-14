package com.jdc.portal.api.controller.office.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jdc.portal.api.model.entity.Employee;
import com.jdc.portal.api.model.entity.Employee.Type;

public record EmployeeDetails(
	    long id,
	    String name,
	    String email,
	    String phone,
	    Type type,
	    String address,
	    String township,
	    String region,
	    LocalDate dob,
	    LocalDate assignAt,
	    LocalDate resignAt,
	    LocalDateTime registeredAt,
	    LocalDateTime activatedAt,
	    boolean changedPass,
	    LocalDateTime changePassAt
	) {

	    // Static Builder class
	    public static class Builder {
	        private long id;
	        private String name;
	        private String email;
	        private String phone;
	        private Type type;
	        private String address;
	        private String township;
	        private String region;
	        private LocalDate dob;
	        private LocalDate assignAt;
	        private LocalDate resignAt;
	        private LocalDateTime registeredAt;
	        private LocalDateTime activatedAt;
	        private boolean changedPass;
	        private LocalDateTime changePassAt;

	        public Builder id(long id) {
	            this.id = id;
	            return this;
	        }

	        public Builder name(String name) {
	            this.name = name;
	            return this;
	        }

	        public Builder email(String email) {
	            this.email = email;
	            return this;
	        }

	        public Builder phone(String phone) {
	            this.phone = phone;
	            return this;
	        }

	        public Builder type(Type type) {
	            this.type = type;
	            return this;
	        }

	        public Builder address(String address) {
	            this.address = address;
	            return this;
	        }

	        public Builder township(String township) {
	            this.township = township;
	            return this;
	        }

	        public Builder region(String region) {
	            this.region = region;
	            return this;
	        }

	        public Builder dob(LocalDate dob) {
	            this.dob = dob;
	            return this;
	        }

	        public Builder assignAt(LocalDate assignAt) {
	            this.assignAt = assignAt;
	            return this;
	        }

	        public Builder resignAt(LocalDate resignAt) {
	            this.resignAt = resignAt;
	            return this;
	        }

	        public Builder registeredAt(LocalDateTime registeredAt) {
	            this.registeredAt = registeredAt;
	            return this;
	        }

	        public Builder activatedAt(LocalDateTime activatedAt) {
	            this.activatedAt = activatedAt;
	            return this;
	        }

	        public Builder changedPass(boolean changedPass) {
	            this.changedPass = changedPass;
	            return this;
	        }

	        public Builder changePassAt(LocalDateTime changePassAt) {
	            this.changePassAt = changePassAt;
	            return this;
	        }

	        public EmployeeDetails build() {
	            return new EmployeeDetails(
	                id, name, email, phone, type, address, township, region,
	                dob, assignAt, resignAt, registeredAt, activatedAt,
	                changedPass, changePassAt
	            );
	        }
	    }

	    // Static factory method
	    public static EmployeeDetails from(Employee entity) {
	        return new Builder()
	            .id(entity.getId())
	            .name(entity.getName())
	            .email(entity.getAccount().getEmail())
	            .phone(entity.getContact().getPhone())
	            .type(entity.getType())
	            .address(entity.getContact().getAddress())
	            .township(entity.getContact().getTownship())
	            .region(entity.getContact().getRegion())
	            .dob(entity.getDob())
	            .assignAt(entity.getAssignAt())
	            .resignAt(entity.getResignAt())
	            .registeredAt(entity.getAccount().getRegisteredAt())
	            .activatedAt(entity.getAccount().getActivatedAt())
	            .changedPass(entity.getAccount().isChangedPass())
	            .changePassAt(entity.getAccount().getChangePassAt())
	            .build();
	    }
	}

