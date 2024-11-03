package com.jdc.spring.web.model;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@NoRepositoryBean
public interface BaseRepostiroy<T, ID> extends JpaRepository<T, ID>{
	
	List<T> search(Function<CriteriaBuilder, CriteriaQuery<T>> queryFunc);
}
