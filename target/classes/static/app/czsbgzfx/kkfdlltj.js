var kkfdlltj=(function (Vue, _, $) {
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
					data: {"field": "ADDRESS", "table": "tb_vehicle_hk2"},
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
		$('#kkfdlltj-stateTime').datetimepicker(dateDefaultOption);
		$('#kkfdlltj-stateTime').val(new Date().Format('yyyy-MM-dd'));

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
			url: "../../zpsj/xll",
			data:{"field":"ADDRESS","table":"tb_vehicle_hk2"},
			timeout : 180000,
			dataType: 'json',
			success:function(data){
				$("#kkfdlltj-address").empty();
				$("#kkfdlltj-address").append('<option value="" disabled selected>场站</option>');
				$("#kkfdlltj-address").append('<option value="null">全部</option>');
				for(var i=0; i<data.length;i++){
					if(null!=data[i].ADDRESS){
						$("#kkfdlltj-address").append('<option value="'+data[i].ADDRESS+'">'+data[i].ADDRESS+'</option>');						
					}
				}
			},
			error: function(){
			}         
		});
		
		var bljlFields = [
		                  	{name: 'id', title: '序号', width: 60, align: 'center'},
		                  	{name: 'cz', title: '场站', width: 100, align: 'center'},
			    			{name: 'c0', title: '00:00-02:00', width: 100, align: 'center'},
			    			{name: 'c1', title: '02:00-04:00', width: 100, align: 'center'},
			    			{name: 'c2', title: '04:00-06:00', width: 100, align: 'center'},
			    			{name: 'c3', title: '06:00-08:00', width: 100, align: 'center'},
			    			{name: 'c4', title: '08:00-10:00', width: 100, align: 'center'},
			    			{name: 'c5', title: '10:00-12:00', width: 100, align: 'center'},
			    			{name: 'c6', title: '12:00-14:00', width: 100, align: 'center'},
			    			{name: 'c7', title: '14:00-16:00', width: 100, align: 'center'},
			    			{name: 'c8', title: '16:00-18:00', width: 100, align: 'center'},
			    			{name: 'c9', title: '18:00-20:00', width: 100, align: 'center'},
			    			{name: 'c10', title: '20:00-22:00', width: 100, align: 'center'},
			    			{name: 'c11', title: '22:00-24:00', width: 100, align: 'center'},
			    		];
		$('#kkfdlltj-select').on('click', function () {
			findkkfdlltj();
		});
		function findkkfdlltj(){
//			if($('#kkfdlltj-address').val()==""||$('#kkfdlltj-address').val()=="null"||$('#kkfdlltj-address').val()==null){
//				layer.msg('请选择场站',{icon:1});
//				return false;
//			}
			var all = 0;
			$('#kkfdlltjTable').jsGrid({
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
				url: "../../zpsj/findkkfdlltj",
				data:{"time":$("#kkfdlltj-stateTime").val(),
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
         					var a=[],b={};
         					var rs={};
         					var vehicle=data[0].datas[i];
         					rs.id = startIndex+i+1;	
         					rs.cz =  data[0].datas[i].ADDRESS;
         					rs.c0 =  data[0].datas[i].c0;
         					rs.c1 =  data[0].datas[i].c1;
         					rs.c2 =  data[0].datas[i].c2;
         					rs.c3 =  data[0].datas[i].c3;
         					rs.c4 =  data[0].datas[i].c4;
         					rs.c5 =  data[0].datas[i].c5;
         					rs.c6 =  data[0].datas[i].c6;
         					rs.c7 =  data[0].datas[i].c7;
         					rs.c8 =  data[0].datas[i].c8;
         					rs.c9 =  data[0].datas[i].c9;
         					rs.c10 =  data[0].datas[i].c10;
         					rs.c11 =  data[0].datas[i].c11;
         					jsycxData.push(rs);
         					c.push(data[0].datas[i].ADDRESS);
         					a.push(data[0].datas[i].c0);
         					a.push(data[0].datas[i].c1);
         					a.push(data[0].datas[i].c2);
         					a.push(data[0].datas[i].c3);
         					a.push(data[0].datas[i].c4);
         					a.push(data[0].datas[i].c5);
         					a.push(data[0].datas[i].c6);
         					a.push(data[0].datas[i].c7);
         					a.push(data[0].datas[i].c8);
         					a.push(data[0].datas[i].c9);
         					a.push(data[0].datas[i].c10);
         					a.push(data[0].datas[i].c11);
         					b.name=data[0].datas[i].ADDRESS;
         					b.type='line';
         					b.data=a;
         					d.push(b);
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
			echart = echarts.init(document.getElementById("kkfdlltjEchart"));
			echart.clear();
			echart.setOption ({
					color: ['#1391fd', '#4575b4', '#74add1', '#d48265', '#6e7074', '#749f83', '#c4ccd3', '#fdae61', '#f46d43', '#d73027', '#a50026'],
				    title : {
				        text: '分段流量统计',
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
				        data:c				        
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
				            name : '时间',
				            data : ['0-2','2-4','4-6','6-8','8-10','10-12','12-14','14-16','16-18','18-20','20-22','22-24']
				        },
				    ],
				    yAxis : [
				        {
				            type : 'value',
				            name : '数据量',
				        }
				    ],
				});
			echart.setOption ({
				series : d
			});
		}
		findkkfdlltj();
		$('#kkfdlltj-reset').on('click', function () {
			$('.panel-queryBar .form-control').val('');
			$('.panel-queryBar .select2').val([""]).trigger("change");
		});
		$('#kkfdlltj-dc').on('click', function () {
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
				window.open(basePath+"zpsj/findkkfdlltjdc?time="+$("#kkfdlltj-stateTime").val()
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

