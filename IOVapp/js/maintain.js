var responseText={
	"data": [{
		"brand": "奥迪",
		"classno": "140101596",
		"engineP": "好",
		"engineno": "64090329",
		"gas_percent": 50,
		"hphm": "浙CEV583",
		"id": 1,
		"level": "4座",
		"lightS": "好",
		"loaded": true,
		"mileage": 12,
		"sid": "001",
		"storeProcesses": {
			"search": "select * from {tablename} where username={username}",
			"queryByHphm": "select *from {tablename} where hphm={hphm}",
			"getById": "select * from {tableName} where id={id}",
			"updateByHphm": "update {tablename} set symbol={symbol},type={type},classno={classno},engineno={engineno},level={level},mileage={mileage},gas_percent={gas_percent},engineP={engineP},transmissionP={transmissionP},lightS={lightS} where hphm={hphm} and username={username}"
		},
		"symbol": "img/flag/flag_baoma.png",
		"tableName": "carNet",
		"transmissionP": "好",
		"type": "A4L",
		"username": "zzj"
	}, {
		"brand": "宝马",
		"classno": "6072005235",
		"engineP": "异常",
		"engineno": "02033933",
		"gas_percent": 0,
		"hphm": "浙CEF155",
		"id": 2,
		"level": "",
		"lightS": "坏",
		"loaded": true,
		"mileage": 1500000,
		"sid": "002",
		"storeProcesses": {
			"search": "select * from {tablename} where username={username}",
			"queryByHphm": "select *from {tablename} where hphm={hphm}",
			"getById": "select * from {tableName} where id={id}",
			"updateByHphm": "update {tablename} set symbol={symbol},type={type},classno={classno},engineno={engineno},level={level},mileage={mileage},gas_percent={gas_percent},engineP={engineP},transmissionP={transmissionP},lightS={lightS} where hphm={hphm} and username={username}"
		},
		"symbol": "img/flag/flag_aodi.png",
		"tableName": "carNet",
		"transmissionP": "异常",
		"type": "3系",
		"username": "zzj"
	}]
};
console.log("js");


function transNull (str) {
console.log("str:"+str);
	return (str==""||str==0)?"无数据":str;
	//return str;
}
function getInfo (car) {
	var carInfo="发动机号:"+car.engineno+'</br>'+
	     "车身级别:"+transNull(car.level)+'</br>'+
	     "里程数:"+transNull(car.mileage)+'</br>'+
	     "汽油量(%):"+transNull(car.gas_percent)+'</br>'+
	     "发动机性能:"+transNull(car.engineP)+'</br>'+"变速器性能:"+transNull(car.transmissionP)+'</br>'+"车灯:"+transNull(car.lightS)+'</br>';
	     
	     return carInfo;
}

function rewritePopover (cars) {
	
}
function getCarsSuc (cars) {
	

//var cars=responseText.data;
/*var info=getInfo(cars[0]);
console.log(info);
document.querySelector("#middlePopover").innerHTML=info;*/


var ul=document.querySelector('.mui-table-view');
ul.innerHTML="";
document.querySelector('.popovers').innerHTML="";
for(var i=0;i<cars.length;i++){
	var li=document.createElement('li');
	li.innerHTML='<img class="mui-media-object mui-pull-left" src="'+cars[i].symbol+'">'+
					'<div class="mui-media-object mui-pull-right " >'+
						'<span  class="mui-icon iconfont icon-saoma"></span>'+
					
						"<span class='saomaText'>扫码维护</span>"+
					
					'</div>'+	
					'<div  class="mui-media-object mui-pull-right " >'+
						'<a href="#middlePopover'+i+'">'+
							"<span  class='mui-icon iconfont icon-xiangqing'></span>"+
						'</a>'+
					
						"<span class='saomaText'>查看详情</span>"+
					
					'</div>'+	
					'<div class="mui-media-body">'+cars[i].brand+cars[i].type+
						
						"<p class='mui-ellipsis'>"+cars[i].hphm+"</p>"+
					"</div>";
		li.className="mui-table-view-cell mui-media";		
		ul.appendChild(li);
		
		var div=document.createElement('div');
		div.id="middlePopover"+i;div.className="mui-popover";
		div.innerHTML=getInfo(cars[i]);
		
		
		document.querySelector('.popovers').appendChild(div);
}
var icons=document.querySelectorAll(".icon-saoma");
for(var i=0;i<icons.length;i++){
	icons[i].addEventListener('click',function  () {
	//alert("in");
	mui.openWindow({url:'barcode_scan.html',id:'barcode_scan.html'});
},false)
}

}

var carArr=[];
if (window.plus) {
				plusReady();
			} else {
				document.addEventListener("plusready", plusReady, false);
			}
			
			
function plusReady () {
	//plus.storage.setItem("username","zzj");
    var username=plus.storage.getItem("username");
        var postUrl = plus.storage.getItem("serverUrl") + "GetCarsServlet"+"?username="+username;
		console.log(username );			
	//alert("ready");
	xhr(postUrl,true,getCarsSuc,function  () {
	plus.nativeUI.toast('服务器无法联系，请检查网络链接是否正常！');
	})
	/*var self=plus.webview.currentWebview();
	carArr=self.cars;
	//alert(carArr);
	getCarsSuc(carArr);*/
}

function xhr(url, isJson, sucCallback, errCallback){
	//alert("in");
	var xhr = null; 
	try{
		
		xhr = new plus.net.XMLHttpRequest();	
		xhr.timeout = 20000;
		xhr.onreadystatechange = function () {
			switch ( xhr.readyState ) {
				case 0:
					//alert( "xhr请求已初始化" );
				break;
				case 1:
					//alert( "xhr请求已打开" );
				break;
				case 2:
					//alert( "xhr请求已发送" );
				break;
				case 3:
					//alert( "xhr请求已响应");
					break;
				case 4:
					if ( xhr.status == 200 ) {
						//alert(xhr.responseText);
						
						console.log(xhr.responseText);
						if(isJson)
							sucCallback( (JSON.parse( xhr.responseText )).data );
						else 
							sucCallback(  xhr.responseText );
					} else {
						errCallback();
					}
					break;
				default :
					break;
			}
		}
		console.log(url);
		xhr.open("POST", url);
		xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		xhr.send();
			
	}catch(e){
		console.log(e);
		if( xhr != null){
			xhr.abort();
			xhr = null;
		}
	}
	
}

function requestAgain () {
	  var username=plus.storage.getItem("username");
        var postUrl = plus.storage.getItem("serverUrl") + "GetCarsServlet"+"?username="+username;
		console.log(username );			
	//alert("ready");
	xhr(postUrl,true,getCarsSuc,function  () {
	plus.nativeUI.toast('服务器无法联系，请检查网络链接是否正常！');
});
}



