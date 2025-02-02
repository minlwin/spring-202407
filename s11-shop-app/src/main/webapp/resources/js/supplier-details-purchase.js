document.addEventListener('DOMContentLoaded', () => {
	
	const helper = new PurchaseHelper
	const purchaseSizeSelect = document.getElementById('purchaseSizeSelect')
	purchaseSizeSelect.addEventListener('change', (e) => {
		helper.sizeChange(e.target.value)
	})
	
	
	helper.search()
})

class PurchaseHelper {

	pageChange(page) {
		const pageInput = document.getElementById('purchasePage')
		pageInput.value = page
		this.search()
	}

	sizeChange(size) {
		const pageInput = document.getElementById('purchasePage')
		const sizeInput = document.getElementById('purchaseSize')
		
		pageInput.value = 0
		sizeInput.value = size
		
		this.search()	
	}


	async search() {
		const searchForm = document.getElementById('purchaseSearchForm')
		const pageInput = document.getElementById('purchasePage')
		const sizeInput = document.getElementById('purchaseSize')
		
		const url = searchForm.action 
		const params = new URLSearchParams
		params.append('page', pageInput.value)
		params.append('size', sizeInput.value)
		
		try {
			const response = await fetch(`${url}?${params.toString()}`, {
					method : 'GET',
					headers : {
						'Content-Type' : 'application/json'
					}
				}
			)
			
			if(!response.ok) {
				throw new Error('Featch Error : Purchase for Supplier')
			}
			
			const {contents, ... pageInfo} = await response.json()
			
			this.loadData(contents)
			this.loadPagination(pageInfo)
			
		} catch(e) {
			console.log(e)
		}
		
	}

	loadData(contents) {
		console.log(contents)
		const purchaseList = document.getElementById('purchaseList')
		const baseUrl = purchaseList.dataset.details
		
		Array.from(purchaseList.children).forEach(child => purchaseList.removeChild(child))
		
		contents.forEach(item => {
			purchaseList.appendChild(this.getRow(item, baseUrl))
		})
	}

	getRow(item, baseUrl) {
		const tr = document.createElement('tr')
		tr.appendChild(this.getCell(item.id.code))
		tr.appendChild(this.getCell(item.id.issueAt))
		tr.appendChild(this.getCell(item.status))
		tr.appendChild(this.getCell(item.totalItem))
		tr.appendChild(this.getCell(item.totalAmount))
		tr.appendChild(this.getLinkCell(item.id.code, baseUrl))
		return tr
	}

	getLinkCell(data, baseUrl) {
		const icon = document.createElement('i')
		icon.classList.add('bi-arrow-right')
		
		const link = document.createElement('a')
		link.href = `${baseUrl}/${data}`
		link.appendChild(icon)
		
		const td = document.createElement('td')
		td.appendChild(link)
		td.classList.add('text-center')
		
		return td
	}

	getCell(data) {
		const td = document.createElement('td')
		
		if(typeof data === 'number') {
			td.classList.add('text-end')
		}
		
		const value = typeof data === 'number' ? data.toLocaleString() : data
		
		td.appendChild(document.createTextNode(value))
		return td
	}

	loadPagination(pageInfo) {
		console.log(pageInfo)
		
		const purchaseTotalPages = document.getElementById('purchaseTotalPages')
		const purchaseCount = document.getElementById('purchaseCount')
		
		purchaseTotalPages.innerText = pageInfo.totalPages
		purchaseCount.innerText = pageInfo.count
		
		const pageLinks = document.getElementById('purchasePageLinks')
		Array.from(pageLinks.children).forEach(child => pageLinks.removeChild(child))
		
		if(pageInfo.totalPage > 1) {
			pageLinks.classList.add('ms-2')
		} 
		
		if(pageInfo.showFirst) {
			const icon = document.createElement('i')
			icon.classList.add('bi-arrow-left')

			const link = document.createElement('a')
			link.classList.add('btn', 'btn-outline-primary', 'ms-2')
			link.appendChild(icon)
			link.addEventListener('click', () => pageChange(0))
			pageLinks.appendChild(link)
		}
		
		if(pageInfo.links.length > 1) {
			for(let page of pageInfo.links) {
				const link = document.createElement('a')
				link.classList.add('btn', 'btn-outline-primary', 'ms-2')
				link.innerText = page + 1
				link.addEventListener('click', () => pageChange(page))
				pageLinks.appendChild(link)
			}
		}
		
		if(pageInfo.showLast) {
			const icon = document.createElement('i')
			icon.classList.add('bi-arrow-right')

			const link = document.createElement('a')
			link.classList.add('btn', 'btn-outline-primary', 'ms-2')
			link.appendChild(icon)
			link.addEventListener('click', () => pageChange(pageInfo.totalPages - 1))
			pageLinks.appendChild(link)
		}	
	}
}

