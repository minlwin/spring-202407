document.addEventListener('DOMContentLoaded', () => {
	
	const searchForm = document.getElementById('searchForm')
	const searchBtn = document.getElementById('searchBtn')
	const sizeInput = document.getElementById('sizeInput')
	const pageInput = document.getElementById('pageInput')

	const sizeSelect = document.getElementById('sizeSelect')
	
	if(searchForm && sizeInput && pageInput && sizeSelect && searchBtn) {
		
		sizeSelect.addEventListener('change', (event) => {
			if(event.target.value) {
				sizeInput.value = event.target.value
				pageInput.value = 0
				searchForm.submit()
			}
		})
		
		searchBtn.addEventListener('click', () => {
			pageInput.value = 0
			searchForm.submit()
		})
		
		Array.from(document.getElementsByClassName('pageLink')).forEach(link => {
			link.addEventListener('click', () => {
				var pageNumber = link.dataset.page
				if(pageNumber !== undefined && pageNumber >= 0) {
					pageInput.value = pageNumber
					searchForm.submit()
				}
			})
		})
	}
})