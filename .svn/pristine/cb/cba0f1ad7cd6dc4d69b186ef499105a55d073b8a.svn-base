var ystpcx=(function ($) {
	$(function () {
//		$('#ystp-stateTime').datetimepicker(datetimeDefaultOption);
////		$('#ystp-stateTime').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));
//		$('#ystp-endTime').datetimepicker(datetimeDefaultOption);
////		$('#ystp-endTime').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));
		
		var today = new Date();
		var oneday = 1000 * 60 * 60 * 2;
		$('#ystp-stateTime').datetimepicker(datetimeDefaultOption);
		$('#ystp-stateTime').val(new Date(today-oneday).Format('yyyy-MM-dd hh:mm:ss'));
		$('#ystp-endTime').datetimepicker(datetimeDefaultOption);
		$('#ystp-endTime').val(new Date(today).Format('yyyy-MM-dd hh:mm:ss'));
		//地图
		var map = new AMap.Map('ystpMap', {
			zoom:14,
			center: new AMap.LngLat(120.153576,30.287459)
		});
		var pathSimplifierIns =null;
		var mapMarker=null;
		$(document).ready(function() {
			  $(".select2").select2({  
				  	language: "zh-CN",  //设置 提示语言
			        tags:true,  
			        allowClear: true,
			        createTag:function (decorated, params) {  
			            return null;  
			        },  
			    });
			});
		jqxhr=$.ajax({
				type: "POST",
				url: "../../ycyy/xll",
				data:{"field":"VEHICLE_NO","table":"TB_TAXI_TPC"},
				timeout : 180000,
				dataType: 'json',
				success:function(data){
					$("#ystp-cphm").select2({ 
						language: "zh-CN",  //设置 提示语言
				        minimumInputLength: 3,
				        tags:true,  
				        allowClear: true,
				        closeOnSelect: true,
				        createTag:function (decorated, params) {  
				            return null;  
				        },  
				    });
					$("#ystp-cphm").empty();
					$("#ystp-cphm").append('<option value=""></option>');
					$("#ystp-cphm").append('<option value="null">全部车牌</option>');
					for(var i=0; i<data.length;i++){
						$("#ystp-cphm").append('<option value="'+data[i].VEHICLE_NO+'">'+data[i].VEHICLE_NO+'</option>');
					}
				},
				error: function(){
				}         
			});
//			jqxhr=$.ajax({
//				type: "POST",
//				url:"../../claq/qyveh",
//				data:{},
//				dataType: 'json',
//				timeout : 3600000,
//				success:function(json){
//					console.log(json);
//					var data= json.dataveh;
//					for (var i = 0; i < data.length; i++) {
//						data[i].id=data[i].PLATE_NUMBER;
//						data[i].text=data[i].PLATE_NUMBER;
//					}
//					var qb={};
//					qb.id='null';
//					qb.text='全部';
//					data.unshift(qb);
//					$('#ystp-cphm').select2({
//						data: data,
//						language:'zh-CN',
//						minimumInputLength: 3,
//						allowClear: true
//					});
//				}
//			});
// 		jqxhr=$.ajax({
// 				type: "POST",
// 				url: "../../ycyy/xll",
// 				data:{"field":"HK_ADDRESS","table":"TB_TAXI_TPC"},
// 				timeout : 180000,
// 				dataType: 'json',
// 				success:function(data){
// 					$("#ystp-type").empty();
// 					$("#ystp-type").append('<option value="" disabled selected>类型</option>');
// 					$("#ystp-type").append('<option value="null">全部类型</option>');
// 					// for(var i=0; i<data.length;i++){
// 						$("#ystp-type").append('<option value="套牌">套牌</option>');
// 					// }
// 				},
// 				error: function(){
// 				}
// 			});
		jqxhr=$.ajax({
			type: "POST",
			url: "../../ycyy/xll",
			data:{"field":"HK_ADDRESS","table":"TB_TAXI_TPC"},
			timeout : 180000,
			dataType: 'json',
			success:function(data){
				$("#ystp-address").empty();
				$("#ystp-address").append('<option value="" disabled selected>采集点</option>');
				$("#ystp-address").append('<option value="null">全部地点</option>');
				for(var i=0; i<data.length;i++){
					if(null!=data[i].HK_ADDRESS){
						$("#ystp-address").append('<option value="'+data[i].HK_ADDRESS+'">'+data[i].HK_ADDRESS+'</option>');
					}
				}
			},
			error: function(){
			}         
		});

		jqxhr=$.ajax({
			type: "POST",
			url:"../../claq/qycomp",
			data:{},
			dataType: 'json',
			timeout : 3600000,
			success:function(json){
				console.log(json);
				var data= json.datacomp;
				for (var i = 0; i < data.length; i++) {
					data[i].id=data[i].NAME;
					data[i].text=data[i].NAME;
				}
				var qb={};
				qb.id='null';
				qb.text='全部';
				data.unshift(qb);
				$('#ystp-gs').select2({
					data: data,
					allowClear: true
				});
			}
		});
		
		var bljlFields = [
		                  	{name: 'id', title: '序号', width: 60, align: 'center'},
			    			{name: 'cphm', title: '车牌号', width: 120, align: 'center'},
			    			{name: 'ssgs', title: '所属公司', width: 120, align: 'center'},
							{name: 'zpsj', title: '抓拍时间', width: 120, align: 'center'},
							{name: 'zpdd', title: '抓拍地点', width: 120, align: 'center'},
			    			{name: 'zpjwd', title: '抓拍经纬度', width: 120, align: 'center'},
			    			{name: 'dwsj', title: '定位时间', width: 120, align: 'center'},
			    			{name: 'dwdd', title: '定位地点', width: 120, align: 'center'},
			    			{name: 'dwjwd', title: '定位经纬度', width: 120, align: 'center'},
			    			{name: 'caozuo', title: '操作',
			    				itemTemplate:
			    					function(value, item) {
				    					var style = {marginRight: '4px'};
				    					return [
											$('<a>').addClass('btn btn-primary btn-sm').text('详情').css(style).on('click', function () {
												var key = "pic_"+item.cphm+"_"+item.zpdd+"_"+item.zpsj.replace("-","").replace("-","").replace(" ","").replace(":","").replace(":","")+".jpg";
												document.getElementById('ystpbig').src = "../../common/pic?key="+key;
												imagebigscale("ystpbigImage", "ystpbig", "ystpimagetool", "ystpclose", "ystpleft-rote", "ystpright-rote", "ystpbigger", "ystpsmaller");
												document.getElementById('ystpbigImage').style.display = 'block';
												document.getElementById('ystpbigImage').style.top = '0px';
												document.getElementById('ystpbigImage').style.left = '30px';
											}),
											$('<a>').addClass('btn btn-primary btn-sm').text('轨迹').on('click', function () {
												findGj(item.cphm, item.zpsj);
											})
		    					        ]
			    				}, width: 100, align: 'center'}
			    		];
		$('#ystp-select').on('click', function () {
			findystp();
		});

		var allData=null;
		function findGj(vehicle,time){
			if(time == ""){
				return layer.msg("抓拍时间为空！");
			}
			if(vehicle == ""){
				return layer.msg("车牌号码为空！");
			}
			var stime =new Date(new Date(time).getTime() - 0.5*60*60*1000).Format("yyyy-MM-dd hh:mm:ss");
			var etime =new Date(new Date(time).getTime() + 0.5*60*60*1000).Format("yyyy-MM-dd hh:mm:ss");
			layer.load(2);
			jqxhr=$.ajax({
				type: "POST",
				url:"../../claq/findGj",
				data:{
					"stime" : stime,
					"etime" : etime,
					"vehino" : vehicle
				},
				dataType: 'json',
				timeout : 3600000,
				cache:true,
				success:function(json){
					$('#ystp-dialog').modal('show');
					var gjhfgzData=[];
					console.log(json)
					if(json.code == 0){
						var d = [];
						var a = {};
						a.path = [];
						a.name = "1";
						if(json.data.length == 0){
							layer.msg('无轨迹数据');
							layer.closeAll('loading');
							return;
						}
						map.clearMap();
						allData=json.data;
						var lc=0;
						for(var i = 0; i< json.data.length ;i++){
							if(i>0){
								var l1 = new AMap.LngLat(json.data[i].PX,json.data[i].PY);
								var l2 = new AMap.LngLat(json.data[i-1].PX,json.data[i-1].PY);
								lc += l1.distance(l2);
								json.data[i].LC =parseFloat((lc/1000).toFixed(2));
							}else{
								json.data[i].LC = 0;
							}
							addmks(json.data[i]);
							var rs={};
							rs.date = formatYYYYMMDDHHMISS(json.data[i].SPEED_TIME);
							rs.speed = json.data[i].SPEED;
							rs.direction = fxzh(json.data[i].DIRECTION);
							rs.position = json.data[i].PX+","+json.data[i].PY;
							rs.mileage = json.data[i].LC;
							rs.state = (json.data[i].STATE=="0"?"空车":(json.data[i].STATE=="1"?"重车":""));
							gjhfgzData.push(rs);
							var everypath = [];
							everypath.push(json.data[i].PX);
							everypath.push(json.data[i].PY);
							a.path.push(everypath);
						}
						d.push(a)
						if(pathSimplifierIns){
							pathSimplifierIns.setData(null);
						}
						//地图轨迹
						AMapUI.load(['ui/misc/PathSimplifier', 'lib/$'], function(PathSimplifier, $) {
							if (!PathSimplifier.supportCanvas) {
								alert('当前环境不支持 Canvas！');
								return;
							}
							var colors = ["#3366cc", "#dc3912"];
							pathSimplifierIns = new PathSimplifier({
								zIndex: 100,
								map: map, //所属的地图实例
								getPath: function(pathData, pathIndex) {
									return pathData.path;
								},
								renderOptions: {
									pathLineStyle: {
										dirArrowStyle: true
									},
									getPathStyle: function(pathItem, zoom) {
										var color = colors[pathItem.pathIndex],
											lineWidth = Math.round(2 * Math.pow(1.1, zoom - 3));
										return {
											pathLineStyle: {
												strokeStyle: color,
												lineWidth: lineWidth
											},
											pathLineSelectedStyle: {
												lineWidth: lineWidth + 2
											},
											pathNavigatorStyle: {
												fillStyle: color
											}
										}
									}
								}
							});
							function onload() {
								pathSimplifierIns.renderLater();
							}

							function onerror(e) {
								alert('图片加载失败！');
							}

							function getNavg() {
								//创建一个轨迹巡航器
								var navg = pathSimplifierIns.createPathNavigator(0,{
//	          			                loop: true,
									speed: 5000,
									pathNavigatorStyle: {
										width: 50,
										height: 50,
										// content: PathSimplifier.Render.Canvas.getImageContent('../../resources/images/car/z1.png', onload, onerror),
										zIndex:102,
										strokeStyle: null,
										fillStyle: null
									}
								});
								// navg.start()
							}
							function initRoutesContainer() {
								var navg = getNavg();
							}
							window.pathSimplifierIns = pathSimplifierIns;
							$('<div id="loadingTip">加载数据，请稍候...</div>').appendTo(document.body);
							$('#loadingTip').remove();
							var flyRoutes = [];
							d.push.apply(d, flyRoutes);
							pathSimplifierIns.setData(d);
							initRoutesContainer();
							pathSimplifierIns.on('selectedPathIndexChanged', function(e, info) {
							});
							pathSimplifierIns.on('pointClick pointMouseover pointMouseout', function(e, record) {
								//console.log(e.type, record);
							});
							pathSimplifierIns.on('pathClick pathMouseover pathMouseout', function(e, record) {
								//console.log(e.type, record);
							});
						});
					}else{
					}
					layer.closeAll('loading');
				},
				error:function(){
					layer.closeAll('loading');
//	          			layer。msg("数据异常");
				}
			});
		}

		function addmks(obj){
			if(obj.hasOwnProperty('PX')&&obj.hasOwnProperty('PY')){
				var marker = new AMap.Marker({
					position:new AMap.LngLat(obj.PX,obj.PY),
					offset:new AMap.Pixel(-14,-17), //相对于基点的偏移位置
					draggable:false,  //是否可拖动
					icon:obj.STATE=="0"?"../../resources/images/kcl.png":"../../resources/images/zcl.png", //marker图标，直接传递地址url
					zIndex:100,
					content: ''   //自定义点标记覆盖物内容
				});
				marker.setMap(map);  //在地图上添加点
				AMap.event.addListener(marker,"click",function(e){
					addMapMarker(obj);
				});
			}
		}
		/* 将单个车辆的位置信息显示在地图上 */
		function addMapMarker(item) {
			if(mapMarker) mapMarker.setMap(null);
			mapMarker = new AMap.Marker({
				map: map,
				position: new AMap.LngLat(item.PX, item.PY),
				offset:new AMap.Pixel(-14,-17), //相对于基点的偏移位置
				draggable: false,  //是否可拖动
				icon:item.STATE=="0"?"../../resources/images/kcl.png":"../../resources/images/zcl.png", //marker图标，直接传递地址url
				zIndex:100,
				content: ''   //自定义点标记覆盖物内容
			});
			map.setCenter(new AMap.LngLat(item.PX,item.PY));
			var txt = "<table><tr><td><b style='color:#3399FF'>"+item.vehicle_num+"</b></td>" +
				"<tr><td><b>[速度(km/h)]</b>："+item.SPEED+"</td></tr>" +
				"<tr><td><b>[方向]</b>："+fxzh(item.DIRECTION)+"</td></tr>" +
				"<tr><td><b>[总里程(km)]</b>："+item.LC+"</td></tr>" +
				"<tr><td><b>[空重车]</b>："+(item.STATE=="0"?"空车":(item.STATE=="1"?"重车":""))+"</td></tr>" +
				"<tr><td><b>[经度]</b>："+item.PX+"</td></tr><tr><td><b>[纬度]</b>："+item.PY+"</td></tr>" +
				"<tr><td><b>[经纬度上传时间]</b>："+formatYYYYMMDDHHMISS(item.SPEED_TIME);+"</td></tr>" +
			"";
			var info = [];
			info.push(txt);
			var inforWindowone = new AMap.InfoWindow({
				offset: new AMap.Pixel(-0,-0),
				content: info.join("</table>")
			});
			inforWindowone.open(map,mapMarker.getPosition());
			AMap.event.addListener(mapMarker, "click", function (e) {
				inforWindowone.open(map, mapMarker.getPosition());
			});
		}

		function findystp(){
			var all = 0;
			$('#ystpTable').jsGrid({
				width: '100%',
				height: 'calc(100% - 55px)',
				autoload: true,
				paging: true,
				pageLoading: true,
				editing: true,
				sorting: true,
				pageSize: 15,
				pageIndex: 1,
				controller: {
                    loadData: function(filter) {
                    	var d = $.Deferred();
                    	var a = re(filter, function(item){
                    		d.resolve(item);
                    	})
                    	return d.promise();
                    }
                },
                fields: bljlFields,
				pagerContainer: null,
			    pageButtonCount: 5,
			    pagerFormat: "{first} {prev} {pages} {next} {last} {pageIndex} of {pageCount}",
			    pagePrevText: "上一页",
			    pageNextText: "下一页",
			    pageFirstText: "第一页",
			    pageLastText: "末页",
			    pageNavigatorNextText: ">",
			    pageNavigatorPrevText: "<"
          	});

//			setTimeout("findystp()",1000);
		}
		function re(filter, callback){
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            jqxhr=jqxhr=$.ajax({
            	type: "POST",
				url: "../../ycyy/findystp",
				data:{"stime":$("#ystp-stateTime").val(),
					"etime":$("#ystp-endTime").val(),
					"vehicle":$("#ystp-cphm").val(),
					"company": $("#ystp-gs").val(),
					"type":'',
					"address":$("#ystp-address").val(),
					"pageIndex":filter.pageIndex,
     				"pageSize":filter.pageSize
					},
				timeout : 180000,
				dataType: 'json',
            }).done(function(data) {
            		var jsycxData = [];
     				all = data[0].count;
         			if(data.length>0){
         				for(var i = 0; i< data[0].datas.length ;i++){
         					var rs={};
         					var vehicle=data[0].datas[i];
         					rs.id =  startIndex+i+1;
         					rs.cphm =  data[0].datas[i].VEHICLE_NO;
         					rs.ssgs =  data[0].datas[i].COMPANY_NAME;
         					rs.zpsj =  (data[0].datas[i].HK_TIME==null?"":(new Date(data[0].datas[i].HK_TIME)).Format("yyyy-MM-dd hh:mm:ss"));
         					rs.zpdd =  data[0].datas[i].HK_ADDRESS;
         					rs.zpjwd =  data[0].datas[i].ZX_POINT;
         					rs.dwsj =  (data[0].datas[i].SPEED_TIME==null?"":(new Date(data[0].datas[i].SPEED_TIME)).Format("yyyy-MM-dd hh:mm:ss"));
         					rs.dwdd =  data[0].datas[i].GPS_ADDRESS;
         					rs.dwjwd =  data[0].datas[i].GPS_POINT;
         					jsycxData.push(rs);
         				}
         				return callback({
                            data: jsycxData,
                            itemsCount: all
                        });

         			}else{
        			}
            }).fail(function() {
//        			alert("数据异常");
            });
		}
		findystp();
		$('#ystp-reset').on('click', function () {
			$('.panel-queryBar .form-control').val('');
			$('.panel-queryBar .select2').val([""]).trigger("change");
		});
		$('#ystp-dc').on('click', function () {
			layer.confirm('你确定要导出数据', {
				btn: ['确定', '取消'],
				offset: '100px'
			}, function (layerIndex) {
				window.open(basePath+"ycyy/findystpdc?stime="+$("#ystp-stateTime").val()
						+"&etime="+$("#ystp-endTime").val()
						+"&vehicle="+$("#ystp-cphm").val()
						+"&company="+$("#ystp-gs").val()
						+"&type="
						+"&address="+$("#ystp-address").val()
				);
				layer.close(layerIndex);
			}, function (layerIndex) {
				layer.close(layerIndex);
			});
		});
	})
	
})(jQuery)

