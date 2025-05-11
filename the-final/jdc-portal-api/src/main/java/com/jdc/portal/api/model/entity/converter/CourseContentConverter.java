package com.jdc.portal.api.model.entity.converter;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdc.portal.api.model.dto.CourseContent;
import com.jdc.portal.api.utils.exceptions.ApiSystemException;

import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CourseContentConverter implements AttributeConverter<List<CourseContent>, String>{

	private final ObjectMapper objectMapper;
	
	@Override
	public String convertToDatabaseColumn(List<CourseContent> attribute) {
		
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
	public List<CourseContent> convertToEntityAttribute(String dbData) {
		
		if(StringUtils.hasLength(dbData)) {
			try {
				return objectMapper.readValue(dbData, new TypeReference<List<CourseContent>>() {});
			} catch (JsonProcessingException e) {
				throw new ApiSystemException(e);
			}
		}
		
		return null;
	}

}
