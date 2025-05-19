package com.jdc.portal.api.model.entity.converter;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdc.portal.api.model.dto.CourseFees;
import com.jdc.portal.api.model.entity.Course.FeesType;
import com.jdc.portal.api.utils.exceptions.ApiSystemException;

import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CourseFeesConverter implements AttributeConverter<Map<FeesType, CourseFees>, String>{

	private final ObjectMapper objectMapper;

	@Override
	public String convertToDatabaseColumn(Map<FeesType, CourseFees> attribute) {
		if(null != attribute && !attribute.isEmpty()) {
			try {
				return objectMapper.writeValueAsString(attribute);
			} catch (JsonProcessingException e) {
				throw new ApiSystemException(e);
			}
		}
		
		return null;
	}

	@Override
	public Map<FeesType, CourseFees> convertToEntityAttribute(String dbData) {
		if(StringUtils.hasLength(dbData)) {
			try {
				return objectMapper.readValue(dbData, new TypeReference<Map<FeesType, CourseFees>>() {});
			} catch (JsonProcessingException e) {
				throw new ApiSystemException(e);
			}
		}
		
		return null;
	}

}
