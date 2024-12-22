document.addEventListener('DOMContentLoaded', () => {
	const logoutForm = document.getElementById('logoutForm')
	const logoutMenu = document.getElementById('logoutMenu')
	
	if(logoutForm && logoutMenu) {
		logoutMenu.addEventListener('click', () => logoutForm.submit())
	}
	
	const statusUpdateForm = document.getElementById('statusUpdateForm')
	const statusUpdateInput = document.getElementById('statusUpdateInput')
	
	if(statusUpdateForm && statusUpdateInput) {
		const statusUpdateLinks = Array.from(document.getElementsByClassName('statusUpdateLink'))
		if(statusUpdateLinks) {
			statusUpdateLinks.forEach(link => {
				link.addEventListener('click', () => {
					statusUpdateInput.value = link.id
					statusUpdateForm.submit()
				})
			})
		}
	}
})