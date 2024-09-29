package com.jdc.spring.trx.args;

import java.time.LocalDate;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import com.jdc.spring.trx.domain.Employee;

public class EmployeeAgregator implements ArgumentsAggregator {

	@Override
	public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
			throws ArgumentsAggregationException {
		return new Employee(accessor.getString(0), accessor.getString(1), accessor.getString(2), accessor.getString(3), LocalDate.now());
	}

}
