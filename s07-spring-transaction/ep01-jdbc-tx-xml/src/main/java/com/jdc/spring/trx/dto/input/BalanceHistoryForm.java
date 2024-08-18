package com.jdc.spring.trx.dto.input;

import com.jdc.spring.trx.utils.constants.LedgerType;

public record BalanceHistoryForm(
		int trxId, 
		String accountId, 
		int beforeAmount, 
		int trxAmount, 
		LedgerType ledger,
		String particular) {
	
	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private int trxId;
		private String accountId;
		private int beforeAmount;
		private int trxAmount;
		private LedgerType ledger;
		private String particular;

		public Builder trxId(int trxId) {
			this.trxId = trxId;
			return this;
		}

		public Builder accountId(String accountId) {
			this.accountId = accountId;
			return this;
		}

		public Builder beforeAmount(int beforeAmount) {
			this.beforeAmount = beforeAmount;
			return this;
		}

		public Builder trxAmount(int trxAmount) {
			this.trxAmount = trxAmount;
			return this;
		}

		public Builder ledger(LedgerType ledger) {
			this.ledger = ledger;
			return this;
		}

		public Builder particular(String particular) {
			this.particular = particular;
			return this;
		}

		public BalanceHistoryForm build() {
			return new BalanceHistoryForm(trxId, accountId, beforeAmount, trxAmount, ledger, particular);
		}
	}
}
