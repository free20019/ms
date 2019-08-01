var clzxcx=(function ($) {
	$(function () {
		$('#clzxcx-stateTime').datetimepicker({
        	language:  'zh-CN',
        	format: 'yyyy-mm-dd',
        	autoclose: 1,
            startView: 2,
        	minView: 2,
            endDate:new Date(new Date()-24*60*60*1000),
        });
		$('#clzxcx-stateTime').val(new Date(new Date()-24*60*60*1000).Format('yyyy-MM-dd'));
		$('#clzxcx-endTime').datetimepicker({
        	language:  'zh-CN',
        	format: 'yyyy-mm-dd',
        	autoclose: 1,
            startView: 2,
        	minView: 2,
            endDate:new Date(new Date()-24*60*60*1000),
        });
		$('#clzxcx-endTime').val(new Date(new Date()-24*60*60*1000).Format('yyyy-MM-dd'));
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
//				data:{"field":"VEHI_NO","table":"TB_TAXI_NOT_ONLINE"},
//				timeout : 180000,
//				dataType: 'json',
//				success:function(data){
//					for(var i=0; i<data.length;i++){
//						data[i].id=data[i].VEHI_NO;
//						data[i].text=data[i].VEHI_NO;
//					}
//					var qb={};
//					qb.id='null';
//					qb.text='全部';
//					data.unshift(qb);
//					$("#clzxcx-cphm").select2({ 
//							language: "zh-CN",  //设置 提示语言
//					        minimumInputLength: 3,
//					        allowClear: true,
//					        data:data
//					    });
//				},
//				error: function(){
//				}         
//			});
//		jqxhr=$.ajax({
//				type: "POST",
//				url: "../../wsfw/xll",
//				data:{"field":"COMP_NAME","table":"VW_VEHICLE"},
//				timeout : 180000,
//				dataType: 'json',
//				success:function(data){
//					for(var i=0; i<data.length;i++){
//						data[i].id=data[i].COMP_NAME;
//						data[i].text=data[i].COMP_NAME;
//					}
//					var qb={};
//					qb.id='null';
//					qb.text='全部';
//					data.unshift(qb);
//					$("#clzxcx-company").select2({ 
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
				$('#clzxcx-cphm').select2({
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
				$('#clzxcx-company').select2({
					data: data,
					allowClear: true
					});
			}
		});
		var bljlFields = [
		                  	{name: 'id', title: '序号', width: 120, align: 'center'},
						    {name: 'gs', title: '公司', width: 160,align:'center'},
						    {name: 'cph', title: '车牌号', width: 120,align:'center'},
						    {name: 'sim', title: 'SIM卡号', width: 140,align:'center'},
						    {name: 'lxr', title: '联系人', width: 120,align:'center'},
						    {name: 'lxdd', title: '联系电话', width: 140,align:'center'},
						    {name: 'time', title: '最后汇报时间', width: 150,align:'center'},
						    {name: 'zt', title: '状态', width: 150,align:'center'}
			    		];
		$('#clzxcx-select').on('click', function () {
			findclzxcx();
		});
		function findclzxcx(){
			var all = 0;
			$('#clzxcxTable').jsGrid({
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

//			setTimeout("findclzxcx()",1000);
		}
		function re(filter, callback){
            var startIndex = (filter.pageIndex - 1) * filter.pageSize;
            jqxhr=$.ajax({
            	type: "POST",
				url: "../../wsfw/findclzxcx",
				data:{"stime":$("#clzxcx-stateTime").val(),
					"etime":$("#clzxcx-endTime").val(),
					"vehicle":$("#clzxcx-cphm").val(),
					"company":$("#clzxcx-company").val(),
					"pageIndex":filter.pageIndex,
     				"pageSize":filter.pageSize
					},
				timeout : 180000,
				dataType: 'json',
            }).done(function(data) {
            		var jsycxData = [];
     				all = data[0].count;
         			if(data.length>0){
         				document.getElementById("zxl").innerText="车辆在线率："+(((data[0].count-data[0].datas[0].COUNTBZX)/data[0].count)*100).toFixed(2)+"%";
         				for(var i = 0; i< data[0].datas.length ;i++){
         					var rs={};
         					var vehicle=data[0].datas[i];
         					rs.id =  startIndex+i+1;	
         					rs.gs =  data[0].datas[i].COMP_NAME;
         					rs.cph =  data[0].datas[i].VEHI_NO;
         					rs.sim =  data[0].datas[i].VEHI_SIM;
         					rs.lxr =  data[0].datas[i].OWN_NAME;
         					rs.lxdd = data[0].datas[i].HOME_TEL;
         					rs.time = data[0].datas[i].ONLINE_TIME;
         					rs.zt = data[0].datas[i].LICHENG;
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
		findclzxcx();
		$('#clzxcx-dc').on('click', function () {
			layer.confirm('你确定要导出数据', {
				btn: ['确定', '取消'],
				offset: '100px'
			}, function (layerIndex) {
				window.open(basePath+"wsfw/findclzxcxdc?stime="+$("#clzxcx-stateTime").val()
						+"&etime="+$("#clzxcx-endTime").val()
						+"&vehicle="+$("#clzxcx-cphm").val()
						+"&company="+$("#clzxcx-company").val()
				);
				layer.close(layerIndex);
			}, function (layerIndex) {
				layer.close(layerIndex);
			});
		});
	})
	
})(jQuery)

