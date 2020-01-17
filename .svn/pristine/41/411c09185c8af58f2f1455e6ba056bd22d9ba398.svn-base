var djxxjr=(function ($) {
	var clfb_map;
	$(function () {
		$('#djxx-stateTime').datetimepicker(datetimeDefaultOption);
		$('#djxx-stateTime').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));
		$('#djxx-endTime').datetimepicker(datetimeDefaultOption);
		$('#djxx-endTime').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));
		finddjxx();
		findvehicle('');
		$.fn.modal.Constructor.prototype.enforceFocus = function () {}
		$(document).ready(function() {
			  $(".select2").select2({  
					language: "zh-CN",  //设置 提示语言
			        tags:true,  
			        createTag:function (decorated, params) {  
			            return null;  
			        },  
			    });
			});
		function findvehicle(obj){
			jqxhr=$.ajax({
				type: "POST",
				url: "../../sbwx/xll",
				data:{"table":"vw_vehicle"},
				timeout : 180000,
				dataType: 'json',
				success:function(data){
					$("#djxx-cphm").empty();
					$("#djxx-cphm").append('<option value="" selected>全部车辆</option>');
					for(var i=0; i<data.length;i++){
						if(obj.length>0){
							if(obj == data[i].VEHI_NO){
								$("#djxx-cphm").append('<option value="'+data[i].VEHI_NO+'" selected>'+data[i].VEHI_NO+'</option>');
							}else{
								$("#djxx-cphm").append('<option value="'+data[i].VEHI_NO+'">'+data[i].VEHI_NO+'</option>');
							}
						}else{
							$("#djxx-cphm").append('<option value="'+data[i].VEHI_NO+'">'+data[i].VEHI_NO+'</option>');
						}
					}
				},
				error: function(){
				}         
			});
		}
		
		jqxhr=$.ajax({
			type: "POST",
			url: "../../sbwx/xll",
			data:{"table":"TB_OWNER"},
			timeout : 180000,
			dataType: 'json',
			success:function(data){
//				var data=eval(JSON.stringify(data));
				$("#djxx-block").empty();
				$("#djxx-block").append('<option value="" disabled selected>区块</option>');
				$("#djxx-block").append('<option value="">全部区块</option>');
				for(var i=0; i<data.length;i++){
					$("#djxx-block").append('<option value="'+data[i].OWNER_NAME+'">'+data[i].OWNER_NAME+'</option>');				
				}
			},
			error: function(){
			}         
		});
		jqxhr=$.ajax({
			type: "POST",
			url: "../../sbwx/xll",
			data:{"table":"TB_BUSI_AREA"},
			timeout : 180000,
			dataType: 'json',
			success:function(data){
				$("#djxx-company").empty();
				$("#djxx-company").append('<option value="" disabled selected>业户</option>');
				$("#djxx-company").append('<option value="">全部业户</option>');
				for(var i=0; i<data.length;i++){
					$("#djxx-company").append('<option value="'+data[i].BA_NAME+'">'+data[i].BA_NAME+'</option>');				
				}
			},
			error: function(){
			}         
		});
		jqxhr=$.ajax({
			type: "POST",
			url: "../../sbwx/xll",
			data:{"table":"TB_USER_WX"},
			timeout : 180000,
			dataType: 'json',
			success:function(data){
				$("#djxx-person").empty();
				$("#djxx-person").append('<option value="" disabled selected>维修人员</option>');
				$("#djxx-person").append('<option value="">全部维修人员</option>');
				var reg = RegExp(/wx/);
				for(var i=0; i<data.length;i++){
					if(data[i].USER_NAME.match(reg)&&data[i].REAL_NAME!=null){						
						$("#djxx-person").append('<option value="'+data[i].USER_NAME+'">('+data[i].USER_NAME+')'+data[i].REAL_NAME+'</option>');				
					}
				}
			},
			error: function(){
			}         
		});
		jqxhr=$.ajax({
			type: "POST",
			url: "../../sbwx/xll",
			data:{"table":"TB_REPAIR_TYPE"},
			timeout : 180000,
			dataType: 'json',
			success:function(data){
				$("#djxx-type").empty();
				$("#djxx-type").append('<option value="" disabled selected>维修类型</option>');
				$("#djxx-type").append('<option value="">全部维修类型</option>');
				for(var i=0; i<data.length;i++){
					$("#djxx-type").append('<option value="'+data[i].RT_TYPE+'">'+data[i].RT_TYPE+'</option>');				
				}
			},
			error: function(){
			}         
		});
		jqxhr=$.ajax({
			type: "POST",
			url: "../../sbwx/xll",
			data:{"table":"tb_mdt_type"},
			timeout : 180000,
			dataType: 'json',
			success:function(data){
				$("#djxx-terminal").empty();
				$("#djxx-terminal").append('<option value="" disabled selected>终端类型</option>');
				$("#djxx-terminal").append('<option value="">全部终端类型</option>');
				for(var i=0; i<data.length;i++){
					$("#djxx-terminal").append('<option value="'+data[i].MT_NAME+'">'+data[i].MT_NAME+'</option>');				
				}
			},
			error: function(){
			}         
		});
