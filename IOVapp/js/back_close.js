document.querySelector('.iback').onclick = function() {
//alert("back");
plus.navigator.setStatusBarBackground("#72B6FB");
	plus.webview.close(plus.webview.currentWebview(), "slide-out-right");
}