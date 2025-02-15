document.addEventListener('DOMContentLoaded', () => {
	const cartCount = document.getElementById('cartCount')
	const checkOutMenu = document.getElementById('checkOutMenu')
	
	if(cartCount && checkOutMenu) {
		
		Array.from(document.getElementsByClassName('add-to-cart')).forEach(link => {
			link.addEventListener('click', async (event) => {
				
				event.preventDefault()
				const url = link.getAttribute('href')
				
				const response = await fetch(url, {method: 'GET'})
				const result = await response.json()
				console.log(result)
				
				if(result) {
					console.log(`Cart Count value : ${result}`)
					cartCount.innerText = result
					checkOutMenu.classList.remove('d-none')
				}
			})
		})
	}
})