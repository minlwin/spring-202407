document.addEventListener('DOMContentLoaded', () => {
	var cancelBtn = document.getElementById('cancelBtn')
	
	var dialog = new bootstrap.Modal('#cancelDialog')
	
	cancelBtn.addEventListener('click', () => dialog.show())
})