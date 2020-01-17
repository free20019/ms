var gjhfgz=(function ($) {
		var today = new Date();
		var oneday = 1000 * 60 * 60 * 1;
		var gjhfgzFields = [
			{name: 'date', title: '时间', width: 100, align: 'center'},
			{name: 'speed', title: '速度', width: 140, align: 'center'},
			{name: 'direction', title: '方向', width: 80, align: 'center'}/*,
			{name: 'position', title: '位置', width: 200, align: 'center'}*/
		];
		var gjhfgzData = [
// 			{date: '2018-08-01', speed: '61', direction: '1', position: '120.11111111,30.12512551'},
		];
		$(function () {
			$('.scrollbar-macosx').scrollbar();
			titlePanelScaling('#gjhfgzPanel', {width: 530});
			var map = new AMap.Map('gjhfgzMap', {
				zoom:11,
				viewMode:'3D'
			});

			$('#gjhfgz-datetimeStart').datetimepicker(dateDefaultOption);
			$('#gjhfgz-datetimeEnd').datetimepicker(dateDefaultOption);
			$('#gjhfgz-datetimeStart').val(new Date(today - oneday).Format('yyyy-MM-dd hh:mm:ss'));
			$('#gjhfgz-datetimeEnd').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));

			$('#gjhfgzTable').jsGrid({
				width: '100%',
				height: 'calc(100% - 80px)',
				editing: true,
				sorting: true,
				paging: false,
				autoload: true,
				data: gjhfgzData,
				fields: gjhfgzFields
			});

			//读取defaultVehi的值
			if($($($(window.parent.document)).find('#defaultVehi')).val() != ""){
				$("#gjhfgz-licensePlate").val($($($(window.parent.document)).find('#defaultVehi')).val())
			}

			//查询
			$("#gjcx").on('click',function(){
				if ($("#gjhfgz-licensePlate").val( ) == "") {
					alert("请填写正确的车牌号码!");
					return;
				}
				layer.load(2);
				$.ajax({
	          		 type: "POST",
	          	        url:"../../claq/findGj",
	          	        data:{
	          				"stime" : $("#gjhfgz-datetimeStart").val(),
	          				"etime" : $("#gjhfgz-datetimeEnd").val(),
	          				"vehino" : $("#gjhfgz-licensePlate").val(),
	          	        },
	          	        dataType: 'json',
	          			timeout : 3600000,
	          		success:function(json){
	          			console.log(json)
	          			if(json.code == 0){
	          				var d = [];
	          				var a = {};
	          				a.path = [];
	          				a.name = "1";

	          				for(var i = 0; i< json.data.length ;i++){
	          					var rs={};
	          					rs.date = formatYYYYMMDDHHMISS(json.data[i].SPEED_TIME);
	          					rs.speed = json.data[i].SPEED;
	          					rs.direction = fxzh(json.data[i].DIRECTION);
	          					rs.position = json.data[i].PX+","+json.data[i].PY;
	          					gjhfgzData.push(rs);
	          					var everypath = [];
	          					everypath.push(json.data[i].PX);
	          					everypath.push(json.data[i].PY);
	          					a.path.push(everypath);
	          				}
	          				d.push(a)

	          				//地图轨迹
	          				AMapUI.load(['ui/misc/PathSimplifier', 'lib/$'], function(PathSimplifier, $) {
	          			        if (!PathSimplifier.supportCanvas) {
	          			            alert('当前环境不支持 Canvas！');
	          			            return;
	          			        }
	          			        var colors = ["#3366cc", "#dc3912"];
	          			        var pathSimplifierIns = new PathSimplifier({
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
	          			                loop: true,
	          			                speed: 5000,
	          			                pathNavigatorStyle: {
	          			                    width: 24,
	          			                    height: 24,
	          			                    content: PathSimplifier.Render.Canvas.getImageContent('../../resources/images/car.jpg', onload, onerror),
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
	          				    pageSize: 10,
	          				    pageButtonCount: 5,
	          				    pagerFormat: "{first} {prev} {pages} {next} {last} {pageIndex} of {pageCount}",
	          				    pagePrevText: "上一页",
	          				    pageNextText: "下一页",
	          				    pageFirstText: "首页",
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
	          			alert("数据异常");
	          		}
	          	});
			});
		})
	})(jQuery)
