package com.jdc.shop.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageStorageService {
	
	public enum Type {
		Product, Profile
	}
	
	private final DateTimeFormatter DF = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	public List<String> save(Type type, String prefix, MultipartFile[] files) {
		
		var result = new ArrayList<String>();
		
		for(var i = 0; i < files.length; i ++) {
			var individualPrefix = "%s_%03d".formatted(prefix, i + 1);
			var fileName = save(type, individualPrefix, files[i]);
			if(null != fileName) {
				result.add(fileName);
			}
		}
		
		return result;
	}

	public String save(Type type, String prefix, MultipartFile file) {
		
		try {
			var fileName = getFileName(type, prefix, file);
			Files.copy(file.getInputStream(), getImagePath(fileName), StandardCopyOption.REPLACE_EXISTING);
			return fileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private Path getImagePath(String fileName) {
		var storageFolder = getStorageFolder().get();
		return Path.of(storageFolder, fileName);
	}

	private Optional<String> getStorageFolder() {
		
		if(RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes attr) {
			return Optional.ofNullable(attr.getRequest())
				.map(a -> a.getServletContext())
				.map(a -> a.getRealPath("/resources/images"));
		}
		
		return Optional.empty();
	}

	private String getFileName(Type type, String prefix, MultipartFile file) {
		var extension = getExtension(file.getOriginalFilename());
		var timeStamp = LocalDateTime.now().format(DF);
		// type_prefix_timestamp.extension
		return "%s_%s_%s.%s".formatted(type.name().toLowerCase(), prefix, timeStamp, extension);
	}

	private String getExtension(String originalFilename) {
		var array = originalFilename.split("\\.");
		return array[array.length - 1];
	}
}
