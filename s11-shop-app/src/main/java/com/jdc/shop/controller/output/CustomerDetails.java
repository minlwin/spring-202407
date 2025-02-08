package com.jdc.shop.controller.output;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.jdc.shop.model.account.entity.Customer;
import com.jdc.shop.model.account.entity.Customer.Gender;

public record CustomerDetails(
		UUID id,
		String name,
		String phone,
		String email,
		String profileImage,
		LocalDate dob,
		Gender gender,
		LocalDateTime registeredAt) {
	
	public static CustomerDetails from(Customer entity) {
		return new Builder()
				.id(entity.getId())
				.name(entity.getName())
				.phone(entity.getPhone())
				.email(entity.getAccount().getEmail())
				.profileImage(entity.getProfileImage())
				.dob(entity.getDob())
				.gender(entity.getGender())
				.registeredAt(entity.getRegisteredAt())
				.build();
	}

	
	public static class Builder {
        private UUID id;
        private String name;
        private String phone;
        private String email;
        private String profileImage;
        private LocalDate dob;
        private Gender gender;
        private LocalDateTime registeredAt;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder profileImage(String profileImage) {
            this.profileImage = profileImage;
            return this;
        }

        public Builder dob(LocalDate dob) {
            this.dob = dob;
            return this;
        }

        public Builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder registeredAt(LocalDateTime registeredAt) {
            this.registeredAt = registeredAt;
            return this;
        }

        public CustomerDetails build() {
            return new CustomerDetails(id, name, phone, email, profileImage, dob, gender, registeredAt);
        }		
	}

}
