function buttonFunction() {
	var x = Math.floor((Math.random() * 2000) + 1);
	var y = Math.floor((Math.random() * 1000) + 1);
	document.getElementById("moveButton").setAttribute('style', 'top:' + x + 'px;left:' + y + 'px;');
}

function changeBackground(color) {
   document.body.style.background = color;
}


function getInput() {
	var inputValue = document.getElementById("input").value;
	changeBackground(inputValue);
}