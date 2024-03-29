var fgslctj=(function ($) {
	$(function () {
		$('#fgslctj-stateTime').datetimepicker(dateDefaultOption);
		$('#fgslctj-stateTime').val(new Date().Format('yyyy-MM-dd'));
		$('#fgslctj-endTime').datetimepicker(dateDefaultOption);
		$('#fgslctj-endTime').val(new Date().Format('yyyy-MM-dd'));
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
//		jqxhr=$.ajax({
//			type: "POST",
//			url: "../../wsfw/xll",
//			data:{"field":"CPHM","table":"JJQ_COMPANY"},
//			timeout : 180000,
//			dataType: 'json',
//			success:function(data){
//				for(var i=0; i<data.length;i++){
//					data[i].id=data[i].CPHM;
//					data[i].text=data[i].CPHM;
//				}
//				var qb={};
//				qb.id='null';
//				qb.text='全部';
//				data.unshift(qb);
//				$("#fgslctj-cphm").select2({ 
//						language: "zh-CN",  //设置 提示语言
//				        minimumInputLength: 3,
//				        allowClear: true,
//				        data:data
//				    });
//			},
//			error: function(){
//			}         
//		});
//		jqxhr=$.ajax({
//				type: "POST",
//				url: "../../wsfw/xll",
//				data:{"field":"FGS","table":"JJQ_COMPANY"},
//				timeout : 180000,
//				dataType: 'json',
//				success:function(data){
//					for(var i=0; i<data.length;i++){
//						data[i].id=data[i].FGS;
//						data[i].text=data[i].FGS;
//					}
//					var qb={};
//					qb.id='null';
//					qb.text='全部';
//					data.unshift(qb);
//					$("#fgslctj-company").select2({ 
//							language: "zh-CN",  //设置 提示语言
////					        minimumInputLength: 3,
//					        allowClear: true,
//					        data:data
//					    });
//				},
//				error: function(){
//				}         
//			});
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
					$('#fgslctj-cphm').select2({
						data: data,
						language:'zh-CN',
						minimumInputLength: 3,
						allowClear: true
					});
				}
			});
            jqxhr=$.ajax({
				type: "POST",
				url:"../../wsfw/qycomp",
				data:{},
				dataType: 'json',
				timeout : 3600000,
				success:function(json){
					var data= json.datacomp;
					for (var i = 0; i < data.length; i++) {
						data[i].id=data[i].FGS;
						data[i].text=data[i].FGS;
					}
					var qb={};
					qb.id='null';
					qb.text='全部';
					data.unshift(qb);
					$('#fgslctj-company').select2({
						data: data,
						allowClear: true
						});
				}
			});
		var bljlFields = [
		                  	{name: 'id', title: '序号', width: 120, align: 'center'},
						    {name: 'gs', title: '公司', width: 160,align:'center'},
						    {name: 'cph', title: '车牌号', width: 120,align:'center'},
						    {name: 'zlc', title: '总里程(公里)', width: 140,align:'center'}
			    		];
		$('#fgslctj-select').on('click', function () {
			findfgslctj();
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
		function findfgslctj(){
			var str1=$('#fgslctj-stateTime').val();
  			var str2=$('#fgslctj-endTime').val();
  			if(issh(str1,str2)=='1'){
  				layer.msg('不支持跨越查询',{icon:2});
  				return false;
  			}
			var all = 0;
			$('#fgslctjTable').jsGrid({
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

//			setTimeout("findfgslctj()",1000);
		}
		function re(filter, callback){
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            jqxhr=$.ajax({
            	type: "POST",
				url: "../../wsfw/findfgslctj",
				data:{"stime":$("#fgslctj-stateTime").val(),
					"etime":$("#fgslctj-endTime").val(),
					"company":$("#fgslctj-company").val(),
					"vehicle":$("#fgslctj-cphm").val(),
					"pageIndex":filter.pageIndex,
     				"pageSize":filter.pageSize
					},
				timeout : 180000,
				dataType: 'json',
            }).done(function(data) {
            	if(data.code==500100){
            		layer.msg('数据不存在',{icon:2});
            		return callback();
            	}
            		var jsycxData = [];
     				all = data[0].count;
         			if(data.length>0){
         				for(var i = 0; i< data[0].datas.length ;i++){
         					var rs={};
         					var vehicle=data[0].datas[i];
         					rs.id =  startIndex+i+1;	
         					rs.gs =  data[0].datas[i].FGS;
         					rs.cph =  data[0].datas[i].CPHM;
         					rs.zlc =  data[0].datas[i].ZLC;
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
		findfgslctj();
		$('#fgslctj-dc').on('click', function () {
			var str1=$('#fgslctj-stateTime').val();
  			var str2=$('#fgslctj-endTime').val();
  			if(issh(str1,str2)=='1'){
  				layer.msg('不支持跨越导出',{icon:2});
  				return false;
  			}
			layer.confirm('你确定要导出数据', {
				btn: ['确定', '取消'],
				offset: '100px'
			}, function (layerIndex) {
				window.open(basePath+"wsfw/findfgslctjdc?stime="+$("#fgslctj-stateTime").val()
						+"&etime="+$("#fgslctj-endTime").val()
						+"&vehicle="+$("#fgslctj-cphm").val()
						+"&company="+$("#fgslctj-company").val()
				);
				layer.close(layerIndex);
			}, function (layerIndex) {
				layer.close(layerIndex);
			});
		});
	})
	
})(jQuery)

