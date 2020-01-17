var cljkgz=(function ($) {
	var zdcljkmap;
	//所有车辆数
	var clgzvehilist;
	//车辆经纬度数组
	var vhicsz=new Array();
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
			zoom:11,
			viewMode:'3D'
		});
		
		$.ajax({
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
 											'<span class="col" type="licensePlate">'+clgzvehilist[i].vehino+'</span>'+
 											'<span class="col btn btn-default btn-xs gz" type="button">跟踪</span>'+
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
 	 											'<span class="col" type="licensePlate">'+clgzvehilist[i].vehino+'</span>'+
 	 											'<span class="col btn btn-default btn-xs gz" type="button">跟踪</span>'+
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
				maker_vehi($($(this).prev()).html());
			});
			//轨迹回放
			$('.gj').on('click',function(){
				var vehi = $($(this).prev().prev()).html();
				var _parent = parent.$(window.parent.document);
				if (_parent.find('.ip-tabBarItem[data-name="gjhfgz"]').length > 0) {
					_parent.find('.ip-tabBarItem[data-name="gjhfgz"]').trigger('click');
					_parent.find('#gjhfgz').get(0).contentWindow.location.reload(true);
				}
				_parent.find('#defaultVehi').val(vehi);
				_parent.find('.ip-menuItem [data-skip="gjhfgz"]').trigger("click");
			});
		}
		
		//跟踪-定位
		function maker_vehi(c){
			zdcljkmap.clearMap();
			for(var i=0;i<=clgzvehilist.length;i++){
				if(clgzvehilist[i] != undefined && clgzvehilist[i].vehino == c){
					var obj = clgzvehilist[i];
					var marker = new AMap.Marker({
		  			  	map:zdcljkmap,
					    icon:"../../resources/images/car2.png",
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
									'<div class="mapInfo-item" type="mapInfoItem" title="车辆所属人">'+obj.ownname+'</div>'+
									'<div class="mapInfo-item" type="mapInfoItem" title="联系电话">'+obj.owntel+'</div>'+
									'<div class="mapInfo-item" type="mapInfoItem" title="经度">'+obj.longi+'</div>'+
									'<div class="mapInfo-item" type="mapInfoItem" title="纬度">'+obj.lati+'</div>'+
								'</div>';
					inforWindow.setContent(txt);
					inforWindow.open(zdcljkmap,new AMap.LngLat(obj.longi,obj.lati));
				}
			}
		}
	})
})(jQuery)
