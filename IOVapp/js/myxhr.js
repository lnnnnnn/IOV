function myxhr(param,servlet) {
					
					//var data = "data=" + jsonStr;
					var wd = plus.nativeUI.showWaiting();	// 显示一个等待的对话框  
					var postUrl = plus.storage.getItem("serverUrl") + servlet+"?"+param;
					console.log(postUrl);
					var request = new plus.net.XMLHttpRequest();
					//XMLHttpRequest();
					request.open("POST", postUrl);
					request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
					request.send();
					request.onreadystatechange = function() {
						if (request.readyState == 4) {
							if (request.status == 200) {
								wd.close(); // 调用成功，先关闭等待的对话框  
								
								console.log("query" + request.responseText);
								
							} else {
								alert("query" + "发生错误" + request.status);
							}
						}
					}
				}