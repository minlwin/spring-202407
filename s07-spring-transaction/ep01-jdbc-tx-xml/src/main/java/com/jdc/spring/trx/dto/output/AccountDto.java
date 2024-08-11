package com.jdc.spring.trx.dto.output;

import com.jdc.spring.trx.utils.constants.UserLevel;

public record AccountDto(
		String loginId,
		String name,
		UserLevel level,
		int amount) {

}
