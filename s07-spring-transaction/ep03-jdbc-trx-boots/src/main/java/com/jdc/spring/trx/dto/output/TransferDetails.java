package com.jdc.spring.trx.dto.output;

import java.time.LocalDateTime;

import com.jdc.spring.trx.utils.constants.LedgerType;
import com.jdc.spring.trx.utils.constants.TransactionStatus;
import com.jdc.spring.trx.utils.constants.TransactionType;
import com.jdc.spring.trx.utils.constants.UserLevel;

public record TransferDetails(
		int id,
		TransactionType trxType,
		LocalDateTime issueAt,
		TransactionStatus status,
		LedgerType senderLedger,
		String senderId,
		String senderName,
		UserLevel senderLevel,
		int senderBefore,
		LedgerType receiverLedger,
		String receiverId,
		String receiverName,
		UserLevel receiverLevel,
		int receiverBefore,
		int amount,
		String particular
) {

}
