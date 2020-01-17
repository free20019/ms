var zdcljkmap;
var navg;
var cljkgz=(function ($) {
	//所有车辆数
	var clgzvehilist;
	//车辆经纬度数组
	var vhicsz=new Array();
	
	var path=[];
	var first = true;
	var allstime=null,
	alletime=null,
	st= null;
	
	
	$(function () {
		/*$('.tabHeaderBar[type=tab] .tabItem:not([type=active])').on('click', function () {
			$(this).addClass('active').siblings('.active').removeClass('active');
		});*/
		$('.scrollbar-macosx').scrollbar();
		$('.searchBar input[type=text]').on({
			focus: function () {
				$(this).parent().attr('state', 'focus');
			},
			blur: function () {
				$(this).parent().removeAttr('state')
			}
		});
		
		zdcljkmap = new AMap.Map('cljkgzMap', {
			zoom:14,
			center: new AMap.LngLat(120.153576,30.287459)
		});
		
		jqxhr=$.ajax({
     		type: "POST",
 	        url:"../../claq/qyjk",
 	        data:{},
 	        dataType: 'json',
 			timeout : 3600000,
 			success:function(data){
 				console.log(data)
 				clgzvehilist = data.vehilist;
 				
 				$("#cphm_value").on("keyup",function(){
 					var vehi = this.value;
 					if(this.value.length >= 3){
 						var text = "";
 						var count = 12;
 						for(var i=0;i<=clgzvehilist.length;i++){
 							if(clgzvehilist[i] != undefined && clgzvehilist[i].vehino.indexOf(vehi) != -1){
 								text +='<li class="listItem">'+
 											'<span class="col gz" type="licensePlate">'+clgzvehilist[i].vehino+'</span>'+
// 											'<span class="col btn btn-default btn-xs gz" type="button">跟踪</span>'+
 											'<span class="col btn btn-primary btn-xs gj" type="button">轨迹回放</span>'+
 										'</li>';
 								count--;
 							}else if(count <= 0){
 								break;
 							}
 						}
 						$('#vehi_list').html(text);
 						jump();
 						//绑定功能 更多
 						$('#get_more').on('click',function(){
 							for(var i=0;i<=clgzvehilist.length;i++){
 	 							if(clgzvehilist[i] != undefined && clgzvehilist[i].vehino.indexOf(vehi) != -1){
 	 								text +='<li class="listItem">'+
 	 											'<span class="col gz" type="licensePlate">'+clgzvehilist[i].vehino+'</span>'+
// 	 											'<span class="col btn btn-default btn-xs gz" type="button">跟踪</span>'+
 	 											'<span class="col btn btn-primary btn-xs gj" type="button">轨迹回放</span>'+
 	 										'</li>';
 	 							}
 	 						}
 							$('#vehi_list').html(text);
 							jump();
 						});
 					
 					}
 				});
 			}
		});
		//绑定-跟踪 跳转
		function jump(){
			//跟踪
			$('.gz').on('click',function(){
				layer.load(2);
				maker_vehi(zdcljkmap,$(this).html());
				first = true;
				layer.closeAll('loading');
			});
			//轨迹回放
			$('.gj').on('click',function(){
				var vehi = $($(this).prev()).html();
				console.log(vehi)
				var _parent = parent.$(window.parent.document);
				if (_parent.find('.ip-tabBarItem[data-name="gjhfgz"]').length > 0) {
					_parent.find('.ip-tabBarItem[data-name="gjhfgz"]').trigger('click');
					_parent.find('#gjhfgz').get(0).contentWindow.location.reload(true);
				}
				_parent.find('#defaultVehi').val(vehi);
				_parent.find('.ip-menuItem [data-skip="gjhfgz"]').trigger("click");
			});
		}
		
		//参数：第一次打点f  地图编号o  车牌号c 对象obj  状态s
		//first true判断是否第一次进入
		function xh(o,c,obj,s){
			//清空操作
			o.clearMap();
			path= [];
			if(st != null){
				clearTimeout(st);
			}
			var stime,etime;
			//第一次打点 first
			if(first){
				stime = new Date(new Date().getTime() - 1000 * 60 * 2).Format('yyyy-MM-dd hh:mm:ss');//前一分钟
				allstime = stime;
			}else{
				stime = allstime;
			}
			etime = new Date().Format('yyyy-MM-dd hh:mm:ss');
			
			jqxhr=$.ajax({
         		type: "POST",
     	        url:"../../claq/findGj",
     	        data:{
     				"stime" : stime,
     				"etime" : etime,
     				"vehino" : c,
     	        },
     	        async:false, 
     	        dataType: 'json',
     			timeout : 3600000,
         		success:function(json){
         			console.log(json)
         			if(json == null){
         				layer.msg("无定位数据");
         			}
         			if(json.data.length == 0){
         				marksingle(o,obj,s,1);
         			}else{
         				if(first){
             				var re = json.data[json.data.length-1];
             				//重新赋值最新的时间
             				allstime = formatYYYYMMDDHHMISS(re.SPEED_TIME);
             				//重新赋值first
             				first=false;
             				
             				var os = {};
             				os.compname = re.COMP_NAME||'';
             				os.simka = re.VEHI_SIM||'';
             				os.mdt_no = re.MDT_NO||'';
             				os.ownname = re.OWN_NAME||'';
             				os.owntel = obj.owntel||'';
             				os.longi = re.PX||'';
             				os.lati = re.PY||'';
             				os.vehino = c||'';
             				os.heading = re.DIRECTION||'';
             				os.speed = re.SPEED||'';
             				os.time = re.SPEED_TIME!==null?formatYYYYMMDDHHMISS(re.SPEED_TIME):'';
             				marksingle(o,os,s);
             			}else{
             				var res = json.data;
             				for(var i =0 ;i<res.length;i++){
             					var re = res[i];
             					if(i == res.length -1){
             						//终点
             						var os = {};
                     				os.compname = re.COMP_NAME||'';
                     				os.simka = re.VEHI_SIM||'';
                     				os.mdt_no = re.MDT_NO||'';
                     				os.ownname = re.OWN_NAME||'';
                     				os.owntel = obj.owntel||'';
                     				os.longi = re.PX||'';
                     				os.lati = re.PY||'';
                     				os.vehino = c||'';
                     				os.heading = re.DIRECTION||'';
                     				os.speed = re.SPEED||'';
                                    os.time = re.SPEED_TIME!==null?formatYYYYMMDDHHMISS(re.SPEED_TIME):'';
                     				
                     				marksingle(o,os,s);
             					}else{
             						//所有点
             						var os = {};
             						os.PX = re.PX;
                     				os.PY = re.PY;
                     				os.DIRECTION = re.DIRECTION;
                     				
                     				markotherpoint(o,os);
             					}
             					//绘制路线添加数组元素
             					path.push(new AMap.LngLat(re.PX,re.PY));
             				}
             				//路线
             				markroad(o,path);
             			}
         				st = setTimeout(function(){
         					xh(o,c,obj,s);
         				},30000);
         			}
         			
         		}
			});
		}
		
		//跟踪-定位
		function maker_vehi(o,c){
			for(var i=0;i<=clgzvehilist.length;i++){
				if(clgzvehilist[i] != undefined && clgzvehilist[i].vehino == c){
					var obj = clgzvehilist[i];
					var status;
					if (obj.lati == null || "" == obj.lati
							|| 0==obj.lati
							|| ""==obj.longi
							|| 0 ==obj.longi 
					) {
						status = "off";
					}else{
						if (obj.onoffstate=="1") {
							if (obj.carStatus=="0") {
								status = "empty";
							}else{
								status = "busy";
							}
						}else if (obj.onoffstate=="0") {
							status = "off";
						}
					}
					if(status == "off"){
						//单点打印  地图编号o 车辆对象obj
						marksingle(o,obj,status,2);
					}else{
						//不离线的车辆  开始循环轨迹  参数：  地图编号o  车牌号c   对象obj  状态s
						xh(o,c,obj,status);
					}
				}
			}
		}
		
		//单点打印 地图编号o  车辆对象obj  状态s
		function marksingle(o,obj,s,e){
//			console.log(obj)
			var icon,statue;
			if(s == "off"){
				icon = dirc("off",obj.heading);
				statue = "离线";
			}else if(s == "busy"){
				icon = dirc("busy",obj.heading);
				statue = "重车";
			}else if(s == "empty"){
				icon = dirc("empty",obj.heading);
				statue = "空车";
			}
			if(e == 1){
				obj.time = obj.dateTime.substring(0,19);
			}
			var marker = new AMap.Marker({
  			  	map:o,
			    icon:icon,
			    position:new AMap.LngLat(obj.longi,obj.lati),
			    offset:new AMap.Pixel(-8,-8), //相对于基点的偏移位置
			    draggable:false,
			    vehicle:obj
			});
			var inforWindow = new AMap.InfoWindow({
			    offset:new AMap.Pixel(0,0)
			});
			var txt = '<div class="mapInfo-body">'+
							'<div class="mapInfo-title">'+obj.vehino+'</div>'+
							'<div class="mapInfo-item" type="mapInfoItem" title="所属公司">'+obj.compname+'</div>'+
							'<div class="mapInfo-item" type="mapInfoItem" title="SIM卡">'+obj.simka+'</div>'+
							'<div class="mapInfo-item" type="mapInfoItem" title="终端号">'+obj.mdt_no+'</div>'+
							'<div class="mapInfo-item" type="mapInfoItem" title="车辆所属人">'+obj.ownname+'</div>'+
							'<div class="mapInfo-item" type="mapInfoItem" title="联系电话">'+obj.owntel+'</div>'+
							'<div class="mapInfo-item" type="mapInfoItem" title="经度">'+obj.longi+'</div>'+
							'<div class="mapInfo-item" type="mapInfoItem" title="纬度">'+obj.lati+'</div>'+
							'<div class="mapInfo-item" type="mapInfoItem" title="速度">'+obj.speed+'</div>'+
							'<div class="mapInfo-item" type="mapInfoItem" title="方向">'+fxzh(obj.heading)+'</div>'+
							'<div class="mapInfo-item" type="mapInfoItem" title="状态">'+statue+'</div>'+
							'<div class="mapInfo-item" type="mapInfoItem" title="GPS时间">'+obj.time+'</div>'+
						'</div>';
			inforWindow.setContent(txt);
			inforWindow.open(o,new AMap.LngLat(obj.longi,obj.lati));
			AMap.event.addListener(marker,"click",function(e){
				 inforWindow.open(o,marker.getPosition());
			});
			o.setZoomAndCenter(17,marker.getPosition());
		}
		
		//地图编号 o 打点对象obj
		function markotherpoint(o,obj){
			var marker = new AMap.Marker({
			    map:o,
			    position:new AMap.LngLat(obj.PX,obj.PY),
			    offset:new AMap.Pixel(-3,-7), //相对于基点的偏移位置
			    draggable:false,  //是否可拖动
			    icon:"../../resources/images/fx4.png",
			    angle:obj.DIRECTION-90
			});
		}
		
		//地图编号 o  所有点a
		function markroad(o,a){
		    //绘制轨迹
		    polyline = new AMap.Polyline({
		        map:o,
		        path:a,
		        strokeColor:"#282c34",//线颜色
		        strokeOpacity:1,//线透明度
		        strokeWeight:3,//线宽
		        strokeStyle:"solid"//线样式
		    });
		}
		
		//跟踪-定位
//		function maker_vehi(c){
//			zdcljkmap.clearMap();
//			for(var i=0;i<=clgzvehilist.length;i++){
//				if(clgzvehilist[i] != undefined && clgzvehilist[i].vehino == c){
//					var obj = clgzvehilist[i];
//					var icon = "";
//					if (obj.lati == null || "" == obj.lati
//								|| 0==obj.lati
//								|| ""==obj.longi
//								|| 0 ==obj.longi 
//						) {
//						icon = dirc("off",obj.heading);
//					}else{
//						if (obj.onoffstate=="1") {
//							if (obj.carStatus=="0") {
//								icon = dirc("empty",obj.heading);
//							}else{
//								icon = dirc("busy",obj.heading);
//							}
//						}else if (obj.onoffstate=="0") {
//							icon = dirc("off",obj.heading);
//						}
//					}
//					
//					console.log(obj)
//					var marker = new AMap.Marker({
//		  			  	map:zdcljkmap,
//					    icon:icon,
//					    position:new AMap.LngLat(obj.longi,obj.lati),
//					    offset:new AMap.Pixel(-8,-8), //相对于基点的偏移位置
//					    draggable:false,
//					    vehicle:obj
//					});
//					var inforWindow = new AMap.InfoWindow({
//					    offset:new AMap.Pixel(0,0)
//					});
//					var d = obj.dateTime==""?new Date().Format('yyyy-MM-dd hh:mm:ss'):obj.dateTime.substring(0,19);
//					var txt = '<div class="mapInfo-body">'+
//									'<div class="mapInfo-title">'+obj.vehino+'</div>'+
//									'<div class="mapInfo-item" type="mapInfoItem" title="所属公司">'+obj.compname+'</div>'+
//									'<div class="mapInfo-item" type="mapInfoItem" title="SIM卡">'+obj.simka+'</div>'+
//									'<div class="mapInfo-item" type="mapInfoItem" title="车辆所属人">'+obj.ownname+'</div>'+
//									'<div class="mapInfo-item" type="mapInfoItem" title="联系电话">'+obj.owntel+'</div>'+
//									'<div class="mapInfo-item" type="mapInfoItem" title="经度">'+obj.longi+'</div>'+
//									'<div class="mapInfo-item" type="mapInfoItem" title="纬度">'+obj.lati+'</div>'+
//									'<div class="mapInfo-item" type="mapInfoItem" title="GPS时间">'+d+'</div>'+
//									'<div class="mapInfo-item" type="mapInfoItem" title="速度">'+obj.speed+'</div>'+
//									'<div class="mapInfo-item" type="mapInfoItem" title="方向">'+fxzh(obj.heading)+'</div>'+
//								'</div>';
//					inforWindow.setContent(txt);
//					inforWindow.open(zdcljkmap,new AMap.LngLat(obj.longi,obj.lati));
//				}
//			}
//		}
	})
})(jQuery)
