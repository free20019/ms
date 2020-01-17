var qyyysjfx=(function ($) {
	$(function () {
		$('#qyyysjfx-stateTime').datetimepicker(dateDefaultOption);
		$('#qyyysjfx-stateTime').val(new Date().Format('yyyy-MM-dd'));
		$('#qyyysjfx-endTime').datetimepicker(dateDefaultOption);
		$('#qyyysjfx-endTime').val(new Date().Format('yyyy-MM-dd'));
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
//				type: "POST",
//				url: "../../wsfw/xll",
//				data:{"field":"ZGS","table":"JJQ_TJ_201812_DAY"},
//				timeout : 180000,
//				dataType: 'json',
//				success:function(data){
//					for(var i=0; i<data.length;i++){
//						data[i].id=data[i].ZGS;
//						data[i].text=data[i].ZGS;
//					}
//					var qb={};
//					qb.id='null';
//					qb.text='全部';
//					data.unshift(qb);
//					$("#qyyysjfx-company").select2({ 
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
				$('#qyyysjfx-company').select2({
					data: data,
					allowClear: true
					});
			}
		});

		var bljlFields = [
		                  	{name: 'id', title: '序号', width: 120, align: 'center'},
						    {name: 'gs', title: '公司', width: 160,align:'center'},
						    {name: 'cls', title: '车辆数', width: 120,align:'center'},
						    {name: 'yys', title: '营运数', width: 120, align:'center'},
		          			{name: 'ccl', title: '出车率', width: 120, align:'center'},
		          			{name: 'yycs', title: '营运次数/(次)', width: 120, align:'center'},
		          			{name: 'yyje', title: '营运金额/(元)', width: 120, align:'center'},
		          			{name: 'zlc', title: '总里程/(公里)', width: 120, align:'center'},
		          			{name: 'zklc', title: '载客里程/(公里)', width: 120, align:'center'},
		          			{name: 'kslc', title: '空驶里程/(公里)', width: 120, align:'center'},
		          			{name: 'szl', title: '实载率', width: 120, align:'center'},
		          			{name: 'zksj', title: '载客时间/(分钟)', width: 150, align:'center'},
		          			{name: 'zkdhsj', title: '载客等候时间/(分钟)', width: 150, align:'center'}
			    		];
		$('#qyyysjfx-select').on('click', function () {
			findqyyysjfx();
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
		function findqyyysjfx(){
			var str1=$('#qyyysjfx-stateTime').val();
  			var str2=$('#qyyysjfx-endTime').val();
  			if(issh(str1,str2)=='1'){
  				layer.msg('不支持跨越查询',{icon:2});
  				return false;
  			}
			var all = 0;
			$('#qyyysjfxTable').jsGrid({
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

//			setTimeout("findqyyysjfx()",1000);
		}
		function re(filter, callback){
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            jqxhr=$.ajax({
            	type: "POST",
				url: "../../wsfw/findqyyysjfx",
				data:{"stime":$("#qyyysjfx-stateTime").val(),
					"etime":$("#qyyysjfx-endTime").val(),
					"company":$("#qyyysjfx-company").val(),
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
         					rs.gs =  data[0].datas[i].ZGS;
         					rs.cls =  data[0].datas[i].CLS;
         					rs.yys =  data[0].datas[i].YYS;
         					rs.ccl =  data[0].datas[i].CCL;
         					rs.yycs =  data[0].datas[i].YYCS;
         					rs.yyje = data[0].datas[i].YYJE;   
         					rs.zlc =  data[0].datas[i].ZLC;
         					rs.zklc =  data[0].datas[i].ZKLC;
         					rs.kslc = data[0].datas[i].KSLC;
         					rs.szl = data[0].datas[i].SZL;
         					rs.zksj = data[0].datas[i].ZKSJ;
         					rs.zkdhsj = data[0].datas[i].ZKDHSJ;
         					
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
		findqyyysjfx();
		$('#qyyysjfx-dc').on('click', function () {
			var str1=$('#qyyysjfx-stateTime').val();
  			var str2=$('#qyyysjfx-endTime').val();
  			if(issh(str1,str2)=='1'){
  				layer.msg('不支持跨越导出',{icon:2});
  				return false;
  			}
			layer.confirm('你确定要导出数据', {
				btn: ['确定', '取消'],
				offset: '100px'
			}, function (layerIndex) {
				window.open(basePath+"wsfw/findqyyysjfxdc?stime="+$("#qyyysjfx-stateTime").val()
						+"&etime="+$("#qyyysjfx-endTime").val()
						+"&company="+$("#qyyysjfx-company").val()
				);
				layer.close(layerIndex);
			}, function (layerIndex) {
				layer.close(layerIndex);
			});
		});
	})
	
})(jQuery)

