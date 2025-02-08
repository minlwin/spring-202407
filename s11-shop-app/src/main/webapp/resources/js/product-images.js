document.addEventListener('DOMContentLoaded', () => {
	
	const photos = document.getElementsByClassName('image-control-image')
	const coverPhoto = document.getElementById('coverPhoto')
	
	if(photos && coverPhoto) {
		Array.from(photos).forEach(photo => {
			photo.addEventListener('click', () => coverPhoto.src = photo.src)
		})
	}
})