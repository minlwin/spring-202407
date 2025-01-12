package com.jdc.shop.model.master.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.controller.input.CategoryForm;
import com.jdc.shop.controller.input.CategorySearch;
import com.jdc.shop.controller.output.CategoryInfo;
import com.jdc.shop.model.PageInfo;
import com.jdc.shop.model.master.entity.Category;
import com.jdc.shop.model.master.entity.Category_;
import com.jdc.shop.model.master.repo.CategoryRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryManagementService {
	
	private final CategoryRepo repo;

	@Transactional(readOnly = true)
	public PageInfo<CategoryInfo> search(CategorySearch search, int page, int size) {
		return repo.search(searchFunc(search), countFunc(search), page, size);
	}

	private Function<CriteriaBuilder, CriteriaQuery<CategoryInfo>> searchFunc(CategorySearch search) {
		return cb -> {
			var cq = cb.createQuery(CategoryInfo.class);
			var root = cq.from(Category.class);
			
			CategoryInfo.select(cb, cq, root);
			cq.where(search.where(cb, root));
			
			cq.orderBy(cb.asc(root.get(Category_.id)));
			
			return cq;
		};
	}
	
	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(CategorySearch search) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Category.class);
			
			cq.select(cb.count(root.get(Category_.id)));
			cq.where(search.where(cb, root));
			
			return cq;
		};
	}	

	@Transactional
	public void upload(MultipartFile file) {
		if(null != file) {
			
			try(var br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
				
				String line = null;
				
				while(null != (line = br.readLine())) {
					
					if(repo.countByNameIgnoreCase(line) == 0L) {
						var entity = new Category();
						entity.setName(line);
						repo.save(entity);
					}
				}
				
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Transactional
	public void save(CategoryForm form) {
		
		if(repo.countByNameIgnoreCase(form.getName()) == 0L) {
			
			if(null != form.getId()) {
				repo.findById(form.getId()).ifPresent(entity -> entity.setName(form.getName()));
			} else {
				var entity = new Category();
				entity.setName(form.getName());
				repo.save(entity);
			}
		}
	}

}
