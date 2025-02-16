package com.jdc.shop.model;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

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
	public <R> List<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
		return em.createQuery(queryFunc.apply(em.getCriteriaBuilder())).getResultList();
	}

	@Override
	public <R> PageInfo<R> search(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc, 
			Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc, int page, int size) {
		
		var count = count(countFunc);
		var query = em.createQuery(queryFunc.apply(em.getCriteriaBuilder()));
		query.setFirstResult(page * size);
		query.setMaxResults(size);
		
		return new PageInfo<R>(query.getResultList(), count, page, size);
	}

	@Override
	public Long count(Function<CriteriaBuilder, CriteriaQuery<Long>> queryFunc) {
		return search(queryFunc).stream().findAny().orElse(0L);
	}

	@Override
	public <R> Optional<R> searchOne(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunc) {
		return em.createQuery(queryFunc.apply(em.getCriteriaBuilder())).getResultList().stream().findAny();
	}

}
