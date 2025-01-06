document.addEventListener('DOMContentLoaded', () => {
	
	// For Upload
	const uploadBtn = document.getElementById('uploadBtn')
	const uploadForm = document.getElementById('uploadForm')
	const uploadInput = document.getElementById('uploadInput')
	
	if(uploadBtn && uploadForm && uploadInput) {
		uploadBtn.addEventListener('click', () => uploadInput.click())
		uploadInput.addEventListener('change', () => uploadForm.submit())
	}
	
	// For Edit Modal Dialog
	const editDialog = document.getElementById('editDialog')
	const modalDialog = new bootstrap.Modal(editDialog)
	const dialogTitle = document.getElementById('dialogTitle')
	const nameInput = document.getElementById('nameInput')
	const idInput = document.getElementById('idInput')
	const dialogMessage = document.getElementById('dialogMessage')
	const searchForm = document.getElementById('searchForm')
	
	const addNewBtn = document.getElementById('addNewBtn')
	
	if(addNewBtn) {
		addNewBtn.addEventListener('click', () => {
			dialogTitle.innerText = 'Add New Category'
			dialogMessage.classList.add('d-none')
			
			nameInput.value = ''
			idInput.value = ''
			
			modalDialog.show()
		})
	}
	
	const closeBtn = document.getElementById('closeBtn') 
	
	if(closeBtn) {
		closeBtn.addEventListener('click', () => modalDialog.hide())
	}
	
	// Edit Buttons
	const editButtons = document.getElementsByClassName('editBtn')
	const array = Array.from(editButtons)
	array.forEach(btn => btn.addEventListener('click', () => {
		dialogTitle.innerText = 'Edit Category'
		dialogMessage.classList.add('d-none')

		nameInput.value = btn.dataset.name
		idInput.value = btn.dataset.id
		
		modalDialog.show()
	}))

	const editForm = document.getElementById('editForm')
	
	editForm.addEventListener('submit', async (event) => {
		event.preventDefault()
		
		const formData = new FormData(editForm)
		
		const response = await fetch(editForm.action, {
			method : 'POST',
			body: formData
		})
		
		const result = await response.json()
		
		if(result.messages) {
			// Has Error
			result.messages.forEach(message => {
				const messageNode = document.createElement('div')
				messageNode.innerText = message
				dialogMessage.appendChild(messageNode)
			})
			dialogMessage.classList.remove('d-none')
		} else {
			// Success	
			searchForm.submit()		
		}
	})
})