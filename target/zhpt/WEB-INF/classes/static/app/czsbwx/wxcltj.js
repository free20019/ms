var wxcltj=(function ($) {
	var clfb_map;
	$(function () {
		$('#wxtj-stateTime').datetimepicker(datetimeDefaultOption);
//		$('#wxtj-stateTime').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));
		$('#wxtj-endTime').datetimepicker(datetimeDefaultOption);
//		$('#wxtj-endTime').val(new Date().Format('yyyy-MM-dd hh:mm:ss'));
//		findvehicle('');
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
//		function findvehicle(obj){
//			jqxhr=$.ajax({
//				type: "POST",
//				url: "../../sbwx/clxll2",
//				data:{"table":"TB_ALARM_REALTIME"},
//				timeout : 180000,
//				dataType: 'json',
////				cache:false, 
////			    async:false, 
//				success:function(data){
//					$("#wxtj-cphm").select2({ 
//						language: "zh-CN",  //设置 提示语言
//				        minimumInputLength: 3,
//				        tags:true,  
//				        allowClear: true,
//				        closeOnSelect: true,
//				        createTag:function (decorated, params) {  
//				            return null;  
//				        },  
//				    });
//					$("#wxtj-cphm").empty();
//					$("#wxtj-cphm").append('<option value="" selected></option>');
//					$("#wxtj-cphm").append('<option value="null">全部车辆</option>');
//					for(var i=0; i<data.length;i++){
//						if(obj.length>0){
//							if(obj == data[i].VEHICLE_NO){
//								$("#wxtj-cphm").append('<option value="'+data[i].VEHICLE_NO+'" selected>'+data[i].VEHICLE_NO+'</option>');
//							}else{
//								$("#wxtj-cphm").append('<option value="'+data[i].VEHICLE_NO+'">'+data[i].VEHICLE_NO+'</option>');
//							}
//						}else{
//							$("#wxtj-cphm").append('<option value="'+data[i].VEHICLE_NO+'">'+data[i].VEHICLE_NO+'</option>');
//						}
//					}
//					if($($($(window.parent.document)).find('#defaultVehicle')).val() != ""){
//						findwxtj();
//						layer.load(2);
//						var value=$($($(window.parent.document)).find('#defaultVehicle')).val();
//						$("#wxtj-cphm").val([value]).trigger("change");
//						$($($(window.parent.document)).find('#defaultVehicle')).val("");
//						layer.closeAll('loading');
////						findwxtj();
//					}
//
//				},
//				error: function(){
//				}         
//			});
//		}
//
//		jqxhr=$.ajax({
//			type: "POST",
//			url: "../../sbwx/xll",
//			data:{"table":"tb_company"},
//			timeout : 180000,
//			dataType: 'json',
//			success:function(data){
//				$("#wxtj-company").empty();
//				$("#wxtj-company").append('<option value="" disabled selected>业户</option>');
//				$("#wxtj-company").append('<option value="null">全部业户</option>');
//				for(var i=0; i<data.length;i++){
//					$("#wxtj-company").append('<option value="'+data[i].COMP_ID+'">'+data[i].COMP_NAME+'</option>');				
//				}
//			},
//			error: function(){
//			}         
//		});
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
				$('#wxtj-cphm').select2({
					data: data,
					language:'zh-CN',
					minimumInputLength: 3,
					allowClear: true
				});
				if($($($(window.parent.document)).find('#defaultVehicle')).val() != ""){
					findwxtj();
					layer.load(2);
					var value=$($($(window.parent.document)).find('#defaultVehicle')).val();
					$("#wxtj-cphm").val([value]).trigger("change");
					$($($(window.parent.document)).find('#defaultVehicle')).val("");
					layer.closeAll('loading');
	//				findwxtj();
				}
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
				$('#wxtj-company').select2({
					data: data,
					allowClear: true
				});
			}
		});
//        jqxhr=$.ajax({
//			type: "POST",
//			url:"../../sbwx/qycomp",
//			data:{},
//			dataType: 'json',
//			timeout : 3600000,
//			success:function(json){
//				var data= json.datacomp;
//				for (var i = 0; i < data.length; i++) {
//					data[i].id=data[i].COMP_NAME;
//					data[i].text=data[i].COMP_NAME;
//				}
//				var qb={};
//				qb.id='null';
//				qb.text='全部';
//				data.unshift(qb);
//				$('#wxtj-company').select2({
//					data: data,
//					allowClear: true
//					});
//			}
//		});	
		
		var bljlFields = [
		                  	{name: 'id', title: '序号', width: 60, align: 'center'},
		                  	{name: 'cphm', title: '车牌号', width: 80, align: 'center'},
			    			{name: 'gs', title: '业户', width: 120, align: 'center'},
			    			{name: 'gzlx', title: '当前故障类型', width: 240, align: 'center'},
			    			{name: 'gzsj', title: '故障发生时间', width: 120, align: 'center'},    			
			    			{name: 'jdxx', title: '维修进度', width: 120, align: 'center'},
			    			{name: 'simk', title: 'SIM卡', width: 120, align: 'center'},
			    			{name: 'cz', title: '车主', width: 80, align: 'center'},
			    			{name: 'sjhm', title: '手机号码', width: 120, align: 'center'},
				  		    {name: 'sxsj', title: '送修时间', width: 120, align: 'center'},
				  		    {name: 'wxsj', title: '完修时间', width: 120, align: 'center'},
				  		    
				  		    {name: 'wxlx', title: '上次维修类型', width: 120, align: 'center'},
			    			{name: 'wxdd', title: '维修地点', width: 120, align: 'center'},
			    			{name: 'wxrxm', title: '维修人姓名', width: 120, align: 'center'},
			    			{name: 'wxfy', title: '维修费用(元)', width: 120, align: 'center'},
			    			{name: 'khmyd', title: '客户满意度', width: 120, align: 'center'},			
			    			{name: 'wxjg', title: '维修结果', width: 120, align: 'center'},
			    			
			    		];
		$('#wxtj-select').on('click', function () {
			findwxtj();
		});
		
//		var findwxtj=function (){
		function findwxtj(){
			//读取defaultVehicle的值
			var all = 0;
			$('#wxtjTable').jsGrid({
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
            var value='';
            if($($($(window.parent.document)).find('#defaultVehicle')).val() != ""){
				value=$($($(window.parent.document)).find('#defaultVehicle')).val();
			}else{
				value=$("#wxtj-cphm").val();
			}
            jqxhr=$.ajax({
            	type: "POST",
				url: "../../sbwx/findwxtj",
				data:{"stime":$("#wxtj-stateTime").val(),
					"etime":$("#wxtj-endTime").val(),
					"vehicle":value,
					"company":$("#wxtj-company").val(),
					"terminal":$("#wxtj-terminal").val(),
					"status":$("#wxtj-status").val(),
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
         					var gz='';
//         					//有定位无营运
        					if (vehicle.NO_GPS!='0'){
        						gz +='有定位无营运;'
        					}	
        					//有营运无定位
        					if (vehicle.NO_JJQ!='0') {
        						gz +='有营运无定位;'
        					}
        					//有抓拍无定位无营运
        					if (vehicle.NO_GPS_JJQ!='0') {
        						gz +='有抓拍无定位无营运;'
        					}
        					//7天无定位无营运
        					if (vehicle.SEVEN_GPS_JJQ!='0') {
        						gz +='7天无定位无营运;'
        					}
        					//空重车无变化
        					if (vehicle.EMPTY_HEAVY!='0') {
        						gz +='空重车无变化;'
        					}
         					rs.id =  startIndex+i+1;
         					rs.gs =  vehicle.COMP_NAME;
         					rs.cphm =  vehicle.VEHICLE_NO;
         					rs.gzlx =  gz;
         					rs.simk =  vehicle.SIM_NUM;
         					rs.cz =  vehicle.OWN_NAME;
         					rs.sjhm =  vehicle.OWN_TEL;      					
         					
//         					if(vehicle.DB_TIME<=vehicle.RR_TIME_END){
         						rs.wxdd =  vehicle.RA_ADDR;
             					rs.wxlx =  vehicle.RT_TYPE;
             					rs.wxrxm =  vehicle.WXRY;
             					rs.wxfy =  vehicle.RR_COST;
             					rs.khmyd =  vehicle.TCSS;
         						rs.sxsj = (vehicle.RR_TIME==null?"":(new Date(vehicle.RR_TIME)).Format("yyyy-MM-dd hh:mm:ss"));
         						rs.wxsj = (vehicle.RR_TIME_END==null?"":(new Date(vehicle.RR_TIME_END)).Format("yyyy-MM-dd hh:mm:ss"));
         						var date=new Date();
         						if(date.getTime()<vehicle.RR_TIME_END){
         							rs.jdxx ='正在维修';
         							rs.wxjg =vehicle.RC_CONTENT;	
         						}else if(gz==''&&vehicle.RR_TIME_END!=null){
         							rs.jdxx ='维修完成';
         							rs.wxjg =vehicle.RC_CONTENT;	
         						}else if(gz!=''){   
         							rs.jdxx = '未维修';
         						}
         						rs.gzsj = (vehicle.DB_TIME==null?"":(new Date(vehicle.DB_TIME)).Format("yyyy-MM-dd hh:mm:ss"));
//         					}else{
//         						rs.wxdd = '';
//             					rs.wxlx = '';
//             					rs.wxrxm = '';
//             					rs.wxfy = '';
//             					rs.khmyd = '';
//         						rs.sxsj = '';
//         						rs.wxsj = '';
//         						rs.jdxx = '未维修';
//             					rs.wxjg = '';
//         					}
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
		if($($($(window.parent.document)).find('#defaultVehicle')).val() == ""){
			findwxtj();
		}
		$('#wxtj-reset').on('click', function () {
			$('.panel-queryBar .form-control').val('');
			$('.panel-queryBar .select2').val([""]).trigger("change");
			$('#wxtj-terminal').html('<option value="" disabled selected>具体异常状态</option>');
		});
		$('#wxtj-dc').on('click', function () {
			layer.confirm('你确定要导出数据', {
				btn: ['确定', '取消'],
				offset: '100px'
			}, function (layerIndex) {
				window.open(basePath+"sbwx/findwxtjdc?stime="+$("#wxtj-stateTime").val()
						+"&etime="+$("#wxtj-endTime").val()
						+"&vehicle="+$("#wxtj-cphm").val()
						+"&company="+$("#wxtj-company").val()
						+"&terminal="+$("#wxtj-terminal").val()
						+"&status="+$("#wxtj-status").val()
					);
				layer.close(layerIndex);
			}, function (layerIndex) {
				layer.close(layerIndex);
			});
		});
	})
})(jQuery)
function findgz(){
	var gz=$('#wxtj-type').val();
	var text='';
	if(gz=='0'){
		text +='<option value="">全部</option>';
	}
	if(gz=='1'){
		text +='<option value="NO_GPS">无定位数据</option>';
//		text +='<option value="LOW_VOLTAGE">终端主电源欠压</option>'
//			  +'<option value="NO_POWER">主电源掉电</option>'
//			  +'<option value="NO_GPS">无定位数据</option>'
//			  +'<option value="NO_UPLOAD">无数据上传</option>';
	}
	if(gz=='2'){
		text +='<option value="MOD_FAULT">定位模块故障</option>'
			  +'<option value="ANT_FAULT">天线短路</option>'
			  +'<option value="INEXACT">非精确定位</option>';
	}
	if(gz=='3'){
		text +='<option value="COMM_FAULT">通讯故障</option>';
	}
	if(gz=='4'){
		text +='<option value="GPS_OVER_BACK">定位回传过密</option>'
			  +'<option value="GPS_NO_BACK">回传数据丢失</option>';
	}
	if(gz=='5'){
		text +='<option value="CAM_OCCLUSION">摄像头遮挡</option>'
			  +'<option value="CAM_NOSIGN">摄像头信号丢失</option>';
	}
	
	if(gz=='6'){
		text +='<option value="HD_FAULT">硬盘故障</option>'
			  +'<option value="SD_FAULT">SD卡故障</option>'
			  +'<option value="VD_FAULT">视频主机故障</option>'
			  +'<option value="VD_EX_FAULT">视频扩展故障</option>';
	}
	if(gz=='7'){
		text +='<option value="METER_DISCONN">计价器连接断开</option>';
	}
	if(gz=='8'){
		text +='<option value="NAV_DISCONN">导航屏断开</option>';
	}
	if(gz=='9'){
		text +='<option value="ST_NO_CHG">空重车不变化</option>'
			  +'<option value="ST_OVER_CHG">空重车切换频繁</option>';
	}
	$('#wxtj-terminal').html(text);
}
