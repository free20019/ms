var sbyccx=(function ($) {
	var clfb_map;
	$(function () {
		var today = new Date();
		var oneday = 1000 * 60 * 60 * 2;
		$('#sbyc-stateTime').datetimepicker(dateDefaultOption);
		$('#sbyc-stateTime').val(new Date(today-oneday).Format('yyyy-MM-dd'));
		$('#sbyc-endTime').datetimepicker(dateDefaultOption);
		$('#sbyc-endTime').val(new Date(today).Format('yyyy-MM-dd'));
		$(document).ready(function() {
			  $(".select2").select2({  
				  	language: "zh-CN",  //设置 提示语言
			        tags:true,  
			        allowClear: true,
			        closeOnSelect: true,
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
				qb.id='0';
				qb.text='全部';
				data.unshift(qb);
				$('#sbyc-cphm').select2({
					data: data,
					language:'zh-CN',
                    minimumInputLength: 3,
					allowClear: true
					});
			}
		});
		
//		jqxhr=$.ajax({
//				type: "POST",
//				url: "../../ycyy/xll",
//				data:{"field":"VEHICLE_NO","table":"TB_ALARM_VEHICLE"},
//				timeout : 180000,
//				dataType: 'json',
//				success:function(data){
//					$("#sbyc-cphm").select2({ 
//							language: "zh-CN",  //设置 提示语言
//					        minimumInputLength: 3,
//					        tags:true,  
//					        allowClear: true,
//					        closeOnSelect: true,
//					        createTag:function (decorated, params) {  
//					            return null;  
//					        },  
//					    });
//					$("#sbyc-cphm").empty();
//					$("#sbyc-cphm").append('<option value="" selected></option>');
//					$("#sbyc-cphm").append('<option value="null">全部车辆</option>');
//					for(var i=0; i<data.length;i++){
//						$("#sbyc-cphm").append('<option value="'+data[i].VEHICLE_NO+'">'+data[i].VEHICLE_NO+'</option>');
//					}
//				},
//				error: function(){
//				}         
//			});
//		jqxhr=$.ajax({
//				type: "POST",
//				url: "../../ycyy/xll",
//				data:{"field":"ERROR_TYPE","table":"TB_ALARM_VEHICLE"},
//				timeout : 180000,
//				dataType: 'json',
//				success:function(data){
//					$("#sbyc-type").empty();
//					$("#sbyc-type").append('<option value="" disabled selected>类型</option>');
//					$("#sbyc-type").append('<option value="null">全部类型</option>');
//					for(var i=0; i<data.length;i++){
//						$("#sbyc-type").append('<option value="'+data[i].ERROR_TYPE+'">'+data[i].ERROR_TYPE+'</option>');
//					}
//				},
//				error: function(){
//				}         
//			});
//		jqxhr=$.ajax({
//			type: "POST",
//			url: "../../ycyy/xll",
//			data:{"field":"ERROR_ADDRESS","table":"TB_ALARM_VEHICLE"},
//			timeout : 180000,
//			dataType: 'json',
//			success:function(data){
//				$("#sbyc-address").empty();
//				$("#sbyc-address").append('<option value="" disabled selected>采集点</option>');
//				$("#sbyc-address").append('<option value="null">全部地点</option>');
//				for(var i=0; i<data.length;i++){
//					if(null!=data[i].ERROR_ADDRESS){
//						$("#sbyc-address").append('<option value="'+data[i].ERROR_ADDRESS+'">'+data[i].ERROR_ADDRESS+'</option>');						
//					}
//				}
//			},
//			error: function(){
//			}         
//		});
		
		var bljlFields = [
		                  	{name: 'id', title: '序号', width: 60, align: 'center'},
			    			{name: 'cphm', title: '车牌号', width: 120, align: 'center'},
			    			{name: 'yclx', title: '异常类型', width: 120, align: 'center'},
			    			{name: 'cjsj', title: '采集时间', width: 120, align: 'center'},
			    			{name: 'caozuo', title: '操作',
			    				itemTemplate: 
			    					function(value, item) {
				    					var style = {marginRight: '4px'};
				    					return [
											$('<a>').addClass('btn btn-primary btn-sm').text('详情').on('click', function () {
												var key = "pic_"+item.cphm+"_"+item.cjdd+"_"+item.cjsj.replace("-","").replace("-","").replace(" ","").replace(":","").replace(":","")+".jpg";
												document.getElementById('sbycbig').src = "../../common/pic?key="+key;
												imagebigscale("sbycbigImage", "sbycbig", "sbycimagetool", "sbycclose", "sbycleft-rote", "sbycright-rote", "sbycbigger", "sbycsmaller");
												document.getElementById('sbycbigImage').style.display = 'block';
												document.getElementById('sbycbigImage').style.top = '0px';
												document.getElementById('sbycbigImage').style.left = '30px';
											})  
		    					        ]		
			    				}, width: 40, align: 'center'}
			    		];
		$('#sbyc-select').on('click', function () {
			findsbyc();		
		});
		function findsbyc(){
			var all = 0;
			$('#sbycTable').jsGrid({
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

//			setTimeout("findsbyc()",1000);
		}
		function re(filter, callback){
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            jqxhr=$.ajax({
            	type: "POST",
				url: "../../ycyy/findsbyc",
				data:{"stime":$("#sbyc-stateTime").val(),
					"etime":$("#sbyc-endTime").val(),
					"vehicle":$("#sbyc-cphm").val(),
					"type":$("#sbyc-type").val(),
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
         					rs.yclx =  data[0].datas[i].GZ;
         					rs.cjsj = (data[0].datas[i].DB_TIME==null?"":(new Date(data[0].datas[i].DB_TIME)).Format("yyyy-MM-dd"));
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
		findsbyc();
		$('#sbyc-reset').on('click', function () {
			$('.panel-queryBar .form-control').val('');
			$('.panel-queryBar .select2').val([""]).trigger("change");
		});
		$('#sbyc-dc').on('click', function () {
			layer.confirm('你确定要导出数据', {
				btn: ['确定', '取消'],
				offset: '100px'
			}, function (layerIndex) {
				window.open(basePath+"ycyy/findsbycdc?stime="+$("#sbyc-stateTime").val()
						+"&etime="+$("#sbyc-endTime").val()
						+"&vehicle="+$("#sbyc-cphm").val()
						+"&type="+$("#sbyc-type").val()
				);
				layer.close(layerIndex);
			}, function (layerIndex) {
				layer.close(layerIndex);
			});
		});
	})
	
})(jQuery)

