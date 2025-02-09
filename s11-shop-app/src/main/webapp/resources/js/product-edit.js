document.addEventListener('DOMContentLoaded', () => {
	
	const featuresHolder = document.getElementById('featuresHolder')
	const addFeatureBtn = document.getElementById('addFeatureBtn')
	
	const deleteRow = (index) => {
		const deleteInput = document.getElementById(`features${index}.deleted`)

		const row = deleteInput.parentElement
		
		if(deleteInput.dataset['server']) {
			deleteInput.setAttribute('value', 'true')
			row.classList.add('d-none')
		} else {
			featuresHolder.removeChild(row)			
		}
		
		const array = Array.from(featuresHolder.children)
			.filter(element => !element.classList.contains('d-none'))
			
		if(array.length > 0) {
			array[0].classList.toggle('mt-2')
		}
	
	}
	
	const createRow = (index) => {
		const row = document.createElement('div')
		row.classList.add('row')
		row.classList.add('mt-2')
		
		const deleteInput = document.createElement('input')
		deleteInput.setAttribute('type', 'hidden')
		deleteInput.setAttribute('value', 'false')
		deleteInput.setAttribute('id', `features${index}.deleted`)
		deleteInput.setAttribute('name', `features[${index}].deleted`)
		
		row.appendChild(deleteInput)
		
		const nameCol = document.createElement('div')
		nameCol.classList.add('col-4')
		const nameInput = document.createElement('input')
		nameInput.classList.add('form-control')

		nameInput.setAttribute('id', `features${index}.name`)
		nameInput.setAttribute('name', `features[${index}].name`)
		nameInput.setAttribute('type', 'text')
		nameInput.setAttribute('placeholder', 'Enter Name')
		nameCol.appendChild(nameInput)
		
		const featureCol = document.createElement('div')
		featureCol.classList.add('col')
		
		const featureInput = document.createElement('input')
		const inputGroup = document.createElement('div')
		inputGroup.classList.add('input-group')
		inputGroup.appendChild(featureInput)
		
		const deleteIcon = document.createElement('i')
		deleteIcon.classList.add('bi-trash')

		const deleteBtn = document.createElement('button')
		deleteBtn.setAttribute('type', 'button')

		deleteBtn.classList.add('btn', 'btn-outline-primary', 'input-group-text', 'deleteBtn')
		deleteBtn.addEventListener('click', () => deleteRow(index))
		
		deleteBtn.appendChild(deleteIcon)
		inputGroup.appendChild(deleteBtn)
		
		featureInput.classList.add('form-control')

		featureInput.setAttribute('id', `features${index}.feature`)
		featureInput.setAttribute('name', `features[${index}].feature`)
		featureInput.setAttribute('type', 'text')
		featureInput.setAttribute('placeholder', 'Enter Feature')
		featureCol.appendChild(inputGroup)
		
		row.appendChild(nameCol)
		row.appendChild(featureCol)
		return row
	}
	
	if(featuresHolder && addFeatureBtn) {
		addFeatureBtn.addEventListener('click', () => {
			const row = createRow(featuresHolder.children.length)
			featuresHolder.appendChild(row)
		})
	}
	
	Array.from(document.getElementsByClassName('deleteBtn')).forEach((btn, index) => {
		btn.addEventListener('click', () => deleteRow(index))
	})
})