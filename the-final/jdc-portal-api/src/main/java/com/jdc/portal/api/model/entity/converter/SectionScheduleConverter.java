package com.jdc.portal.api.model.entity.converter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdc.portal.api.model.dto.SectionSchedule;
import com.jdc.portal.api.utils.exceptions.ApiSystemException;

import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SectionScheduleConverter implements AttributeConverter<SectionSchedule, String>{

	private final ObjectMapper objectMapper;
	
	@Override
	public String convertToDatabaseColumn(SectionSchedule attribute) {
		
		if(null != attribute) {
			try {
				return objectMapper.writeValueAsString(attribute);
			} catch (JsonProcessingException e) {
				throw new ApiSystemException(e);
			}
		}
		
		return null;
	}

	@Override
	public SectionSchedule convertToEntityAttribute(String dbData) {
		
		if(StringUtils.hasLength(dbData)) {
			try {
				return objectMapper.readValue(dbData, SectionSchedule.class);
			} catch (JsonProcessingException e) {
				throw new ApiSystemException(e);
			}
		}
		
		return null;
	}

}
