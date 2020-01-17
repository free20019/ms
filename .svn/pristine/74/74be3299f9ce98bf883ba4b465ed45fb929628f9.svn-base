

function imagebigscale(bigImage,big,imagetool,close,leftrote,rightrote,bigger,smaller) {
	
	var a = document.getElementsByTagName("img");
	var bigImage = document.querySelector("#"+bigImage);
	var big = document.querySelector("#"+big);
	var toolbar = document.querySelector("#"+imagetool);
	var close = document.querySelector("#"+close);
	var leftrote = document.querySelector('#'+leftrote);
	var rightrote = document.querySelector('#'+rightrote);
	var bigger = document.querySelector('#'+bigger);
	var smaller = document.querySelector('#'+smaller);
	
	bigImage.style.display = 'none';
	var rote = 0;

	var scale = 1;
	var b = Array.prototype.slice.apply(a);
	b.forEach(function(item, index) {
		function showorhide(ev) {
			big.src = item.src;
			bigImage.style.width = big.width = item.width;
			bigImage.style.height = big.height = item.height;
			bigImage.style.display = 'block';
		}
		 if(window.addEventListener){ 
			 item.addEventListener('click', showorhide, false);

	     } else {
	    	 item.attachEvent('onclick',  showorhide);
	     }
		
	});

	big.onmousedown = function(ev) {
		var ev = ev || window.event;
		var disX = ev.clientX - bigImage.offsetLeft;
		var disY = ev.clientY - bigImage.offsetTop;

		document.onmousemove = function(ev) {
			var ev = ev || window.event;
			bigImage.style.left = ev.clientX - disX　 + 'px';
			bigImage.style.top = ev.clientY - disY　 + 'px';
			toolbar.style.left = ev.clientX - disX　 + 'px';
			toolbar.style.top = ev.clientY - disY　 + 'px';
			
			return false;
		}
		document.onmouseup = function(ev) {
			var ev = ev || window.event;
			document.onmousemove = null;
			document.onmouseup = null;
		}

		return false;

	}

	close.onclick = function() {
		bigImage.style.display = 'none';
		big.src = "";
	}

	leftrote.onclick = function() {
		rote += 90
		big.style.transform = "rotate(" + rote + "deg) scale(" + scale + ")";

	}
	rightrote.onclick = function() {
		rote -= 90
		big.style.transform = "rotate(" + rote + "deg) scale(" + scale + ")";

	}

	bigger.onclick = function() {
		scale += 0.4;
		big.style.transform = "rotate(" + rote + "deg) scale(" + scale + ")";
	}
	smaller.onclick = function() {
		scale -= 0.4;
		big.style.transform = "rotate(" + rote + "deg) scale(" + scale + ")";
	}
	


};