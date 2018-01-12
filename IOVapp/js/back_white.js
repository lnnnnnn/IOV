document.querySelector('.iback').onclick = function() {
//alert("back");
plus.navigator.setStatusBarBackground("rgba(249, 94, 21, 0))");
	plus.webview.hide(plus.webview.currentWebview(), "slide-out-right");
}