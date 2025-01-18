document.addEventListener('DOMContentLoaded', () => {
	
	const addItem = document.getElementById('addItem')
	const removeItems = document.getElementsByClassName('removeItem')
	const itemsForm = document.getElementById('itemsForm')
	
	if(addItem && removeItems, itemsForm) {
		
		addItem.addEventListener('click', (e) => {
			e.preventDefault()
			itemsForm.action = addItem.href
			itemsForm.submit()
		})
		
		Array.from(removeItems).forEach(removeItem => {
			removeItem.addEventListener('click', (e) => {
				e.preventDefault()
				itemsForm.action = removeItem.href
				itemsForm.submit()
			})
		})
	}
	
})

const calculate = (index) => {
	const priceInput = document.getElementById(`p-${index}`)
	const qtyInput = document.getElementById(`q-${index}`)
	const total = document.getElementById(`t-${index}`)
	
	total.innerText = `${priceInput.value * qtyInput.value}`
}
