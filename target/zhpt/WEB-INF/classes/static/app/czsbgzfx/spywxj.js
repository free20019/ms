var spywxj=(function ($) {
	var clfb_map;
	$(function () {
		$('#spywxj-stateTime').datetimepicker(dateDefaultOption);
//		$('#spywxj-stateTime').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));
		$('#spywxj-endTime').datetimepicker(dateDefaultOption);
//		$('#spywxj-endTime').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));

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
				$('#spywxj-cphm').select2({
					data: data,
					language:'zh-CN',
					minimumInputLength: 3,
					allowClear: true
				});
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
				$('#spywxj-company').select2({
					data: data,
					allowClear: true
				});
			}
		});

		var bljlFields = [
		              		{name: 'id', title: '序号', width: 60, align: 'center'},
		              		{name: 'cphm', title: '车牌号', width: 80, align: 'center'},
			    			{name: 'gs', title: '业户', width: 120, align: 'center'},
			    			{name: 'one1', title: '1路（第一次巡检）', width: 120, align: 'center'},
			    			{name: 'one2', title: '2路（第一次巡检）', width: 120, align: 'center'},
			    			{name: 'one3', title: '3路（第一次巡检）', width: 120, align: 'center'},
			    			{name: 'one4', title: '4路（第一次巡检）', width: 120, align: 'center'},		
				  		    {name: 'two1', title: '1路（第二次巡检）', width: 120, align: 'center'},
				  		    {name: 'two2', title: '2路（第二次巡检）', width: 120, align: 'center'},
			    			{name: 'two3', title: '3路（第二次巡检）', width: 120, align: 'center'},
			    			{name: 'two4', title: '4路（第二次巡检）', width: 120, align: 'center'},
			    			{name: 'time', title: '巡查时间', width: 120, align: 'center'},
			    			
			    		];
		$('#spywxj-select').on('click', function () {
			findspywxj();
		});
		function findspywxj(){
			var all = 0;
			$('#spywxjTable').jsGrid({
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
				url: "../../sbwx/findspywxj",
				data:{"stime":$("#spywxj-stateTime").val(),
					"etime":$("#spywxj-endTime").val(),
					"vehicle":$("#spywxj-cphm").val(),
					"company":$("#spywxj-company").val(),
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
         					rs.gs =  vehicle.COMP_NAME;
         					rs.cphm =  vehicle.VEHI_NO;
         					rs.one1 =  vehicle.ONE_ROAD_ONE;
         					rs.one2 =  vehicle.ONE_ROAD_TWO;
         					rs.one3 =  vehicle.ONE_ROAD_THREE;
         					rs.one4 =  vehicle.ONE_ROAD_FOUR;
         					rs.two1 =  vehicle.TWO_ROAD_ONE;
         					rs.two2 =  vehicle.TWO_ROAD_TWO;
         					rs.two3 =  vehicle.TWO_ROAD_THREE;
         					rs.two4 =  vehicle.TWO_ROAD_FOUR;
     						rs.time = (vehicle.TIME==null?"":(new Date(vehicle.TIME)).Format("yyyy-MM-dd"));
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
		findspywxj();
		$('#spywxj-dc').on('click', function () {
			layer.confirm('你确定要导出数据', {
				btn: ['确定', '取消'],
				offset: '100px'
			}, function (layerIndex) {
					window.open(basePath+"sbwx/findspywxjdc?stime="+$("#spywxj-stateTime").val()
					+"&etime="+$("#spywxj-endTime").val()
					+"&vehicle="+$("#spywxj-cphm").val()
					+"&company="+$("#spywxj-company").val()
				);
				layer.close(layerIndex);
		}, function (layerIndex) {
			layer.close(layerIndex);
		});
		});
	})
})(jQuery)
