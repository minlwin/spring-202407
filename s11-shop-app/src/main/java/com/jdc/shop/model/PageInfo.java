package com.jdc.shop.model;

import java.util.ArrayList;
import java.util.List;

public record PageInfo<T>(
		List<T> contents,
		Long count,
		int page,
		int size
		) {

	public List<Integer> links() {
		
		var list = new ArrayList<Integer>();
		
		list.add(page);
		
		while(list.getFirst() > 0 && list.size() < 3) {
			list.addFirst(list.getFirst() - 1);
		}
		
		while(list.getLast() < totalPages() - 1 && list.size() < 5) {
			list.add(list.getLast() + 1);
		}
		
		while(list.getFirst() > 0 && list.size() < 5) {
			list.addFirst(list.getFirst() - 1);
		}

		return list;
	}
	
	public int totalPages() {
		var totalPage = count.intValue() / size;
		return count.intValue() % size > 0 ? totalPage + 1 : totalPage;
	}
	
	public boolean showFirst() {
		var links = links();
		return !links.isEmpty() && links.getFirst() > 0;
	}

	public boolean showLast() {
		var links = links();
		return !links.isEmpty() && links.getLast() < totalPages() - 1;
	}
}
