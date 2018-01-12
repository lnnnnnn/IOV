function reviseOrder (id) {
	console.log(id);
	           var postUrl = plus.storage.getItem("serverUrl") + "ReviseOrderServlet"+"?id="+id;
	           
	           xhr(postUrl,false,function  (responseText) {
	           	 console.log(responseText);
	           },
	           function  () {
	           	console.log("updateStatusError");
	           })
        }

function payOrder (orderId,money) {
	//alert("in")
		var pays={};
		
		/*var self=plus.webview.currentWebview();
			var id= self.id;
			var money=self.money;*/
			var channel;
			plus.payment.getChannels(function(channels){
				for(var i in channels){
					var channel=channels[i];
					if(channel.id=='qhpay'||channel.id=='qihoo'){	// 过滤掉不支持的支付通道：暂不支持360相关支付
						continue;
					}
					pays[channel.id]=channel;
					console.log(channel.id);
					checkServices(channel);
				}
				pay(channels[0].id,money);	
			},function(e){
				outLine("获取支付通道失败："+e.message);
			});
	
		function checkServices(channel){
			if(!channel.serviceReady){
				var txt=null;
				switch(channel.id){
					case "alipay":
					txt="检测到系统未安装“支付宝快捷支付服务”，无法完成支付操作，是否立即安装？";
					break;
					default:
					txt="系统未安装“"+channel.description+"”服务，无法完成支付，是否立即安装？";
					break;
				}
				plus.nativeUI.confirm(txt,function(e){
					if(e.index==0){
						channel.installService();
					}
				},channel.description);
			}
		}
		var PAYSERVER='http://demo.dcloud.net.cn/payment/?payid=';
		// 支付
		function pay(id,amount){
			var url=PAYSERVER;
			if(id=='alipay'||id=='wxpay'){
				url+=id;
			}else{
				plus.nativeUI.alert("当前环境不支持此支付通道！",null,"支付");
				return;
			}
			var appid=plus.runtime.appid;
			if(navigator.userAgent.indexOf('StreamApp')>=0){ 
				appid='Stream';
			}
			url+='&appid='+appid+'&total=';
			
			plus.nativeUI.showWaiting();
			// 请求支付订单
			//var amount = 0.01;
			var xhr=new XMLHttpRequest();
			xhr.onreadystatechange=function(){
				switch(xhr.readyState){
					case 4:
					plus.nativeUI.closeWaiting();
					if(xhr.status==200){
						
						var order=xhr.responseText;
						plus.payment.request(pays[id],order,function(result){
							plus.nativeUI.alert("支付成功：感谢你的支持，我们会继续努力完善产品。",function(){
//								reviseOrder(orderId);
//								//plus.webview.currentWebview().close();
							location.reload();
							});
						},function(e){
							console.log("["+e.code+"]："+e.message);
							plus.nativeUI.alert("更多错误信息请参考支付(Payment)规范文档：http://www.html5plus.org/#specification#/specification/Payment.html",null,"支付失败："+e.code);
							plus.webview.currentWebview().close();
						});
					}else{
						console.log( xhr.status );
						plus.nativeUI.alert("获取订单信息失败！",null);
						plus.webview.currentWebview().close();
					}
					break;
					default:
					break;
				}
			}
			xhr.open('GET',url+amount);
			console.log("请求支付订单："+url+amount);
			xhr.send();
			/*var opener=plus.webview.currentWebview().opener();
			mui.fire(opener,'reload');*/
		}
		}

