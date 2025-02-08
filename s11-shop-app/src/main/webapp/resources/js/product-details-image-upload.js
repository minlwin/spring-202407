document.addEventListener('DOMContentLoaded', () => {
	
	const imageUploadBtn = document.getElementById('imageUploadBtn')
	const imageUploadForm = document.getElementById('imageUploadForm')
	const imageInput = document.getElementById('imageInput')
	
	if(imageInput && imageUploadBtn && imageUploadForm) {
		imageUploadBtn.addEventListener('click', () => imageInput.click())
		imageInput.addEventListener('change', () => imageUploadForm.submit())
	}
})