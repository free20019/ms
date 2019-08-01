var wxgd=(function ($) {
	$(function () {
		$('#wxgd-stateTime').datetimepicker(datetimeDefaultOption);
		$('#wxgd-stateTime').val(new Date().Format('yyyy-MM-dd 00:00:00'));
		$('#wxgd-endTime').datetimepicker(datetimeDefaultOption);
		$('#wxgd-endTime').val(new Date().Format('yyyy-MM-dd 23:59:59'));

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
			url:"../../claq/qyveh",
			data:{},
			dataType: 'json',
			timeout : 3600000,
			success:function(json){
				console.log(json);
				var data= json.dataveh;
				for (var i = 0; i < data.length; i++) {
					data[i].id=data[i].PLATE_NUMBER;
					data[i].text=data[i].PLATE_NUMBER;
				}
				var qb={};
				qb.id='null';
				qb.text='全部';
				data.unshift(qb);
				$('#wxgd-cphm').select2({
					data: data,
					language:'zh-CN',
					minimumInputLength: 3,
					allowClear: true
				});
			}
		});
		var bljlFields = [
		              		{name: 'id', title: '序号', width: 60, align: 'center'},
		              		{name: 'cphm', title: '车牌号', width: 80, align: 'center'},
			    			{name: 'gs', title: '业户', width: 120, align: 'center'},
			    			{name: 'area', title: '区域', width: 120, align: 'center'},
			    			{name: 'gzlx', title: '故障类型', width: 80, align: 'center'},
			    			{name: 'gzsj', title: '故障时间', width: 120, align: 'center'},
			    			
			    			{name: 'once', title: '第一次催告时间', width: 240, align: 'center'},		
				  		    {name: 'twice', title: '第二次催告时间', width: 120, align: 'center'},
				  		    {name: 'third', title: '第三次催告时间', width: 120, align: 'center'},
			    			{name: 'fgsj', title: '反馈时间', width: 120, align: 'center'},
			    			{name: 'sfwx', title: '是否维修', width: 120, align: 'center'},
			    			
			    			{name: 'wxsj', title: '维修时间', width: 120, align: 'center'},
				    		{name: 'wxjg', title: '维修结果', width: 120, align: 'center'},
				    		{name: 'beiz', title: '备注', width: 120, align: 'center'},
			    			
			    		];
		$('#wxgd-select').on('click', function () {
			findwxgd();
		});
		function findwxgd(){
			var all = 0;
			$('#wxgdTable').jsGrid({
				width: '100%',
				height: 'calc(100% - 55px)',
				autoload: true,
				paging: true,
				pageLoading: true,
				editing: true,

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
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            jqxhr=$.ajax({
            	type: "POST",
				url: "../../sbwx/findwxgd",
				data:{"stime":$("#wxgd-stateTime").val(),
					"etime":$("#wxgd-endTime").val(),
					"vehicle":$("#wxgd-cphm").val(),
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
         					var item=data[0].datas[i];
         					
         					rs.id =  startIndex+i+1;
         					rs.cphm =  item.VEHICLE_NO;
         					rs.area =  item.AREA_NAME;
         					rs.gs =  item.COMP_NAME;
         					rs.gzlx =  item.FAULT_TYPE;
         					rs.gzsj =  item.TIME;
         					rs.once =  item.ONCE;
         					rs.twice =  item.TWICE;
         					rs.third =  item.THIRD;
         					rs.fgsj =  item.FGSJ;
         					rs.sfwx =  item.IS_REPAIR;
         					rs.wxsj =  item.WXSJ;
         					rs.wxjg =   item.REPAIR_RESULT;
         					rs.beiz =   item.DESCRIPTION;
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
		findwxgd();
		$('#wxgd-dc').on('click', function () {
			layer.confirm('你确定要导出数据', {
				btn: ['确定', '取消'],
				offset: '100px'
			}, function (layerIndex) {
					window.open(basePath+"sbwx/findwxgddc?stime="+$("#wxgd-stateTime").val()
					+"&etime="+$("#wxgd-endTime").val()
					+"&vehicle="+$("#wxgd-cphm").val()
				);
				layer.close(layerIndex);
		}, function (layerIndex) {
			layer.close(layerIndex);
		});
		});
	})
})(jQuery)