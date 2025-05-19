package com.jdc.portal.api.model;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.jdc.portal.api.model.dto.PageResult;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

public class BaseRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID>{

	private EntityManager em;
	
	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
	}

	@Override
	public <R> PageResult<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc,
			Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc, int page, int size) {
		
		var count = em.createQuery(countFunc.apply(em.getCriteriaBuilder())).getSingleResult();
		
		if(count == 0L) {
			return PageResult.of(count, page, size);
		}
		
		var query = em.createQuery(queryFunc.apply(em.getCriteriaBuilder()));
		query.setMaxResults(size);
		query.setFirstResult(size * page);
		var content = query.getResultList();
		
		return new PageResult<R>(content, page, size, count);
	}

	@Override
	public <R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
		return em.createQuery(queryFunc.apply(em.getCriteriaBuilder())).getResultList();
	}

	@Override
	public <R> Optional<R> findOne(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
		return Optional.ofNullable(em.createQuery(queryFunc.apply(em.getCriteriaBuilder())).getSingleResult());
	}
}
