package com.jdc.spring.trx.dto.input;

import com.jdc.spring.trx.utils.constants.TransactionType;
import com.jdc.spring.trx.utils.constants.UserLevel;

public record LimitValidationForm(
		String userId, 
		UserLevel level, 
		TransactionType type, 
		int userBalance,
		int trxAmount) {
	
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private String userId;
		private UserLevel level;
		private TransactionType type;
		private int userBalance;
		private int trxAmount;

		public Builder userId(String userId) {
			this.userId = userId;
			return this;
		}

		public Builder level(UserLevel level) {
			this.level = level;
			return this;
		}

		public Builder type(TransactionType type) {
			this.type = type;
			return this;
		}

		public Builder userBalance(int userBalance) {
			this.userBalance = userBalance;
			return this;
			
		}

		public Builder trxAmount(int trxAmount) {
			this.trxAmount = trxAmount;
			return this;
		}

		public LimitValidationForm build() {
			return new LimitValidationForm(userId, level, type, userBalance, trxAmount);
		}
	}
}
