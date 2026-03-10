export default class Common {
	constructor() {
		this.resizeImages();
		window.addEventListener("resize", this.resizeImages);
	}

	resizeImages = () => {
		const flex = document.body.querySelectorAll('img#flex');
		for(let item of flex) {
			item.style.width = (window.innerWidth - 40) + 'px';
		}
	}
}
