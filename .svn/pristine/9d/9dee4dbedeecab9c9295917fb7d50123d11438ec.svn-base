(function ($) {
	var ssjkmap;
	//所有车辆数
	var wxclvehilist;
	//车辆经纬度数组
	var vhicsz=new Array();
	$(function () {
		$('.scrollbar-macosx').scrollbar();
		$('.searchBar input[type=text]').on({
			focus: function () {
				$(this).parent().attr('state', 'focus');
			},
			blur: function () {
				$(this).parent().removeAttr('state')
			}
		});
		var position = new AMap.LngLat(120.16378, 30.25840);//创建中心点坐标
		ssjkmap = new AMap.Map('ssjkMap', {
			center : position,
			level : 15,
			resizeEnable : true
		});
		ssjkmap.plugin( [ "AMap.ToolBar", "AMap.OverView", "AMap.Scale" ],
				function() {
					//加载工具条
				tool = new AMap.ToolBar( {
					direction : false,//隐藏方向导航
					ruler : false,//隐藏视野级别控制尺
					autoPosition : false
				//禁止自动定位
						});
				ssjkmap.addControl(tool);
				//加载鹰眼
				view = new AMap.OverView();
				ssjkmap.addControl(view);
				//加载比例尺
				scale = new AMap.Scale();
				ssjkmap.addControl(scale);
				ssjkmap.plugin( [ "AMap.MapType" ], function() {
					var type = new AMap.MapType( {
						defaultType : 0
					});//初始状态使用2D地图
					ssjkmap.addControl(type);
					});
			});
		
		//查询所有的车辆
		jqxhr=$.ajax({
     		type: "POST",
 	        url:"../../sbwx/vehicle",
 	        data:{},
 	        dataType: 'json',
 			timeout : 3600000,
 			success:function(data){
 				wxclvehilist = data;	
 				$("#cphm_value").on("keyup",function(){
 					var vehi = this.value;
 					if(this.value.length >= 3){
 						var text = "";
 						var count = 12;
 						for(var i=0;i<=wxclvehilist.length;i++){
 							if(wxclvehilist[i] != undefined && wxclvehilist[i].VEHI_NO.indexOf(vehi) != -1){
 								text +='<li class="listItem">'+
 											'<span class="col" type="licensePlate">'+wxclvehilist[i].VEHI_NO+'</span>'+
 											'<span class="col btn btn-default btn-xs gz" type="button">跟踪</span>'+
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
 							text="";
 							for(var i=0;i<=wxclvehilist.length;i++){
 	 							if(wxclvehilist[i] != undefined && wxclvehilist[i].VEHI_NO.indexOf(vehi) != -1){
 	 								text +='<li class="listItem">'+
 	 											'<span class="col" type="licensePlate">'+wxclvehilist[i].VEHI_NO+'</span>'+
 	 											'<span class="col btn btn-default btn-xs gz" type="button">跟踪</span>'+
 	 										'</li>';
 	 							}
 	 						}
 							$('#vehi_list').html(text);
 							jump();
 						});
 					}
 				});
 				var count1=0;
 				var count2=0;
 				var count3=0;
 				var count4=0;
 				var count5=0;
 				var count6=0;
 				var count7=0;
 				var count8=0;
 				var count9=0;
 				var amount=0;
 				for(var z=0;z<data.length;z++){
 					var count10=0;
 					var obj=data[z];
 					//主机故障
 					if(obj.LOW_VOLTAGE!='0'||obj.NO_POWER!='0'||obj.NO_GPS!='0'||obj.NO_UPLOAD!='0'){
 						count1 ++;
 						count10++;
 					}
 					//定位故障
 					if(obj.MOD_FAULT!='0'||obj.ANT_FAULT!='0'||obj.INEXACT!='0'){
 						count2 ++;count10++;
 					}
 					//通信故障
 					if(obj.COMM_FAULT!='0'){
 						count3 ++;count10++;
 					}
 					//定位回传故障
 					if(obj.GPS_OVER_BACK!='0'||obj.GPS_NO_BACK!='0'){
 						count4 ++;count10++;
 					}
 					//摄像头故障
 					if(obj.CAM_OCCLUSION!='0'||obj.CAM_NOSIGN!='0'){
 						count5 ++;count10++;
 					}
 					//视频主机/存储故障
 					if(obj.HD_FAULT!='0'||obj.SD_FAULT!='0'||obj.VD_FAULT!='0'||obj.VD_EX_FAULT!='0'){
 						count6 ++;count10++;
 					}
 					//计价器断开故障
 					if(obj.METER_DISCONN!='0'){
 						count7 ++;count10++;
 					}
 					//导航屏断开故障
 					if(obj.NAV_DISCONN!='0'){
 						count8 ++;count10++;
 					}
 					//空车灯故障
 					if(obj.ST_NO_CHG!='0'||obj.ST_OVER_CHG!='0'){
 						count9 ++;count10++;
 					}
 					if(count10>1){
 						amount++;
 					}
 				}
 				$('#total').html(data.length);
 				$('#num1').html(count1);
 				$('#num2').html(count2);
 				$('#num3').html(count3);
 				$('#num4').html(count4);
 				$('#num5').html(count5);
 				$('#num6').html(count6);
 				$('#num7').html(count7);
 				$('#num8').html(count8);
 				$('#num9').html(count9);
 				$('#num').html(amount);
 				for(var j=0;j<data.length;j++){ 	
 					addmks(data[j]);
 					if(j==data.length-1){
 						addCluster(1);
 					}
 				}
 			}
		});
		//将查询出的所有车辆放入地图
		
		var markers= [];
		function addmks(obj){
			if(obj.hasOwnProperty('PX')&&obj.hasOwnProperty('PY')){		
				var count=0;
				var markerContent = document.createElement("div");
				markerContent.className = "markerContentStyle";
				//点标记中的图标
				var markerImg= document.createElement("img");
				markerImg.className="markerlnglat";
				//主机故障
				if(obj.LOW_VOLTAGE!='0'||obj.NO_POWER!='0'||obj.NO_GPS!='0'||obj.NO_UPLOAD!='0'){
					count ++;
					markerImg.src="../../resources/images/401.png";
				}
				//定位故障
				if(obj.MOD_FAULT!='0'||obj.ANT_FAULT!='0'||obj.INEXACT!='0'){
					count ++;
					markerImg.src="../../resources/images/402.png";
				}
				//通信故障
				if(obj.COMM_FAULT!='0'){
					count ++;
					markerImg.src="../../resources/images/403.png";
				}
				//定位回传故障
				if(obj.GPS_OVER_BACK!='0'||obj.GPS_NO_BACK!='0'){
					count ++;
					markerImg.src="../../resources/images/404.png";
				}
				//摄像头故障
				if(obj.CAM_OCCLUSION!='0'||obj.CAM_NOSIGN!='0'){
					count ++;
					markerImg.src="../../resources/images/405.png";
				}
				//视频主机/存储故障
				if(obj.HD_FAULT!='0'||obj.SD_FAULT!='0'||obj.VD_FAULT!='0'||obj.VD_EX_FAULT!='0'){
					count ++;
					markerImg.src="../../resources/images/406.png";
				}
				//计价器断开故障
				if(obj.METER_DISCONN!='0'){
					count ++;
					markerImg.src="../../resources/images/407.png";
				}
				//导航屏断开故障
				if(obj.NAV_DISCONN!='0'){
					count ++;
					markerImg.src="../../resources/images/408.png";
				}
				//空车灯故障
				if(obj.ST_NO_CHG!='0'||obj.ST_OVER_CHG!='0'){
					count ++;
					markerImg.src="../../resources/images/409.png";
				}
				if(count>1){
					markerImg.src="../../resources/images/410.png";
				}
				markerContent.appendChild(markerImg);
				var marker1 = new AMap.Marker({
					//   map:mapObjhistory,
					position:new AMap.LngLat(obj.PX,obj.PY),     
					offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
					draggable:false,  //是否可拖动
					content:markerContent   //自定义点标记覆盖物内容
				});
				marker1.setMap(ssjkmap);  //在地图上添加点			
				markers.push(marker1);
				AMap.event.addListener(marker1,"click",function(e){  
					vhicmarker(obj.VEHI_NO);
				});
			}
		}
		//点击车辆图标,在地图上显示该车辆的当前信息
		function vhicmarker(obj){
			jqxhr=$.ajax({
				 type: "POST",
			        url:"../../sbwx/vhicmarker",
			        data:{
						vehi_no : obj
			        },
			       dataType: 'json',
					timeout : 180000,
				success:function(json){
			        	var cust=json;
			        	for(var i=0;i<cust.length;i++){
			        		addMarker(cust[0]);
			        	}
				},
				error:function(){
					
				}		
			});
		}
		//将单个车辆的位置信息显示在地图上
		var marker="";
		function addMarker(vehicle){
			//每次加载将上次地图添加的点删除
			if(marker!=""){
				marker.setMap(null);
			}
			var markerContent = document.createElement("div");
		    markerContent.className = "txtstyle";
		    //点标记中的图标
		    var markerImg= document.createElement("img");
			markerImg.className="markerlnglat";
			var count=0;
			var gz='';
			//主机故障
			if(vehicle.LOW_VOLTAGE!='0'||vehicle.NO_POWER!='0'||vehicle.NO_GPS!='0'||vehicle.NO_UPLOAD!='0'){
				count ++;
				gz +='主机故障(';
				if(vehicle.LOW_VOLTAGE!='0'){
					gz +='终端主电源欠压,';
				}
				if(vehicle.NO_POWER!='0'){
					gz +='主电源掉电,';
				}
				if(vehicle.NO_GPS!='0'){
					gz +='无定位数据,';
				}
				if(vehicle.NO_UPLOAD!='0'){
					gz +='无数据上传,';
				}
				gz +='),';
				markerImg.src="../../resources/images/401.png";
			}
			//定位故障
			if(vehicle.MOD_FAULT!='0'||vehicle.ANT_FAULT!='0'||vehicle.INEXACT!='0'){
				count ++;
				gz +='定位故障(';
				if(vehicle.MOD_FAULT!='0'){
					gz +='定位模块故障,';
				}
				if(vehicle.ANT_FAULT!='0'){
					gz +='天线短路,';
				}
				if(vehicle.INEXACT!='0'){
					gz +='非精确定位,';
				}
				gz +='),';
				markerImg.src="../../resources/images/402.png";
			}
			//通信故障
			if(vehicle.COMM_FAULT!='0'){
				count ++;
				gz +='通信故障(';
				gz +='通讯故障';
				gz +='),';
				markerImg.src="../../resources/images/403.png";
			}
			//定位回传故障
			if(vehicle.GPS_OVER_BACK!='0'||vehicle.GPS_NO_BACK!='0'){
				count ++;
				gz +='定位回传故障(';
				if(vehicle.GPS_OVER_BACK!='0'){
					gz +='定位回传过密,';
				}
				if(vehicle.GPS_NO_BACK!='0'){
					gz +='回传数据丢失,';
				}
				gz +='),';
				markerImg.src="../../resources/images/404.png";
			}
			//摄像头故障
			if(vehicle.CAM_OCCLUSION!='0'||vehicle.CAM_NOSIGN!='0'){
				count ++;
				gz +='摄像头故障(';
				if(vehicle.CAM_OCCLUSION!='0'){
					gz +='摄像头遮挡,';
				}
				if(vehicle.CAM_NOSIGN!='0'){
					gz +='摄像头信号丢失,';
				}
				gz +='),';
				markerImg.src="../../resources/images/405.png";
			}
			//视频主机/存储故障
			if(vehicle.HD_FAULT!='0'||vehicle.SD_FAULT!='0'||vehicle.VD_FAULT!='0'||vehicle.VD_EX_FAULT!='0'){
				count ++;
				gz +='视频主机/存储故障(';
				if(vehicle.HD_FAULT!='0'){
					gz +='硬盘故障,';
				}
				if(vehicle.SD_FAULT!='0'){
					gz +='SD卡故障,';
				}
				if(vehicle.VD_FAULT!='0'){
					gz +='视频主机故障,';
				}
				if(vehicle.VD_EX_FAULT!='0'){
					gz +='视频扩展故障,';
				}
				gz +='),';
				
				markerImg.src="../../resources/images/406.png";
			}
			//计价器断开故障
			if(vehicle.METER_DISCONN!='0'){
				count ++;
				gz +='计价器断开故障(';
				gz +='计价器连接断开';
				gz +='),';
				markerImg.src="../../resources/images/407.png";
			}
			//导航屏断开故障
			if(vehicle.NAV_DISCONN!='0'){
				count ++;
				gz +='导航屏断开故障(';
				gz +='导航屏断开';
				gz +='),';
				markerImg.src="../../resources/images/408.png";
			}
			//空车灯故障
			if(vehicle.ST_NO_CHG!='0'||vehicle.ST_OVER_CHG!='0'){
				count ++;
				gz +='空车灯故障(';
				if(vehicle.ST_NO_CHG!='0'){
					gz +='空重车不变化,';
				}
				if(vehicle.ST_OVER_CHG!='0'){
					gz +='空重车切换频繁,';
				}
				gz +='),';
				markerImg.src="../../resources/images/409.png";
			}
			if(count>1){
				markerImg.src="../../resources/images/410.png";
			}
			markerContent.appendChild(markerImg);
			marker = new AMap.Marker({
			    map:ssjkmap,
			    position:new AMap.LngLat(vehicle.PX,vehicle.PY),     
			    offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
			    draggable:false,  //是否可拖动
			    content:markerContent   //自定义点标记覆盖物内容
			});
			ssjkmap.setCenter(new AMap.LngLat(vehicle.PX,vehicle.PY));
			var txt = "<table><tr><td><b style='color:#3399FF'>"+vehicle.VEHI_NO+"</b></td>" +
			"<td></td></tr><tr><td><b>[所属业户]</b>："+vehicle.COMP_NAME+"</td></tr>" +
			"<tr><td><b>[车辆商标]</b>："+vehicle.VT_NAME+"</td></tr>" +
			"<tr><td><b>[车辆颜色]</b>："+vehicle.VC_NAME+"</td></tr>" +
			"<tr><td><b>[SIM卡]</b>："+vehicle.SIM_NUM+"</td></tr>" +
			"<tr><td><b>[车辆所属人]</b>："+(vehicle.OWN_NAME==null?"":vehicle.OWN_NAME)+"</td></tr>" +
			"<tr><td><b>[联系电话]</b>："+(vehicle.OWN_TEL==null?"":vehicle.OWN_TEL)+"</td></tr>" +
			"<tr><td><b>[经度]</b>："+vehicle.PX+"</td></tr><tr><td><b>[纬度]</b>："+vehicle.PY+"</td></tr>" +
			"<tr><td><b>[故障类型]</b>："+gz+"</td></tr>" +
			"<tr><td><b>[故障上传时间]</b>："+(vehicle.DBTIME==null?"":(new Date(vehicle.DBTIME)).Format("yyyy-MM-dd"))+"</td></tr>" +
//			"<tr><td><b>[维修人员]</b>："+(vehicle.WXRY==null?"":vehicle.WXRY)+"</td></tr>" +
//			"<tr><td><b>[维修地点]</b>："+(vehicle.RA_ADDR==null?"":vehicle.RA_ADDR)+"</td></tr>" +
//			"<tr><td><b>[维修类型]</b>："+(vehicle.RT_TYPE==null?"":vehicle.RT_TYPE)+"</td></tr>" +
			"<tr><td><b>[经纬度上传时间]</b>："+(vehicle.STIME==null?"":(new Date(vehicle.STIME)).Format("yyyy-MM-dd hh:mm:ss"))+"</td></tr>" +
//			"<tr><td><b>[维修完成时间]</b>："+(vehicle.RR_TIME_END==null?"":(new Date(vehicle.RR_TIME_END)).Format("yyyy-MM-dd hh:mm:ss"))+"</td></tr>" +
			"";
			var info = [];
			info.push(txt);
			               
			var inforWindowone = new AMap.InfoWindow({                 
			  offset:new AMap.Pixel(5,10),                 
			  content:info.join("</table>")                 
			});           
			  inforWindowone.open(ssjkmap,marker.getPosition());                 
			  AMap.event.addListener(marker,"click",function(e){                 
				  inforWindowone.open(ssjkmap,marker.getPosition());                 
				});
			  
		}
		//点聚合
		var cluster;
		function addCluster(tag){
			if(cluster) {
				cluster.setMap(null);
			}
			ssjkmap.clearMap();
			if(tag==1) {
				var sts=[{url:"../../resources/images/12.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
						{url:"../../resources/images/11.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
						{url:"../../resources/images/13.jpg", size:new AMap.Size(48,48),offset:new AMap.Pixel(-24,-45),textColor:'#CC0066'}];
				ssjkmap.plugin(["AMap.MarkerClusterer"],function(){
					cluster = new AMap.MarkerClusterer(ssjkmap,markers,{minClusterSize:10,styles:sts});
				});
			}else {
				ssjkmap.plugin(["AMap.MarkerClusterer"],function(){
					cluster = new AMap.MarkerClusterer(ssjkmap,markers);
				});
			}
		}
		//比较查询出的时间和当前时间
		function comparetime(time){
			var today;
			if(time==null){
				today="";
			}else{				
				today=(new Date(time)).Format("yyyy-MM-dd");
			}
			var date = new Date();
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			if (month < 10) {
			    month = "0" + month;
			}
			if (day < 10) {
			    day = "0" + day;
			}
			var nowDate = year + "-" + month + "-" + day;
			return (nowDate>today?1:0);
		}
		//绑定-跟踪 跳转
		function jump(){
			//跟踪
			$('.gz').on('click',function(){
				maker_vehi($($(this).prev()).html());
			});
		}
		
		//跟踪-定位
		function maker_vehi(c){
			for(var i=0;i<wxclvehilist.length;i++){
				if(wxclvehilist[i] != undefined && wxclvehilist[i].VEHI_NO == c){
					var obj = wxclvehilist[i];
					addMarker(obj);
				}
			}
		}
	})
})(jQuery)
