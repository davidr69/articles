export default class Mobile {
	popout;
	handle;

	constructor() {
		this.popout = document.getElementById("popout");
		this.handle = document.getElementById("handle");

		this.popout.style.zIndex = 1;
		for(let item of document.querySelectorAll('a.menuItem')) {
			item.style.color = "#d0d0d0";
			item.style.fontSize = "10px";
		}
	}

	openMenu = () => {
		let counter = 1;
		this.handle.style.display = '';
		this.popout.style.left = '-300px';
		let interval = setInterval(() => {
			this.popout.style.marginLeft = counter + 'px';
			counter += 2;
			if (counter > 300) {
				clearInterval(interval);
				setTimeout(() => {
					this.#closeMenu();
				}, 7000);
			}
		}, 2);
	}

	#closeMenu = () => {
		let counter = 300;
		let interval = setInterval(() => {
			this.popout.style.marginLeft = counter + 'px';
			counter -= 2;
			if (counter <= 0) {
				clearInterval(interval);
				this.popout.style.marginLeft = '-300x';
				this.handle.style.display = 'inline';
			}
		}, 2);
	}
}
