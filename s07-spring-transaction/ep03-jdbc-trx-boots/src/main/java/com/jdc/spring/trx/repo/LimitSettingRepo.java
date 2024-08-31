package com.jdc.spring.trx.repo;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.jdc.spring.trx.dto.output.LimitSettingDto;
import com.jdc.spring.trx.utils.constants.TransactionType;
import com.jdc.spring.trx.utils.constants.UserLevel;

@Transactional(readOnly = true)
public interface LimitSettingRepo {

	Optional<LimitSettingDto> findById(UserLevel level, TransactionType type);

}
