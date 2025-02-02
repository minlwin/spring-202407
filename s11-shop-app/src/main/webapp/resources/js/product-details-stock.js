document.addEventListener('DOMContentLoaded', () => {
	
	const helper = new StockHelper
	const stockSizeSelect = document.getElementById('stockSizeSelect')
	stockSizeSelect.addEventListener('change', (e) => {
		helper.sizeChange(e.target.value)
	})
	
	
	helper.search()
})

class StockHelper {

	pageChange(page) {
		const pageInput = document.getElementById('stockPage')
		pageInput.value = page
		this.search()
	}

	sizeChange(size) {
		const pageInput = document.getElementById('stockPage')
		const sizeInput = document.getElementById('stockSize')
		
		pageInput.value = 0
		sizeInput.value = size
		
		this.search()	
	}


	async search() {
		const searchForm = document.getElementById('stockSearchForm')
		const pageInput = document.getElementById('stockPage')
		const sizeInput = document.getElementById('stockSize')
		
		const url = searchForm.action 
		const params = new URLSearchParams
		params.append('page', pageInput.value)
		params.append('size', sizeInput.value)
		
		console.log(`URL : ${url}?${params.toString()}`)
		
		try {
			const response = await fetch(`${url}?${params.toString()}`, {
					method : 'GET',
					headers : {
						'Content-Type' : 'application/json'
					}
				}
			)
			
			if(!response.ok) {
				throw new Error('Featch Error : stock for Supplier')
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
		const stockList = document.getElementById('stockList')
		const baseUrl = stockList.dataset.details
		
		Array.from(stockList.children).forEach(child => stockList.removeChild(child))
		
		contents.forEach(item => {
			stockList.appendChild(this.getRow(item, baseUrl))
		})
	}

	getRow(item, baseUrl) {
		const tr = document.createElement('tr')
		tr.appendChild(this.getCell(item.id.issueAt))
		tr.appendChild(this.getCell(item.id.action))
		tr.appendChild(this.getCell(item.target))
		tr.appendChild(this.getCell(item.beforeStock))
		tr.appendChild(this.getCell(item.quantity))
		tr.appendChild(this.getCell(item.stock))
		tr.appendChild(this.getLinkCell(item.id.code, item.id.action === 'Buy' ? `${baseUrl}/purchase` : `${baseUrl}/invoice`))
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
		
		const stockTotalPages = document.getElementById('stockTotalPages')
		const stockCount = document.getElementById('stockCount')
		
		stockTotalPages.innerText = pageInfo.totalPages
		stockCount.innerText = pageInfo.count
		
		const pageLinks = document.getElementById('stockPageLinks')
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