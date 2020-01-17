var zlcljk = (function($) {
	var zlcljk_map;
	var dataCar = ['浙A12345', '浙A12346', '浙A12347', '浙A12348', '浙A12349'];
	$(function () {
		
		$('#zlcljk-datetimeStart').datetimepicker(datetimeDefaultOption);
		$('#zlcljk-datetimeStart').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));
		
		$('#zlcljk-vehicleOperateStatus').select2({
			language: 'zh-CN',
			width: '160',
			minimumResultsForSearch: -1,
			data: [
				{id: '0', text: '不限'},
				{id: '1', text: '载客'},
				{id: '2', text: '空载'}
			]
		});
		titlePanelScaling('#zlcljkPanel', {width: 485});
		$('.scrollbar-macosx').scrollbar();
		zlcljk_map = new AMap.Map('zlcljkMap', {
			zoom:14,
			center: new AMap.LngLat(120.153576,30.287459)
		});
		
		//在地图中添加MouseTool插件
	    var mouseTool = new AMap.MouseTool(zlcljk_map);
	    
    	AMap.event.addDomListener(document.getElementById('zlcljk_dw'), 'click', function() {
    		zlcljk_map.clearMap();
 	        mouseTool.polygon();
 	    }, false);
    	
    	$('#zlcljk_dc').on("click",function(){
    		var area=[];
    		if(zlcljk_map.getAllOverlays().length == 0){
    			alert("请先定位！")
    			return;
    		}
			var obj = zlcljk_map.getAllOverlays()[0].Qi.path;
			for(var i=0;i<obj.length;i++){
				var a = {};
				a.lng = obj[i].lng;
				a.lat = obj[i].lat;
				area.push(a);
			}
			url = "../../claq/cxVehidc?time="+$('#zlcljk-datetimeStart').val()+
									"&between="+$('#between_time').val()+
									"&type="+$('#zlcljk-vehicleOperateStatus').val()+
							        "&speed="+$('#zlcljk_speed').val()+
							        "&area="+JSON.stringify(area), window.open(url);
    	});
    	
    	
		
		$('#zlcljk_cx').on("click",function(){
			var area=[];
			if(zlcljk_map.getAllOverlays().length == 0){
				alert("请先定位！")
				return;
			}
			var obj = zlcljk_map.getAllOverlays()[0].Qi.path;
			for(var i=0;i<obj.length;i++){
				var a = {};
				a.lng = obj[i].lng;
				a.lat = obj[i].lat;
				area.push(a);
			}
			
			jqxhr=$.ajax({
	     		type: "POST",
	 	        url:"../../claq/cxVehi",
	 	        data:{"time":$('#zlcljk-datetimeStart').val(),
	 	        	  "between":$('#between_time').val(),
	 	        	  "type":$('#zlcljk-vehicleOperateStatus').val(),
	 	        	  "speed":$('#zlcljk_speed').val(),
	 	        	  "area":JSON.stringify(area)
	 	        },
	 	        dataType: 'json',
	 			timeout : 3600000,
	 			success:function(data){
	 				console.log(data)
	 				var text = "";
	 				if(data.msg == "success"){
	 					for(var i=0;i<data.data.length;i++){
	 						text += '<li class="listItem">'+
										'<span class="item-col" type="vehicleNum">'+data.data[i].vehino+'</span>'+
										'<span class="item-col" type="datetime">'+data.data[i].dateTime.substring(0,19)+'</span>'+
										'<span class="item-col" type="company">'+data.data[i].compname+'</span>'+
									'</li>';
	 					}
	 					$('#zlcljk-carList').html(text);
	 					
	 					//点击查看车辆的信息
	 					$('#zlcljk-carList li').on('click',function(){
	 						var ve = $($(this).children()[0]).html();
	 						for(var i=0;i<=data.data.length;i++){
	 							if(data.data[i] != undefined && data.data[i].vehino == ve){
	 								makerCar(data.data[i]);
	 								break;
	 							}
	 						}
	 					});
	 				}
	 			}
			});
		});
		
		function makerCar(obj){
			var status,statue;
			if (obj.lati == null || "" == obj.lati
					|| 0==obj.lati
					|| ""==obj.longi
					|| 0 ==obj.longi 
			) {
				status = "off";
				statue = "离线";
			}else{
				if (obj.onoffstate=="1") {
					if (obj.carStatus=="0") {
						status = "empty";
						statue = "空车";
					}else{
						status = "busy";
						statue = "重车";
					}
				}else if (obj.onoffstate=="0") {
					status = "off";
					statue = "离线";
				}
			}
			obj.time = obj.dateTime.substring(0,19);
			var icon = dirc(status,obj.heading);
			
			var arr = zlcljk_map.getAllOverlays();
			for(var i=0;i<arr.length;i++){
				if(arr[i].CLASS_NAME != "AMap.Polygon"){
					zlcljk_map.remove(arr[i]);
				}
			}
			var marker = new AMap.Marker({
			  	map:zlcljk_map,
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
			inforWindow.open(zlcljk_map,new AMap.LngLat(obj.longi,obj.lati));
			zlcljk_map.setZoomAndCenter(15,marker.getPosition());
		}
	})
})(jQuery);