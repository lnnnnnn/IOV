/*
 * 获取服务器地址
 * 
 */

// 扩展API加载完毕后调用onPlusReady回调函数
document.addEventListener("plusready", onPlusReady, false);
// 扩展API加载完毕，现在可以正常调用扩展API
function onPlusReady() {

	plus.io.resolveLocalFileSystemURL(
		"_www/config.json",
		function(entry) {
			var reader = null;
			entry.file(function(file) {
				reader = new plus.io.FileReader();
				reader.readAsText(file);
				reader.onloadend = function(evt) {
					var j = JSON.parse(evt.target.result);
					//	alert("filesys"+j.serverUrl);
					plus.storage.setItem("serverUrl", j.serverUrl);
					if (window.localStorage) {
						localStorage.record_route_num = j.record_route_num;
					}

					//plus.android.runtimeMainActivity().setRemoteServerUrl( j.serverUrl );

				}
			});
		},
		function(e) {
			console.log("Resolve file URL failed: " + e.message);
		}
	);
}

/**
 * localstorage存储记录条数 注意放在整个app入口
 */
/*if (window.localStorage) {
	localStorage.record_route_num = 0;
}*/
//plus.storage.setItem("username","");