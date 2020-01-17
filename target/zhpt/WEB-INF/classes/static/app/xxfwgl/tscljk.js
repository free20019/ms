var tscljk = (function($) {
	$(function () {
		$('.scrollbar-macosx').scrollbar();

		$('#tsclcxTab .mp-tabs-item').on('click', function () {
			var skip = $(this).attr('skip');
			console.info('skip:', skip)
			$(this).addClass('active').siblings('.active').removeClass('active')
			$('#tsclcxTab .mp-tabs-body').find('#'+skip).addClass('active').siblings('.active').removeClass('active')
		});

		$('#tsclcx-datetimeStart').datetimepicker(datetimeDefaultOption);
		$('#tsclcx-datetimeStart').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));
		$('#tsclcx-datetimeEnd').datetimepicker(datetimeDefaultOption);
		$('#tsclcx-datetimeEnd').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));

		$('.form-control[type=calendar] .glyphicon-calendar').on('click', function () {
			$(this).next('input').trigger('focus');
		});
		titlePanelScaling('#tsclcxPanel', {width: 360});

		var shiwumap = new AMap.Map('tsclcxMap', {
			zoom:14,
			center: new AMap.LngLat(120.153576,30.287459)
		});
		var stime="";
		var etime="";
		var mapMarkers= [];
		var pathSimplifierIns =null;
		var inforWindowone =null;
		var fxudmouseTool = null;
		var polygonfk = null;
		var polygonfks = [null, null];
		var udpolygonOption = {
			strokeColor: "",
			strokeOpacity: 0,
			strokeWeight: 1
		};
		var isStart = false, isEnd = false;
		var mouseTool = null;
		var qd_jwd = "", zd_jwd = "";
		var kt1 = new Array(), kt2 = new Array();
		var zj;


		setTimeout(function () {
			$($($(window.parent.document)).find('.ip-menuItem').get(3)).find('.ip-menuTitle').click();
		}, 1000)


		//起点
		$('#dwqdfw').click(function () {
			if (!isStart) isStart = arawAreaFun(shiwumap, isStart, 1);
		});
		//终点
		$('#dwzdfw').click(function () {
			if (!isEnd) isEnd = arawAreaFun(shiwumap, isEnd, 2);
		});
		//清除地图
		$('#clearMap').click(function () {
			if (polygonfk != null) {
				shiwumap.clearMap();
				if(pathSimplifierIns){
					pathSimplifierIns.setData(null);
				}
			}
			qd_jwd = "";
			zd_jwd = "";
			isStart = false;
			isEnd = false;
			polygonfks = [null, null];
			$('#qdfwPanel table tbody, #clxxPanel table tbody, #zdfwPanel table tbody').empty();
		});
		//导出
		$('#swcz_dc').on('click', function () {
			if ($('#clxxPanel table tbody').html() == "") {
				alert("无数据无法导出信息!");
			} else {
				var data = {
					"qd_stime": timestart($("#tsclcx-datetimeStart").val(), $("#qd_etime").val()),
					"qd_etime": timeend($("#tsclcx-datetimeStart").val(), $("#qd_etime").val()),
					"zd_stime": timestart($("#tsclcx-datetimeEnd").val(), $("#zd_etime").val()),
					"zd_etime": timeend($("#tsclcx-datetimeEnd").val(), $("#zd_etime").val()),
					"qd_jwd": qd_jwd,
					"zd_jwd": zd_jwd,
				};
				url = "http://115.236.61.148:9085/zhpt/claq/findswczexcle?data=" + JSON.stringify(data) , window.open(url)
			}
		});


		//查询
		$('#swcz_cx').on('click', function () {
			if (qd_jwd == "" || zd_jwd == "") {
				alert("请先选择区域!");
				return;
			}
			layer.load(2);
			$("#swcz_cx").attr('disabled', "true");
			stime = timestart($("#tsclcx-datetimeStart").val(), $("#qd_etime").val());
			etime = timeend($("#tsclcx-datetimeEnd").val(), $("#zd_etime").val());
			jqxhr=$.ajax({
				type: "POST",
				url: "http://115.236.61.148:9085/zhpt/claq/findswcz",
				data: {
					"qd_stime": timestart($("#tsclcx-datetimeStart").val(), $("#qd_etime").val()),
					"qd_etime": timeend($("#tsclcx-datetimeStart").val(), $("#qd_etime").val()),
					"zd_stime": timestart($("#tsclcx-datetimeEnd").val(), $("#zd_etime").val()),
					"zd_etime": timeend($("#tsclcx-datetimeEnd").val(), $("#zd_etime").val()),
					"qd_jwd": qd_jwd,
					"zd_jwd": zd_jwd,
				},
				dataType: 'json',
				timeout: 3600000,
				success: function (json) {
					if(mapMarkers.length>0){
						for (var j = 0; j < mapMarkers.length; j++) {
							mapMarkers[j].setMap(null);
						}
					}
					if(inforWindowone!=null){
						inforWindowone.close();
					}
					if(pathSimplifierIns){
						pathSimplifierIns.setData(null);
					}

					console.log(json)
					$('#qdfwPanel table tbody, #clxxPanel table tbody, #zdfwPanel table tbody').empty();
					console.log(json[0].length + "   " + json[1].length + "   " + json[2].length)
					if (json[0].length > 0 || json[1].length > 0 || json[2].length > 0) {
						console.log(json)
						var qd = json[0];
						var zd = json[1];
						zj = json[2];
						for (var i = 0; i < qd.length; i++) {
							var trTag = $('<tr>');
							$('<td>').addClass('t_vehicle').text(qd[i].vehi_no).appendTo(trTag);
							$('<td>').addClass('t_datetime').text(qd[i].stime.replace(",","")).appendTo(trTag);
							$('#qdfwPanel table tbody').append(trTag);
						}
						for (var i = 0; i < zd.length; i++) {
							var trTag = $('<tr>');
							$('<td>').addClass('t_vehicle').text(zd[i].vehi_no).appendTo(trTag);
							$('<td>').addClass('t_datetime').text(zd[i].stime.replace(",","")).appendTo(trTag);
							$('#zdfwPanel table tbody').append(trTag);
						}
						var icon = "../../resources/images/c.png";
						var marker1;
						for (var i = 0; i < zj.length; i++) {
							var trTag = $('<tr>');
							$('<td>').addClass('t_vehicle').text(zj[i].vehi_no).appendTo(trTag);
							var operate = $('<td>').addClass('t_operate');
							operate.append(
								$('<button>').addClass('btn btn-primary btn-xs guijichaxun').data('ip-index', i).text('轨迹').on('click', function () {
									var index = $(this).data('ip-index');
									// var _parent = parent.$(window.parent.document);
									// if (_parent.find('.ip-tabBarItem[data-name="gjhfgz"]').length > 0) {
									// 	_parent.find('.ip-tabBarItem[data-name="gjhfgz"]').trigger('click');
									// 	_parent.find('#gjhfgz').get(0).contentWindow.location.reload(true);
									// }
									// _parent.find('#defaultVehi').val(zd[index].vehi_no);
									// _parent.find('#defaultStime').val(timestart($("#tsclcx-datetimeStart").val(), $("#qd_etime").val()));
									// _parent.find('#defaultEtime').val(timeend($("#tsclcx-datetimeEnd").val(), $("#zd_etime").val()));
									// _parent.find('.ip-menuItem [data-skip="gjhfgz"]').trigger("click");
									//车辆轨迹
									layer.load(2);
									jqxhr=$.ajax({
										type: "POST",
										url:"../../claq/findGj",
										data:{
											"stime" : stime,
											"etime" : etime,
											"vehino" : zj[index].vehi_no
										},
										dataType: 'json',
										timeout : 3600000,
										cache:true,
										success:function(json){
											gjhfgzData=[];
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
												if(mapMarkers.length>0){
													for (var j = 0; j < mapMarkers.length; j++) {
														mapMarkers[j].setMap(null);
													}
												}
												if(inforWindowone!=null){
													inforWindowone.close();
												}
												// shiwumap.clearMap();
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
														map: shiwumap, //所属的地图实例
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
//	          			              						loop: true,
															speed: 5000,
															pathNavigatorStyle: {
																width: 50,
																height: 50,
																content: PathSimplifier.Render.Canvas.getImageContent('../../resources/images/car/z1.png', onload, onerror),
																zIndex:102,
																strokeStyle: null,
																fillStyle: null
															}
														});

														$('.glyphicon-play').on('click',function() {
															navg.start(0);
														});

														$('.glyphicon-repeat').on('click',function() {
															navg.resume();
														});

														$('.glyphicon-stop').on('click',function() {
															navg.stop();
														});

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
										}
									});




								})
							);
							operate.appendTo(trTag);
							$('#clxxPanel table tbody').append(trTag);
						}
						$('#swcz_cx').removeAttr("disabled");
						layer.closeAll('loading');
					} else {
						$('#swcz_cx').removeAttr("disabled");
						layer.closeAll('loading');
						alert("该时间内起点与终点区域内没有符合条件的车辆");
					}
				},
				error: function () {
					layer.closeAll('loading');
				}
			});
		});

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
				mapMarkers.push(marker);
				marker.setMap(shiwumap);  //在地图上添加点
				AMap.event.addListener(marker,"click",function(e){
					addMapMarker(obj);
				});
			}
		}

		/* 将单个车辆的位置信息显示在地图上 */
		function addMapMarker(item) {
			shiwumap.setCenter(new AMap.LngLat(item.PX,item.PY));
			var txt = "<table><tr><td><b style='color:#3399FF'>"+item.vehicle_num+"</b></td>" +
				//					"<td></td></tr><tr><td><b>[所属业户]</b>："+item.COMP_NAME+"</td></tr>" +
				//					"<tr><td><b>[SIM卡]</b>："+item.VEHI_SIM+"</td></tr>" +
				"<tr><td><b>[速度(km/h)]</b>："+item.SPEED+"</td></tr>" +
				"<tr><td><b>[方向]</b>："+fxzh(item.DIRECTION)+"</td></tr>" +
				"<tr><td><b>[总里程(km)]</b>："+item.LC+"</td></tr>" +
				"<tr><td><b>[空重车]</b>："+(item.STATE=="0"?"空车":(item.STATE=="1"?"重车":""))+"</td></tr>" +
				"<tr><td><b>[经度]</b>："+item.PX+"</td></tr><tr><td><b>[纬度]</b>："+item.PY+"</td></tr>" +
				"<tr><td><b>[经纬度上传时间]</b>："+formatYYYYMMDDHHMISS(item.SPEED_TIME);+"</td></tr>" +
			"";
			var info = [];
			info.push(txt);
			inforWindowone = new AMap.InfoWindow({
				offset: new AMap.Pixel(-0,-0),
				content: info.join("</table>")
			});
			inforWindowone.open(shiwumap,[item.PX,item.PY]);
		}
		//画框
		function arawAreaFun(mapObj, isArea, num) {
			console.info(num);
			if (isArea) return false;
			mapObj.plugin(["AMap.MouseTool"], function () {
				fxudmouseTool = new AMap.MouseTool(mapObj);
				fxudmouseTool.polygon(udpolygonOption);   //使用鼠标工具绘制多边形
				AMap.event.addListener(fxudmouseTool, "draw", function (e) {
					var drawObj = e.obj;
					var pointsCount = e.obj.getPath().length;
					var a = e.obj.getPath();
					var zbs = "";
					var polygonArr1 = [];//多边形覆盖物节点坐标数组
					for (var i = 0; i < pointsCount; i++) {
						polygonArr1.push([a[i].lng, a[i].lat]);
					}
					polygonfk = fanwei(polygonArr1, num);
					polygonfk.setMap(mapObj);
					if (num == 1) polygonfks[0] = polygonfk;
					else polygonfks[1] = polygonfk;
					fxudmouseTool.close(true);
					fxudmouseTool = null;
					$('#clearMap').show();
				});
			});
			return true
		}

		// //轨迹 --- 跳转
		// findlsgj = function(obj){
		// 	console.log(zj[obj].vehi_no)
		// 	var li = $($(window.parent.document)).find('.ip-tabBarItem');
		// 	for(var i=0;i<li.length;i++){
		// 		if(li.get(i).getAttribute('data-name') == "gjhfgz"){
		// 			$($(window.parent.document)).find('.ip-tabItemRemove').get(i).click();
		// 		}
		// 	}
		// 	parent.$($($(window.parent.document)).find('#defaultVehi')).val(zj[obj].vehi_no);
		// 	parent.$($($(window.parent.document)).find('.ip-menuItem').get(3)).find('.ip-menuTitle').trigger("click");
		// }

		//范围坐标
		function fanwei(polygonArr1, num) {
			if (1 == num) {
				qd_jwd = "";
				for (var i = 0; i < polygonArr1.length; i++) {
					qd_jwd += polygonArr1[i] + ";";
					kt1.push(polygonArr1[i]);
				}
			}
			if (2 == num) {
				zd_jwd = "";
				for (var i = 0; i < polygonArr1.length; i++) {
					zd_jwd += polygonArr1[i] + ";";
					kt2.push(polygonArr1[i]);
				}
			}
			return new AMap.Polygon({
				path: polygonArr1,//设置多边形边界路径
				strokeColor: "#1791fc", //线颜色
				strokeOpacity: 1, //线透明度
				strokeWeight: 2,    //线宽
				fillColor: "#1791fc", //填充色
				fillOpacity: .5//填充透明度
			});
		}

		//开始时间
		function timestart(e, c) {
			var ddate = new Date(e);
			var result = new Date(ddate.getTime() - parseInt(c) * 60 * 1000);
			return setformatnewlc(result);
		}

		//结束时间
		function timeend(e, c) {
			var ddate = new Date(e);
			var result = new Date(ddate.getTime() + parseInt(c) * 60 * 1000);
			return setformatnewlc(result);
		}

		//截取时间
		function setformatnewlc(obj) {
			var y = (obj.getFullYear()).toString();
			var m = (obj.getMonth() + 1).toString();
			if (m.length == 1) {
				m = "0" + m;
			}
			var d = obj.getDate().toString();
			if (d.length == 1) {
				d = "0" + d;
			}
			var h = obj.getHours().toString();
			if (h.length == 1) {
				h = "0" + h;
			}
			var mi = obj.getMinutes().toString();
			if (mi.length == 1) {
				mi = "0" + mi;
			}
			var s = obj.getSeconds().toString();
			if (s.length == 1) {
				s = "0" + s;
			}
			var time = y + "-" + m + "-" + d + " " + h + ":" + mi + ":" + s;
			console.log(time)
			return time;
		}
	})
})(jQuery);
