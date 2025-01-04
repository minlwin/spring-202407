document.addEventListener('DOMContentLoaded', () => {
	
	const uploadBtn = document.getElementById('uploadBtn')
	const uploadForm = document.getElementById('uploadForm')
	const uploadInput = document.getElementById('uploadInput')
	
	if(uploadBtn && uploadForm && uploadInput) {
		uploadBtn.addEventListener('click', () => uploadInput.click())
		uploadInput.addEventListener('change', () => uploadForm.submit())
	}
	
})