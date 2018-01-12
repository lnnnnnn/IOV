musicJson=[
{url:"/audio/BlueDucks_FourFlossFiveSix.mp3"},
{url:"/audio/Depapepe.mp3"},
{url:"/audio/Diamonds.mp3"},
{url:"/audio/With An Orchid.mp3"}
]


mui.init();
var player;
var musicList=document.getElementsByClassName("mui-table-view-cell");
var length=musicList.length;
mui.plusReady(ready);
function ready(){
	console.log(length);
	playMusic(1,musicJson[1].url);
	for(var i=0;i<length;i++){
		(function(i){
			console.log("i="+i)
			musicList[i].addEventListener('tap',function(){
				playMusic(i,musicJson[i].url);
			},false);
		})(i)
	}
	function playMusic(index,musicURL){
		if(player){
			player.stop()
		}
		
		console.log(index)
		if(musicURL){
			player=new plus.audio.createPlayer(musicURL);
			console.log(musicURL)
		}else{
			if(index<(length-1)){
				player=new plus.audio.createPlayer(musicJson[++index].url);
				console.log("next")
			}
			else{
				index=0;
				player=new plus.audio.createPlayer(musicJson[0].url);
				console.log('replay')
			}
		}
		console.log('++index='+index)
		player.play(function(){
			playMusic(index);
		},function(){});
	}
	/*function playNext(){
		console.log("next")
		playMusic();
	}*/
	
}
