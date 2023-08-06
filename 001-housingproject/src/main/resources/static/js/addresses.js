document.addEventListener('DOMContentLoaded', () => {
	const controls = document.getElementsByClassName('navBtn')
	const targetViews = document.getElementsByClassName('displayTarget')
	if(controls && targetViews) {
		Array.from(controls).forEach(control => {
			control.addEventListener('click', () => {
				Array.from(controls).forEach(btn => {
					btn.classList.remove('btn-primary')				
					btn.classList.remove('btn-outline-primary')		
					
					if(btn === control) {
						btn.classList.add('btn-primary')
					} else {
						btn.classList.add('btn-outline-primary')
					}	
				})
				Array.from(targetViews).forEach(view => {
					view.classList.remove('d-none')
					if(view.id != control.dataset.target) {
						view.classList.add('d-none')
					}
				})				
			})
		})
	}
})