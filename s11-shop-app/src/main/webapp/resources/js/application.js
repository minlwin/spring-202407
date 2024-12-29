document.addEventListener('DOMContentLoaded', () => {
	
	const signOutMenu = document.getElementById('signOutMenu')
	const signOutForm = document.getElementById('signOutForm')
	
	if(signOutForm && signOutMenu) {
		signOutMenu.addEventListener('click', () => signOutForm.submit())
	}
})