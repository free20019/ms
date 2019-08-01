var cljjdqyj=(function ($) {
	var cljjdqyjmap;
	//所有车辆数
	var cljjdqvehilist;
	//车辆经纬度数组
	var vhicsz=new Array();
	$(function () {
		yjcx();
		function getMyDate(str) {
			    var oDate = new Date(str),
			    oYear = oDate.getFullYear(),
			    oMonth = oDate.getMonth()+1,
			    oDay = oDate.getDate(),
//			    oHour = oDate.getHours(),
//			    oMin = oDate.getMinutes(),
//			    oSen = oDate.getSeconds(),
			    oTime = oYear +'-'+ addZero(oMonth) +'-'+ addZero(oDay);
			    return oTime;
			}
		function addZero(num){
		    if(parseInt(num) < 10){
		        num = '0'+num;
		    }
		    return num;
		}
//		$('#cljjdqyj-datetimeStart').datetimepicker(dateDefaultOption);
//		$('#cljjdqyj-datetimeEnd').datetimepicker(dateDefaultOption);
		$('#cljjdqyj-datetimeStart').val(getMyDate(parseInt(new Date().getTime()-6*24*3600*1000)));
		$('#cljjdqyj-datetimeEnd').val(new Date().Format('yyyy-MM-dd'));
		 $("#cljjdqyj-datetimeStart").datetimepicker({　
        　　			format: 'yyyy-mm-dd',
	    　　　　　　language: 'zh-CN',
	    　　　　　　weekStart: 1,
//	    　　　　　　todayBtn: 1,//显示‘今日’按钮
	    　　　　　　autoclose: 1,
	    　　　　　　todayHighlight: 1,
	    　　　　　　startView: 2,
	    　　　　　　minView: 2,  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
	    　　　　　　forceParse: 1,
	           endDate: new Date(),
	     }).on('changeDate',function(ev){
	         var starttime=$("#cljjdqyj-datetimeStart").val();
	         $("#cljjdqyj-datetimeEnd").datetimepicker('setStartDate',starttime);
	         $("#cljjdqyj-datetimeStart").datetimepicker('hide');
	    });
	    $("#cljjdqyj-datetimeEnd").datetimepicker({　
	      　　format: 'yyyy-mm-dd',
	  　　　　　　language: 'zh-CN',
	  　　　　　　weekStart: 1,
//	  　　　　　　todayBtn: 1,//显示‘今日’按钮
	  　　　　　　autoclose: 1,
	  　　　　　　todayHighlight: 1,
	  　　　　　　startView: 2,
	  　　　　　　minView: 2,  //Number, String. 默认值：0, 'hour'，日期时间选择器所能够提供的最精确的时间选择视图。
	  　　　　　　forceParse: 0,
	          endDate: new Date(),
	   }).on('changeDate',function(ev){
	       var endtime=$("#cljjdqyj-datetimeEnd").val();
	       $("#cljjdqyj-datetimeStart").datetimepicker('setEndDate',endtime);
	       $("#cljjdqyj-datetimeEnd").datetimepicker('hide'); 
	  });
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
		cljjdqyjmap = new AMap.Map('cljjdqyjMap', {
			center : position,
			level : 15,
			resizeEnable : true
		});
		cljjdqyjmap.plugin( [ "AMap.ToolBar", "AMap.OverView", "AMap.Scale" ],
				function() {
					//加载工具条
				tool = new AMap.ToolBar( {
					direction : false,//隐藏方向导航
					ruler : false,//隐藏视野级别控制尺
					autoPosition : false
				//禁止自动定位
						});
				cljjdqyjmap.addControl(tool);
				//加载鹰眼
				view = new AMap.OverView();
				cljjdqyjmap.addControl(view);
				//加载比例尺
				scale = new AMap.Scale();
				cljjdqyjmap.addControl(scale);
				cljjdqyjmap.plugin( [ "AMap.MapType" ], function() {
					var type = new AMap.MapType( {
						defaultType : 0
					});//初始状态使用2D地图
					cljjdqyjmap.addControl(type);
					});
			});
		
		//查询所有的车辆
		$('#yjcx').on('click',function(){
			yjcx();
		})
		function yjcx(){
			markers=[];
			jqxhr=$.ajax({
	     		type: "POST",
	 	        url:"../../sbwx/findjjbj",
	 	        data:{
	 	        	"stime" : $('#cljjdqyj-datetimeStart').val(),
	 	        	"etime" : $('#cljjdqyj-datetimeEnd').val()
	 	        },
	 	        dataType: 'json',
	 			timeout : 3600000,
	 			success:function(data){
	 				cljjdqvehilist = data;	
	 				var text = "";
	 				for(var i=0;i<=cljjdqvehilist.length;i++){
	 					if(cljjdqvehilist[i] != undefined){
	 						text +='<li class="listItem">'+
	 									'<span class="item-col" type="vehicleNum">'+cljjdqvehilist[i].VEHICLE_NO+'</span>'+
	 									'<span class="item-col gz" type="button">跟踪</span>'+
	 								'</li>';
	 					}
	 				}
	 				$('#vehi_list').html(text);
	 				jump();
	 				$("#cphm_value").on("keyup",function(){
	 					var vehi = this.value;
	 						var text = "";
	 						for(var i=0;i<=cljjdqvehilist.length;i++){
	 							if(cljjdqvehilist[i] != undefined && cljjdqvehilist[i].VEHICLE_NO.indexOf(vehi) != -1){
	 								text +='<li class="listItem">'+
	 											'<span class="item-col" type="vehicleNum">'+cljjdqvehilist[i].VEHICLE_NO+'</span>'+
	 											'<span class="item-col gz" type="button">跟踪</span>'+
	 										'</li>';
	 							}
	 						}
	 						$('#vehi_list').html(text);
	 						jump();
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
		}
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
					markerImg.src="../../resources/images/c.png";
				}
				//定位故障
				if(obj.MOD_FAULT!='0'||obj.ANT_FAULT!='0'||obj.INEXACT!='0'){
					count ++;
					markerImg.src="../../resources/images/c.png";
				}
				//通信故障
				if(obj.COMM_FAULT!='0'){
					count ++;
					markerImg.src="../../resources/images/c.png";
				}
				//定位回传故障
				if(obj.GPS_OVER_BACK!='0'||obj.GPS_NO_BACK!='0'){
					count ++;
					markerImg.src="../../resources/images/c.png";
				}
				//摄像头故障
				if(obj.CAM_OCCLUSION!='0'||obj.CAM_NOSIGN!='0'){
					count ++;
					markerImg.src="../../resources/images/c.png";
				}
				//视频主机/存储故障
				if(obj.HD_FAULT!='0'||obj.SD_FAULT!='0'||obj.VD_FAULT!='0'||obj.VD_EX_FAULT!='0'){
					count ++;
					markerImg.src="../../resources/images/c.png";
				}
				//计价器断开故障
				if(obj.METER_DISCONN!='0'){
					count ++;
					markerImg.src="../../resources/images/c.png";
				}
				//导航屏断开故障
				if(obj.NAV_DISCONN!='0'){
					count ++;
					markerImg.src="../../resources/images/c.png";
				}
				//空车灯故障
				if(obj.ST_NO_CHG!='0'||obj.ST_OVER_CHG!='0'){
					count ++;
					markerImg.src="../../resources/images/c.png";
				}
				if(count>1){
					markerImg.src="../../resources/images/h.png";
				}
				markerContent.appendChild(markerImg);
				var marker1 = new AMap.Marker({
					//   map:mapObjhistory,
					position:new AMap.LngLat(obj.PX,obj.PY),     
					offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
					draggable:false,  //是否可拖动
					content:markerContent   //自定义点标记覆盖物内容
				});
				marker1.setMap(cljjdqyjmap);  //在地图上添加点			
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
				markerImg.src="../../resources/images/c.png";
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
				markerImg.src="../../resources/images/c.png";
			}
			//通信故障
			if(vehicle.COMM_FAULT!='0'){
				count ++;
				gz +='通信故障(';
				gz +='通讯故障';
				gz +='),';
				markerImg.src="../../resources/images/c.png";
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
				markerImg.src="../../resources/images/c.png";
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
				markerImg.src="../../resources/images/c.png";
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
				
				markerImg.src="../../resources/images/c.png";
			}
			//计价器断开故障
			if(vehicle.METER_DISCONN!='0'){
				count ++;
				gz +='计价器断开故障(';
				gz +='计价器连接断开';
				gz +='),';
				markerImg.src="../../resources/images/c.png";
			}
			//导航屏断开故障
			if(vehicle.NAV_DISCONN!='0'){
				count ++;
				gz +='导航屏断开故障(';
				gz +='导航屏断开';
				gz +='),';
				markerImg.src="../../resources/images/c.png";
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
				markerImg.src="../../resources/images/c.png";
			}
			if(count>1){
				markerImg.src="../../resources/images/h.png";
			}
			markerContent.appendChild(markerImg);
			marker = new AMap.Marker({
			    map:cljjdqyjmap,
			    position:new AMap.LngLat(vehicle.PX,vehicle.PY),     
			    offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
			    draggable:false,  //是否可拖动
			    content:markerContent   //自定义点标记覆盖物内容
			});
			cljjdqyjmap.setCenter(new AMap.LngLat(vehicle.PX,vehicle.PY));
			var txt = "<table><tr><td><b style='color:#3399FF'>"+vehicle.VEHI_NO+"</b></td>" +
			"<td></td></tr><tr><td><b>[所属业户]</b>："+vehicle.COMP_NAME+"</td></tr>" +
			"<tr><td><b>[车辆商标]</b>："+vehicle.VT_NAME+"</td></tr>" +
			"<tr><td><b>[车辆颜色]</b>："+vehicle.VC_NAME+"</td></tr>" +
			"<tr><td><b>[SIM卡]</b>："+vehicle.SIM_NUM+"</td></tr>" +
			"<tr><td><b>[车辆所属人]</b>："+(vehicle.OWN_NAME==null?"":vehicle.OWN_NAME)+"</td></tr>" +
			"<tr><td><b>[联系电话]</b>："+(vehicle.OWN_TEL==null?"":vehicle.OWN_TEL)+"</td></tr>" +
			"<tr><td><b>[经度]</b>："+vehicle.PX+"</td></tr><tr><td><b>[纬度]</b>："+vehicle.PY+"</td></tr>" +
			"<tr><td><b>[故障类型]</b>："+gz+"</td></tr>" +
			"<tr><td><b>[故障最后上传时间]</b>："+(vehicle.DBTIME==null?"":(new Date(vehicle.DBTIME)).Format("yyyy-MM-dd"))+"</td></tr>" +
//			"<tr><td><b>[维修人员]</b>："+(vehicle.WXRY==null?"":vehicle.WXRY)+"</td></tr>" +
//			"<tr><td><b>[维修地点]</b>："+(vehicle.RA_ADDR==null?"":vehicle.RA_ADDR)+"</td></tr>" +
//			"<tr><td><b>[维修类型]</b>："+(vehicle.RT_TYPE==null?"":vehicle.RT_TYPE)+"</td></tr>" +
			"<tr><td><b>[经纬度上传时间]</b>："+(vehicle.STIME==null?"":(new Date(vehicle.STIME)).Format("yyyy-MM-dd hh:mm:ss"))+"</td></tr>" +
//			"<tr><td><b>[维修完成时间]</b>："+(vehicle.RR_TIME_END==null?"":(new Date(vehicle.RR_TIME_END)).Format("yyyy-MM-dd hh:mm:ss"))+"</td></tr>" +
			"";
			if(vehicle.OWN_TEL!=null){
				txt +="<tr><td><a href='javascript:void(0);' onclick='findjgtz(\""+vehicle.OWN_TEL+"\");'>警告通知</a></td></tr>";
			}
			var info = [];
			info.push(txt);
			               
			var inforWindowone = new AMap.InfoWindow({                 
			  offset:new AMap.Pixel(5,10),                 
			  content:info.join("</table>")                 
			});           
			  inforWindowone.open(cljjdqyjmap,marker.getPosition());                 
			  AMap.event.addListener(marker,"click",function(e){                 
				  inforWindowone.open(cljjdqyjmap,marker.getPosition());                 
				});
			  
		}
		//点聚合
		var cluster;
		function addCluster(tag){
			if(cluster) {
				cluster.setMap(null);
			}
			cljjdqyjmap.clearMap();
			if(tag==1) {
				var sts=[{url:"../../resources/images/12.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
						{url:"../../resources/images/11.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
						{url:"../../resources/images/13.jpg", size:new AMap.Size(48,48),offset:new AMap.Pixel(-24,-45),textColor:'#CC0066'}];
				cljjdqyjmap.plugin(["AMap.MarkerClusterer"],function(){
					cluster = new AMap.MarkerClusterer(cljjdqyjmap,markers,{minClusterSize:10,styles:sts});
				});
			}else {
				cljjdqyjmap.plugin(["AMap.MarkerClusterer"],function(){
					cluster = new AMap.MarkerClusterer(cljjdqyjmap,markers);
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
			for(var i=0;i<cljjdqvehilist.length;i++){
				if(cljjdqvehilist[i] != undefined && cljjdqvehilist[i].VEHI_NO == c){
					var obj = cljjdqvehilist[i];
					addMarker(obj);
				}
			}
		}
	})
})(jQuery)
function findjgtz(tel){
	var _parent = parent.$(window.parent.document);
	if (_parent.find('.ip-tabBarItem[data-name="sjyddcx"]').length > 0) {
		alert(1);
		_parent.find('.ip-tabBarItem[data-name="sjyddcx"]').trigger('click');
		_parent.find('#sjyddcx').get(0).contentWindow.location.reload(true);
	}
	_parent.find('.ip-menuItem [data-skip="sjyddcx"]').trigger("click");
	_parent.find('#defaultTel').val(tel);
}
