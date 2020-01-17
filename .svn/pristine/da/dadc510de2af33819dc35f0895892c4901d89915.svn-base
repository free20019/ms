var gjhfgz=(function ($) {
		var today = new Date();
		var oneday = 1000 * 60 * 60 * 1;
		var gjhfgzFields = [
			{name: 'date', title: '时间', width: 140, align: 'center'},
			{name: 'speed', title: '速度', width: 80, align: 'center'},
			{name: 'direction', title: '方向', width: 80, align: 'center'},
			{name: 'state', title: '空重车', width: 80, align: 'center'},
			{name: 'mileage', title: '总里程', width: 80, align: 'center'},/*,
			{name: 'position', title: '位置', width: 200, align: 'center'}*/
		];
		var gjhfgzData = [
// 			{date: '2018-08-01', speed: '61', direction: '1', position: '120.11111111,30.12512551'},
		];
		$(function () {
			$('.scrollbar-macosx').scrollbar();
			titlePanelScaling('#gjhfgzPanel', {width: 530});
			var map = new AMap.Map('gjhfgzMap', {
				zoom:14,
				center: new AMap.LngLat(120.153576,30.287459)
			});
			var pathSimplifierIns =null;
			var mapMarker=null;
			$('#gjhfgz-datetimeStart').datetimepicker(datetimeDefaultOption);
			$('#gjhfgz-datetimeEnd').datetimepicker(datetimeDefaultOption);
			$('#gjhfgz-datetimeStart').val(new Date(today - oneday).Format('yyyy-MM-dd hh:mm:ss'));
			$('#gjhfgz-datetimeEnd').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));
			
			$(".select2").select2({  
			  	language: "zh-CN",  //设置 提示语言
		        tags:true,  
		        createTag:function (decorated, params) {  
		            return null;  
		        },  
		    });
			findVehicle();
			function findVehicle(){		
//				if($($($(window.parent.document)).find('#defaultVehi')).val() != ""){
//					jqxhr=$.ajax({
//						type: "POST",
//						url:"../../claq/qyveh",
//						data:{},
//						dataType: 'json',
//						timeout : 3600000,
//						success:function(json){
//							console.log(json);
//							var data= json.dataveh;
//							for (var i = 0; i < data.length; i++) {
//								data[i].id=data[i].PLATE_NUMBER;
//								data[i].text=data[i].PLATE_NUMBER;
//							}
//							var qb={};
//							qb.id='0';
//							qb.text='全部';
//							data.unshift(qb);
//							$('#gjhfgz-licensePlate').select2({
//								data: data,
//								allowClear: true,
//								language:'zh-CN',
//			                    minimumInputLength: 3
//							});
//							//读取defaultVehi的值  时间
//							if($($($(window.parent.document)).find('#defaultVehi')).val() != ""){
//								layer.load(2);
//								$("#gjhfgz-licensePlate").val($($($(window.parent.document)).find('#defaultVehi')).val()).trigger("change");
//								if($($($(window.parent.document)).find('#defaultEtime')).val() != "" && $($($(window.parent.document)).find('#defaultStime')).val() != ""){
//									$("#gjhfgz-datetimeStart").val($($($(window.parent.document)).find('#defaultStime')).val()).trigger("change");
//									$("#gjhfgz-datetimeEnd").val($($($(window.parent.document)).find('#defaultEtime')).val()).trigger("change");
//								}
//								$($($(window.parent.document)).find('#defaultStime')).val("");
//								$($($(window.parent.document)).find('#defaultEtime')).val("");
//								$($($(window.parent.document)).find('#defaultVehi')).val("");
//								layer.closeAll('loading');
//							}
//						}
//					});
//				}else{					
					$(".select2").select2({
						language:'zh-CN',
						allowClear: true,
						minimumInputLength: 3,
						ajax:{
							type: "POST",
							url:"../../claq/qyvehyb",
							dataType: 'json',
							timeout : 3600000,
							data:function(params){
								return {
									name: params.term
								};
							},
							cache:true,
							processResults: function (data, params) {
								console.log("params",params)
								console.log("data",data)
								for (var i = 0; i < data.length; i++) {
									data[i].id = data[i].PLATE_NUMBER;
									data[i].text = data[i].PLATE_NUMBER;
								}
								return {
									results: data
								};
							},
							escapeMarkup: function (markup) { return markup; }		        
						} 
					});
					//读取defaultVehi的值  时间
					if($($($(window.parent.document)).find('#defaultVehi')).val() != ""){			            
						$("#gjhfgz-licensePlate").append('<option value="'+$($($(window.parent.document)).find('#defaultVehi')).val()+'">'+$($($(window.parent.document)).find('#defaultVehi')).val()+'</option>');
						layer.load(2);
						$("#gjhfgz-licensePlate").val($($($(window.parent.document)).find('#defaultVehi')).val()).trigger("change");
						if($($($(window.parent.document)).find('#defaultEtime')).val() != "" && $($($(window.parent.document)).find('#defaultStime')).val() != ""){
							$("#gjhfgz-datetimeStart").val($($($(window.parent.document)).find('#defaultStime')).val()).trigger("change");
							$("#gjhfgz-datetimeEnd").val($($($(window.parent.document)).find('#defaultEtime')).val()).trigger("change");
						}
						$($($(window.parent.document)).find('#defaultStime')).val("");
						$($($(window.parent.document)).find('#defaultEtime')).val("");
						$($($(window.parent.document)).find('#defaultVehi')).val("");
						layer.closeAll('loading');
					}
//				}
			}
			
			$('#gjhfgzTable').jsGrid({
				width: '100%',
				height: 'calc(100% - 80px)',
				editing: true,
				sorting: true,
				paging: false,
				autoload: true,
				data: gjhfgzData,
				rowClick:handleJumpVehicleClick,
				fields: gjhfgzFields
			});

			function handleJumpVehicleClick(obj) {
				for(var i = 0; i< allData.length ;i++){
					if (formatYYYYMMDDHHMISS(allData[i].SPEED_TIME)==obj.item.date) {
						console.log("1111111111111=",allData[i])
						addMapMarker(allData[i]);
					}
				}			
			}
			//导出
			$("#gjdc").on('click',function(){
				if ($('#gjhfgzTable table tbody').html() == "") {
					alert("无数据无法导出信息!");
				} else {
					var data = {
						"stime" : $("#gjhfgz-datetimeStart").val(),
          				"etime" : $("#gjhfgz-datetimeEnd").val(),
          				"vehino" : $("#gjhfgz-licensePlate option:selected").html()
					};
					url = "../../claq/findGJexcle?data=" + JSON.stringify(data) , window.open(url)
				}
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
				var inforWindowone = new AMap.InfoWindow({
					offset: new AMap.Pixel(-0,-0),
					content: info.join("</table>")
				});
				inforWindowone.open(map,mapMarker.getPosition());
				AMap.event.addListener(mapMarker, "click", function (e) {
					inforWindowone.open(map, mapMarker.getPosition());
				});
			}
			var allData=null;
			//查询
			$("#gjcx").on('click',function(){
				if ($("#gjhfgz-licensePlate").val( ) == "") {
					alert("请填写正确的车牌号码!");
					return;
				}
				layer.load(2);
				jqxhr=$.ajax({
	          		 type: "POST",
	          	        url:"../../claq/findGj",
	          	        data:{
	          				"stime" : $("#gjhfgz-datetimeStart").val(),
	          				"etime" : $("#gjhfgz-datetimeEnd").val(),
	          				"vehino" : $("#gjhfgz-licensePlate").val()
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
	          			                    content: PathSimplifier.Render.Canvas.getImageContent('../../resources/images/car/z1.png', onload, onerror),
	          			                    zIndex:102,
	          			                    strokeStyle: null,
	          			                    fillStyle: null
	          			                }
	          			            });

	          			            $('.glyphicon-play').on('click',function() {
	          			            	navg.start(0);
	          			            });

//	          			            $('.glyphicon-pause').on('click',function() {
//	          			            	navg.pause();
//	          			            });

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


	          				//图表
	          				$('#gjhfgzTable').jsGrid({
	          					width: '100%',
								height: 'calc(100% - 80px)',
	          					editing: true,
	          					sorting: true,
	          					paging: false,
	          					autoload: true,
	          					data: gjhfgzData,
	          					fields: gjhfgzFields,
	          					paging: true,
	          					pagerContainer: null,
	          				    pageIndex: 1,
	          				    pageSize: 15000,
	          				    pageButtonCount: 5,
	          				    pagerFormat: "{first} {prev} {pages} {next} {last} {pageIndex} of {pageCount}",
	          				    pagePrevText: "上一页",
	          				    pageNextText: "下一页",
	          				    pageFirstText: "第一页",
	          				    pageLastText: "末页",
	          				    pageNavigatorNextText: ">",
	          				    pageNavigatorPrevText: "<"
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
			});
		})
	})(jQuery)