//		$('#djxx-block').select2({
//			language: 'zh-CN',
//			minimumResultsForSearch: -1,
//			data: [
//			       {id: '0', text: '全部'},
//			       {id: '1', text: '营运'},
//			       {id: '2', text: '停运'},
//			       {id: '3', text: '已转出'},
//			       {id: '4', text: '已转籍'},
//			       {id: '5', text: '已报废'},
//			       {id: '6', text: '检测不合格'},
//			       {id: '7', text: '注销'}
//			       ]
//		});
		var bljlFields = [
				  		    {name: 'sxsj', title: '送修时间', width: 120},
			    			{name: 'gs', title: '业户', width: 120},
			    			{name: 'qk', title: '区块', width: 120},
			    			{name: 'cphm', title: '车牌号', width: 120},
			    			{name: 'zdlx', title: '终端类型', width: 120},
			    			{name: 'zdzlx', title: '终端子类型', width: 120},			    			
			    			{name: 'simk', title: 'SIM卡号', width: 160},
			    			{name: 'zdbh', title: '终端编号', width: 120},
			    			{name: 'czxm', title: '车主姓名', width: 120},
			    			{name: 'lxdh', title: '联系电话', width: 120},
			    			{name: 'gzxx', title: '故障现象', width: 120},
			    			{name: 'wxnr', title: '维修内容', width: 120},
			    			{name: 'wxdd', title: '维修地点', width: 120},
			    			{name: 'wxlx', title: '维修类型', width: 120},
			    			{name: 'wxrxm', title: '维修人姓名', width: 120},
			    			{name: 'wxfy', title: '维修费用', width: 120},
			    		];
		$('#djxx-select').on('click', function () {
			finddjxx();
		});
		function finddjxx(){
			var all = 0;
			$('#djxxTable').jsGrid({
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
				url: "../../sbwx/find",
				data:{"stime":$("#djxx-stateTime").val(),
					"etime":$("#djxx-endTime").val(),
					"vehicle":$("#djxx-cphm").val(),
					"block":$("#djxx-block").val(),
					"company":$("#djxx-company").val(),
					"person":$("#djxx-person").val(),
					"type":$("#djxx-type").val(),
					"terminal":$("#djxx-terminal").val(),
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
         					rs.sxsj = (data[0].datas[i].RR_TIME==null?"":(new Date(data[0].datas[i].RR_TIME)).Format("yyyy-MM-dd hh:mm:ss"));
         					rs.gs =  data[0].datas[i].COMP_NAME;
           					rs.qk =  data[0].datas[i].OWNER_NAME;
         					rs.cphm =  data[0].datas[i].VEHI_NO;
         					rs.zdlx =  data[0].datas[i].MT_NAME;
         					rs.zdzlx =  data[0].datas[i].MDT_SUB_TYPE;	
         					rs.simk =  data[0].datas[i].SIM_NUM;
         					rs.zdbh =  data[0].datas[i].VEHI_SIM;
         					rs.czxm =  data[0].datas[i].OWN_NAME;
         					rs.lxdh =  data[0].datas[i].OWN_TEL;
         					rs.gzxx =  data[0].datas[i].RM_MALFUNCTION;
         					rs.wxnr =  data[0].datas[i].RC_CONTENT;
         					rs.wxdd =  data[0].datas[i].RA_ADDR;
         					rs.wxlx =  data[0].datas[i].RT_TYPE;
         					rs.wxrxm =  data[0].datas[i].WXRY;
         					rs.wxfy =  data[0].datas[i].RR_COST;
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
		$('#djxx-reset').on('click', function () {
			$('.panel-queryBar .form-control').val('');
			$('.panel-queryBar .select2').val('').select2();
		});
	})
})(jQuery)
