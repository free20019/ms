var czlltj=(function (Vue, _, $) {
	var vm = new Vue({
		el: '#root',
		data: function () {
			return {
				changzhan: [],
				changzhanOptions: []
			}
		},
		mounted: function () {
			var _this = this;
			this.$nextTick(function () {
				_this.getczxll();
			});
		},
		methods: {
			getczxll :function(){
				var _this = this;
				$.ajax({
					type: "POST",
					url: "../../zpsj/xll",
					data: {"field": "ADDRESS", "table": "tb_vehicle_hk"},
					timeout: 180000,
					dataType: 'json',
					success: function (data) {
						_this.changzhanOptions = _.map(data, function(item) {
							return item.ADDRESS && {id: item.ADDRESS, label: item.ADDRESS};
						})
					},
					error: function () {
					}
				});
			}
		},
		components: {
			'tree-select': VueTreeselect.Treeselect
		}
	});
	var echart = null;
	$(function () {
		$('.scrollbar-macosx').scrollbar();
		$('#czlltj-stateTime').datetimepicker(datetimeDefaultOption);
		$('#czlltj-stateTime').val(new Date().Format('yyyy-MM-dd 00:00:00'));
		$('#czlltj-endTime').datetimepicker(datetimeDefaultOption);
		$('#czlltj-endTime').val(new Date().Format('yyyy-MM-dd 23:59:59'));

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
		var bljlFields = [
		                  	{name: 'id', title: '序号', width: 60, align: 'center'},
			    			{name: 'cjdd', title: '场站', width: 120, align: 'center'},
			    			{name: 'sjl', title: '数据量', width: 120, align: 'center'},
			    		];
		$('#czlltj-select').on('click', function () {
			findczlltj();
		});
//		function issh(str1,str2){
//			if(str1==''||str2==''){
//				return 0;
//			}else{				
//				if(str1.substring(5,7)==str2.substring(5,7)){
//					return 0;
//				}else{
//					
//					return 1;
//				}
//			}
//		}	
		function findczlltj(){
//			var str1=$('#czlltj-stateTime').val();
//  			var str2=$('#czlltj-endTime').val();
//  			if(issh(str1,str2)=='1'){
//  				layer.msg('不支持跨越查询',{icon:2});
//  				return false;
//  			}
			var all = 0;
			$('#czlltjTable').jsGrid({
				width: '100%',
				height: 'calc(100% - 250px)',
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

		}
		function re(filter, callback){
//			var check='';
//			if($("#check").is(':checked')){
//				check='1';
//			}
			var address = "";
			for (i = 0; i < vm.changzhan.length; i++) {
				address += "'" + vm.changzhan[i] + "',";
			}
			address = address.substr(0, (address.length - 1));
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            jqxhr=$.ajax({
            	type: "POST",
				url: "../../zpsj/findczlltj",
				data:{"stime":$("#czlltj-stateTime").val(),
					"etime":$("#czlltj-endTime").val(),
					"address":address,
					"check":$("#check").val(),
					"pageIndex":filter.pageIndex,
     				"pageSize":filter.pageSize
					},
				timeout : 180000,
				dataType: 'json',
            }).done(function(data) {
            		var jsycxData = [];
            		var c=[],d=[];
     				all = data[0].count;
         			if(data.length>0){
         				for(var i = 0; i< data[0].datas.length ;i++){
         					var rs={};
         					var vehicle=data[0].datas[i];
         					rs.id = startIndex+i+1;	
         					rs.cjdd =  data[0].datas[i].ADDRESS;
         					rs.sjl =  data[0].datas[i].SJL;
         					jsycxData.push(rs);
         					c.push(data[0].datas[i].ADDRESS);
         					d.push(data[0].datas[i].SJL);
         				}
         				drawEcharts(c,d);
         				return callback({
                            data: jsycxData,
                            itemsCount: all
                        });

         			}else{
        			}
            }).fail(function() {
            });
		}
		function drawEcharts(c,d){			
			echart = echarts.init(document.getElementById("czlltjEchart"));
			echart.setOption ({
					color: ['#1391fd', '#4575b4', '#74add1', '#d48265', '#6e7074', '#749f83', '#c4ccd3', '#fdae61', '#f46d43', '#d73027', '#a50026'],
				    title : {
				        text: '场站流量统计',
				        textStyle : {
							fontWeight : 'normal', // 标题颜色
							color : '#696969'
						},
				    },
				    grid: {
						top: '70px',
						left: '50px',
						right: '80px',
						bottom: '60px'
					},
				    tooltip : {
				        trigger: 'axis',
				        axisPointer: {
							type: 'shadow'
						}
				    },
				    legend: {
				        data:['数据量']  
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    xAxis : [
				        {
				        	axisTick: {
								alignWithLabel: true
							},
				            type : 'category',
				            name : '场站',
				            data : c
				        },
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            name : '数据量',
				        }
				    ],
				    series : [
				        {
				        	name: '数据量',
							type: 'bar',
							barWidth: '20%',
				            data:d
				        },
				    ]
				});
		}
		findczlltj();
		$('#czlltj-reset').on('click', function () {
			$('.panel-queryBar .form-control').val('');
			$('.panel-queryBar .select2').val([""]).trigger("change");
		});
		$('#czlltj-dc').on('click', function () {
//			var str1=$('#czlltj-stateTime').val();
//  			var str2=$('#czlltj-endTime').val();
//  			if(issh(str1,str2)=='1'){
//  				layer.msg('不支持跨越导出',{icon:2});
//  				return false;
//  			}
//			var check='';
//			if($("#check").is(':checked')){
//				check='1';
//			}
			var address = "";
			for (i = 0; i < vm.changzhan.length; i++) {
				address += "'" + vm.changzhan[i] + "',";
			}
			address = address.substr(0, (address.length - 1));
			layer.confirm('你确定要导出数据', {
				btn: ['确定', '取消'],
				offset: '100px'
			}, function (layerIndex) {
				window.open(basePath+"zpsj/findczlltjdc?stime="+$("#czlltj-stateTime").val()
						+"&etime="+$("#czlltj-endTime").val()
						+"&address="+address
						+"&check="+$("#check").val()
				);
				layer.close(layerIndex);
			}, function (layerIndex) {
				layer.close(layerIndex);
			});
		});
	})
	
})(Vue, _, jQuery);

