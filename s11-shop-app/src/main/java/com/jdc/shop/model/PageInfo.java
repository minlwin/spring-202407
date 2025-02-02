package com.jdc.shop.model;

import java.util.ArrayList;
import java.util.List;

public record PageInfo<T>(
		List<T> contents,
		Long count,
		int page,
		int size
		) {

	public List<Integer> getLinks() {
		
		var list = new ArrayList<Integer>();
		
		list.add(page);
		
		while(list.getFirst() > 0 && list.size() < 3) {
			list.addFirst(list.getFirst() - 1);
		}
		
		while(list.getLast() < getTotalPages() - 1 && list.size() < 5) {
			list.add(list.getLast() + 1);
		}
		
		while(list.getFirst() > 0 && list.size() < 5) {
			list.addFirst(list.getFirst() - 1);
		}

		return list;
	}
	
	public int getTotalPages() {
		var totalPage = count.intValue() / size;
		return count.intValue() % size > 0 ? totalPage + 1 : totalPage;
	}
	
	public boolean isShowFirst() {
		var links = getLinks();
		return !links.isEmpty() && links.getFirst() > 0;
	}
	
	

	public boolean isShowLast() {
		var links = getLinks();
		return !links.isEmpty() && links.getLast() < getTotalPages() - 1;
	}
}
