document.addEventListener('DOMContentLoaded', () => {
	
	const searchForm = document.getElementById('searchForm')
	const createForm = document.getElementById('createForm')
	const searchBtn = document.getElementById('searchBtn')
	const createBtn = document.getElementById('createBtn')
	const modeInput = document.getElementById('modeInput')
	const errors = document.getElementById('errors')
	
	searchBtn.addEventListener('click', () => {
		if(errors) {
			errors.classList.add('d-none')
		}

		searchForm.classList.toggle('d-none')
		createForm.classList.toggle('d-none')
		createBtn.classList.toggle('d-none')
		searchBtn.classList.toggle('d-none')
		
		modeInput.value = 'Search'		
	})
	
	createBtn.addEventListener('click', () => {

		if(errors) {
			errors.classList.add('d-none')
		}

		searchForm.classList.toggle('d-none')
		createForm.classList.toggle('d-none')
		createBtn.classList.toggle('d-none')
		searchBtn.classList.toggle('d-none')

		modeInput.value = 'Create'
	})	
})