package com.jdc.portal.api.model;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.jdc.portal.api.model.dto.PageResult;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
	
	<R> PageResult<R> search(
			Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc, 
			Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc, 
			int page, int size);

	<R> List<R> search(
			Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc);

	<R> Optional<R> findOne(
			Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc);
	
	T persist(T entity);
}
