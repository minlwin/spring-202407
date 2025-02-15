document.addEventListener('DOMContentLoaded', () => {
	const totalAmount = document.getElementById('totalAmount')
	const itemContainer = document.getElementById('itemContainer')
	const checkOutMenu = document.getElementById('checkOutMenu')
	const cartCount = document.getElementById('cartCount')
	
	if(totalAmount && itemContainer && checkOutMenu && cartCount) {
		Array.from(document.getElementsByClassName('addOrRemove')).forEach(link => link.addEventListener('click', async (event) => {
			event.preventDefault()
			
			const url = link.getAttribute('href')
			const response = await fetch(url, {method : 'GET'})
			const result = await response.json()
			
			console.log(result)
			
			cartCount.innerText = result.totalCount
			
			if(result.totalCount == 0) {
				checkOutMenu.classList.add('d-none')
			}
			
			totalAmount.innerText = result.totalAmount
			
			const itemIds = result.items.map(item => `item-${item.id}`)
			
			Array.from(itemContainer.children).forEach(item => {
				if(!itemIds.includes(item.id) && item.id != 'summaryRow') {
					itemContainer.removeChild(item)
				}
			})
			
			result.items.forEach(item => {
				const quantity = document.getElementById(`itemQuentity-${item.id}`)
				const total = document.getElementById(`itemTotal-${item.id}`)
				
				quantity.innerText = item.quantity
				total.innerText = item.total
			})
		}))
	}
})