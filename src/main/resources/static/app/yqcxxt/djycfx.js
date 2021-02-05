var djycfxcx=(function ($) {
	$(function () {
		var today = new Date();
		var oneday = 1000 * 60 * 60 * 24;
		$('#djycfx-stateTime').datetimepicker(datetimeDefaultOption);
		$('#djycfx-endTime').datetimepicker(datetimeDefaultOption);
		$('#djycfx-stateTime').val(new Date(today-oneday).Format('yyyy-MM-dd 00:00:00'));
		$('#djycfx-endTime').val(new Date(today-oneday).Format('yyyy-MM-dd 23:59:59'));
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
		// jqxhr=$.ajax({
		// 		type: "POST",
		// 		url: "../../ycyy/xll",
		// 		data:{"field":"VEHICLE_NO","table":"tb_taxi_gzfx_yq"},
		// 		timeout : 180000,
		// 		dataType: 'json',
		// 		success:function(data){
		// 			$("#djycfx-cphm").select2({
		// 				language: "zh-CN",  //设置 提示语言
		// 		        minimumInputLength: 3,
		// 		        tags:true,
		// 		        allowClear: true,
		// 		        closeOnSelect: true,
		// 		        createTag:function (decorated, params) {
		// 		            return null;
		// 		        },
		// 		    });
		// 			$("#djycfx-cphm").empty();
		// 			$("#djycfx-cphm").append('<option value=""></option>');
		// 			$("#djycfx-cphm").append('<option value="null">全部车牌</option>');
		// 			for(var i=0; i<data.length;i++){
		// 				$("#djycfx-cphm").append('<option value="'+data[i].VEHICLE_NO+'">'+data[i].VEHICLE_NO+'</option>');
		// 			}
		// 		},
		// 		error: function(){
		// 		}
		// 	});

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
				$('#djycfx-cphm').select2({
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
				$('#djycfx-gs').select2({
					data: data,
					allowClear: true
				});
			}
		});

		var dd = [
			{id: 'null', text: '全部'},
			{id: 'JJQ_NDJ', text: '有营运无登记'},
			{id: 'GPS_NYY_NDJ', text: '有定位无登记'},
			{id: 'NGPS_NJJQ_NDJ_ZP', text: '有卡口记录无登记'},
		];

		$('#djycfx-value').select2({
			language: 'zh-CN',
			width: '150',
			minimumResultsForSearch: -1,
			allowClear: true,
			data: dd
		});
		
		var bljlFields = [
		                  	{name: 'id', title: '序号', width: 60, align: 'center'},
			    			{name: 'cphm', title: '车牌号', width: 120, align: 'center'},
			    			{name: 'ssgs', title: '所属公司', width: 120, align: 'center'},
							{name: 'jjq_ndj', title: '有营运无登记', width: 120, align: 'center'},
							{name: 'gps_nyy_ndj', title: '有定位无登记', width: 120, align: 'center'},
			    			{name: 'ngps_njjq_ndj_zp', title: '有卡口记录无登记', width: 120, align: 'center'}
			    		];
		$('#djycfx-select').on('click', function () {
			finddjycfx();
		});


		function finddjycfx(){
			var all = 0;
			$('#djycfxTable').jsGrid({
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

		}
		function re(filter, callback){
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            jqxhr=jqxhr=$.ajax({
            	type: "POST",
				url: "../../yqcx/finddjycfx",
				data:{"stime":$("#djycfx-stateTime").val(),
					"etime":$("#djycfx-endTime").val(),
					"vehicle":$("#djycfx-cphm").val(),
					"company":$("#djycfx-gs").val(),
					"value": $("#djycfx-value").val(),
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
         					rs.jjq_ndj =  data[0].datas[i].JJQ_NDJ;
         					rs.gps_nyy_ndj =  data[0].datas[i].GPS_NYY_NDJ;
         					rs.ngps_njjq_ndj_zp =  data[0].datas[i].NGPS_NJJQ_NDJ_ZP;
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
		finddjycfx();
		$('#djycfx-reset').on('click', function () {
			$('.panel-queryBar .form-control').val('');
			$('.panel-queryBar .select2').val([""]).trigger("change");
		});
		$('#djycfx-dc').on('click', function () {
			layer.confirm('你确定要导出数据', {
				btn: ['确定', '取消'],
				offset: '100px'
			}, function (layerIndex) {
				window.open(basePath+"yqcx/finddjycfxdc?stime="+$("#djycfx-stateTime").val()
						+"&etime="+$("#djycfx-endTime").val()
						+"&vehicle="+$("#djycfx-cphm").val()
						+"&company="+$("#djycfx-gs").val()
						+"&value="+$("#djycfx-value").val()
				);
				layer.close(layerIndex);
			}, function (layerIndex) {
				layer.close(layerIndex);
			});
		});
	})
	
})(jQuery)

