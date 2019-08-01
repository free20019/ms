	var yydcycfx = new Vue({
		el: '#yydcycfxPanel',
		data: function () {
			return {
				query: {
					startTime: formatYYYYMMDD(new Date()),
					minMileage: '10',
					maxMileage: '20',
					a:0,
					b:0
				},
				lineChart: null,
				oneTable: {
					fields: [
						{name: 'ti', title: '区间', width: 120, align: 'center'},
						{name: 'count', title: '数量', width: 140,align: 'center'},
						{ name: "operate",title: '操作', type: "number", width: 50,align: 'center',
							itemTemplate: this.initTableBtnHtml
						}
					],
					data: []
				},
				twoTable: {
					fields: [
					    {name: 'ID', title: '序号', width: 60, align: 'center'},
						{name: 'CPHM', title: '车牌', width: 120, align: 'center'},
						{name: 'C', title: '单次', width: 120, align: 'center'},
						{name: 'DAY', title: '日期', width: 140, align: 'center'},
						{name: 'ZGS', title: '总公司', width: 140, align: 'center'},
						{name: 'FGS', title: '分公司', width: 140, align: 'center'},
					],
					data: []
				}
			}
		},
		created: function () {
			var _this = this;
			this.$nextTick(function () {
				_this.resizePanel();
				$('.scrollbar-macosx').scrollbar();
				$('#yydcycfx-datetime').datetimepicker(dateDefaultOption).on('change', function () {
					_this.query.startTime = $(this).val()
				});
				_this.initB();
			});
		},
		methods: {
			handleExcleClick: function () {
				if(this.query.a == 0 && this.query.b == 0){
					layer.msg("请选择区间详情");
					return;
				}
				var data = {
						"maxMileage" : this.query.b,
	     				"minMileage" : this.query.a,
	     				"startTime" : this.query.startTime,
					};
					url = "../../tjfx/yydcycfxTwoexcle?data=" + JSON.stringify(data) , window.open(url)
			},
			handleQueryClick: function () {
				var _this = this;
				$('#yydcycfx-tabel-two').html('');
				_this.initB();
			},
			initB:function(){
				var _this = this;
				_this.init(function(d){
					console.log(d)
					var data = [],count=[];
					for(var i=0;i<d.length;i++){
						data.push(d[i].ti);
						count.push(d[i].count)
					}
					_this.lineChart = echarts.init(document.getElementById('yydcycfx-echart'));
					_this.lineChart.setOption({
						xAxis: {
							type: 'category',
							data: data
						},
						yAxis: {
							type: 'value'
						},
						series: [{
							data:  count,
							type: 'line'
						}]
					});
					$('#yydcycfx-tabel').jsGrid({
						width: 'calc(100% - 2px)',
						height: 'calc(100% - 2px)',
						editing: true,
						sorting: true,
						paging: false,
						autoload: true,
						data: d,
						fields: _this.oneTable.fields
					});
					$(window).on('resize', function () {
						_this.lineChart.resize();
						_this.resizePanel();
					})
				});
			},
			initTableBtnHtml: function(val, item) {
				console.info('initTable:', item)
				var _this = this;
				return $('<a class="btn btn-primary btn-xs">').text('详情').on('click', function() {
					_this.showTwoTableClick(item);
				})
			},
			showTwoTableClick: function (item) {
				var _this = this;
				$('#yydcycfx-tabel-two').jsGrid({
					width: 'calc(100% - 2px)',
					height: 'calc(100% - 2px)',
					autoload: true,
//					paging: true,
					pageLoading: true,
//					pageSize: 15,
//					pageIndex: 1,
					controller: {
	                    loadData: function(filter) {
	                    	var d = $.Deferred();
	                    	var a = _this.init2(item, filter,function(item){
	                    		d.resolve(item);
	                    	})
	                    	return d.promise();
	                    }
	                },
					fields: this.twoTable.fields,
//					pagerContainer: null,
//				    pageButtonCount: 5,
//				    pagerFormat: "{first} {prev} {pages} {next} {last} {pageIndex} of {pageCount}",
//				    pagePrevText: "上一页",
//				    pageNextText: "下一页",
//				    pageFirstText: "首页",
//				    pageLastText: "末页",
//				    pageNavigatorNextText: ">",
//				    pageNavigatorPrevText: "<"
				});
				
			},
			resizePanel: function () {
				var panelBodyHeight = $('.form-inline + .panel-body').height();
				if ((panelBodyHeight - 300) > 300) $('.panel-table.col100P').removeAttr('style');
				else $('.panel-table.col100P').css({height: '300px'});
			},
			init:function(callback){
				layer.load(2);
			    jqxhr=$.ajax({
	     	        url:"../../tjfx/yydcycfx",
	     	        data:{
	     				"maxMileage" : this.query.maxMileage,
	     				"minMileage" : this.query.minMileage,
	     				"startTime" : this.query.startTime,
	     	        },
	     	        dataType: 'json'
	            }).done(function(json) {
	            	console.log(json)
	            	layer.closeAll('loading');
     				re = json.data[0].datas;
	            	if(json.code == 0){
         				return callback(re);
         			}else{
         			}
	            });
			},
			init2:function(item,filter,callback){
				layer.load(2);
				var _this = this;
				var startIndex = (filter.pageIndex - 1) * filter.pageSize;
				var a = 0,b = 0;
				if(item.ti.split("-").length > 1){
					a = parseInt(item.ti.split("-")[0]);
					b = parseInt(item.ti.split("-")[1]);
				}else{
					a = parseInt(item.ti.split("以上")[0]);
				}
				this.query.a=a;
				this.query.b=b;
				jqxhr=$.ajax({
	     	        url:"../../tjfx/yydcycfxTwo",
	     	        data:{
	     	        	"minMileage" : a,
	     				"maxMileage" : b,
	     				"startTime" : this.query.startTime,
	     	        },
	     	        dataType: 'json'
	            }).done(function(json) {
	            	console.log(json)
	            	layer.closeAll('loading');
	            	var yydcycfxdata = [];
     				re = json.data[0].datas;
	            	all = json.data[0].datas.length;
	            	if(json.code == 0){
	            		for(var i = 0; i< re.length ;i++){
		            		var rs={};
		            		rs.ID = startIndex+i+1;
	     					rs.CPHM =  "浙"+re[i].CPHM;
	       					rs.C =  re[i].C;
	     					rs.DAY =  re[i].DAY.substring(0,4)+"-"+re[i].DAY.substring(4,6)+"-"+re[i].DAY.substring(6,8);
	     					rs.ZGS =  re[i].ZGS;
	     					rs.FGS =  re[i].FGS;
	     					yydcycfxdata.push(rs);
	            		}
	            		return callback({
                            data: yydcycfxdata,
                            itemsCount: all
                        });
         			}else{
         			}
	            });
			}
		}
	});