var clyysjtj = (function($) {
	var all = 0, re;
	var echart = null;

	var sdqylctjFields = [ {
		name : 'gridId',
		title : '序号',
		width : 60,
		align : 'center'
	}, {
		name : 'VEHICLE_NUM',
		title : '车号',
		width : 100,
		align : "center"
	}, {
		name : 'LONGI',
		title : '经度',
		width : 100,
		align : "center"
	}, {
		name : 'LATI',
		title : '纬度',
		width : 100,
		align : 'center'
	}, {
		name : 'SPEED',
		title : '速度',
		width : 80,
		align : 'center'
	}, {
		name : 'fx',
		title : '方向',
		width : 80,
		align : 'center'
	}, {
		name : 'TIME',
		title : '时间',
		width : 160,
		align : 'center'
	}, {
		name : 'LC',
		title : '里程（km）',
		width : 80,
		align : 'center'
	},
	// {name: 'dlwz', title: '位置信息', width: 250,align:'center'}
	];
	var sdqylctjData = [];

	function hxx() {
		if($("#sdqylctj-carNumber").val()==''){
			layer.msg("请输入车牌号码");
			return false;
		}
		$('#sdqylctjTable').jsGrid({
			width : '100%',
			height : 250,
			autoload : true,
			paging : true,
			pageLoading : true,
//			pageSize : 7,
//			pageIndex : 1,
			controller : {
				loadData : function(filter) {
					var d = $.Deferred();
					var a = res(filter,function(item) {
					d.resolve(item);
				})
				return d.promise();
				}
			},
			fields:sdqylctjFields,
			pagerContainer : null,
			pageButtonCount : 5,
			pagerFormat : "{first} {prev} {pages} {next} {last} {pageIndex} of {pageCount}",
			pagePrevText : "上一页",
			pageNextText : "下一页",
			pageFirstText : "第一页",
			pageLastText : "末页",
			pageNavigatorNextText : ">",
			pageNavigatorPrevText : "<"
		});
	}

	function res(filter, callback) {
		var startIndex = (filter.pageIndex - 1) * filter.pageSize;
		jqxhr=$.ajax({
			url : "../../getYdcsdqxjlctjFindAlls",
			data : {
				"kssj" : $("#sdqylctj-datetimeStart").val(),
				"jssj" : $("#sdqylctj-datetimeEnd").val(),
				"cph" : $("#sdqylctj-carNumber").val(),
				"csz" : $("#sdqylctj-csz").val(),
//				"pageIndex" : filter.pageIndex,
//				"pageSize" : filter.pageSize
			},
			dataType : 'json'
		}).done(function(json) {
			var clcxData =[];
			var c=[],d=[];
			console.log(json)
			// console.log(json)
			all = json.data[0].list.length;
			re = json.data[0].list;
			if(json.code == 0){
 				for(var i = 0; i< re.length ;i++){
 					var rs={};
 					rs.gridId = startIndex+i+1;
 					rs.VEHICLE_NUM =  re[i].VEHICLE_NUM;
   					rs.LONGI =  re[i].LONGI;
 					rs.LATI =  re[i].LATI;
 					rs.SPEED =  re[i].SPEED;
 					rs.fx =  fxzh(re[i].DIRECTION);
 					rs.TIME =  re[i].TIME;
 					rs.LC =  (parseFloat(re[i].lc)/100).toFixed(2);
 					clcxData.push(rs);
 					c.push(re[i].SPEED);
 					d.push(re[i].TIME);
 				}
 				drawEcharts(d,c);
 				return callback({
                    data: clcxData,
                    itemsCount: all
                });
 			}else{
			}
		}).fail(function() {
//			alert("数据异常");
		});
	}
	function drawEcharts(d,c) {
		echart = echarts.init(document.getElementById('sdqylctjEchart'));
		echart.setOption({
			title : {
				text : '车辆速度曲线与及里程统计',
				textStyle : {
					fontWeight : 'normal', // 标题颜色
					color : '#696969'
				},
			},
			grid : {
				left : 100,
				right : 50
			},
			tooltip : {
				trigger : 'axis',
			// formatter: function (params) {
			// console.info('qwqe',params);
			// return params;
			// }
			},
			legend : {
				data : ['速度']
			},
			xAxis : {
				boundaryGap : false,
				name : '时间',
				type : 'category',
				data : d
			},
			yAxis : {
				name : '速度',
				type : 'value'
			},
			series : [
			{
				name : '速度',
				type : 'line',
				stack : '总量',
				areaStyle : {},
				data:c
			}
			]
		});
		$(window).resize(function() {
			echart.resize();
		});
	}

	$(function() {
		var today = new Date();
		var oneday = 1000 * 60 * 60 * 2;
		$('#sdqylctj-datetimeStart').datetimepicker(datetimeDefaultOption);
		$('#sdqylctj-datetimeEnd').datetimepicker(datetimeDefaultOption);
		$('#sdqylctj-datetimeStart').val(new Date(today - oneday).Format('yyyy-MM-dd hh:mm:ss'));
		$('#sdqylctj-datetimeEnd').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));
		
		$('.addTimePeriod, .period').on('click', function() {
			if ($(this).hasClass('addTimePeriod'))
				$(this).addClass('period').removeClass('addTimePeriod');
			else if ($(this).hasClass('period'))
				$(this).addClass('addTimePeriod').removeClass('period');
		});

		jqxhr=$.ajax({
			type : "POST",
			url : "../../claq/qyveh",
			data : {},
			dataType : 'json',
			timeout : 3600000,
			success : function(json) {
				console.log(json);
				var data = json.dataveh;
				for (var i = 0; i < data.length; i++) {
					data[i].id = data[i].PLATE_NUMBER;
					data[i].text = data[i].PLATE_NUMBER;
				}
				var qb = {};
				qb.id = '0';
				qb.text = '全部';
				data.unshift(qb);
				$('#sdqylctj-carNumber').select2({
					language: "zh-CN",
					data : data,
					minimumInputLength: 3,
					allowClear : true
				});
			}
		});

		

		$('.scrollbar-macosx').scrollbar();

		$("#select_num").off('click').on('click', function() {
			if(!validtion($('#sdqylctj-datetimeStart').val(),$('#sdqylctj-datetimeEnd').val())){
            	layer.msg("无法跨月查询");
            	return;
            }
			hxx();
		})
		
		//验证跨月
		function validtion(a,b){
			if(a.replace("-","").substring(4,6) != b.replace("-","").substring(4,6)){
				return false;
			}else{
				return true;
			}
		}

		$("#dcsdqx_Export").off('click').on('click',function() {
			if(!validtion($('#sdqylctj-datetimeStart').val(),$('#sdqylctj-datetimeEnd').val())){
            	layer.msg("无法跨月查询");
            	return;
            }
			layer.confirm('需要导出吗 ?', {
				btn : [ '确认', '取消' ]
			}, function() {
				window.open(basePath + "getYdcsdqxjlctjExpport?kssj="
						+ $("#sdqylctj-datetimeStart").val() + "&jssj="
						+ $("#sdqylctj-datetimeEnd").val() + "&cph="
						+ $("#sdqylctj-carNumber").val() + "&csz="
						+ $("#sdqylctj-csz").val())
				layer.msg('导出成功？', {
					icon : 1
				});
			}, function() {
				layer.msg('已取消导出', {
					icon : 2
				});
			})
		})
	})

})(jQuery);
