package com.jdc.spring.trx.dto.input;

import com.jdc.spring.trx.utils.constants.LedgerType;
import com.jdc.spring.trx.utils.constants.TransactionType;

public record TransactionBaseForm(
		TransactionType type, 
		LedgerType ledger, 
		String account, 
		int beforeAmount, 
		int amount,
		String particular) {
	
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private TransactionType type;
		private LedgerType ledger;
		private String account;
		private int beforeAmount;
		private int amount;
		private String particular;

		public Builder type(TransactionType type) {
			this.type = type;
			return this;
		}

		public Builder ledger(LedgerType ledger) {
			this.ledger = ledger;
			return this;
		}

		public Builder account(String account) {
			this.account = account;
			return this;
		}

		public Builder beforeAmount(int beforeAmount) {
			this.beforeAmount = beforeAmount;
			return this;
		}

		public Builder amount(int amount) {
			this.amount = amount;
			return this;
		}

		public Builder particular(String particular) {
			this.particular = particular;
			return this;
		}

		public TransactionBaseForm build() {
			return new TransactionBaseForm(type, ledger, account, beforeAmount, amount, particular);
		}
	}
}
