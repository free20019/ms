var djsjfxcx=(function ($) {
	$(function () {
		var today = new Date();
		var oneday = 1000 * 60 * 60 * 2;
		$('#djsjfx-stateTime').datetimepicker(datetimeDefaultOption);
		$('#djsjfx-endTime').datetimepicker(datetimeDefaultOption);
		// $('#djsjfx-stateTime').val(new Date(today-oneday).Format('yyyy-MM-dd hh:mm:ss'));
		$('#djsjfx-stateTime').val(new Date().Format('yyyy-MM-dd 00:00:00'));
		$('#djsjfx-endTime').val(new Date(today).Format('yyyy-MM-dd hh:mm:ss'));
		// $('#djsjfx-endTime').val(new Date().Format('yyyy-MM-dd 23:59:59'));
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
		// 		data:{"field":"CPHM","table":"TB_TAXI_SMDJ"},
		// 		timeout : 180000,
		// 		dataType: 'json',
		// 		success:function(data){
		// 			$("#djsjfx-cphm").select2({
		// 				language: "zh-CN",  //设置 提示语言
		// 		        minimumInputLength: 3,
		// 		        tags:true,
		// 		        allowClear: true,
		// 		        closeOnSelect: true,
		// 		        createTag:function (decorated, params) {
		// 		            return null;
		// 		        },
		// 		    });
		// 			$("#djsjfx-cphm").empty();
		// 			$("#djsjfx-cphm").append('<option value=""></option>');
		// 			$("#djsjfx-cphm").append('<option value="null">全部车牌</option>');
		// 			for(var i=0; i<data.length;i++){
		// 				$("#djsjfx-cphm").append('<option value="'+data[i].CPHM+'">'+data[i].CPHM+'</option>');
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
					$('#djsjfx-cphm').select2({
						data: data,
						language:'zh-CN',
						minimumInputLength: 3,
						allowClear: true
					});
				}
			});
// 		jqxhr=$.ajax({
// 				type: "POST",
// 				url: "../../ycyy/xll",
// 				data:{"field":"HK_ADDRESS","table":"TB_TAXI_TPC"},
// 				timeout : 180000,
// 				dataType: 'json',
// 				success:function(data){
// 					$("#djsjfx-type").empty();
// 					$("#djsjfx-type").append('<option value="" disabled selected>类型</option>');
// 					$("#djsjfx-type").append('<option value="null">全部类型</option>');
// 					// for(var i=0; i<data.length;i++){
// 						$("#djsjfx-type").append('<option value="套牌">套牌</option>');
// 					// }
// 				},
// 				error: function(){
// 				}
// 			});
//
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
				$('#djsjfx-gs').select2({
					data: data,
					allowClear: true
				});
			}
		});
		
		var bljlFields = [
		                  	{name: 'id', title: '序号', width: 60, align: 'center'},
			    			{name: 'cphm', title: '车牌号', width: 120, align: 'center'},
			    			{name: 'ssgs', title: '所属公司', width: 120, align: 'center'},
							{name: 'djcs', title: '登记次数', width: 120, align: 'center'},
							{name: 'yyjls', title: '营运记录数', width: 120, align: 'center'},
			    			{name: 'cz', title: '差值', width: 120, align: 'center'}
			    		];
		$('#djsjfx-select').on('click', function () {
			finddjsjfx();
		});


		function issh(str1,str2){
			if(str1==''||str2==''){
				return 0;
			}else{
				if(str1.substring(5,7)==str2.substring(5,7)){
					return 0;
				}else{

					return 1;
				}
			}
		}

		function finddjsjfx(){
			var str1=$('#djsjfx-stateTime').val();
			var str2=$('#djsjfx-endTime').val();
			if(str1==''||str2==''){
				layer.msg('请输入时间',{icon:2});
				return false;
			}
			if(issh(str1,str2)=='1'){
				layer.msg('不支持跨越查询',{icon:2});
				return false;
			}

			var all = 0;
			$('#djsjfxTable').jsGrid({
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

//			setTimeout("finddjsjfx()",1000);
		}
		function re(filter, callback){
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            jqxhr=jqxhr=$.ajax({
            	type: "POST",
				url: "../../yqcx/finddjsjfx",
				data:{"stime":$("#djsjfx-stateTime").val(),
					"etime":$("#djsjfx-endTime").val(),
					"vehicle":$("#djsjfx-cphm").val(),
					"company":$("#djsjfx-gs").val(),
					"value": $("#djsjfx-value").val(),
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
         					rs.cphm =  data[0].datas[i].CPHM;
         					rs.ssgs =  data[0].datas[i].COMPANY_NAME;
         					rs.djcs =  data[0].datas[i].DJCS;
         					rs.yyjls =  data[0].datas[i].YYJLS;
         					rs.cz =  data[0].datas[i].CZ;
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
		finddjsjfx();
		$('#djsjfx-reset').on('click', function () {
			$('.panel-queryBar .form-control').val('');
			$('.panel-queryBar .select2').val([""]).trigger("change");
		});
		$('#djsjfx-dc').on('click', function () {
			var str1=$('#djsjfx-stateTime').val();
			var str2=$('#djsjfx-endTime').val();
			if(str1==''||str2==''){
				layer.msg('请输入时间',{icon:2});
				return false;
			}
			if(issh(str1,str2)=='1'){
				layer.msg('不支持跨越查询',{icon:2});
				return false;
			}
			layer.confirm('你确定要导出数据', {
				btn: ['确定', '取消'],
				offset: '100px'
			}, function (layerIndex) {
				window.open(basePath+"yqcx/finddjsjfxdc?stime="+$("#djsjfx-stateTime").val()
						+"&etime="+$("#djsjfx-endTime").val()
						+"&vehicle="+$("#djsjfx-cphm").val()
						+"&company="+$("#djsjfx-gs").val()
						+"&value="+$("#djsjfx-value").val()
				);
				layer.close(layerIndex);
			}, function (layerIndex) {
				layer.close(layerIndex);
			});
		});
	})
	
})(jQuery)

